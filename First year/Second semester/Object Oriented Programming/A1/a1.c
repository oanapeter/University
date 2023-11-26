#include <stdio.h>
#include <string.h>
#include <math.h>
#include <stdbool.h>

bool check_if_prime(int number_to_check_if_prime) {
    int iterator;
    if (number_to_check_if_prime <= 1) {
        return false;
    }
    for (iterator = 2; iterator <= sqrt(number_to_check_if_prime); iterator++) {
        if (number_to_check_if_prime % iterator == 0) {
            return false;
        }
    }
    return true;
}

void longest_contiguous_subsequence_such_that_the_difference_of_any_two_consecutive_elements_is_a_prime_number(int array_of_numbers[], int number_of_elements_in_array)
{
    int maximum_length = 0;
    int starting_position = 0, ending_position = 0;
    int current_length = 1;
    int iterator;
    int difference_between_two_elements;
    for (iterator = 0; iterator < number_of_elements_in_array - 1; iterator++)
    {
        difference_between_two_elements = abs(array_of_numbers[iterator] - array_of_numbers[iterator + 1]);
        if (check_if_prime(difference_between_two_elements))
        {
            current_length = current_length + 1;
            if (current_length > maximum_length)
            {
                maximum_length = current_length;
                ending_position = iterator + 1;
                starting_position = ending_position - maximum_length + 1;
            }
        }
        else
        {
            current_length = 1;
        }
    }
    printf("The longest contiguous subsequence with prime number differences is: \n");
    for (iterator = starting_position; iterator <= ending_position; iterator++)
        printf("%d ", array_of_numbers[iterator]);
}

int main()
{
    int option;
    int precision;
    int iterator;
    int number_of_elements_in_array;
    int array_of_numbers[1000];
    int longest_array[1000];
    float number_to_find_the_square_root;
    float result;
    while (true)
    {
    printf("\n");
    printf("1. Compute the approximated value of square root of a positive real number. The precision is provided by the user.\n");
    printf("2. Given a vector of numbers, find the longest contiguous subsequence such that the difference of any two consecutive elements is a prime number.\n");
    printf("0. Exit.\n");
    printf("Choose your option: ");
    scanf("%d", &option);
        if (option == 1)
        {
            char precision[10];
            printf("Enter a positive real number: ");
            scanf("%f", &number_to_find_the_square_root);
            printf("Enter the precision: ");
            scanf("%s", precision);
            char format[] = "%.";
            strcat(format, precision);
            strcat(format, "f");
            result = sqrt(number_to_find_the_square_root);
            printf(format, result);

        }
        else if (option == 2)
        {
            printf("Enter the number of the elements in array: ");
            scanf("%d", &number_of_elements_in_array);
            printf("Enter the elements of the array: ");
            for (iterator = 0; iterator < number_of_elements_in_array; iterator++)
                scanf("%d", &array_of_numbers[iterator]);
            longest_contiguous_subsequence_such_that_the_difference_of_any_two_consecutive_elements_is_a_prime_number(array_of_numbers, number_of_elements_in_array);


        }
        else if (option == 0)
        {
            return;
        }
    }

    char stop[10];
    scanf("%s", &stop);

}