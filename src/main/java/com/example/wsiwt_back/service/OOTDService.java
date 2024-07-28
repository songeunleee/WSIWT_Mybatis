package com.example.wsiwt_back.service;

import com.example.wsiwt_back.domain.clothes.Clothes;
import com.example.wsiwt_back.domain.comment.Comment;
import com.example.wsiwt_back.domain.ootd.OOTD;
import com.example.wsiwt_back.domain.ootd.OOTDRepository;
import com.example.wsiwt_back.web.dto.Page;
import com.example.wsiwt_back.web.dto.comment.CommentResponseDto;
import com.example.wsiwt_back.web.dto.ootd.OOTDResponseDto;
import com.example.wsiwt_back.web.dto.ootd.OOTDUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class OOTDService {
    private final int pageSize = 5;
    private final OOTDRepository ootdRepository;
    private final CommentService commentService;
    private final UserService userService;

    public Long save(OOTD ootd){
        return ootdRepository.save(ootd);
    }

    public OOTD findById(Long id)
    {return  ootdRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException(" not exist : " + id)); }

    public Page<OOTDResponseDto> findAll(int page){

        int totalElements = ootdRepository.count();

        int totalPages = (int) Math.ceil((double) totalElements / pageSize);;
        int offset = page * pageSize;
        boolean isFirst = page == 0;
        boolean isLast = page == totalPages - 1;
        boolean hasNext = page < totalPages - 1;
        boolean hasPrevious = page > 0;




        //대댓글까지 있는 댓글 dto 완성
        //commnetmapper에서 sql 문으로 user정보까지 다 가져오면 끝
        List<OOTD> ootds = ootdRepository.findAll(offset);


        List<OOTDResponseDto> result = ootds.stream()
                .map(item->new OOTDResponseDto(item,userService.findById(item.getUserId().toString())
                        ,commentService.getHierarchicalCommentsByOOTDId(item.id))).collect(Collectors.toList());


      //  List<OOTDResponseDto> result = ootds.stream()
        //                .map(item->new OOTDResponseDto(item,userService.findById(item.getUserId().toString())
        //                        ,commentService.findByOOTDId(item.id).stream()
        //                        .map(comment->new CommentResponseDto(comment
        //                                ,commentService.findByParentId(comment.getId()))).collect(Collectors.toList())))
        //                .collect(Collectors.toList());

       // List<OOTDResponseDto> content = result.stream() .map(ootd -> {
       //     List<CommentResponseDto> filteredComments = ootd.getComments().stream()
       //             .filter(comment -> comment.getDepth() == 0)
       //             .collect(Collectors.toList());
       //     return new OOTDResponseDto(ootd,filteredComments);
       // }).collect(Collectors.toList());




        return new Page<>(result, totalElements, page, pageSize, totalPages, isFirst, isLast, hasNext, hasPrevious);
    }

    public void delete(Long id,Long userId){

        OOTD ootd = ootdRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));


        if(!ootd.getUserId().equals(userId)  ){
            log.warn("글을 작성한 사람만 삭제할 수 있습니다.");
            throw new RuntimeException("글을 작성한 사람만 삭제할 수 있습니다.");
        }

        ootdRepository.deleteById(id);
    }

    public Long update(Long id,Long userId, OOTDUpdateRequestDto requestDto){


        OOTD ootd = ootdRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        if(!ootd.getUserId().equals(userId)  ){
            log.warn("글을 작성한 사람만 수정할 수 있습니다.");
            throw new RuntimeException("글을 작성한 사람만 수정할 수 있습니다.");
        }else{

            return ootdRepository.updateById(id,requestDto);
        }


    }


}
