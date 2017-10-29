package com.kuaimi.app.config;

import com.qcloud.weapp.ConfigurationException;
import com.qcloud.weapp.ConfigurationManager;
import org.json.JSONException;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

/**
 * @author : chenwei
 * @create : 2017-10-07 12:34
 */
@Configuration
public class QcloudConfig {
    @Valid("")
    private String macSdkConfigpath;

    public static void setupSDK() {
        try {
            String configFilePath = getConfigFilePath();
            System.out.println("QCloud SDK 配置文件路径：" + configFilePath);

            ConfigurationManager.setupFromFile(configFilePath);
            System.out.println("QCloud SDK 已成功配置！");
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static String getConfigFilePath() {
        String osName = System.getProperty("os.name").toLowerCase();
        String defaultConfigFilePath = null;
        boolean isWindows = osName.indexOf("windows") > -1;
        boolean isLinux = osName.indexOf("linux") > -1;
        boolean isMac = osName.indexOf("mac") > -1;

        if (isWindows) {
            defaultConfigFilePath = "C:\\qcloud\\sdk.config";
        }
        else if (isLinux) {
            defaultConfigFilePath = "/etc/qcloud/sdk.config";
        }
        else if(isMac) {
            defaultConfigFilePath = "";
        }
        return defaultConfigFilePath;
    }
}
