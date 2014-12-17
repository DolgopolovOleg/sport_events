package com.myapp.dao;

import com.myapp.common.Comments;
import com.myapp.entity.Comment;

import java.util.List;

public interface CommentDao extends AbstractDao<Comment, Integer>{

    List<Comment> findByFromAndFromId(Comments from, Integer id);
    void deleteByFromAndFromId(Comments from, Integer fromId);

}
