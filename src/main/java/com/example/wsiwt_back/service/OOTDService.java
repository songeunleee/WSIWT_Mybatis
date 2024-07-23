package com.example.wsiwt_back.service;

import com.example.wsiwt_back.domain.clothes.Clothes;
import com.example.wsiwt_back.domain.ootd.OOTD;
import com.example.wsiwt_back.domain.ootd.OOTDRepository;
import com.example.wsiwt_back.web.dto.Page;
import com.example.wsiwt_back.web.dto.ootd.OOTDUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class OOTDService {
    private final int pageSize = 5;
    private final OOTDRepository ootdRepository;

    public Long save(OOTD ootd){
        return ootdRepository.save(ootd);
    }

    public OOTD findById(Long id)
    {return  ootdRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException(" not exist : " + id)); }

    public Page<OOTD> findAll(int page){

        int totalElements = ootdRepository.count();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);;
        int offset = page * pageSize;
        boolean isFirst = page == 0;
        boolean isLast = page == totalPages - 1;
        boolean hasNext = page < totalPages - 1;
        boolean hasPrevious = page > 0;

        List<OOTD> content = ootdRepository.findAll(offset);


        return new Page<>(content, totalElements, page, pageSize, totalPages, isFirst, isLast, hasNext, hasPrevious);
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
