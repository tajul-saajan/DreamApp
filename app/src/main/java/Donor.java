import com.example.saajan.dreamapp.Request;

import java.util.ArrayList;

public class Donor {
    private String name,roll,bloodGroup,contactNo,department;
    private  ArrayList<Donation> donationHistory;

    public Donor(String name, String roll, String bloodGroup, String contactNo, String department) {
        this.name = name;
        this.roll = roll;
        this.bloodGroup = bloodGroup;
        this.contactNo = contactNo;
        this.department = department;
        this.donationHistory = new ArrayList<Donation>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public ArrayList<Donation> getDonationHistory() {
        return donationHistory;
    }

    public void addDonationHistory(Donation donation) {
        this.donationHistory.add(donation);

    }

    public class Donation{
        String reqID,date,roll;
    }
}


