package com.myapp.dao.impl;


import com.myapp.dao.ActivationDao;
import com.myapp.entity.Activation;
import com.myapp.entity.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class ActivationDaoImpl extends AbstractDaoImpl<Activation, Integer> implements ActivationDao {

    public ActivationDaoImpl() {
        super(Activation.class);
    }

    @Override
    public Activation findByUser(User user) {
        return (Activation) super.findByCriteria(Restrictions.eq("user", user));
    }

}
