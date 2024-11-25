package com.example.bomul_backend.operator.service;

import com.example.bomul_backend.common.wrapper.PasswordWrapper;
import com.example.bomul_backend.operator.model.Entity.Operator;

public interface OperatorService {
     public int signup(Operator operator);
     public Operator login(String email, String password);
}
