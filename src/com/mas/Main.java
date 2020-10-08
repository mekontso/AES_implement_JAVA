package com.mas;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static void main(String[] args) {

//        BigInteger securityCode = generateSecurityCode("08-09-2020 16:31:01");
//        System.out.println(securityCode);
        final String secretKey = "AAAAAAAAAA";
        String originalString = "1234";
        for(int i = 0; i < 20; i++){

            System.out.println("Product " +i+"/50000");
            System.out.println("Original product data: " +originalString);
            String encryptedString = AES.encrypt(originalString, secretKey);
            System.out.println("Encrypted data of product: " +encryptedString);
            String decryptedString = AES.decrypt(encryptedString, secretKey);
            System.out.println("Decrypted product data: "+decryptedString);
        }

    }

    public static BigInteger generateSecurityCode(String timestamp){

        BigInteger securityCode = BigInteger.ONE;
        for (int i = 0 ; i < timestamp.length() ; i++){
            if (Character.isDigit(timestamp.charAt(i))){

                int num = Character.getNumericValue(timestamp.charAt(i));
                  securityCode = securityCode.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(num));
            }
        }
        securityCode = securityCode.add(BigInteger.valueOf(ThreadLocalRandom.current().nextInt(1,9999999)));

        return securityCode;
    }

    public static String concatProdSerialDatetime(String serial){
        System.out.println("Generating product data");

        LocalDateTime myDateObj = LocalDateTime.now();
//        System.out.println(myDateObj);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println("Current time stamp including seconds "+formattedDate);
        System.out.println("Product security code:" + generateSecurityCode(formattedDate));

        return serial.concat(formattedDate);
    }
}
