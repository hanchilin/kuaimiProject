package com.kuaimi.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : chenwei
 * @create : 2017-10-05 22:12
 */
@Service
public class UserService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername (String s) throws UsernameNotFoundException {
        return null;
    }
}
