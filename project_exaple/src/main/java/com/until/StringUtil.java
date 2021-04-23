package com.until;

import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * 进制 -- String
 */
public class StringUtil {

    public static String base64Decoder(String encoderStr){
        //新建一个BASE64Decoder?
        BASE64Decoder decode = new BASE64Decoder();
        //将base64转换为byte[]
        byte[] b = new byte[0];
        try {
            b = decode.decodeBuffer(encoderStr);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        String msg = "";
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            msg += (hex.toUpperCase());
        }
        return msg;
    }

    //十六进制ASCII码转化String
    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for( int i=0; i<hex.length()-1; i+=2 ){

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char)decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

    public static boolean NOT_Blank(String str){
        if (!str.equals("") && str != null) {
            return true;
        }
        return false;
    }


}
