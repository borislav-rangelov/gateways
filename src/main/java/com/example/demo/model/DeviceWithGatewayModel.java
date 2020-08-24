package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceWithGatewayModel extends DeviceModel {
    private GatewayModel gateway;
}
