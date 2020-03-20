package criptografia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.spec.RSAKeyGenParameterSpec;

public class GeradorParChaves {
	private static final int RSAKEYSIZE = 1024;

	public void geraParChaves(File fPub, File fPvk) throws IOException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, CertificateException, KeyStoreException {

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(new RSAKeyGenParameterSpec(RSAKEYSIZE, RSAKeyGenParameterSpec.F4));
		KeyPair kpr = kpg.generateKeyPair();
		PrivateKey priv = kpr.getPrivate();
		PublicKey pub = kpr.getPublic();

		// Gravando a chave pública em formato serializado
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fPub));
		oos.writeObject(pub);
		System.out.println("*** Chave pública gerada ***");
		oos.close();

		// Gravando a chave privada em formato serializado
		oos = new ObjectOutputStream(new FileOutputStream(fPvk));
		oos.writeObject(priv);
		System.out.println("*** Chave privada gerada ***");
		oos.close();

	}
}
