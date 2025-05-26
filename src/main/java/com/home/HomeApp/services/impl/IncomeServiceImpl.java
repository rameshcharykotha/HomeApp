package com.home.HomeApp.services.impl;

import com.home.HomeApp.models.Income;
import com.home.HomeApp.repositories.IncomeRepository;
import com.home.HomeApp.services.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IncomeServiceImpl implements IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;

    @Override
    public Income save(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public Optional<Income> findById(Long id) {
        return incomeRepository.findById(id);
    }

    @Override
    public List<Income> findAll() {
        return incomeRepository.findAll();
    }

    @Override
    public Income update(Income income) {
        return incomeRepository.save(income);
    }

    @Override
    public void deleteById(Long id) {
        incomeRepository.deleteById(id);
    }
}