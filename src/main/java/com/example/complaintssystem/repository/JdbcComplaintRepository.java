package com.example.complaintssystem.repository;

import com.example.complaintssystem.Complaint;
import com.example.complaintssystem.repository.ComplaintRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcComplaintRepository implements ComplaintRepository {


    @Override
    public void createComplaint(Complaint complaint) {
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

            String query = (" insert into complaints (complaintNumber, author, status, complaintDate,complaintText)"
                    + " values (?, ?, ?, ?,?)");

            PreparedStatement insertIntoTable = con.prepareStatement(query);
            insertIntoTable.setInt(1, complaint.getComplaintNumber());
            insertIntoTable.setString(2, complaint.getAuthor());
            insertIntoTable.setString(3, complaint.getStatus());
            insertIntoTable.setDate(4, complaint.getDate());
            insertIntoTable.setString(5, complaint.getComplaintText());
            insertIntoTable.execute();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Complaint> listComplaintByAuthor(String author) {

        List<Complaint> complaints = new ArrayList<>();
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
            PreparedStatement query = con.prepareStatement(
                    "select * from complaints WHERE author=?");

            query.setString(1, author);


            ResultSet rs = query.executeQuery();


            while (rs.next()) {


                Complaint complaint = new Complaint();
                complaint.setComplaintNumber(rs.getInt("complaintNumber"));
                complaint.setAuthor(rs.getString("author"));
                complaint.setStatus(rs.getString("status"));
                complaint.setDate(rs.getDate("complaintDate"));
                complaint.setComplaintText(rs.getString("complaintText"));
                complaints.add(complaint);

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return complaints;
    }

    @Override
    public void changeStatus(Complaint complaint) {
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

            String query = (" UPDATE complaints " +
                    "SET status=? ");

            PreparedStatement updateStatement = con.prepareStatement(query);
            updateStatement.setString(1, complaint.getStatus());
            updateStatement.execute();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Complaint> listAllComplaints() {


        List<Complaint> complaints = new ArrayList<>();
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
            ResultSet rs = stmt.executeQuery("select * from complaints");
            while (rs.next()) {


                Complaint complaint = new Complaint();
                complaint.setComplaintNumber(rs.getInt("complaintNumber"));
                complaint.setAuthor(rs.getString("author"));
                complaint.setStatus(rs.getString("status"));
                complaint.setDate(rs.getDate("complaintDate"));
                complaint.setComplaintText(rs.getString("complaintText"));
                complaints.add(complaint);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return complaints;

    }

}
