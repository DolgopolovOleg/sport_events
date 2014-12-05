package com.myapp.dao.impl;

import com.myapp.dao.PlaceDao;
import com.myapp.entity.Place;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaceDaoImpl extends AbstractDaoImpl<Place, Integer> implements PlaceDao {

    protected PlaceDaoImpl() {
        super(Place.class);
    }

    @Override
    public List<Place> findAllByCreatorId(Integer creatorId) {
        return super.findByCriteria(Restrictions.eq("creator", (Object) creatorId));
    }

}
