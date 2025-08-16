package app;

public class Demo {
    public static void main(String[] args) {
        try (Database db = Database.getInstance()) {
            DatabaseQueryService service = new DatabaseQueryService();

            System.out.println("=== Project Prices ===");
            service.printProjectPrices().forEach(System.out::println);

            System.out.println("\n=== Youngest & Eldest Workers ===");
            service.findYoungestEldestWorkers().forEach(System.out::println);

            System.out.println("\n=== Max Salary Worker ===");
            service.findMaxSalaryWorker().forEach(System.out::println);

            System.out.println("\n=== Max Projects Client ===");
            service.findMaxProjectsClient().forEach(System.out::println);

            System.out.println("\n=== Longest Project ===");
            service.findLongestProject().forEach(System.out::println);

        }
    }
}
