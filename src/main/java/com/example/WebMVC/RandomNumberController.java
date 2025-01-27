package com.example.WebMVC;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class RandomNumberController {
    @Autowired
    private OperationService operationService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/generate")
    public String generateRandomNumbers(@RequestParam("min") int min,
                                        @RequestParam("max") int max,
                                        Model model) {
        List<Integer> randomNumbers = operationService.generateNumbers(min, max, 1000);
        String numbersAsString = randomNumbers.stream()
                .map(String::valueOf)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");

        double average = operationService.calculateAverage(randomNumbers);

        model.addAttribute("numbers", numbersAsString);
        model.addAttribute("average", average);
        return "index";
    }

}
