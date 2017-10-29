package com.kuaimi.security.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : chenwei
 * @create : 2017-10-07 12:53
 */
public class AuthCookieUtil {

    public static String NAME = "AuthToken";
    public static String SESSION_NAME = "JSESSIONID";

    public static void removeAuthCookie(HttpServletResponse resp){
        //remove the auth cookie if they have it.
        Cookie cookie = new Cookie(NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
    }
}
