package com.myapp.service.impl;

import com.myapp.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service("simpleSocialUserDetailsService")
@Transactional(readOnly = false)
public class SimpleSocialUserDetailsService implements SocialUserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
        UserDetails userDetails = userDao.findByEmail(userId);
        return (SocialUserDetails) userDetails;
    }
}
