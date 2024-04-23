package org.fullstack4.springmvc.sample.mapper;

import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.A;
import org.fullstack4.springmvc.domain.BbsVO;
import org.fullstack4.springmvc.dto.PageRequestDTO;
import org.fullstack4.springmvc.mapper.BbsMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class BbsMapperTests {
    @Autowired(required = false)
    private BbsMapper bbsMapper;

    @Test
    public void testBbsTotalCount() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .page_size(10)
                .build();
        int totalCount = bbsMapper.bbsTotalCount(pageRequestDTO);
        log.info("========================");
        log.info("testBbsTotalCount :" + totalCount);
        log.info("========================");

    }

    @Test
    public void testBbsListByPage(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .page_size(10)
                .build();
        List<BbsVO> bbsList = bbsMapper.bbsListByPage(pageRequestDTO);
        log.info("========================");
        bbsList.forEach(list -> log.info(list));
        log.info("========================");
    }

    @Test
    public void testBbsListBySearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .page_size(10)
                .search_type(new String[]{"t","u"})
                .search_word("제목")
                .build();
        int total_count = bbsMapper.bbsTotalCount(pageRequestDTO);
        List<BbsVO> bbsList = bbsMapper.bbsListByPage(pageRequestDTO);
        log.info("========================");
        log.info("total_count :" + total_count);
        bbsList.forEach(list -> log.info(list));
        log.info("========================");
    }
}
