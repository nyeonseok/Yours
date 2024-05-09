package nyeonseok.Yours.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LostThingController {
    @GetMapping("/lostThings")
    public String showLostThings(){
        return "lostThings";
    }

    @GetMapping("/addLostThing")
    public String showAddLostThing(){
        return "addLostThing";
    }
}
