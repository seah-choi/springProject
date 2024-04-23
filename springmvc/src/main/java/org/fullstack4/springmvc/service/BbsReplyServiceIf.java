package org.fullstack4.springmvc.service;

import org.fullstack4.springmvc.dto.BbsReplyDTO;

import java.util.List;

public interface BbsReplyServiceIf {
    int reply_regist(BbsReplyDTO bbsReplyDTO);
    List<BbsReplyDTO> reply_list();
    int update_reply_cnt(int bbs_idx);
}
