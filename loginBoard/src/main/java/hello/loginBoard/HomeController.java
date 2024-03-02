package hello.loginBoard;

import hello.loginBoard.login.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String home(@SessionAttribute(value = "loginMember",required = false) Member loginMember, Model model){

        if (loginMember == null){
            return "home";
        }

        // 세션이 유지되면 로그인홈으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";


    }
}
