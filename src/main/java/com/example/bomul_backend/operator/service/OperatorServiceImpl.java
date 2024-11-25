package com.example.bomul_backend.operator.service;

import com.example.bomul_backend.common.wrapper.PasswordWrapper;
import com.example.bomul_backend.operator.model.dao.OperatorDao;
import com.example.bomul_backend.operator.model.entity.Operator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final OperatorDao operatorDao;

    public OperatorServiceImpl(OperatorDao operatorDao){
        this.operatorDao = operatorDao;
    }

    @Transactional
    @Override
    public int signup(Operator operator) {
        String salt = generateSalt();
        PasswordWrapper passwordWrapper = operator.getPassword();
        passwordWrapper.setSalt(salt);

        String encryptedPassword = passwordWrapper.encryptPassword();
        passwordWrapper.setPassword(encryptedPassword);
        operator.setPassword(passwordWrapper);

        operatorDao.insertOperator(operator);

        Map<String, String> saltInfo = new HashMap<>();
        saltInfo.put("operatorId", String.valueOf(operator.getOperatorId()));
        saltInfo.put("salt", salt);
        return operatorDao.insertSalt(saltInfo);
    }

    @Override
    public Operator login(String email, String password) {
        String salt = operatorDao.selectSalt(email);
        PasswordWrapper passwordWrapper = new PasswordWrapper();
        passwordWrapper.setSalt(salt);
        passwordWrapper.setPassword(password);
        String encryptedPassword = passwordWrapper.encryptPassword();
        System.out.println(encryptedPassword);

        Map<String, String> info = new HashMap<>();
        info.put("email", email);
        info.put("password", encryptedPassword);

        return operatorDao.selectOperator(info);
    }

    private String generateSalt() {
        return UUID.randomUUID().toString();
    }
}
