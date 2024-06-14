package nyeonseok.Yours.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import nyeonseok.Yours.Dto.JoinRequest;
import nyeonseok.Yours.Dto.LoginRequest;
import nyeonseok.Yours.Entity.Member;
import nyeonseok.Yours.Service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/cookie-login")
public class LoginController {
    private final MemberService memberService;

//    @GetMapping(value = { "/login"})
//    public String home(@CookieValue(name = "userId", required = false) Long userId, Model model) {
//        model.addAttribute("loginType", "cookie-login");
//        model.addAttribute("pageName", "쿠키 로그인");
//
//        Member loginMember = memberService.getLoginUser(userId);
//
//        if(loginUser != null) {
//            model.addAttribute("username", loginUser.getNickname());
//            loginUser.getNickname());
//        }
//
//        return "home";
//    }

    @GetMapping("/join")
    public String joinPage(Model model) {
        model.addAttribute("loginType", "security-login");
        model.addAttribute("pageName", "Security 로그인");

        model.addAttribute("joinRequest", new JoinRequest());
        return "join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute JoinRequest joinRequest, BindingResult bindingResult, Model model) {
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");

        // loginId 중복 체크
        if(memberService.checkLoginIdDuplicate(joinRequest.getUserId())) {
            bindingResult.addError(new FieldError("joinRequest", "loginId", "로그인 아이디가 중복됩니다."));
        }
        // 닉네임 중복 체크
        if(memberService.checkNicknameDuplicate(joinRequest.getUsername())) {
            bindingResult.addError(new FieldError("joinRequest", "nickname", "닉네임이 중복됩니다."));
        }
        // password와 passwordCheck가 같은지 체크
        if(!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())) {
            bindingResult.addError(new FieldError("joinRequest", "passwordCheck", "바밀번호가 일치하지 않습니다."));
        }

        if(bindingResult.hasErrors()) {
            return "join";
        }

        memberService.join(joinRequest);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");

        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }

//    @PostMapping("/login")
//    public String login(@ModelAttribute LoginRequest loginRequest, BindingResult bindingResult,
//                        HttpServletResponse response, Model model) {
//        model.addAttribute("loginType", "cookie-login");
//        model.addAttribute("pageName", "쿠키 로그인");
//
//        Member member = memberService.login(loginRequest);
//
//        // 로그인 아이디나 비밀번호가 틀린 경우 global error return
//        if(member == null) {
//            bindingResult.reject("loginFail", "로그인 아이디 또는 비밀번호가 틀렸습니다.");
//        }
//
//        if(bindingResult.hasErrors()) {
//            return "login";
//        }
//
//        // 로그인 성공 => 쿠키 생성
//        Cookie cookie = new Cookie("userId", String.valueOf(member.getId()));
//        cookie.setMaxAge(60 * 60);  // 쿠키 유효 시간 : 1시간
//        response.addCookie(cookie);
//
//        return "redirect:/";
//    }
@PostMapping("/login")
public String login(@ModelAttribute LoginRequest loginRequest, BindingResult bindingResult,
                    HttpServletResponse response, Model model) {
    Member member = memberService.login(loginRequest);

    if (member == null) {
        bindingResult.reject("loginFail", "로그인 아이디 또는 비밀번호가 틀렸습니다.");
        return "login";
    }

    Cookie cookie = new Cookie("userId", String.valueOf(member.getId()));
    cookie.setMaxAge(60 * 60);  // 쿠키 유효 시간 : 1시간
    cookie.setPath("/"); // 모든 경로에서 쿠키가 유효하도록 설정
    response.addCookie(cookie);

    return "redirect:/";
}


    @GetMapping("/logout")
    public String logout(HttpServletResponse response, Model model) {
        model.addAttribute("loginType", "cookie-login");
        model.addAttribute("pageName", "쿠키 로그인");

        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
