package com.kuaimi.security;

import com.kuaimi.security.auth.AuthFailureHandler;
import com.kuaimi.security.auth.AuthSuccessHandler;
import com.kuaimi.security.auth.LoginAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author : chenwei
 * @create : 2017-10-04 14:51
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//开启security的注解，在需要控制权限的方法上面使用@PreAuthorize，@PreFilter这些注解
@ComponentScan(basePackages = "com.kuaimi")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationProvider remoteAuthProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(
                "/bootstrap/**",
                "/css/**",
                "/documentation/**",
                "/fonts/**",
                "/js/**",
                "/pages/**",
                "/plugins/**",
                "/home",
                "/hello",
                "/",
                "/hello/**"
            ).permitAll() //默认不拦截静态资源的url pattern
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            .logout().logoutSuccessUrl("/")
            .permitAll();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("root")
                .roles("USER")
            .and()
                .withUser("admin").password("admin")
                .roles("ADMIN", "USER")
            .and()
                .withUser("user").password("password")
                .roles("USER");
    }

    @Bean
    public LoginAuthFilter loginAuthFilter(){
        LoginAuthFilter jsonAuthFilter = new LoginAuthFilter(remoteAuthProvider);
        jsonAuthFilter.setAuthenticationSuccessHandler(new AuthSuccessHandler());
        jsonAuthFilter.setAuthenticationFailureHandler(new AuthFailureHandler());
        return jsonAuthFilter;
    }
}
