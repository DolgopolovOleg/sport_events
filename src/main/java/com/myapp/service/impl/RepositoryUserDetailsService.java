package com.myapp.service.impl;


import com.myapp.common.ExampleUserDetails;
import com.myapp.dao.UserDao;
import com.myapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("repositoryUserDetailService")
@Transactional(readOnly = false)
public class RepositoryUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        ExampleUserDetails principal = ExampleUserDetails.getBuilder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getUserRole())
                .build();

        return principal;
    }
}
