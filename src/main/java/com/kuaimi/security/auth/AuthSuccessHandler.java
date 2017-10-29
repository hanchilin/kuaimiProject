package com.kuaimi.security.auth;

import com.kuaimi.domain.entity.User;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : chenwei
 * @create : 2017-10-07 12:46
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private final Logger log = LoggerFactory.getLogger(AuthSuccessHandler.class);
    private static final int TWO_MONTHS_IN_SEC = 60*60*24*60;

    public AuthSuccessHandler() {
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication a) throws IOException, ServletException {
        User user = (User) a.getPrincipal();
        log.debug("Successful login attempt by {}. Setting language code to: {}", user.getPhone(), user.getName());
//        addCookie("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE", user.getName(), response);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");

        addAuthCookie(a, response, request);

        JSONObject resp = new JSONObject();
        resp.put("name", user.getName());
        resp.put("phone", user.getPhone());
        resp.put("authenticated", true);
        response.getWriter().write(resp.toString());
        response.getWriter().flush();
    }

    private void addAuthCookie(Authentication a, HttpServletResponse resp, HttpServletRequest request) {
        AuthDetailsInfo details = (AuthDetailsInfo) a.getDetails();
        final Cookie cookie = new Cookie(AuthCookieUtil.NAME, details.getToken());
        cookie.setPath("/");
        cookie.setMaxAge(TWO_MONTHS_IN_SEC);
        if(!request.getRequestURL().toString().contains("localhost")){
            cookie.setSecure(true); //sending this to any remote host should only be done through https
        }
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
    }

    private Cookie addCookie(String name, String value, HttpServletResponse resp){
        final Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(TWO_MONTHS_IN_SEC);
        resp.addCookie(cookie);
        return cookie;
    }
}
