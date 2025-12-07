package com.agency.commission.domain;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brokers_list")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Broker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column (nullable = false, length = 11)
    private String cpf;

    @Column (nullable = false, length = 100)
    private String email;

    @Column (nullable = false)
    private Boolean active = true;

    @Column (name = "Created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column (name = "Updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }
}
