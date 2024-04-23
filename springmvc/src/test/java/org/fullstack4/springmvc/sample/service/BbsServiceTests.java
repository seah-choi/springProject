package org.fullstack4.springmvc.sample.service;

import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.dto.PageRequestDTO;
import org.fullstack4.springmvc.dto.PageResponseDTO;
import org.fullstack4.springmvc.service.BbsServiceIf;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class BbsServiceTests {
    @Autowired
    private BbsServiceIf bbsServiceIf;

    @Test
    public void testRegist(){
        BbsDTO bbsDTO = BbsDTO.builder()
                .user_id("test")
                .title("제목 테스트")
                .content("내용 테스트")
                .display_date("2024-04-18")
                .read_cnt(0)
                .build();
        bbsServiceIf.regist(bbsDTO);
    }

    @Test
    public void testBbsToTalCount(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .page_size(10)
                .build();
        int totalCount = bbsServiceIf.bbsTotalCount(pageRequestDTO);
        log.info("========================");
        log.info("BbsServiceTests :" + totalCount);
        log.info("========================");
    }

    @Test
    public void testBbsListByPage(){
        log.info("========================");
        log.info("BbsServiceTests >> testBbsListByPage START");

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .page_size(10)
                .search_type(new String[]{"t","u"})
                .search_word("제목")
                .search_date1("2024-02-18")
                .build();
        PageResponseDTO<BbsDTO> responseDTO = bbsServiceIf.bbsListByPage(pageRequestDTO);
        responseDTO.getDtoList().forEach(vo-> log.info(vo));

        log.info("BbsServiceTests >> testBbsListByPage END");
        log.info("========================");
    }

}
