package com.example.bomul_backend.common.utils;

import java.security.MessageDigest;

public class CryptoUtils {
    private CryptoUtils() {
        throw new UnsupportedOperationException("Cannot be instantiated");
    }

    public static byte[] getSHA256(String source, String salt){
        byte[] byteData = null;
        try{
            MessageDigest md = MessageDigest.GetInstance("SHA-256");
            md.update(source.getBytes());
            md.update(salt.getBytes());
            byteData=md.
        }
    }

}
