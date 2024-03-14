package lk.ijse.tm;

public class AdminTm {
    private String adminId;
    private String Name;
    private String Email;

    public AdminTm() {
    }

    public AdminTm(String adminId, String name, String email) {
        this.adminId = adminId;
        Name = name;
        Email = email;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
