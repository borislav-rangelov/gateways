package com.example.demo.service;

import com.example.demo.entity.Gateway;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GatewayService {
    Page<Gateway> getPage(Pageable pageable);
    Optional<Gateway> getById(String id);
    Gateway create(Gateway gateway);
    Gateway update(Gateway gateway);
    void delete(String id);
}
