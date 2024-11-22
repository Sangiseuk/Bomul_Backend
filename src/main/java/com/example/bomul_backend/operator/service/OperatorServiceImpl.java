package com.example.bomul_backend.operator.service;

import com.example.bomul_backend.operator.model.Dao.OperatorDao;
import com.example.bomul_backend.operator.model.Entity.Operator;
import org.springframework.stereotype.Service;

@Service
public class OperatorServiceImpl implements OperatorService {

    private final OperatorDao operatorDao;

    public OperatorServiceImpl(OperatorDao operatorDao){
        this.operatorDao = operatorDao;
    }

    @Override
    public int signup(Operator operator) {

        int signUpResult = operatorDao.insertOperator(operator);

        return signUpResult;
    }
}
