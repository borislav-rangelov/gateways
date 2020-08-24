package com.example.demo.model;

import com.example.demo.util.validation.IPv4;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class GatewayCreateModel {

    @NotBlank(message = "{gateway.validation.blank-name}")
    @Length(max = 250, message = "{gateway.validation.name-too-long}")
    private String name;

    @NotBlank(message = "{gateway.validation.blank-address}")
    @IPv4
    private String address;
}
