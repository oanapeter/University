import numpy as np

def char_to_num(char):
    return ord(char.upper()) - ord('A')

def num_to_char(num):
    return chr(num % 26 + ord('A'))


# Encrypt a pair of characters using Hill cipher for m=2
def hill_cipher_encrypt(plaintext, key_matrix):
    if len(plaintext) % 2 != 0:
        plaintext += 'X'  # Padding in case of odd length

    ciphertext = ''
    for i in range(0, len(plaintext), 2):
        # Convert pair of characters to a vector
        pair = np.array([char_to_num(plaintext[i]), char_to_num(plaintext[i + 1])])

        # Multiply key matrix with plaintext vector
        encrypted_pair = np.dot(key_matrix, pair) % 26

        # Convert the result back to characters
        ciphertext += num_to_char(encrypted_pair[0]) + num_to_char(encrypted_pair[1])

    return ciphertext


# Decrypt a pair of characters using Hill cipher for m=2
def hill_cipher_decrypt(ciphertext, key_matrix):
    # Compute the inverse of the key matrix mod 26
    determinant = int(np.round(np.linalg.det(key_matrix)))  # Determinant of the key matrix
    determinant_inv = pow(determinant, -1, 26)  # Modular inverse of determinant mod 26
    key_matrix_inv = determinant_inv * np.round(determinant * np.linalg.inv(key_matrix)).astype(int) % 26

    plaintext = ''
    for i in range(0, len(ciphertext), 2):
        # Convert pair of characters to a vector
        pair = np.array([char_to_num(ciphertext[i]), char_to_num(ciphertext[i + 1])])

        # Multiply inverse key matrix with ciphertext vector
        decrypted_pair = np.dot(key_matrix_inv, pair) % 26

        # Convert the result back to characters
        plaintext += num_to_char(decrypted_pair[0]) + num_to_char(decrypted_pair[1])

    return plaintext


key_matrix = np.array([[11, 8], [3, 7]])

plaintext = "OANA"
ciphertext = hill_cipher_encrypt(plaintext, key_matrix)
decrypted_text = hill_cipher_decrypt(ciphertext, key_matrix)

print("Plaintext:", plaintext)
print("Ciphertext:", ciphertext)
print("Decrypted text:", decrypted_text)
