package com.myapp.service.impl;

import com.myapp.dao.UserConnectionDao;
import com.myapp.registration.UserConnectionRepositoryImpl;
import com.myapp.service.UserConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userConnectionService")
@Transactional
public class UserConnectionServiceImpl implements UserConnectionService {


    @Autowired
    HttpServletRequest request;

    @Autowired
    UserConnectionDao userConnectionDao;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    private TextEncryptor textEncryptor;

    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        ConnectionKey key = connection.getKey();
        return userConnectionDao.findUserIdsByProviderIdAndProviderUserId(key.getProviderId(), key.getProviderUserId());
    }

    @Override
    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        return new HashSet<String>(userConnectionDao.findUserIdsByProviderIdAndProviderUserIds(providerId, providerUserIds));
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        return new UserConnectionRepositoryImpl(
                userId,
                userConnectionDao,
                connectionFactoryLocator,
                (true ? textEncryptor : null)
        );
    }
}
