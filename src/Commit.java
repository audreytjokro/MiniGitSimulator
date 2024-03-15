import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This class represents a repository commit.  Each commit is assigned a unique integer
 * id number, a timestamp, a commit message, and a link to a past commit if applicable.
 */
public class Commit {
    private static int nextId = 0;
    public long timeStamp;
    public int id;
    public String message;
    public Commit past;

    public Commit(String message, Commit past){
        this.id = getNextId();
        this.message = message;
        this.timeStamp = System.currentTimeMillis();
        this.past = past;

    }

    public String getTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        return formatter.format(new Date(timeStamp));
    }

    private static int getNextId(){
        return nextId++;
    }

    @Override
    public String toString(){
        return "" + id + " at " + getTimeStamp() + ": " + message;
    }

}
