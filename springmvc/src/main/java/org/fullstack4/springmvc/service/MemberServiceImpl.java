package org.fullstack4.springmvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.domain.MemberVO;
import org.fullstack4.springmvc.dto.MemberDTO;
import org.fullstack4.springmvc.mapper.MemberMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.Source;
import java.lang.annotation.Target;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberServiceIf {
    private final MemberMapper memberMapper;
    private final ModelMapper modelMapper;

    @Override
    public int join(MemberDTO memberDTO) {
        log.info("===============");
        log.info("JoinServiceImpl >> join(memberDTO) : "+ memberDTO.toString());

        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        log.info("JoinServiceImpl >> join(memberVO) : "+ memberVO.toString());
        int result = memberMapper.join(memberVO);
        return result;
    }

    @Override
    public int modify(MemberDTO memberDTO) {
        MemberVO memberVO = modelMapper.map(memberDTO, MemberVO.class);
        int result = memberMapper.modify(memberVO);

        log.info("MemberServiceImpl >> memberVO : "+memberVO.toString());
        return result;
    }

    @Override
    public MemberDTO view(String user_id) {
        MemberVO memberVO = memberMapper.view(user_id);
        MemberDTO memberDTO = modelMapper.map(memberVO, MemberDTO.class);
        return memberDTO;
    }

    @Override
    public int delete(String user_id) {
        int result = memberMapper.delete(user_id);
        return result;
    }

    @Override
    public int idCheck(String user_id) {
        int result = memberMapper.idCheck(user_id);
        log.info("idCheckResult :"+result);
        return result;
    }

}
