/*
 *
 */
package me.melvins.labs;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Mels
 */
public class End {

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException {

        String readFilePath = "D:\\Workshop\\IntelliJ\\secure-extraction-project\\enc.txt";
        BufferedReader br = new BufferedReader(new FileReader(new File(readFilePath)));

        String writeFilePath = "D:\\Workshop\\IntelliJ\\secure-extraction-project\\Write.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(writeFilePath)));

        String line;
        while ((line = br.readLine()) != null) {

            String encryptedLine = App.decrypt("AES", "MyBestSecretKeys", line);

            bw.write(encryptedLine);
            bw.newLine();
        }

        bw.flush();
    }


}
