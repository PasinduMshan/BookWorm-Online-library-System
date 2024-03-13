package lk.ijse.tm;

public class MemberTm {
    private String memberId;
    private String Name;
    private String Address;
    private String Email;
    private String Contact;

    public MemberTm() {
    }

    public MemberTm(String memberId, String name, String address, String email, String contact) {
        this.memberId = memberId;
        Name = name;
        Address = address;
        Email = email;
        Contact = contact;
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
}
