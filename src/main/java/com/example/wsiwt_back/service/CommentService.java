package com.example.wsiwt_back.service;

import com.example.wsiwt_back.domain.comment.Comment;
import com.example.wsiwt_back.domain.comment.CommentRepository;
import com.example.wsiwt_back.web.dto.comment.CommentResponseDto;
import com.example.wsiwt_back.web.dto.comment.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;

    public Long save(Comment comment){


        return commentRepository.save(comment);
    }

    public Comment findById(Long id)
    {return  commentRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException(" not exist : " + id)); }

    public List<CommentResponseDto> findByOOTDId(Long ootdId){

        return commentRepository.findByOOTDId(ootdId);
    }

    public  List<CommentResponseDto> findByParentId(Long parentId){
        return commentRepository.findByParentId(parentId);
    }

    public List<CommentResponseDto> getHierarchicalCommentsByOOTDId(Long ootdId) {
        List<CommentResponseDto> allComments = findByOOTDId(ootdId);
        Map<Long, CommentResponseDto> commentMap = allComments.stream()
                .collect(Collectors.toMap(CommentResponseDto::getId, Function.identity()));

        List<CommentResponseDto> rootComments = new ArrayList<>();
        for (CommentResponseDto comment : allComments) {
            if (comment.getParentId() == null) {
                rootComments.add(comment);
            } else {
                CommentResponseDto parent = commentMap.get(comment.getParentId());
                if (parent != null) {
                    if (parent.getChild() == null) {
                        parent.setChild(new ArrayList<>());
                    }
                    parent.getChild().add(comment);
                }
            }

        }
        return rootComments;
    }

    public void delete(Long id,String userId){

        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id="+id));
        System.out.println(userId);
        System.out.println(comment.getUserId());
        if(!comment.getUserId().equals(Long.parseLong(userId))  ){
            log.warn("댓글을 작성한 사람만 삭제할 수 있습니다.");
            throw new RuntimeException("댓글을 작성한 사람만 삭제할 수 있습니다.");
        }

        commentRepository.deleteById(id);
    }

    @Transactional
    public Comment update(Long id,String userId, CommentUpdateRequestDto requestDto){


        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다. id="+id));

        if(!comment.getUserId().equals(Long.parseLong(userId))  ){
            log.warn("댓글을 작성한 사람만 삭제할 수 있습니다.");
            throw new RuntimeException("댓글을 작성한 사람만 삭제할 수 있습니다.");
        }


        return comment;
    }




}
