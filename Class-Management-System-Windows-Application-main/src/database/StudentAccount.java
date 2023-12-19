package database;

public class StudentAccount {
    String email, accountID;

    public StudentAccount(String email, String accountID) {
        this.email = email;
        this.accountID = accountID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }
    
}
