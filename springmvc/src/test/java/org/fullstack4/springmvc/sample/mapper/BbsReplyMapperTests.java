package org.fullstack4.springmvc.sample.mapper;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.domain.BbsReplyVO;
import org.fullstack4.springmvc.dto.BbsReplyDTO;
import org.fullstack4.springmvc.dto.PageRequestDTO;
import org.fullstack4.springmvc.mapper.BbsReplyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class BbsReplyMapperTests {

    @Autowired(required = false)
    BbsReplyMapper bbsReplyMapper;

    @Test
    public void testRegist() {
        BbsReplyVO bbsReplyVO = BbsReplyVO.builder()
                .bbs_idx(10)
                .user_id("test")
                .title("댓글 테스트")
                .build();

        int result = bbsReplyMapper.reply_regist(bbsReplyVO);
        log.info("========================");
        log.info("testRegist :" + result);
        log.info("========================");
    }
}
