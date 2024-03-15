import java.util.*;

/**
 * This class allows a user to interact with a repository
 */
public class Client {
    private static List<String> ops = new ArrayList<>();

    public static void main(String[] args) {
        Collections.addAll(ops, "create", "head", "history", "commit", "contains",
                "drop", "quit");
        Scanner console = new Scanner(System.in);
        Map<String, Repository> repos = new HashMap<>();
        String op = "";
        String name = "";

        intro();

        while (!op.equalsIgnoreCase("quit")) {
            System.out.println("Available repositories: ");
            for (Repository repo : repos.values()) {
                System.out.println("\t" + repo);
            }
            System.out.println("Operations: " + ops);
            System.out.print("Enter operation and repository: ");
            String[] input = console.nextLine().split("\\s+");
            op = input[0];
            name = input.length > 1 ? input[1] : "";
            while (!ops.contains(op) || (!op.equalsIgnoreCase("create") &&
                    !op.equalsIgnoreCase("quit") &&
                    !repos.containsKey(name))) {
                System.out.println("  **ERROR**: Operation or repository not recognized.");
                System.out.print("Enter operation and repository: ");
                input = console.nextLine().split("\\s+");
                op = input[0];
                name = input.length > 1 ? input[1] : "";
            }

            Repository currRepo = repos.get(name);
            op = op.toLowerCase();
            if (op.equalsIgnoreCase("create")) {
                if (currRepo != null) {
                    System.out.println("  **ERROR**: Repository with that name already exists.");
                } else {
                    Repository newRepo = new Repository(name);
                    repos.put(name, newRepo);
                    System.out.println("  New repository created: " + newRepo);
                }
            } else if (op.equalsIgnoreCase("head")) {
                System.out.println(currRepo.getRepoHead());
            } else if (op.equalsIgnoreCase("history")) {
                System.out.print("How many commits back? ");
                int nHist = console.nextInt();
                console.nextLine();
                System.out.println(currRepo.getHistory(nHist));
            } else if (op.equalsIgnoreCase("contains")) {
                System.out.print("Which id do you want to check? ");
                int id = console.nextInt();
                console.nextLine();
                if (currRepo.contains(id))
                    System.out.println("\tId:" + id + " is in " + currRepo.getName());
                else
                    System.out.println("\tId:" + id + " is not in " + currRepo.getName());
            }
            else if (op.equalsIgnoreCase("commit")) {
                System.out.print("Enter commit message: ");
                String message = console.nextLine();
                System.out.println("  New commit: " + currRepo.commit(message));
            } else if (op.equalsIgnoreCase("drop")) {
                System.out.print("Enter ID to drop: ");
                String idDrop = console.nextLine();
                if (currRepo.drop(Integer.parseInt(idDrop))) {
                    System.out.println("  Successfully dropped " + idDrop);
                } else {
                    System.out.println("  No commit dropped!");
                }
            }
            System.out.println();
        }
    }

    // Prints out an introduction to the Mini-Git test client.
    public static void intro() {
        System.out.println("Welcome to the Mini-Git client program");
        System.out.println("Use this program to test your Mini-Git repository methods.");
        System.out.println();
    }
}
