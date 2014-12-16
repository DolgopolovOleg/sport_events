package com.myapp.dao;

import com.myapp.common.Comments;
import com.myapp.entity.Comment;

import java.util.List;

public interface CommentDao extends AbstractDao<Comment, Integer>{

    List<Comment> findByFromAndFromId(String from, Integer id);
    void deleteByFromAndFromId(String from, Integer fromId);

}
