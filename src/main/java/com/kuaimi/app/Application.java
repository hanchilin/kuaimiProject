package com.kuaimi.app;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author : chenwei
 * @create : 2017-10-04 14:48
 */
@Import({})
@SpringBootApplication(scanBasePackages="com.kuaimi")
public class Application extends WebMvcConfigurerAdapter {

    @Value("${deployment.environment}")
    private String deploymentEnvironment;

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main (String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /*@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/app/**").addResourceLocations("/app/");
        registry.addResourceHandler("/js/**").addResourceLocations("/app/");
        registry.addResourceHandler("/lib/**").addResourceLocations("/lib/");
        registry.addResourceHandler("/bower_components/**").addResourceLocations("/bower_components/");
        super.addResourceHandlers(registry);
    }*/

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");
        super.addViewControllers(registry);
    }

/*
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new KuaiMiMessageSource();
        ms.setBasenames("/WEB-INF/i18n/messages");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }


    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/app/");
        resolver.setSuffix(".html");
        return resolver;
    }*/

    /**
     * This is just a environment property for now. Spring has more elaborate
     * environment support but is overkill for this scenario right now.
     *
     * @return current environment (Development, QA, Demo, Production)
     */
    @Bean(name = "deploymentEnvironment")
    public DeploymentEnvironment deploymentEnvironment() {
        log.debug("Property value for deploymentEnvironment: {}", deploymentEnvironment);
        if (StringUtils.isNotEmpty(deploymentEnvironment)) {
            return DeploymentEnvironment.valueOf(deploymentEnvironment);
        } else {
            log.warn("deploymentEnvironment property not set, defaulting to DEVELOPMENT");
            return DeploymentEnvironment.DEVELOPMENT;
        }
    }

}
