package com.kuaimi.security.auth;

import java.io.Serializable;

/**
 * @author : chenwei
 * @create : 2017-10-07 12:51
 */
public class AuthDetailsInfo implements Serializable {

    private final String token;
    private final String ipAddress;

    public AuthDetailsInfo(String token, String ipAddress) {
        this.token = token;
        this.ipAddress = ipAddress;
    }

    public String getToken() {
        return token;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}
