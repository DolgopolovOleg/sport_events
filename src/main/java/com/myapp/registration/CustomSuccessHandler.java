package com.myapp.registration;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.context.request.WebRequest;

public class CustomSuccessHandler extends
        SavedRequestAwareAuthenticationSuccessHandler{


    @Autowired
    WebRequest webRequest;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        super.onAuthenticationSuccess(request, response, authentication);

        HttpSession session = request.getSession(true);
        Connection<?> connection = ProviderSignInUtils.getConnection(webRequest);
    }
}
