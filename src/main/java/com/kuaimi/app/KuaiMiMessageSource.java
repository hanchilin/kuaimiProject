package com.kuaimi.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.text.MessageFormat;
import java.util.Locale;

import static com.kuaimi.app.DeploymentEnvironment.DEVELOPMENT;
import static com.kuaimi.app.DeploymentEnvironment.QA;

/**
 * @author : chenwei
 * @create : 2017-10-04 16:30
 */
public class KuaiMiMessageSource extends ReloadableResourceBundleMessageSource {

    private final Logger log = LoggerFactory.getLogger(KuaiMiMessageSource.class);
    @Autowired
    Application webConfig;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        MessageFormat format = super.resolveCode(code, locale);
        return format;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String format = lookupRawCode(code, locale);
        if (webConfig.deploymentEnvironment() == DEVELOPMENT
            || webConfig.deploymentEnvironment() == QA) {
            format = "<span data-prop-message title=" + code + ">" + format + "</span>";
        } else {
            format = "<span data-prop-message>"+format+"</span>";
        }
        return format;
    }

    public String lookupRawCode(String code, Locale locale) {
        String format = super.resolveCodeWithoutArguments(code, locale);
        return format;
    }
}
