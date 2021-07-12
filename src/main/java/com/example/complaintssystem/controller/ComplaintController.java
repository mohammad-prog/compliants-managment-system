package com.example.complaintssystem.controller;

import com.example.complaintssystem.Complaint;
import com.example.complaintssystem.repository.JdbcComplaintRepository;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = "/Api/v1/complaint")
public class ComplaintController {
    private JdbcComplaintRepository complaintRepository = new JdbcComplaintRepository();

    @GetMapping("/listAllComplaints")
    public List<Complaint> listAllComplaints() {
        return complaintRepository.listAllComplaints();
    }

    @GetMapping("/listComplaintByAuthor")
    public List<Complaint> listComplaintByAuthor(@RequestBody String author) {
        return complaintRepository.listComplaintByAuthor(author);
    }

    @PostMapping("/changeStatus")
    public void changeStatus(@RequestBody Complaint complaint) {
        complaintRepository.changeStatus(complaint);
    }

    @PostMapping("/createComplaint")
    public void createComplaint(@RequestBody Complaint complaint) {
        complaintRepository.createComplaint(complaint);
    }


}
