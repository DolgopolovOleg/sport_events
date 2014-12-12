package com.myapp.dao.impl;


import com.myapp.dao.UserDao;
import com.myapp.entity.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDaoImpl<User, Integer> implements UserDao{

    protected UserDaoImpl() {
        super(User.class);
    }

    @Override
    public void save(User user) {
        saveOrUpdate(user);
    }

    @Override
    public User findByUsername(String username) {
        return (User) super.findByCriteria(Restrictions.eq("username", (Object) username)).get(0);
    }

}
