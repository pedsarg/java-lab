package application;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DatabaseService databaseService = new DatabaseService();

        System.out.println("\nChoice an option:");
        System.out.println("  1 - Read data");
        System.out.println("  2 - Insert data");
        System.out.println("  3 - Update data");
        System.out.println("  4 - Delete data");
        System.out.println("  5 - Transactional update");
        System.out.println("  6 - Insert data with generated keys");
        System.out.println("  0 - Exit");

        System.out.print(" Option: ");
        int option = sc.nextInt();

        switch (option) {
            case 0:
                System.out.println("Exiting...");
                break;
            case 1:
                databaseService.ReadData();
                break;
            case 2:
                databaseService.InsertData();
                break;
            case 3:
                databaseService.UpdateData();
                break;
            case 4:
                databaseService.DeleteData();
                break;
            case 5:
                databaseService.TransactionalUpdate();
                break;
            case 6:
                databaseService.InsertDataWithGeneratedKeys();
                break;
            default:
                System.out.println("Invalid option!");
        }
        sc.close();
    }
}
