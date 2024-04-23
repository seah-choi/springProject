package org.fullstack4.springmvc.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Log4j2
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BbsDTO {

    @Builder.Default
    @PositiveOrZero
    private int idx = 0;
    @NotBlank
    private String user_id;
    @NotBlank
    private String title;
    @NotBlank
    private String content;
    private String display_date;
    @Builder.Default
    @PositiveOrZero
    @Min(value = 0)
    private int read_cnt = 0;
    private LocalDateTime reg_date;
    private LocalDateTime modify_date;
    private String interest;
    @Builder.Default
    @PositiveOrZero
    @Min(value = 0)
    private int reply_cnt = 0;
}
