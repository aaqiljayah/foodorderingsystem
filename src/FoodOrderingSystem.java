import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// FileHandler class to manage file operations
class FileHandler {
    private static final String USER_FILE = "users.txt";
    private static final String ORDER_FILE = "orders.txt";
    private static final String MENU_FILE = "menus.txt";
    private static final String EARNINGS_FILE = "earnings.txt";
    private static final String REVIEWS_FILE = "reviews.txt";

    // Save user data to file
    public static void saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(user.getUserID() + "," + user.getUsername() + "," + user.getRole() + "," + user.getPassword());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    // Load users from file
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String userID = parts[0];
                    String username = parts[1];
                    String role = parts[2];
                    String password = parts[3];

                    switch (role) {
                        case "Admin":
                            users.add(new Admin(username, password, userID));
                            break;
                        case "Vendor":
                            users.add(new Vendor(username, password, userID));
                            break;
                        case "Customer":
                            users.add(new Customer(username, password, userID, 100.0));
                            break;
                        case "DeliveryRunner":
                            users.add(new DeliveryRunner(username, password, userID));
                            break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("User file not found. Initializing empty user database.");
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    // Save order data to file
    public static void saveOrder(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE, true))) {
            writer.write(order.getOrderId() + "," + order.getCustomerName() + "," + order.getVendorID() + "," + order.getStatus() + "," + order.getDeliveryFee() + "," + order.getTotalAmount() + "," + order.getDeliveryRunnerID() + "," + order.getOrderTime());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        }
    }

    // Load orders from file
    public static List<Order> loadOrders() {
        List<Order> orders = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 8) {
                    orders.add(new Order(parts[1], parts[0], parts[2], OrderStatus.valueOf(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), parts[6], Long.parseLong(parts[7])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Order file not found. Initializing empty order database.");
        } catch (IOException e) {
            System.out.println("Error loading orders: " + e.getMessage());
        }
        return orders;
    }

    // Save menu data to file
    public static void saveMenu(Menu menu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE, true))) {
            writer.write(menu.getVendorID() + "," + menu.getItemName() + "," + menu.getPrice());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving menu: " + e.getMessage());
        }
    }

    // Load menus from file
    public static List<Menu> loadMenus() {
        List<Menu> menus = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    menus.add(new Menu(parts[0], parts[1], Double.parseDouble(parts[2])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Menu file not found. Initializing empty menu database.");
        } catch (IOException e) {
            System.out.println("Error loading menus: " + e.getMessage());
        }
        return menus;
    }

    // Save earnings data to file
    public static void saveEarnings(String userID, double earnings) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EARNINGS_FILE, true))) {
            writer.write(userID + "," + earnings);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving earnings: " + e.getMessage());
        }
    }

    // Load earnings from file
    public static Map<String, Double> loadEarnings() {
        Map<String, Double> earningsMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(EARNINGS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    earningsMap.put(parts[0], Double.parseDouble(parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Earnings file not found. Initializing empty earnings database.");
        } catch (IOException e) {
            System.out.println("Error loading earnings: " + e.getMessage());
        }
        return earningsMap;
    }

    // Save review data to file
    public static void saveReview(Review review) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REVIEWS_FILE, true))) {
            writer.write(review.getReviewerID() + "," + review.getRevieweeID() + "," + review.getReviewText() + "," + review.getRating());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving review: " + e.getMessage());
        }
    }

    // Load reviews from file
    public static List<Review> loadReviews() {
        List<Review> reviews = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(REVIEWS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    reviews.add(new Review(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Reviews file not found. Initializing empty reviews database.");
        } catch (IOException e) {
            System.out.println("Error loading reviews: " + e.getMessage());
        }
        return reviews;
    }
}

// Review class to represent reviews
class Review {
    private final String reviewerID;
    private final String revieweeID;
    private final String reviewText;
    private final int rating; // Rating out of 5

    public Review(String reviewerID, String revieweeID, String reviewText, int rating) {
        this.reviewerID = reviewerID;
        this.revieweeID = revieweeID;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public String getReviewerID() { return reviewerID; }
    public String getRevieweeID() { return revieweeID; }
    public String getReviewText() { return reviewText; }
    public int getRating() { return rating; }
}

// Menu class to represent menu items
class Menu {
    private final String vendorID;
    private final String itemName;
    private final double price;

    public Menu(String vendorID, String itemName, double price) {
        this.vendorID = vendorID;
        this.itemName = itemName;
        this.price = price;
    }

    public String getVendorID() { return vendorID; }
    public String getItemName() { return itemName; }
    public double getPrice() { return price; }
}

// Abstract User class
abstract class User {
    protected final String username, password, userID;
    protected final String role;

    public User(String username, String password, String userID, String role) {
        this.username = username;
        this.password = password;
        this.userID = userID;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getUserID() { return userID; }
    public String getRole() { return role; }

    public abstract void displayMenu();
}

// Admin class
class Admin extends User {
    public Admin(String username, String password, String userID) {
        super(username, password, userID, "Admin");
    }

    @Override
    public void displayMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Register Vendor");
        System.out.println("2. Register Customer");
        System.out.println("3. Register Delivery Runner");
        System.out.println("4. Top-up Customer Credit");
        System.out.println("6. Exit");
    }
}

// Customer class
class Customer extends User {
    private double walletBalance;

    public Customer(String username, String password, String userID, double walletBalance) {
        super(username, password, userID, "Customer");
        this.walletBalance = walletBalance;
    }

    public double getWalletBalance() { return walletBalance; }
    public void topUp(double amount) { walletBalance += amount; }
    public boolean deduct(double amount) {
        if (walletBalance >= amount) {
            walletBalance -= amount;
            return true;
        }
        return false;
    }

    // Method to view wallet balance
    public void viewWalletBalance() {
        System.out.println("Your current wallet balance is: $" + walletBalance);
    }

    // Method to write a review for a vendor
    public void writeVendorReview(String vendorID, String reviewText, int rating) {
        Review review = new Review(this.getUserID(), vendorID, reviewText, rating);
        FileHandler.saveReview(review);
        System.out.println("Review submitted successfully!");
    }

    @Override
    public void displayMenu() {
        System.out.println("Customer Menu:");
        System.out.println("1. View Menu");
        System.out.println("2. Place Order");
        System.out.println("3. Check Order Status");
        System.out.println("4. View Order History");
        System.out.println("5. View Wallet Balance");
        System.out.println("6. Write Review for Vendor");
        System.out.println("7. Exit");
    }
}

// Vendor class
class Vendor extends User {
    private double earnings;

    public Vendor(String username, String password, String userID) {
        super(username, password, userID, "Vendor");
        this.earnings = 0.0;
    }

    public double getEarnings() { return earnings; }
    public void addEarnings(double amount) {
        earnings += amount;
        FileHandler.saveEarnings(this.getUserID(), earnings); // Save earnings to file
    }

    // Method to write a review for a customer
    public void writeCustomerReview(String customerID, String reviewText, int rating) {
        Review review = new Review(this.getUserID(), customerID, reviewText, rating);
        FileHandler.saveReview(review);
        System.out.println("Review submitted successfully!");
    }

    @Override
    public void displayMenu() {
        System.out.println("Vendor Menu:");
        System.out.println("1. Add Menu Item");
        System.out.println("2. View Orders");
        System.out.println("3. Update Order Status");
        System.out.println("4. View Earnings");
        System.out.println("5. Write Review for Customer");
        System.out.println("6. Exit");
    }

    public void viewEarnings() {
        System.out.println("Your current earnings are: $" + earnings);
    }
}

// DeliveryRunner class
class DeliveryRunner extends User {
    private double earnings;

    public DeliveryRunner(String username, String password, String userID) {
        super(username, password, userID, "DeliveryRunner");
        this.earnings = 0.0;
    }

    public double getEarnings() { return earnings; }
    public void addEarnings(double amount) {
        earnings += amount;
        FileHandler.saveEarnings(this.getUserID(), earnings); // Save earnings to file
    }

    @Override
    public void displayMenu() {
        System.out.println("Delivery Runner Menu:");
        System.out.println("1. View Tasks");
        System.out.println("2. Accept Task");
        System.out.println("3. Update Task Status");
        System.out.println("4. View Earnings");
        System.out.println("5. Exit");
    }

    // Method to view earnings breakdown
    public void viewEarningsBreakdown(List<Order> orders) {
        double dailyEarnings = calculateEarnings(orders, 1);
        double monthlyEarnings = calculateEarnings(orders, 30);
        double yearlyEarnings = calculateEarnings(orders, 365);

        System.out.println("\n=== Earnings Breakdown ===");
        System.out.println("Daily Earnings: $" + dailyEarnings);
        System.out.println("Monthly Earnings: $" + monthlyEarnings);
        System.out.println("Yearly Earnings: $" + yearlyEarnings);
    }

    // Helper method to calculate earnings for a given time period
    private double calculateEarnings(List<Order> orders, int days) {
        long currentTime = System.currentTimeMillis();
        return orders.stream()
                .filter(order -> order.getDeliveryRunnerID() != null && order.getDeliveryRunnerID().equals(this.getUserID()))
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .filter(order -> (currentTime - order.getOrderTime()) <= (days * 24 * 60 * 60 * 1000L))
                .mapToDouble(Order::getDeliveryFee)
                .sum();
    }
}

// Order class
class Order {
    private final String orderId;
    private final String customerName;
    private final String vendorID;
    private OrderStatus status;
    private final double deliveryFee;
    private final double totalAmount;
    private String deliveryRunnerID;
    private final long orderTime; // Track when the order was placed

    public Order(String customerName, String orderId, String vendorID, OrderStatus status, double deliveryFee, double totalAmount, String deliveryRunnerID, long orderTime) {
        this.customerName = customerName;
        this.orderId = orderId;
        this.vendorID = vendorID;
        this.status = status;
        this.deliveryFee = deliveryFee;
        this.totalAmount = totalAmount;
        this.deliveryRunnerID = deliveryRunnerID;
        this.orderTime = orderTime;
    }

    public String getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getVendorID() { return vendorID; }
    public OrderStatus getStatus() { return status; }
    public double getDeliveryFee() { return deliveryFee; }
    public double getTotalAmount() { return totalAmount; }
    public String getDeliveryRunnerID() { return deliveryRunnerID; }
    public void setDeliveryRunnerID(String deliveryRunnerID) { this.deliveryRunnerID = deliveryRunnerID; }
    public long getOrderTime() { return orderTime; }

    // Method to update order status and send notifications
    public void setStatus(OrderStatus newStatus, List<User> users, Customer customer) {
        OrderStatus oldStatus = this.status;
        this.status = newStatus;

        // Notify the customer
        NotificationService.sendNotification(customer, "Your order " + orderId + " status changed from " + oldStatus + " to " + newStatus);

        // Notify the vendor
        User vendor = users.stream()
                .filter(u -> u.getUserID().equals(vendorID))
                .findFirst()
                .orElse(null);

        if (vendor != null) {
            NotificationService.sendNotification(vendor, "Order " + orderId + " status changed to " + newStatus);
        }

        // Refund if the order is declined
        if (newStatus == OrderStatus.DECLINED) {
            customer.topUp(totalAmount); // Refund the exact amount deducted
            System.out.println("Order declined. Refund of $" + totalAmount + " issued to " + customer.getUsername());
        }

        // Update earnings if the order is delivered
        if (newStatus == OrderStatus.DELIVERED) {
            // Update delivery runner earnings (delivery fee)
            User deliveryRunner = users.stream()
                    .filter(u -> u.getRole().equals("DeliveryRunner") && u.getUserID().equals(deliveryRunnerID))
                    .findFirst()
                    .orElse(null);

            if (deliveryRunner instanceof DeliveryRunner) {
                ((DeliveryRunner) deliveryRunner).addEarnings(deliveryFee);
            }
        }
    }
}

// NotificationService class to handle notifications
class NotificationService {
    // Method to send notification to a user
    public static void sendNotification(User user, String message) {
        String timestamp = new java.util.Date().toString();
        String ANSI_GREEN = "\u001B[32m"; // Green color for notifications
        String ANSI_RESET = "\u001B[0m"; // Reset color
        System.out.println(ANSI_GREEN + "[" + timestamp + "] Notification to " + user.getUsername() + ": " + message + ANSI_RESET);
    }
}

// Main FoodOrderingSystem class
public class FoodOrderingSystem {
    private static final List<User> users = FileHandler.loadUsers();
    private static final List<Order> orders = FileHandler.loadOrders();
    private static final List<Menu> menus = FileHandler.loadMenus();
    private static final Map<String, Double> earningsMap = FileHandler.loadEarnings();
    private static final List<Review> reviews = FileHandler.loadReviews();

    // Load earnings for vendors and delivery runners
    static {
        for (User user : users) {
            if (user instanceof Vendor) {
                ((Vendor) user).addEarnings(earningsMap.getOrDefault(user.getUserID(), 0.0));
            } else if (user instanceof DeliveryRunner) {
                ((DeliveryRunner) user).addEarnings(earningsMap.getOrDefault(user.getUserID(), 0.0));
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (users.stream().noneMatch(user -> user.getRole().equals("Admin"))) {
            System.out.println("No admin found. Creating default admin...");
            users.add(new Admin("admin", "admin123", UUID.randomUUID().toString().substring(0, 8)));
            FileHandler.saveUser(users.get(users.size() - 1)); // Save the default admin to file
        }

        while (true) {
            System.out.println("\n=== Food Ordering System ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else if (choice == 3) {
                System.out.println("Exiting... Thank you for using the Food Ordering System");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (user == null) {
            System.out.println("Invalid username or password.");
        } else {
            System.out.println("Login successful!");
            handleUserActions(user);
        }
    }

    private static void register() {
        System.out.println("\n=== Registration ===");
        System.out.println("1. Register as Customer");
        System.out.println("2. Register as Vendor");
        System.out.println("3. Register as Delivery Runner");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String userID = UUID.randomUUID().toString().substring(0, 8);

        User newUser = null;
        switch (choice) {
            case 1:
                newUser = new Customer(username, password, userID, 100.0);
                break;
            case 2:
                newUser = new Vendor(username, password, userID);
                break;
            case 3:
                newUser = new DeliveryRunner(username, password, userID);
                break;
            default:
                System.out.println("Invalid choice. Registration failed.");
                return;
        }

        users.add(newUser);
        FileHandler.saveUser(newUser);
        System.out.println(newUser.getRole() + " registered successfully!");
    }

    private static void handleUserActions(User user) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            user.displayMenu(); // Display the role-specific menu
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (user.getRole()) {
                case "Customer":
                    handleCustomerActions((Customer) user, choice);
                    break;
                case "Admin":
                    handleAdminActions((Admin) user, choice);
                    break;
                case "Vendor":
                    handleVendorActions((Vendor) user, choice);
                    break;
                case "DeliveryRunner":
                    handleDeliveryRunnerActions((DeliveryRunner) user, choice);
                    break;
                default:
                    System.out.println("Invalid role. Returning to main menu.");
                    return;
            }

            // Exit the role-specific menu if the user chooses to exit
            if (shouldExit(user.getRole(), choice)) {
                System.out.println("Returning to main menu...");
                break;
            }
        }
    }

    private static boolean shouldExit(String role, int choice) {
        switch (role) {
            case "Customer":
                return choice == 7;
            case "Admin":
                return choice == 6;
            case "Vendor":
                return choice == 6;
            case "DeliveryRunner":
                return choice == 5;
            default:
                return false;
        }
    }

    private static void handleAdminActions(Admin admin, int choice) {
        switch (choice) {
            case 1:
                registerVendor();
                break;
            case 2:
                registerCustomer();
                break;
            case 3:
                registerDeliveryRunner();
                break;
            case 4:
                topUpCustomerCredit();
                break;
            case 6:
                System.out.println("Exiting admin menu...");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    // Handle actions for Customer role
    private static void handleCustomerActions(Customer customer, int choice) {
        switch (choice) {
            case 1:
                viewMenu();
                break;
            case 2:
                placeOrder(customer);
                break;
            case 3:
                checkOrderStatus(customer);
                break;
            case 4:
                viewOrderHistory(customer);
                break;
            case 5:
                customer.viewWalletBalance(); // Call the new method
                break;
            case 6:
                writeVendorReview(customer);
                break;
            case 7:
                System.out.println("Exiting customer menu...");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    // Handle actions for Vendor role
    private static void handleVendorActions(Vendor vendor, int choice) {
        switch (choice) {
            case 1:
                addMenuItem(vendor);
                break;
            case 2:
                viewOrders(vendor);
                break;
            case 3:
                updateOrderStatus(vendor);
                break;
            case 4:
                vendor.viewEarnings();
                break;
            case 5:
                writeCustomerReview(vendor);
                break;
            case 6:
                System.out.println("Exiting vendor menu...");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    // Method to write a review for a vendor
    private static void writeVendorReview(Customer customer) {
        System.out.print("Enter the vendor ID to review: ");
        String vendorID = scanner.nextLine();
        System.out.print("Enter your review: ");
        String reviewText = scanner.nextLine();
        System.out.print("Enter your rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating. Please enter a rating between 1 and 5.");
            return;
        }

        customer.writeVendorReview(vendorID, reviewText, rating);
    }

    // Method to write a review for a customer
    private static void writeCustomerReview(Vendor vendor) {
        System.out.print("Enter the customer ID to review: ");
        String customerID = scanner.nextLine();
        System.out.print("Enter your review: ");
        String reviewText = scanner.nextLine();
        System.out.print("Enter your rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating. Please enter a rating between 1 and 5.");
            return;
        }

        vendor.writeCustomerReview(customerID, reviewText, rating);
    }

    // Handle actions for Delivery Runner role
    private static void handleDeliveryRunnerActions(DeliveryRunner runner, int choice) {
        switch (choice) {
            case 1:
                viewTasks(runner);
                break;
            case 2:
                acceptTask(runner);
                break;
            case 3:
                updateTaskStatus(runner);
                break;
            case 4:
                runner.viewEarningsBreakdown(orders); // Show earnings breakdown
                break;
            case 5:
                System.out.println("Exiting delivery runner menu...");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    // Method to view menu
    private static void viewMenu() {
        if (menus.isEmpty()) {
            System.out.println("No menu items available.");
        } else {
            System.out.println("\n=== Menu ===");
            for (Menu menu : menus) {
                String vendorName = users.stream()
                        .filter(u -> u.getUserID().equals(menu.getVendorID()))
                        .findFirst()
                        .map(User::getUsername)
                        .orElse("Unknown Vendor");
                System.out.println("Vendor: " + vendorName + ", Item: " + menu.getItemName() + ", Price: $" + menu.getPrice());
            }
        }
    }

    // Method to place an order
    private static void placeOrder(Customer customer) {
        viewMenu();
        System.out.print("Enter the item name to order: ");
        String itemName = scanner.nextLine();
        Menu selectedMenu = menus.stream()
                .filter(menu -> menu.getItemName().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);

        if (selectedMenu != null) {
            // Ask for delivery option
            System.out.println("Choose delivery option:");
            System.out.println("1. Dine-in");
            System.out.println("2. Takeaway");
            System.out.println("3. Delivery");
            System.out.print("Enter choice: ");
            int deliveryChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            double deliveryFee = 0.0;
            if (deliveryChoice == 3) {
                deliveryFee = 5.0; // Set delivery fee to $5 (you can change this value)
                System.out.println("Delivery fee of $" + deliveryFee + " will be added to your order.");
            }

            // Calculate total amount (item price + delivery fee)
            double totalAmount = selectedMenu.getPrice() + deliveryFee;

            // Check if the customer has sufficient funds
            if (customer.getWalletBalance() < totalAmount) {
                System.out.println("Insufficient funds. Please top up your wallet.");
                return; // Exit the method if the customer doesn't have enough funds
            }

            // Generate unique order ID
            String orderId = UUID.randomUUID().toString();

            // Create order and set initial status to "Pending"
            Order newOrder = new Order(customer.getUsername(), orderId, selectedMenu.getVendorID(), OrderStatus.PENDING, deliveryFee, totalAmount, null, System.currentTimeMillis());

            // Deduct wallet balance (item price + delivery fee)
            if (customer.deduct(totalAmount)) {
                // Save the order
                orders.add(newOrder);
                FileHandler.saveOrder(newOrder);
                System.out.println("Order placed successfully! Waiting for vendor approval...");
            } else {
                System.out.println("Insufficient funds. Please top up your wallet.");
                return;
            }

            // Notify the vendor
            User vendor = users.stream()
                    .filter(u -> u.getUserID().equals(selectedMenu.getVendorID()))
                    .findFirst()
                    .orElse(null);

            if (vendor != null) {
                NotificationService.sendNotification(vendor, "New order " + orderId + " placed by " + customer.getUsername());
            }

            // Display order summary
            System.out.println("Order Summary:");
            System.out.println("Item: " + selectedMenu.getItemName());
            System.out.println("Price: $" + selectedMenu.getPrice());
            System.out.println("Delivery Fee: $" + deliveryFee);
            System.out.println("Total Amount: $" + totalAmount);
            System.out.println("Order Status: " + newOrder.getStatus());
        } else {
            System.out.println("Item not found.");
        }
    }

    // Method to check order status
    private static void checkOrderStatus(Customer customer) {
        System.out.println("\n=== Your Orders ===");
        List<Order> customerOrders = orders.stream()
                .filter(order -> order.getCustomerName().equals(customer.getUsername()))
                .collect(Collectors.toList());

        if (customerOrders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            customerOrders.forEach(order -> System.out.println("Order ID: " + order.getOrderId() + ", Status: " + order.getStatus()));
        }
    }

    // Method to view order history
    private static void viewOrderHistory(Customer customer) {
        System.out.println("\n=== Order History ===");
        List<Order> customerOrders = orders.stream()
                .filter(order -> order.getCustomerName().equals(customer.getUsername()))
                .collect(Collectors.toList());

        if (customerOrders.isEmpty()) {
            System.out.println("No order history found.");
        } else {
            customerOrders.forEach(order -> System.out.println("Order ID: " + order.getOrderId() + ", Status: " + order.getStatus()));
        }
    }

    // Method to register a new vendor
    private static void registerVendor() {
        System.out.print("Enter vendor username: ");
        String username = scanner.nextLine();
        System.out.print("Enter vendor password: ");
        String password = scanner.nextLine();

        String userID = UUID.randomUUID().toString().substring(0, 8);
        User newVendor = new Vendor(username, password, userID);
        users.add(newVendor);
        FileHandler.saveUser(newVendor);
        System.out.println("Vendor registered successfully!");
    }

    // Method to register a new customer
    private static void registerCustomer() {
        System.out.print("Enter customer username: ");
        String username = scanner.nextLine();
        System.out.print("Enter customer password: ");
        String password = scanner.nextLine();

        String userID = UUID.randomUUID().toString().substring(0, 8);
        User newCustomer = new Customer(username, password, userID, 100.0);
        users.add(newCustomer);
        FileHandler.saveUser(newCustomer);
        System.out.println("Customer registered successfully!");
    }

    // Method to register a new delivery runner
    private static void registerDeliveryRunner() {
        System.out.print("Enter delivery runner username: ");
        String username = scanner.nextLine();
        System.out.print("Enter delivery runner password: ");
        String password = scanner.nextLine();

        String userID = UUID.randomUUID().toString().substring(0, 8);
        User newRunner = new DeliveryRunner(username, password, userID);
        users.add(newRunner);
        FileHandler.saveUser(newRunner);
        System.out.println("Delivery Runner registered successfully!");
    }

    // Method to top-up customer credit
    private static void topUpCustomerCredit() {
        System.out.print("Enter customer username: ");
        String username = scanner.nextLine();
        System.out.print("Enter top-up amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        User customer = users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getRole().equals("Customer"))
                .findFirst()
                .orElse(null);

        if (customer != null) {
            ((Customer) customer).topUp(amount);
            System.out.println("Credit topped up successfully!");
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Method to view orders for a vendor
    private static void viewOrders(Vendor vendor) {
        System.out.println("\n=== Your Orders ===");
        List<Order> vendorOrders = orders.stream()
                .filter(order -> order.getVendorID().equals(vendor.getUserID())) // Filter by vendorID
                .collect(Collectors.toList());

        if (vendorOrders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : vendorOrders) {
                // Find the menu item associated with the order
                Menu menuItem = menus.stream()
                        .filter(menu -> menu.getVendorID().equals(order.getVendorID()))
                        .findFirst()
                        .orElse(null);

                if (menuItem != null) {
                    System.out.println(
                            "Order ID: " + order.getOrderId() +
                                    ", Customer: " + order.getCustomerName() +
                                    ", Item: " + menuItem.getItemName() +
                                    ", Status: " + order.getStatus());
                } else {
                    System.out.println(
                            "Order ID: " + order.getOrderId() +
                                    ", Customer: " + order.getCustomerName() +
                                    ", Status: " + order.getStatus());
                }
            }
        }
    }

    // Method to update order status for a vendor
    private static void updateOrderStatus(Vendor vendor) {
        viewOrders(vendor); // Show the vendor's orders first
        System.out.print("Enter the order ID to update: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter the new status (PENDING, ACCEPTED, DECLINED): ");
        String status = scanner.nextLine().toUpperCase();

        Order order = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);

        if (order != null) {
            try {
                OrderStatus newStatus = OrderStatus.valueOf(status);
                // Find the customer for the order
                Customer customer = (Customer) users.stream()
                        .filter(u -> u.getUsername().equals(order.getCustomerName()))
                        .findFirst()
                        .orElse(null);

                if (customer != null) {
                    order.setStatus(newStatus, users, customer); // Pass the customer for refund logic
                    FileHandler.saveOrder(order); // Save the updated order
                    System.out.println("Order status updated successfully!");
                } else {
                    System.out.println("Customer not found.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please enter PENDING, ACCEPTED, or DECLINED.");
            }
        } else {
            System.out.println("Order not found.");
        }
    }

    // Method to view tasks for a delivery runner
    private static void viewTasks(DeliveryRunner runner) {
        System.out.println("\n=== Tasks ===");
        List<Order> tasks = orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.ACCEPTED)
                .collect(Collectors.toList());

        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            tasks.forEach(order -> System.out.println("Order ID: " + order.getOrderId() + ", Customer: " + order.getCustomerName()));
        }
    }

    // Method to accept a task for a delivery runner
    private static void acceptTask(DeliveryRunner runner) {
        viewTasks(runner);
        System.out.print("Enter the order ID to accept: ");
        String orderId = scanner.nextLine();

        Order order = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);

        if (order != null) {
            order.setDeliveryRunnerID(runner.getUserID());
            order.setStatus(OrderStatus.DELIVERING, users, (Customer) users.stream()
                    .filter(u -> u.getUsername().equals(order.getCustomerName()))
                    .findFirst()
                    .orElse(null));
            FileHandler.saveOrder(order);
            System.out.println("Task accepted successfully! Status updated to DELIVERING.");
        } else {
            System.out.println("Order not found.");
        }
    }

    // Method to update task status for a delivery runner
    private static void updateTaskStatus(DeliveryRunner runner) {
        viewTasks(runner);
        System.out.print("Enter the order ID to update: ");
        String orderId = scanner.nextLine();
        System.out.print("Enter the new status (DELIVERING, DELIVERED): ");
        String status = scanner.nextLine().toUpperCase();

        Order order = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);

        if (order != null) {
            try {
                OrderStatus newStatus = OrderStatus.valueOf(status);
                Customer customer = (Customer) users.stream()
                        .filter(u -> u.getUsername().equals(order.getCustomerName()))
                        .findFirst()
                        .orElse(null);

                if (customer != null) {
                    order.setStatus(newStatus, users, customer);
                    FileHandler.saveOrder(order);
                    System.out.println("Task status updated successfully!");
                } else {
                    System.out.println("Customer not found.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid status. Please enter DELIVERING or DELIVERED.");
            }
        } else {
            System.out.println("Order not found.");
        }
    }

    // Method to add menu item (for vendors)
    private static void addMenuItem(Vendor vendor) {
        System.out.print("Enter item name: ");
        String itemName = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        Menu newMenu = new Menu(vendor.getUserID(), itemName, price);
        menus.add(newMenu);
        FileHandler.saveMenu(newMenu);
        System.out.println("Menu item added successfully!");
    }
}