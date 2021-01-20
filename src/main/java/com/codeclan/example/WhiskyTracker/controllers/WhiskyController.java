package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping(value = "/whiskies")
    public ResponseEntity<List<Whisky>> findWhiskyAndFilterByYear(@RequestParam(name="year", required=false) Integer year) {
        if (year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/distilleries")
    public ResponseEntity<List<Whisky>> findWhiskiesWithDistilleriesAgedQueryString(@RequestParam(name="named") String name, @RequestParam(name="age") Integer age){
        return new ResponseEntity<>(whiskyRepository.findByDistilleryNameAndAge(name, age), HttpStatus.OK);
    }

    @GetMapping(value = "/whiskies/distilleries/region")
    public ResponseEntity<List<Whisky>> findWhiskiesWithDistilleriesByRegion(@RequestParam(name="region") String region){
        return new ResponseEntity<>(whiskyRepository.findByDistilleryRegion(region), HttpStatus.OK);
    }


}

//, @RequestParam(name="region", required=false) String region
// if (region != null){
//            return new ResponseEntity<>(whiskyRepository.findByRegion(region), HttpStatus.OK);
//        }
