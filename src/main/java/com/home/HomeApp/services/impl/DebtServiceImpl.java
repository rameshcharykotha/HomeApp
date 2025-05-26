package com.home.HomeApp.services.impl;

import com.home.HomeApp.models.Debt;
import com.home.HomeApp.repositories.DebtRepository;
import com.home.HomeApp.services.DebtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DebtServiceImpl implements DebtService {

    @Autowired
    private DebtRepository debtRepository;

    @Override
    public Debt save(Debt debt) {
        return debtRepository.save(debt);
    }

    @Override
    public Optional<Debt> findById(Long id) {
        return debtRepository.findById(id);
    }

    @Override
    public List<Debt> findAll() {
        return debtRepository.findAll();
    }

    @Override
    public Debt update(Debt debt) {
        return debtRepository.save(debt);
    }

    @Override
    public void deleteById(Long id) {
        debtRepository.deleteById(id);
    }
}