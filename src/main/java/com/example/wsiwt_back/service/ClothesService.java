package com.example.wsiwt_back.service;

import com.example.wsiwt_back.domain.clothes.Clothes;
import com.example.wsiwt_back.domain.clothes.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor

@Service
public class ClothesService{

    private final ClothesRepository clothesRepository;

    public Long save(Clothes clothes){
        String url = "dd";

        return clothesRepository.save(clothes);
    }

    public List<Clothes>  FindByUserId(Long userId){
        return clothesRepository.findByUserId(userId);
    }

    public void DeleteById(Long id){

        clothesRepository.deleteById(id);
    }



}