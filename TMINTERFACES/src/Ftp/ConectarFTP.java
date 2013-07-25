package Ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FileTransferClient;

public class ConectarFTP {
	
	private FileTransferClient connect() throws FTPException, IOException {
		 
        String server = "192.168.1.101";
        int port = 21;
        String user = "administrator";
        String password = "administrator";
 
        FileTransferClient ftp = new FileTransferClient();
 
        ftp.setRemoteHost(server);
        ftp.setRemotePort(port);
        ftp.setUserName(user);
        ftp.setPassword(password);
                 
        ftp.connect();
 
        return ftp;
}
	
	
	
	private boolean downloadFileByFTP(String server, String user, String pass, String localPath, String remotePath) {
		try {
		URL url = new URL("ftp://" + user + ":" + pass + "@" + server + remotePath + ";type=i");
		URLConnection urlc = url.openConnection();
		InputStream is = urlc.getInputStream();
		BufferedWriter bw = new BufferedWriter(new FileWriter(localPath));
		int c;
		while ((c = is.read()) != -1) {
		bw.write(c);
		}
		is.close();
		bw.flush();
		bw.close();
		return true;
		} catch (Exception ex) {
		ex.printStackTrace();
		System.out.println(ex.getMessage());
		return false;
		}
		}
	
	
	private boolean uploadFileByFTP(String server, String user, String pass, String localPath, String remotePath) {
		try {
		URL url = new URL("ftp://" + user + ":" + pass + "@" + server + remotePath + ";type=i");
		URLConnection urlc = url.openConnection();
		OutputStream os = urlc.getOutputStream();
		BufferedReader br = new BufferedReader(new FileReader(localPath));
		int c;
		while ((c = br.read()) != -1) {
		os.write(c);
		}
		os.flush();
		os.close();
		br.close();
		return true;
		} catch (Exception ex) {
		ex.printStackTrace();
		return false;
		}
		}

	
	public static void  pdescargarctocktoftptm(){
		try {
			ConectarFTP descargar= new ConectarFTP();
			descargar.downloadFileByFTP("192.168.1.101", "cstock", "cstock", "D:\\vcaperu\\proyectos\\fuentes\\TMINTERFACES\\FTPTEMP\\BODTXT20130705.txt", "/FTP_CSTOCK/BODTXT20130705.txt");
			System.out.println("Se descargo Correctamente ..");
			ConectarFTP cargar= new ConectarFTP();
			cargar.uploadFileByFTP("192.168.1.101", "sfslftp105", "sfslftp105", "D:\\vcaperu\\proyectos\\fuentes\\TMINTERFACES\\FTPTEMP\\BODTXT20130705.txt", "/FTP_SFSLFTP105/BODTXT20130705.txt");
			System.out.println("Se cargo Correctamente ..");
//			File fichero = new File("D:\\vcaperu\\proyectos\\fuentes\\TMINTERFACES\\FTPTEMP\\BODTXT20130705.txt");
//			if (fichero.delete())
//				   System.out.println("El fichero ha sido borrado satisfactoriamente");
//				else
//				   System.out.println("El fichero no puede ser borrado");
			
												
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No conecta");
			
		}
		
		
	}
}
	
