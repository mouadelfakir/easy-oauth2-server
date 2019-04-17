package com.stackextend.tutos.oauth2server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@Import(FileSecurityProperties.class)
@Order(1)
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FileSecurityProperties fileSecurityProperties;

    @Bean
    @Override
    public UserDetailsService userDetailsService () {
        final InMemoryUserDetailsManager inMemory = new InMemoryUserDetailsManager(this.fileSecurityProperties.getUsers());
        return inMemory;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers()
                .antMatchers("/login", "/oauth/authorize")
                .and()
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll();
    }
}
