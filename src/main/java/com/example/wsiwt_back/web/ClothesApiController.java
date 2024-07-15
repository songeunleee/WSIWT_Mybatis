package com.example.wsiwt_back.web;

import com.example.wsiwt_back.domain.clothes.Clothes;
import com.example.wsiwt_back.service.ClothesService;
import com.example.wsiwt_back.web.dto.ResponseDto;
import com.example.wsiwt_back.web.dto.clothes.ClothesResponseDto;
import com.example.wsiwt_back.web.dto.clothes.ClothesSaveRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Api( tags = {"my closet"})
@Tag(name = "my closet", description = "my closet에 들어갈 옷 데이터에 대한 Create Read Delete")
@RequiredArgsConstructor
@RestController
public class ClothesApiController {

    private final ClothesService clothesService;

    @ApiOperation("clothes create")
    @ApiResponse(responseCode = "200" , description = "my closet에 clothes 추가")
    @PostMapping("/api/v1/clothes")
    public ResponseEntity<Long> save(@AuthenticationPrincipal String userId, @RequestBody ClothesSaveRequestDto requestDto){

        Clothes clothes = requestDto.toEntity(Long.parseLong(userId));

        return  ResponseEntity.status(HttpStatus.CREATED).body(clothesService.save(clothes));
    }

    @ApiOperation("clothes read")
    @ApiResponse(responseCode = "200" , description = "my closet의 clothes 데이터 불러오기")
    @GetMapping("/api/v1/clothes")
    public ResponseEntity<List<ClothesResponseDto>> findById(@AuthenticationPrincipal String userId){
        System.out.println(userId);
        List<ClothesResponseDto> responseDto = clothesService.FindByUserId(Long.parseLong(userId)).stream().map( ClothesResponseDto::new).collect(Collectors.toList());

        return ResponseEntity.ok().body(responseDto);
    }

    @ApiOperation("clothes delete")
    @ApiResponse(responseCode = "200" , description = "내 옷장에 clothes 삭제제")
    @DeleteMapping("api/v1/clothes/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String userId,@PathVariable Long id){
        try{
            clothesService.DeleteById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();

            return ResponseEntity.badRequest().body(responseDto);
        }

    }

}