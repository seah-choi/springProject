package org.fullstack4.springmvc.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.fullstack4.springmvc.dto.BbsDTO;
import org.fullstack4.springmvc.dto.PageRequestDTO;
import org.fullstack4.springmvc.dto.PageResponseDTO;
import org.fullstack4.springmvc.service.BbsServiceIf;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.UUID;


@Log4j2
@Controller
@RequestMapping (value = "/bbs")
@RequiredArgsConstructor
public class BbsController {

    private final BbsServiceIf bbsServiceIf;

//    @GetMapping("/list")
//    public void list(Model model){
//        log.info("==========");
//        log.info("BbsController >> list()");
//
//        List<BbsDTO> bbsDTOList = bbsServiceIf.listAll();
//        model.addAttribute("bbsList", bbsDTOList);
//
//        log.info("==========");
//
//    }

    //페이징 리스트
    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO
            ,BindingResult bindingResult
            ,RedirectAttributes redirectAttributes
            , Model model){
        log.info("==========");
        log.info("BbsController >> list() START");
        log.info("pageRequestDTO : "+ pageRequestDTO.toString());

        if(bindingResult.hasErrors()){
            log.info("BbsController >> list errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
        }

        PageResponseDTO<BbsDTO> responseDTO = bbsServiceIf.bbsListByPage(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);

        log.info("responseDTO : "+ responseDTO.toString());
        log.info("BbsController >> list() END");
        log.info("==========");

    }

    @GetMapping("/view")
    public void view(@RequestParam(name="idx", defaultValue = "0") int idx,
                     Model model){
        log.info("==========");
        log.info("BbsController >> view()");
        log.info("idx : "+idx);
        log.info("==========");

        BbsDTO bbsDTO = bbsServiceIf.view(idx);
        model.addAttribute("bbs", bbsDTO);

    }

    @GetMapping("/regist")
    public void registGET(){
        log.info("==========");
        log.info("BbsController >> registGET()");
        log.info("==========");

    }

    @PostMapping("/regist")
    public String registPOST(@Valid BbsDTO bbsDTO,
                             BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("==========");
        log.info("BbsController >> registPOst()");

        if(bindingResult.hasErrors()){
            log.info("Errors");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("bbs",bbsDTO);
            return "redirect:/bbs/regist";
        }

        log.info("bbsDTO : "+ bbsDTO.toString());

        int result = bbsServiceIf.regist(bbsDTO);
        log.info("result : "+result);
        log.info("==========");



        if(result > 0) {
            return "redirect:/bbs/list";
        } else {
            return "redirect:/bbs/regist";
        }
    }

    @GetMapping("/modify")
    public void modifyGET(@RequestParam(name="idx", defaultValue = "0") int idx,
                          Model model){
        log.info("==========");
        log.info("BbsController >> modifyGET");
        log.info("idx : "+idx);
        log.info("==========");

        BbsDTO bbsDTO = bbsServiceIf.view(idx);
        model.addAttribute("bbs", bbsDTO);
    }

    @PostMapping("/modify")
    public String modifyPOST(BbsDTO bbsDTO,
                             Model model, RedirectAttributes redirectAttributes){
        log.info("==========");
        log.info("BbsController >> modifyPOst()");
        log.info("bbsDTO : "+ bbsDTO.toString());
        log.info("==========");

        int result = bbsServiceIf.modify(bbsDTO);
        if(result > 0) {
            return "redirect:/bbs/view?idx="+bbsDTO.getIdx();
        } else {
            return "redirect:/bbs/modify";
        }
    }

    @PostMapping("delete")
    public String deletePOST(@RequestParam(name="idx", defaultValue = "0") int idx,
                           Model model){
        log.info("==========");
        log.info("BbsController >> deletePost()");
        log.info("==========");

        int result = bbsServiceIf.delete(idx);
        if(result > 0) {
            return "redirect:/bbs/list";
        } else {
            return "redirect:/bbs/view?idx"+idx;
        }
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String fileUploadGet(){
        return "/bbs/fileUpload";
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String fileUploadPost(@RequestParam("file") MultipartFile file){
        String uploadFolder = "D:\\java4\\uploads";
        String fileRealName = file.getOriginalFilename();
        long size = file.getSize();
        String fileExt = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());
        log.info("===================");
        log.info("uploadFolder : "+ uploadFolder);
        log.info("fileRealName : "+fileRealName);
        log.info("fileExt : "+fileExt);
        log.info("size : "+size);
        log.info("===================");

        //새로운 파일명 생성
        UUID uuid = UUID.randomUUID();
        String[] uuids = uuid.toString().split("-");
        String newName = uuids[0];
        log.info("===================");
        log.info("uuid : "+ uuid);
        log.info("uuids : "+uuids);
        log.info("newName : "+newName);

        File saveFile = new File(uploadFolder + "\\"+newName + fileExt);
        try{
            file.transferTo(saveFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        log.info("===================");
        return "/bbs/fileUpload";
    }

    @RequestMapping(value = "/fileUpload2", method = RequestMethod.POST)
    public String fileUploadPost2(MultipartHttpServletRequest files){
        String uploadFolder = "D:\\java4\\uploads";

        log.info("===================");
        log.info("uploadFolder : "+ uploadFolder);

        List<MultipartFile> list = files.getFiles("files");
        for(int i=0; i<list.size(); i++){
            String fileRealName = list.get(i).getOriginalFilename();
            long size = list.get(i).getSize();
            String fileExt = fileRealName.substring(fileRealName.lastIndexOf("."), fileRealName.length());

            log.info("fileRealName : "+fileRealName);
            log.info("fileExt : "+fileExt);
            log.info("size : "+size);

            //새로운 파일명 생성
            UUID uuid = UUID.randomUUID();
            String[] uuids = uuid.toString().split("-");
            String newName = uuids[0];
            log.info("===================");
            log.info("uuid : "+ uuid);
            log.info("uuids : "+uuids);
            log.info("newName : "+newName);

            File saveFile = new File(uploadFolder + "\\"+newName + fileExt);
            try{
                list.get(i).transferTo(saveFile);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        log.info("===================");

        return "/bbs/fileUpload";
    }
}
