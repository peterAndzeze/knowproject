package com.sw.project.teamshrio.admin;

import com.sw.project.teamshrio.captcha.CaptchaHelper;
import com.sw.project.teamshrio.client.filter.TeamshrioFilter;
import com.sw.project.teamshrio.common.TeamShiroConstant;
import com.sw.project.teamshrio.framework.spring.BaseController;
import com.sw.project.teamshrio.framework.spring.ReturnMsgModel;
import com.sw.project.teamshrio.framework.token.LoginUser;
import com.sw.project.teamshrio.framework.token.TokenManager;
import com.sw.project.teamshrio.provider.IdProvider;
import com.sw.project.teamshrio.provider.PasswordProvider;
import com.sw.project.teamshrio.user.UserModel;
import com.sw.project.teamshrio.user.UserService;
import com.sw.project.teamshrio.util.CookieUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author sw
 * @Title: LoginController
 * @ProjectName knowproject
 * @Description: 跳转登录
 * @date 18-11-26 下午5:12
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
    private static final String LOGIN_PATH="/login";
    @Resource
    private TokenManager tokenManager;
    @Resource
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String login( String backUrl, HttpServletRequest request) {
        System.out.println("loginGet**********************");
        String token = CookieUtils.getCookie(request, TokenManager.TOKEN);
        if (!StringUtils.isEmpty(token) && tokenManager.validate(token) != null) {
            return "redirect:" + authBackUrl(backUrl, token);
        }
        else {
            return goLoginPath(backUrl, request);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String login(
             String backUrl,UserModel userModel, String captcha,
            HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println("loginpost**********************");
        /*if (!CaptchaHelper.validate(request, captcha)) {
            request.setAttribute("errorMessage", "验证码不正确");
            return goLoginPath(backUrl, request);
        }*/
        ReturnMsgModel result = userService.login(getIpAddr(request), userModel.getUserName(), PasswordProvider.encrypt(userModel.getUserPwd()));
        if (!result.getCode().equals(TeamShiroConstant.SUCCESS)) {
            request.setAttribute("errorMessage", result.getMsg());
            return goLoginPath(backUrl, request);
        }
        else {
            UserModel user = (UserModel) result.getData();
            LoginUser loginUser = new LoginUser(user.getId(), user.getUserName());
            String token = CookieUtils.getCookie(request, TokenManager.TOKEN);
            if (StringUtils.isEmpty(token) || tokenManager.validate(token) == null) {// 没有登录的情况
                token = createToken(loginUser);
                addTokenInCookie(token, request, response);
            }

            // 跳转到原请求
            backUrl = URLDecoder.decode(backUrl, "utf-8");
            return "redirect:" + authBackUrl(backUrl, token);
        }
    }

    private String goLoginPath(String backUrl, HttpServletRequest request) {
        request.setAttribute("backUrl", backUrl);
        return LOGIN_PATH;
    }

    private String authBackUrl(String backUrl, String token) {
        StringBuilder sbf = new StringBuilder(backUrl);
        if (backUrl.indexOf("?") > 0) {
            sbf.append("&");
        }
        else {
            sbf.append("?");
        }
        sbf.append(TeamshrioFilter.SSO_TOKEN_NAME).append("=").append(token);
        return sbf.toString();
    }

    private String createToken(LoginUser loginUser) {
        // 生成token
        String token = IdProvider.createUUIDId();

        // 缓存中添加token对应User
        tokenManager.addToken(token, loginUser);
        return token;
    }

    private void addTokenInCookie(String token, HttpServletRequest request, HttpServletResponse response) {
        // Cookie添加token
        Cookie cookie = new Cookie(TokenManager.TOKEN, token);
        cookie.setPath("/");
        if ("https".equals(request.getScheme())) {
            cookie.setSecure(true);
        }
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    @Override
    public String getPath(HttpServletRequest request) {
        return null;
    }
}


