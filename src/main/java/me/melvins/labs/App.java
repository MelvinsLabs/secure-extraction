/*
 *
 */
package me.melvins.labs;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

/**
 * @author Mels
 */
public class App {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {

        String algorithm = "AES";

        String secret = "MyBestSecretKeys";

        String encodedData = encrypt(algorithm, secret, new String("MyData"));

        System.out.println(encodedData);

        String decryptedValue = decrypt(algorithm, secret, encodedData);

        System.out.println(decryptedValue);
    }

    /**
     * <p>
     * Initialize a {@code Cipher} with {@code algorithm} to encrypt the data using the {@code secretInBytes}. <br>
     * Encrypt the Data using the {@code Cipher}.
     * Encode the encrypted data using {@code BASE64Encoder}.
     * </p>
     *
     * @param algorithm The algorithm used for encryption.
     * @param secret    The Secret for decryption.
     * @param data
     * @return The encrypted data.
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String encrypt(String algorithm, String secret, String data) throws NoSuchAlgorithmException,
            NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] secretInBytes = secret.getBytes();

        // Step 1
        Key key = new SecretKeySpec(secretInBytes, algorithm);

        Cipher encryptCipher = Cipher.getInstance(algorithm);

        encryptCipher.init(Cipher.ENCRYPT_MODE, key);

        // Step 2
        byte[] encryptedData = encryptCipher.doFinal(data.getBytes());

        // Step 3
        return new BASE64Encoder().encode(encryptedData);
    }

    /**
     * <p>
     * Decode the encoded Data first using {@code BASE64Decoder}. <br>
     * Initialize a {@code Cipher} with {@code algorithm} to decrypt the decoded Data using the {@code secretInBytes}
     * . <br>
     * Use the {@code Cipher} to decrypt.
     * </p>
     *
     * @param algorithm   The algorithm used for decryption.
     * @param secret      The Secret for decryption.
     * @param encodedData The encoded data which needs to be decoded and decrypted.
     * @return The decrypted data.
     * @throws IOException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    public static String decrypt(String algorithm, String secret, String encodedData) throws IOException,
            NoSuchAlgorithmException,
            NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        byte[] secretInBytes = secret.getBytes();

        // Step 1
        byte[] decodedData = new BASE64Decoder().decodeBuffer(encodedData);

        // Step 2
        Key key = new SecretKeySpec(secretInBytes, algorithm);

        Cipher decryptCipher = Cipher.getInstance(algorithm);

        decryptCipher.init(Cipher.DECRYPT_MODE, key);

        // Step 3
        byte[] decryptedData = decryptCipher.doFinal(decodedData);

        return new String(decryptedData);
    }

}
