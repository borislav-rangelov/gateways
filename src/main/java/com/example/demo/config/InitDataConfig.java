package com.example.demo.config;

import com.example.demo.entity.Device;
import com.example.demo.entity.Gateway;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.repository.GatewayRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class InitDataConfig {

    private final GatewayRepository gatewayRepository;
    private final DeviceRepository deviceRepository;

    @Bean
    public ApplicationRunner dataLoader() {
        return args -> {

            long count = gatewayRepository.count();

            if (count == 0) {
                Device device1 = Device.builder()
                        .vendor("Vendor 1")
                        .status(Device.Status.ONLINE)
                        .build();

                Device device2 = Device.builder()
                        .vendor("Vendor 2")
                        .status(Device.Status.OFFLINE)
                        .build();

                Gateway gateway1 = Gateway.builder()
                        .address("192.168.0.101")
                        .name("Gateway 1")
                        .build();
                device1.setGateway(gateway1);
                device2.setGateway(gateway1);

                Gateway gateway2 = Gateway.builder()
                        .address("192.168.0.102")
                        .name("Gateway 2")
                        .build();

                gatewayRepository.saveAll(Arrays.asList(
                        gateway1,
                        gateway2
                ));

                deviceRepository.saveAll(Arrays.asList(
                        device1,
                        device2
                ));
            }

        };
    }
}
