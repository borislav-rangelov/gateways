package com.example.demo.service;

import com.example.demo.entity.Device;
import com.example.demo.entity.Gateway;
import com.example.demo.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository repository;

    @Override
    public Page<Device> getPageForGatewayId(String gatewayId, Pageable pageable) {
        return repository.getByGatewayId(gatewayId, pageable);
    }

    @Override
    public Optional<Device> getByGatewayIdAndId(String gatewayId, long id) {
        return repository.getByGatewayIdAndId(gatewayId, id);
    }

    @Override
    public Page<Device> getPageByVendor(String vendor, Pageable pageable) {
        return repository.getByVendorLike(vendor, pageable);
    }

    @Override
    public Device create(Device device) {
        return repository.saveAndFlush(device);
    }

    @Override
    public Device update(Device device) {
        return repository.saveAndFlush(device);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
