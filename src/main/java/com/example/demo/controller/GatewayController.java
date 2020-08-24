package com.example.demo.controller;

import com.example.demo.entity.Gateway;
import com.example.demo.model.GatewayCreateModel;
import com.example.demo.model.GatewayWithDevicesModel;
import com.example.demo.model.PageModel;
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
@RequestMapping(GatewayController.BASE_PATH)
@AllArgsConstructor
@Slf4j
public class GatewayController {

    static final String BASE_PATH = "/gateways";
    private static final String ID_PATH = "/{id}";
    private final ModelMapper mapper;
    private final GatewayService service;

    @GetMapping
    public HttpEntity<PageModel<GatewayWithDevicesModel>> getGatewayPage(Pageable pageable) {
        Page<Gateway> page = service.getPage(pageable);
        Page<GatewayWithDevicesModel> models = page.map(gateway -> mapper.map(gateway, GatewayWithDevicesModel.class));
        return ResponseEntity.ok(new PageModel<>(models));
    }

    @GetMapping(ID_PATH)
    public HttpEntity<GatewayWithDevicesModel> getGatewayById(@PathVariable String id) {
        Optional<Gateway> result = service.getById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.map(result.get(), GatewayWithDevicesModel.class));
    }

    @PostMapping
    public HttpEntity<GatewayWithDevicesModel> createGateway(
            @Valid @RequestBody GatewayCreateModel model, UriComponentsBuilder componentsBuilder) {
        Gateway gateway = mapper.map(model, Gateway.class);
        gateway = service.create(gateway);
        return ResponseEntity
                .created(componentsBuilder.path(BASE_PATH + ID_PATH).buildAndExpand(gateway.getId()).toUri())
                .body(mapper.map(gateway, GatewayWithDevicesModel.class));
    }

    @PutMapping(ID_PATH)
    public HttpEntity<GatewayWithDevicesModel> updateGateway(@PathVariable String id, @Valid @RequestBody GatewayCreateModel model) {
        Optional<Gateway> result = service.getById(id);
        if (result.isEmpty()) { // TODO handle upsert case
            return ResponseEntity.notFound().build();
        }
        mapper.map(model, result.get());
        Gateway gateway = service.update(result.get());
        return ResponseEntity.ok(mapper.map(gateway, GatewayWithDevicesModel.class));
    }

    @DeleteMapping(ID_PATH)
    public HttpEntity<?> deleteGateway(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
