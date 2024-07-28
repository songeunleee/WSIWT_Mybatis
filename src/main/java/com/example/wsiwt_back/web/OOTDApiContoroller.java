package com.example.wsiwt_back.web;

import com.example.wsiwt_back.domain.ootd.OOTD;
import com.example.wsiwt_back.domain.user.UserEntity;
import com.example.wsiwt_back.service.OOTDService;
import com.example.wsiwt_back.service.UserService;
import com.example.wsiwt_back.web.dto.Page;
import com.example.wsiwt_back.web.dto.PageRequestDto;
import com.example.wsiwt_back.web.dto.ResponseDto;
import com.example.wsiwt_back.web.dto.ootd.OOTDPaginationResponseDto;
import com.example.wsiwt_back.web.dto.ootd.OOTDResponseDto;
import com.example.wsiwt_back.web.dto.ootd.OOTDSaveRequestDto;
import com.example.wsiwt_back.web.dto.ootd.OOTDUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequiredArgsConstructor
public class OOTDApiContoroller {

    private final OOTDService ootdService;
    private final UserService userService;


    @PostMapping("/api/v1/ootd")
    public ResponseEntity<?> save(@AuthenticationPrincipal String userId, @RequestBody OOTDSaveRequestDto requestDto){

        UserEntity user = userService.findById(userId);
        OOTD ootd = OOTD.builder().userId(user.getId()).url(requestDto.getUrl()).contents(requestDto.getContents()).build();

        return  ResponseEntity.status(HttpStatus.CREATED).body(ootdService.save(ootd));
    }

    @GetMapping("/ootds/{page}")
    public ResponseEntity<Page<OOTDResponseDto>> findAllOOTDs(@PathVariable int page){

        System.out.println(page);
        Page<OOTDResponseDto> ootds = ootdService.findAll(page);
        System.out.println(ootds.toString());
      //  List<OOTDResponseDto> responseDto = ootds.stream().map(item -> new OOTDResponseDto(item,userService.findById(item.getUserId().toString()))).collect(Collectors.toList());

        return ResponseEntity.ok().body(ootds);
    }





    @PutMapping("api/v1/ootd/{id}")
    public ResponseEntity<?> update(@AuthenticationPrincipal String userId, @PathVariable Long id, @RequestBody OOTDUpdateRequestDto requestDto){

        try {
            Long updatedOOTD = ootdService.update(id, Long.parseLong(userId), requestDto);
            return ResponseEntity.ok().body(updatedOOTD);
        }catch  (Exception e){
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();

            return ResponseEntity.badRequest().body(responseDto);
        }
    }
    @DeleteMapping("api/v1/ootd/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal String userId,@PathVariable Long id){
        try{
            ootdService.delete(id,Long.parseLong(userId));
            return ResponseEntity.ok().build();
        }catch (Exception e){
            ResponseDto responseDto = ResponseDto.builder().error(e.getMessage()).build();

            return ResponseEntity.badRequest().body(responseDto);
        }


    }

}
