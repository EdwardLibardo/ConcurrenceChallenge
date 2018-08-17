import java.util.function.Supplier;

public class Agent implements Supplier<String> {
    protected String name;
    protected boolean availability;
    protected Client assignedClient;
    protected int timeAtention;
    protected int id;

    public Agent(){
        this.availability=true;
    }

    /**
     * In this method the time to sleep is the defined, and call the client with its bank operation.
     * @return
     */
    public String attendClient()
    {
        timeAtention=(int)(Math.random()*5)+10;
        try {
            Thread.sleep(timeAtention*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result=name+" "+assignedClient.getName()+" "+assignedClient.getOperation();
        setAssignedClient(null);
        setAvailability(true);
        return result;
    }

    @Override
    /**
     * The main class in Dispatcher is Agent called,
     */
    public String get() {
        return attendClient();
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability=availability;
    }

    public void setAssignedClient(Client assignedClient) {
        this.assignedClient=assignedClient;
    }
    public int getTimeAtention() {
        return timeAtention;
    }
}
