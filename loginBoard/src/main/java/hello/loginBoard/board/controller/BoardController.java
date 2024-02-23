package hello.loginBoard.board.controller;

import hello.loginBoard.board.dto.BoardDTO;
import hello.loginBoard.board.service.BoardService;
import hello.loginBoard.member.domain.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping(value = "/board")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "board/list";
    }

    @GetMapping("/board/save")
    public String addBoard(Model model){
        model.addAttribute("boardDTO",new BoardDTO());
        log.info("새 게시글 등록 폼");
        return "board/saveForm";
    }
//    @PostMapping("/board/save")
//    public String saveBoard(@ModelAttribute("boardDTO") BoardDTO boardDTO){
//        log.info("save DTO"+boardDTO.toString());
//        boardService.save(boardDTO,mem);
//        return "board/save";
//    }
    @PostMapping("/board/save")
    public String saveBoard(@ModelAttribute("boardDTO") BoardDTO boardDTO, HttpSession session) {
        // 세션에서 현재 로그인한 회원을 가져옴
        Member loggedInMember = (Member) session.getAttribute("loginMember");

        if (loggedInMember != null) {
            log.info("save DTO" + boardDTO.toString());
            // 현재 로그인한 회원을 사용하여 board 저장
            boardService.save(boardDTO, loggedInMember);
        } else {
            // 로그인되지 않은 경우 처리 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/loginForm";
        }

        return "redirect:/board";
    }



}
