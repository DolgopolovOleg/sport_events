package com.myapp.controller;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.connect.web.DisconnectInterceptor;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


public class SocialController extends ConnectController {
    public SocialController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
    }

    @Override
    public void setInterceptors(List<ConnectInterceptor<?>> interceptors) {
        super.setInterceptors(interceptors);
    }

    @Override
    public void setConnectInterceptors(List<ConnectInterceptor<?>> interceptors) {
        super.setConnectInterceptors(interceptors);
    }

    @Override
    public void setDisconnectInterceptors(List<DisconnectInterceptor<?>> interceptors) {
        super.setDisconnectInterceptors(interceptors);
    }

    @Override
    public void setApplicationUrl(String applicationUrl) {
        super.setApplicationUrl(applicationUrl);
    }

    @Override
    public void setViewPath(String viewPath) {
        super.setViewPath(viewPath);
    }

    @Override
    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        super.setSessionStrategy(sessionStrategy);
    }

    @Override
    public void addInterceptor(ConnectInterceptor<?> interceptor) {
        super.addInterceptor(interceptor);
    }

    @Override
    public void addDisconnectInterceptor(DisconnectInterceptor<?> interceptor) {
        super.addDisconnectInterceptor(interceptor);
    }

    @Override
    public String connectionStatus(NativeWebRequest request, Model model) {
        return super.connectionStatus(request, model);
    }

    @Override
    public String connectionStatus(@PathVariable String providerId, NativeWebRequest request, Model model) {
        String connectionStatus = super.connectionStatus(providerId, request, model);
        return connectionStatus;
    }

    @Override
    public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) {
        return super.connect(providerId, request);
    }

    @Override
    public RedirectView oauth1Callback(@PathVariable String providerId, NativeWebRequest request) {
        return super.oauth1Callback(providerId, request);
    }

    @Override
    public RedirectView oauth2Callback(@PathVariable String providerId, NativeWebRequest request) {
        return super.oauth2Callback(providerId, request);
    }

    @Override
    public RedirectView oauth2ErrorCallback(@PathVariable String providerId, @RequestParam("error") String error, @RequestParam(value = "error_description", required = false) String errorDescription, @RequestParam(value = "error_uri", required = false) String errorUri, NativeWebRequest request) {
        return super.oauth2ErrorCallback(providerId, error, errorDescription, errorUri, request);
    }

    @Override
    public RedirectView removeConnections(@PathVariable String providerId, NativeWebRequest request) {
        return super.removeConnections(providerId, request);
    }

    @Override
    public RedirectView removeConnection(@PathVariable String providerId, @PathVariable String providerUserId, NativeWebRequest request) {
        return super.removeConnection(providerId, providerUserId, request);
    }

    @Override
    protected String connectView() {
        return super.connectView();
    }

    @Override
    protected String connectView(String providerId) {
        return super.connectView(providerId);
    }

    @Override
    protected String connectedView(String providerId) {
        return super.connectedView(providerId);
    }

    @Override
    protected RedirectView connectionStatusRedirect(String providerId, NativeWebRequest request) {
        return super.connectionStatusRedirect(providerId, request);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
    }

}
