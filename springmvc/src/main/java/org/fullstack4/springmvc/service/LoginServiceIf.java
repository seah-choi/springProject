package org.fullstack4.springmvc.service;

import org.fullstack4.springmvc.dto.LoginDTO;

public interface LoginServiceIf {
    LoginDTO login(String id, String pwd);
}
