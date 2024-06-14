package nyeonseok.Yours.Service;

import nyeonseok.Yours.Entity.LostThings;
import nyeonseok.Yours.Repository.LostThingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LostThingsService {

    @Autowired
    private LostThingsRepository lostThingsRepository;

    // 모든 분실물 데이터를 가져오는 메서드
    public List<LostThings> getAllLostThings() {
        return lostThingsRepository.findAll();
    }

    public Optional<LostThings> getLostThingById(int id) {
        return lostThingsRepository.findById(String.valueOf(id));
    }

    public void saveLostThings(LostThings lostThings){lostThingsRepository.save(lostThings);}

    public Optional<LostThings> findByitemName(String itemName){
        return lostThingsRepository.findByitemName(itemName);
    }

}
