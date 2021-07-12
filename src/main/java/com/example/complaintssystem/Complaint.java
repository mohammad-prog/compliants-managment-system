package com.example.complaintssystem;

import java.sql.Date;
import java.util.Objects;

public class Complaint {
    private int ComplaintNumber;
    private String author;
    private String status;
    private Date date;
    private String complaintText;

    public String getComplaintText() {
        return complaintText;
    }

    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getComplaintNumber() {
        return ComplaintNumber;
    }

    public void setComplaintNumber(int complaintNumber) {
        ComplaintNumber = complaintNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Complaint{" +
                "ComplaintNumber=" + ComplaintNumber +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complaint complaint = (Complaint) o;
        return ComplaintNumber == complaint.ComplaintNumber && Objects.equals(author, complaint.author) && Objects.equals(status, complaint.status) && Objects.equals(date, complaint.date) && Objects.equals(complaintText, complaint.complaintText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ComplaintNumber, author, status, date, complaintText);
    }
}
