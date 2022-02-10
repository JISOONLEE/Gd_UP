package com.web.gdup.domain.comment.service;

import com.web.gdup.domain.comment.Entity.CommentEntity;
import com.web.gdup.domain.comment.dto.CommentDto;
import com.web.gdup.domain.comment.dto.ReCommentDto;
import com.web.gdup.domain.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public boolean insertComment(CommentDto commentDto) {

        CommentEntity comment = commentDto.toEntity();

        if(comment != null){
            int originId = commentRepository.findOriginId();
            comment.setOriginId(originId);
            commentRepository.save(comment);
            return true;
        }
        return false;
    }

    @Override
    public boolean insertReComment(ReCommentDto reCommentDto) {

        CommentEntity comment = reCommentDto.toEntity();

        if(comment != null){
            int groupOrder = commentRepository.findGroupOrder(comment.getOriginId());
            comment.setGroupOrder(groupOrder);
            commentRepository.save(comment);
            return true;
        }
        return false;
    }


    @Override
    public boolean deleteComment(int commentId) {
        Optional<CommentEntity> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            commentRepository.deleteById(commentId);
            return true;
        }
        return false;
    }

    @Override
    public CommentEntity modifyComment(int commentId, String content) {

        Optional<CommentEntity> comment = commentRepository.findById(commentId);

        if(comment.isPresent()){
            comment.get().setContent(content);
            commentRepository.save(comment.get());
            return  comment.get();
        }
        return null;
    }


}