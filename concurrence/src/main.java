
import java.util.ArrayList;
import java.util.List;


public class main {
    private static List<Client> clientsnames = new ArrayList<>();

    /**
     * The main creates the list of clients.
     * @param args
     */
    public static void main(String[] args) {

        Dispatcher a = new Dispatcher();
        for (int i = 1; i <= 10; i++) {
            clientsnames.add(new Client("Number of client " + i,getBankOperation()));
        }

        a.attend(clientsnames);

    }

    /**
     * We define the types of tasks worked for the bank, this types can be done for the cashier, supervisor and director
     * @return Types of bank operations.
     */
    public static BankOperation getBankOperation(){
        switch ((int)(Math.random()*2)+1)
        {
            case 1:
                return BankOperation.DEPOSITS;
            case 2:
                return BankOperation.WITHDRAWALS;
            case 3:
                return BankOperation.ISSUES;
            default:
                return null;
        }
    }

    }


