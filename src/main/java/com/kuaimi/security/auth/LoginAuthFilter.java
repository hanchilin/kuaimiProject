package com.kuaimi.security.auth;

import com.qcloud.weapp.ConfigurationException;
import com.qcloud.weapp.authorization.LoginService;
import com.qcloud.weapp.authorization.LoginServiceException;
import com.qcloud.weapp.authorization.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author : chenwei
 * @create : 2017-10-05 22:26
 */
public class LoginAuthFilter extends AbstractAuthenticationProcessingFilter {
    public static final String DEFAULT_PROCESSING_URL = "/login";
    private final Logger log = LoggerFactory.getLogger(LoginAuthFilter.class);

    public LoginAuthFilter(AuthenticationProvider authenticationProvider) {
        super(DEFAULT_PROCESSING_URL);
        setAuthenticationManager(new ProviderManager(Arrays.asList(authenticationProvider)));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (((HttpServletRequest) req).getMethod().equals("POST")) {
            super.doFilter(req, res, chain);
        } else {
            HttpServletResponse resp = (HttpServletResponse) res;
            resp.setHeader("Access-Control-Allow-Origin", "*");
            chain.doFilter(req, res);
        }
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.debug("Attempting to get email/password from request body.");

        LoginService service = new LoginService(request, response);
        try {
            // 调用登录接口，如果登录成功可以获得登录信息
            UserInfo userInfo = service.login();
            System.out.println("========= LoginSuccess, UserInfo: ==========");
            System.out.println(userInfo.toString());

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userInfo.getOpenId(), userInfo.getNickName());
            return getAuthenticationManager().authenticate(token);
        } catch (LoginServiceException e) {
            // 登录失败会抛出登录失败异常
            e.printStackTrace();
            throw new AuthenticationServiceException("login error :{}", e);
        } catch (ConfigurationException e) {
            // SDK 如果还没有配置会抛出配置异常
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
