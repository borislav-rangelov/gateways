package com.example.demo.controller;

import com.example.demo.entity.Device;
import com.example.demo.entity.Gateway;
import com.example.demo.model.*;
import com.example.demo.service.DeviceService;
import com.example.demo.service.GatewayService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(DeviceController.BASE_PATH)
@AllArgsConstructor
@Slf4j
public class DeviceController {

    static final String BASE_PATH = "/gateways/{gatewayId}/devices";
    private static final String ID_PATH = "/{id}";
    private static final String BY_VENDOR_PATH = "/search-vendor";

    private final ModelMapper mapper;
    private final DeviceService deviceService;
    private final GatewayService gatewayService;

    @GetMapping
    public HttpEntity<PageModel<DeviceWithGatewayModel>> getDevicePage(@PathVariable String gatewayId, Pageable pageable) {
        Page<Device> page = deviceService.getPageForGatewayId(gatewayId, pageable);
        Page<DeviceWithGatewayModel> models = page.map(gateway -> mapper.map(gateway, DeviceWithGatewayModel.class));
        return ResponseEntity.ok(new PageModel<>(models));
    }

    @GetMapping(ID_PATH)
    public HttpEntity<DeviceWithGatewayModel> getDeviceById(@PathVariable String gatewayId, @PathVariable Long id) {
        Optional<Device> result = deviceService.getByGatewayIdAndId(gatewayId, id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.map(result.get(), DeviceWithGatewayModel.class));
    }

    @PostMapping
    public HttpEntity<DeviceWithGatewayModel> createDevice(
            @PathVariable String gatewayId, @Valid @RequestBody DeviceCreateModel model,
            UriComponentsBuilder componentsBuilder) {

        Optional<Gateway> gateway = gatewayService.getById(gatewayId);
        if (gateway.isEmpty()) { // TODO return error message
            return ResponseEntity.notFound().build();
        }

        Device device = mapper.map(model, Device.class);
        device.setGateway(gateway.get());
        device = deviceService.create(device);

        return ResponseEntity
                .created(componentsBuilder.path(BASE_PATH + ID_PATH).buildAndExpand(gatewayId, device.getId()).toUri())
                .body(mapper.map(device, DeviceWithGatewayModel.class));
    }

    @PutMapping(ID_PATH)
    public HttpEntity<DeviceWithGatewayModel> updateDevice(@PathVariable String gatewayId, @PathVariable Long id,
                                                 @Valid @RequestBody DeviceCreateModel model) {
        Optional<Device> result = deviceService.getByGatewayIdAndId(gatewayId, id);
        if (result.isEmpty()) { // TODO handle upsert case
            return ResponseEntity.notFound().build();
        }
        mapper.map(model, result.get());
        Device device = deviceService.update(result.get());
        return ResponseEntity.ok(mapper.map(device, DeviceWithGatewayModel.class));
    }

    @DeleteMapping(ID_PATH)
    public HttpEntity<?> deleteDevice(@PathVariable String gatewayId, @PathVariable Long id) {
        // technically we don't need the check but the uri requires a gatewayId check

        Optional<Device> result = deviceService.getByGatewayIdAndId(gatewayId, id);
        if (result.isEmpty()) { // TODO handle upsert case
            return ResponseEntity.notFound().build();
        }

        deviceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
