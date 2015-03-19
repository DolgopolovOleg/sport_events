package com.myapp.controller;

import com.myapp.common.Comments;
import com.myapp.entity.Comment;
import com.myapp.service.CommentService;
import com.myapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@Controller
@RequestMapping(value = "/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "addComment", method = RequestMethod.POST)
    public String addComment(Comment comment, Model model){
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        DateTimeDateFormat dt = new DateTimeDateFormat();
//        dt.format(new Date());
//        comment.setCreated(new Date());
//        comment.setCreated(new Timestamp(new Date().getTime()));
        comment.setCreated(new Date());
        comment.setUser(userService.getLoggedUser());
        commentService.save(comment);
        String from = comment.getFrom() == Comments.EVENT?"events":"places";
        String page = "redirect:/" + from + "/" + comment.getFrom_id();
        return page;
    }

}