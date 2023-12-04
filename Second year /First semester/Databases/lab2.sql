use MakeUpShop;

insert into Categories(CategoryID, CategoryName) values (1, 'Face');
insert into Categories(CategoryID, CategoryName) values (2, 'Eyes');
insert into Categories(CategoryID, CategoryName) values (3, 'Eyebrows');
insert into Categories(CategoryID, CategoryName) values (4, 'Lips');

insert into Brands(BrandID, BrandName) values (1, 'Too Faced');
insert into Brands(BrandID, BrandName) values (2, 'Dior');
insert into Brands(BrandID, BrandName) values (3, 'Fenty');
insert into Brands(BrandID, BrandName) values (4, 'Rare Beauty');
insert into Brands(BrandID, BrandName) values (5, 'Kat Von D');
insert into Brands(BrandID, BrandName) values (6, 'Urban Decay');
insert into Brands(BrandID, BrandName) values (7, 'Tarte');
insert into Brands(BrandID, BrandName) values (8, 'Jeffree Star');

insert into Customers(CustomerID, FirstName, LastName, Phone) values (1, 'Pop', 'Mariana', 0745353453);
insert into Customers(CustomerID, FirstName, LastName, Phone) values (2, 'Peter', 'Oana', 0752920843);
insert into Customers(CustomerID, FirstName, LastName, Phone) values (3, 'Sabou', 'Crina', 0765734355);
insert into Customers(CustomerID, FirstName, LastName, Phone) values (4, 'Rechisan', 'Dana', 0732423424);
insert into Customers(CustomerID, FirstName, LastName, Phone) values (5, 'Janghina', 'Alex', 0763457564);
insert into Customers(CustomerID, FirstName, LastName, Phone) values (6, 'Micle', 'Daria', 0765482369);

insert into Products(ProductID, ProductName, CategoryID, BrandID) values (1, 'Lipliner', 4, 2);
insert into Products(ProductID, ProductName, CategoryID, BrandID) values (2, 'Foundation', 1, 2);
insert into Products(ProductID, ProductName, CategoryID, BrandID) values (3, 'Eye palette', 2, 1);
insert into Products(ProductID, ProductName, CategoryID, BrandID) values (4, 'Mascara', 2, 5);
insert into Products(ProductID, ProductName, CategoryID, BrandID) values (5, 'Blush', 1, 4);

insert into Orders (OrderID, OrderDate, CustomerID) values
    (1, '2023-11-01 10:00:00', 4),
    (2, '2023-11-02 11:30:00', 1),
    (3, '2023-11-03 14:45:00', 2);
insert into Orders (OrderID, OrderDate, CustomerID) values
    (4, '2023-10-12 10:00:00', 4),
    (5, '2023-09-13 11:30:00', 1),
    (6, '2023-11-03 14:45:00', 6);
    
insert into Reviews(ReviewID, Rating, ReviewText, ProductID, CustomerID) values
	(1, 5, 'Perfect', 5, 4),
    (2, 2.5, 'Not good not bad', 4, 3),
    (3, 1.4, 'Terrible', 5, 3);
    
insert into Reviews(ReviewID, Rating, ReviewText, ProductID, CustomerID) values
	(4, 2.1, 'Bad', 5, 4),
    (5, 4.7, 'The best', 1, 1);
    
insert into Employees(EmployeeID, FirstName, LastName, Position) values
	(1, 'Petrescu', 'Iulia', 'Manager'),
    (2, 'Mihai', 'Denisa', 'Cashier'),
    (3, 'Bica', 'Dora', 'Cashier'),
    (4, 'Popescu', 'Manuela', 'Manager');
insert into Employees(EmployeeID, FirstName, LastName, Position) values
    (5, 'Petre', 'Izabela', 'Makeup Artist'),
    (6, 'Moga', 'Denisa', 'Consultant'),
    (7, 'Goron', 'Violeta', 'Eyelash specialist');
    
insert into EmployeeAttendance(AttendanceID, EmployeeID, WorkedHours) values
	(1, 2, 8),
    (2, 1, 12),
    (3, 7, 16),
    (4, 3, 4);
    
insert into MakeUpAppointments(AppointmentID, AppointmentDate, EmployeeID, CustomerID) values
	(1, '2023-11-10 14:00:00', 5, 1),
    (2, '2023-11-12 11:30:00', 6, 2),
    (3, '2023-11-15 16:45:00', 7, 3),
    (4, '2023-11-16 13:30:00', 5, 4);

insert into CustomersFavoriteProducts(CustomerID, ProductID) values
	(1,1),
    (1,2),
    (2,2),
    (3,5);
    
-- statement that violates referential integrity constraints
insert into Products(ProductID, ProductName, CategoryID, BrandID) values (7, 'Eyeliner', 9, 4);

update Brands
set BrandName = 'MAC'
where BrandID = 5;

update Customers
set Phone = 0788888888
where Phone is not null and CustomerID >3;

update Reviews
set CustomerID = 3
where Rating<3;

delete from Brands
where BrandName in ('Tarte', 'Jeffree Star');

delete from Customers
where LastName like '%Alex';

select FirstName, LastName
from Customers
where CustomerID=1
union
select FirstName, LastName
from Employees
where EmployeeID <3;

select ProductID, Rating
from Reviews
where Rating > 2 or CustomerID = 4;

-- retrieve customers who placed an order and also left a review (intersect doesn't work)
select C.FirstName, C.LastName
from Customers C
inner join Orders O on C.CustomerID = O.CustomerID
inner join Reviews R on C.CustomerID = R.CustomerID;

-- retrieve the name of the employees who attended work and also have make-up appointments scheduled with clients
select E.FirstName, E.LastName
from Employees E
where E.EmployeeID in (
	select EA.EmployeeID
	from EmployeeAttendance EA
)
and E.EmployeeID in (
	select MA.EmployeeID
    from MakeUpAppointments MA
)order by E.FirstName, E.LastName;

-- find customers who placed orders but have not reviewed products using not in (except doesn't work)
select CustomerID, FirstName, LastName
from Customers
where CustomerID in (
	select O.CustomerID
    from Orders O
    where O.CustomerID not in (
		select distinct CustomerID
        from Reviews
        )
);

-- find employees who have attendance but don't have makeup appointments scheduled
select top 2 E.EmployeeID, E.FirstName, E.LastName
from Employees E
where E.EmployeeId in (
	select A.EmployeeID
    from EmployeeAttendance A
	where A.EmployeeID not in (
		select distinct EmployeeID
        from MakeUpAppointments
        )
)

-- display the customer's full name and the name of the favorite products
select C.FirstName, C.LastName, P.ProductName
from Customers C
inner join CustomersFavoriteProducts F on C.CustomerID = F.CustomerID
inner join Products P on F.ProductID = P.ProductID;

-- display customer's first name and the order id
select Customers.FirstName, Orders.OrderID
from Customers
left join Orders on Customers.CustomerID = Orders.CustomerID
order by Customers.FirstName;

-- display the employee's full name and the date of their scheduled appointments
select E.FirstName, E.LastName, MA.AppointmentDate
from Employees E
right join MakeUpAppointments MA on E.EmployeeID = MA.EmployeeID;

-- full join
select *
from Customers
left join Orders on Customers.CustomerID = Orders.CustomerID
union all
select *
from Customers
right join Orders on Customers.CustomerID = Orders.CustomerID;

select *
from EmployeeAttendance EA
where EA.EmployeeID in (
	select MA.EmployeeID
    from MakeUpAppointments MA
)order by EA.EmployeeID;


select *
from MakeUpAppointments MA
where MA.EmployeeID in (
	select E.EmployeeID
    from Employees E
    where EmployeeID in (
		select EA.EmployeeID
        from EmployeeAttendance EA
        where EA.WorkedHours>8
        )
);

select distinct C.FirstName, C.LastName
from Customers C
where exists (
	select *
    from Reviews R
    where R.CustomerID = C.CustomerID and R.Rating > 2
);

select distinct E.FirstName, E.LastName
from Employees E
where exists (
	select *
    from MakeUpAppointments MA
    where MA.EmployeeID = E.EmployeeID and MA.AppointmentDate<'2023-11-14 00:00:00'
);

select C.CustomerID, C.FirstName, C.LastName, R.ReviewText
from (select * from Reviews where ReviewText like '%d') as R, Customers C
where C.CustomerID = R.CustomerID;

select R.ReviewID, R.ReviewText, R.Rating
from( select avg(Rating) as AverageRating from Reviews) as A, Reviews R
where R.Rating>=AverageRating ;

select CategoryID, count(*) as ProductCount
from Products
group by CategoryID
having count(*) >= 2;

select ProductID, avg(Rating) as AverageRating
from Reviews
group by ProductID
having AverageRating >=3;

select EmployeeID, sum(WorkedHours) as Total
from EmployeeAttendance
group by EmployeeID
having Total > 10;

select CustomerID, count(*) as ProductsCount
from CustomersFavoriteProducts
group by CustomerID
having ProductsCount >= 2;

select ProductID
from Reviews
where Rating < all (select Rating from Reviews)
order by Rating;

select ProductID
from Reviews
where Rating < (select min(Rating) from Reviews)
order by Rating;

select CustomerID, FirstName, LastName
from Customers
where CustomerID = any ( select R.CustomerID from Reviews R);

select CustomerID, FirstName, LastName
from Customers
where CustomerID in ( select R.CustomerID from Reviews R);

select ProductID, Rating
from Reviews
where Rating < any (select Rating from Reviews)
order by Rating;

select ProductID, Rating
from Reviews
where Rating <  (select max(Rating) from Reviews)
order by Rating;

select CustomerID, FirstName, LastName
from Customers
where CustomerID <> all( select MA.CustomerID from MakeUpAppointments MA);

select CustomerID, FirstName, LastName
from Customers
where CustomerID not in ( select MA.CustomerID from MakeUpAppointments MA);