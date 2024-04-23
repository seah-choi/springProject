package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.dto.MemberDTO;
import org.fullstack4.springmvc.service.MemberServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;

@Log4j2
@Controller
@RequestMapping(value = "/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceIf memberService;

    @GetMapping("/view")
    public void view(@RequestParam(name="user_id", defaultValue = "") String user_id,
                     HttpServletRequest req,
                     Model model){
        log.info("==========");
        log.info("MemberController >> view()");
        log.info("user_id : "+user_id);
        log.info("==========");


        MemberDTO memberDTO = memberService.view(user_id);

        log.info("memberDTO : "+memberDTO);
        HttpSession session = req.getSession();
        session.setAttribute("member", memberDTO);
//        model.addAttribute("member", memberDTO);

    }

    @GetMapping("/join")
    public void join(){
        log.info("==========");
        log.info("MemberController >> joinGET()");
        log.info("==========");

    }

    @PostMapping("/join")
    public String join(@Valid MemberDTO memberDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.info("Errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("member", memberDTO);
            return "redirect:/member/join";
        }

        log.info("dto : " + memberDTO);

        int result = memberService.join(memberDTO);
        log.info(result);
        if(result > 0){
            return "redirect:/login/login";
        } else {
            return "redirect:/member/join";
        }
    }

    @GetMapping("/modify")
    public void modifyGET(@RequestParam(name="user_id", defaultValue = "") String user_id,
                            Model model){
        log.info("==========");
        log.info("MemberController >> modifyGET");
        log.info("==========");

        MemberDTO memberDTO = memberService.view(user_id);
        model.addAttribute("member", memberDTO);
    }

    @PostMapping("/modify")
    public String modifyPOST(MemberDTO memberDTO, RedirectAttributes redirectAttributes, Model model){
        log.info("==========");
        log.info("MemberController >> modifyPost()");
        log.info("==========");

        int result = memberService.modify(memberDTO);
        if(result > 0 ){
            return "redirect:/bbs/list";
        } else {
            return "redirect:/member/modify?user_id="+memberDTO.getUser_id();
        }

    }

    //@PostMapping("/delete")
    @RequestMapping(value = "/delete", method = {RequestMethod.GET})
    public String delete(@RequestParam(name="user_id", defaultValue = "") String user_id){
        log.info("==========");
        log.info("MemberController >> delete()");
        log.info("==========");

        int result = memberService.delete(user_id);
        if(result > 0){
            return "redirect:/index.jsp";
        } else {
            return "redirect:/member/modify?user_id="+user_id;
        }
    }

    @ResponseBody
    @PostMapping("/idCheck")
    public void idCheck(@RequestParam(name="user_id", defaultValue = "") String user_id,
                       HttpServletResponse resp){
        int result = memberService.idCheck(user_id);

        log.info("result : "+result);
        if(result > 0){
            try {
                resp.getWriter().print("Y");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                resp.getWriter().print("N");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
