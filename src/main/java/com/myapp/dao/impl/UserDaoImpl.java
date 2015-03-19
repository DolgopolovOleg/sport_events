package com.myapp.dao.impl;


import com.myapp.dao.UserDao;
import com.myapp.entity.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Integer> implements UserDao{

    protected UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User save(User user) {
        saveOrUpdate(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        ArrayList<User> users = (ArrayList<User>) super.findByCriteria(Restrictions.eq("email", (Object) email));
        return users.size() == 0 ? null : users.get(0);
    }

}
