package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/*
* a unique serial number (string),
human-readable name (string),
IPv4 address (to be validated),
multiple associated peripheral devices.

* */

@Entity
@Table(name = "gateways")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gateway {

    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @GeneratedValue(generator = "uuid")
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 15)
    private String address;

    @OneToMany(mappedBy = "gateway", fetch = FetchType.EAGER)
    private List<Device> devices;
}
