package com.company.authentication.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import lombok.*;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    /*
   @ManyToMany - Ta adnotacja oznacza, że pole roles będzie reprezentować relację wiele do wielu. Oznacza to,
   że jeden użytkownik może mieć wiele ról, a jedna rola może być przypisana do wielu użytkowników.

fetch = FetchType.LAZY - Określa sposób, w jaki dane powinny być pobierane z bazy danych. W tym przypadku,
kiedy odwołujesz się do pola roles w instancji User, Hibernate (lub inna implementacja JPA) nie będzie
automatycznie pobierać roli z bazy danych, chyba że zostaną one wyraźnie użyte. Jest to zalecane dla relacji
typu wiele do wielu, aby unikać ładowania zbędnych danych.

@JoinTable - Jest używane do określenia tabeli pośredniczącej, która będzie przechowywać informacje o relacji
wiele do wielu między User a Role.

name = "user_roles" - Określa nazwę tabeli pośredniczącej, która przechowuje informacje o relacji wiele
do wielu między użytkownikami a rolami.

joinColumns - Definiuje kolumnę klucza obcego (user_id), która łączy tabelę użytkowników z tabelą pośredniczącą.

inverseJoinColumns - Definiuje kolumnę klucza obcego (role_id), która łączy tabelę ról z tabelą pośredniczącą.

Podsumowując, ten fragment kodu oznacza, że istnieje relacja wiele do wielu między encją User a encją Role,
 przechowywana w tabeli pośredniczącej o nazwie user_roles. Ta relacja umożliwia przypisanie wielu ról
 do wielu użytkowników. */

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
