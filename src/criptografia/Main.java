package criptografia;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {

		Helper helper = new Helper();
		Scanner sc = new Scanner(System.in);
		String input = null;
		String locale = null;
		String chave = null;

		do {
			System.out.println(
					"Selecione a op��o abaixo:\n1 - Gerar chaves de criptografia/descriptografia\n2 - Criptografar arquivo\n3 - Descriptografar arquivo");
			input = sc.next();

			if (input.equals("1")) {
				// Gera o par de chaves, em dois arquivos (chave.publica e chave.privada)
				GeradorParChaves gpc = new GeradorParChaves();
				gpc.geraParChaves(new File("chave.publica"), new File("chave.privada"));
			}

			if (input.equals("2")) {
				System.out.println("Informe o local do arquivo .txt que deve ser criptografado");
				locale = sc.next();
				String conteudo = helper.abrirArquivo(locale);
				System.out.println("O conte�do do txt � (sem criptografia): \n" + conteudo);

				byte[] textoClaro = conteudo.getBytes("ISO-8859-1");
				ChavePublica ccp = new ChavePublica();
				// Busca a chave p�blica gerada anteriormente
				PublicKey pub = ccp.carregaChavePublica(new File("chave.publica"));
				Cifrador cf = new Cifrador();
				byte[][] cifrado = cf.cifra(pub, textoClaro);
				// Criptografa o conte�do do arquivo
				helper.criarConteudoCriptografado(cifrado[0]);
				helper.criarChaveCriptografado(cifrado[1]);
				System.out.println("*** Gerado arquivo .crypt com conte�do criptografado ***");
			}

			if (input.equals("3")) {
				System.out.println("Informe o local do arquivo .txt (arquivo criptografado)");
				locale = sc.next();

				System.out.println("Informe o local da chave para descriptografar o arquivo");
				chave = sc.next();

				// Busca a chave privada
				ChavePrivada ccpv = new ChavePrivada();
				PrivateKey pvk = ccpv.carregaChavePrivada(new File("chave.privada"));
				Decifrador dcf = new Decifrador();

				String conteudoCriptografado = helper.abrirArquivo(locale);
				String chaveCriptografada = helper.abrirArquivo(chave);

				// Descriptografa o arquivo
				byte[] decifrado = dcf.decifra(pvk, conteudoCriptografado.getBytes("ISO-8859-1"),
						chaveCriptografada.getBytes("ISO-8859-1"));
				helper.criarArquivoDescriptografado(decifrado);
				System.out.println("O conte�do do arquivo descriptografado �: \n" + new String(decifrado, "ISO-8859-1"));
				System.out.println("*** Gerado arquivo .txt com conte�do descriptografado ***");
			}

		} while (!input.equals("0"));
		
		System.out.println("Equipe: Felipe Reblin, Sabrina Diehl e Larissa Censi\nSeguran�a e Auditoria de Sistemas de Info.");
	}
}