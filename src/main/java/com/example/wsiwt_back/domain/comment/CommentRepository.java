package com.example.wsiwt_back.domain.comment;

import com.example.wsiwt_back.web.dto.comment.CommentResponseDto;
import com.example.wsiwt_back.web.dto.comment.CommentUpdateRequestDto;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Long save(Comment comment);

    Optional<Comment> findById(Long id);

    List<CommentResponseDto> findByOOTDId(Long ootdId);

    List<CommentResponseDto> findByParentId(Long parentId);

    Long updateById(Long id, CommentUpdateRequestDto requestDto);

    void deleteById(Long id);
}
