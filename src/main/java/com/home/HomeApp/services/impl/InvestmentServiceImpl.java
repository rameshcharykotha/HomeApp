package com.home.HomeApp.services.impl;

import com.home.HomeApp.models.Investment;
import com.home.HomeApp.repositories.InvestmentRepository;
import com.home.HomeApp.services.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestmentServiceImpl implements InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Override
    public Investment save(Investment investment) {
        return investmentRepository.save(investment);
    }

    @Override
    public Optional<Investment> findById(Long id) {
        return investmentRepository.findById(id);
    }

    @Override
    public List<Investment> findAll() {
        return investmentRepository.findAll();
    }

    @Override
    public Investment update(Investment investment) {
        return investmentRepository.save(investment);
    }

    @Override
    public void deleteById(Long id) {
        investmentRepository.deleteById(id);
    }
}