/*
* Author: Sagar Sikchi
*/

import java.util.*;

class Grocery {
    private Map<String, ArrayList<String>> list = new HashMap<String, ArrayList<String>>();

    String lowercaseName(String name) {
        return name.toLowerCase();
    }

    void printList() {
        System.out.println("\nGROCERY LIST is:");
        System.out.printf("%-15s", "Product Name");
        System.out.printf("%-15s", "Quantity");
        System.out.printf("%-15s", "Price");
        System.out.println();

        for(String key : list.keySet()) {
            System.out.printf("%-15s", key);
            System.out.printf("%-15s", list.get(key).get(0));
            System.out.printf("%-15s", list.get(key).get(1));
            System.out.println();
        }
        System.out.println("\n");
    }

    void leftBudgetBuy(int leftBudget) {
        String string = "\nAmount left can buy you ";
        for(String key : list.keySet()) {
            if(Integer.parseInt(list.get(key).get(1)) == leftBudget) {
                System.out.println(string + key);
                break;
            }
        }
    }

    int validate(int originalBudget) {
        int usedBudget = 0;
        for(String key : list.keySet()) {
            usedBudget += Integer.parseInt(list.get(key).get(1));
        }
        return originalBudget - usedBudget;
    }

    void addProduct(String product, String quantity, String price) {
        product = lowercaseName(product);
        if(list.containsKey(product)) {
            list.get(product).set(0, quantity);
            list.get(product).set(1, price);
        } else {
            ArrayList<String> details = new ArrayList<>(Arrays.asList(quantity, price));
            list.put(product, details);
        }
    }

}
public class Main {
    public static void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your budget: ");
        int budget = scanner.nextInt();
        int choice, originalBudget = budget;
        String product, quantity, price;
        Grocery grocery = new Grocery();

        while(true) {
            System.err.print("\n1. Add an item\n2. Exit\nEnter your choice: ");
            choice = scanner.nextInt();
            switch(choice) {
                case 1:
                System.out.print("\nEnter product: ");
                product = scanner.next();
                scanner.nextLine();
                System.out.print("Enter quantity: ");
                quantity = scanner.next();
                scanner.nextLine();
                System.out.print("Enter price: ");
                price = scanner.next();
                scanner.nextLine();
                if(Integer.parseInt(price) > budget) {
                    System.out.println("\nCan't Buy the product ###(because budget left is " + budget + ")");
                } else {
                    grocery.addProduct(product, quantity, price);
                    budget = grocery.validate(originalBudget);
                    System.out.print("\nAmount left: " + budget + "\n");
                }
                break;
                case 2:
                scanner.close();
                // budget = grocery.validate(originalBudget);
                grocery.leftBudgetBuy(budget);
                grocery.printList();
                return;
                default:
                System.out.println("\nInvalid Choice!");
                break;
            }
        }
    }
    public static void main(String[] args) {
        execute();
    }
}
