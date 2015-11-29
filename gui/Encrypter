package com.santosh.encryption;

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

import sun.misc.BASE64Encoder;
import java.lang.*;
import java.util.Base64;

public class Encrypter {

	static Encrypter encrypter = new Encrypter();
	String encodedEncryptedValue = "";
	Cipher aesCipher = null;
	SecretKey secretKey = null;
	KeyGenerator keyGen = null;
	byte[] byteCipherText = null;

	private Encrypter() {
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

	public static Encrypter getInstance() {
		return encrypter;
	}

	String EncryptString(String plainData) {
		try {
			aesCipher = Cipher.getInstance("AES");
//			System.out.println("aesCipher : "+aesCipher);
			aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] byteDataToEncrypt = plainData.getBytes();
/*			System.out.println("byteDataToEncrypt="
					+ byteDataToEncrypt.toString());
*/			byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
//			System.out.println("byteCipherText=" + byteCipherText.toString());
			encodedEncryptedValue = new BASE64Encoder().encode(byteCipherText);
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

	String DeCryptEncryptedString(String encryptedString) {
		byte[] byteDecryptedText = null;
		try {
			aesCipher.init(Cipher.DECRYPT_MODE, secretKey,
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

	String getSecretKey(){
		return Base64.getEncoder().encodeToString(secretKey.getEncoded());
	}
	void setSecretKey(String encodedKey){
		byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
		secretKey = new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");
	}
}
