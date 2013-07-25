package Ftp;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class EjecutarInterface {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws SQLException, IOException {
		
      ConectarFTP.pdescargarctocktoftptm();
      CargarArchivo.pcargarBodegasFTP();
		String rs=InsertarTablasMaximo.pvalidar_registrarMaximo();
					
     if(rs.split("-")[0].equalsIgnoreCase("ok")){
    	 System.out.println("ok");
     }else{
    	 
    	 System.out.println("con log");
    	 logger loger = new logger("D:\\vcaperu\\proyectos\\fuentes\\TMINTERFACES\\Log\\");
         Logger log = loger.getLog();
         
         log.debug(rs);
    }
     System.out.println("Proceso Realizado");
    	 
    
		
	}

}
