package com.myapp.service;

import com.myapp.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment findById(Integer id);
    void delete(Comment comment);
    void save(Comment comment);
    void addComment(String from, Comment comment);
    List<Comment> findByFromAndFromId(String from, Integer id);

}
