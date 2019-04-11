package feature.toggling.model;

public class Account {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public double getMaxAllowedBalance() {
        return maxAllowedBalance;
    }

    public void setMaxAllowedBalance(double maxAllowedBalance) {
        this.maxAllowedBalance = maxAllowedBalance;
    }

    private String email;
    private String phoneNumber;
    private String accountNumber;
    private AccountType accountType;
    private double balance;
    private boolean isBlocked = false;
    private double maxAllowedBalance;

    public Account(String accountNumber, double balance, String email, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.email = email;
        this.accountType = accountType;
        this.maxAllowedBalance = Double.MAX_VALUE;
    }

    public Account(String accountNumber, double balance, String email, AccountType accountType, double maxAllowedBalance) {
        this(accountNumber, balance, email, accountType);
        this.maxAllowedBalance = maxAllowedBalance;
    }
}
