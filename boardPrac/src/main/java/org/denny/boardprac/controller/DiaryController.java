package org.denny.boardprac.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.denny.boardprac.dto.DiaryDTO;
import org.denny.boardprac.dto.PageRequestDTO;
import org.denny.boardprac.dto.PageResponseDTO;
import org.denny.boardprac.service.DiaryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/diary")
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String registerPost(DiaryDTO diaryDTO, RedirectAttributes redirectAttributes) {

        log.info("================  DiaryController registerPost  ===================");
        log.info("================  DiaryController registerPost  ===================");
        log.info(diaryDTO);

        Long dno = diaryService.register(diaryDTO);

        log.info("DNO : " + dno);

        redirectAttributes.addFlashAttribute("result", dno);

        return  "redirect:/diary/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        PageResponseDTO<DiaryDTO> responseDTO = diaryService.getList(pageRequestDTO);

        model.addAttribute("res", responseDTO);

    }


}
