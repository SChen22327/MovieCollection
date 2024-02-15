import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection {
    private ArrayList<Movie> collection;
    private Scanner scan;
    public MovieCollection() {
        collection = new ArrayList<Movie>();
        scan = new Scanner(System.in);
        createList();
        start();
    }

    private void start() {
        System.out.println("Welcome to the movie collection!");
        String menuOption = "";
        while (!menuOption.equals("q")) {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (c)ast");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scan.nextLine();
            if (menuOption.equals("t")) {
                searchTitles();
            } else if (menuOption.equals("c")) {
                searchCast();
            } else if (menuOption.equals("q")) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private void searchTitles() {
        System.out.print("Enter search term: ");
        String search = scan.nextLine().toLowerCase();
        ArrayList<String> titles = new ArrayList<String>();
        for (Movie movie : collection) {
            String title = movie.getTitle();
            if (title.toLowerCase().contains(search)) {
                titles.add(title);
            }
        }
        if (titles.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }
        insertionSort(titles);
        for (int i = 0; i < titles.size(); i++) {
            System.out.println((i + 1) + ". " + titles.get(i));
        }
        System.out.println("Which movie would you like to learn about?");
        System.out.print("Enter number: ");
        int num = scan.nextInt();
        scan.nextLine();
        System.out.println();
    }
    private void searchCast() {

    }
    private void insertionSort(ArrayList<String> movies) {
        for (int i = 1; i < movies.size(); i++) {
            String current = movies.get(i);
            int j = i;
            while (j > 0 && current.compareTo(movies.get(j - 1)) < 0) {
                movies.set(j, movies.get(j - 1));
                j--;
            }
            movies.set(j, current);
        }
    }
    private void createList() {
        File movies = new File("src\\movies_data.csv");
        try {
            Scanner data = new Scanner(movies);
            data.nextLine();
            while (data.hasNext()) {
                String line = data.nextLine();
                String[] split = line.split(",");
                collection.add(new Movie(split[0], split[1], split[2], split[3], Integer.parseInt(split[4]), Double.parseDouble(split[5])));
            }

        } catch (IOException e) {
            e.getMessage();
        }
    }
}
