package com.myapp.dao;


import com.myapp.entity.UserConnection;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Set;

public interface UserConnectionDao {

    public void save(UserConnection socialUser);

    public void update(UserConnection socialUser);

    public List<UserConnection> findByProviderId(String providerId);

    public List<UserConnection> findByUserId(String userId);

    public List<UserConnection> findByUserIdAndProviderId(String userId, String providerId);

    public List<UserConnection> findByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds);

    public UserConnection get(String userId, String providerId, String providerUserId);

    public List<UserConnection> findPrimaryByUserIdAndProviderId(String userId, String providerId);

    public Integer selectMaxRankByUserIdAndProviderId(String userId, String providerId);

    public UserConnection findPrimarySocialByUserId(String userId);

    public UserConnection findPrimarySocialByUsername(String username);


    public List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId);

    public List<String> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserIds);

    public void delete(UserConnection socialUser);

    public List<UserConnection> findByProviderAndProviderUserID(String providerID, String providerUserID);
}
