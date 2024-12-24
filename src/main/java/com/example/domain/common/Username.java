/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.domain.common;

import jakarta.persistence.Embeddable;

/**
 * @author hantsy
 */
@Embeddable
public record Username(
        String username
){}
