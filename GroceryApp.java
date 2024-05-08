package grocery;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    int userID;
    String name;
    String email;
    String password;
    String role;

    public User(int userID, String name, String email, String password, String role) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}

class GroceryItem {
    int groceryId;
    String groceryName;
    String quantity;
    String price;

    public GroceryItem(int groceryId, String groceryName, String quantity, String price) {
        this.groceryId = groceryId;
        this.groceryName = groceryName;
        this.quantity = quantity;
        this.price = price;
    }
}

class CartItem {
    int cartId;
    int userId;
    int groceryId;
    int quantity;
    String bookedAt;

    public CartItem(int cartId, int userId, int groceryId, int quantity, String bookedAt) {
        this.cartId = cartId;
        this.userId = userId;
        this.groceryId = groceryId;
        this.quantity = quantity;
        this.bookedAt = bookedAt;
    }
}

class Payment {
    int paymentId;
    String paymentMethod;
    String cardNumber;
    String month;
    String year;
    String cvv;
    int amount;
    String paymentStatus;

    public Payment(int paymentId, String paymentMethod, String cardNumber, String month, String year, String cvv, int amount, String paymentStatus) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.month = month;
        this.year = year;
        this.cvv = cvv;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }
}

class GroceryApp {
    List<User> users;
    List<GroceryItem> groceryItems;
    List<CartItem> cartItems;
    List<Payment> payments;
    String address;

    public GroceryApp() {
        this.users = new ArrayList<>();
        this.groceryItems = new ArrayList<>();
        this.cartItems = new ArrayList<>();
        this.payments = new ArrayList<>();
        this.address = address;
    }

    public User validateLogin(String email, String password, List<User> users) {
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addGroceryItem(GroceryItem groceryItem) {
        groceryItems.add(groceryItem);
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public User groceryForCustomer(User currentUser, List<GroceryItem> groceryListItems, List<CartItem> cartItems,List<Payment> payments) {
        boolean stayInCustomerMenu = true;
        Scanner scanner = new Scanner(System.in);

        while (stayInCustomerMenu) {
            System.out.println("1. View Grocery List");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Make Payment");
            System.out.println("5. Exit");

            int choice = scanner.nextInt(); // Get the choice from user input

            switch (choice) {
                case 1:
                    System.out.println("Grocery List:");
                    for (GroceryItem grocery : groceryListItems) {
                        System.out.println("Grocery ID: " + grocery.groceryId);
                        System.out.println("Grocery Name: " + grocery.groceryName);
                        System.out.println("Quantity: " + grocery.quantity);
                        System.out.println("Price: " + grocery.price);
                        System.out.println("-------------------------");
                    }
                    break;

                case 2:
                    System.out.println("Enter Grocery ID:");
                    int groceryId = scanner.nextInt();
                    System.out.println("Enter Quantity:");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline character
                    System.out.println("Enter Booked At:");
                    String bookedAt = scanner.nextLine();

                    CartItem cartItem = new CartItem(cartItems.size() + 1, currentUser.userID, groceryId, quantity, bookedAt);
                    addCartItem(cartItem);
                    System.out.println("Item added to cart successfully!");
                    break;

                case 3:
                    System.out.println("Cart Items:");
                    for (CartItem cart : cartItems) {
                        if (cart.userId == currentUser.userID) {
                            System.out.println("Cart ID: " + cart.cartId);
                            System.out.println("Grocery ID: " + cart.groceryId);
                            System.out.println("Quantity: " + cart.quantity);
                            System.out.println("Booked At: " + cart.bookedAt);
                            System.out.println("-------------------------");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Enter Payment Method:");
                    String paymentMethod = scanner.nextLine();
                    System.out.println("Enter Card Number:");
                    String cardNumber = scanner.nextLine();
                    System.out.println("Enter Month (MM):");
                    String month = scanner.nextLine();
                    System.out.println("Enter Year (YYYY):");
                    String year = scanner.nextLine();
                    System.out.println("Enter CVV:");
                    String cvv = scanner.nextLine();
                    System.out.println("Enter Amount:");
                    int amount = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline character
                    System.out.println("Enter Payment Status:");
                    String paymentStatus = scanner.nextLine();

                    Payment payment = new Payment(payments.size() + 1, paymentMethod, cardNumber, month, year, cvv, amount, paymentStatus);
                    addPayment(payment);
                    System.out.println("Payment made successfully!");
                    break;

                case 5:
                    stayInCustomerMenu = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        return currentUser;
    }

    public User groceryForAdmin(User currentUser, List<GroceryItem> groceryListItems) {
        boolean stayInAdminMenu = true;
        Scanner scanner = new Scanner(System.in);

        while (stayInAdminMenu) {
            System.out.println("1. View Grocery List");
            System.out.println("2. Add Grocery Item");
            System.out.println("3. Exit");

            int choice = scanner.nextInt(); // Get the choice from user input

            switch (choice) {
                case 1:
                    System.out.println("Grocery List:");
                    for (GroceryItem grocery : groceryListItems) {
                        System.out.println("Grocery ID: " + grocery.groceryId);
                        System.out.println("Grocery Name: " + grocery.groceryName);
                        System.out.println("Quantity: " + grocery.quantity);
                        System.out.println("Price: " + grocery.price);
                        System.out.println("-------------------------");
                    }
                    break;

                case 2:
                    System.out.println("Enter Grocery ID:");
                    int groceryId = scanner.nextInt();
                    scanner.nextLine(); // Clear the newline character
                    System.out.println("Enter Grocery Name:");
                    String groceryName = scanner.nextLine();
                    System.out.println("Enter Quantity:");
                    String quantity = scanner.nextLine();
                    System.out.println("Enter Price:");
                    String price = scanner.nextLine();

                    GroceryItem groceryItem = new GroceryItem(groceryId, groceryName, quantity, price);
                    addGroceryItem(groceryItem);
                    System.out.println("Grocery item added successfully!");
                    break;

                case 3:
                    stayInAdminMenu = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        return currentUser;
    }

    public static void main(String[] args) {
        GroceryApp portal = new GroceryApp();

        // Adding sample data
        User user1 = new User(1, "John", "john@example.com", "password123", "customer");
        User user2 = new User(2, "Admin", "admin@example.com", "admin123", "admin");
        portal.addUser(user1);
        portal.addUser(user2);

        GroceryItem item1 = new GroceryItem(1, "Apple", "5", "2.99");
        GroceryItem item2 = new GroceryItem(2, "Banana", "10", "1.99");
        portal.addGroceryItem(item1);
        portal.addGroceryItem(item2);

        // Simulating login for a customer
        User loggedInUser = portal.validateLogin("john@example.com", "password123", portal.users);
        if (loggedInUser != null && loggedInUser.role.equals("customer")) {
            portal.groceryForCustomer(loggedInUser, portal.groceryItems, portal.cartItems, portal.payments);
        }

        // Simulating login for an admin
        loggedInUser = portal.validateLogin("admin@example.com", "admin123", portal.users);
        if (loggedInUser != null && loggedInUser.role.equals("admin")) {
            portal.groceryForAdmin(loggedInUser, portal.groceryItems);
        }
    }
}

