package org.fullstack4.springmvc.service;

import org.fullstack4.springmvc.dto.MemberDTO;

public interface MemberServiceIf {
    int join(MemberDTO memberDTO);

    int modify(MemberDTO memberDTO);

    MemberDTO view(String user_id);

    int delete(String user_id);

    int idCheck(String user_id);
}
