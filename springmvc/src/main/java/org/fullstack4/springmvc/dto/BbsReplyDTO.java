package org.fullstack4.springmvc.dto;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Log4j2
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BbsReplyDTO {
    private int idx;
    private int bbs_idx;
    private String title;
    private String user_id;
    private LocalDate reg_date;
    private LocalDate modify_date;
}
