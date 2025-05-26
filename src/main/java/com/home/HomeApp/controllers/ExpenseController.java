package com.home.HomeApp.controllers;

import com.home.HomeApp.models.Expense;
import com.home.HomeApp.repositories.CategoryRepository;
import com.home.HomeApp.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.206:3000"})
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Optional<Expense> expense = expenseRepository.findById(id);
        if (expense.isPresent()) {
            return ResponseEntity.ok(expense.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense) {
        if (expense.getCategory() == null || !categoryRepository.existsById(expense.getCategory().getId())) {
            return ResponseEntity.badRequest().build();
        }
        Expense savedExpense = expenseRepository.save(expense);
        return ResponseEntity.ok(savedExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            Expense expense = optionalExpense.get();
            expense.setDate(expenseDetails.getDate());
            expense.setCategory(expenseDetails.getCategory());
            expense.setAmount(expenseDetails.getAmount());
            expense.setNotes(expenseDetails.getNotes());
            expense.setDescription(expenseDetails.getDescription());
            expense.setRecurring(expenseDetails.getRecurring());
            expense.setPaymentMethod(expenseDetails.getPaymentMethod());
            expense.setRecurrenceInterval(expenseDetails.getRecurrenceInterval());
            expense.setStore(expenseDetails.getStore());
            return ResponseEntity.ok(expenseRepository.save(expense));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            expenseRepository.delete(optionalExpense.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/upload-receipt")
    public ResponseEntity<Expense> uploadReceipt(@RequestParam("file") MultipartFile file) {
        // Create a new expense with dummy data
        Expense newExpense = new Expense();
        newExpense.setDate(new Date());
        newExpense.setDescription("Receipt Upload");
        newExpense.setAmount(BigDecimal.valueOf(0.01)); // Dummy amount
        newExpense.setCategory(null); // Assuming category can be null
        newExpense.setPaymentMethod("Cash"); // Dummy payment method
        newExpense.setStore("Unknown Store"); // Dummy store
        newExpense.setNotes("Uploaded via receipt");
        newExpense.setRecurring(false);
        newExpense.setRecurrenceInterval(null);

        try {
            newExpense.setReceipt(file.getBytes());
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }

        Expense savedExpense = expenseRepository.save(newExpense);
        return ResponseEntity.ok(savedExpense);
    }
}