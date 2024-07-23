package com.example.wsiwt_back.domain.ootd;

import com.example.wsiwt_back.domain.comment.Comment;
import com.example.wsiwt_back.domain.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter



public class OOTD  {

    public Long id;


    public String url;

    public String contents;

    public Long userId;

    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;



    @Builder
    public OOTD(Long id, String contents, String author, String url, Long userId){
        this.id = id;
        this.contents = contents;
        this.url = url;
        this.userId = userId;

    }

    public void update(String content, String url){
        this.contents = content;
        this.url = url;
    }
}
