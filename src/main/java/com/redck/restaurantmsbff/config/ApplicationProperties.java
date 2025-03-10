package com.redck.restaurantmsbff.config;

import liquibase.logging.mdc.MdcManager;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties
{

    private final MsManager msManager = new MsManager();

    public MsManager getMsManager()
    {
        return msManager;
    }

    public static class MsManager
    {

        private String url = "http://192.168.1.44:8181/restaurant/manager";

        /**
         * Get Ms Manager Url.
         * @return Ms Manager Url.
         */
        public String getUrl()
        {
            return url;
        }

        /**
         * Set Ms Manager Url.
         * @param url Ms Manager Url.
         */
        public void setUrl(final String url)
        {
            this.url = url;
        }

    }

}
