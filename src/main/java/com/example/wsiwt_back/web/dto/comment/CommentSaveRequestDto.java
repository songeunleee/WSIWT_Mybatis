package com.example.wsiwt_back.web.dto.comment;

import com.example.wsiwt_back.domain.comment.Comment;
import com.example.wsiwt_back.domain.ootd.OOTD;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentSaveRequestDto {

    private String content;
    private String author;


    @Builder
    public CommentSaveRequestDto(String content, String author,String ootdId){
        this.content = content;
        this.author = author;


    }

    public Comment toEntity(){
        return Comment.builder().content(content).author(author).build();
    }
}
