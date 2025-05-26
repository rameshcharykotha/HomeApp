package com.home.HomeApp.services.impl;

import com.home.HomeApp.models.Saving;
import com.home.HomeApp.repositories.SavingRepository;
import com.home.HomeApp.services.SavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavingServiceImpl implements SavingService {

    @Autowired
    private SavingRepository savingRepository;

    @Override
    public Saving save(Saving saving) {
        return savingRepository.save(saving);
    }

    @Override
    public Optional<Saving> findById(Long id) {
        return savingRepository.findById(id);
    }

    @Override
    public List<Saving> findAll() {
        return savingRepository.findAll();
    }

    @Override
    public Saving update(Saving saving) {
        return savingRepository.save(saving);
    }

    @Override
    public void deleteById(Long id) {
        savingRepository.deleteById(id);
    }
}