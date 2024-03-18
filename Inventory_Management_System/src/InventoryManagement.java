import java.io.*;
import java.util.*;

public class InventoryManagement {
    private static final String FILE_NAME = "inventory.txt";

    private ArrayList<Item> inventory;

    public InventoryManagement() {
        inventory = new ArrayList<>();
        // loadInventoryFromFile();
    }

    public static void main(String[] args) {
        InventoryManagement inventory = new InventoryManagement();
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Item");
            System.out.println("4. Display Inventory");
            System.out.println("5. Save Inventory");
            System.out.println("6. Load Already Existing Data In The File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    inventory.addItem();
                    break;
                case 2:
                    inventory.removeItem();
                    break;
                case 3:
                    inventory.updateItem();
                    break;
                case 4:
                    inventory.displayInventory();
                    break;
                case 5:
                    inventory.writeInventoryToFile();
                    System.out.println("Inventory saved successfully!");
                    break;
                case 6:
                    inventory.loadInventoryFromFile2();
                    System.out.println("Inventory Load successfully! From a File");
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private void writeInventoryToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Item item : inventory) {
                bw.write(item.getName() + " : " + item.getQuantity() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        inventory.add(new Item(name, quantity));
        System.out.println("Item added successfully!");
    }

    private void removeItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name to remove: ");
        String name = scanner.nextLine();
        Item itemToRemove = null;
        for (Item item : inventory) {
            if (item.getName().equals(name)) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            inventory.remove(itemToRemove);
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Item not found!");
        }
    }

    private void updateItem() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter item name to update: ");
        String name = scanner.nextLine();
        Item itemToUpdate = null;
        for (Item item : inventory) {
            if (item.getName().equals(name)) {
                itemToUpdate = item;
                break;
            }
        }
        if (itemToUpdate != null) {
            System.out.print("Enter new quantity: ");
            int quantity = scanner.nextInt();
            itemToUpdate.setQuantity(quantity);
            System.out.println("Item updated successfully!");
        } else {
            System.out.println("Item not found!");
        }
    }

    private void displayInventory() {
		System.out.println();
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty!");
        } else {
            System.out.println("------- Inventory -------");
            for (Item item : inventory) {
                System.out.println("Name : " + item.getName() + ", Quantity : " + item.getQuantity());
            }
        }
    }

    // private void loadInventoryFromFile() {
    //     try {
    //         File file = new File(FILE_NAME);
    //         if (!file.exists() || file.length()== 0) {
    //             return;
    //         }
    //         Scanner scanner = new Scanner(file);
    //         while (scanner.hasNextLine()) {
    //             String line = scanner.nextLine();
    //             String[] parts = line.split(" -->");
    //             String name = parts[0];
    //             int quantity = Integer.parseInt(parts[1]);
    //             inventory.add(new Item(name, quantity));
    //         }
    //         scanner.close();

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    private void loadInventoryFromFile2() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                return;
            } 
            else if(file.length() == 0){
                System.out.println("File is empty!");
                return;
            }else {
                FileReader fileReader = new FileReader(FILE_NAME);
                try {
                    int i;
                    // System.out.print("Name : ");
                    while ((i = fileReader.read()) != -1) {
                        System.out.print((char) i);
                    }
                } finally {
                    fileReader.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
