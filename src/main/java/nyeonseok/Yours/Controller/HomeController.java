package nyeonseok.Yours.Controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/checkLogin")
    @ResponseBody
    public boolean checkLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Cookie Name: " + cookie.getName() + ", Cookie Value: " + cookie.getValue());
                if ("userId".equals(cookie.getName()) && cookie.getValue() != null && !cookie.getValue().isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {
        boolean isLoggedIn = false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("Cookie Name: " + cookie.getName() + ", Cookie Value: " + cookie.getValue());
                if ("userId".equals(cookie.getName())) {
                    isLoggedIn = true;
                    break;
                }
            }
        }
        System.out.println(isLoggedIn);
        model.addAttribute("isLoggedIn", isLoggedIn);
        return "home";
    }
}
