package com.myapp.dao.impl;

import com.myapp.dao.SportDao;
import com.myapp.entity.Sport;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class SportDaoImpl extends AbstractDaoImpl<Sport, Integer> implements SportDao {

    protected SportDaoImpl() {
        super(Sport.class);
    }

    @Override
    public Sport findByName(String name) {
        return (Sport) super.findByCriteria(Restrictions.eq("name", (Object) name)).get(0);
    }
}
