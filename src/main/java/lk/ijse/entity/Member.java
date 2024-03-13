package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    private String memberId;
    private String Name;
    private String Address;
    private String Email;
    private String Contact;
    private String D_O_B;

    public Member() {
    }

    public Member(String memberId, String name, String address, String email, String contact, String d_O_B) {
        this.memberId = memberId;
        Name = name;
        Address = address;
        Email = email;
        Contact = contact;
        D_O_B = d_O_B;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getD_O_B() {
        return D_O_B;
    }

    public void setD_O_B(String d_O_B) {
        D_O_B = d_O_B;
    }
}
