package com.myapp.dao.impl;


import com.myapp.dao.UserConnectionDao;
import com.myapp.entity.UserConnection;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class UserConnectionDaoImpl extends AbstractDaoImpl<UserConnection, Integer> implements UserConnectionDao{

    protected UserConnectionDaoImpl() {
        super(UserConnection.class);
    }

    private static final String USER_ID = "userId";
    private static final String PROVIDER_ID = "providerId";
    private static final String PROVIDER_USER_ID = "providerUserId";
    private static final String RANK = "rank";

    private Criteria createCriteria() {
        return super.getCurrentSession().createCriteria(UserConnection.class);
    }

    @Override
    public void save(UserConnection socialUser) {
        saveOrUpdate(socialUser);
    }

    @Override
    public void update(UserConnection socialUser) {
        saveOrUpdate(socialUser);
    }

    @Override
    public List<UserConnection> findByProviderId(String providerId) {
        return (List<UserConnection>) super.findByCriterion(Restrictions.eq(PROVIDER_ID, (Object) providerId));
    }

    @Override
    public List<UserConnection> findByUserId(String userId) {
        return (List<UserConnection>) super.findByCriterion(Restrictions.eq(USER_ID, (Object) userId));
    }

    @Override
    public List<UserConnection> findByUserIdAndProviderId(String userId, String providerId) {
        return (List<UserConnection>) createCriteria()
                .add(Restrictions.eq(USER_ID, userId))
                .add(Restrictions.eq(PROVIDER_ID, providerId))
                .list();
    }

    @Override
    public List<UserConnection> findByUserIdAndProviderUserIds(String userId, MultiValueMap<String, String> providerUserIds) {
        Criteria criteria = createCriteria();
        criteria.add(Restrictions.eq(USER_ID, userId));
        Disjunction or = Restrictions.disjunction();
        for (String providerId : providerUserIds.keySet()) {
            or.add(
                    Restrictions.and(
                            Restrictions.eq(PROVIDER_ID, providerId),
                            Restrictions.in(PROVIDER_USER_ID, providerUserIds.get(providerId))
                    )
            );
        }
        return (List<UserConnection>) criteria.list();
    }

    @Override
    public UserConnection get(String userId, String providerId, String providerUserId) {
        return (UserConnection) createCriteria()
                .add(Restrictions.eq(USER_ID, userId))
                .add(Restrictions.eq(PROVIDER_ID, providerId))
                .add(Restrictions.eq(PROVIDER_USER_ID, providerUserId))
                .uniqueResult();
    }

    @Override
    public List<UserConnection> findPrimaryByUserIdAndProviderId(String userId, String providerId) {
        return null;
    }

    @Override
    public Integer selectMaxRankByUserIdAndProviderId(String userId, String providerId) {
        return null;
    }

    @Override
    public UserConnection findPrimarySocialByUserId(String userId) {
        return null;
    }

    @Override
    public UserConnection findPrimarySocialByUsername(String username) {
        return null;
    }

    @Override
    public List<String> findUserIdsByProviderIdAndProviderUserId(String providerId, String providerUserId) {
        List<UserConnection> socialUsers = (List<UserConnection>) createCriteria().add(Restrictions.eq(PROVIDER_ID, providerId)).add(Restrictions.eq(PROVIDER_USER_ID, providerUserId)).list();
        List<String> userIds = new ArrayList<String>(socialUsers.size());
        for (UserConnection socialUser : socialUsers) {
            userIds.add(socialUser.getUserId());
        }
        return userIds;
    }

    @Override
    public List<String> findUserIdsByProviderIdAndProviderUserIds(String providerId, Set<String> providerUserIds) {
        List<UserConnection> socialUsers = (List<UserConnection>) createCriteria()
                .add(Restrictions.eq(PROVIDER_ID, providerId))
                .add(Restrictions.in(PROVIDER_USER_ID, providerUserIds))
                .list();
        List<String> userIds = new ArrayList<String>(socialUsers.size());
        for (UserConnection socialUser : socialUsers) {
            userIds.add(socialUser.getUserId());
        }
        return userIds;
    }

    @Override
    public List<UserConnection> findByProviderAndProviderUserID(String providerID, String providerUserID) {
        return null;
    }
}