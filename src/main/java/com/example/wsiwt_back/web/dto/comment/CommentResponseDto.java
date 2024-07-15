package com.example.wsiwt_back.web.dto.comment;

import com.example.wsiwt_back.domain.comment.Comment;
import com.example.wsiwt_back.domain.ootd.OOTD;
import com.example.wsiwt_back.domain.user.UserEntity;
import com.example.wsiwt_back.web.dto.user.AuthorDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentResponseDto {

    @ApiModelProperty(value = "댓글의 id", example = "string")
    private String id;
    @ApiModelProperty(value = "댓글의 내용", example = "string")
    private String content;
    @ApiModelProperty(value = "댓글의 작성자 정보")
    private AuthorDto author;

    @ApiModelProperty(value = "댓글의 깊이, 1이상이면 대댓글", example = "0")
    private Long depth;
    @ApiModelProperty(value = "댓글의 생성 시각")
    private LocalDateTime createdAt;
    @ApiModelProperty(value = "댓글 수정 시각")
    private LocalDateTime updatedAt;
    @ApiModelProperty(value = "댓글이 작성된 ootd의 id", example = "1")
    private Long ootdId;
    @ApiModelProperty(value = "댓글의 대댓글 리스트")
    private List<CommentResponseDto> child;



    public CommentResponseDto(Comment comment){
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = new AuthorDto(comment.getUser());

        this.depth = comment.getDepth();
        this.child = comment.getChild().stream().map(CommentResponseDto::new)
                .collect(Collectors.toList());
        this.ootdId = comment.getOotd().getId();
        this.createdAt = comment.getCreatedDate();
        this.updatedAt = comment.getModifiedDate();


    }

}
