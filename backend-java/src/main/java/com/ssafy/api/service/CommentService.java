package com.ssafy.api.service;

import com.ssafy.api.request.CommentRegisterPostReq;
import com.ssafy.db.entity.Article;
import com.ssafy.db.entity.Comment;


/**
 * 댓글 서비스 인터페이스 정의
 */
public interface CommentService {

    void registerComment(CommentRegisterPostReq commentInfo, Article article);
    void deleteComment(Comment comment);
}
