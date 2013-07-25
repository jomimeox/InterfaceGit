package Ftp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexion.OracleConexion;

public class CargarArchivo {

	public static void pcargarBodegasFTP() throws SQLException {
	        Connection cn=null;
	        PreparedStatement  pst=null;
	        try{
	        // Abrimos el archivo
	        FileInputStream fstream = new FileInputStream("D:\\vcaperu\\proyectos\\fuentes\\TMINTERFACES\\FTPTEMP\\BODTXT20130705.TXT");
	        // Creamos el objeto de entrada
	        DataInputStream entrada = new DataInputStream(fstream);
	        // Creamos el Buffer de Lectura
	        BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
	        String strLinea;
	        cn=OracleConexion.obtenerConexion();
	        cn.setAutoCommit(false);
	        String sql="insert into TM_INT_FTP_BODEGA (TM_INT_BODEGAID,LOCALIDAD,SUBLOCALIDAD,BODEGA,DESCRIPTION,ESTADO, DIRECCION,HASLD,ROWSTAMP)";
	        sql+="values(?,?,?,?,?,?,?,0,MAXSEQ.NEXTVAL)";
	        pst=cn.prepareStatement(sql);
	       
	        int cont=0;
	        int dato=0;
	        while ((strLinea = buffer.readLine()) != null)   {
	              String P1= strLinea.substring(0,2).trim();
	              String P2 = strLinea.substring(2,4).trim();
	              String P3 = strLinea.substring(4,9).trim();
	              String P4 = strLinea.substring(9,49).trim();
	              String P5 = strLinea.substring(54,55).trim();
	              String P6 = strLinea.substring(55,strLinea.length()).trim();
	              
//	              System.out.print ("\tCodigo de Codigo de Localidad : "+P1);
//	              System.out.print ("\tCodigo de Codigo de SubLocalidad : "+P2);
//	              System.out.print ("\tCodigo de Codigo de Bodega : "+P3);
//	              System.out.print ("\tCodigo de Nombre Bodega : "+P4);
//	              System.out.print ("\tCodigo de Estado : "+P5);
//	              System.out.print ("\tCodigo de Direccion : "+P6);
//	              System.out.println("");

	              
	              dato++;
	              pst.setInt(1,dato);          		
	              pst.setString(2, P1);
	              pst.setString(3, P2);
	              pst.setString(4, P3);
	              pst.setString(5, P4);
	              pst.setString(6, ((P5.equalsIgnoreCase("S"))?"ACTIVO":"INACTIVO"));
	              pst.setString(7, P6);
	              pst.addBatch();
	              cont++;
	        }
	        entrada.close();
	        pst.executeBatch();
	        cn.commit();
	        System.out.println("ejecutadoscont:"+cont);
	        System.out.println("\nPROCESO CORRECTO");
	    }catch (Exception e){ //Catch de excepciones
	        cn.rollback();
	        System.out.println("OCURRIO ERROR : " + e.getMessage());
	    }finally{
	        pst.clearBatch();
	        pst.close();
	        cn.close();
	    }
	  }

	
	

}
