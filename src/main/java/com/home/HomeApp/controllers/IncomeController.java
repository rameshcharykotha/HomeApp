package com.home.HomeApp.controllers;

import com.home.HomeApp.models.Income;
import com.home.HomeApp.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/incomes")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.206:3000"})
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public Income createIncome(@RequestBody Income income) {
        return incomeService.save(income);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Income> getIncomeById(@PathVariable Long id) {
        Optional<Income> income = incomeService.findById(id);
        return income.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Income> getAllIncomes() {
        return incomeService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable Long id, @RequestBody Income income) {
        if (!incomeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        income.setId(id);
        return ResponseEntity.ok(incomeService.update(income));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id) {
        if (!incomeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        incomeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}