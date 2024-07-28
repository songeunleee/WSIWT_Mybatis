package com.example.wsiwt_back.web;

import com.example.wsiwt_back.domain.comment.Comment;
import com.example.wsiwt_back.domain.ootd.OOTD;
import com.example.wsiwt_back.domain.user.UserEntity;
import com.example.wsiwt_back.service.CommentService;
import com.example.wsiwt_back.service.OOTDService;
import com.example.wsiwt_back.service.UserService;
import com.example.wsiwt_back.web.dto.ResponseDto;
import com.example.wsiwt_back.web.dto.comment.CommentSaveRequestDto;
import com.example.wsiwt_back.web.dto.comment.CommentUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;
    private final OOTDService ootdService;
    private final UserService userService;


    @PostMapping(value = {"/api/v1/{ootdId}/comment","/api/v1/{ootdId}/comment/{id}"})
    public ResponseEntity<?> save(@AuthenticationPrincipal String userId, @PathVariable Long ootdId, @PathVariable(required = false) Long id, @RequestBody CommentSaveRequestDto requestDto){
        //OOTD ootd = ootdService.findById(ootdId);
        Comment comment;
        if(id == null){
            comment = Comment.builder().userId(Long.parseLong(userId)).author(requestDto.getAuthor()).contents(requestDto.getContents()).ootdId(ootdId).depth(0L).build();
        }else{
            Comment parentComment = commentService.findById(id);
            Long depth = parentComment.getDepth() + 1;
            comment = Comment.builder().userId(Long.parseLong(userId)).author(requestDto.getAuthor()).contents(requestDto.getContents()).ootdId(ootdId).depth(depth).parentId(id).build();
        }

        return  ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(comment));
    }
   @PutMapping("api/v1/comment/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal String userId, @PathVariable String id, @RequestBody CommentUpdateRequestDto requestDto){

        try {
           // Comment updatedComment = commentService.update(id, userId, requestDto);
            return ResponseEntity.ok().body(1);
        }catch  (Exception e){
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();

            return ResponseEntity.badRequest().body(responseDto);
        }
    }
    @DeleteMapping("api/v1/comment/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String userId, @PathVariable String id ){
        try{
          //  commentService.delete(id,userId);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDto);
        }


    }


}