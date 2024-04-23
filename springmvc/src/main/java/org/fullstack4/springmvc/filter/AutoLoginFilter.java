package org.fullstack4.springmvc.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebFilter(urlPatterns = {"/*"})
public class AutoLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("AutoLoginFilter");

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        String auto_login = "";
        String id = "";
        String name = "";
        Cookie[] cookies = req.getCookies();
        for(Cookie c : cookies) {
            if(c.getName().equals("auto_login")) {
                auto_login = c.getValue();
            } if(c.getName().equals("id")) {
                id = c.getValue();
                name = c.getValue();
            }
        }
        if (auto_login.equals("Y")) {
            session.setAttribute("user_id",id);
            session.setAttribute("name", name);
        }
        chain.doFilter(req,resp);
    }

}
