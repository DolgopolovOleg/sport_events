package com.myapp.dao;

import com.myapp.entity.Comment;

import java.util.List;

public interface CommentDao extends AbstractDao<Comment, Integer>{

    List<Comment> findByFromAndFromId(String from, Integer id);

}
