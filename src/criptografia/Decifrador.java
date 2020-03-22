package criptografia;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decifrador {
	public byte[] decifra(PrivateKey pvk, byte[] textoCifrado, byte[] chaveCifrada)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, InvalidAlgorithmParameterException {
		byte[] textoDecifrado = null;

		// Decifra a chave simétrica com a chave privada
		Cipher rsacf = Cipher.getInstance("RSA");
		rsacf.init(Cipher.DECRYPT_MODE, pvk);
		byte[] chaveDecifrada = rsacf.doFinal(chaveCifrada);
		// Decifra o texto com a chave simétrica decifrada
		Cipher aescf = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec ivspec = new IvParameterSpec(new byte[16]);
		aescf.init(Cipher.DECRYPT_MODE, new SecretKeySpec(chaveDecifrada, "AES"), ivspec);
		textoDecifrado = aescf.doFinal(textoCifrado);

		return textoDecifrado;
	}
}
