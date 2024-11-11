package com.cibertec.login.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @JsonIgnoreProperties({"roles", "handler", "hibernateLazyInitializer"})
    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
