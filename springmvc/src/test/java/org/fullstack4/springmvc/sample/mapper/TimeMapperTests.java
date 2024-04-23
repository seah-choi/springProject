package org.fullstack4.springmvc.sample.mapper;

import lombok.extern.log4j.Log4j2;
import org.checkerframework.checker.units.qual.A;
import org.fullstack4.springmvc.mapper.TimeMapper;
import org.fullstack4.springmvc.mapper.TimeMapper2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TimeMapperTests {
    @Autowired(required = false)
    private TimeMapper timeMapper;

    @Autowired(required = false)
    private TimeMapper2 timeMapper2;


    @Test
    public void testgetTime() {
        log.info("===========");
        log.info("timeMapper.getTime() : "+ timeMapper.getTime());
        log.info("===========");
    }

    @Test
    public void testgetNow(){
        log.info("===========");
        log.info("timeMapper2.getNow() : "+ timeMapper2.getNow());
        log.info("===========");
    }
}
