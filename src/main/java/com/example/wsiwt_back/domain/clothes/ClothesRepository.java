package com.example.wsiwt_back.domain.clothes;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface ClothesRepository {

   // @Insert("INSERT INTO Clothes (name, category, url, userId, type) VALUES (#{name}, #{category}, #{url}, #{userId}, #{type})")
    Long save(Clothes clothes);

   // @Select("SELECT * FROM Clothes WHERE userId = #{userId}")
    List<Clothes> findByUserId(Long id);

    void deleteById(Long id);

}
