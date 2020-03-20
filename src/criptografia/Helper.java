package criptografia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class Helper {

	public String abrirArquivo(String nomeArquivo) throws IOException {

		File file = new File(nomeArquivo);
		byte[] bytes = Files.readAllBytes(file.toPath());
		String textoDoArquivo = new String(bytes, "ISO-8859-1");
		return textoDoArquivo;
	}

	public void criarConteudoCriptografado(byte[] conteudo) throws IOException {
		// Cria arquivo
		File file = new File("conteudo_encriptografado.crypt");

		// Se o arquivo nao existir, ele gera
		if (!file.exists()) {
			file.createNewFile();
		}

		try {
			OutputStream out = new FileOutputStream(file);
			out.write(conteudo);
			out.close();
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void criarChaveCriptografado(byte[] chave) throws IOException {
		// Cria arquivo
		File file = new File("encriptografado_chave.txt");

		// Se o arquivo nao existir, ele gera
		if (!file.exists()) {
			file.createNewFile();
		}

		try {
			OutputStream out = new FileOutputStream(file);
			out.write(chave);
			out.close();
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
	

	public void criarArquivoDescriptografado(byte[] conteudo) throws IOException {
		// Cria arquivo
		File file = new File("descriptografado.txt");

		// Se o arquivo nao existir, ele gera
		if (!file.exists()) {
			file.createNewFile();
		}

		try {
			OutputStream out = new FileOutputStream(file);
			out.write(conteudo);
			out.close();
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
