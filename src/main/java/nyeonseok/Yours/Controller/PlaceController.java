package nyeonseok.Yours.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlaceController {

    @GetMapping("/place")
    public String showPlace(){
        return "place";
    }

    @GetMapping("/addPlace")
    public String showAddPlace(){
        return "addPlace";
    }
}
