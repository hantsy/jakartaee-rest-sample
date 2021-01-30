/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain.user;

import com.example.domain.common.AbstractEntity;
import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hantsy
 */
@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AbstractEntity<Long> {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String firstName;
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @ElementCollection()
    private Set<String> authorities = new HashSet<>();

}
