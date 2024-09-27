package com.example.warehousemanagement.test

import com.example.warehousemanagement.domain.model.Address
import com.example.warehousemanagement.domain.model.Area
import com.example.warehousemanagement.domain.model.Customer
import com.example.warehousemanagement.domain.model.ExportPackages
import com.example.warehousemanagement.domain.model.Genre
import com.example.warehousemanagement.domain.model.ImportPackages
import com.example.warehousemanagement.domain.model.Information
import com.example.warehousemanagement.domain.model.Notification
import com.example.warehousemanagement.domain.model.NotificationType
import com.example.warehousemanagement.domain.model.Product
import com.example.warehousemanagement.domain.model.Report
import com.example.warehousemanagement.domain.model.Role
import com.example.warehousemanagement.domain.model.Status
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.domain.model.User
import java.util.Date
import java.util.UUID

val products = listOf(
    Product(
        id = UUID.randomUUID().toString(),
        productName = "Laptop ABC",
        genre = Genre(
            idGenre = "1",
            genreName = "Electronics"
        ),
        quantity = 50,
        description = "High-performance laptop for gaming and work.",
        supplier = Supplier(
            idSupplier = "101",
            name = "Tech Supplies Co.",
            address = Address(
                street = "123 Tech Street",
                district = "Tech District",
                city = "Tech City",
                postalCode = "12345",
                phone = "0123-456789"
            )
        ),
        isInStock = true,
        barcode = "ABC123456",
        area = Area(
            idArea = "A1",
            areaName = "Main Warehouse",
            areaImage = "http://example.com/images/warehouse1.png"
        ),
        lastUpdated = Date()
    ),
    Product(
        id = UUID.randomUUID().toString(),
        productName = "Smartphone XYZ",
        genre = Genre(
            idGenre = "2",
            genreName = "Mobile Devices"
        ),
        quantity = 100,
        description = "Latest model smartphone with advanced features.",
        supplier = Supplier(
            idSupplier = "102",
            name = "Mobile World Ltd.",
            address = Address(
                street = "456 Mobile Ave",
                district = "Mobile District",
                city = "Mobile City",
                postalCode = "67890",
                phone = "0987-654321"
            )
        ),
        isInStock = true,
        barcode = "XYZ987654",
        area = Area(
            idArea = "A2",
            areaName = "Electronics Section",
            areaImage = "http://example.com/images/electronics.png"
        ),
        lastUpdated = Date()
    ),
    Product(
        id = UUID.randomUUID().toString(),
        productName = "Laptop ABC",
        genre = Genre(
            idGenre = "1",
            genreName = "Electronics"
        ),
        quantity = 50,
        description = "High-performance laptop for gaming and work.",
        supplier = Supplier(
            idSupplier = "101",
            name = "Tech Supplies Co.",
            address = Address(
                street = "123 Tech Street",
                district = "Tech District",
                city = "Tech City",
                postalCode = "12345",
                phone = "0123-456789"
            )
        ),
        isInStock = true,
        barcode = "ABC123456",
        area = Area(
            idArea = "A1",
            areaName = "Main Warehouse",
            areaImage = "http://example.com/images/warehouse1.png"
        ),
        lastUpdated = Date()
    ),
    Product(
        id = UUID.randomUUID().toString(),
        productName = "Smartphone XYZ",
        genre = Genre(
            idGenre = "2",
            genreName = "Mobile Devices"
        ),
        quantity = 100,
        description = "Latest model smartphone with advanced features.",
        supplier = Supplier(
            idSupplier = "102",
            name = "Mobile World Ltd.",
            address = Address(
                street = "456 Mobile Ave",
                district = "Mobile District",
                city = "Mobile City",
                postalCode = "67890",
                phone = "0987-654321"
            )
        ),
        isInStock = true,
        barcode = "XYZ987654",
        area = Area(
            idArea = "A2",
            areaName = "Electronics Section",
            areaImage = "http://example.com/images/electronics.png"
        ),
        lastUpdated = Date()
    ),
    Product(
        id = UUID.randomUUID().toString(),
        productName = "Wireless Headphones",
        genre = Genre(
            idGenre = "3",
            genreName = "Audio"
        ),
        quantity = 75,
        description = "Noise-cancelling wireless headphones for immersive sound.",
        supplier = Supplier(
            idSupplier = "103",
            name = "Audio Experts",
            address = Address(
                street = "789 Sound Blvd",
                district = "Audio District",
                city = "Sound City",
                postalCode = "11223",
                phone = "0456-789012"
            )
        ),
        isInStock = true,
        barcode = "HEADPHONES123",
        area = Area(
            idArea = "A3",
            areaName = "Audio Section",
            areaImage = "http://example.com/images/audio.png"
        ),
        lastUpdated = Date()
    ),
    Product(
        id = UUID.randomUUID().toString(),
        productName = "4K Monitor",
        genre = Genre(
            idGenre = "4",
            genreName = "Displays"
        ),
        quantity = 30,
        description = "Ultra HD 4K monitor for professional use.",
        supplier = Supplier(
            idSupplier = "104",
            name = "Display Solutions",
            address = Address(
                street = "321 Vision Rd",
                district = "Display District",
                city = "Vision City",
                postalCode = "33445",
                phone = "0678-901234"
            )
        ),
        isInStock = false,
        barcode = "MONITOR456",
        area = Area(
            idArea = "A4",
            areaName = "Display Section",
            areaImage = "http://example.com/images/display.png"
        ),
        lastUpdated = Date()
    ),
    Product(
        id = UUID.randomUUID().toString(),
        productName = "Gaming Mouse",
        genre = Genre(
            idGenre = "5",
            genreName = "Gaming Accessories"
        ),
        quantity = 150,
        description = "High-precision gaming mouse with customizable buttons.",
        supplier = Supplier(
            idSupplier = "105",
            name = "Gaming Gear",
            address = Address(
                street = "654 Game St",
                district = "Gaming District",
                city = "Game City",
                postalCode = "55667",
                phone = "0123-456789"
            )
        ),
        isInStock = true,
        barcode = "MOUSE789",
        area = Area(
            idArea = "A5",
            areaName = "Gaming Section",
            areaImage = "http://example.com/images/gaming.png"
        ),
        lastUpdated = Date()
    )
)
val reports = listOf(
    Report(
        id = "REP001",
        timestamp = Date(),
        title = "Monthly Sales Report",
        reportType = "FINANCIAL",
        description = "Detailed breakdown of sales for the current month",
        status = "COMPLETED"
    ),
    Report(
        id = "REP002",
        timestamp = Date(System.currentTimeMillis() - 86400000), // Yesterday
        title = "Customer Satisfaction Survey Results",
        reportType = "CUSTOMER",
        description = null,
        status = "IN_PROGRESS"
    ),
    Report(
        id = "REP003",
        timestamp = Date(System.currentTimeMillis() + 86400000), // Tomorrow
        title = "Inventory Status",
        reportType = "LOGISTICS",
        description = "Current stock levels and reorder recommendations",
        status = "SCHEDULED"
    ),
    Report(
        id = "REP004",
        timestamp = Date(),
        title = "Employee Performance Review",
        reportType = "HR",
        description = "Annual performance evaluations for all departments",
        status = "DRAFT"
    ),
    Report(
        id = "REP005",
        timestamp = Date(System.currentTimeMillis() - 172800000), // 2 days ago
        title = "Website Traffic Analysis",
        reportType = "MARKETING",
        description = "Detailed analysis of website visitors and user behavior",
        status = "COMPLETED"
    )
)


val notifications = listOf(
    Notification(
        id = "NOTIF001",
        title = "New Message",
        senderId = "USER123",
        receiverId = "USER456",
        description = "You have received a new message from John Doe",
        type = NotificationType.UPDATE,
        timestamp = Date()
    ),
    Notification(
        id = "NOTIF002",
        title = "System Maintenance",
        senderId = "SYSTEM",
        receiverId = "ALL_USERS",
        description = "Scheduled maintenance will occur tonight from 2 AM to 4 AM",
        type = NotificationType.WARNING,
        timestamp = Date(System.currentTimeMillis() + 3600000) // 1 hour from now
    ),
    Notification(
        id = "NOTIF003",
        title = "Friend Request",
        senderId = "USER789",
        receiverId = "USER456",
        description = null,
        type = NotificationType.UPDATE,
        timestamp = Date(System.currentTimeMillis() - 86400000) // Yesterday
    ),
    Notification(
        id = "NOTIF004",
        title = "Account Security Alert",
        senderId = "SECURITY_TEAM",
        receiverId = "USER123",
        description = "Unusual login attempt detected. Please verify your recent activity.",
        type = NotificationType.WARNING,
        timestamp = Date(System.currentTimeMillis() - 1800000) // 30 minutes ago
    ),
    Notification(
        id = "NOTIF005",
        title = "New Feature Available",
        senderId = "PRODUCT_TEAM",
        receiverId = "ALL_USERS",
        description = "Check out our new chat feature! Communicate with your team in real-time.",
        type = NotificationType.CLASSIFY,
        timestamp = Date()
    )
)
val users = listOf(
    User(
        id = "USER001",
        username = "johndoe",
        passwordHash = "5f4dcc3b5aa765d61d8327deb882cf99", // hash for "password"
        role = Role(
            roleId = "ROLE001",
            roleName = "User"
        ),
        information = Information(
            firstName = "John",
            lastName = "Doe",
            email = "john.doe@example.com",
        )
    ),
    User(
        id = "USER002",
        username = "janesmit",
        passwordHash = "098f6bcd4621d373cade4e832627b4f6", // hash for "test"
        role = Role(
            roleId = "ROLE002",
            roleName = "Admin"
        ),
        information = Information(
            firstName = "Jane",
            lastName = "Smith",
            email = "jane.smith@example.com"
        )
    ),
    User(
        id = "USER003",
        username = "bobwilson",
        passwordHash = "62c8ad0a15d9d1ca38d5dee762a16e01", // hash for "123456"
        role = Role(
            roleId = "ROLE001",
            roleName = "User"
        ),
        information = Information(
            firstName = "Bob",
            lastName = "Wilson",
            email = "bob.wilson@example.com",

        )
    ),
    User(
        id = "USER004",
        username = "alicejohn",
        passwordHash = "e10adc3949ba59abbe56e057f20f883e", // hash for "123456"
        role = Role(
            roleId = "ROLE003",
            roleName = "User"
        ),
        information = Information(
            firstName = "Alice",
            lastName = "Johnson",
            email = "alice.johnson@example.com"
        )
    ),
    User(
        id = "USER005",
        username = "charliebrw",
        passwordHash = "fcea920f7412b5da7be0cf42b8c93759", // hash for "1234567"
        role = Role(
            roleId = "ROLE001",
            roleName = "User"
        ),
        information = Information(
            firstName = "Charlie",
            lastName = "Brown",
            email = "charlie.brown@example.com",
        )
    )
)
val importPackages = listOf(
    ImportPackages(
        id = "IMP001",
        packageName = "Electronics Shipment",
        importDate = Date(),
        supplier = Supplier("SUP001", "TechWorld Electronics", address = Address(
            street = "123 Main St",
            district = "Downtown",
            city = "Mobile City",
            postalCode = "12345",
            phone = "555-1234"
        )),
        idReceivedBy = "EMP123",
        status = Status.PENDING,
        note = "Handle with care, contains fragile items"
    ),
    ImportPackages(
        id = "IMP002",
        packageName = "Office Supplies",
        importDate = Date(System.currentTimeMillis() - 86400000), // Yesterday
        supplier = Supplier("SUP002", "Office Depot", address = Address(
            street = "123 Main St",
            district = "Downtown",
            city = "Mobile City",
            postalCode = "12345",
            phone = "555-1234"
        )),
        idReceivedBy = "EMP456",
        status = Status.DONE
    ),
    ImportPackages(
        id = "IMP003",
        packageName = "Food Products",
        importDate = Date(System.currentTimeMillis() + 86400000), // Tomorrow
        supplier = Supplier("SUP003", "Fresh Foods Inc.", address = Address(
            street = "123 Main St",
            district = "Downtown",
            city = "Mobile City",
            postalCode = "12345",
            phone = "555-1234"
        )),
        idReceivedBy = "EMP789",
        status = Status.PENDING,
        note = "Refrigerate immediately upon arrival"
    ),
    ImportPackages(
        id = "IMP004",
        packageName = "Furniture Shipment",
        importDate = Date(),
        supplier = Supplier("SUP004", "Modern Furnishings", address = Address(
            street = "123 Main St",
            district = "Downtown",
            city = "Mobile City",
            postalCode = "12345",
            phone = "555-1234"
        )),
        idReceivedBy = "EMP234",
        status = Status.DONE,
        note = "Large items, requires forklift"
    ),
    ImportPackages(
        id = "IMP005",
        packageName = "Clothing Batch",
        importDate = Date(System.currentTimeMillis() - 172800000), // 2 days ago
        supplier = Supplier("SUP005", "Fashion Forward", address = Address(
            street = "123 Main St",
            district = "Downtown",
            city = "Mobile City",
            postalCode = "12345",
            phone = "555-1234"
        )),
        idReceivedBy = "EMP567",
        status = Status.PENDING
    )
)
val exportPackages = listOf(
    ExportPackages(
        id = "EXP001",
        packageName = "Electronics Order",
        exportDate = Date(),
        customer = Customer(
            id = "CUST001",
            customerName = "John Doe",
            address = Address(
                street = "123 Main St",
                district = "Downtown",
                city = "Mobile City",
                postalCode = "12345",
                phone = "555-1234"
            )
        ),
        shipTo = "456 Elm St, Uptown, 67890",
        status = Status.PENDING,
        deliveryMethod = "Express Shipping",
        note = "Fragile items, handle with care"
    ),
    ExportPackages(
        id = "EXP002",
        packageName = "Office Supplies",
        exportDate = Date(System.currentTimeMillis() - 86400000), // Yesterday
        customer = Customer(
            id = "CUST002",
            customerName = "Jane Smith",
            address = Address(
                street = "789 Oak Ave",
                district = "Midtown",
                city = "Mobile City",
                postalCode = "54321",
                phone = "555-5678"
            )
        ),
        shipTo = "101 Pine Rd, Suburb, 10101",
        status = Status.DONE,
        deliveryMethod = "Standard Shipping"
    ),
    ExportPackages(
        id = "EXP003",
        packageName = "Furniture Set",
        exportDate = Date(System.currentTimeMillis() + 86400000), // Tomorrow
        customer = Customer(
            id = "CUST003",
            customerName = "Bob Johnson",
            address = Address(
                street = "321 Maple Ln",
                district = "Westside",
                city = "Mobile City",
                postalCode = "98765",
                phone = "555-9876"
            )
        ),
        shipTo = "654 Cedar Blvd, Eastside, 45678",
        status = Status.PENDING,
        deliveryMethod = "Freight",
        note = "Large items, special handling required"
    ),
    ExportPackages(
        id = "EXP004",
        packageName = "Clothing Order",
        exportDate = Date(),
        customer = Customer(
            id = "CUST004",
            customerName = "Alice Brown",
            address = Address(
                street = "246 Birch St",
                district = "Northend",
                city = "Mobile City",
                postalCode = "13579",
                phone = "555-2468"
            )
        ),
        shipTo = "135 Willow Ave, Southend, 24680",
        status = Status.PENDING,
        deliveryMethod = "Priority Mail"
    ),
    ExportPackages(
        id = "EXP005",
        packageName = "Book Collection",
        exportDate = Date(System.currentTimeMillis() - 172800000), // 2 days ago
        customer = Customer(
            id = "CUST005",
            customerName = "Charlie Wilson",
            address = Address(
                street = "579 Spruce Rd",
                district = "Easttown",
                city = "Mobile City",
                postalCode = "97531",
                phone = "555-3690"
            )
        ),
        shipTo = "864 Fir Lane, Westtown, 86420",
        status = Status.DONE,
        deliveryMethod = "Ground Shipping",
        note = "Educational materials"
    )
)
