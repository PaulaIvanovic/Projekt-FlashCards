package databaseInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class PasswordEncryption {
	public static String encrypt(String pw) {
		   try {
	            // Create MessageDigest instance for MD5
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            // Add input bytes to digest
	            md.update(pw.getBytes());
	            // Get the hash's bytes
	            byte[] bytes = md.digest();
	            // Convert byte array to hex string
	            StringBuilder sb = new StringBuilder();
	            for (byte aByte : bytes) {
	                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
	            }
	            // Return the hexadecimal string
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	}
}


