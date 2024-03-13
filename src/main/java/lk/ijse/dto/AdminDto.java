package lk.ijse.dto;

public class AdminDto {

    private String adminId;
    private String Name;
    private String Email;
    private String userName;
    private String password;
    private String confirmPassword;

    public AdminDto() {
    }

    public AdminDto(String adminId, String name, String email, String userName, String password) {
        this.adminId = adminId;
        Name = name;
        Email = email;
        this.userName = userName;
        this.password = password;
    }

    public AdminDto(String adminId, String name, String email, String userName, String password, String confirmPassword) {
        this.adminId = adminId;
        Name = name;
        Email = email;
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String id) {
        adminId = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }


}
