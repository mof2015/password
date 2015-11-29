package db_connec_test;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import java.lang.*;
import java.util.Base64;

public class Encryptor {

	static Encryptor encryptor = new Encryptor();
	String encodedEncryptedValue = "";
	Cipher aesCipher = null;
	SecretKey secretKey = null;
	KeyGenerator keyGen = null;
	byte[] byteCipherText = null;

	public Encryptor() {
		try {
			keyGen = KeyGenerator.getInstance("AES");
//			System.out.println("keygen = "+keyGen);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		keyGen.init(128);
		secretKey = keyGen.generateKey();
//		System.out.println("secretKey = "+secretKey);
	}
	public SecretKey getKey()
	{
		return secretKey;		
	}

	public static Encryptor getInstance() {
		return encryptor;
	}

	String EncryptString(String plainData, SecretKey key) {
		try {
			aesCipher = Cipher.getInstance("AES");	
//			System.out.println("aesCipher : "+aesCipher);
			aesCipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] byteDataToEncrypt = plainData.getBytes();
/*			System.out.println("byteDataToEncrypt="
					+ byteDataToEncrypt.toString());
*/			byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
//			System.out.println("byteCipherText=" + byteCipherText.toString());
			encodedEncryptedValue = Base64.getEncoder().encodeToString(byteCipherText);
/*			System.out.println("encodedEncryptedValue Text="
					+ encodedEncryptedValue);
*/		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encodedEncryptedValue;
	}

	String DeCryptEncryptedString(String encryptedString, SecretKey key) {
		byte[] byteDecryptedText = null;
		try {
			aesCipher.init(Cipher.DECRYPT_MODE, key,
					aesCipher.getParameters());
			byteDecryptedText = aesCipher.doFinal(byteCipherText);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		encryptedString = new String(byteDecryptedText);
		return encryptedString;
	}

	String getSecretKey(SecretKey key){
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	void setSecretKey(String encodedKey){
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		secretKey = new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");
	}
}
