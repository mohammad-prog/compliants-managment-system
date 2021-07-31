package com.example.complaintssystem.repository;

import com.example.complaintssystem.Complaint;
import com.example.complaintssystem.repository.ComplaintRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcComplaintRepository implements ComplaintRepository {

    private String driverClass;
    private String url;
    private String username;
    private String password;

    public JdbcComplaintRepository(@Value("database.driverClass") String driverClass,
                                   @Value("database.url") String url,
                                   @Value("database.username") String username,
                                   @Value("database.password") String password) {
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public void createComplaint(Complaint complaint) {
        try {
            Class.forName(driverClass);
            Connection con = DriverManager.getConnection(url, username, password);

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
            Class.forName(driverClass);
            Connection con = DriverManager.getConnection(url, username, password);
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
            Class.forName(driverClass);
            Connection con = DriverManager.getConnection(url, username, password);


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

            Class.forName(driverClass);
            Connection con = DriverManager.getConnection(url, username, password);

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
