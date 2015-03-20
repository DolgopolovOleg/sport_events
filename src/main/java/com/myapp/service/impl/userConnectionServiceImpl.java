package com.myapp.service.impl;

import com.myapp.dao.UserConnectionDao;
import com.myapp.entity.UserConnection;
import com.myapp.registration.UserConnectionRepositoryImpl;
import com.myapp.service.UserConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.*;
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

    @Override
    public UserConnection findUserConnectionByProviderIdAndProviderUserId(String providerId, String providerUserId) {
        return null;
    }

    @Override
    public List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId) {
        return userConnectionDao.findUserIdsByProviderIdAndProviderUserId(providerId, providerUserId);
    }


    @Transactional(readOnly = false)
    @Override
    public void addConnection(Connection<?> connection, String userId) {
        ConnectionData connectionData = connection.createData();
        // check if this social account is already connected to a local account
        List<String> userIds = userConnectionDao.findUserIdsByProviderIdAndProviderUserId(connectionData.getProviderId(), connectionData.getProviderUserId());
        if (!userIds.isEmpty()) {
            throw new DuplicateConnectionException(new ConnectionKey(connectionData.getProviderId(), connectionData.getProviderUserId()));
        }

        //check if this user already has a connected account for this provider
        List<UserConnection> socialUsers = userConnectionDao.findByUserIdAndProviderId(userId, connectionData.getProviderId());
        if (!socialUsers.isEmpty()) {
            throw new DuplicateConnectionException(new ConnectionKey(connectionData.getProviderId(), connectionData.getProviderUserId()));
        }

        Integer maxRank = userConnectionDao.selectMaxRankByUserIdAndProviderId(userId, null);
        int nextRank = (maxRank == null ? 0 : maxRank + 1);

        UserConnection socialUser = new UserConnection();
//        userId = userService.getLoggedUser().getUserId();
        socialUser.setUserId(userId);
        socialUser.setProviderId(connectionData.getProviderId());
        socialUser.setProviderUserId(connectionData.getProviderUserId());
        socialUser.setRank(nextRank);
        socialUser.setDisplayName(connectionData.getDisplayName());
        socialUser.setProfileUrl(connectionData.getProfileUrl());

        String pic = connectionData.getImageUrl();


        socialUser.setImageUrl(pic);
//        if(connectionData.getProviderId().equals(vk){
//            //this setup will be done in postsignup
//            socialUser.setImageUrl(pic);
//        }
//        else
//        if(connectionData.getProviderId().equals(fb)){
//            socialUser.setImageUrl(pic+Constants.FB_IMG_S);
//        }



        // encrypt these values
        socialUser.setAccessToken(encrypt(connectionData.getAccessToken()));
        socialUser.setSecret(encrypt(connectionData.getSecret()));
        socialUser.setRefreshToken(encrypt(connectionData.getRefreshToken()));

        socialUser.setExpireTime(connectionData.getExpireTime());

        try {
            userConnectionDao.save(socialUser);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateConnectionException(new ConnectionKey(connectionData.getProviderId(), connectionData.getProviderUserId()));
        }


    }

    private String encrypt(String text) {
        return (textEncryptor != null && text != null) ? textEncryptor.encrypt(text) : text;
    }

}
