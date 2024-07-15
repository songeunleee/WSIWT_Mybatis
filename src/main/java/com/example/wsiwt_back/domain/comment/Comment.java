package com.example.wsiwt_back.domain.comment;

import com.example.wsiwt_back.domain.BaseTimeEntity;
import com.example.wsiwt_back.domain.ootd.OOTD;
import com.example.wsiwt_back.domain.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;



import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter



public class Comment  extends BaseTimeEntity {

  //  @Id
  //  @GeneratedValue(generator = "system-uuid")
  //  @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String id;


   // @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "ootd_id")
    //@JsonIgnore
    public OOTD ootd;

    //@Column(nullable = false)

    public String content;

    //@ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "user_id")
    //@JsonIgnore
    private UserEntity user;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "parent_id")
    private Comment parent;

    private Long depth;

    //@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY,
      //      cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    //@JsonIgnore
    private List<Comment> child = new ArrayList<>();


    @Builder
    public Comment(String id,  String content, String author, UserEntity user, OOTD ootd, Comment parent, Long depth,List<Comment> child){
        this.id = id;
        this.content = content;
        this.user = user;
        this.ootd = ootd;
        this.parent = parent;
        this.depth = depth;
        this.child = child;
    }

    public void update(String content){
        this.content = content;

    }

}
