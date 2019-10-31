package org.landy.template.method.jdbc.test;


import org.landy.template.method.jdbc.dao.UserDao;

public class UserDaoTest {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        System.out.println(userDao.query());

    }
}
