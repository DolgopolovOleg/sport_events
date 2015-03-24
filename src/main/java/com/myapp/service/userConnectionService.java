package com.myapp.service;


import com.myapp.entity.UserConnection;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UsersConnectionRepository;

import java.util.List;
import java.util.Set;

public interface UserConnectionService extends UsersConnectionRepository {

    UserConnection findUserConnectionByProviderIdAndProviderUserId(String providerId, String providerUserId);
    List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId);
    void addConnection(Connection<?> connection, String userId);
    Connection<?> getConnection(ConnectionKey connectionKey);
    String findUserIdByConnection(Connection connection);
}
