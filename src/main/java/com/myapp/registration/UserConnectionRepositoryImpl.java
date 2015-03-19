package com.myapp.registration;

import com.myapp.common.SocialMediaService;
import com.myapp.dao.UserConnectionDao;
import com.myapp.entity.UserConnection;
import com.myapp.service.UserConnectionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
public class UserConnectionRepositoryImpl implements ExtendedConnectionRepository {

    private String userId;
    private UserConnectionDao userConnectionDao;
    private ConnectionFactoryLocator connectionFactoryLocator;
    private TextEncryptor textEncryptor;

    @Resource(name = "userConnectionService")
    private UserConnectionService socialUserService;

    private String vk = SocialMediaService.VKONTAKTE.name().toLowerCase();
    private String fb = SocialMediaService.FACEBOOK.name().toLowerCase();

    public UserConnectionRepositoryImpl(String userId,
                                        UserConnectionDao userConnectionDao,
                                        ConnectionFactoryLocator connectionFactoryLocator,
                                        TextEncryptor textEncryptor) {
        this.userId = userId;
        this.userConnectionDao = userConnectionDao;
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.textEncryptor = textEncryptor;
    }

    public MultiValueMap<String, Connection<?>> findAllConnections() {
        MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();

        List<UserConnection> allUserConnection = userConnectionDao.findByUserId(userId);
        for (UserConnection userConnection : allUserConnection) {
            ConnectionData connectionData = toConnectionData(userConnection);
            Connection<?> connection = createConnection(connectionData);
            connections.add(connectionData.getProviderId(), connection);
        }

        return connections;
    }

    public List<Connection<?>> findConnections(String providerId) {
        List<Connection<?>> connections = new ArrayList<Connection<?>>();

        List<UserConnection> socialUsers = userConnectionDao.findByUserIdAndProviderId(userId, providerId);
        for (UserConnection socialUser : socialUsers) {

            if((socialUser.getExpireTime() == null)||(socialUser.getExpireTime() > System.currentTimeMillis())){
                ConnectionData connectionData = toConnectionData(socialUser);
                Connection<?> connection = createConnection(connectionData);
                connections.add(connection);
            }
            else{
                userConnectionDao.delete(socialUser);
            }
        }

        return connections;
    }

    public <A> List<Connection<A>> findConnections(Class<A> apiType) {
        String providerId = connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();

        // do some lame stuff to make the casting possible
        List<?> connections = findConnections(providerId);
        return (List<Connection<A>>) connections;
    }

    public MultiValueMap<String, Connection<?>> findConnectionsToUsers(MultiValueMap<String, String> providerUserIds) {
        MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();

        List<UserConnection> allSocialUsers = userConnectionDao.findByUserIdAndProviderUserIds(userId, providerUserIds);
        for (UserConnection socialUser : allSocialUsers) {
            ConnectionData connectionData = toConnectionData(socialUser);
            Connection<?> connection = createConnection(connectionData);
            connections.add(connectionData.getProviderId(), connection);
        }

        return connections;
    }

    public Connection<?> getConnection(ConnectionKey connectionKey) {
        UserConnection socialUser = userConnectionDao.get(userId, connectionKey.getProviderId(), connectionKey.getProviderUserId());
        if (socialUser == null) {
            throw new NoSuchConnectionException(connectionKey);
        }
        return createConnection(toConnectionData(socialUser));
    }

    public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
        String providerId = connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
        UserConnection socialUser = userConnectionDao.get(userId, providerId, providerUserId);
        if (socialUser == null) {
            throw new NoSuchConnectionException(new ConnectionKey(providerId, providerUserId));
        }
        return (Connection<A>) createConnection(toConnectionData(socialUser));
    }

    public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
        Connection<A> connection = findPrimaryConnection(apiType);
        if (connection == null) {
            String providerId = connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
            throw new NotConnectedException(providerId);
        }
        return connection;
    }

    public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
        String providerId = connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();

        List<UserConnection> socialUsers = userConnectionDao.findPrimaryByUserIdAndProviderId(userId, providerId);
        Connection<A> connection = null;
        if (socialUsers != null && !socialUsers.isEmpty()) {
            connection = (Connection<A>) createConnection(toConnectionData(socialUsers.get(0)));
        }

        return connection;
    }

    @Transactional(readOnly = false)
    public void addConnection(Connection<?> connection) {
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

    @Transactional(readOnly = false)
    public void updateConnection(Connection<?> connection) {
        ConnectionData connectionData = connection.createData();
        UserConnection socialUser = userConnectionDao.get(userId, connectionData.getProviderId(), connectionData.getProviderUserId());
        if (socialUser != null) {
            socialUser.setDisplayName(connectionData.getDisplayName());
            socialUser.setProfileUrl(connectionData.getProfileUrl());


            socialUser.setImageUrl(connectionData.getImageUrl());

            socialUser.setAccessToken(encrypt(connectionData.getAccessToken()));
            socialUser.setSecret(encrypt(connectionData.getSecret()));
            socialUser.setRefreshToken(encrypt(connectionData.getRefreshToken()));

            socialUser.setExpireTime(connectionData.getExpireTime());
            //socialUserDAO.save(socialUser);
            userConnectionDao.update(socialUser);
        }
    }

    @Transactional(readOnly = false)
    public void removeConnections(String providerId) {
        // TODO replace with bulk delete HQL
        List<UserConnection> socialUsers = userConnectionDao.findByUserIdAndProviderId(userId, providerId);
        for (UserConnection socialUser : socialUsers) {
            userConnectionDao.delete(socialUser);
        }
    }

    @Transactional(readOnly = false)
    public void removeConnection(ConnectionKey connectionKey) {
        UserConnection socialUser = userConnectionDao.get(userId, connectionKey.getProviderId(), connectionKey.getProviderUserId());
        if (socialUser != null) {
            userConnectionDao.delete(socialUser);
        }
    }

    private ConnectionData toConnectionData(UserConnection socialUser) {
        return new ConnectionData(socialUser.getProviderId(),
                socialUser.getProviderUserId(),
                socialUser.getDisplayName(),
                socialUser.getProfileUrl(),
                socialUser.getImageUrl(),

                decrypt(socialUser.getAccessToken()),
                decrypt(socialUser.getSecret()),
                decrypt(socialUser.getRefreshToken()),

                convertZeroToNull(socialUser.getExpireTime()));
    }

    private Connection<?> createConnection(ConnectionData connectionData) {
        ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId());
        return connectionFactory.createConnection(connectionData);
    }

    private Long convertZeroToNull(Long expireTime) {
        return (expireTime != null && expireTime == 0 ? null : expireTime);
    }

    private String decrypt(String encryptedText) {
        return (textEncryptor != null && encryptedText != null) ? textEncryptor.decrypt(encryptedText) : encryptedText;
    }

    private String encrypt(String text) {
        return (textEncryptor != null && text != null) ? textEncryptor.encrypt(text) : text;
    }
}
