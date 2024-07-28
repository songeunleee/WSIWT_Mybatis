package com.example.wsiwt_back.domain.comment;

import com.example.wsiwt_back.domain.BaseTimeEntity;
import com.example.wsiwt_back.domain.ootd.OOTD;
import com.example.wsiwt_back.domain.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter



public class Comment {


    private Long id;

    public Long ootdId;

    public String contents;

    private Long userId;

    private Long parentId;

    private Long depth;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    private List<Comment> child;



    @Builder
    public Comment(Long id,  String contents, String author, Long userId, Long ootdId, Long parentId, Long depth){
        this.id = id;
        this.contents = contents;
        this.userId = userId;
        this.ootdId = ootdId;
        this.parentId = parentId;
        this.depth = depth;
    }


}
