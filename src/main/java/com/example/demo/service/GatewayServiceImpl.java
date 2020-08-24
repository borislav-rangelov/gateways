package com.example.demo.service;

import com.example.demo.entity.Gateway;
import com.example.demo.repository.GatewayRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GatewayServiceImpl implements GatewayService {

    private final GatewayRepository repository;

    @Override
    public Page<Gateway> getPage(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Gateway> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Gateway create(Gateway gateway) {
        return repository.saveAndFlush(gateway);
    }

    @Override
    public Gateway update(Gateway gateway) {
        return repository.saveAndFlush(gateway);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }
}
