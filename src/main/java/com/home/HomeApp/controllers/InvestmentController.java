package com.home.HomeApp.controllers;

import com.home.HomeApp.models.Investment;
import com.home.HomeApp.repositories.CategoryRepository;
import com.home.HomeApp.repositories.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/investments")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.206:3000"})
public class InvestmentController {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Investment> getAllInvestments() {
        return investmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable Long id) {
        Optional<Investment> investment = investmentRepository.findById(id);
        if (investment.isPresent()) {
            return ResponseEntity.ok(investment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Investment> createInvestment(@RequestBody Investment investment) {
        if (investment.getCategory() == null || !categoryRepository.existsById(investment.getCategory().getId())) {
            return ResponseEntity.badRequest().build();
        }
        Investment savedInvestment = investmentRepository.save(investment);
        return ResponseEntity.ok(savedInvestment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable Long id, @RequestBody Investment investmentDetails) {
        Optional<Investment> optionalInvestment = investmentRepository.findById(id);
        if (optionalInvestment.isPresent()) {
            Investment investment = optionalInvestment.get();
            investment.setDate(investmentDetails.getDate());
            investment.setInvestmentType(investmentDetails.getInvestmentType());
            investment.setAmount(investmentDetails.getAmount());
            investment.setCategory(investmentDetails.getCategory());
            investment.setNotes(investmentDetails.getNotes());
            return ResponseEntity.ok(investmentRepository.save(investment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        Optional<Investment> optionalInvestment = investmentRepository.findById(id);
        if (optionalInvestment.isPresent()) {
            investmentRepository.delete(optionalInvestment.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}