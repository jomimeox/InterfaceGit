package Ftp;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Conexion.OracleConexion;

import oracle.jdbc.internal.OracleTypes;

public class InsertarTablasMaximo {

	public static String pvalidar_registrarMaximo() throws IOException
	{   String rs="";
		Connection cn=null;
        PreparedStatement  pst=null;
		try {
            
            String sql="begin SP_PROCESARBODEGA(?); end;";
            cn=OracleConexion.obtenerConexion();
            CallableStatement callableStatement=cn.prepareCall(sql);

            //Parametros de entrada
            callableStatement.registerOutParameter(1, OracleTypes.VARCHAR);
            callableStatement.execute();
            //Se obtiene el cursor en forma de ResultSe
            rs = (String)callableStatement.getObject(1);
            System.out.println("nn:"+rs.split("-")[0]);
            callableStatement.close();
            
            cn.close();
            
        } catch (SQLException e) {
            //Imprime el mensaje de la exception lanzada en pl/sql si el valor es diferente de 1
            e.printStackTrace();
        }
		//Crear Log
		
		return rs;
	}
	
	

}
