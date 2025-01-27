package com.example.calc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculationService {

    @Autowired
    private CalculationRepository repository;

    public void saveCalculation(String operation, Double result) {
        Calculation calculation = new Calculation();
        calculation.setOperation(operation);
        calculation.setResult(result);
        repository.save(calculation);
    }

    public List<Calculation> getAllCalculations() {
        return repository.findAll();
    }
}