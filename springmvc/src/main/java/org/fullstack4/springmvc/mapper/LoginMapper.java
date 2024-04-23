package org.fullstack4.springmvc.mapper;

import org.apache.ibatis.annotations.Param;
import org.fullstack4.springmvc.domain.MemberVO;

public interface LoginMapper {
    //MemberVO login(String id);

    MemberVO login(@Param("user_id") String id, @Param("pwd") String pwd);

    void logout(String id);
}
