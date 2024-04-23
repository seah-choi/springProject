package org.fullstack4.springmvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.domain.MemberVO;
import org.fullstack4.springmvc.dto.LoginDTO;
import org.fullstack4.springmvc.mapper.LoginMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginServiceIf{

    private final LoginMapper loginMapper;
    private final ModelMapper modelMapper;

    @Override
    public LoginDTO login(String id, String pwd) {

        MemberVO memberVO = loginMapper.login(id, pwd);


        LoginDTO memberDTO = null;

        if(memberVO != null && memberVO.getPwd().equals(pwd)) {
            memberDTO = modelMapper.map(memberVO, LoginDTO.class);
        }
        return memberDTO;
    }
}
