public enum OrderStatus {
    PENDING,   // Order is waiting for vendor action
    ACCEPTED,  // Order is accepted by vendor
    DECLINED,  // Order has been declined by vendor
    COMPLETED, DELIVERING, DELIVERED; // Order has been delivered to the customer
}
