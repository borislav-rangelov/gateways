package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/*
a UID (number),
vendor (string),
date created,
status - online/offline.
 */

@Entity
@Table(name = "devices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedEntityGraph(name = "Device.gateway",
        attributeNodes = @NamedAttributeNode("gateway")
)
public class Device {

    @Id
    @GeneratedValue
    private Long id;

    @Length(max = 250)
    @Column(nullable = false)
    private String vendor;

    @NotNull
    @Column(nullable = false)
    private Status status;

    @CreationTimestamp
    private LocalDateTime created;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_gateway"))
    private Gateway gateway;

    public enum Status {
        ONLINE, OFFLINE
    }
}
