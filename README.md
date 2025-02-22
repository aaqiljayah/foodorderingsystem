# Food Ordering System

Welcome to the **Food Ordering System**! This is a Java-based application designed to simulate a food delivery platform where users can register as customers, vendors, delivery runners, or administrators. The system allows customers to place orders, vendors to manage menus and orders, delivery runners to handle deliveries, and administrators to manage user accounts and system operations.

---

## Features

### **User Roles**
1. **Admin**:
   - Register new vendors, customers, and delivery runners.
   - Top-up customer wallet balances.
   - Manage system-wide operations.

2. **Customer**:
   - Browse menus from vendors.
   - Place orders for food items.
   - Check order status and view order history.
   - Write reviews for vendors.
   - Manage wallet balance.

3. **Vendor**:
   - Add and manage menu items.
   - View and update order statuses.
   - Write reviews for customers.
   - Track earnings from orders.

4. **Delivery Runner**:
   - View and accept delivery tasks.
   - Update task status (e.g., delivering, delivered).
   - Track earnings from completed deliveries.

---

### **Core Functionalities**
- **User Authentication**: Login and registration for all user roles.
- **Order Management**: Customers can place orders, vendors can accept/decline orders, and delivery runners can update delivery status.
- **Menu Management**: Vendors can add and manage their menu items.
- **Review System**: Customers and vendors can leave reviews for each other.
- **Earnings Tracking**: Vendors and delivery runners can track their earnings.
- **Notifications**: Real-time notifications for order status updates.

---

## How It Works

1. **Registration**:
   - Users can register as customers, vendors, or delivery runners.
   - Admins can register new users and manage existing ones.

2. **Login**:
   - Users log in with their credentials to access role-specific menus.

3. **Role-Specific Actions**:
   - **Customers**: Place orders, view order history, and write reviews.
   - **Vendors**: Manage menus, update order statuses, and track earnings.
   - **Delivery Runners**: Accept and update delivery tasks.
   - **Admins**: Manage user accounts and system operations.

4. **Data Persistence**:
   - All user, order, menu, earnings, and review data is saved to text files for persistence.

---

## Getting Started

### Prerequisites
- Java Development Kit (JDK) installed on your machine.
- A Java IDE (e.g., IntelliJ IDEA, Eclipse) or command-line tools.

### Running the Application
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/food-ordering-system.git
   ```
2. Navigate to the project directory:
   ```bash
   cd food-ordering-system
   ```
3. Compile and run the `FoodOrderingSystem.java` file:
   ```bash
   javac FoodOrderingSystem.java
   java FoodOrderingSystem
   ```
4. Follow the on-screen instructions to register, log in, and use the system.

---

## File Structure
- **`users.txt`**: Stores user data (username, password, role, etc.).
- **`orders.txt`**: Stores order details (order ID, customer, vendor, status, etc.).
- **`menus.txt`**: Stores menu items (vendor ID, item name, price).
- **`earnings.txt`**: Stores earnings data for vendors and delivery runners.
- **`reviews.txt`**: Stores reviews left by users.

---

## Code Overview

### Key Classes
1. **`FileHandler`**: Handles file operations for saving and loading data.
2. **`User`**: Abstract class representing a user. Extended by `Admin`, `Customer`, `Vendor`, and `DeliveryRunner`.
3. **`Order`**: Represents an order with details like order ID, customer, vendor, status, and delivery fee.
4. **`Menu`**: Represents a menu item with details like vendor ID, item name, and price.
5. **`Review`**: Represents a review with details like reviewer ID, reviewee ID, review text, and rating.
6. **`NotificationService`**: Handles sending notifications to users.

---

## Example Usage

### Customer Flow
1. Register as a customer.
2. Log in and browse the menu.
3. Place an order.
4. Check order status and view order history.
5. Write a review for the vendor.

### Vendor Flow
1. Register as a vendor.
2. Log in and add menu items.
3. View and update order statuses.
4. Track earnings and write reviews for customers.

### Delivery Runner Flow
1. Register as a delivery runner.
2. Log in and view available tasks.
3. Accept and update delivery tasks.
4. Track earnings.

### Admin Flow
1. Log in as an admin.
2. Register new users (vendors, customers, delivery runners).
3. Top-up customer wallet balances.

---

## Contributing
Contributions are welcome! If you'd like to contribute, please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature or bugfix.
3. Commit your changes.
4. Submit a pull request.

---

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact
For questions or feedback, please reach out to [your-email@example.com].

---

Enjoy using the **Food Ordering System**! 🍔🍕🚴‍♂️
