import java.io.*;
import java.util.*;

public class InventoryManagement2 {
    public static Scanner scanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }

    private static final String FILE_NAME = "inventory.txt";
    private final HashMap<String, Item> inventory;

    public InventoryManagement2() {
        inventory = new HashMap<>();
        // loadInventoryFromFile();
    }

    public static void main(String[] args) {
        InventoryManagement2 inventory = new InventoryManagement2();

        boolean b = true;
        String choice;
        do {
            System.out.println("\nInventory Management System");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Item");
            System.out.println("4. Display Inventory");
            System.out.println("5. Save Inventory");
            System.out.println("6. Existing Inventory File load");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner().nextLine();

            switch (choice) {
                case "1":
                    inventory.addItem();
                    break;
                case "2":
                    inventory.removeItem();
                    break;
                case "3":
                    inventory.updateItem();
                    break;
                case "4":
                    inventory.displayInventory();
                    break;
                case "5":
                    inventory.writeInventoryToFile();
                    System.out.println("Inventory saved successfully!");
                    break;
                case "6":
                    inventory.loadInventoryFromFile2();
                    // System.out.println("Existing Inventory File loaded successfully!");
                    break;
                case "0":
                    System.out.println("Exiting program...");
                    // System.exit(0);
                    b= false;
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }while (b);
    }

    private void writeInventoryToFile() {
        Item i = new Item();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Item item : inventory.values()) {
                // Access items using values()
                bw.write(item.getName() + " : " + item.getQuantity() + "\n");
            }
            // String s = String.format(i.getName() + " : "+ i.getQuantity());
            // bw.write(s);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error saving inventory: " + e.getMessage());
        }
    }

    private void addItem() {
        System.out.print("Enter item name: ");
        String name = scanner().nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner().nextInt();

        // Check if item already exists using key (name) in inventory file.
        if (inventory.containsKey(name)) {
            System.out.println("Item with name '" + name + "' already exists. Update quantity instead?");
            return;
        }

        inventory.put(name, new Item(name, quantity));
        System.out.println("Item added successfully!");
    }

    private void removeItem() {
        System.out.print("Enter item name to remove: ");
        String name = scanner().nextLine();

        // Remove item using key (name) to file.
        Item itemToRemove = inventory.remove(name);
        if (itemToRemove != null) {
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Item not found!");
        }
    }

    private void updateItem() {
        System.out.print("Enter item name to update: ");
        String name = scanner().nextLine();

        // Get item using key (name)
        Item itemToUpdate = inventory.get(name);
        if (itemToUpdate != null) {
            System.out.print("Enter new quantity: ");
            int quantity = scanner().nextInt();
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
            for (Item item : inventory.values()) {
                System.out.println("Name : " + item.getName() + ", Quantity : " + item.getQuantity());
            }
        }
    }

    private void loadInventoryFromFile2() {

        System.out.println();
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("File is not Exist!");
                return;
            } else if (file.length() == 0) {
                System.out.println("File is empty!");
                return;
            } else {
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
            System.out.println();
            System.out.println("Existing Inventory File loaded successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}