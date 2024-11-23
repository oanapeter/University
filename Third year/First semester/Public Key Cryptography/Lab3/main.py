import math

def fermat(n, B):

    k = 1
    while True:
        if n % 2 == 0:
            return 2, n//2
        t0 = math.isqrt(k * n)

        for t in range(t0 + 1, t0 + B + 1):
            difference = t * t - k * n
            s = int(math.isqrt(difference))
            if s * s == difference:
                factor1 = t - s
                factor2 = t + s
                if factor1 % k == 0 or factor2 % k == 0:
                    if factor1 % k == 0:
                        factor1 = factor1 // k
                    else:
                        factor2 = factor2 // k

                    if factor1 != 1 and factor1 != n:
                        return factor1, factor2
        k += 1

n = 141467
B = 5
result = fermat(n, B)

if result:
    print("Factorii sunt: ", result)
else:
    print("Nu s-a gasit niciun factor")