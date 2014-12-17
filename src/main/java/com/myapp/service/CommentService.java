package com.myapp.service;

import com.myapp.common.Comments;
import com.myapp.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment findById(Integer id);
    void delete(Comment comment);
    void save(Comment comment);
    void addComment(Comments from, Comment comment);
    List<Comment> findByFromAndFromId(Comments from, Integer id);

}
