package com.myapp.dao.impl;

import com.myapp.common.Comments;
import com.myapp.dao.CommentDao;
import com.myapp.entity.Comment;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl extends AbstractDaoImpl<Comment, Integer> implements CommentDao{


    protected CommentDaoImpl() {
        super(Comment.class);
    }

    @Override
    public List<Comment> findByFromAndFromId(Comments from, Integer id) {
        return super.getCurrentSession().createCriteria(Comment.class)
                .add(Restrictions.eq("from", from))
                .add(Restrictions.eq("from_id", id))
                .list();
    }

    @Override
    public void deleteByFromAndFromId(Comments from, Integer fromId) {
        this.findByFromAndFromId(from, fromId);
    }

}
