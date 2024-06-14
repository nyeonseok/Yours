package nyeonseok.Yours.Controller;

import nyeonseok.Yours.Entity.LostThings;
import nyeonseok.Yours.Repository.LostThingsRepository;
import nyeonseok.Yours.Service.LostThingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LostThingController {

    @Autowired
    private LostThingsService lostThingsService;


    // 모든 분실물 데이터를 반환하는 엔드포인트
    @GetMapping("/getLostThingsData")
    public List<LostThings> getLostThingsData() {
        return lostThingsService.getAllLostThings();
    }




}
