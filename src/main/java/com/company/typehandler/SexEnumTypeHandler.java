package com.company.typehandler;

import com.company.pojo.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(SexEnum.class)
@MappedJdbcTypes(JdbcType.INTEGER)
public class SexEnumTypeHandler implements TypeHandler<SexEnum> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i,sexEnum.getId());
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, String s) throws SQLException {
        int id=resultSet.getInt(s);
        return SexEnum.getSexEnumByNum(id);
    }

    @Override
    public SexEnum getResult(ResultSet resultSet, int i) throws SQLException {
        int id=resultSet.getInt(i);
        return SexEnum.getSexEnumByNum(id);
    }

    @Override
    public SexEnum getResult(CallableStatement callableStatement, int i) throws SQLException {
        int id =callableStatement.getInt(i);
        return SexEnum.getSexEnumByNum(id);
    }
}
