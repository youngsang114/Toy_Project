package toyproject.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import toyproject.board.dto.BoardDTO;
import toyproject.board.service.BoardService;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    @GetMapping("/save")
    public String saveForm(){
        log.info("save form");
        return "save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){
        log.info("save DTO"+boardDTO.toString());
        boardService.save(boardDTO);
        return "redirect:/";
    }
}
