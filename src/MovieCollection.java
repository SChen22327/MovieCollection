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
        ArrayList<Movie> titles = new ArrayList<Movie>();
        for (Movie movie : collection) {
            String title = movie.getTitle();
            if (title.toLowerCase().contains(search)) {
                titles.add(movie);
            }
        }
        if (titles.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }
        sortMovies(titles);
        for (int i = 0; i < titles.size(); i++) {
            System.out.println((i + 1) + ". " + titles.get(i).getTitle());
        }
        System.out.println("Which movie would you like to learn about?");
        System.out.print("Enter number: ");
        int num = scan.nextInt();
        scan.nextLine();
        System.out.println("\n" + titles.get(num - 1));
    }
    private void searchTitles(String actor) {
        ArrayList<Movie> titles = new ArrayList<Movie>();
        for (Movie movie : collection) {
            if (movie.getCast().contains(actor)) {
                titles.add(movie);
            }
        }
        sortMovies(titles);
        for (int i = 0; i < titles.size(); i++) {
            System.out.println((i + 1) + ". " + titles.get(i).getTitle());
        }
        System.out.println("Which movie would you like to learn about?");
        System.out.print("Enter number: ");
        int num = scan.nextInt();
        scan.nextLine();
        System.out.println("\n" + titles.get(num - 1));
    }
    private void searchCast() {
        System.out.print("Enter search term: ");
        String search = scan.nextLine().toLowerCase();
        ArrayList<String> actors = new ArrayList<String>();
        for (Movie movie : collection) {
            String[] cast = movie.getCast().split("\\|");
            for (String actor : cast) {
                if (actor.toLowerCase().contains(search) && !actors.contains(actor)) {
                    actors.add(actor);
                }
            }
        }
        if (actors.isEmpty()) {
            System.out.println("No matches found.");
            return;
        }
        sortActors(actors);
        for (int i = 0; i < actors.size(); i++) {
            System.out.println((i + 1) + ". " + actors.get(i));
        }
        System.out.println("Which would you like to see all movies for?");
        System.out.print("Enter number: ");
        int num = scan.nextInt();
        scan.nextLine();
        String actor = actors.get(num - 1);
        searchTitles(actor);
    }
    private void sortMovies(ArrayList<Movie> movies) {
        for (int i = 1; i < movies.size(); i++) {
            Movie current = movies.get(i);
            int j = i;
            while (j > 0 && current.getTitle().compareTo(movies.get(j - 1).getTitle()) < 0) {
                movies.set(j, movies.get(j - 1));
                j--;
            }
            movies.set(j, current);
        }
    }
    private void sortActors(ArrayList<String> actors) {
        for (int i = 1; i < actors.size(); i++) {
            String current = actors.get(i);
            int j = i;
            while (j > 0 && current.compareTo(actors.get(j - 1)) < 0) {
                actors.set(j, actors.get(j - 1));
                j--;
            }
            actors.set(j, current);
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
