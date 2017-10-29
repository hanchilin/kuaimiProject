package com.kuaimi.controller.login;

import com.qcloud.weapp.ConfigurationException;
import com.qcloud.weapp.authorization.LoginService;
import com.qcloud.weapp.authorization.LoginServiceException;
import com.qcloud.weapp.authorization.UserInfo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : chenwei
 * @create : 2017-10-04 15:23
 */
@Controller
@Scope("request")
public class LoginController {

    @RequestMapping("/wx-login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过 ServletRequest 和 ServletResponse 初始化登录服务
        LoginService service = new LoginService(request, response);
        try {
            // 调用登录接口，如果登录成功可以获得登录信息
            UserInfo userInfo = service.login();
            System.out.println("========= LoginSuccess, UserInfo: ==========");
            System.out.println(userInfo.toString());
        } catch (LoginServiceException e) {
            // 登录失败会抛出登录失败异常
            e.printStackTrace();
        } catch (ConfigurationException e) {
            // SDK 如果还没有配置会抛出配置异常
            e.printStackTrace();
        }
    }
}
