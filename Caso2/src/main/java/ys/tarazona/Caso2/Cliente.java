package ys.tarazona.Caso2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.cert.CertificateException;

public class Cliente {

	public static final int PUERTO = 3400;
	public static final String SERVIDOR = "localhost";
	
	public static void main(String[] args) throws IOException{
		Socket socket = null;
		PrintWriter escritor = null;
		BufferedReader lector = null;
		
		System.out.println("Cliente ...");
		
		try {
			socket = new Socket(SERVIDOR, PUERTO);
			escritor = new PrintWriter(socket.getOutputStream(),true);
			lector = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			ProtocoloCliente.procesar(stdIn, lector, escritor);
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		stdIn.close();
		escritor.close();
		lector.close();
		socket.close();
	}
}