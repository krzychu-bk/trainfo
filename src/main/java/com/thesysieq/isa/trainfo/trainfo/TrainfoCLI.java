package com.thesysieq.isa.trainfo.trainfo;

import com.thesysieq.isa.trainfo.trainfo.data.entity.CategoryEntity;
import com.thesysieq.isa.trainfo.trainfo.data.entity.StopEntity;
import com.thesysieq.isa.trainfo.trainfo.data.entity.TrainEntity;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.CategoryService;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.StopService;
import com.thesysieq.isa.trainfo.trainfo.remote.rest.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TrainfoCLI implements CommandLineRunner {
    private final CategoryService categoryService;
    private final TrainService trainService;
    private final StopService stopService;

    @Autowired
    public TrainfoCLI(final CategoryService categoryService, final TrainService trainService, final StopService stopService) {
        this.categoryService = categoryService;
        this.trainService = trainService;
        this.stopService = stopService;
    }

    @Override
    public void run(final String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("[INFO] List of commands - type 'help'");
            System.out.println("[PROMPT] Enter command: ");
            String command = "";
            while(command.isEmpty()){
                command = scanner.nextLine();
            }
            switch (command) {
                case "help":
                    System.out.println("[INFO] List of commands:");
                    System.out.println("  help - shows this help message");
                    System.out.println("  list_categories - lists all train categories");
                    System.out.println("  list_trains - lists all trains");
                    System.out.println("  list_stops - lists all stops");
                    System.out.println("  add_stop - starts addition prompt sequence");
                    System.out.println("  remove_stop - removes prompted element");
                    System.out.println("  :wq - exits the CLI");
                    break;
                case "list_categories":
                    categoryService.findAll().forEach(System.out::println);
                    break;
                case "list_trains":
                    trainService.findAll().forEach(System.out::println);
                    break;
                case "list_stops":
                    stopService.findAll().forEach(System.out::println);
                    break;
                case "add_stop":
                    this.addStop(scanner);
                    break;
                case "delete_stop":
                    this.deleteStop(scanner);
                    break;
                case ":wq":
                    System.out.println("[INFO] Shutting down...");
                    running = false;
                    break;
                default:
                    System.out.println("[ERROR] Invalid command, type 'help' to see available commands.");
            }
        }
    }

    private void addStop(Scanner scanner) {
        // Step 1: List all categories and allow the user to select one
        List<CategoryEntity> categories = categoryService.findAll();
        if (categories.isEmpty()) {
            System.out.println("[ERROR] No categories found. Please add categories first.");
            return;
        }

        System.out.println("\n[INFO] Available categories:");
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, categories.get(i).getBusinessName());
        }
        System.out.print("[ERROR] Select a category (enter number): ");
        int categoryIndex = scanner.nextInt()-1;

        if (categoryIndex < 0 || categoryIndex >= categories.size()) {
            System.out.println("[ERROR] Invalid category selection. Operation cancelled");
            return;
        }

        CategoryEntity selectedCategory = categories.get(categoryIndex);

        // Step 2: List all trains in the selected category and allow the user to select one
        List<TrainEntity> trains = trainService.findAll().stream()
                .filter(train -> train.getCategory().getCategoryId().equals(selectedCategory.getCategoryId()))
                .toList();

        if (trains.isEmpty()) {
            System.out.println("[ERROR] No trains found in this category. Please add trains first. Operation cancelled.");
            return;
        }

        System.out.println("\n[INFO] Available trains:");
        for (int i = 0; i < trains.size(); i++) {
            System.out.println(trains.get(i).getCategory().getCategoryType().toString()+" "+trains.get(i).getTrainNumber());
        }
        System.out.print("[PROMPT] Select a train (enter the train number): ");
        int trainNumber = scanner.nextInt();

        if (trains.stream().noneMatch(train -> train.getTrainNumber().equals(trainNumber))) {
            System.out.println("[ERROR] Invalid train selection. Operation cancelled.");
            return;
        }

        TrainEntity selectedTrain = trains.stream().filter(train -> train.getTrainNumber().equals(trainNumber)).findFirst().get();

        // Step 3: Collect details for the new timetable entry
        String stationName = "";
        while(stationName.isEmpty()) {
            System.out.println("[PROMPT] Enter station name: ");
            stationName = scanner.nextLine();
        }

        System.out.print("[PROMPT] Enter arrival time (HH:mm): ");
        String arrivalTime = scanner.nextLine();
        Time arrival = Time.valueOf(arrivalTime + ":00");

        System.out.print("[PROMPT] Enter departure time (HH:mm): ");
        String departureTime = scanner.nextLine().trim();
        Time departure = Time.valueOf(departureTime + ":00");

        System.out.print("[PROMPT] Enter distance from origin (km): ");
        Float distance = Float.parseFloat(scanner.nextLine().trim());

        // Step 4: Save the new timetable entry
        StopEntity newEntry = StopEntity.builder()
                .stopID(UUID.randomUUID())
                .train(selectedTrain)
                .station(stationName)
                .arrival(arrival)
                .departure(departure)
                .distanceFromOriginKm(distance)
                .build();
        stopService.save(newEntry);

        System.out.println("[INFO] Timetable entry added successfully!");
    }

    private void deleteStop(Scanner scanner) {
        System.out.println("\n[INFO] Available stops:");
        List<StopEntity> stops = stopService.findAll();
        if (stops.isEmpty()) {
            System.out.println("[ERROR] No stops found. No need to delete. Operation cancelled.");
            return;
        }
        stops.forEach(stop -> System.out.println(" "+(stops.indexOf(stop)+1)+". "+stop));
        System.out.print("[PROMPT] Select a stop (enter its assigned number): ");
        int indexToDelete = scanner.nextInt()-1;
        if(indexToDelete > stops.size()) {
            System.out.println("[ERROR] Invalid stop selection. Operation cancelled.");
            return;
        }
        stopService.delete(stops.get(indexToDelete));
        System.out.println("[INFO] Stop deleted successfully!");
    }
}
