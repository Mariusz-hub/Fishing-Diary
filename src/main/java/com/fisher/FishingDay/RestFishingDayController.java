package com.fisher.FishingDay;


import com.fisher.exceptions.FishingDayNotFoundException;
import com.fisher.exceptions.UserNotFoundException;
import com.fisher.user.RestUserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class RestFishingDayController {

    private static final Logger logger = LoggerFactory.getLogger(RestUserController.class);
    @Autowired
    private final FishingDayService fishingDayService;

    public RestFishingDayController(FishingDayService fishingDayService) {
        this.fishingDayService = fishingDayService;
    }

    @PostMapping("/addFishingDay")
    public ResponseEntity<FishingDay> addFishingDay(@RequestParam MultipartFile file,
                                                    @RequestParam String fishName,
                                                    @RequestParam Integer fishSize,
                                                    @RequestParam String date  ){

        return new ResponseEntity<>(fishingDayService.saveFishingDayToDB(file, fishName,date, fishSize), HttpStatus.OK);
    }

    @PutMapping(path="{fishingDayId}")
    public void updateFishingDay(@PathVariable("fishingDayId") Long fishingDayId,
                              @RequestParam(required = false) String fishName,
                              @RequestParam (required = false) int fishSize
    ){
        fishingDayService.updateFishingDay(fishingDayId,fishName,fishSize);
    }

    @GetMapping("/getFishDay")
    public ResponseEntity<?> showFishDay(){
        List<FishingDay> fd = fishingDayService.showFishDays();
        if (fd.isEmpty()){
            logger.error("List of Fish Days is empty");
            throw new FishingDayNotFoundException("List of Fish Days is empty");
        }
        return new ResponseEntity<>(fd,HttpStatus.OK);
    }

    @GetMapping("/getFishDay/{id}")
    public ResponseEntity<FishingDay> showFishDayById(@PathVariable Long id){
        FishingDay fishingDay = fishingDayService.showFishdayById(id);
        if (fishingDay == null) {
            logger.error("Fishing Day  with id {} not found", id);
            throw new FishingDayNotFoundException("FishingDay not found");
        }
        logger.info("Return Client with id: {}", id);


        return new ResponseEntity<>(fishingDay,HttpStatus.OK);
    }

    @DeleteMapping("deleteFishDay/{id}/delete")
    public ResponseEntity<?> deleteFishDay(@PathVariable(value = "id") long id){
        if(fishingDayService.deleteFishDay(id) > 0){
            logger.info("Delete FishDay with id: {}", id);
            return new ResponseEntity<>(fishingDayService.deleteFishDay(id), HttpStatus.OK);
        }
        logger.info("FishDay with id {} not exists", id);
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
