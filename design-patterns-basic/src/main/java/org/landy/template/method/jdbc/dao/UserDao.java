package org.landy.template.method.jdbc.dao;


import org.landy.template.method.jdbc.JdbcTemplate;
import org.landy.template.method.jdbc.RowMapper;
import org.landy.template.method.jdbc.entity.User;
import org.landy.template.method.jdbc.util.RowMapperUtils;

import java.sql.ResultSet;
import java.util.List;

/**
 * 不用继承，主要是为了解耦
 */
public class UserDao {

    //为什么不继承，主要是为了解耦
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(null);

    public List<?> query() {
        String sql = "select * from t_user";
        return jdbcTemplate.executeQuery(sql, new RowMapperUtils(User.class), null);
//        return jdbcTemplate.executeQuery(sql, (ResultSet rs, int rowNum) -> {
//            User member = new User();
//            member.setUsername(rs.getString("username"));
//            member.setPassword(rs.getString("password"));
//            member.setNickName(rs.getString("nickName"));
//            member.setAge(rs.getInt("age"));
//            member.setAddr(rs.getString("addr"));
//            return member;
//        }, null);
    }


}
