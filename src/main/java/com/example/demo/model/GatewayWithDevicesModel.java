package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GatewayWithDevicesModel extends GatewayModel {
    private List<DeviceModel> devices;
}
