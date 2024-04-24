package databaseInfo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Help {
	//sss
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
	
	
	public static void main(String args[]) {
		//Crude crude = new Crude();
		//crude.create("user","Marko","mrakomail@mail.com","1231234","nema");
		//crude.create("grupa","Fizika","plava","1");
		//crude.create("subgroup","kinematika","smeda","2");
		//crude.create("card","naslov","odlomak","bijela","2");
		//crude.delete("grupa", "name", "Fizika"); //izbrise podgrupu kinematika
		//crude.update("user", "username", "Marko", "Mitar");
		//crude.create("user","Njofra","mraksail@mail.com","1234","nema");
		//String proba;
		//System.out.println(proba=Help.encrypt("1234")); 
		//crude.update("user","password","1234","12345"); // sve je ovo stestirano i radi
		//sproban unique constraint na korisnickome imenu i radi
		//brisanjem korisnika ili grupe kaskadno se brisu podaci (cascade constraint) 
		////sprobano brisanje grupe pa se izbrisu podgrupe i sprobano brisanje usera i brisu se grupe i podgrupe (radi)
		//enkripcija napravljena na principu md5 hashinga
		//crude.closeConnection();
	}

}
