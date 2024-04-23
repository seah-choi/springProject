package org.fullstack4.springmvc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
    public static void setCookies(HttpServletResponse response, String domain, String path, int expire, String name, String val) {
        Cookie cookie = new Cookie(name, val);
        if(domain != null && !domain.isEmpty()) {
            cookie.setDomain(domain);
        }
        cookie.setPath((path!=null && !path.isEmpty() ? path : "/"));
        cookie.setMaxAge(expire);
        response.addCookie(cookie);
    }

    public static String getCookieValue(HttpServletRequest req, String name) {
        String rtnCookie = "";
        Cookie[] cookies = req.getCookies();
        for(Cookie c : cookies) {
            if(name.equals(c.getName())) {
                rtnCookie = c.getValue();
                break;
            }
        }
        return rtnCookie;
    }

    public static void setDeleteCookie(HttpServletResponse response, String domain, String path, int expire, String name, String val) {
        setCookies(response, domain, path, expire, name, val);
    }
}
