/*
* Clase			:	CrearArchivosPHP.java
* Paquete	:	generador
* Usuario 	:	Usuario
* Fecha		:	11/01/2007 - 15:25:45
*
* Historial de Modificaciones
*
*/

/**
 * 
 */
package generador;

import static generador.Constante.DIRECTORIO_ARCHIVOS_PHP;
import static generador.Constante.finLinea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;

/**
 * @author Usuario
 * @version 2.0
 *
 */
public class CrearArchivosPHP {

	public static void crearClasePhp(String nombreTabla, Vector vdc) 
	throws IOException {

		try{
		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(DIRECTORIO_ARCHIVOS_PHP+ "AT" + nombreTabla + ".php");
		BufferedWriter outSource = new BufferedWriter(header);
		// String espacios = new String(" ");
		String sbComilla = "\"";

		DefinicionCampo dc;

		outSource.write("<?php \n");
		outSource.write("/*\n");
		outSource.write("*	Propiedad intelectual de Play Tech. \n");
		outSource.write("*	Nombre			:    AT" + nombreTabla + ".php" + "\n");
		outSource.write("*	Descripcion		:    \n");
		outSource.write("*	Autor			:    \n");
		outSource.write("*	Creacion		:    " + dtFechaActual + " - " + tiHoraActual + "\n");
		outSource.write("*/\n\n");
		
		outSource.write("include_once  (" + sbComilla + "../../utils/user/Socket.php" + sbComilla + ");\n");
		outSource.write("include_once  (" + sbComilla + "../br/Servicios.php" + sbComilla + ");\n\n");

		// Crea un constructor y destructor para la clase
		
		outSource.write("class AT" + nombreTabla + "{ \n\n");

		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			String tipoJava = dc.getTipoJava();
			String primero = tipoJava.substring(0, 1);
			String resto = tipoJava.substring(1, tipoJava.length());
			tipoJava = primero.toUpperCase() + resto;

			outSource.write("  var $" + dc.getPrefijo() + dc.getNombre()+";\n");
			
		}
		
		outSource.write("\n  var $rc" + nombreTabla+";\n\n");
		
		
		outSource.write("function " + nombreTabla+"() { \n");
		// asigna valores recuparados al objeto
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			String tipoJava = dc.getTipoJava();
			String primero = tipoJava.substring(0, 1);
			String resto = tipoJava.substring(1, tipoJava.length());
			tipoJava = primero.toUpperCase() + resto;

			if (tipoJava.equals("Int")) {
				outSource.write("  $this->" + dc.getPrefijo() + dc.getNombre() + " = -1" + ";\n");
			}

			if (tipoJava.equals("Float")) {
				outSource.write("  $this->" + dc.getPrefijo() + dc.getNombre() + " = -1" + ";\n");
			}
			if (tipoJava.equals("QString")) {
				outSource.write("  $this->" + dc.getPrefijo() + dc.getNombre() + " = " + sbComilla + sbComilla + ";\n");
			}
		}
		
		outSource.write("\n  $this->rc"+nombreTabla+" = array();\n");
		
		outSource.write("}\n");
		outSource.write("\n");
		
		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}

		// Los metodos de acceso por llave
		if (vdcLlave.size() > 0) {
			outSource.write("  function loadDB(");
			
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				outSource.write("   $"+dc.getPrefijo()+"New"+dc.getNombre());
				int y=i;
				if ((y+1)<vdcLlave.size())
					outSource.write(", ");
				
				
			}
			outSource.write(") {\n");
			outSource.write("    $objSocket = new Socket();\n");
			outSource.write("    $sbPeticion = CONSULTAR_"+nombreTabla.toUpperCase()+".SEPAREGISTROS.\n");
			outSource.write("      ");
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				outSource.write("$"+dc.getPrefijo()+"New"+dc.getNombre());
				int y=i;
				if ((y+1)<vdcLlave.size())
					outSource.write(".SEPACAMPOS.");
				
				
			}
			outSource.write(";\n");
			outSource.write("    $objSocket->EscribirToken($sbPeticion);"+finLinea);
			outSource.write("    $sbRespuesta = $objSocket->LeerToken();"+finLinea);
			outSource.write("    $objSocket->CerrarSocket();"+finLinea);
			outSource.write("    $objRespuestaServ = new MensajeError($sbRespuesta);"+finLinea);
						
			outSource.write("    if ($objRespuestaServ->esExitosa()) {\n");
			outSource.write("      $objParser = new StringTokenizer($sbRespuesta, SEPAREGISTROS);\n");
			outSource.write("      $objParser->nextToken();\n");
			outSource.write("      $objParserCampos = new StringTokenizer ($objParser->nextToken(), SEPACAMPOS);\n");
			
			for (int i = 0; i < vdc.size(); i++) {
				dc = (DefinicionCampo) vdc.elementAt(i);
				if (dc.getTipoJava().equals("QDate")||dc.getTipoJava().equals("QTime")||dc.getTipoJava().equals("int")||dc.getTipoJava().equals("QString")) {
					outSource.write("      $this->" +dc.getPrefijo()+dc.getNombre() + " = $objParserCampos->nextToken();\n");
				}
				if (dc.getTipoJava().equals("float")) {
					outSource.write("      $this->" +dc.getPrefijo()+dc.getNombre() + " = $objParserCampos->nextFloat();\n");
				}
				if (dc.getTipoJava().equals("bool")) {
					outSource.write("      $this->" +dc.getPrefijo()+dc.getNombre() + " = $objParserCampos->nextBool();\n");
				}
			}
			outSource.write("    }\n");
			outSource.write("    return $objRespuestaServ;\n");
			outSource.write("  }\n\n");
			
		}

		// ///////////////////////////////
		outSource.write("  function updateDB() {\n");
		outSource.write("    $objSocket = new Socket();\n");
		outSource.write("    $sbPeticion = ACTUALIZAR_"+nombreTabla.toUpperCase()+".SEPAREGISTROS.\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			
			outSource.write("          $this->"+dc.getPrefijo()+dc.getNombre());
			int y=i;
			if ((y+1)<vdc.size())
			outSource.write(".SEPACAMPOS.\n");
		}
		
		outSource.write(";\n");
		
		outSource.write("    $objSocket->EscribirToken($sbPeticion);\n");
		outSource.write("    $sbRespuesta = $objSocket->LeerToken();\n");
		outSource.write("    $objSocket->CerrarSocket();\n");
		outSource.write("    $objMensaje = new MensajeError($sbRespuesta);\n");
		outSource.write("    return $objMensaje;\n");
		outSource.write("  }\n}\n\n?>\n");

		//
		outSource.flush();
		header.close();
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
}
