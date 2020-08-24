package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeviceModel {
    private Long id;

    private String vendor;

    private DeviceStatus status;

    private LocalDateTime created;
}
