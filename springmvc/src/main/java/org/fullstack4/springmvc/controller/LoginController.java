package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.domain.MemberVO;
import org.fullstack4.springmvc.dto.LoginDTO;
import org.fullstack4.springmvc.dto.MemberDTO;
import org.fullstack4.springmvc.service.LoginServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.fullstack4.springmvc.utils.CookieUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.net.URLEncoder;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {
    private final LoginServiceIf loginServiceIf;

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public void loginGET(HttpServletRequest req, Model model) {
        log.info("==========");
        log.info("LoginController >> loginGET()");

        CookieUtil.getCookieValue(req,"id");
        CookieUtil.getCookieValue(req,"name");

        String acc_url = req.getHeader("referer");
        model.addAttribute("acc_url", req.getHeader("referer"));
        log.info("==========");

    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public String loginPOST(Model model,
                            @Valid LoginDTO memberDTO,
                            @RequestParam(name="acc_url", defaultValue = "/bbs/list", required = false) String acc_url,
                            @RequestParam(name="save_id", defaultValue = "") String save_id,
                            @RequestParam(name="auto_login", defaultValue = "") String auto_login,
                            BindingResult bindingResult,
                            HttpServletRequest req,
                            HttpServletResponse resp,
                            RedirectAttributes redirectAttributes) {
//        String rtn_url = "";
//        try {
//            rtn_url = URLEncoder.encode(acc_url, "UTF-8");
//        } catch (Exception e){
//
//        }
//
//        log.info(rtn_url);
//        acc_url.split("/");

        log.info("==========");
        log.info("LoginController >> loginPOST()");
        log.info("==========");

        LoginDTO loginMemberDTO = loginServiceIf.login(memberDTO.getUser_id(), memberDTO.getPwd());

//        if(bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
//            return "redirect:/login/login";
//        }

        if(loginMemberDTO != null) {
            //true:세션 없으면 생성하여 리턴, 있으면 있는 거 리턴 (기본값)
            //false : 세션 있으면 리턴, 없으면 생성 안함.

            if(save_id != null && save_id.equals("Y")) {
                CookieUtil.setCookies(resp,"","",60*60*24,"save_id",memberDTO.getUser_id());
                log.info("로그인 아이디 저장");
            }

            if(auto_login != null && auto_login.equals("Y")){
                CookieUtil.setCookies(resp,"", "/", 60*60*24, "auto_login","Y");
                CookieUtil.setCookies(resp,"", "/", 60*60*24, "id",memberDTO.getUser_id());
                CookieUtil.setCookies(resp,"", "/", 60*60*24, "name",memberDTO.getName());
                log.info("자동 로그인");
            }

            HttpSession session = req.getSession();
            session.setAttribute("member", loginMemberDTO); //나중에 필요한 정보만 넣기(아이디, 이름 등)
            session.setAttribute("user_id", memberDTO.getUser_id());
            session.setAttribute("name", memberDTO.getName());

            return "redirect:"+acc_url;
        } else {
            System.out.println("로그인실패");
            redirectAttributes.addFlashAttribute("loginErr", "아이디 혹은 비밀번호가 일치하지 않습니다.");
            return "redirect:/login/login";
        }

    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest req) {
        log.info("==========");
        log.info("LoginController >> logout()");

        HttpSession session = req.getSession(false);
        if(session != null) {
            //세션 삭제
            session.invalidate();
        }

        log.info("==========");

        return "redirect:/index.jsp";
    }
}
