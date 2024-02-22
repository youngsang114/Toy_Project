package hello.loginBoard.member.controller;

import hello.loginBoard.member.domain.Member;
import hello.loginBoard.member.dto.LoginFormDTO;
import hello.loginBoard.member.dto.MemberSaveDTO;
import hello.loginBoard.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/add")
    public String addForm(Model model){
        log.info("회원가입 form");
        model.addAttribute("member",new Member());
        return "members/addMemberForm";
    }

    @PostMapping("/members/add")
    public String saveMember(@Valid @ModelAttribute("member") MemberSaveDTO memberSaveDTO, BindingResult result){
        if (result.hasErrors()){
            log.info("회원가입 실패! -> 다시 입력");
            return "members/addMemberForm";
        }
        memberService.save(memberSaveDTO);
        log.info("회원가입 성공 -> 홈 화면으로");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model){
        log.info("로그인 form");
        model.addAttribute("loginFormDTO",new LoginFormDTO());
        return "login/loginForm";
    }
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginFormDTO") LoginFormDTO loginFormDTO, BindingResult bindingResult,
                        HttpServletRequest request){

        if (bindingResult.hasErrors()){
            log.info("field error 발생");
            return "login/loginForm";
        }
        try{
            Member loginMember = memberService.login(loginFormDTO);
            log.info("login? {}",loginMember);

            // 세션이 있으면 있는 세션 반환, 없으면 신규 세션 생성
            HttpSession session = request.getSession();
            // 세션에 로그인 회원 정보 보관
            session.setAttribute("loginMember",loginMember);

            return "redirect:/";

        } catch (RuntimeException e){
            bindingResult.reject("loginFail"," 아이디 비밀번호가 일치하지 않습니다.");
            return "login/loginForm";

        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if (session!=null){
            session.invalidate();
        }
        return "redirect:/";
    }

}
