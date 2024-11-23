import datetime
import time


def gcd_brute_force(x, y):
    """
    This function calculates the greatest common divisor (GCD) by checking each number from the minimum of x and y down to 1
    to see if both numbers are divisible by it.
    """
    if x == 0 or x == y:
        return y
    elif y == 0:
        return x
    greatest_common_divisor = min(x, y)
    while x % greatest_common_divisor != 0 or y % greatest_common_divisor != 0:
        greatest_common_divisor -= 1
    return greatest_common_divisor


def gcd_euclidean_subtractions(x, y):
    """
    This function uses the Euclidean algorithm by repeatedly subtracting the smaller number from the larger one
    until both numbers are equal, at which point the GCD is found.
    """
    if x == 0 or x == y:
        return y
    elif y == 0:
        return x
    while x != y:
        if x < y:
            y -= x
        else:
            x -= y
    return x


def gcd_euclidean_divisions(x, y):
    """
    This function implements the Euclidean algorithm recursively, using division.
    It finds the GCD by continuously taking the remainder until one of the numbers becomes zero.
    """
    if x == 0 or x == y:
        return y
    elif y == 0:
        return x
    return gcd_euclidean_divisions(y % x, x)


# Entry point of the script
if __name__ == '__main__':
    test_cases = [
        (5, 0),
        (18, 12),
        (30, 17),
        (256, 14),
        (17, 19),
        (17, 34),
        (4137524, 1227244),
        (294733, 10383680172),
        (2 ** 50, 4 ** 20),
        (17 ** 23, 17 ** 16),
        (182364, 116033),
        (70004, 43602),
        (106626666, 783764444)
    ]

    for x, y in test_cases:
        print(f"\nTesting GCD for x = {x}, y = {y}")

        # Brute Force Method
        print("Running Brute Force GCD...")
        start_time = time.perf_counter()
        gcd = gcd_brute_force(x, y)
        end_time = time.perf_counter()
        print(f"Brute Force GCD: {gcd} | Time taken: {end_time - start_time:.6f} seconds")

        # Euclidean Method (Subtractions)
        print("Running Euclidean GCD using Subtractions...")
        start_time = time.perf_counter()
        gcd = gcd_euclidean_subtractions(x, y)
        end_time = time.perf_counter()
        print(f"Euclidean (Subtractions) GCD: {gcd} | Time taken: {end_time - start_time:.6f} seconds")

        # Euclidean Method (Divisions)
        print("Running Euclidean GCD using Divisions...")
        start_time = time.perf_counter()
        gcd = gcd_euclidean_divisions(x, y)
        end_time = time.perf_counter()
        print(f"Euclidean (Divisions) GCD: {gcd} | Time taken: {end_time - start_time:.6f} seconds")

        print("--------------------------------------------------------------------------")
