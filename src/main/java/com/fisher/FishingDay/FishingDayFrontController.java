package com.fisher.FishingDay;


import com.fisher.exceptions.service.FishingDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Controller
public class FishingDayFrontController {

    @Autowired
    private final FishingDayService fishingDayService;

    public FishingDayFrontController(FishingDayService fishingDayService) {
        this.fishingDayService = fishingDayService;
    }

    @GetMapping("/menu")
    public String viewMenu(){
        return "welcome";

    }


    @GetMapping("/fishDayForm")
    public String fishDayForm(Model model){
        model.addAttribute("fishingDay",new FishingDay());
        return "fish_day";
    }


    @PostMapping("/addFishingDay")
    public String saveUser(@RequestParam("file") MultipartFile file,
                           @RequestParam String fishName,
                           @RequestParam Integer fishSize,
                           @RequestParam String date ) throws IOException {
        fishingDayService.saveFishingDayToDB(file, fishName,date, fishSize);

        return "added_trip";
    }



    @GetMapping("/list")
    public String showAllFishingDays(Model model){
        List<FishingDay> fishingDays= fishingDayService.showFishDays();
        model.addAttribute("fishing",fishingDays);
        return "listFD";
    }

}
