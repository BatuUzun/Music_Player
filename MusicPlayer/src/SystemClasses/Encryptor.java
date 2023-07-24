package SystemClasses;

import java.util.Stack;

public class Encryptor {
	
	public static String encrypt(String password) {
		Stack<String> chars = new Stack<>(); char a = ' ';
		for(int i = 0; i < password.length();i++) {
			a = password.charAt(i);
			a = (char)((int)a+2);
			
			chars.add(String.valueOf(a));
		}
		String encryptedPasswordString = "";
		for(int i = 0; i<chars.size();i++) {
			encryptedPasswordString+=chars.pop();
		}
		
		return encryptedPasswordString;
	}
	
}