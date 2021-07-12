package com.example.complaintssystem.repository;

import com.example.complaintssystem.Complaint;

import java.util.List;

public interface ComplaintRepository {

    void createComplaint(Complaint complaint);

    List<Complaint> listComplaintByAuthor(String author);

    void changeStatus(Complaint complaint);

    List<Complaint> listAllComplaints();


}
