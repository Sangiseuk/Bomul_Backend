package com.example.bomul_backend.common.wrapper;

import com.example.bomul_backend.common.utils.CryptoUtils;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordWrapper {
    String password;
    String salt;

    public String encryptPassword(){
        if (password == null || salt == null) {
            throw new IllegalArgumentException("Password or salt cannot be null");
        }
        byte[] hashingPassword = CryptoUtils.getSHA256(password, salt);
        return CryptoUtils.byteArrayToHex(hashingPassword);
    }

}
