//#include <stdio.h>
//#include <string.h>
//
//struct Estate{
//	char address[100];
//	char type[10];
//	int surface;
//	int price;
//} ;
//
//void add_estate(struct Estate* array_of_estates, int* length_of_the_array)
//{
//	struct Estate new_estate;
//	printf("Enter the estate address: ");
//	//scanf("%s", new_estate.address);
//	gets(new_estate.address);
//	gets(new_estate.address);
//	//fgets(new_estate.address, 100, stdin);
//	//getchar();
//	//fflush(stdin);
//	printf("Enter the estate type (house, apartment, or penthouse): ");
//	scanf("%s", new_estate.type);
//	//getchar();
//	//fgets(new_estate.type, 20, stdin);
//	printf("Enter the estate surface: ");
//	scanf("%d", &new_estate.surface);
//	printf("Enter the estate price: ");
//	scanf("%d", &new_estate.price);
//
//	for (int i = 0; i < *length_of_the_array; i++) {
//		if (strcmp(array_of_estates[i].address, new_estate.address) == 0) {
//			printf("An estate with the same address already exists.\n");
//			return;
//		}
//	}
//	array_of_estates[*length_of_the_array] = new_estate;
//	(*length_of_the_array)++;
//
//}
//
//void delete_estate(struct Estate* array_of_estates, int* length_of_the_array)
//{
//	char address_to_delete[50];
//	printf("\nEnter the address of the estate to be deleted: ");
//	gets(address_to_delete);
//	gets(address_to_delete);
//	for (int index = 0; index < *length_of_the_array; index++) {
//		if (strcmp(array_of_estates[index].address, address_to_delete) == 0)
//		{
//
//			for (int j = index; j < *length_of_the_array - 1; j++) {
//				array_of_estates[j] = array_of_estates[j + 1];
//			}
//			(*length_of_the_array)--;
//			printf("Estate deleted successfully.\n");
//		}
//	}
//
//}
//void update_estate(struct Estate* array_of_estates, int* length_of_the_array)
//{
//	char address[50];
//
//	printf("\nEnter the address of the estate to be updated: ");
//	gets(address);
//	gets(address);
//
//	for (int i = 0; i < *length_of_the_array; i++) {
//		if (strcmp(address, array_of_estates[i].address) == 0) {
//			printf("Enter the new type (house/apartment/penthouse): ");
//			scanf("%s", &array_of_estates[i].type);
//			printf("Enter the new surface: ");
//			scanf("%d", &array_of_estates[i].surface);
//			printf("Enter the new price: ");
//			scanf("%d", &array_of_estates[i].price);
//
//			printf("Estate updated successfully.\n");
//			return;
//		}
//	}
//
//	printf("Estate not found!\n");
//}
//
//void display_estates_containing_a_given_string(struct Estate* array_of_estates, int* length_of_the_array)
//{
//	struct Estate auxiliar;
//	for (int i = 0; i< length_of_the_array - 1; i++)
//		for (int j = i+1; j<length_of_the_array; j++)
//			if (array_of_estates[i].price > array_of_estates[j].price)
//			{
//				auxiliar = array_of_estates[i];
//				array_of_estates[i] = array_of_estates[j];
//				array_of_estates[j] = auxiliar;
//			}
//	char given_string[20];
//	printf("Please enter the string: ");
//	//scanf("%s", given_string);
//	gets(given_string);
//	gets(given_string);
//	for (int i = 0; i < length_of_the_array; i++)
//	{
//		if (strstr(array_of_estates[i].address, given_string) != NULL)
//			printf("Address: %s\nType: %s\nSurface: %d\nPrice: %d\n", array_of_estates[i].address, array_of_estates[i].type, array_of_estates[i].surface, array_of_estates[i].price);
//
//	}
//
//}
//int main()
//{
//	struct Estate array_of_estates[100];
//	int length_of_the_array = 0;
//	
//	while (1) 
//	{
//	printf("Choose an option: \n");
//	printf("(a) Add, delete or update an estate. An estate is uniquely identified by its address.\n");
//	printf("(b) Display all estates whose address contains a given string.\n");
//	printf("Enter your option: ");
//	char option[2];
//	scanf("%s", &option);
//		if (strcmp(option, "a") == 0)
//		{
//			int option2;
//			printf("1. Add\n");
//			printf("2. Delete\n");
//			printf("3. Update\n");
//			scanf("%d", &option2);
//			if (option2 == 1)
//			{
//				add_estate(array_of_estates, &length_of_the_array);
//			}
//			else if (option2 == 2)
//			{
//				delete_estate(array_of_estates, &length_of_the_array);
//			}
//			else if (option2 == 3)
//			{
//				update_estate(array_of_estates, &length_of_the_array);
//			}
//			else if (option2 == 4)
//			{
//				for (int i = 0; i < length_of_the_array; i++)
//					printf("%s\n%s\n%d\n%d\n", array_of_estates[i].address, array_of_estates[i].type, array_of_estates[i].surface, array_of_estates[i].price);
//
//			}
//		}
//		else if (strcmp(option, "b") == 0)
//		{
//			display_estates_containing_a_given_string(array_of_estates, length_of_the_array);
//		}
//	}
//	char st[2];
//	scanf("%s", &st);
//}