//Audrey Tjokro

/**
 * This class will imitate a version control system similar to Git where we are implementing multiple operations
 * which a repository also has such as [create, head, history, commit, contains, drop, quit]
 *
 * TODO: Finish this class.
 */
public class Repository {

    private String name; // name of the repository
    private Commit head; // equivalent to front node

    // You may add more fields
    private int size; // number of nodes in the list

    //TODO Javadoc and finish

    /**
     * This constructor creates a new empty repository with the specified name.
     * If the name is null or empty, you should throw an IllegalArgumentException
     * @param name
     */
    public Repository(String name){
        if (name == null || name.equalsIgnoreCase("")){
            throw new IllegalArgumentException("Name is null or empty");
        }

        this.name = name;
        this.head = null;
        this.size=0;

    }

    // TODO Javadoc and finish

    /**
     * This method returns the name of the repository
     * @return
     */
    public String getName(){
        return name;
    }

    // TODO Javadoc and finish

    /**
     * This method returns the toString representation for the current head of this repository.
     * @return
     */
    public String getRepoHead(){
        if (head == null) {
            return "";
        }

        return head.message;
    }

    // TODO Javadoc and finish

    /**
     * This method returns the number of commits in the repository.
     * @return
     */
    public int getRepoSize(){

        return size;
    }

    // TODO Javadoc and finish

    /**
     * This returns true if the commit with the target id is in the repository, false otherwise
     * @param targetID
     * @return
     */
    public boolean contains(int targetID){
        Commit current = head;

        while (current!= null){
            if (current.id == targetID){
                return true;
            }
            current = current.past; // go through the entire previous commit and check
        }

        return false;
    }

    // TODO JavaDoc and finish

    /**
     * Create a new commit with the given message, add it to this repository.
     * The new commit should become the new head of this repository, preserving the history behind it.
     * Return the toString representation of the new head.
     * @param message
     * @return
     */
    public String commit(String message){
        Commit newCommit = new Commit(message, head);
        head = newCommit; // new commit becomes the old head
        size++;

        return head.toString();
    }

    // TODO JavaDoc and finish

    /**
     * Remove the commit with ID targetID from this repository, maintaining the rest of the history.
     * Returns true if the commit was successfully dropped
     * Returns false if there is no commit that matches the given ID in the repository.
     * @param targetID
     * @return
     */
    public boolean drop(int targetID) {
        if (head == null){
            return false;
        }

        if (head.id == targetID) {
            head = head.past;
            size--;
            return true;
        }

        Commit current = head;
        while (current.past != null){
            if (current.past.id == targetID){ // if the id is found, update the current.past to the id one more before to "remove"
                current.past = current.past.past;
                size--;
                return true;
            }
            current = current.past;
        }
        return false;
    }


    // TODO Javadoc and finish

    /**
     * Return a string consisting of the String representations of the most recent n commits in this character.
     * @param n
     * @return
     */
    public String getHistory(int n){
        //4 at 2023-10-25 at 06:55:05 PST: one more commit

        String result = "";
        Commit current = head;
        while (current != null && n > 0){
            result = result + current + "\n";
            current = current.past;
            n--;
        }
        return result;
    }

    // TODO Javadoc and finish

    /**
     * Return a string representation of this repository in the following format:
     *
     * <name> - Current head: <head>
     *
     * where <head>  is the String returned by a call to toString() on the head commit.
     *
     * If there are no commits in this repository, instead return <name> - No commits
     * @return
     */
    @Override
    public String toString(){
        if (size == 0){
            return name + " - No commits";
        }
        return name + " - Current head: " + head.toString();
    }
}

/*
Program output

/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/bin/java -javaagent:/private/var/folders/vm/lr4jy00d50z5kvg7m2g2dl6c0000gp/T/AppTranslocation/56A79D3C-58EE-4C37-B63D-9C7C8D751B74/d/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=64330:/private/var/folders/vm/lr4jy00d50z5kvg7m2g2dl6c0000gp/T/AppTranslocation/56A79D3C-58EE-4C37-B63D-9C7C8D751B74/d/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /Users/audreytjokro/Desktop/Java2IntelliJ/IndividualAssignment4/out/production/IndividualAssignment4 Client
Welcome to the Mini-Git client program
Use this program to test your Mini-Git repository methods.

Available repositories:
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: create repo1
  New repository created: repo1 - No commits

Available repositories:
	repo1 - No commits
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: commit repo1
Enter commit message: First commit!
  New commit: 0 at 2023-11-29 14:49:49 WIB: First commit!

Available repositories:
	repo1 - Current head: 0 at 2023-11-29 14:49:49 WIB: First commit!
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: commit repo1
Enter commit message: Another commit.
  New commit: 1 at 2023-11-29 14:50:00 WIB: Another commit.

Available repositories:
	repo1 - Current head: 1 at 2023-11-29 14:50:00 WIB: Another commit.
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: history repo1
How many commits back? 2
1 at 2023-11-29 14:50:00 WIB: Another commit.
0 at 2023-11-29 14:49:49 WIB: First commit!


Available repositories:
	repo1 - Current head: 1 at 2023-11-29 14:50:00 WIB: Another commit.
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: create repo2
  New repository created: repo2 - No commits

Available repositories:
	repo2 - No commits
	repo1 - Current head: 1 at 2023-11-29 14:50:00 WIB: Another commit.
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: commit repo2
Enter commit message: Commit the third
  New commit: 2 at 2023-11-29 14:50:43 WIB: Commit the third

Available repositories:
	repo2 - Current head: 2 at 2023-11-29 14:50:43 WIB: Commit the third
	repo1 - Current head: 1 at 2023-11-29 14:50:00 WIB: Another commit.
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: commit repo1
Enter commit message: Fourth commit
  New commit: 3 at 2023-11-29 14:50:52 WIB: Fourth commit

Available repositories:
	repo2 - Current head: 2 at 2023-11-29 14:50:43 WIB: Commit the third
	repo1 - Current head: 3 at 2023-11-29 14:50:52 WIB: Fourth commit
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: history repo1
How many commits back? 4
3 at 2023-11-29 14:50:52 WIB: Fourth commit
1 at 2023-11-29 14:50:00 WIB: Another commit.
0 at 2023-11-29 14:49:49 WIB: First commit!


Available repositories:
	repo2 - Current head: 2 at 2023-11-29 14:50:43 WIB: Commit the third
	repo1 - Current head: 3 at 2023-11-29 14:50:52 WIB: Fourth commit
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: head repo1
Fourth commit

Available repositories:
	repo2 - Current head: 2 at 2023-11-29 14:50:43 WIB: Commit the third
	repo1 - Current head: 3 at 2023-11-29 14:50:52 WIB: Fourth commit
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: commit repo1
Enter commit message: one more commit
  New commit: 4 at 2023-11-29 14:53:17 WIB: one more commit

Available repositories:
	repo2 - Current head: 2 at 2023-11-29 14:50:43 WIB: Commit the third
	repo1 - Current head: 4 at 2023-11-29 14:53:17 WIB: one more commit
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: contains repo1
Which id do you want to check? 3
	Id:3 is in repo1

Available repositories:
	repo2 - Current head: 2 at 2023-11-29 14:50:43 WIB: Commit the third
	repo1 - Current head: 4 at 2023-11-29 14:53:17 WIB: one more commit
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: drop repo1
Enter ID to drop: 3
  Successfully dropped 3

Available repositories:
	repo2 - Current head: 2 at 2023-11-29 14:50:43 WIB: Commit the third
	repo1 - Current head: 4 at 2023-11-29 14:53:17 WIB: one more commit
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: history repo1
How many commits back? 3
4 at 2023-11-29 14:53:17 WIB: one more commit
1 at 2023-11-29 14:50:00 WIB: Another commit.
0 at 2023-11-29 14:49:49 WIB: First commit!


Available repositories:
	repo2 - Current head: 2 at 2023-11-29 14:50:43 WIB: Commit the third
	repo1 - Current head: 4 at 2023-11-29 14:53:17 WIB: one more commit
Operations: [create, head, history, commit, contains, drop, quit]
Enter operation and repository: quit


Process finished with exit code 0
 */