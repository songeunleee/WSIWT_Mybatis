package com.example.wsiwt_back.domain.ootd;

import com.example.wsiwt_back.domain.comment.Comment;
import com.example.wsiwt_back.domain.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter



public class OOTD  {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    //@Column
    private String url;

    //@Column(nullable = false)
    public String content;

    //@JsonIgnore
    //@ManyToOne( fetch = FetchType.LAZY)
    //@JoinColumn(name = "user_id")
    private UserEntity user;

    //@BatchSize(size = 100)
    //@JsonIgnore
    //@OneToMany(mappedBy = "ootd",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();



    @Builder
    public OOTD(Long id, String content, String author, String url, UserEntity user,List<Comment> comments){
        this.id = id;
        this.content = content;
        this.url = url;
        this.user = user;
        this.comments = comments;

    }

    public void update(String content, String url){
        this.content = content;
        this.url = url;
    }
}
