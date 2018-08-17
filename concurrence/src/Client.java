public class Client {
    private String nameClient;
    private BankOperation operation;

    public Client(String nameClient, BankOperation operation) {
        this.nameClient = nameClient;
        this.operation = operation;
    }


    public String getName() {
        return nameClient;
    }

    public BankOperation getOperation() {
        return operation;
    }
}
