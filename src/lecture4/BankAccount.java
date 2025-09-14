package lecture4;

class BankAccount {
    private String accountNumber;
    private Person owner;       // reference type (to another object)
    private double balance;     // keep state private

    // Behavior that safely changes internal state:
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;

        }
    }

    // Getters/Setters
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public Person getOwner() { return owner; }
    public void setOwner(Person owner) { this.owner = owner; }

    public double getBalance() { return balance; }
}
