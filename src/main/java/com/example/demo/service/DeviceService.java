package com.example.demo.service;

import com.example.demo.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DeviceService {
    Page<Device> getPageForGatewayId(String gatewayId, Pageable pageable);
    Optional<Device> getByGatewayIdAndId(String gatewayId, long id);
    Page<Device> getPageByVendor(String vendor, Pageable pageable);
    Device create(Device gateway);
    Device update(Device gateway);
    void delete(long id);
}
