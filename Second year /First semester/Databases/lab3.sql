
go
-- a. modify the type of a column
create or alter procedure modifyPhoneNumber as
	alter table Customers alter column Phone varchar(11)



go
-- revert
create or alter procedure modifyBackPhoneNumber as
	alter table Customers alter column Phone int



go
-- b. add a column
create or alter procedure addBonusPoints as
	alter table Customers add BonusPoints int



go
-- revert
create or alter procedure removeBonusPoints as
	alter table Customers drop BonusPoints



go 
-- c. add default constraint
create or alter procedure addDefaultRating as
	alter table Reviews add constraint DefaultRating default(10) for Rating



go
-- revert
create or alter procedure removeDefaultRating as
	alter table Reviews drop constraint DefaultRating



go
-- g. create a table
create or alter procedure addTable as
	create table FidelityCards(
		CardID varchar(10),
        CardType varchar(10),
        CustomerID int,
        Phone int,
        BonusPoints int
        )




go
-- revert
create or alter procedure deleteTable as
	drop table FidelityCards



go
-- d. add a primary key
create or alter procedure addPrimaryKey as
	alter table FidelityCards add constraint PK_FidelityCards_CardID primary key (CardID)



go
-- revert
create or alter procedure removePrimaryKey as

	alter table FidelityCards drop constraint PK_FidelityCards_CardID



go
-- e. add candidate key
create or alter procedure addCandidateKey as 
	alter table FidelityCards add constraint CK_FidelityCards_Phone unique (Phone)



go
-- revert
create or alter procedure removeCandidateKey as
	alter table FidelityCards drop constraint CK_FidelityCards_Phone



go
-- f. add a foreign key
create or alter procedure addForeignKey as
	alter table FidelityCards add constraint FK_FidelityCards_CustomerID foreign key (CustomerID) references Customers(CustomerID)


go
-- revert
create or alter procedure removeForeignKey as
	alter table FidelityCards drop constraint FK_FidelityCards_CustomerID

go
create table CurrentVersionTable(
	CurrentVersion int
)
    
insert into CurrentVersionTable(CurrentVersion) values (1)

create table VersionAndProcedureTable(
	InitialVersion int,
    FinalVersion int,
    ProcedureName varchar(100),
    primary key(InitialVersion, FinalVersion, ProcedureName)
);

insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (1, 2, 'modifyPhoneNumber');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (2, 1, 'modifyBackPhoneNumber');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (2, 3, 'addBonusPoints');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (3, 2, 'removeBonusPoints');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (3, 4, 'addDefaultRating');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (4, 3, 'removeDefaultRating');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (4, 5, 'addTable');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (5, 4, 'deleteTable');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (5, 6, 'addPrimaryKey');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (6, 5, 'removePrimaryKey');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (6, 7, 'addCandidateKey');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (7, 6, 'removeCandidateKey');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (7, 8, 'addForeignKey');
insert into VersionAndProcedureTable(InitialVersion, FinalVersion, ProcedureName) values (8, 7, 'removeForeignKey');


go
create or alter procedure go_to_version(@new_version int) 
as
	declare @current_version int
	declare @procedure_name varchar(max)
	select @current_version = CurrentVersion from CurrentVersionTable

	if (@new_version > (select max(FinalVersion) from VersionAndProcedureTable) or @new_version < 1)
		raiserror ('Invalid version, try again!', 10, 1)
	else
	begin
		if @new_version = @current_version
			print('You are already on this version!');
		else
		begin
			if @current_version > @new_version			--if we want to go from a higher version to a smaller one
			begin
				while @current_version > @new_version 
					begin
						select @procedure_name = ProcedureName from VersionAndProcedureTable where InitialVersion = @current_version and FinalVersion = @current_version-1
						print('We are executing: ||' + @procedure_name + '|| to go in version -> ' +  cast(@current_version - 1 as varchar(10)) );
						exec (@procedure_name)
						set @current_version = @current_version - 1
					end
			end
			if @current_version < @new_version
			begin
				while @current_version < @new_version		--if we want to go from a smaller version to a higher one
					begin
						select @procedure_name = ProcedureName from VersionAndProcedureTable where InitialVersion = @current_version and FinalVersion = @current_version+1
						print('We are executing ||' + @procedure_name +  '|| to go in version -> ' +  cast(@current_version + 1 as varchar(10)) );
						exec (@procedure_name)
						set @current_version = @current_version + 1
					end
			end
			update CurrentVersionTable set CurrentVersion = @new_version
		end
	end

	exec go_to_version 5

	exec addBonusPoints
	exec modifyPhoneNumber