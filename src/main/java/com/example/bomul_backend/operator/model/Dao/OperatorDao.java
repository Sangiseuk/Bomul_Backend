package com.example.bomul_backend.operator.model.Dao;

import com.example.bomul_backend.operator.model.Entity.Operator;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface OperatorDao {
    int insertOperator(Operator operator);
    String selectSalt(String email);
    Operator selectOperator(Map<String, String> info);
}
