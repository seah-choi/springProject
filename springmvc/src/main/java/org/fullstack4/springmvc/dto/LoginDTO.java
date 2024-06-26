package org.fullstack4.springmvc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class LoginDTO {
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
