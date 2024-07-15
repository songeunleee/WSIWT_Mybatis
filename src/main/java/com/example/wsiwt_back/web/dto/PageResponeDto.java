package com.example.wsiwt_back.web.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class PageResponeDto  {

    @ApiModelProperty(value = "총 페이지", example = "2")
    private int totalPages;
    @ApiModelProperty(value = "총 컨텐츠(ex OOTD)의 갯수", example = "10")
    private long totalElements;
    @ApiModelProperty(value = "현재 페이지 0부터 시작", example = "0")
    private int pageNumber;
    @ApiModelProperty(value = "현재 페이지가 첫 페이지인지 알려줌", example = "true")
    private boolean isFirst;
    @ApiModelProperty(value = "현재 페이지가 마지막 페이지인지 알려줌", example = "false")
    private boolean isLast;
    @ApiModelProperty(value = "다음 페이지가 있는지 알려줌", example = "true")
    private boolean hasNext;
    @ApiModelProperty(value = "이전 페이지가 있는지 알려줌", example = "false")
    private boolean hasPrevious;


   // public PageResponeDto(Page page){
    //    this.totalPages = page.getTotalPages();
     //   this.totalElements = page.getTotalElements();
     //   this.pageNumber = page.getNumber();
     //   this.isFirst = page.isFirst();
     //   this.isLast = page.isLast();
     //   this.hasNext = page.hasNext();
     //   this.hasPrevious = page.hasPrevious();

  //  }

}

