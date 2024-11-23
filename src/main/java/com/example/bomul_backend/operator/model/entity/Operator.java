package com.example.bomul_backend.operator.model.entity;

import com.example.bomul_backend.common.wrapper.PasswordWrapper;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Operator {
    private int operatorId;
    private String nickname;
    private String email;
    private PasswordWrapper password;
    private LocalDateTime createAt;
}
