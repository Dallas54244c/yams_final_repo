package com.example.probability;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/probability")
public class ProbabilityController {

    private final ProbabilityService probabilityService;

    public ProbabilityController(ProbabilityService probabilityService) {
        this.probabilityService = probabilityService;
    }

    @GetMapping("/calculate/{coup}")
    public double calculateProbability(@PathVariable int coup, @RequestParam int[] des) {
        return probabilityService.calculateProbability(coup, des);
    }
}
