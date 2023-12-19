package security;

import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashCode {
    public static String generateHash(String password){
        try {
            String algorithm = "MD5";
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            digest.reset();
            byte[] hash = digest.digest(password.getBytes());
            return String.copyValueOf(Hex.encodeHex(hash));
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashCode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static boolean checkPassword(String password, String hash){
        return hash.equals(generateHash(password));
    }
}

