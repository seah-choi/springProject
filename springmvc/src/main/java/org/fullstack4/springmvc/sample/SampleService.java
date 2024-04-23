package org.fullstack4.springmvc.sample;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@ToString
@Service
@RequiredArgsConstructor
public class SampleService {
    //@Autowired //생성자 만들 때, 의존성 주입 요청
    @Qualifier("event")
    private final SampleDAO sampleDAO;

}
