package com.myapp.service.impl;


import com.myapp.dao.SportDao;
import com.myapp.entity.Sport;
import com.myapp.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("sportService")
@Transactional(readOnly = true)
public class SportServiceImpl implements SportService {

    @Autowired
    SportDao sportsDao;

    @Override
    public Sport findById(Integer id) {
        return sportsDao.findById(id);
    }

    @Override
    public void save(Sport sport) {
        sportsDao.saveOrUpdate(sport);
    }

    @Override
    public Sport findByName(String name) {
        return sportsDao.findByName(name);
    }

    @Override
    public List<Sport> findAll() {
        return sportsDao.findAll();
    }
}
