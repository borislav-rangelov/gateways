package com.example.demo.model;

import com.example.demo.util.validation.EnumElement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeviceCreateModel {
    @NotBlank(message = "{device.validation.blank-vendor}")
    @Length(max = 250, message = "{device.validation.vendor-too-long}")
    private String vendor;

    @NotNull(message = "{device.validation.blank-status}")
    @EnumElement(type = DeviceStatus.class, message = "{device.validation.invalid-status}")
    private String status;
}
