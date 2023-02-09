package com.geteWay.controller;

import com.geteWay.models.AuthRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    ResponseEntity<AuthRespone> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,//isse hume  exccess token,expire
            @AuthenticationPrincipal OidcUser user,//id vagra,athoritis
            Model model
            ){
user.getEmail();
//creating AuthResponse
AuthRespone authRespone=new AuthRespone();
//setting email to auth respone
authRespone.setUserId(user.getEmail());
        //setting access token to respone
authRespone.setAccessTokenl(client.getAccessToken().getTokenValue());
authRespone.setRefreshToken(client.getRefreshToken().getTokenValue());
authRespone.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
authRespone.setAuthorititis(user.getAuthorities().stream().map(grantedAuthority -> {return  grantedAuthority.getAuthority();}).collect(Collectors.toList()));
   return new ResponseEntity<>(authRespone, HttpStatus.OK);
    }
}
