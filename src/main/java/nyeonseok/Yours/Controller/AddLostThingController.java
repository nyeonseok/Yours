package nyeonseok.Yours.Controller;

import nyeonseok.Yours.Entity.LostThings;
import nyeonseok.Yours.Service.LostThingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddLostThingController {

    @Autowired
    private LostThingsService lostThingsService;

    @GetMapping("/lostThings")
    public String showLostThings(){
        return "lostThings";
    }

    @GetMapping("/addLostThing")
    public String showAddLostThing(){
        return "addLostThing";
    }

    @PostMapping("/addLostThing")
    public String submitSignupForm(@ModelAttribute LostThings lostThings) {
        lostThingsService.saveLostThings(lostThings);
        return "lostThings";
    }

}
