package criptografia;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;

public class ChavePrivada {
	public PrivateKey carregaChavePrivada(File fPvk) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fPvk));
		PrivateKey ret = (PrivateKey) ois.readObject();
		ois.close();
		return ret;
	}
}
