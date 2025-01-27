package com.example.calc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculationService calculationService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("calculations", calculationService.getAllCalculations());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam double a, @RequestParam double b, @RequestParam String operation, Model model) {
        double result = 0;

        // Логика вычислений
        switch (operation) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "x":
                result = a * b;
                break;
            case "/":
                if (b != 0) {
                    result = a / b;
                } else {
                    model.addAttribute("error", "Cannot divide by zero");
                }
                break;
        }

        if (result != 0 || operation.equals("divide") && b != 0) {
            calculationService.saveCalculation(a + " " + operation + " " + b, result);
        }

        return "redirect:/";
    }
}
