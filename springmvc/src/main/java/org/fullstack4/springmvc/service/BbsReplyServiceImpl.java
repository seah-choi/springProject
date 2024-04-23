package org.fullstack4.springmvc.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.domain.BbsReplyVO;
import org.fullstack4.springmvc.dto.BbsReplyDTO;
import org.fullstack4.springmvc.mapper.BbsReplyMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class BbsReplyServiceImpl implements BbsReplyServiceIf{

    private final BbsReplyMapper bbsReplyMapper;
    private final ModelMapper modelMapper;

    @Override
    public int reply_regist(BbsReplyDTO bbsReplyDTO) {
        BbsReplyVO bbsReplyVO = modelMapper.map(bbsReplyDTO, BbsReplyVO.class);
        log.info("==================");
        log.info("bbsReplyDTO"+bbsReplyDTO.toString());
        log.info("bbsReplyVO"+bbsReplyVO.toString());

        int result = bbsReplyMapper.reply_regist(bbsReplyVO);
        int reply_result = bbsReplyMapper.update_reply_cnt(bbsReplyDTO.getBbs_idx());
        log.info("result"+result);

        return result;
    }

    @Override
    public List<BbsReplyDTO> reply_list() {
        return null;
    }

    @Override
    public int update_reply_cnt(int bbs_idx) {

        log.info("==================");
        int result = bbsReplyMapper.update_reply_cnt(bbs_idx);
        log.info("result"+result);
        log.info("==================");

        return 0;
    }
}
