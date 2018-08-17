

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * We create the list related to differente class that use the bank to do its diffentes operations, and the executorservice is created
 */
public class Dispatcher {

    //private List<Agent> agents = new ArrayList<>();
    private List<Cashier> cashiers = new ArrayList<>();
    private List<Supervisor> supervisors = new ArrayList<>();
    private Director directors;
    ExecutorService executor = Executors.newFixedThreadPool(10);

    public Dispatcher() {
        createagents();
    }


    /**
     * This method make possible assign threads, when we ask if there are cashiers, supervisor, and director availables
     * @param clients
     */
    public void attend(List<Client> clients) {
        int i = 0;
        while (i < clients.size()) {
            Cashier cashierAvailable = cashiobtainAvailable();
            Supervisor supervisorAvailable = superObtainAvailable();
            Director directorAvailable = direObtainAvailable();
            if (cashierAvailable != null) {
                cashierAvailable.setAssignedClient(clients.get(i));
                cashierAvailable.setAvailability(false);
                CompletableFuture
                        .supplyAsync(cashierAvailable, executor)
                        .thenAccept(response -> {
                            System.out.println(response + " Atention Time: " + cashierAvailable.getTimeAtention());
                        });
                i++;

            } else if (supervisorAvailable != null) {
                supervisorAvailable.setAssignedClient(clients.get(i));
                supervisorAvailable.setAvailability(false);
                CompletableFuture
                        .supplyAsync(supervisorAvailable, executor)
                        .thenAccept(response -> {
                            System.out.println(response + " Atention Time: " + supervisorAvailable.getTimeAtention());
                        });
                i++;

            } else if (directorAvailable != null) {
                directorAvailable.setAssignedClient(clients.get(i));

                directorAvailable.setAvailability(false);

                CompletableFuture
                        .supplyAsync(directorAvailable, executor)
                        .thenAccept(response -> {
                            System.out.println(response + " Atention Time: " + directorAvailable.getTimeAtention());

                        });
                i++;
            }


        }executor.shutdown();
    }

    /**
     * We create cashiers, supervisor and director, and define the order to assign thread
     */
    private void createagents() {
        for (int i = 1; i <= 6; i++) {
            cashiers.add(new Cashier("Number of cashier "+i, i));
        }

        for (int i = 1; i <= 3; i++) {
            supervisors.add(new Supervisor("Number of supervisor "+i, i));
        }
        directors = (new Director("Director "));


    }

    /**
     * We ask if there are cashiers availables
     * @return
     */
    private Cashier cashiobtainAvailable() {
        for (Cashier cashier : cashiers) {
            if (cashier.isAvailability()) {
                return cashier;
            }
        }
        return null;
    }

    /**
     * We ask if there  are supervisor availables
     * @return
     */

    private Supervisor superObtainAvailable() {
        for (Supervisor supervi : supervisors) {
            if (supervi.isAvailability()) {
                return supervi;
            }
        }
        return null;
    }

    /**
     * We ask if there is the director available
     * @return
     */
    private Director direObtainAvailable() {

        if (directors.isAvailability()) {
            return directors;
        }
        return null;
    }


}
