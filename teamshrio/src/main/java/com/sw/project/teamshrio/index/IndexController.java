package com.sw.project.teamshrio.index;

import com.sw.project.teamshrio.user.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Controller
public class IndexController {
    private Logger logger= LoggerFactory.getLogger(IndexController.class);
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("/login")
    public String  login(UserModel userModel, HttpServletRequest request){
        logger.info("login ifno ,userName:{}",userModel.getUserName());
        //验证数据session 是否存在
        //验证用户在数据中书否存在
        //放入session 中心（异步通知）
        //跳转到shrio系统菜单获取数据地方
        if(null==userModel || StringUtils.isEmpty(userModel.getUserName()) || StringUtils.isEmpty(userModel.getUserPwd())){
            return "redirect:/";
        }
        request.setAttribute("user",userModel);
        return "main";

    }


}
