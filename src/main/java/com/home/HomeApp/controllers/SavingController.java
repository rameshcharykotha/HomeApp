package com.home.HomeApp.controllers;

import com.home.HomeApp.models.Saving;
import com.home.HomeApp.services.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/savings")
@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.0.206:3000"})
public class SavingController {

    @Autowired
    private SavingService savingService;

    @PostMapping
    public Saving createSaving(@RequestBody Saving saving) {
        return savingService.save(saving);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saving> getSavingById(@PathVariable Long id) {
        Optional<Saving> saving = savingService.findById(id);
        return saving.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Saving> getAllSavings() {
        return savingService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Saving> updateSaving(@PathVariable Long id, @RequestBody Saving saving) {
        if (!savingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        saving.setId(id);
        return ResponseEntity.ok(savingService.update(saving));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSaving(@PathVariable Long id) {
        if (!savingService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        savingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}