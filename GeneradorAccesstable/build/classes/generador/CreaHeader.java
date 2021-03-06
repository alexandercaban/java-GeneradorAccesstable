package generador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;

public class CreaHeader {
	
	public static void crearHeader(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioHeaders + "AT" + nombreTabla + ".h");
		// String espacios = new String(" ");
		String sbComilla = "\"";
		BufferedWriter outHeader = new BufferedWriter(header);
		DefinicionCampo dc;

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}

		outHeader.write("#ifndef __ACCESS_TABLE_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outHeader.write("#define __ACCESS_TABLE_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outHeader.write("\n\n");
		outHeader.write("/*\n");
		outHeader.write("    Propiedad intelectual de Play Tech. \n\n");
		outHeader.write("    Nombre  :    AT" + nombreTabla + ".h" + "\n");
		outHeader.write("    Descripcion  :    " + "Definicion Cabecera de accestable de tabla" + nombreTabla + "\n");
		outHeader.write("    Autor  :    " + "Play Tech." + "\n");
		outHeader.write("    Creacion  :    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outHeader.write("    Historial de Modificaciones \n");
		outHeader.write("    Fecha    Autor    	Descripcion \n");
		outHeader.write("*/\n\n");

		/*
		 * outHeader.write( "#include <comm/ControlRed.h>\n\n");
		 * outHeader.write( "#include <general/Constantes.h>\n");
		 * outHeader.write( "#include <general/Parser.h>\n"); outHeader.write(
		 * "#include <general/Utilidades.h>\n\n");
		 */

		outHeader.write("#include " + Constante.Comilla + "../../General/headers/Constantes.h" + Constante.Comilla
				+ "\n");
		outHeader.write("#include " + Constante.Comilla + "../../General/headers/ControlRed.h" + Constante.Comilla
				+ "\n");
		outHeader.write("#include " + Constante.Comilla + "../../General/headers/Utilidades.h" + Constante.Comilla
				+ "\n");
		outHeader.write("#include " + Constante.Comilla + "../../General/headers/MensajeError.h" + Constante.Comilla
				+ "\n");
		outHeader
				.write("#include " + Constante.Comilla + "../../General/headers/Parser.h" + Constante.Comilla + "\n\n");

		outHeader.write("#include " + sbComilla + "ServiciosAccessTable.h" + sbComilla + "\n");
		outHeader.write("#include <qstring.h>\n");
		outHeader.write("#include <qdatetime.h>\n\n\n");

		outHeader.write("//! la clase AT" + nombreTabla + " provee un objeto de acceso a datos de la tabla "
				+ nombreTabla + Constante.finLinea);
		outHeader.write("/*! AT" + nombreTabla + " permite un manejo de la la tabla " + nombreTabla
				+ " dentro del esquema de Base de datos" + Constante.finLinea);
		outHeader
				.write("  permitiendo tener acceso a los campos de la tabla por medio de metodos set y get para la manipulacion de Datos"
						+ Constante.finLinea);
		outHeader.write("*/" + Constante.finLinea);

		outHeader.write("class AT" + nombreTabla + "\n");
		outHeader.write("{\n");

		// Crea los atributos de la clase
		outHeader.write("  /* Atributos */\n");
		outHeader.write("  private:\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    " + dc.getTipoJava() + " " + dc.getPrefijo() + dc.getNombre() + ";\n");
		}
		outHeader.write("    int nuIndice;\n\n");

		// Crea un constructor y destructor para la clase
		outHeader.write("  public:\n");
		outHeader.write("    //! constructor de la clase AT" + nombreTabla + Constante.finLinea);
		outHeader.write("    /*! construye un objeto AT" + nombreTabla
				+ " vacio con miembros numericos en -1 y miembros QString vacios" + Constante.finLinea);
		outHeader.write("    */" + Constante.finLinea);
		outHeader.write("    AT" + nombreTabla + "();  /* Constructor " + nombreTabla + "*/\n");
		outHeader.write("    AT" + nombreTabla + "(const AT" + nombreTabla + "& copy);  /* Constructor de copia de "
				+ nombreTabla + "*/\n");
		outHeader.write("    ~AT" + nombreTabla + "();  /* Destructor " + nombreTabla + "*/\n");

		// Crea los metodos para asignar valor a los atributos de la clase
		outHeader.write("\n\n    /* Metodos Set de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    void set" + dc.getNombre() + "(" + dc.getTipoJava() + " " + dc.getPrefijo() + "New"
					+ dc.getNombre() + ");\n");
		}
		outHeader.write("    void setIndice (int nuNewIndice);\n");

		outHeader.write("\n\n    /* Metodos Get de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    " + dc.getTipoJava() + " get" + dc.getNombre() + "();\n");
		}
		outHeader.write("    int getIndice ();\n\n");

		outHeader.write("  /* Acceso a datos por socket*/\n");
		outHeader.write("  public:\n");
		outHeader.write("    QString loadRecord ();\n");
		outHeader.write("    QString updateRecord ();\n\n");

		// Aqui se hace el proceso de calcular la llave primaria
		if (vdcLlave.size() > 0) {
			outHeader.write("    MensajeError loadDB (");// );\n");

			dc = (DefinicionCampo) vdcLlave.elementAt(0);
			outHeader.write(dc.getTipoJava());
			if (vdcLlave.size() > 1) {
				for (int i = 1; i < vdcLlave.size(); i++) {
					dc = (DefinicionCampo) vdcLlave.elementAt(i);
					outHeader.write(", " + dc.getTipoJava());
				}
			}
			outHeader.write(");\n");
		}

		outHeader.write("    MensajeError updateDB ();\n\n");
		outHeader.write("    bool blCamposRequeridos ();\n");
		outHeader.write("    QString toString (QString sbSeparador = Constantes :: Separador_Campos);\n");
		outHeader
				.write("    void fromString (QString sbCampos, QString sbSeparador = Constantes :: Separador_Campos);\n");

		outHeader.write("  public:\n");
		outHeader.write("    ControlRed SocketRed;");

		outHeader.write("\n\n};\n");
		outHeader.write("typedef QValueList <AT" + nombreTabla + "> TLista" + nombreTabla + ";\n");
		outHeader.write("typedef TLista" + nombreTabla + " :: iterator it" + nombreTabla + ";\n");
		outHeader.write("#endif\n");
		outHeader.flush();
		header.close();
	}
	
	public static void crearHeaderProyecto(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioHeaders + "AT" + nombreTabla + ".h");
		// String espacios = new String(" ");
		String sbComilla = "\"";
		BufferedWriter outHeader = new BufferedWriter(header);
		DefinicionCampo dc;

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}

		outHeader.write("#ifndef __ACCESS_TABLE_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outHeader.write("#define __ACCESS_TABLE_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outHeader.write("\n\n");
		outHeader.write("/*\n");
		outHeader.write("    Propiedad intelectual de Play Tech. \n\n");
		outHeader.write("    Nombre  			:    AT" + nombreTabla + ".h" + "\n");
		outHeader.write("    Descripcion 	:    " + "Definicion Cabecera de accestable de tabla" + nombreTabla + "\n");
		outHeader.write("    Autor  			:    " + "Play Tech." + "\n");
		outHeader.write("    Creacion  		:    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outHeader.write("    Historial de Modificaciones \n");
		outHeader.write("    Fecha    Autor    	Descripcion \n");
		outHeader.write("*/\n\n");

		/*
		 * outHeader.write( "#include <comm/ControlRed.h>\n\n");
		 * outHeader.write( "#include <general/Constantes.h>\n");
		 * outHeader.write( "#include <general/Parser.h>\n"); outHeader.write(
		 * "#include <general/Utilidades.h>\n\n");
		 */

		outHeader.write("#include <general/Constantes.h>\n");
		outHeader.write("#include <general/ControlRed.h>\n");
		outHeader.write("#include <general/Utilidades.h>\n");
		outHeader.write("#include <general/MensajeError.h>\n");
		outHeader.write("#include <general/Parser.h>\n\n");

		outHeader.write("#include " + sbComilla + "ServiciosAccessTable.h" + sbComilla + "\n");
		outHeader.write("#include <qstring.h>\n");
		outHeader.write("#include <qdatetime.h>\n\n\n");

		outHeader.write("//! la clase AT" + nombreTabla + " provee un objeto de acceso a datos de la tabla "
				+ nombreTabla + Constante.finLinea);
		outHeader.write("/*! AT" + nombreTabla + " permite un manejo de la la tabla " + nombreTabla
				+ " dentro del esquema de Base de datos" + Constante.finLinea);
		outHeader
				.write("  permitiendo tener acceso a los campos de la tabla por medio de metodos set y get para la manipulacion de Datos"
						+ Constante.finLinea);
		outHeader.write("*/" + Constante.finLinea);

		outHeader.write("class AT" + nombreTabla + "\n");
		outHeader.write("{\n");

		// Crea los atributos de la clase
		outHeader.write("  /* Atributos */\n");
		outHeader.write("  private:\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    " + dc.getTipoJava() + " " + dc.getPrefijo() + dc.getNombre() + ";\n");
		}
		outHeader.write("    int nuIndice;\n\n");

		// Crea un constructor y destructor para la clase
		outHeader.write("  public:\n");
		outHeader.write("    //! constructor de la clase AT" + nombreTabla + Constante.finLinea);
		outHeader.write("    /*! construye un objeto AT" + nombreTabla
				+ " vacio con miembros numericos en -1 y miembros QString vacios" + Constante.finLinea);
		outHeader.write("    */" + Constante.finLinea);
		outHeader.write("    AT" + nombreTabla + "();  /* Constructor " + nombreTabla + "*/\n");
		outHeader.write("    AT" + nombreTabla + "(const AT" + nombreTabla + "& copy);  /* Constructor de copia de "
				+ nombreTabla + "*/\n");
		outHeader.write("    ~AT" + nombreTabla + "();  /* Destructor " + nombreTabla + "*/\n");

		// Crea los metodos para asignar valor a los atributos de la clase
		outHeader.write("\n\n    /* Metodos Set de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    void set" + dc.getNombre() + "(" + dc.getTipoJava() + " " + dc.getPrefijo() + "New"
					+ dc.getNombre() + ");\n");
		}
		outHeader.write("    void setIndice (int nuNewIndice);\n");

		outHeader.write("\n\n    /* Metodos Get de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    " + dc.getTipoJava() + " get" + dc.getNombre() + "();\n");
		}
		outHeader.write("    int getIndice ();\n\n");

		outHeader.write("  /* Acceso a datos por socket*/\n");
		outHeader.write("  public:\n");
		outHeader.write("    QString loadRecord ();\n");
		outHeader.write("    QString updateRecord ();\n\n");

		// Aqui se hace el proceso de calcular la llave primaria
		if (vdcLlave.size() > 0) {
			outHeader.write("    MensajeError loadDB (");// );\n");

			dc = (DefinicionCampo) vdcLlave.elementAt(0);
			outHeader.write(dc.getTipoJava());
			if (vdcLlave.size() > 1) {
				for (int i = 1; i < vdcLlave.size(); i++) {
					dc = (DefinicionCampo) vdcLlave.elementAt(i);
					outHeader.write(", " + dc.getTipoJava());
				}
			}
			outHeader.write(");\n");
		}

		outHeader.write("    MensajeError updateDB ();\n\n");
		outHeader.write("    bool blCamposRequeridos ();\n");
		outHeader.write("    QString toString (QString sbSeparador = Constantes :: Separador_Campos);\n");
		outHeader
				.write("    void fromString (QString sbCampos, QString sbSeparador = Constantes :: Separador_Campos);\n");

		outHeader.write("  public:\n");
		outHeader.write("    ControlRed SocketRed;");

		outHeader.write("\n\n};\n");
		outHeader.write("typedef QValueList <AT" + nombreTabla + "> TLista" + nombreTabla + ";\n");
		outHeader.write("typedef TLista" + nombreTabla + " :: iterator it" + nombreTabla + ";\n");
		outHeader.write("#endif\n");
		outHeader.flush();
		header.close();
	}

	// se crea el .cpp del accesstable para cada tabla
	public static void crearSource(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioSources + "AT" + nombreTabla + ".cpp");
		BufferedWriter outSource = new BufferedWriter(header);
		// String espacios = new String(" ");
		String sbComilla = "\"";

		DefinicionCampo dc;

		outSource.write("/*\n");
		outSource.write("    Propiedad intelectual de Play Tech. \n\n");
		outSource.write("    Nombre  :    AT" + nombreTabla + ".cpp" + "\n");
		outSource.write("    Descripcion  :    " + "Definicion fuentes de accestable de tabla" + nombreTabla + "\n");
		outSource.write("    Autor  :    " + "Play Tech." + "\n");
		outSource.write("    Creacion  :    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outSource.write("    Historial de Modificaciones \n");
		outSource.write("    Fecha    Autor    	Descripcion \n");
		outSource.write("*/\n\n");
		outSource.write("#include  " + sbComilla + "../../General/headers/Parser.h" + sbComilla + "\n\n");
		outSource.write("#include  " + sbComilla + "../headers/AT" + nombreTabla + ".h" + sbComilla + "\n");
		outSource.write("#include  " + sbComilla + "../headers/ServiciosAccessTable.h" + sbComilla + "\n");
		outSource.write("#include <qstring.h>\n");
		outSource.write("#include <qdatetime.h>\n\n\n");

		// Crea un constructor y destructor para la clase
		outSource.write("/* Constructor " + nombreTabla + "*/\n");
		outSource.write("AT" + nombreTabla + " :: AT" + nombreTabla + "()\n");
		outSource.write("{\n");

		// asigna valores recuparados al objeto
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			String tipoJava = dc.getTipoJava();
			String primero = tipoJava.substring(0, 1);
			String resto = tipoJava.substring(1, tipoJava.length());
			tipoJava = primero.toUpperCase() + resto;

			if (tipoJava.equals("Int")) {
				outSource.write("  " + dc.getPrefijo() + dc.getNombre() + " = -1" + ";\n");
			}

			if (tipoJava.equals("Float")) {
				outSource.write("  " + dc.getPrefijo() + dc.getNombre() + " = -1" + ";\n");
			}
			if (tipoJava.equals("QString")) {
				outSource.write("  " + dc.getPrefijo() + dc.getNombre() + " = " + sbComilla + sbComilla + ";\n");
			}
		}
		outSource.write("}\n");
		outSource.write("\n");

		outSource.write("/* Destructor " + nombreTabla + "*/\n");
		outSource.write("AT" + nombreTabla + " :: ~AT" + nombreTabla + "()\n");
		outSource.write("{\n}\n");

		// Crea los metodos para asignar valor a los atributos de la clase
		outSource.write("\n\n/* Metodos Set de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outSource.write("void AT" + nombreTabla + " :: set" + dc.getNombre() + "(" + dc.getTipoJava() + " "
					+ dc.getPrefijo() + "New" + dc.getNombre() + ")\n");
			outSource.write("{\n");
			outSource.write("  " + dc.getPrefijo() + dc.getNombre() + " = " + dc.getPrefijo() + "New" + dc.getNombre()
					+ ";\n");
			outSource.write("}\n\n");
		}
		outSource.write("void AT" + nombreTabla + " :: setIndice (int nuNewIndice)\n");
		outSource.write("{\n");
		outSource.write("  nuIndice = nuNewIndice;\n");
		outSource.write("}\n");

		outSource.write("\n\n/* Metodos Get de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outSource.write(dc.getTipoJava() + " AT" + nombreTabla + " :: get" + dc.getNombre() + "()\n");
			outSource.write("{\n");
			outSource.write("  return " + dc.getPrefijo() + dc.getNombre() + ";\n");
			outSource.write("}\n\n");
		}
		outSource.write("int AT" + nombreTabla + " :: getIndice ()\n");
		outSource.write("{\n");
		outSource.write("  return nuIndice;\n");
		outSource.write("}\n");

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}

		// Los metodos de acceso por llave
		if (vdcLlave.size() > 0) {
			outSource.write("QString AT" + nombreTabla + " :: loadRecord()\n");
			outSource.write("{\n");
			outSource.write("  QString sbPeticionServ;\n");
			outSource.write("  QString sbRespuestaServ;\n");
			outSource.write("  Parser objParserCampos;\n\n");
			outSource.write("  sbPeticionServ  = ServiciosAccessTable :: Consultar_" + nombreTabla + ";\n");
			outSource.write("  sbPeticionServ += Constantes :: Separador_Registros;\n");
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				if (dc.getTipoJava().equals("QDate")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre()
							+ ".toString(Constantes :: Formato_Fecha);\n");
				}
				if (dc.getTipoJava().equals("QTime")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre()
							+ ".toString(Constantes :: Formato_Fecha);\n");
				}
				if (dc.getTipoJava().equals("QString")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre() + ";\n");
				}
				if (dc.getTipoJava().equals("int") || dc.getTipoJava().equals("float")) {
					outSource.write("  sbPeticionServ += QString :: number (" + dc.getPrefijo() + dc.getNombre()
							+ ");\n");
				}
				if (i + 1 < vdcLlave.size()) {
					outSource.write("  sbPeticionServ += Constantes :: Separador_Campos;\n");
				}
			}
			outSource.write("  SocketRed.EscribirToken( sbPeticionServ );\n");
			outSource.write("  Parser objParser ( SocketRed.LeerToken(), Constantes :: Separador_Registros );\n");
			outSource.write("  SocketRed.CerrarControlRed();\n\n");
			outSource.write("  sbRespuestaServ = objParser.getNextString();\n");
			outSource.write("  if ( sbRespuestaServ == Constantes :: Exito ) {\n");
			outSource.write("    while (objParser.HasMoreToken() ) {\n");
			outSource
					.write("      objParserCampos.stringtoTokenizer ( objParser.getNextString(), Constantes :: Separador_Campos );\n");

			for (int i = 0; i < vdc.size(); i++) {
				dc = (DefinicionCampo) vdc.elementAt(i);
				if (dc.getTipoJava().equals("QDate")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextDate());\n");
				}
				if (dc.getTipoJava().equals("QTime")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextTime());\n");
				}
				if (dc.getTipoJava().equals("QString")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextString());\n");
				}
				if (dc.getTipoJava().equals("int")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextInteger());\n");
				}
				if (dc.getTipoJava().equals("float")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextFloat());\n");
				}
				if (dc.getTipoJava().equals("bool")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextBoolean());\n");
				}
			}
			outSource.write("    }\n");
			outSource.write("  }\n");
			outSource.write("  return sbRespuestaServ;\n");
			outSource.write("}\n\n");
			
			// Generacion del Metodo loadDB con retorno del tipo MensajeError
			outSource.write("MensajeError AT" + nombreTabla + " :: loadDB(");
			
			dc = (DefinicionCampo) vdcLlave.elementAt(0);
			outSource.write(dc.getTipoJava());
			if (vdcLlave.size() > 1) {
				for (int i = 1; i < vdcLlave.size(); i++) {
					dc = (DefinicionCampo) vdcLlave.elementAt(i);
					outSource.write(", " + dc.getTipoJava());
				}
			}
			outSource.write(") {\n");
			outSource.write("  QString sbPeticionServ;\n");
			outSource.write("  QString sbRespuestaServ;\n");
			outSource.write("  Parser objParserCampos;\n\n");
			outSource.write("  sbPeticionServ  = ServiciosAccessTable :: Consultar_" + nombreTabla + ";\n");
			outSource.write("  sbPeticionServ += Constantes :: Separador_Registros;\n");
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				if (dc.getTipoJava().equals("QDate")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre()
							+ ".toString(Constantes :: Formato_Fecha);\n");
				}
				if (dc.getTipoJava().equals("QTime")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre()
							+ ".toString(Constantes :: Formato_Fecha);\n");
				}
				if (dc.getTipoJava().equals("QString")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre() + ";\n");
				}
				if (dc.getTipoJava().equals("int") || dc.getTipoJava().equals("float")) {
					outSource.write("  sbPeticionServ += QString :: number (" + dc.getPrefijo() + dc.getNombre()
							+ ");\n");
				}
				if (i + 1 < vdcLlave.size()) {
					outSource.write("  sbPeticionServ += Constantes :: Separador_Campos;\n");
				}
			}
			outSource.write("  SocketRed.EscribirToken( sbPeticionServ );\n");
			outSource.write("  Parser objParser ( SocketRed.LeerToken(), Constantes :: Separador_Registros );\n");
			outSource.write("  SocketRed.CerrarControlRed();\n\n");
			outSource.write("  sbRespuestaServ = objParser.getNextString();\n");
			outSource.write("  MensajeError objRespuestaServ = objParser.getCadena();\n");
			outSource.write("  if ( objRespuestaServ.esExitosa() ) {\n");
			outSource.write("    while (objParser.HasMoreToken() ) {\n");
			outSource
					.write("      objParserCampos.stringtoTokenizer ( objParser.getNextString(), Constantes :: Separador_Campos );\n");

			for (int i = 0; i < vdc.size(); i++) {
				dc = (DefinicionCampo) vdc.elementAt(i);
				if (dc.getTipoJava().equals("QDate")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextDate());\n");
				}
				if (dc.getTipoJava().equals("QTime")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextTime());\n");
				}
				if (dc.getTipoJava().equals("QString")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextString());\n");
				}
				if (dc.getTipoJava().equals("int")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextInteger());\n");
				}
				if (dc.getTipoJava().equals("float")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextFloat());\n");
				}
				if (dc.getTipoJava().equals("bool")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextBoolean());\n");
				}
			}
			outSource.write("    }\n");
			outSource.write("  }\n");
			outSource.write("  return objRespuestaServ;\n");
			outSource.write("}\n");

		}

		// ///////////////////////////////
		outSource.write("QString AT" + nombreTabla + " :: updateRecord()\n");
		outSource.write("{\n");
		outSource.write("  QString sbPeticionServ;\n");
		outSource.write("  QString sbRespuestaServ;\n");

		outSource.write("  sbPeticionServ  = ServiciosAccessTable :: Update_" + nombreTabla
				+ " + Constantes :: Separador_Registros;\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getTipoJava().equals("QDate")) {
				outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre()
						+ ".toString(Constantes :: Formato_Fecha);\n");
			}
			if (dc.getTipoJava().equals("QTime")) {
				outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre()
						+ ".toString(Constantes :: Formato_Hora);\n");
			}
			if (dc.getTipoJava().equals("QString")) {
				outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre() + ";\n");
			}
			if (dc.getTipoJava().equals("bool")) {
				// outSource.write(" sbPeticionServ += Constantes ::
				// Separador_Campos;\n");
				outSource.write("  if (" + dc.getPrefijo() + dc.getNombre() + " == TRUE)\n");
				outSource.write("  {\n");
				outSource.write("    sbPeticionServ += Constantes :: True_Cadena;\n");
				outSource.write("  }\n");
				outSource.write("  else\n");
				outSource.write("  {\n");
				outSource.write("    sbPeticionServ += Constantes :: False_Cadena;\n");
				outSource.write("  }\n");
				// outSource.write(" sbPeticionServ += Constantes ::
				// Separador_Campos;\n");

			}
			if (dc.getTipoJava().equals("int") || dc.getTipoJava().equals("float")) {
				outSource.write("  sbPeticionServ += QString :: number (" + dc.getPrefijo() + dc.getNombre() + ");\n");
			}
			if (i + 1 < vdc.size()) {
				outSource.write("  sbPeticionServ += Constantes :: Separador_Campos;" + Constante.finLinea);
			}
		}
		outSource.write("  SocketRed.EscribirToken(sbPeticionServ);\n");
		outSource.write("  sbRespuestaServ = SocketRed.LeerToken();\n");
		outSource.write("  SocketRed.CerrarControlRed();\n");
		outSource.write("  return sbRespuestaServ;\n");
		outSource.write("}\n\n");

		outSource.write("bool AT" + nombreTabla + " :: blCamposRequeridos()\n");
		outSource.write("{\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getRequerido().equals("N")) {
				if (dc.getTipoJava().equals("QTime")) {
					outSource.write("  if ( " + dc.getPrefijo() + dc.getNombre() + ".isValid() == false) {\n");
					outSource.write("    return false;\n");
					outSource.write("  }\n");
				}

				if (dc.getTipoJava().equals("QDate")) {
					outSource.write("  if ( " + dc.getPrefijo() + dc.getNombre() + ".isValid() == false) {\n");
					outSource.write("    return false;\n");
					outSource.write("  }\n");
				}
				if (dc.getTipoJava().equals("QString")) {
					outSource.write("  if ( " + dc.getPrefijo() + dc.getNombre() + ".isEmpty() == false) {\n");
					outSource.write("    return false;\n");
					outSource.write("  }\n");
				}
			}
		}
		outSource.write("  return true;\n");
		outSource.write("}\n");

		//
		outSource.flush();
		header.close();
	}

	public static void crearHeaderLibreria(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioHeadersLibrerias + "AT" + nombreTabla + ".h");
		//String espacios = new String("                                             ");
		String sbComilla = "\"";
		BufferedWriter outHeader = new BufferedWriter(header);
		DefinicionCampo dc;

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}		
		outHeader.write("#ifndef __ACCESS_TABLE_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outHeader.write("#define __ACCESS_TABLE_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outHeader.write("\n\n");
		outHeader.write("/*\n");
		outHeader.write("    Propiedad intelectual de Play Tech. \n\n");
		outHeader.write("    Nombre  :    AT" + nombreTabla + ".h" + "\n");
		outHeader.write("    Descripcion  :    " + "Definicion Cabecera de accestable de tabla" + nombreTabla + "\n");
		outHeader.write("    Autor  :    " + "Play Tech." + "\n");
		outHeader.write("    Creacion  :    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outHeader.write("    Historial de Modificaciones \n");
		outHeader.write("    Fecha    Autor    	Descripcion \n");
		outHeader.write("*/\n\n");
		outHeader.write("#include  " + sbComilla + "Constantes.h" + sbComilla + "\n");
		outHeader.write("#include  " + sbComilla + "ControlRed.h" + sbComilla + "\n");
		outHeader.write("#include  " + sbComilla + "Utilidades.h" + sbComilla + "\n");
		outHeader.write("#include  " + sbComilla + "MensajeError.h" + sbComilla + "\n\n");
		outHeader.write("#include <qstring.h>\n");
		outHeader.write("#include <qdatetime.h>\n\n\n");

		outHeader.write("//! la clase AT" + nombreTabla + " provee un objeto de acceso a datos de la tabla "
				+ nombreTabla + Constante.finLinea);
		outHeader.write("/*! AT" + nombreTabla + " permite un manejo de la la tabla " + nombreTabla
				+ " dentro del esquema de Base de datos" + Constante.finLinea);
		outHeader
				.write("  permitiendo tener acceso a los campos de la tabla por medio de metodos set y get para la manipulacion de Datos"
						+ Constante.finLinea);
		outHeader.write("*/" + Constante.finLinea);

		outHeader.write("class AT" + nombreTabla + "\n");
		outHeader.write("{\n");

		// Crea los atributos de la clase
		outHeader.write("  /* Atributos */\n");
		outHeader.write("  private:\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    " + dc.getTipoJava() + " " + dc.getPrefijo() + dc.getNombre() + ";\n");
		}
		outHeader.write("    int nuIndice;\n\n");

		// Crea un constructor y destructor para la clase
		outHeader.write("  public:\n");
		outHeader.write("    //! constructor de la clase AT" + nombreTabla + Constante.finLinea);
		outHeader.write("    /*! construye un objeto AT" + nombreTabla
				+ " vacio con miembros numericos en -1 y miembros QString vacios" + Constante.finLinea);
		outHeader.write("    */" + Constante.finLinea);
		outHeader.write("    AT" + nombreTabla + "();  /* Constructor " + nombreTabla + "*/\n");
		outHeader.write("    AT" + nombreTabla + "(const AT" + nombreTabla + "& copy);  /* Constructor de copia de "
				+ nombreTabla + "*/\n");
		// outHeader.write(" AT"+nombreTabla+"(const AT"+nombreTabla+"& copy);
		// /* Constructor de copia de "+ nombreTabla +"*/\n");
		outHeader.write("    ~AT" + nombreTabla + "();  /* Destructor " + nombreTabla + "*/\n");

		// Crea los metodos para asignar valor a los atributos de la clase
		outHeader.write("\n\n    /* Metodos Set de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    void set" + dc.getNombre() + "(" + dc.getTipoJava() + " " + dc.getPrefijo() + "New"
					+ dc.getNombre() + ");\n");
		}
		outHeader.write("    void setIndice (int nuNewIndice);\n");

		outHeader.write("\n\n    /* Metodos Get de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    " + dc.getTipoJava() + " get" + dc.getNombre() + "();\n");
		}
		outHeader.write("    int getIndice ();\n\n");

		outHeader.write("  /* Acceso a datos por socket*/\n");
		outHeader.write("  public:\n");
		outHeader.write("    QString loadRecord ();\n");
		outHeader.write("    QString updateRecord ();\n\n");
		
		if (vdcLlave.size() > 0) {
			outHeader.write("    MensajeError loadDB (");// );\n");

			dc = (DefinicionCampo) vdcLlave.elementAt(0);
			outHeader.write(dc.getTipoJava());
			if (vdcLlave.size() > 1) {
				for (int i = 1; i < vdcLlave.size(); i++) {
					dc = (DefinicionCampo) vdcLlave.elementAt(i);
					outHeader.write(", " + dc.getTipoJava());
				}
			}
			outHeader.write(");\n");
		}

		outHeader.write("    MensajeError updateDB ();\n\n");
		outHeader.write("    QString toString (QString sbSeparador = Constantes :: Separador_Campos);\n");
		outHeader
				.write("    void fromString (QString sbCampos, QString sbSeparador = Constantes :: Separador_Campos);\n");
		outHeader.write("    bool blCamposRequeridos ();\n");
		outHeader.write("  public:\n");
		outHeader.write("    ControlRed SocketRed;");

		outHeader.write("\n\n};\n");
		outHeader.write("typedef QValueList <AT" + nombreTabla + "> TLista" + nombreTabla + ";\n");
		outHeader.write("typedef TLista" + nombreTabla + " :: iterator it" + nombreTabla + ";\n");
		outHeader.write("#endif\n");
		outHeader.flush();
		header.close();
	}
	
	public static void crearHeaderLibreriaProyecto(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioHeadersLibrerias + "AT" + nombreTabla + ".h");
		//String espacios = new String("                                             ");
		//String sbComilla = "\"";
		BufferedWriter outHeader = new BufferedWriter(header);
		DefinicionCampo dc;

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}		
		outHeader.write("#ifndef __ACCESS_TABLE_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outHeader.write("#define __ACCESS_TABLE_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outHeader.write("\n\n");
		outHeader.write("/*\n");
		outHeader.write("    Propiedad intelectual de Play Tech. \n\n");
		outHeader.write("    Nombre  			:    AT" + nombreTabla + ".h" + "\n");
		outHeader.write("    Descripcion  	:    " + "Definicion Cabecera de accestable de tabla" + nombreTabla + "\n");
		outHeader.write("    Autor  			:    " + "Play Tech." + "\n");
		outHeader.write("    Creacion  		:    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outHeader.write("    Historial de Modificaciones \n");
		outHeader.write("    Fecha    Autor    	Descripcion \n");
		outHeader.write("*/\n\n");
		outHeader.write("#include  <general/Constantes.h>\n");
		outHeader.write("#include  <general/ControlRed.h>\n");
		outHeader.write("#include  <general/Utilidades.h>\n");
		outHeader.write("#include  <general/MensajeError.h>\n\n");
		outHeader.write("#include <qstring.h>\n");
		outHeader.write("#include <qdatetime.h>\n\n\n");

		outHeader.write("//! la clase AT" + nombreTabla + " provee un objeto de acceso a datos de la tabla "
				+ nombreTabla + Constante.finLinea);
		outHeader.write("/*! AT" + nombreTabla + " permite un manejo de la la tabla " + nombreTabla
				+ " dentro del esquema de Base de datos" + Constante.finLinea);
		outHeader
				.write("  permitiendo tener acceso a los campos de la tabla por medio de metodos set y get para la manipulacion de Datos"
						+ Constante.finLinea);
		outHeader.write("*/" + Constante.finLinea);

		outHeader.write("class AT" + nombreTabla + "\n");
		outHeader.write("{\n");

		// Crea los atributos de la clase
		outHeader.write("  /* Atributos */\n");
		outHeader.write("  private:\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    " + dc.getTipoJava() + " " + dc.getPrefijo() + dc.getNombre() + ";\n");
		}
		outHeader.write("    int nuIndice;\n\n");

		// Crea un constructor y destructor para la clase
		outHeader.write("  public:\n");
		outHeader.write("    //! constructor de la clase AT" + nombreTabla + Constante.finLinea);
		outHeader.write("    /*! construye un objeto AT" + nombreTabla
				+ " vacio con miembros numericos en -1 y miembros QString vacios" + Constante.finLinea);
		outHeader.write("    */" + Constante.finLinea);
		outHeader.write("    AT" + nombreTabla + "();  /* Constructor " + nombreTabla + "*/\n");
		outHeader.write("    AT" + nombreTabla + "(const AT" + nombreTabla + "& copy);  /* Constructor de copia de "
				+ nombreTabla + "*/\n");
		// outHeader.write(" AT"+nombreTabla+"(const AT"+nombreTabla+"& copy);
		// /* Constructor de copia de "+ nombreTabla +"*/\n");
		outHeader.write("    ~AT" + nombreTabla + "();  /* Destructor " + nombreTabla + "*/\n");

		// Crea los metodos para asignar valor a los atributos de la clase
		outHeader.write("\n\n    /* Metodos Set de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    void set" + dc.getNombre() + "(" + dc.getTipoJava() + " " + dc.getPrefijo() + "New"
					+ dc.getNombre() + ");\n");
		}
		outHeader.write("    void setIndice (int nuNewIndice);\n");

		outHeader.write("\n\n    /* Metodos Get de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outHeader.write("    " + dc.getTipoJava() + " get" + dc.getNombre() + "();\n");
		}
		outHeader.write("    int getIndice ();\n\n");

		outHeader.write("  /* Acceso a datos por socket*/\n");
		outHeader.write("  public:\n");
		outHeader.write("    QString loadRecord ();\n");
		outHeader.write("    QString updateRecord ();\n\n");
		
		if (vdcLlave.size() > 0) {
			outHeader.write("    MensajeError loadDB (");// );\n");

			dc = (DefinicionCampo) vdcLlave.elementAt(0);
			outHeader.write(dc.getTipoJava());
			if (vdcLlave.size() > 1) {
				for (int i = 1; i < vdcLlave.size(); i++) {
					dc = (DefinicionCampo) vdcLlave.elementAt(i);
					outHeader.write(", " + dc.getTipoJava());
				}
			}
			outHeader.write(");\n");
		}

		outHeader.write("    MensajeError updateDB ();\n\n");
		outHeader.write("    QString toString (QString sbSeparador = Constantes :: Separador_Campos);\n");
		outHeader
				.write("    void fromString (QString sbCampos, QString sbSeparador = Constantes :: Separador_Campos);\n");
		outHeader.write("    bool blCamposRequeridos ();\n");
		outHeader.write("  public:\n");
		outHeader.write("    ControlRed SocketRed;");

		outHeader.write("\n\n};\n");
		outHeader.write("typedef QValueList <AT" + nombreTabla + "> TLista" + nombreTabla + ";\n");
		outHeader.write("typedef TLista" + nombreTabla + " :: iterator it" + nombreTabla + ";\n");
		outHeader.write("#endif\n");
		outHeader.flush();
		header.close();
	}

	public static void crearSourceFinal(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioSources + "AT" + nombreTabla + ".cpp");
		BufferedWriter outSource = new BufferedWriter(header);
		//String espacios = new String("                                             ");
		String sbComilla = "\"";

		DefinicionCampo dc;

		outSource.write("/*\n");
		outSource.write("    Propiedad intelectual de Play Tech. \n\n");
		outSource.write("    Nombre  			:    AT" + nombreTabla + ".cpp" + "\n");
		outSource.write("    Descripcion  	:    " + "Definicion fuentes de accestable de tabla" + nombreTabla + "\n");
		outSource.write("    Autor  				:    " + "Play Tech." + "\n");
		outSource.write("    Creacion  		:    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outSource.write("    Historial de Modificaciones \n");
		outSource.write("    Fecha    Autor    	Descripcion \n");
		outSource.write("*/\n\n");
		outSource.write("#include  " + sbComilla + "../headers/AT" + nombreTabla + ".h" + sbComilla + "\n");
		outSource.write("\n\n\n");

		// Crea un constructor y destructor para la clase
		outSource.write("/* Constructor " + nombreTabla + "*/\n");
		outSource.write("AT" + nombreTabla + " :: AT" + nombreTabla + "()\n");
		outSource.write("{\n");

		// asigna valores recuparados al objeto
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			String tipoJava = dc.getTipoJava();
                        //javax.swing.JOptionPane.showMessageDialog(null, tipoJava);
			String primero = tipoJava.substring(0, 1);
			String resto = tipoJava.substring(1, tipoJava.length());
			tipoJava = primero.toUpperCase() + resto;

			if (tipoJava.equals("Int")) {
				outSource.write("  " + dc.getPrefijo() + dc.getNombre() + " = -1" + ";\n");
			}

			if (tipoJava.equals("Float")) {
				outSource.write("  " + dc.getPrefijo() + dc.getNombre() + " = -1" + ";\n");
			}
			if (tipoJava.equals("QString")) {
				outSource.write("  " + dc.getPrefijo() + dc.getNombre() + " = " + sbComilla + sbComilla + ";\n");
			}
		}
		outSource.write("}\n");
		outSource.write("\n");

		outSource.write("AT" + nombreTabla + " :: AT" + nombreTabla + "( const AT" + nombreTabla + "& copy)\n");
		outSource.write("{\n");

		// asigna valores recuparados al objeto
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			String tipoJava = dc.getTipoJava();
			String primero = tipoJava.substring(0, 1);
			String resto = tipoJava.substring(1, tipoJava.length());
			tipoJava = primero.toUpperCase() + resto;

			/*
			 * if ( tipoJava.equals("Int") ) { outSource.write("
			 * "+dc.getPrefijo()+dc.getNombre()+" =
			 * copy."+dc.getPrefijo()+dc.getNombre()+";\n"); }
			 * 
			 * if ( tipoJava.equals("Float") ) { outSource.write("
			 * "+dc.getPrefijo()+dc.getNombre()+" =
			 * copy."+dc.getPrefijo()+dc.getNombre()+";\n"); } if (
			 * tipoJava.equals("QString") ) {
			 */
			outSource.write("  " + dc.getPrefijo() + dc.getNombre() + " = copy." + dc.getPrefijo() + dc.getNombre()
					+ ";\n");
			// }
		}
		outSource.write("}\n");
		outSource.write("\n");

		outSource.write("/* Destructor " + nombreTabla + "*/\n");
		outSource.write("AT" + nombreTabla + " :: ~AT" + nombreTabla + "()\n");
		outSource.write("{\n}\n");

		// Crea los metodos para asignar valor a los atributos de la clase
		outSource.write("\n\n/* Metodos Set de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outSource.write("void AT" + nombreTabla + " :: set" + dc.getNombre() + "(" + dc.getTipoJava() + " "
					+ dc.getPrefijo() + "New" + dc.getNombre() + ")\n");
			outSource.write("{\n");
			outSource.write("  " + dc.getPrefijo() + dc.getNombre() + " = " + dc.getPrefijo() + "New" + dc.getNombre()
					+ ";\n");
			outSource.write("}\n\n");
		}
		outSource.write("void AT" + nombreTabla + " :: setIndice (int nuNewIndice)\n");
		outSource.write("{\n");
		outSource.write("  nuIndice = nuNewIndice;\n");
		outSource.write("}\n");

		outSource.write("\n\n/* Metodos Get de la clase*/\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			outSource.write(dc.getTipoJava() + " AT" + nombreTabla + " :: get" + dc.getNombre() + "()\n");
			outSource.write("{\n");
			outSource.write("  return " + dc.getPrefijo() + dc.getNombre() + ";\n");
			outSource.write("}\n\n");
		}
		outSource.write("int AT" + nombreTabla + " :: getIndice ()\n");
		outSource.write("{\n");
		outSource.write("  return nuIndice;\n");
		outSource.write("}\n\n");

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}

		if (vdcLlave.size() > 0) {
			outSource.write("QString AT" + nombreTabla + " :: loadRecord()\n");
			outSource.write("{\n");
			outSource.write("  QString sbPeticionServ;\n");
			outSource.write("  QString sbRespuestaServ;\n");
			outSource.write("  Parser objParserCampos;\n\n");
			outSource.write("  sbPeticionServ  = ServiciosAccessTable :: Consultar_" + nombreTabla + ";\n");
			outSource.write("  sbPeticionServ += Constantes :: Separador_Registros;\n");
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				if (dc.getTipoJava().equals("QDate")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre()
							+ ".toString(Constantes :: Formato_Fecha);\n");
				}
				if (dc.getTipoJava().equals("QTime")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre()
							+ ".toString(Constantes :: Formato_Fecha);\n");
				}
				if (dc.getTipoJava().equals("QString")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre() + ";\n");
				}
				if (dc.getTipoJava().equals("int") || dc.getTipoJava().equals("float")) {
					outSource.write("  sbPeticionServ += QString :: number (" + dc.getPrefijo() + dc.getNombre()
							+ ");\n");
				}
				if (i + 1 < vdcLlave.size()) {
					outSource.write("  sbPeticionServ += Constantes :: Separador_Campos;\n");
				}
			}
			outSource.write("  SocketRed.EscribirToken( sbPeticionServ );\n");
			outSource.write("  Parser objParser ( SocketRed.LeerToken(), Constantes :: Separador_Registros );\n");
			outSource.write("  SocketRed.CerrarControlRed();\n\n");
			outSource.write("  sbRespuestaServ = objParser.getNextString();\n");
			outSource.write("  if ( sbRespuestaServ == Constantes :: Exito ) {\n");
			outSource.write("    while (objParser.HasMoreToken() ) {\n");
			outSource
					.write("      objParserCampos.stringtoTokenizer ( objParser.getNextString(), Constantes :: Separador_Campos );\n");

			for (int i = 0; i < vdc.size(); i++) {
				dc = (DefinicionCampo) vdc.elementAt(i);
				if (dc.getTipoJava().equals("QDate")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextDate());\n");
				}
				if (dc.getTipoJava().equals("QTime")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextTime());\n");
				}
				if (dc.getTipoJava().equals("QString")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextString());\n");
				}
				if (dc.getTipoJava().equals("int")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextInteger());\n");
				}
				if (dc.getTipoJava().equals("float")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextFloat());\n");
				}
				if (dc.getTipoJava().equals("bool")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextBoolean());\n");
				}
			}
			outSource.write("    }\n");
			outSource.write("  }\n");
			outSource.write("  return sbRespuestaServ;\n");
			outSource.write("}\n\n");
			
//			 Generacion del Metodo loadDB con retorno del tipo MensajeError
			outSource.write("MensajeError AT" + nombreTabla + " :: loadDB(");
			
			dc = (DefinicionCampo) vdcLlave.elementAt(0);
			outSource.write(dc.getTipoJava()+" "+dc.getPrefijo()+"New"+dc.getNombre());
			if (vdcLlave.size() > 1) {
				for (int i = 1; i < vdcLlave.size(); i++) {
					dc = (DefinicionCampo) vdcLlave.elementAt(i);
					outSource.write(", " + dc.getTipoJava()+" "+dc.getPrefijo()+"New"+dc.getNombre());
				}
			}
			outSource.write(") {\n");
			outSource.write("  QString sbPeticionServ;\n");
			outSource.write("  QString sbRespuestaServ;\n");
			outSource.write("  Parser objParserCampos;\n\n");
			outSource.write("  sbPeticionServ  = ServiciosAccessTable :: Consultar_" + nombreTabla + ";\n");
			outSource.write("  sbPeticionServ += Constantes :: Separador_Registros;\n");
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				if (dc.getTipoJava().equals("QDate")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() +"New"+ dc.getNombre()
							+ ".toString(Constantes :: Formato_Fecha);\n");
				}
				if (dc.getTipoJava().equals("QTime")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() +"New"+  dc.getNombre()
							+ ".toString(Constantes :: Formato_Fecha);\n");
				}
				if (dc.getTipoJava().equals("QString")) {
					outSource.write("  sbPeticionServ += " + dc.getPrefijo() +"New"+  dc.getNombre() + ";\n");
				}
				if (dc.getTipoJava().equals("int") || dc.getTipoJava().equals("float")) {
					outSource.write("  sbPeticionServ += QString :: number (" + dc.getPrefijo() +"New"+  dc.getNombre()
							+ ");\n");
				}
				if (i + 1 < vdcLlave.size()) {
					outSource.write("  sbPeticionServ += Constantes :: Separador_Campos;\n");
				}
			}
			outSource.write("  SocketRed.EscribirToken( sbPeticionServ );\n");
			outSource.write("  Parser objParser ( SocketRed.LeerToken(), Constantes :: Separador_Registros );\n");
			outSource.write("  SocketRed.CerrarControlRed();\n\n");
			outSource.write("  sbRespuestaServ = objParser.getNextString();\n");
			outSource.write("  MensajeError objRespuestaServ = objParser.getCadena();\n");
			outSource.write("  if ( objRespuestaServ.esExitosa() ) {\n");
			outSource.write("    while (objParser.HasMoreToken() ) {\n");
			outSource
					.write("      objParserCampos.stringtoTokenizer ( objParser.getNextString(), Constantes :: Separador_Campos );\n");

			for (int i = 0; i < vdc.size(); i++) {
				dc = (DefinicionCampo) vdc.elementAt(i);
				if (dc.getTipoJava().equals("QDate")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextDate());\n");
				}
				if (dc.getTipoJava().equals("QTime")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextTime());\n");
				}
				if (dc.getTipoJava().equals("QString")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextString());\n");
				}
				if (dc.getTipoJava().equals("int")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextInteger());\n");
				}
				if (dc.getTipoJava().equals("float")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextFloat());\n");
				}
				if (dc.getTipoJava().equals("bool")) {
					outSource.write("      set" + dc.getNombre() + "(objParserCampos.getNextBoolean());\n");
				}
			}
			outSource.write("    }\n");
			outSource.write("  }\n");
			outSource.write("  return objRespuestaServ;\n");
			outSource.write("}\n\n");

		}

		// ///////////////////////////////
		outSource.write("QString AT" + nombreTabla + " :: updateRecord()\n");
		outSource.write("{\n");
		outSource.write("  QString sbPeticionServ;\n");
		outSource.write("  QString sbRespuestaServ;\n");

		outSource.write("  sbPeticionServ  = ServiciosAccessTable :: Update_" + nombreTabla
				+ " + Constantes :: Separador_Registros;\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getTipoJava().equals("QDate")) {
				outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre()
						+ ".toString(Constantes :: Formato_Fecha);\n");
			}
			if (dc.getTipoJava().equals("QTime")) {
				outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre()
						+ ".toString(Constantes :: Formato_Hora);\n");
			}
			if (dc.getTipoJava().equals("QString")) {
				outSource.write("  sbPeticionServ += " + dc.getPrefijo() + dc.getNombre() + ";\n");
			}
			if (dc.getTipoJava().equals("bool")) {
				// outSource.write(" sbPeticionServ += Constantes ::
				// Separador_Campos;\n");
				outSource.write("  if (" + dc.getPrefijo() + dc.getNombre() + " == TRUE) {\n");
				outSource.write("    sbPeticionServ += Constantes :: True_Cadena;\n");
				outSource.write("  }\n");
				outSource.write("  else {\n");
				outSource.write("    sbPeticionServ += Constantes :: False_Cadena;\n");
				outSource.write("  }\n");
				// outSource.write(" sbPeticionServ += Constantes ::
				// Separador_Campos;\n");

			}
			if (dc.getTipoJava().equals("int") || dc.getTipoJava().equals("float")) {
				if (dc.getTipoJava().equals("int"))
					outSource.write("  sbPeticionServ += QString :: number (" + dc.getPrefijo() + dc.getNombre() + ");\n");
				else 
					outSource.write("  sbPeticionServ += Utilidades :: floattoString (" + dc.getPrefijo() + dc.getNombre() + ", 4);\n");
			}
			if (i + 1 < vdc.size()) {
				outSource.write("  sbPeticionServ += Constantes :: Separador_Campos;" + Constante.finLinea);
			}
		}
		outSource.write("  SocketRed.EscribirToken(sbPeticionServ);\n");
		outSource.write("  sbRespuestaServ = SocketRed.LeerToken();\n");
		outSource.write("  SocketRed.CerrarControlRed();\n");
		outSource.write("  return sbRespuestaServ;\n");
		outSource.write("}\n\n");
		
		outSource.write("MensajeError AT" + nombreTabla + " :: updateDB() {\n");
		outSource.write("  MensajeError objRespuestaServ(this->updateRecord());\n");
		outSource.write("  return objRespuestaServ;\n");
		outSource.write("}\n\n");

		outSource.write("bool AT" + nombreTabla + " :: blCamposRequeridos()\n");
		outSource.write("{\n");
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getRequerido().equals("N")) {
				if (dc.getTipoJava().equals("QTime")) {
					outSource.write("  if ( " + dc.getPrefijo() + dc.getNombre() + ".isValid() == false) {\n");
					outSource.write("    return false;\n");
					outSource.write("  }\n");
				}

				if (dc.getTipoJava().equals("QDate")) {
					outSource.write("  if ( " + dc.getPrefijo() + dc.getNombre() + ".isValid() == false) {\n");
					outSource.write("    return false;\n");
					outSource.write("  }\n");
				}
				if (dc.getTipoJava().equals("QString")) {
					outSource.write("  if ( " + dc.getPrefijo() + dc.getNombre() + ".isEmpty() == false) {\n");
					outSource.write("    return false;\n");
					outSource.write("  }\n");
				}
			}
		}
		outSource.write("  return true;\n");
		outSource.write("}\n\n");

		outSource.write("QString AT" + nombreTabla + " :: toString(QString sbSeparador )\n");
		outSource.write("{\n");
		outSource.write("  QString sbRespuesta;\n");

		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getTipoJava().equals("QDate")) {
				outSource.write("  sbRespuesta += " + dc.getPrefijo() + dc.getNombre()
						+ ".toString(Constantes :: Formato_Fecha);\n");
			}
			if (dc.getTipoJava().equals("QTime")) {
				outSource.write("  sbRespuesta += " + dc.getPrefijo() + dc.getNombre()
						+ ".toString(Constantes :: Formato_Hora);\n");
			}
			if (dc.getTipoJava().equals("QString")) {
				outSource.write("  sbRespuesta += " + dc.getPrefijo() + dc.getNombre() + " ;\n");
			}
			if (dc.getTipoJava().equals("bool")) {
				// outSource.write(" sbPeticionServ += Constantes ::
				// Separador_Campos;\n");
				outSource.write("  if (" + dc.getPrefijo() + dc.getNombre() + " == TRUE) {\n");
				outSource.write("    sbRespuesta += Constantes :: True_Cadena;\n");
				outSource.write("  }\n");
				outSource.write("  else {\n");
				outSource.write("    sbRespuesta += Constantes :: False_Cadena;\n");
				outSource.write("  }\n");
			}
			if (dc.getTipoJava().equals("float")) {
				outSource.write("  sbRespuesta += Utilidades :: floattoString (" + dc.getPrefijo() + dc.getNombre()
						+ ", 4);\n");
			}
			if (dc.getTipoJava().equals("int")) {
				outSource.write("  sbRespuesta += QString :: number (" + dc.getPrefijo() + dc.getNombre() + ");\n");
			}
			if (i + 1 < vdc.size()) {
				outSource.write("  sbRespuesta += sbSeparador;" + Constante.finLinea);
			}
		}

		outSource.write("  return sbRespuesta;\n");
		outSource.write("}\n\n");

		outSource.write("void AT" + nombreTabla + " :: fromString(QString sbCampos, QString sbSeparador )\n");
		outSource.write("{\n");
		outSource.write("  Parser objParserCampos;\n");
		outSource.write("  objParserCampos.stringtoTokenizer (sbCampos, sbSeparador);\n");

		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getTipoJava().equals("QDate")) {
				outSource.write("  set" + dc.getNombre() + "(objParserCampos.getNextDate());\n");
			}
			if (dc.getTipoJava().equals("QTime")) {
				outSource.write("  set" + dc.getNombre() + "(objParserCampos.getNextTime());\n");
			}
			if (dc.getTipoJava().equals("QString")) {
				outSource.write("  set" + dc.getNombre() + "(objParserCampos.getNextString());\n");
			}
			if (dc.getTipoJava().equals("int")) {
				outSource.write("  set" + dc.getNombre() + "(objParserCampos.getNextInteger());\n");
			}
			if (dc.getTipoJava().equals("float")) {
				outSource.write("  set" + dc.getNombre() + "(objParserCampos.getNextFloat());\n");
			}
			if (dc.getTipoJava().equals("bool")) {
				outSource.write("  set" + dc.getNombre() + "(objParserCampos.getNextBoolean());\n");
			}
		}
		outSource.write("}\n\n");
		//
		outSource.flush();
		header.close();
	}
	
}