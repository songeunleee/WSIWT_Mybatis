package com.example.wsiwt_back.domain.ootd;

import com.example.wsiwt_back.domain.clothes.Clothes;
import com.example.wsiwt_back.web.dto.ootd.OOTDUpdateRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OOTDRepository {

    Long save(OOTD clothes);

    List<OOTD> findAll(int offset);

    int count();

    Long updateById(Long id, OOTDUpdateRequestDto requestDto);
    void deleteById(Long id);

    Optional<OOTD> findById(Long id);
}
