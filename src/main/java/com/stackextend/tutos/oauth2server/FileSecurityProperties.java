package com.stackextend.tutos.oauth2server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

@PropertySource("classpath:oauth2.properties")
@ConfigurationProperties(prefix="security.authentication.file")
public class FileSecurityProperties {

    private boolean enabled = false;

    private Properties users;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Properties getUsers() {
        return users;
    }

    public void setUsers(Properties users) {
        this.users = users;
    }

}
