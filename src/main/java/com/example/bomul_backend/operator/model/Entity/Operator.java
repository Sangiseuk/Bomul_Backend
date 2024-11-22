package com.example.bomul_backend.operator.model.Entity;

import com.example.bomul_backend.common.wrapper.PasswordWrapper;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Operator {
    private int operatorId;
    private String nickname;
    private String email;
    private PasswordWrapper password;
    private LocalDateTime createAt;

    public Operator(int operatorId, String nickname, String email, PasswordWrapper password, LocalDateTime createAt) {
        this.operatorId = operatorId;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.createAt = createAt;
    }

    public Operator(String email, PasswordWrapper password) {
        this.email = email;
        this.password = password;
    }


}
