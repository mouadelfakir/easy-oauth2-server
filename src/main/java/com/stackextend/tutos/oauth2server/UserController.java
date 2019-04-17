package com.stackextend.tutos.oauth2server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    ConsumerTokenServices tokenServices;

    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @RequestMapping({ "/user", "/me" })
    public Map<String, String> user(Principal principal) {
        return Collections.singletonMap("name", principal.getName());
    }

    @RequestMapping("/revoke_token")
    public boolean revokeToken() {
        final OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        final String token = tokenStore().getAccessToken(auth).getValue();
        return tokenServices.revokeToken(token);
    }
}
