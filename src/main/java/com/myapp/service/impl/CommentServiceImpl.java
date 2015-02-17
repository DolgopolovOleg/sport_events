package com.myapp.service.impl;

import com.myapp.common.Comments;
import com.myapp.dao.CommentDao;
import com.myapp.entity.Comment;
import com.myapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("commentService")
@Transactional(readOnly = false)
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao commentDao;

    @Override
    public Comment findById(Integer id) {
        return commentDao.findById(id);
    }

    @Override
    public void delete(Comment comment) {
        commentDao.delete(comment);
    }

    @Override
    public void save(Comment comment) {
        commentDao.saveOrUpdate(comment);
    }

    @Override
    public void addComment(Comments from, Comment comment) {

    }

    @Override
    public List<Comment> findByFromAndFromId(Comments from, Integer id) {
        return commentDao.findByFromAndFromId(from, id);
    }
}
