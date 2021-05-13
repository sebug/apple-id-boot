package ch.sebug.appleid;

public class AuthenticationInformation {
    private final String userID;
    private final String userName;

    public AuthenticationInformation(String userID, String userName) {
        this.userID = userID;
        this.userName = userName;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getUserName() {
        return this.userName;
    }
}
