package com.example.bomul_backend.operator.service;

import com.example.bomul_backend.common.wrapper.PasswordWrapper;
import com.example.bomul_backend.operator.model.Dao.OperatorDao;
import com.example.bomul_backend.operator.model.Entity.Operator;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final OperatorDao operatorDao;

    public OperatorServiceImpl(OperatorDao operatorDao){
        this.operatorDao = operatorDao;
    }

    @Override
    public int signup(Operator operator) {
        String salt = generateSalt();
        PasswordWrapper passwordWrapper = operator.getPassword();
        passwordWrapper.setSalt(salt);

        String encryptedPassword = passwordWrapper.encryptPassword();

        passwordWrapper.setPassword(encryptedPassword);

        operator.setPassword(passwordWrapper);

        return operatorDao.insertOperator(operator);
    }

    @Override
    public Operator login(String email, String password) {
        String salt = operatorDao.selectSalt(email);
        PasswordWrapper passwordWrapper = new PasswordWrapper();
        passwordWrapper.setSalt(salt);
        passwordWrapper.setPassword(password);

        String encryptedPassword = passwordWrapper.encryptPassword();

        Map<String, String> info = new HashMap<>();
        info.put("email", email);
        info.put("password", encryptedPassword);

        Operator tmp =operatorDao.selectOperator(info);
        return tmp;
    }

    private String generateSalt() {
        return UUID.randomUUID().toString();
    }
}
