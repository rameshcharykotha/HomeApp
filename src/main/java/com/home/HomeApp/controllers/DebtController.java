package com.home.HomeApp.controllers;

import com.home.HomeApp.models.Debt;
import com.home.HomeApp.services.DebtService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.206:3000"})
public class DebtController {

    private final DebtService debtService;

    public DebtController(DebtService debtService) {
        this.debtService = debtService;
    }

    @GetMapping("/api/debts")
    public List<Debt> getAllDebts() {
        return debtService.findAll();
    }

    @PostMapping("/api/debts")
    public Debt createDebt(@RequestBody Debt debt) {
        if (debt.getDate() == null || debt.getCreditor() == null) {
            throw new IllegalArgumentException("Date and Creditor are required fields.");
        }
        return debtService.save(debt);
    }

    @PutMapping("/api/debts/{id}")
    public Debt updateDebt(@PathVariable Long id, @RequestBody Debt debt) {
        if (debt.getDate() == null || debt.getCreditor() == null) {
            throw new IllegalArgumentException("Date and Creditor are required fields.");
        }
        debt.setId(id);
        return debtService.update(debt);
    }

    @DeleteMapping("/api/debts/{id}")
    public void deleteDebt(@PathVariable Long id) {
        debtService.deleteById(id);
    }
}