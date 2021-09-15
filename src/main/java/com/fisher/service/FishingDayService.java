package com.fisher.service;



import com.fisher.domain.FishingDay;
import com.fisher.exceptions.FishingDayNotFoundException;
import com.fisher.repository.FishingDayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;


@Service
public class FishingDayService {

    @Autowired
    private final FishingDayRepository fishingDayRepository;

    public FishingDayService(FishingDayRepository fishingDayRepository) {
        this.fishingDayRepository = fishingDayRepository;
    }


    public FishingDay saveFishingDayToDB(MultipartFile file, String fishName, String date, int fishSize
            )
    {
        FishingDay p = new FishingDay();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            p.setPhoto(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setFishName(fishName);
        p.setDate(date);
        p.setFishSize(fishSize);

       return fishingDayRepository.save(p);

    }

    public List<FishingDay> showFishDays() {
        List <FishingDay> fishingDayList = fishingDayRepository.findAll();
        if(fishingDayList.isEmpty()){
            throw new FishingDayNotFoundException("There are no fishing memories to see");
        }
        return  fishingDayList;
    }

    public FishingDay showFishdayById(Long id) {
        FishingDay fishingDay = fishingDayRepository.getById(id);
        return  fishingDay;
    }

    @Transactional
    public int deleteFishDay(long id) {
        return fishingDayRepository.deleteFishDay(id);
    }

    public void updateFishingDay(Long fishingDayId, String fishName, int fishSize) {
       Optional<FishingDay> fishingDayOptional = fishingDayRepository.findById(fishingDayId);
        FishingDay fd = new FishingDay();
        fd.setFishName(fishName);
        fd.setFishSize(fishSize);
        fishingDayRepository.save(fd);

    }


}
