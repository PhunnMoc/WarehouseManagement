package com.example.warehousemanagement.test

import com.example.warehousemanagement.domain.model.Address
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
import com.example.warehousemanagement.domain.model.StatusPackage
import com.example.warehousemanagement.domain.model.StorageLocation
import com.example.warehousemanagement.domain.model.Supplier
import com.example.warehousemanagement.domain.model.User
import java.util.Date

//Customer
val customer1 = Customer(
    idCustomer = "C001",
    email = "john.doe@example.com",
    customerName = "John Doe",
    address = Address(
        idAddress = "A001",
        street = "123 Le Loi Street",
        district = "District 1",
        city = "Ho Chi Minh City",
        postalCode = "700000",
        phone = "0909123456"
    )
)

val customer2 = Customer(
    idCustomer = "C002",
    email = "jane.smith@example.com",
    customerName = "Jane Smith",
    address = Address(
        idAddress = "A002",
        street = "456 Hai Ba Trung Street",
        district = "District 3",
        city = "Ho Chi Minh City",
        postalCode = "700001",
        phone = "0912345678"
    )
)

val customer3 = Customer(
    idCustomer = "C003",
    email = "michael.brown@example.com",
    customerName = "Michael Brown",
    address = Address(
        idAddress = "A003",
        street = "789 Nguyen Trai Street",
        district = "District 5",
        city = "Ho Chi Minh City",
        postalCode = "700002",
        phone = "0987654321"
    )
)

val customer4 = Customer(
    idCustomer = "C004",
    email = "emily.davis@example.com",
    customerName = "Emily Davis",
    address = Address(
        idAddress = "A004",
        street = "101 Tran Hung Dao Street",
        district = "District 1",
        city = "Ho Chi Minh City",
        postalCode = "700003",
        phone = "0932123456"
    )
)

val customer5 = Customer(
    idCustomer = "C005",
    email = "david.johnson@example.com",
    customerName = "David Johnson",
    address = Address(
        idAddress = "A005",
        street = "202 Le Duan Street",
        district = "District 3",
        city = "Ho Chi Minh City",
        postalCode = "700004",
        phone = "0909876543"
    )
)

// List customer
val listCustomer = listOf<Customer>(customer1, customer2, customer3, customer4, customer5)

//Genre
val genre1 = Genre(
    idGenre = "G001",
    genreName = "Electronics"
)
val genre2 = Genre(
    idGenre = "G002",
    genreName = "Books"
)
val genre3 = Genre(
    idGenre = "G003",
    genreName = "Clothing"
)
val genre4 = Genre(
    idGenre = "G004",
    genreName = "Home Appliances"
)
val genre5 = Genre(
    idGenre = "G005",
    genreName = "Sports Equipment"
)

//Supplier
val supplier1 = Supplier(
    idSupplier = "S001",
    name = "Tech Supplies Ltd.",
    email = "contact@techsupplies.com",
    address = Address(
        idAddress = "A101",
        street = "789 Le Loi Street",
        district = "District 1",
        city = "Ho Chi Minh City",
        postalCode = "700005",
        phone = "0908123456"
    ),
    ratings = 4.5f
)

val supplier2 = Supplier(
    idSupplier = "S002",
    name = "Book World Co.",
    email = "support@bookworld.com",
    address = Address(
        idAddress = "A102",
        street = "123 Nguyen Thi Minh Khai Street",
        district = "District 3",
        city = "Ho Chi Minh City",
        postalCode = "700006",
        phone = "0909345678"
    ),
    ratings = 4.2f
)

val supplier3 = Supplier(
    idSupplier = "S003",
    name = "Clothiers International",
    email = "info@clothiers.com",
    address = Address(
        idAddress = "A103",
        street = "45 Pasteur Street",
        district = "District 1",
        city = "Ho Chi Minh City",
        postalCode = "700007",
        phone = "0912345678"
    ),
    ratings = 4.7f
)

val supplier4 = Supplier(
    idSupplier = "S004",
    name = "Appliance Masters",
    email = "sales@appliancemasters.com",
    address = Address(
        idAddress = "A104",
        street = "678 Vo Van Tan Street",
        district = "District 5",
        city = "Ho Chi Minh City",
        postalCode = "700008",
        phone = "0987654321"
    ),
    ratings = 4.8f
)
val supplier5 = Supplier(
    idSupplier = "S005",
    name = "Sports Gear Hub",
    email = "contact@sportsgearhub.com",
    address = Address(
        idAddress = "A105",
        street = "12 Tran Quoc Toan Street",
        district = "District 10",
        city = "Ho Chi Minh City",
        postalCode = "700009",
        phone = "0978123456"
    ),
    ratings = 4.4f
)

//List suppliers
val listSuppliers = listOf<Supplier>(supplier1, supplier2, supplier3, supplier4, supplier5)

//StorageLocation
val location1 = StorageLocation(
    id = "L001",
    storageLocationName = "Khu A",
    storageLocationImage = "https://example.com/images/main_warehouse.jpg"
)
val location2 = StorageLocation(
    id = "L002",
    storageLocationName = "Secondary Storage",
    storageLocationImage = "https://example.com/images/secondary_storage.jpg"
)
val location3 = StorageLocation(
    id = "L003",
    storageLocationName = "Cold Storage",
    storageLocationImage = "https://example.com/images/cold_storage.jpg"
)
val location4 = StorageLocation(
    id = "L004",
    storageLocationName = "Outdoor Storage",
    storageLocationImage = "https://example.com/images/outdoor_storage.jpg"
)
val location5 = StorageLocation(
    id = "L005",
    storageLocationName = "Overflow Storage",
    storageLocationImage = "https://example.com/images/overflow_storage.jpg"
)
val listLocation = listOf<StorageLocation>(location1, location2, location3, location4, location5)
//Product
val product1 = Product(
    idProduct = "P001",
    productName = "Smartphone XYZ",
    genreId = "G001", // Electronics
    quantity = 50,
    description = "A high-performance smartphone with 128GB storage.",
    importPrice = 300.00,
    sellingPrice = 500.00,
    supplierId = "S001", // Tech Supplies Ltd.
    isInStock = true,
    barcode = "1234567890123",
    storageLocationId = "L001", // Main Warehouse
    lastUpdated = Date(), // Current date
    image = "https://example.com/images/smartphone_xyz.jpg"
)

val product2 = Product(
    idProduct = "P002",
    productName = "Wireless Headphones ABC",
    genreId = "G001", // Electronics
    quantity = 30,
    description = "Noise-cancelling wireless headphones with Bluetooth 5.0.",
    importPrice = 100.00,
    sellingPrice = 150.00,
    supplierId = "S001", // Tech Supplies Ltd.
    isInStock = true,
    barcode = "9876543210987",
    storageLocationId = "L002", // Secondary Storage
    lastUpdated = Date(),
    image = "https://example.com/images/headphones_abc.jpg"
)

val product3 = Product(
    idProduct = "P003",
    productName = "Novel: The Great Adventure",
    genreId = "G002", // Books
    quantity = 100,
    description = "A thrilling novel about adventure and mystery.",
    importPrice = 5.00,
    sellingPrice = 10.00,
    supplierId = "S002", // Book World Co.
    isInStock = true,
    barcode = "5432109876543",
    storageLocationId = "L003", // Cold Storage
    lastUpdated = Date(),
    image = "https://example.com/images/novel_great_adventure.jpg"
)
val product4 = Product(
    idProduct = "P004",
    productName = "Running Shoes DEF",
    genreId = "G003", // Clothing
    quantity = 25,
    description = "Comfortable running shoes designed for maximum support.",
    importPrice = 50.00,
    sellingPrice = 75.00,
    supplierId = "S003", // Clothiers International
    isInStock = true,
    barcode = "3216549870321",
    storageLocationId = "L004", // Outdoor Storage
    lastUpdated = Date(),
    image = "https://example.com/images/running_shoes_def.jpg"
)

val product5 = Product(
    idProduct = "P005",
    productName = "Blender GHI",
    genreId = "G004", // Home Appliances
    quantity = 15,
    description = "High-performance blender with multiple speed settings.",
    importPrice = 40.00,
    sellingPrice = 65.00,
    supplierId = "S004", // Appliance Masters
    isInStock = true,
    barcode = "4567891234567",
    storageLocationId = "L001", // Main Warehouse
    lastUpdated = Date(),
    image = "https://example.com/images/blender_ghi.jpg"
)

val product6 = Product(
    idProduct = "P006",
    productName = "Yoga Mat JKL",
    genreId = "G005", // Sports Equipment
    quantity = 40,
    description = "Eco-friendly yoga mat for comfortable practice.",
    importPrice = 20.00,
    sellingPrice = 35.00,
    supplierId = "S005", // Sports Gear Hub
    isInStock = true,
    barcode = "6543217890123",
    storageLocationId = "L002", // Secondary Storage
    lastUpdated = Date(),
    image = "https://example.com/images/yoga_mat_jkl.jpg"
)

val product7 = Product(
    idProduct = "P007",
    productName = "Wireless Mouse MNO",
    genreId = "G001", // Electronics
    quantity = 60,
    description = "Ergonomic wireless mouse with customizable buttons.",
    importPrice = 25.00,
    sellingPrice = 40.00,
    supplierId = "S001", // Tech Supplies Ltd.
    isInStock = true,
    barcode = "7890123456789",
    storageLocationId = "L003", // Cold Storage
    lastUpdated = Date(),
    image = "https://example.com/images/wireless_mouse_mno.jpg"
)

val product8 = Product(
    idProduct = "P008",
    productName = "Table Lamp PQR",
    genreId = "G004", // Home Appliances
    quantity = 20,
    description = "Stylish table lamp with adjustable brightness.",
    importPrice = 30.00,
    sellingPrice = 50.00,
    supplierId = "S004", // Appliance Masters
    isInStock = true,
    barcode = "8901234567890",
    storageLocationId = "L005", // Overflow Storage
    lastUpdated = Date(),
    image = "https://example.com/images/table_lamp_pqr.jpg"
)

//List Products
val listProduct =
    listOf<Product>(product1, product2, product3, product4, product5, product6, product7, product8)

// Export Packages
val exportPackage1 = ExportPackages(
    idExportPackages = "EP001",
    packageName = "Order #001",
    totalAmount = 5000.00, // Selling price * quantity
    listProduct = listOf<Product>(
        Product(
            idProduct = "P008",
            productName = "Table Lamp PQR",
            genreId = "G004", // Home Appliances
            quantity = 20,
            description = "Stylish table lamp with adjustable brightness.",
            importPrice = 30.00,
            sellingPrice = 0.0,
            supplierId = "S004", // Appliance Masters
            isInStock = true,
            lastUpdated = Date(),
        )
    ),
    exportDate = Date(),
    idCustomer = "C001", // John Doe
    shipTo = "123 Le Loi Street, District 1, Ho Chi Minh City",
    status = StatusPackage.PENDING,
    deliveryMethod = "Standard Shipping",
    note = "Deliver by next week."
)

val exportPackage2 = ExportPackages(
    idExportPackages = "EP002",
    packageName = "Order #002",
    totalAmount = 375.00, // Selling price * quantity
    exportDate = Date(),
    listProduct = listOf<Product>(
        Product(
            idProduct = "P008",
            productName = "Table Lamp PQR",
            genreId = "G004", // Home Appliances
            quantity = 20,
            description = "Stylish table lamp with adjustable brightness.",
            importPrice = 30.00,
            sellingPrice = 0.0,
            supplierId = "S004", // Appliance Masters
            isInStock = true,
            lastUpdated = Date(),
        )
    ),
    idCustomer = "C002", // Jane Smith
    shipTo = "456 Hai Ba Trung Street, District 3, Ho Chi Minh City",
    status = StatusPackage.CANCELED,
    deliveryMethod = "Express Shipping",
    note = null // No additional notes
)

val exportPackage3 = ExportPackages(
    idExportPackages = "EP003",
    packageName = "Order #003",
    totalAmount = 150.00, // Selling price * quantity
    listProduct = listOf<Product>(
        Product(
            idProduct = "P008",
            productName = "Table Lamp PQR",
            genreId = "G004", // Home Appliances
            quantity = 20,
            description = "Stylish table lamp with adjustable brightness.",
            importPrice = 30.00,
            sellingPrice = 0.0,
            supplierId = "S004", // Appliance Masters
            isInStock = true,
            lastUpdated = Date(),
        )
    ),
    exportDate = Date(),
    idCustomer = "C003", // Michael Brown
    shipTo = "789 Nguyen Trai Street, District 5, Ho Chi Minh City",
    status = StatusPackage.DELIVERED,
    deliveryMethod = "Standard Shipping",
    note = "Customer requested a delivery confirmation."
)
val exportPackage4 = ExportPackages(
    idExportPackages = "EP004",
    packageName = "Order #004",
    totalAmount = 455.00, // Selling price * quantity
    listProduct = listOf<Product>(
        Product(
            idProduct = "P008",
            productName = "Table Lamp PQR",
            genreId = "G004", // Home Appliances
            quantity = 20,
            description = "Stylish table lamp with adjustable brightness.",
            importPrice = 30.00,
            sellingPrice = 0.0,
            supplierId = "S004", // Appliance Masters
            isInStock = true,
            lastUpdated = Date(),
        )
    ),
    exportDate = Date(),
    idCustomer = "C004", // Emily Davis
    shipTo = "101 Tran Hung Dao Street, District 1, Ho Chi Minh City",
    status = StatusPackage.PENDING,
    deliveryMethod = "Standard Shipping",
    note = "Urgent delivery requested."
)

//Import package
val importPackage1 = ImportPackages(
    idImportPackages = "IP001",
    packageName = "Import Order #001",
    importDate = Date(),
    listProduct = listOf<Product>(
        Product(
            idProduct = "P008",
            productName = "Table Lamp PQR",
            genreId = "G004", // Home Appliances
            quantity = 20,
            description = "Stylish table lamp with adjustable brightness.",
            importPrice = 30.00,
            sellingPrice = 0.0,
            supplierId = "S004", // Appliance Masters
            isInStock = true,
            lastUpdated = Date(),
        )
    ),
    idSupplier = "S001", // Tech Supplies Ltd.
    idReceivedBy = "John Doe",
    status = StatusPackage.PENDING,
    totalAmount = 6000.00, // Import price * quantity
    note = "First shipment."
)

val importPackage2 = ImportPackages(
    idImportPackages = "IP002",
    packageName = "Import Order #002",
    listProduct = listOf<Product>(
        Product(
            idProduct = "P008",
            productName = "Table Lamp PQR",
            genreId = "G004", // Home Appliances
            quantity = 20,
            description = "Stylish table lamp with adjustable brightness.",
            importPrice = 30.00,
            sellingPrice = 0.0,
            supplierId = "S004", // Appliance Masters
            isInStock = true,
            lastUpdated = Date(),
        )
    ),
    importDate = Date(),
    idSupplier = "S004", // Appliance Masters
    idReceivedBy = "Emily Davis",
    status = StatusPackage.CANCELED,
    totalAmount = 750.00, // Import price * quantity
    note = null // No additional notes
)

val importPackage3 = ImportPackages(
    idImportPackages = "IP003",
    packageName = "Import Order #003",
    listProduct = listOf<Product>(
        Product(
            idProduct = "P008",
            productName = "Table Lamp PQR",
            genreId = "G004", // Home Appliances
            quantity = 20,
            description = "Stylish table lamp with adjustable brightness.",
            importPrice = 30.00,
            sellingPrice = 0.0,
            supplierId = "S004", // Appliance Masters
            isInStock = true,
            lastUpdated = Date(),
        )
    ),
    importDate = Date(),
    idSupplier = "S002", // Book World Co.
    idReceivedBy = "Michael Brown",
    status = StatusPackage.DELIVERED,
    totalAmount = 300.00, // Import price * quantity
    note = "Bulk order."
)

val importPackage4 = ImportPackages(
    idImportPackages = "IP004",
    packageName = "Import Order #004",
    listProduct = listOf<Product>(
        Product(
            idProduct = "P008",
            productName = "Table Lamp PQR",
            genreId = "G004", // Home Appliances
            quantity = 20,
            description = "Stylish table lamp with adjustable brightness.",
            importPrice = 30.00,
            sellingPrice = 0.0,
            supplierId = "S004", // Appliance Masters
            isInStock = true,
            lastUpdated = Date(),
        )
    ),
    importDate = Date(),
    idSupplier = "S001", // Tech Supplies Ltd.
    idReceivedBy = "David Johnson",
    status = StatusPackage.PENDING,
    totalAmount = 1250.00, // Import price * quantity
    note = "Urgent delivery."
)

//User
val user1 = User(
    idUser = "U001",
    username = "john_doe",
    passwordHash = "hashed_password_1", // Replace with actual hashed password
    information = Information(
        idInformation = "I001",
        firstName = "John",
        lastName = "Doe",
        email = "john.doe@example.com",
        role = Role.USER,
        picture = "https://example.com/images/john_doe.jpg"
    )
)
val user2 = User(
    idUser = "U002",
    username = "jane_smith",
    passwordHash = "hashed_password_2", // Replace with actual hashed password
    information = Information(
        idInformation = "I002",
        firstName = "Jane",
        lastName = "Smith",
        email = "jane.smith@example.com",
        role = Role.ADMIN,
        picture = null // No picture provided
    )
)

//Notification
val notification1 = Notification(
    idNotification = "N001",
    title = "System Update Available",
    description = "A new system update is ready for installation.",
    type = NotificationType.UPDATE,
    timestamp = Date()
)
val notification2 = Notification(
    idNotification = "N002",
    title = "Security Warning",
    description = "A potential security threat has been detected.",
    type = NotificationType.WARNING,
    timestamp = Date()
)
val notification3 = Notification(
    idNotification = "N003",
    title = "Classification Required",
    description = "Please classify the newly added items.",
    type = NotificationType.CLASSIFY,
    timestamp = Date()
)
val notification4 = Notification(
    idNotification = "N004",
    title = "Server Maintenance",
    type = NotificationType.WARNING,
    timestamp = Date() // No description for this notification
)

//Report
val report1 = Report(
    idReport = "R001",
    timestamp = Date(),
    title = "Issue with Product A",
    idSender = "U001", // Assuming this corresponds to a user ID
    reportType = "Product Issue",
    description = "Customer reported a malfunction with Product A.",
    status = "Pending"
)
val report2 = Report(
    idReport = "R002",
    timestamp = Date(),
    title = "Service Outage Notification",
    idSender = "U002",
    reportType = "Service Outage",
    description = "Notification of a service outage affecting multiple users.",
    status = "Resolved"
)
val report3 = Report(
    idReport = "R003",
    timestamp = Date(),
    title = "Feedback on New Feature",
    idSender = "U003",
    reportType = "Feedback",
    description = "User provided feedback on the newly implemented feature.",
    status = "Under Review"
)
val report4 = Report(
    idReport = "R004",
    timestamp = Date(),
    title = "Bug Report for Version 2.1",
    idSender = "U001",
    reportType = "Bug",
    description = null, // No description provided
    status = "Pending"
)
