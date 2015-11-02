import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;

import javax.crypto.Cipher;

/*RSA를 이용해 비밀번호를 encrypt/decrypt할 때 어떻게 public/private key를 만들고 그것을 암호화 및 복호화하는지
 *예시를 보여주는 함수
 */

public class TestRSA {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		try {

			// RSA 공개키/개인키를 생성한다.
			System.out.println("Create public key / private key");
			KeyPairGenerator clsKeyPairGenerator = KeyPairGenerator.getInstance("RSA");

			clsKeyPairGenerator.initialize(2048);



			KeyPair clsKeyPair = clsKeyPairGenerator.genKeyPair();

			Key clsPublicKey = clsKeyPair.getPublic();

			Key clsPrivateKey = clsKeyPair.getPrivate();

			KeyFactory fact = KeyFactory.getInstance("RSA");

			RSAPublicKeySpec clsPublicKeySpec = fact.getKeySpec(clsPublicKey, RSAPublicKeySpec.class);

			RSAPrivateKeySpec clsPrivateKeySpec = fact.getKeySpec(clsPrivateKey, RSAPrivateKeySpec.class);

			System.out.println("public key modulus(" + clsPublicKeySpec.getModulus() + ") exponent(" + clsPublicKeySpec.getPublicExponent() + ")");

			System.out.println("private key modulus(" + clsPrivateKeySpec.getModulus() + ") exponent(" + clsPrivateKeySpec.getPrivateExponent() + ")");



			// 암호화 한다.

			String strPinNumber = new String();
			System.out.println("Enter string: ");
			strPinNumber = input.nextLine();


			Cipher clsCipher = Cipher.getInstance("RSA");

			clsCipher.init(Cipher.ENCRYPT_MODE, clsPublicKey);

			byte[] arrCipherData = clsCipher.doFinal(strPinNumber.getBytes());

			String strCipher = new String(arrCipherData);

			System.out.println("cipher(" + strCipher + ")");



			// 복호화 한다.

			clsCipher.init(Cipher.DECRYPT_MODE, clsPrivateKey);

			byte[] arrData = clsCipher.doFinal(arrCipherData);



			String strResult = new String(arrData);

			System.out.println("result(" + strResult + ")");

		} catch (Exception e) {



		}

	}

}
