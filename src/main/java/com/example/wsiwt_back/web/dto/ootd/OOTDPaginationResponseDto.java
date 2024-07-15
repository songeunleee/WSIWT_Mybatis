package com.example.wsiwt_back.web.dto.ootd;

import com.example.wsiwt_back.web.dto.PageResponeDto;
import lombok.Getter;

import java.util.List;

@Getter

public class OOTDPaginationResponseDto {
    private PageResponeDto page;
    private List<OOTDResponseDto> content;

    public OOTDPaginationResponseDto(List<OOTDResponseDto> content, PageResponeDto page){
        this.page = page;
        this.content = content;
    }

}
