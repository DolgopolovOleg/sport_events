package com.myapp.service.impl;


import com.myapp.dao.ActivationDao;
import com.myapp.dao.UserDao;
import com.myapp.entity.Activation;
import com.myapp.entity.User;
import com.myapp.service.AuthService;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("activationService")
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    @Autowired
    ActivationDao activationDao;

    @Autowired
    UserDao userDao;

    @Override
    public void save(Activation activation) {
        activationDao.saveOrUpdate(activation);
    }

    @Override
    public Activation findByUserID(Integer userID) {
        User user = userDao.findById(userID);
        return (Activation) activationDao.findByCriterion(Restrictions.eq("user", user)).get(0);
    }
}
