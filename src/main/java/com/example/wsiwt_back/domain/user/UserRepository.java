package com.example.wsiwt_back.domain.user;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;


@Mapper
public interface UserRepository {
    //@Insert("INSERT INTO userEntity (username, password, role, picture, authProvider) VALUES (#{username}, #{password}, #{role}, #{picture}, #{authProvider})")
      Long save(UserEntity user);

    //    @Select("SELECT CASE WHEN COUNT(*) > 0 THEN TRUE ELSE FALSE END FROM userEntity WHERE username = #{username}")
        boolean existsByUsername(@Param("username") String username);

  //      @Select("SELECT * FROM userEntity WHERE username = #{username}")
        UserEntity findByUsername(@Param("username") String username);

    //    @Select("SELECT * FROM userEntity WHERE id = #{id}")
        Optional<UserEntity> findById(@Param("id") Long id);
    }

