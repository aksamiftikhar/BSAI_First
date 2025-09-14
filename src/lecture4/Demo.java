package lecture4;

public class Demo {
    public static void main(String[] args) {
        // Create a Person
        Person p = new Person();
        p.setName("Ayesha");
        p.setCnic("35202-1234567-8");

        // Create a BankAccount and link it with Person
        BankAccount acc1 = new BankAccount();
        acc1.setAccountNumber("PK-001");
        acc1.setOwner(p);
        acc1.deposit(1000.0);

        System.out.println("Owner: " + acc1.getOwner().getName());
        System.out.println("Account: " + acc1.getAccountNumber());
        System.out.println("Balance: " + acc1.getBalance());

        // === Reference types in action (aliasing) ===
        BankAccount alias = acc1;            // 'alias' points to THE SAME object as acc1
        alias.withdraw(250.0);               // change via alias

        System.out.println("\nAfter withdrawing 250 via 'alias' reference:");
        System.out.println("acc1 balance: " + acc1.getBalance()); // reflects change
        System.out.println("alias balance: " + alias.getBalance());

        // Create a separate account (different object in memory)
        BankAccount acc2 = new BankAccount();
        acc2.setAccountNumber("PK-002");
        acc2.setOwner(p);                     // same Person object can own multiple accounts
        acc2.deposit(500.0);

        System.out.println("\nSecond account created for SAME Person:");
        System.out.println("acc2 owner: " + acc2.getOwner().getName());
        System.out.println("acc2 balance: " + acc2.getBalance());

        // Demonstrate encapsulation:
        // The following would NOT compile (fields are private):
        // acc1.balance = 99999.0;  // <-- illegal; use setters/methods instead

        // Safer updates through methods:
        acc1.deposit(100.0);
        System.out.println("\nAfter safe deposit of 100 to acc1:");
        System.out.println("acc1 balance: " + acc1.getBalance());
    }
}
