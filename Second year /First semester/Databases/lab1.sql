create database MakeUpShop

use MakeUpShop

create table Categories(
	CategoryID int NOT NULL,
    CategoryName varchar(100),
    primary key(CategoryID)
)


create table Brands(
	BrandID int NOT NULL,
	BrandName varchar(100),
    primary key(BrandID)
)

create table Customers(
	CustomerID int NOT NULL,
    FirstName varchar(50),
    LastName varchar(50),
    Phone int NOT NULL,
    primary key(CustomerID)
)


-- 1:n -> one category can have many products, one product can belong to a single category
create table Products(
	ProductID int NOT NULL,
    ProductName varchar(100),
    CategoryID int NOT NULL,
	BrandID int,
    primary key(ProductID),
    foreign key(CategoryID) references Categories(CategoryID),
	foreign key(BrandID) references Brands(BrandID)
)

-- 1:n -> one customer can place many orders, an order can be placed by one customer
create table Orders(
	OrderID int NOT NULL,
    OrderDate datetime,
    CustomerID int,
    primary key (OrderID),
    foreign key(CustomerID) references Customers(CustomerID)
)

create table Reviews(
	ReviewID int NOT NULL,
    Rating float,
    ReviewText varchar(1000),
    ProductID int,
    CustomerID int,
    primary key(ReviewID),
    foreign key(ProductID) references Products(ProductID),
    foreign key(CustomerID) references Customers(CustomerID)
)

create table Employees(
	EmployeeID int NOT NULL,
    FirstName varchar(50),
    LastName varchar(50),
    Position varchar(50),
    primary key(EmployeeID)
)

create table EmployeeAttendance(
	AttendanceID int NOT NULL,
    EmployeeID int,
    WorkedHours int,
    primary key(AttendanceID),
    foreign key(EmployeeID) references Employees(EmployeeID)

)

create table MakeUpAppointments(
	AppointmentID int NOT NULL,
    AppointmentDate timestamp,
    EmployeeID int,
    CustomerID int,
    primary key(AppointmentID),
    foreign key(EmployeeID) references Employees(EmployeeID),
    foreign key(CustomerID) references Customers(CustomerID)
    
)

-- m:n -> customers can have multiple favorite products, products can be favored by multiple customers
create table CustomersFavoriteProducts(
	CustomerID int,
    ProductID int,
    primary key(CustomerID, ProductID),
	foreign key(CustomerID) references Customers(CustomerID),
    foreign key(ProductID) references Products(ProductID)
)