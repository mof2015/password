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

import sun.misc.BASE64Encoder;

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
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		keyGen.init(128);
		secretKey = keyGen.generateKey();
	}

	public static Encrypter getInstance() {
		return encrypter;
	}

	private String EncryptString(String plainData) {
		try {
			aesCipher = Cipher.getInstance("AES");
			aesCipher.init(Cipher.ENCRYPT_MODE, secretKey);
			byte[] byteDataToEncrypt = plainData.getBytes();
			System.out.println("byteDataToEncrypt="
					+ byteDataToEncrypt.toString());
			byteCipherText = aesCipher.doFinal(byteDataToEncrypt);
			System.out.println("byteCipherText=" + byteCipherText.toString());
			encodedEncryptedValue = new BASE64Encoder().encode(byteCipherText);
			System.out.println("encodedEncryptedValue Text="
					+ encodedEncryptedValue);
		} catch (NoSuchAlgorithmException e) {
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

	private String DeCryptEncryptedString(String encryptedString) {
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

	public static void main(String[] args) {
		String plainData = "hello How are You \n I am Fine", cipherText, decryptedText;
		Encrypter encrypterMain = Encrypter.getInstance();
		String encryptedValue = encrypterMain.EncryptString(plainData);
		System.out.println("__________________________________");
		System.out.println(plainData);
		System.out.println("_____");
		System.out.println("Encrypted to");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println(encryptedValue);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("Again Decrypted to ");
		String deCryptedValue = encrypterMain
				.DeCryptEncryptedString(encryptedValue);
		System.out.println(deCryptedValue);

	}

}
