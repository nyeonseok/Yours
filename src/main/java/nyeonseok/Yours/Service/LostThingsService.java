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

    public void saveLostThings(LostThings lostThings){lostThingsRepository.save(lostThings);}

    public LostThings findById(Long id) {
        Optional<LostThings> optionalLostThing = lostThingsRepository.findById(id);
        return optionalLostThing.orElseThrow(() -> new RuntimeException("분실물을 찾을 수 없습니다."));
    }

}
