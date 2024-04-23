package org.fullstack4.springmvc.domain;

import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MemberVO {
    private String user_id;
    private String name;
    private String pwd;
    private String email;
    private String jumin;
    private String addr1;
    private String addr2;
    private String birthday;
    private String job_code;
    private String interest;
    private int mileage;
    private String user_state;
    private LocalDate reg_date;
    private LocalDate modify_date;
    private LocalDate leave_date;
    private LocalDate pwd_change_date;
}
