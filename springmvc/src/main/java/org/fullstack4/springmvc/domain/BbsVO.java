package org.fullstack4.springmvc.domain;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BbsVO {
    private int idx;
    private String user_id;
    private String title;
    private String content;
    private String display_date;
    private int read_cnt;
    private LocalDateTime reg_date;
    private LocalDateTime modify_date;
    private String interest;
    private int reply_cnt;

}
