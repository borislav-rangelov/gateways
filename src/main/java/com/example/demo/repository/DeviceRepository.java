package com.example.demo.repository;

import com.example.demo.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Long> {
//    @EntityGraph("Device.gateway")
    Page<Device> getByGatewayId(String gatewayId, Pageable pageable);

//    @EntityGraph("Device.gateway")
    Optional<Device> getByGatewayIdAndId(String gatewayId, long id);

    @EntityGraph("Device.gateway")
    Page<Device> getByVendorLike(String vendor, Pageable pageable);
}
