package com.example.complaintssystem.repository;

import com.example.complaintssystem.User;

import java.sql.*;

public class jdbcUserRepository implements UserRepository {
    @Override
    public void registration(User user) {
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");


            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@ (DESCRIPTION =\n" +
                            "    (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))\n" +
                            "    (CONNECT_DATA =\n" +
                            "      (SERVER = DEDICATED)\n" +
                            "      (SERVICE_NAME = orclpdb)\n" +
                            "    )\n" +
                            "  )", "system", "orcl");


            String query = (" insert into users (firstName, lastName, email, password,userType)"
                    + " values (?, ?, ?, ?,?)");

            PreparedStatement insertIntoTable = con.prepareStatement(query);
            insertIntoTable.setString(1, user.getFirstName());
            insertIntoTable.setString(2, user.getLastName());
            insertIntoTable.setString(3, user.getEmail());
            insertIntoTable.setString(4, user.getPassword());
            insertIntoTable.setString(5, user.getUserType());
            insertIntoTable.execute();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean login(User user) {
        boolean flag;
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");


            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@ (DESCRIPTION =\n" +
                            "    (ADDRESS = (PROTOCOL = TCP)(HOST = localhost)(PORT = 1521))\n" +
                            "    (CONNECT_DATA =\n" +
                            "      (SERVER = DEDICATED)\n" +
                            "      (SERVICE_NAME = orclpdb)\n" +
                            "    )\n" +
                            "  )", "system", "orcl");

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            flag = false;
            while (rs.next()) {


                if (user.getEmail().equals(rs.getString("email")) && user.getPassword().equals(rs.getString("password"))) {
                    flag = true;
                } else {

                    flag = false;
                }

            }


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return flag;
    }
}
