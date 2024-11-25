package com.example.bomul_backend.operator.model.dao;

import com.example.bomul_backend.operator.model.entity.Operator;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface OperatorDao {
    int insertOperator(Operator operator);
    int insertSalt(Map<String, String> info);
    String selectSalt(String email);
    Operator selectOperator(Map<String, String> info);

}
