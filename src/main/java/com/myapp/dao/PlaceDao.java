package com.myapp.dao;

import com.myapp.entity.Place;

import java.util.List;

public interface PlaceDao extends AbstractDao<Place, Integer>{

    List<Place> findAllByCreatorId(Integer creatorId);

}
