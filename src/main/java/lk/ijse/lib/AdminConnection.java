package lk.ijse.lib;

public class AdminConnection {
    private static AdminConnection adminConnection;

    private String userName;
    private String Password;

    private AdminConnection() {}

    public static AdminConnection getInstance() {
        return adminConnection == null ? adminConnection = new AdminConnection() : adminConnection;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
