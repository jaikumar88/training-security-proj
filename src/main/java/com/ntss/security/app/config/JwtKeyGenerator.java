package com.ntss.security.app.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        // Generate a secure 256-bit (32-byte) key for HS256
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        
        // Print the Base64-encoded version of the secret key (for storage)
        String base64EncodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println("Generated JWT Secret Key (Base64): " + base64EncodedKey);
    }
}
