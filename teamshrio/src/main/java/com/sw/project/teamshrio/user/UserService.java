package com.sw.project.teamshrio.user;

import com.sw.project.teamshrio.common.TeamShiroConstant;
import com.sw.project.teamshrio.common.TrueFalseEnum;
import com.sw.project.teamshrio.framework.spring.ReturnMsgModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户信息
 */
@Service
public class UserService {
    @Autowired
    UserModelMapper userModelMapper;
    /**
     *
     * @param userName
     * @param userPwd
     * @return
     */
    public UserModel selectByNameAndPwd(String userName,String userPwd){
        return userModelMapper.selectByNameAndPwd(userName,userPwd);
    }

    public ReturnMsgModel login(String ip, String account, String password) {
        ReturnMsgModel result = new ReturnMsgModel();
        UserModel user = selectByNameAndPwd(account,password);
        if (user == null) {
            result.setCode(TeamShiroConstant.ERROR).setMsg("登录名不存在");
        }
        if(user.getState().equals(TeamShiroConstant.SYS_DATA_INVALID)){
            result.setCode(TeamShiroConstant.ERROR).setMsg("用户已过期请重新注册");
        }
        else if (!user.getUserPwd().equals(password)) {
            result.setCode(TeamShiroConstant.ERROR).setMsg("密码不正确");
        }
        else if (TrueFalseEnum.FALSE.getValue().equals(user.getState())) {
            result.setCode(TeamShiroConstant.ERROR).setMsg("已被用户禁用");
        }
        else {
            //user.setLastLoginIp(ip);
            //user.setLoginCount(user.getLoginCount() + 1);
            //user.setLastLoginTime(new Date());
            //dao.update(user);
            result.setData(user);
        }
        return result;
    }

}
