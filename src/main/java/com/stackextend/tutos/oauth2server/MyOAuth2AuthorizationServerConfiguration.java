package com.stackextend.tutos.oauth2server;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableAuthorizationServer
public class MyOAuth2AuthorizationServerConfiguration extends OAuth2AuthorizationServerConfiguration {

    public MyOAuth2AuthorizationServerConfiguration(BaseClientDetails details,
            AuthenticationConfiguration authenticationConfiguration,
            ObjectProvider<TokenStore> tokenStore,
            ObjectProvider<AccessTokenConverter> tokenConverter,
            AuthorizationServerProperties properties) throws Exception {
        super(details, authenticationConfiguration, tokenStore, tokenConverter, properties);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
        security.allowFormAuthenticationForClients();;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints.tokenEnhancer(new TokenEnhancer() {

            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                if (authentication.getPrincipal() instanceof User) {
                    User user = (User) authentication.getPrincipal();

                    final Set<String> scopes = new HashSet<String>();
                    for (GrantedAuthority authority : user.getAuthorities()) {
                        String role = authority.getAuthority();

                        if (role.startsWith("ROLE_")) {
                            scopes.add(role.substring(5).toLowerCase());
                        }
                        else {
                            scopes.add(role.toLowerCase());
                        }
                    }
                    ((DefaultOAuth2AccessToken) accessToken).setScope(scopes);

                }
                return accessToken;
            }
        });
    }

}
