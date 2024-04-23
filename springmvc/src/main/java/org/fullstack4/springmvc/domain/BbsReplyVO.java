package org.fullstack4.springmvc.domain;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Log4j2
@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BbsReplyVO {
    private int idx;
    private int bbs_idx;
    private String title;
    private String user_id;
    private LocalDate reg_date;
    private LocalDate modify_date;
}
