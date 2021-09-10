package generador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;

public class CreaHeaderLista {
	
	public static void crearHeaderLista(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioListaHeaders + "Lista" + nombreTabla + ".h");
		System.out.println(Constante.directorioListaHeaders + "Lista" + nombreTabla + ".h");
		//String espacios = new String("                                             ");
		//String sbComilla = "\"";        
		BufferedWriter outListaHeader = new BufferedWriter(header);
		DefinicionCampo dc;

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}

		outListaHeader.write("#ifndef __LISTA_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outListaHeader.write("#define __LISTA_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outListaHeader.write("\n\n");
		outListaHeader.write("/*\n");
		outListaHeader.write("    Propiedad intelectual de Play Tech. \n\n");
		outListaHeader.write("    Nombre  :    Lista" + nombreTabla + ".h" + "\n");
		outListaHeader.write("    Descripcion  :    " + "Definicion de lista de objetos " + nombreTabla + "\n");
		outListaHeader.write("    Autor  :    " + "Play Tech." + "\n");
		outListaHeader.write("    Creacion  :    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outListaHeader.write("    Historial de Modificaciones \n");
		outListaHeader.write("    Fecha    Autor    	Descripcion \n");
		outListaHeader.write("*/\n\n");
		/*outListaHeader.write( "#include  <comm/ControlRed.h>\n\n");
		 outListaHeader.write( "#include  <general/Constantes.h>\n");
		 outListaHeader.write( "#include  <general/Parser.h>\n");
		 outListaHeader.write( "#include  <general/Utilidades.h>\n\n");		
		 outListaHeader.write( "#include  "+sbComilla+"ServiciosAccessTable.h"+sbComilla+"\n\n");*/

		outListaHeader.write("#include " + Constante.Comilla + "../../General/headers/Constantes.h" + Constante.Comilla
				+ "\n");
		outListaHeader.write("#include " + Constante.Comilla + "../../General/headers/ControlRed.h" + Constante.Comilla
				+ "\n");
		outListaHeader.write("#include " + Constante.Comilla + "../../General/headers/Utilidades.h" + Constante.Comilla
				+ "\n");
		outListaHeader.write("#include " + Constante.Comilla + "../../General/headers/MensajeError.h"
				+ Constante.Comilla + "\n");
		outListaHeader.write("#include " + Constante.Comilla + "../../General/headers/Parser.h" + Constante.Comilla
				+ "\n\n");

		outListaHeader.write("#include " + Constante.Comilla + "../../AccessTable/headers/AT" + nombreTabla + ".h"
				+ Constante.Comilla + "\n\n");
		outListaHeader.write("#include " + Constante.Comilla + "../../AccessTable/headers/ServiciosAccessTable.h"
				+ Constante.Comilla + "\n\n");

		outListaHeader.write("#include <qstring.h>\n");
		outListaHeader.write("#include <qdatetime.h>\n\n\n");
		outListaHeader.write("class Lista" + nombreTabla + "\n");
		outListaHeader.write("{\n");

		//	Crea un constructor y destructor para la clase        
		outListaHeader.write("  public:\n");
		outListaHeader.write("    Lista" + nombreTabla + "();    /* Constructor " + nombreTabla + "*/\n");
		outListaHeader.write("    ~Lista" + nombreTabla + "();    /* Destructor " + nombreTabla + "*/\n\n");

		// Crea los metodos para asignar valor a los atributos de la clase		       		
		outListaHeader.write("    void insertRecord (AT" + nombreTabla + ");\n");
		outListaHeader.write("    void removeRecord ( it" + nombreTabla + " );\n\n");

		outListaHeader.write("    it" + nombreTabla + " get" + nombreTabla + "porIndice( int nuIndice );\n");

		String sbArgumentos = new String();
		String separador = new String(", ");

		if (vdcLlave.size() > 0) {
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				sbArgumentos += dc.getTipoJava() + " " + dc.getPrefijo() + "New" + dc.getNombre();
				if (i + 1 < vdcLlave.size()) {
					sbArgumentos += separador;
				}
			}
			outListaHeader
					.write("    it" + nombreTabla + " get" + nombreTabla + "porLlave ( " + sbArgumentos + " );\n");
		}

		outListaHeader.write("    it" + nombreTabla + " Begin ();\n");
		outListaHeader.write("    it" + nombreTabla + " End ();\n");
		outListaHeader.write("    void Clear ();\n");
		outListaHeader.write("    int Size();\n");

		outListaHeader.write("\n  /* Metodos de acceso a datos*/\n");
		outListaHeader.write("    QString loadListRecord ();\n");
		outListaHeader.write("    MensajeError loadRecords();\n\n");
		//outListaHeader.write("    QString updateListRecord ();\n\n");    			

		outListaHeader.write("  protected:\n");
		outListaHeader.write("    TLista" + nombreTabla + " ListaT" + nombreTabla + ";\n\n");

		outListaHeader.write("  public:\n");
		outListaHeader.write("    ControlRed SocketRed;");

		outListaHeader.write("\n\n};\n");
		outListaHeader.write("#endif\n");
		outListaHeader.flush();
		header.close();
	}
	
	public static void crearHeaderListaProyecto(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioListaHeaders + "Lista" + nombreTabla + ".h");
		System.out.println(Constante.directorioListaHeaders + "Lista" + nombreTabla + ".h");
		//String espacios = new String("                                             ");
		//String sbComilla = "\"";        
		BufferedWriter outListaHeader = new BufferedWriter(header);
		DefinicionCampo dc;

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}

		outListaHeader.write("#ifndef __LISTA_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outListaHeader.write("#define __LISTA_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outListaHeader.write("\n\n");
		outListaHeader.write("/*\n");
		outListaHeader.write("    Propiedad intelectual de Play Tech. \n\n");
		outListaHeader.write("    Nombre  			:    Lista" + nombreTabla + ".h" + "\n");
		outListaHeader.write("    Descripcion  	:    " + "Definicion de lista de objetos " + nombreTabla + "\n");
		outListaHeader.write("    Autor  				:    " + "Play Tech." + "\n");
		outListaHeader.write("    Creacion  		:    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outListaHeader.write("    Historial de Modificaciones \n");
		outListaHeader.write("    Fecha    Autor    	Descripcion \n");
		outListaHeader.write("*/\n\n");
		/*outListaHeader.write( "#include  <comm/ControlRed.h>\n\n");
		 outListaHeader.write( "#include  <general/Constantes.h>\n");
		 outListaHeader.write( "#include  <general/Parser.h>\n");
		 outListaHeader.write( "#include  <general/Utilidades.h>\n\n");		
		 outListaHeader.write( "#include  "+sbComilla+"ServiciosAccessTable.h"+sbComilla+"\n\n");*/

		outListaHeader.write("#include <general/Constantes.h>\n");
		outListaHeader.write("#include <general/ControlRed.h>\n");
		outListaHeader.write("#include <general/Utilidades.h>\n");
		outListaHeader.write("#include <general/MensajeError.h>\n");
		outListaHeader.write("#include <general/Parser.h>\n\n");

		outListaHeader.write("#include " + Constante.Comilla + "../../objetos/headers/AT" + nombreTabla + ".h"
				+ Constante.Comilla + "\n\n");
		outListaHeader.write("#include " + Constante.Comilla + "../../objetos/headers/ServiciosAccessTable.h"
				+ Constante.Comilla + "\n\n");

		outListaHeader.write("#include <qstring.h>\n");
		outListaHeader.write("#include <qdatetime.h>\n\n\n");
		outListaHeader.write("class Lista" + nombreTabla + "\n");
		outListaHeader.write("{\n");

		//	Crea un constructor y destructor para la clase        
		outListaHeader.write("  public:\n");
		outListaHeader.write("    Lista" + nombreTabla + "();    /* Constructor " + nombreTabla + "*/\n");
		outListaHeader.write("    ~Lista" + nombreTabla + "();    /* Destructor " + nombreTabla + "*/\n\n");

		// Crea los metodos para asignar valor a los atributos de la clase		       		
		outListaHeader.write("    void insertRecord (AT" + nombreTabla + ");\n");
		outListaHeader.write("    void removeRecord ( it" + nombreTabla + " );\n\n");

		outListaHeader.write("    it" + nombreTabla + " get" + nombreTabla + "porIndice( int nuIndice );\n");

		String sbArgumentos = new String();
		String separador = new String(", ");

		if (vdcLlave.size() > 0) {
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				sbArgumentos += dc.getTipoJava() + " " + dc.getPrefijo() + "New" + dc.getNombre();
				if (i + 1 < vdcLlave.size()) {
					sbArgumentos += separador;
				}
			}
			outListaHeader
					.write("    it" + nombreTabla + " get" + nombreTabla + "porLlave ( " + sbArgumentos + " );\n");
		}

		outListaHeader.write("    it" + nombreTabla + " Begin ();\n");
		outListaHeader.write("    it" + nombreTabla + " End ();\n");
		outListaHeader.write("    void Clear ();\n");
		outListaHeader.write("    int Size();\n");

		outListaHeader.write("\n  /* Metodos de acceso a datos*/\n");
		outListaHeader.write("    QString loadListRecord ();\n");
		outListaHeader.write("    MensajeError loadRecords();\n\n");
		//outListaHeader.write("    QString updateListRecord ();\n\n");    			

		outListaHeader.write("  protected:\n");
		outListaHeader.write("    TLista" + nombreTabla + " ListaT" + nombreTabla + ";\n\n");

		outListaHeader.write("  public:\n");
		outListaHeader.write("    ControlRed SocketRed;");

		outListaHeader.write("\n\n};\n");
		outListaHeader.write("#endif\n");
		outListaHeader.flush();
		header.close();
	}

	public static void crearSourceLista(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioListaSources + "Lista" + nombreTabla + ".cpp");
		BufferedWriter outSource = new BufferedWriter(header);
		//String espacios = new String("                                             ");
		String sbComilla = "\"";

		DefinicionCampo dc;

		outSource.write("/*\n");
		outSource.write("    Propiedad intelectual de Play Tech. \n\n");
		outSource.write("    Nombre  :    Lista" + nombreTabla + ".cpp" + "\n");
		outSource.write("    Descripcion  :    " + "Definicion fuentes de accestable de tabla" + nombreTabla + "\n");
		outSource.write("    Autor  :    " + "Play Tech." + "\n");
		outSource.write("    Creacion  :    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outSource.write("    Historial de Modificaciones \n");
		outSource.write("    Fecha    Autor    	Descripcion \n");
		outSource.write("*/\n\n");
		outSource.write("#include  " + sbComilla + "../headers/Lista" + nombreTabla + ".h" + sbComilla + "\n\n\n");

		//	Crea un constructor y destructor para la clase
		outSource.write("/* Constructor Lista" + nombreTabla + "*/\n");
		outSource.write("Lista" + nombreTabla + " :: Lista" + nombreTabla + "()\n");
		outSource.write("{\n");
		outSource.write("}\n");
		outSource.write("\n");

		outSource.write("/* Destructor Lista" + nombreTabla + "*/\n");
		outSource.write("Lista" + nombreTabla + " :: ~Lista" + nombreTabla + "()\n");
		outSource.write("{\n}\n\n");

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}

		outSource.write("void Lista" + nombreTabla + " :: insertRecord (AT" + nombreTabla + " new" + nombreTabla
				+ ")\n");
		outSource.write("{\n");
		outSource.write("  ListaT" + nombreTabla + ".append (new" + nombreTabla + ");\n");
		outSource.write("}\n\n");

		outSource.write("void Lista" + nombreTabla + " :: removeRecord (it" + nombreTabla + " old" + nombreTabla
				+ ")\n");
		outSource.write("{\n");
		outSource.write("  ListaT" + nombreTabla + ".remove (old" + nombreTabla + ");\n");
		outSource.write("}\n\n");
		String sbArgumentos = new String();
		String sbArgumentosBusqueda = new String();
		String separador = new String(" ,");

		if (vdcLlave.size() > 0) {
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				sbArgumentos += dc.getTipoJava() + " " + dc.getPrefijo() + "New" + dc.getNombre();
				sbArgumentosBusqueda += "(*" + nombreTabla + "Actual).get" + dc.getNombre() + "() == "
						+ dc.getPrefijo() + "New" + dc.getNombre();
				if (i + 1 < vdcLlave.size()) {
					sbArgumentos += separador;
					sbArgumentosBusqueda += " && ";
				}
			}

			outSource.write("it" + nombreTabla + "  Lista" + nombreTabla + " :: get" + nombreTabla + "porLlave ( "
					+ sbArgumentos + " )\n");
			outSource.write("{\n");
			outSource.write("  it" + nombreTabla + " " + nombreTabla + "Actual;\n");
			outSource.write("  for ( " + nombreTabla + "Actual = Begin(); " + nombreTabla + "Actual != End(); ++"
					+ nombreTabla + "Actual)\n");
			outSource.write("  {\n");
			outSource.write("    if (" + sbArgumentosBusqueda + ")\n");
			outSource.write("    {\n");
			outSource.write("      return " + nombreTabla + "Actual;\n");
			outSource.write("    }\n");
			outSource.write("  }\n");
			outSource.write("  return false;\n");
			outSource.write("}\n");
		}

		outSource.write("it" + nombreTabla + " Lista" + nombreTabla + " :: get" + nombreTabla
				+ "porIndice (int nuIndice)\n");
		outSource.write("{\n");
		outSource.write("  it" + nombreTabla + " " + nombreTabla + "Actual;\n");
		outSource.write("  for ( " + nombreTabla + "Actual = Begin(); " + nombreTabla + "Actual != End(); ++"
				+ nombreTabla + "Actual)\n");
		outSource.write("  {\n");
		outSource.write("    if ((*" + nombreTabla + "Actual).getIndice() == nuIndice)\n");
		outSource.write("    {\n");
		outSource.write("      return " + nombreTabla + "Actual;\n");
		outSource.write("    }\n");
		outSource.write("  }\n");
		outSource.write("  return false;\n");
		outSource.write("}\n\n");

		outSource.write("it" + nombreTabla + " Lista" + nombreTabla + " :: Begin ()\n");
		outSource.write("{\n");
		outSource.write("  return ListaT" + nombreTabla + ".begin();\n");
		outSource.write("}\n\n");

		outSource.write("it" + nombreTabla + " Lista" + nombreTabla + " :: End ()\n");
		outSource.write("{\n");
		outSource.write("  return ListaT" + nombreTabla + ".end();\n");
		outSource.write("}\n\n");

		outSource.write("void Lista" + nombreTabla + " :: Clear ()\n");
		outSource.write("{\n");
		outSource.write("  ListaT" + nombreTabla + ".clear();\n");
		outSource.write("}\n\n");

		outSource.write("int Lista" + nombreTabla + " :: Size () {\n");
		outSource.write("  return ListaT" + nombreTabla + ".count();\n");
		outSource.write("}\n\n");

		outSource.write("QString Lista" + nombreTabla + " :: loadListRecord ()\n");
		outSource.write("{\n");
		outSource.write("  QString sbPeticion;\n");
		outSource.write("  QString sbRespuestaServ;\n");
		outSource.write("  Parser objParser;\n");
		outSource.write("  Parser objParserCampos;\n");
		outSource.write("  AT" + nombreTabla + " obj" + nombreTabla + ";\n\n");
		outSource.write("  SocketRed.EscribirToken( ServiciosAccessTable :: Consultar_Lista_" + nombreTabla + ");\n");
		outSource.write("  objParser.stringtoTokenizer (SocketRed.LeerToken(),Constantes :: Separador_Registros);\n");
		outSource.write("  SocketRed.CerrarControlRed();\n\n");
		outSource.write("  sbRespuestaServ = objParser.getNextString();\n");
		outSource.write("  if ( sbRespuestaServ == Constantes :: Exito){\n");
		outSource.write("    while(objParser.HasMoreToken()) {\n");
		outSource
				.write("      objParserCampos.stringtoTokenizer(objParser.getNextString(), Constantes :: Separador_Campos);\n");

		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getTipoJava().equals("QDate")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextDate());\n");
			}
			if (dc.getTipoJava().equals("QTime")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextTime());\n");
			}
			if (dc.getTipoJava().equals("QString")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextString());\n");
			}
			if (dc.getTipoJava().equals("int")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextInteger());\n");
			}
			if (dc.getTipoJava().equals("float")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextFloat());\n");
			}
			if (dc.getTipoJava().equals("bool")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextBoolean());\n");
			}
		}
		outSource.write("      ListaT" + nombreTabla + ".append(obj" + nombreTabla + "); \n");

		//outSource.write("      obj"+nombreTabla+".();\n");
		outSource.write("    }\n");
		outSource.write("  }\n");
		outSource.write("  return sbRespuestaServ;\n");
		outSource.write("}\n\n");
		
		outSource.write("MensajeError Lista" + nombreTabla + " :: loadRecords () {\n");
		
		outSource.write("  QString sbPeticion;\n");
		outSource.write("  QString sbRespuestaServ;\n");
		outSource.write("  Parser objParser;\n");
		outSource.write("  Parser objParserCampos;\n");
		outSource.write("  AT" + nombreTabla + " obj" + nombreTabla + ";\n\n");
		outSource.write("  SocketRed.EscribirToken( ServiciosAccessTable :: Consultar_Lista_" + nombreTabla + ");\n");
		outSource.write("  objParser.stringtoTokenizer (SocketRed.LeerToken(),Constantes :: Separador_Registros);\n");
		outSource.write("  SocketRed.CerrarControlRed();\n\n");
		outSource.write("  sbRespuestaServ = objParser.getNextString();\n");
		
		outSource.write("  MensajeError objRespuestaServ(objParser.getCadena());\n");
		
		outSource.write("  if ( sbRespuestaServ == Constantes :: Exito){\n");
		outSource.write("    while(objParser.HasMoreToken()) {\n");
		outSource
				.write("      objParserCampos.stringtoTokenizer(objParser.getNextString(), Constantes :: Separador_Campos);\n");

		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getTipoJava().equals("QDate")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextDate());\n");
			}
			if (dc.getTipoJava().equals("QTime")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextTime());\n");
			}
			if (dc.getTipoJava().equals("QString")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextString());\n");
			}
			if (dc.getTipoJava().equals("int")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextInteger());\n");
			}
			if (dc.getTipoJava().equals("float")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextFloat());\n");
			}
			if (dc.getTipoJava().equals("bool")) {
				outSource.write("      obj" + nombreTabla + ".set" + dc.getNombre()
						+ "( objParserCampos.getNextBoolean());\n");
			}
		}
		outSource.write("      ListaT" + nombreTabla + ".append(obj" + nombreTabla + "); \n");

		//outSource.write("      obj"+nombreTabla+".();\n");
		outSource.write("    }\n");
		outSource.write("  }\n");
		outSource.write("  return objRespuestaServ;\n");
		
		outSource.write("}\n\n");

		//			if ( vdcLlave.size() > 0 ) 
		//			{
		//				outSource.write("QString AT"+nombreTabla+" :: loadRecord()\n");
		//				outSource.write("{\n");		  	
		//				outSource.write("  QString sbPeticionServ;\n");
		//				outSource.write("  QString sbRespuestaServ;\n");
		//				outSource.write("  Parser objParser;\n");
		//				outSource.write("  Parser objParserCampos;\n\n");
		//				outSource.write("  sbPeticionServ  = CONSULTAR_"+nombreTabla.toUpperCase()+";\n");		  	
		//				for ( int i = 0 ; i < vdcLlave.size(); i ++ ) 
		//				{
		//					dc = (DefinicionCampo) vdcLlave.elementAt(i);
		//					if ( dc.getTipoJava().equals("QDate"))
		//					{
		//						outSource.write("  sbPeticionServ += SEPACAMPOS;\n");
		//						outSource.write("  sbPeticionServ += "+dc.getPrefijo()+dc.getNombre()+".toString("+sbComilla+"yyyy-MM-dd"+sbComilla+");\n");					
		//					}
		//					if ( dc.getTipoJava().equals("QTime"))
		//					{
		//						outSource.write("  sbPeticionServ += SEPACAMPOS;\n");
		//						outSource.write("  sbPeticionServ += "+dc.getPrefijo()+dc.getNombre()+".toString("+sbComilla+"hh:mm:ss"+sbComilla+");\n");
		//					}
		//					if ( dc.getTipoJava().equals("QString"))
		//					{
		//						outSource.write("  sbPeticionServ += SEPACAMPOS;\n");
		//						outSource.write("  sbPeticionServ += "+dc.getPrefijo()+dc.getNombre()+";\n");
		//					}
		//					if ( dc.getTipoJava().equals("int") || dc.getTipoJava().equals("float") )
		//					{
		//						outSource.write("  sbPeticionServ += SEPACAMPOS;\n");
		//						outSource.write("  sbPeticionServ += QString :: number ("+dc.getPrefijo()+dc.getNombre()+");\n");
		//					}		
		//				}
		//				outSource.write("  SocketRed.EscribirToken( sbPeticionServ );\n");
		//				outSource.write("  objParser.setCadena ( SocketRed.LeerToken() );\n");
		//				outSource.write("  SocketRed.CerrarControlRed();\n\n");
		//				outSource.write("  objParser.setSeparator ( SEPACAMPOS );\n");
		//				outSource.write("  objParser.ExecuteParser ();\n");
		//				outSource.write("  sbRespuestaServ = objParser.getNextString();\n");
		//				outSource.write("  if ( sbRespuestaServ == EXITO )\n");
		//				outSource.write("  {\n");
		//				outSource.write("    objParserCampos.setSeparator ( SEPACAMPOS );\n");
		//				outSource.write("    while (objParser.HasMoreToken() )\n");
		//				outSource.write("    {\n");
		//				outSource.write("      objParserCampos.setCadena ( objParser.getNextString() );\n");
		//				outSource.write("      objParserCampos.ExecuteParser ();\n");
		//			
		//				for ( int i = 0 ; i < vdc.size(); i ++ ) 
		//				{
		//					dc = (DefinicionCampo) vdc.elementAt(i);
		//					if ( dc.getTipoJava().equals("QDate"))
		//					{
		//						outSource.write( "      set"+dc.getNombre()+"(objParser.getNextDate());\n");					
		//					}
		//					if ( dc.getTipoJava().equals("QTime"))
		//					{
		//						outSource.write( "      set"+dc.getNombre()+"(objParser.getNextTime());\n");
		//					}
		//					if ( dc.getTipoJava().equals("QString"))
		//					{
		//						outSource.write( "      set"+dc.getNombre()+"(objParser.getNextString());\n");
		//					}
		//					if ( dc.getTipoJava().equals("int") )
		//					{
		//						outSource.write( "      set"+dc.getNombre()+"(objParser.getNextInteger());\n");
		//					}
		//					if ( dc.getTipoJava().equals("float") )
		//					{
		//						outSource.write( "      set"+dc.getNombre()+"(objParser.getNextFloat());\n");
		//					}						
		//					if ( dc.getTipoJava().equals("bool") )
		//					{
		//						outSource.write( "      set"+dc.getNombre()+"(objParser.getNextBoolean());\n");
		//					}				
		//				}
		//				outSource.write("    }\n");			
		//				outSource.write("  }\n");
		//				outSource.write("  return sbRespuestaServ;\n");
		//				outSource.write("}\n");		
		//			
		//			}

		/////////////////////////////////
		//			outSource.write("QString AT"+nombreTabla+" :: updateRecord()\n");
		//			outSource.write("{\n");		  	
		//			outSource.write("  QString sbPeticionServ;\n");
		//			outSource.write("  QString sbRespuestaServ;\n");
		//		
		//			outSource.write("  sbPeticionServ  = UPDATE_"+nombreTabla.toUpperCase()+";\n");		  	
		//			for ( int i = 0 ; i < vdc.size(); i ++ ) 
		//			{
		//				dc = (DefinicionCampo) vdc.elementAt(i);
		//				if ( dc.getTipoJava().equals("QDate"))
		//				{
		//					outSource.write("  sbPeticionServ += SEPACAMPOS + "+dc.getPrefijo()+dc.getNombre()+".toString("+sbComilla+"yyyy-MM-dd"+sbComilla+");\n");					
		//				}
		//				if ( dc.getTipoJava().equals("QTime"))
		//				{
		//					outSource.write("  sbPeticionServ += SEPACAMPOS +"+dc.getPrefijo()+dc.getNombre()+".toString("+sbComilla+"hh:mm:ss"+sbComilla+");\n");
		//				}
		//				if ( dc.getTipoJava().equals("QString"))
		//				{
		//					outSource.write("  sbPeticionServ += SEPACAMPOS + "+dc.getPrefijo()+dc.getNombre()+";\n");
		//				}
		//				if ( dc.getTipoJava().equals("bool"))
		//				{				
		//					outSource.write("  sbPeticionServ += SEPACAMPOS;\n");
		//					outSource.write("  if ("+dc.getPrefijo()+dc.getNombre()+" == TRUE)\n");
		//					outSource.write("  {\n");
		//					outSource.write("    sbPeticionServ += TRUE_CADENA;\n");
		//					outSource.write("  }\n");
		//					outSource.write("  else\n");
		//					outSource.write("  {\n");
		//					outSource.write("    sbPeticionServ += FALSE_CADENA;\n");
		//					outSource.write("  }\n");
		//				}
		//				if ( dc.getTipoJava().equals("int") || dc.getTipoJava().equals("float") )
		//				{
		//					outSource.write("  sbPeticionServ += SEPACAMPOS + QString :: number ("+dc.getPrefijo()+dc.getNombre()+");\n");
		//				}					
		//			}				
		//			outSource.write("  SocketRed.EscribirToken(sbPeticionServ);\n");
		//			outSource.write("  sbRespuestaServ = SocketRed.LeerToken();\n");
		//			outSource.write("  SocketRed.CerrarControlRed();\n");
		//			outSource.write("  return sbRespuestaServ;\n");
		//			outSource.write("}\n");

		outSource.flush();
		header.close();
	}

	public static void crearHeaderListaLibreriasProyecto(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioHeadersLibrerias + "Lista" + nombreTabla + ".h");
		//String espacios = new String("                                             ");
		String sbComilla = "\"";
		BufferedWriter outListaHeader = new BufferedWriter(header);
		DefinicionCampo dc;

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}

		outListaHeader.write("#ifndef __LISTA_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outListaHeader.write("#define __LISTA_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outListaHeader.write("\n\n");
		outListaHeader.write("/*\n");
		outListaHeader.write("    Propiedad intelectual de Play Tech. \n\n");
		outListaHeader.write("    Nombre  			:    Lista" + nombreTabla + ".h" + "\n");
		outListaHeader.write("    Descripcion  	:    " + "Definicion de lista de objetos " + nombreTabla + "\n");
		outListaHeader.write("    Autor  				:    " + "Play Tech." + "\n");
		outListaHeader.write("    Creacion  		:    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outListaHeader.write("    Historial de Modificaciones \n");
		outListaHeader.write("    Fecha    Autor    	Descripcion \n");
		outListaHeader.write("*/\n\n");
		outListaHeader.write("#include  " + sbComilla + "AT" + nombreTabla + ".h" + sbComilla + "\n");

		outListaHeader.write("#include <qstring.h>\n");
		outListaHeader.write("#include <qdatetime.h>\n\n\n");
		outListaHeader.write("class Lista" + nombreTabla + "\n");
		outListaHeader.write("{\n");

		//	Crea un constructor y destructor para la clase        
		outListaHeader.write("  public:\n");
		outListaHeader.write("    Lista" + nombreTabla + "();    /* Constructor " + nombreTabla + "*/\n");
		outListaHeader.write("    ~Lista" + nombreTabla + "();    /* Destructor " + nombreTabla + "*/\n\n");

		// Crea los metodos para asignar valor a los atributos de la clase		       		
		outListaHeader.write("    void insertRecord (AT" + nombreTabla + ");\n");
		outListaHeader.write("    void removeRecord ( it" + nombreTabla + " );\n\n");

		outListaHeader.write("    it" + nombreTabla + " get" + nombreTabla + "porIndice( int nuIndice );\n");

		String sbArgumentos = new String();
		String separador = new String(", ");

		if (vdcLlave.size() > 0) {
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				sbArgumentos += dc.getTipoJava() + " " + dc.getPrefijo() + "New" + dc.getNombre();
				if (i + 1 < vdcLlave.size()) {
					sbArgumentos += separador;
				}
			}
			outListaHeader
					.write("    it" + nombreTabla + " get" + nombreTabla + "porLlave ( " + sbArgumentos + " );\n");
		}

		outListaHeader.write("    it" + nombreTabla + " Begin ();\n");
		outListaHeader.write("    it" + nombreTabla + " End ();\n");
		outListaHeader.write("    void Clear ();\n");
		outListaHeader.write("    int Size();\n");

		outListaHeader.write("\n  /* Metodos de acceso a datos*/\n");
		outListaHeader.write("    QString loadListRecord ();\n");
		outListaHeader.write("    MensajeError loadRecords();\n\n");
		//outListaHeader.write("    QString updateListRecord ();\n\n");    			

		outListaHeader.write("  protected:\n");
		outListaHeader.write("    TLista" + nombreTabla + " ListaT" + nombreTabla + ";\n\n");

		outListaHeader.write("  public:\n");
		outListaHeader.write("    ControlRed SocketRed;");

		outListaHeader.write("\n\n};\n");
		outListaHeader.write("#endif\n");
		outListaHeader.flush();
		header.close();
	}
	
	public static void crearHeaderListaLibrerias(String nombreTabla, Vector vdc) throws IOException {

		Date dtFechaActual;
		Time tiHoraActual;
		java.util.Date udtFechaActual = new java.util.Date();
		dtFechaActual = new Date(udtFechaActual.getTime());
		tiHoraActual = new Time(udtFechaActual.getTime());

		FileWriter header = new FileWriter(Constante.directorioHeadersLibrerias + "Lista" + nombreTabla + ".h");
		//String espacios = new String("                                             ");
		String sbComilla = "\"";
		BufferedWriter outListaHeader = new BufferedWriter(header);
		DefinicionCampo dc;

		Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		for (int i = 0; i < vdc.size(); i++) {
			dc = (DefinicionCampo) vdc.elementAt(i);
			if (dc.getLlave().equals("S")) {
				vdcLlave.addElement(dc);
			}
		}

		outListaHeader.write("#ifndef __LISTA_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outListaHeader.write("#define __LISTA_" + nombreTabla.toUpperCase() + "_H__" + "\n");
		outListaHeader.write("\n\n");
		outListaHeader.write("/*\n");
		outListaHeader.write("    Propiedad intelectual de Play Tech. \n\n");
		outListaHeader.write("    Nombre  :    Lista" + nombreTabla + ".h" + "\n");
		outListaHeader.write("    Descripcion  :    " + "Definicion de lista de objetos " + nombreTabla + "\n");
		outListaHeader.write("    Autor  :    " + "Play Tech." + "\n");
		outListaHeader.write("    Creacion  :    " + dtFechaActual + " - " + tiHoraActual + "\n\n");
		outListaHeader.write("    Historial de Modificaciones \n");
		outListaHeader.write("    Fecha    Autor    	Descripcion \n");
		outListaHeader.write("*/\n\n");
		outListaHeader.write("#include  " + sbComilla + "Constantes.h" + sbComilla + "\n");
		outListaHeader.write("#include  " + sbComilla + "ControlRed.h" + sbComilla + "\n");
		outListaHeader.write("#include  " + sbComilla + "Utilidades.h" + sbComilla + "\n");
		outListaHeader.write("#include  " + sbComilla + "MensajeError.h" + sbComilla + "\n\n");
		outListaHeader.write("#include  " + sbComilla + "AT" + nombreTabla + ".h" + sbComilla + "\n");

		outListaHeader.write("#include <qstring.h>\n");
		outListaHeader.write("#include <qdatetime.h>\n\n\n");
		outListaHeader.write("class Lista" + nombreTabla + "\n");
		outListaHeader.write("{\n");

		//	Crea un constructor y destructor para la clase        
		outListaHeader.write("  public:\n");
		outListaHeader.write("    Lista" + nombreTabla + "();    /* Constructor " + nombreTabla + "*/\n");
		outListaHeader.write("    ~Lista" + nombreTabla + "();    /* Destructor " + nombreTabla + "*/\n\n");

		// Crea los metodos para asignar valor a los atributos de la clase		       		
		outListaHeader.write("    void insertRecord (AT" + nombreTabla + ");\n");
		outListaHeader.write("    void removeRecord ( it" + nombreTabla + " );\n\n");

		outListaHeader.write("    it" + nombreTabla + " get" + nombreTabla + "porIndice( int nuIndice );\n");

		String sbArgumentos = new String();
		String separador = new String(", ");

		if (vdcLlave.size() > 0) {
			for (int i = 0; i < vdcLlave.size(); i++) {
				dc = (DefinicionCampo) vdcLlave.elementAt(i);
				sbArgumentos += dc.getTipoJava() + " " + dc.getPrefijo() + "New" + dc.getNombre();
				if (i + 1 < vdcLlave.size()) {
					sbArgumentos += separador;
				}
			}
			outListaHeader
					.write("    it" + nombreTabla + " get" + nombreTabla + "porLlave ( " + sbArgumentos + " );\n");
		}

		outListaHeader.write("    it" + nombreTabla + " Begin ();\n");
		outListaHeader.write("    it" + nombreTabla + " End ();\n");
		outListaHeader.write("    void Clear ();\n");
		outListaHeader.write("    int Size();\n");

		outListaHeader.write("\n  /* Metodos de acceso a datos*/\n");
		outListaHeader.write("    QString loadListRecord ();\n");
		outListaHeader.write("    MensajeError loadRecords();\n\n");
		//outListaHeader.write("    QString updateListRecord ();\n\n");    			

		outListaHeader.write("  protected:\n");
		outListaHeader.write("    TLista" + nombreTabla + " ListaT" + nombreTabla + ";\n\n");

		outListaHeader.write("  public:\n");
		outListaHeader.write("    ControlRed SocketRed;");

		outListaHeader.write("\n\n};\n");
		outListaHeader.write("#endif\n");
		outListaHeader.flush();
		header.close();
	}

}