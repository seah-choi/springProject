package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.dto.BbsReplyDTO;
import org.fullstack4.springmvc.service.BbsReplyServiceIf;
import org.fullstack4.springmvc.service.BbsServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping(value = "/bbsReply")
@RequiredArgsConstructor
public class BbsReplyController {

    private final BbsReplyServiceIf bbsServiceIf;

    @PostMapping("/regist")
    public String registPost(@Valid BbsReplyDTO bbsReplyDTO,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("==========");
        log.info("BbsController >> registPost()");

        if (bindingResult.hasErrors()) {
            log.info("Errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("bbs", bbsReplyDTO);
            return "redirect:/bbs/view?idx="+bbsReplyDTO.getBbs_idx();
        }

        log.info("bbsReplyDTO : " + bbsReplyDTO.toString());

        int result = bbsServiceIf.reply_regist(bbsReplyDTO);
        log.info("result : " + result);
        log.info("==========");


        if (result > 0) {
            return "redirect:/bbs/view?idx="+bbsReplyDTO.getBbs_idx();
        } else {
            return "/bbs/view?idx="+bbsReplyDTO.getBbs_idx();
        }
    }

//    @PostMapping("delete")
//    public String deletePOST(@RequestParam(name = "idx", defaultValue = "0") int idx,
//                             Model model) {
//
//        int result = bbsServiceIf.delete(idx);
//        if (result > 0) {
//            return "redirect:/bbs/view?idx" + idx;
//        } else {
//            return "redirect:/bbs/view?idx" + idx;
//        }
//    }
}
