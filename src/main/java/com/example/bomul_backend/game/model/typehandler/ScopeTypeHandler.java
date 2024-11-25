package com.example.bomul_backend.game.model.typehandler;

import com.example.bomul_backend.game.model.entity.Scope;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.*;

public class ScopeTypeHandler extends BaseTypeHandler<Scope.ScopeType> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Scope.ScopeType parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }

    @Override
    public Scope.ScopeType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        int code = rs.getInt(columnName);
        return Scope.ScopeType.fromValue(code);
    }

    @Override
    public Scope.ScopeType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        int code = rs.getInt(columnIndex);
        return Scope.ScopeType.fromValue(code);
    }

    @Override
    public Scope.ScopeType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        int code = cs.getInt(columnIndex);
        return Scope.ScopeType.fromValue(code);
    }
}