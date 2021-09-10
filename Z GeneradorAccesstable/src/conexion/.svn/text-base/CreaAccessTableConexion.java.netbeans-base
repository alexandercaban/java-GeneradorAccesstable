package conexion;
import generador.Constante;
import generador.DefinicionCampo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;
public class CreaAccessTableConexion
{
    public static void crearHeader( String nombreTabla,Vector vdc )
    throws IOException{
    	
    	Date dtFechaActual ;
    	Time tiHoraActual;
	    java.util.Date udtFechaActual =new java.util.Date();
	    dtFechaActual = new Date( udtFechaActual.getTime() );
	    tiHoraActual = new Time( udtFechaActual.getTime() );
		String sbArgumentos = new String();
		  String sbArgumentosCall = new String();
		  String sbArgumentosCallThis = new String();
		  String sbStringError = new String("\"");
		  String separador = new String();
		  String separador2 = new String();
	    
          FileWriter dat = new FileWriter(Constante.directorioHeaders+"AT"+nombreTabla+".h");         
          //String espacios = new String("                                             ");          
          BufferedWriter out = new BufferedWriter( dat );          
          DefinicionCampo dc;
          
		out.write( "#ifndef __ACCESS_TABLE_"+nombreTabla.toUpperCase()+ "_H__"+"\n" );
		out.write( "#define __ACCESS_TABLE_"+nombreTabla.toUpperCase()+"_H__"+"\n" );
		out.write( "\n\n" );        
		out.write("/*\n");
		out.write("    Propiedad intelectual de Play Tech. \n\n");
		out.write("    Nombre      :    AT"+nombreTabla+".h"+"\n");
		out.write("    Descripcion :    "+"Definicion objeto accesstable"+"\n\n");
		out.write("    Autor       :    "+"Play Tech."+"\n");
		out.write("    Creacion :    "+ dtFechaActual+" - "+ tiHoraActual+"\n\n");
		out.write("    Historial de Modificaciones \n");
		out.write("    Fecha    Autor    	Descripcion \n");
		out.write("*/\n\n");
		out.write( "#include  "+Constante.Comilla+"../../General/headers/Constantes.h"+Constante.Comilla+"\n");
		out.write( "#include  "+Constante.Comilla+"../../General/headers/ControlRed.h"+Constante.Comilla+"\n");
		out.write( "#include  "+Constante.Comilla+"../../General/headers/Utilidades.h"+Constante.Comilla+"\n");
		out.write( "#include  "+Constante.Comilla+"../../General/headers/Conexion.h"+Constante.Comilla+"\n");
		out.write("#include <qstring.h>\n");
		out.write("#include <qdatetime.h>\n\n\n");          

		out.write("//! la clase AT"+nombreTabla+" provee un objeto de acceso a datos de la tabla "+nombreTabla+Constante.finLinea);
			out.write("/*! AT"+nombreTabla+" permite un manejo de la la tabla "+nombreTabla+" dentro del esquema de Base de datos"+Constante.finLinea);
			out.write("  permitiendo tener acceso a los campos de la tabla por medio de metodos set y get para la manipulacion de Datos"+Constante.finLinea);
			out.write("*/"+Constante.finLinea);

			out.write("class AT"+nombreTabla+"\n");
			out.write("{\n");

			// Crea los atributos de la clase
			out.write("  /* Atributos */\n");
			out.write("  private:\n");
			for ( int i = 0 ; i < vdc.size(); i ++ )
			{
			  dc = (DefinicionCampo) vdc.elementAt(i);
			  out.write("    "+dc.getTipoJava()+" "+dc.getPrefijo()+dc.getNombre()+";\n");
			}
			out.write("    int nuIndice;\n\n");

			//  Crea un constructor y destructor para la clase
		    out.write("  protected:\n");
			out.write("    QStringList rcIndexQuery;\n");
			
			out.write("  public:\n");
			out.write("    //! constructor de la clase AT"+nombreTabla+Constante.finLinea);
			out.write("    /*! construye un objeto AT"+nombreTabla+" vacio con miembros numericos en -1 y miembros QString vacios"+Constante.finLinea);
			out.write("    */"+Constante.finLinea);
			out.write("    AT"+nombreTabla+"();  /* Constructor "+ nombreTabla +"*/\n");

			out.write("    ~AT"+nombreTabla+"();  /* Destructor "+ nombreTabla +"*/\n");

		  // Crea los metodos para asignar valor a los atributos de la clase
		  out.write("\n\n    /* Metodos Set de la clase*/\n");
		  
			Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
			for ( int i = 0 ; i < vdc.size(); i ++ ) 
			{
				dc = (DefinicionCampo) vdc.elementAt(i);
				if ( dc.getLlave().equals("S") ) 
				{
					vdcLlave.addElement(dc);
				 }
			}
			
		if ( vdcLlave.size() > 0 ) {

					separador = " ";
					separador2 = "";
					for ( int i = 0 ; i < vdcLlave.size(); i ++ ) 
					{
					  dc = (DefinicionCampo) vdcLlave.elementAt(i);
					  sbArgumentos += separador+dc.getTipoJava()+" "+dc.getPrefijo()+dc.getNombre();
					  sbArgumentosCall += separador+dc.getPrefijo()+dc.getNombre();
					  sbArgumentosCallThis += separador+"this."+dc.getPrefijo()+dc.getNombre();
					  sbStringError += separador2+dc.getNombre()+"=\"+"+dc.getPrefijo()+dc.getNombre();
					  separador = ", ";
					  separador2 = "+\",\"+\" ";
					}

				  }
				  
		  for ( int i = 0 ; i < vdc.size(); i ++ )
		  {
			dc = (DefinicionCampo) vdc.elementAt(i);
		  out.write("    void set"+dc.getNombre()+"("+dc.getTipoJava()+" "+dc.getPrefijo()+"New"+dc.getNombre()+");\n");
			}
			out.write("    void setIndice (int nuNewIndice);\n");

			out.write("\n\n    /* Metodos Get de la clase*/\n");
			for ( int i = 0 ; i < vdc.size(); i ++ )
			{
			  dc = (DefinicionCampo) vdc.elementAt(i);
			  out.write("    "+dc.getTipoJava()+" get"+dc.getNombre()+"();\n");
			}
			out.write("    int getIndice ();\n\n");
			out.write("  /* Acceso a datos por socket*/\n");
			out.write("  public:\n");
			out.write("    QString insertDB ();\n");
			out.write("    QString updateDB ();\n");			
			out.write("    QString loadDB ();\n");
			out.write("    QString deleteDB ();\n");
			out.write("    static bool existDB ("+sbArgumentos+");\n");			
			out.write("    bool blCamposRequeridos ();\n");

			out.write("\n\n};\n");
			out.write("typedef QValueList <AT"+nombreTabla+"> TLista"+nombreTabla+";\n");
			out.write("typedef TLista"+nombreTabla+" :: iterator it"+nombreTabla+";\n");
				out.write("#endif\n");
			out.flush();
			dat.close();             
    }
                   
	public static void crearSource( String nombreTabla,Vector vdc )
		throws IOException{       
          
          DefinicionCampo dc;
          
		  Date dtFechaActual ;
		  Time tiHoraActual;
		  java.util.Date udtFechaActual =new java.util.Date();
		  dtFechaActual = new Date( udtFechaActual.getTime() );
		  tiHoraActual = new Time( udtFechaActual.getTime() );

		  FileWriter header = new FileWriter ( Constante.directorioSources+"AT"+nombreTabla+".cpp" );
		  BufferedWriter out = new BufferedWriter ( header );
		  //String espacios = new String("                                             ");
          
          String sbArgumentos = new String();
          String sbArgumentosCall = new String();
          String sbArgumentosCallThis = new String();
          String sbStringError = new String("\"");
          String separador = new String();
          String separador2 = new String();
		  String primero;
		  String resto;          	
		  String sbNombreCampo;

          // arma cadenas de where          
		  Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
		  for ( int i = 0 ; i < vdc.size(); i ++ ) 
		  {
			  dc = (DefinicionCampo) vdc.elementAt(i);
			  if ( dc.getLlave().equals("S") ) 
			  {
				  vdcLlave.addElement(dc);			   
				  sbArgumentos += separador+dc.getTipoJava()+" "+dc.getPrefijo()+dc.getNombre();
				  sbArgumentosCall += separador+dc.getPrefijo()+dc.getNombre();
				  sbArgumentosCallThis += separador+"this."+dc.getPrefijo()+dc.getNombre();
				  sbStringError += separador2+dc.getNombre()+"=\"+"+dc.getPrefijo()+dc.getNombre();
				  separador = ", ";
				  separador2 = "+\",\"+\" ";
			  }
		  }
		            
		  out.write("/*\n");
		  out.write("    Propiedad intelectual de Play Tech. \n\n");
		  out.write("    Nombre  :    AT"+nombreTabla+".cpp"+"\n");
		  out.write("    Descripcion  :    "+"Definicion fuentes de accestable de tabla"+nombreTabla+"\n");
		  out.write("    Autor  :    "+"Play Tech."+"\n");
		  out.write("    Creacion  :    "+ dtFechaActual+" - "+ tiHoraActual+"\n\n");
		  out.write("    Historial de Modificaciones \n");
		  out.write("    Fecha    Autor      Descripcion \n");
		  out.write("*/\n\n");
		  out.write( "#include  "+Constante.Comilla+"../../General/headers/Parser.h"+Constante.Comilla+"\n\n");
		  out.write( "#include  "+Constante.Comilla+"../headers/AT"+nombreTabla+".h"+Constante.Comilla+"\n");
		  out.write( "#include  "+Constante.Comilla+"../headers/ServiciosAccessTable.h"+Constante.Comilla+"\n");
		  out.write("#include <qstring.h>\n");
		  out.write("#include <qdatetime.h>\n\n\n");

		  //  Crea un constructor y destructor para la clase
		  out.write("/* Constructor "+ nombreTabla +"*/\n");
		  out.write("AT"+nombreTabla+" :: AT"+nombreTabla+"()\n");
		  out.write("{\n");

		  //asigna valores recuparados al objeto
		  for ( int i = 0 ; i < vdc.size(); i ++ )
		  {
			dc = (DefinicionCampo) vdc.elementAt(i);
			String tipoJava = dc.getTipoJava();
			primero = tipoJava.substring(0,1);
			resto   = tipoJava.substring(1,tipoJava.length());
			tipoJava = primero.toUpperCase() + resto;

			if ( tipoJava.equals("Int") )
			{
			  out.write("  "+dc.getPrefijo()+dc.getNombre()+" = -1"+";\n");
			}

			if ( tipoJava.equals("Float") )
			{
			  out.write("  "+dc.getPrefijo()+dc.getNombre()+" = -1"+";\n");
			}
			if ( tipoJava.equals("QString") )
			{
			  out.write("  "+dc.getPrefijo()+dc.getNombre()+" = "+Constante.Comilla+Constante.Comilla+";\n");
			}
		  }
		  for ( int i=0; i < vdcLlave.size();i++)
		  {
		  	dc = (DefinicionCampo) vdcLlave.elementAt(i);

		  	primero = dc.getNombre().substring(0,1).toLowerCase();
		  	resto   = dc.getNombre().substring(1,dc.getNombre().length());          	

		  	sbNombreCampo = primero + resto; 
		  	out.write("  rcIndexQuery += "+Constante.Comilla+sbNombreCampo+Constante.Comilla+";"+Constante.finLinea);
		  }		  
		  out.write("}\n");
		  out.write("\n");

		  out.write("/* Destructor "+ nombreTabla +"*/\n");
		  out.write("AT"+nombreTabla+" :: ~AT"+nombreTabla+"()\n");
		  out.write("{\n}\n");

		  // Crea los metodos para asignar valor a los atributos de la clase
		  out.write("\n\n/* Metodos Set de la clase*/\n");
		  for ( int i = 0 ; i < vdc.size(); i ++ )
		  {
			dc = (DefinicionCampo) vdc.elementAt(i);
			out.write("void AT"+nombreTabla+" :: set"+dc.getNombre()+"("+dc.getTipoJava()+" "+dc.getPrefijo()+"New"+dc.getNombre()+")\n");
			out.write("{\n");
			out.write("  "+dc.getPrefijo()+dc.getNombre()+" = "+dc.getPrefijo()+"New"+dc.getNombre()+";\n");
			out.write("}\n\n");
		  }
		  out.write("void AT"+nombreTabla+" :: setIndice (int nuNewIndice)\n");
		  out.write("{\n");
		  out.write("  nuIndice = nuNewIndice;\n");
		  out.write("}\n");

		  out.write("\n\n/* Metodos Get de la clase*/\n");
		  for ( int i = 0 ; i < vdc.size(); i ++ )
		  {
			dc = (DefinicionCampo) vdc.elementAt(i);
			out.write( dc.getTipoJava()+" AT"+nombreTabla+" :: get"+dc.getNombre()+"()\n");
			out.write("{\n");
			out.write("  return "+dc.getPrefijo()+dc.getNombre()+";\n");
			out.write("}\n\n");
		  }
		  out.write("int AT"+nombreTabla+" :: getIndice ()\n");
		  out.write("{\n");
		  out.write("  return nuIndice;\n");
		  out.write("}\n");          

          // Crea el metodo de insercion en la base de datos
          out.write("QString AT"+nombreTabla+" :: insertDB()\n");
          out.write("{\n");
          out.write("  QSqlCursor stmInsert"+nombreTabla+";\n");          
          out.write("  QString sbError;"+Constante.finLinea);
		  out.write("  QSqlIndex idx"+nombreTabla+";"+Constante.finLinea);
		  out.write("  QSqlRecord * sqlBuffer"+nombreTabla+";"+Constante.finLinea);
          
		  out.write("  stmInsert"+nombreTabla+" = Conexion :: getPreparedStatement ("+Constante.Comilla+nombreTabla+
		  		Constante.Comilla+");"+Constante.finLinea);          
          
          out.write ("  idx"+nombreTabla+"=stmInsert"+nombreTabla+".index (rcIndexQuery);"+Constante.finLinea);                   
          out.write ("  sqlBuffer"+nombreTabla+"=stmInsert"+nombreTabla+".primeInsert();"+Constante.finLinea);          
          for ( int i = 0 ; i < vdc.size(); i ++ ) 
          {
            dc = (DefinicionCampo) vdc.elementAt(i);
			primero = dc.getNombre().substring(0,1).toLowerCase();
			resto   = dc.getNombre().substring(1,dc.getNombre().length());          	
          	sbNombreCampo = primero + resto; 
                   
            String tipoJava = dc.getTipoJava();
            primero = tipoJava.substring(0,1);
            resto   = tipoJava.substring(1,tipoJava.length());
            tipoJava = primero.toUpperCase() + resto;
            if ( tipoJava.equals("Int") ) 
            {
                out.write("  if ( "+dc.getPrefijo()+dc.getNombre()+" == Constantes :: Int_Null )"+Constante.finLinea);
                out.write("  {\n");
                out.write("    stmInsert"+nombreTabla+".setNull( "+Constante.Comilla+sbNombreCampo+Constante.Comilla+" );\n");
                out.write("  }"+Constante.finLinea);
                out.write("  else\n");
                out.write("  { \n");
				out.write("    sqlBuffer"+nombreTabla+"->setValue ( "+Constante.Comilla+sbNombreCampo+Constante.Comilla+","+dc.getPrefijo()+dc.getNombre()+" );"+Constante.finLinea);
                out.write("  }\n");
            } 
            else if ( tipoJava.equals("Float") ) 
            {
                out.write("  if ( "+dc.getPrefijo()+dc.getNombre()+" == Constantes :: Int_Null )"+Constante.finLinea);
                out.write("  {\n");
                out.write("    stmInsert"+nombreTabla+".setNull( "+Constante.Comilla+sbNombreCampo+Constante.Comilla+" );\n");
                out.write("  }"+Constante.finLinea);
                out.write("  else"+Constante.finLinea);
                out.write("  {"+Constante.finLinea);
				out.write("    sqlBuffer"+nombreTabla+"->setValue ( "+Constante.Comilla+sbNombreCampo+Constante.Comilla+","+dc.getPrefijo()+dc.getNombre()+" );"+Constante.finLinea);
                out.write("  }"+Constante.finLinea);
            } 
            else 
            {
				out.write("  sqlBuffer"+nombreTabla+"->setValue ( "+Constante.Comilla+sbNombreCampo+Constante.Comilla+","+dc.getPrefijo()+dc.getNombre()+" );"+Constante.finLinea);
            }
          }
          out.write(Constante.finLinea);
          out.write("  stmInsert"+nombreTabla+".insert();"+Constante.finLinea);
		  out.write("  if (stmInsert"+nombreTabla+".lastError ().type() == QSqlError::None )"+Constante.finLinea);
		  out.write("  {"+Constante.finLinea);
		  out.write("    Conexion :: commit();"+Constante.finLinea);
		  out.write("    return Constantes :: Exito;"+Constante.finLinea);
		  out.write("  }"+Constante.finLinea);
          out.write("  else"+Constante.finLinea);
          out.write("  {"+Constante.finLinea);
          out.write("    Conexion :: getErrorString (stmInsert"+nombreTabla+".lastError());"+Constante.finLinea);          
		  out.write("    sbError += stmInsert"+nombreTabla+".lastError().text();"+Constante.finLinea);
		  out.write("    Conexion :: rollBack();"+Constante.finLinea);
		  out.write("    return sbError;"+Constante.finLinea);
		  out.write("  }"+Constante.finLinea);          
          out.write("}"+Constante.finLinea);

          // arma el where para los caso que tenga llave primaria
          //String sbWhere = new String("    \" Where ");
		  String sbWhere = new String();
          separador = "";
          if ( vdcLlave.size() > 0 ) {
              for ( int i = 0 ; i < vdcLlave.size(); i ++ ) {
                  dc = (DefinicionCampo) vdcLlave.elementAt(i);
                  sbWhere = sbWhere+separador+dc.getNombre()+"= "+dc.getPrefijo()+dc.getNombre()+"\"";
                  separador = "+\n    \" and ";
              }
          }
          // si  tiene llave primaria no genera los metodos de actualizacion
          if ( vdcLlave.size() > 0 ) {

              //crea el metodo para la actualizacion en la base de datos
              out.write("QString AT"+nombreTabla+" :: updateDB()"+Constante.finLinea);
              out.write("{\n");			  					               
              out.write("  QSqlCursor stmUpdate"+nombreTabla+";"+Constante.finLinea);
			  out.write("  QSqlRecord * sqlBuffer"+nombreTabla+";"+Constante.finLinea);
			  out.write("  QSqlIndex idx"+nombreTabla+";"+Constante.finLinea);			  
			  out.write("  QString sbError;"+Constante.finLinea+Constante.finLinea);              
			  out.write("  stmUpdate"+nombreTabla+" = Conexion :: getPreparedStatement ("+Constante.Comilla+nombreTabla+
							Constante.Comilla+");"+Constante.finLinea);
              
			out.write ("  idx"+nombreTabla+"=stmUpdate"+nombreTabla+".index (rcIndexQuery);"+Constante.finLinea);
			out.write ("  stmUpdate"+nombreTabla+".select (idx"+nombreTabla+",idx"+nombreTabla+");"+Constante.finLinea);			                   
			out.write ("  if ( stmUpdate"+nombreTabla+".next() )"+Constante.finLinea);
			out.write ("  {"+Constante.finLinea);
			out.write ("    sqlBuffer"+nombreTabla+"=stmUpdate"+nombreTabla+".primeUpdate();"+Constante.finLinea);          
			for ( int i = 0 ; i < vdc.size(); i ++ ) 
			{
			  dc = (DefinicionCampo) vdc.elementAt(i);
			  primero = dc.getNombre().substring(0,1).toLowerCase();
			  resto   = dc.getNombre().substring(1,dc.getNombre().length());          	
			  sbNombreCampo = primero + resto; 
	                   
			  String tipoJava = dc.getTipoJava();
			  primero = tipoJava.substring(0,1);
			  resto   = tipoJava.substring(1,tipoJava.length());
			  tipoJava = primero.toUpperCase() + resto;
			  if ( tipoJava.equals("Int") ) 
			  {
				  out.write("    if ( "+dc.getPrefijo()+dc.getNombre()+" == Constantes :: Int_Null )"+Constante.finLinea);
				  out.write("    {"+Constante.finLinea);
				  out.write("      stmUpdate"+nombreTabla+".setNull( "+Constante.Comilla+sbNombreCampo+Constante.Comilla+" );"+Constante.finLinea);
				  out.write("    }"+Constante.finLinea);
				  out.write("    else"+Constante.finLinea);
				  out.write("    {"+Constante.finLinea);
				  out.write("      sqlBuffer"+nombreTabla+"->setValue ( "+Constante.Comilla+sbNombreCampo+Constante.Comilla+","+dc.getPrefijo()+dc.getNombre()+" );"+Constante.finLinea);
				  out.write("    }"+Constante.finLinea);
			  } 
			  else if ( tipoJava.equals("Float") ) 
			  {
				  out.write("    if ( "+dc.getPrefijo()+dc.getNombre()+" == Constantes :: Int_Null )"+Constante.finLinea);
				  out.write("    {"+Constante.finLinea);
				  out.write("      stmUpdate"+nombreTabla+".setNull( "+Constante.Comilla+sbNombreCampo+Constante.Comilla+" );"+Constante.finLinea);
				  out.write("    }"+Constante.finLinea);
				  out.write("    else"+Constante.finLinea);
				  out.write("    {"+Constante.finLinea);
				  out.write("      sqlBuffer"+nombreTabla+"->setValue ( "+Constante.Comilla+sbNombreCampo+Constante.Comilla+","+dc.getPrefijo()+dc.getNombre()+" );"+Constante.finLinea);
				  out.write("    }"+Constante.finLinea);
			  } 			  else 
			  {
				  out.write("    sqlBuffer"+nombreTabla+"->setValue ( "+Constante.Comilla+sbNombreCampo+Constante.Comilla+","+dc.getPrefijo()+dc.getNombre()+" );"+Constante.finLinea);
			  }
			}
			out.write(Constante.finLinea);
			out.write("    stmUpdate"+nombreTabla+".update();"+Constante.finLinea);
			out.write("    if (stmUpdate"+nombreTabla+".lastError ().type() == QSqlError::None )"+Constante.finLinea);
			out.write("    {"+Constante.finLinea);
			out.write("      Conexion :: commit();"+Constante.finLinea);
			out.write("      return Constantes :: Exito;"+Constante.finLinea);
			out.write("    }"+Constante.finLinea);
			out.write("    else"+Constante.finLinea);
			out.write("    {"+Constante.finLinea);
			out.write("      Conexion :: getErrorString (stmUpdate"+nombreTabla+".lastError());"+Constante.finLinea);          
			out.write("      sbError += stmUpdate"+nombreTabla+".lastError().text();"+Constante.finLinea);
			out.write("      Conexion :: rollBack();"+Constante.finLinea);
			out.write("      return sbError;"+Constante.finLinea);
			out.write("    }"+Constante.finLinea);
			out.write("  }"+Constante.finLinea);
			out.write("  else"+Constante.finLinea);
			out.write("  {"+Constante.finLinea);
			out.write("    return ConstanteError :: Registro_No_Existe;"+Constante.finLinea);
			out.write("  }"+Constante.finLinea);          
			out.write("}"+Constante.finLinea+Constante.finLinea);
          }

          // si  tiene llave primaria genera el metodos de recuperacion de base de datos
          if ( vdcLlave.size() > 0 ) {


              out.write("QString AT"+nombreTabla+"::loadDB()"+Constante.finLinea);
              out.write("{"+Constante.finLinea);              
              out.write("  QSqlCursor stmLoad"+nombreTabla+";"+Constante.finLinea);
              out.write("  QSqlRecord sbLoad"+nombreTabla+";"+Constante.finLinea);
              out.write("  QSqlIndex idx"+nombreTabla+";"+Constante.finLinea);              
              out.write("  QString sbError;"+Constante.finLinea+Constante.finLinea);
			  out.write("  stmLoad"+nombreTabla+" = Conexion :: getPreparedStatement( "+Constante.Comilla+nombreTabla+
					Constante.Comilla+" );"+Constante.finLinea);
			  
			  out.write("  idx"+nombreTabla+"=stmLoad"+nombreTabla+".index(rcIndexQuery);"+Constante.finLinea);
			  out.write("  stmLoad"+nombreTabla+".select (idx"+nombreTabla+",idx"+nombreTabla+" );"+Constante.finLinea);
			  out.write("  if (stmLoad"+nombreTabla+".next() )"+Constante.finLinea);
			  out.write("  {"+Constante.finLinea);

              for ( int i = 0 ; i < vdc.size(); i ++ ) {
                  dc = (DefinicionCampo) vdc.elementAt(i);
                  String tipoJava = dc.getTipoJava();
                  primero = tipoJava.substring(0,1);
                  resto   = tipoJava.substring(1,tipoJava.length());
                  tipoJava = primero.toUpperCase() + resto;
                  
				  primero = dc.getNombre().substring(0,1).toLowerCase();
				  resto   = dc.getNombre().substring(1,dc.getNombre().length());          	
	          	
				  sbNombreCampo = primero + resto;
				  if ( dc.getTipoJava().equals("float"))
				  {
				    out.write("    "+dc.getPrefijo()+dc.getNombre()+" = stmLoad"+nombreTabla+".value ("+Constante.Comilla+sbNombreCampo+Constante.Comilla+
											").toDouble();\n");
				  }
				  else if ( dc.getTipoJava().equals ("QString") || dc.getTipoJava().equals ("QDate") || dc.getTipoJava().equals ("QTime"))
				  {
					resto   = dc.getTipoJava().substring(1,dc.getTipoJava().length());
					out.write("    "+dc.getPrefijo()+dc.getNombre()+" = stmLoad"+nombreTabla+".value ("+Constante.Comilla+sbNombreCampo+Constante.Comilla+
											").to"+resto+"();\n");
				  }
                  else if ( dc.getTipoJava().equals("int"))
                  {
                  	out.write("    "+dc.getPrefijo()+dc.getNombre()+" = stmLoad"+nombreTabla+".value ("+Constante.Comilla+sbNombreCampo+Constante.Comilla+
											").toInt();\n");
                  }
                  else if ( dc.getTipoJava().equals("int"))
                  {					
					out.write("    "+dc.getPrefijo()+dc.getNombre()+" = stmLoad"+nombreTabla+".value ("+Constante.Comilla+sbNombreCampo+Constante.Comilla+
											").toInt();\n");
                  }
                  else
                  {					
					out.write("    "+dc.getPrefijo()+dc.getNombre()+" = stmLoad"+nombreTabla+".value ("+Constante.Comilla+sbNombreCampo+Constante.Comilla);
					primero = dc.getTipoJava().substring(0,1).toUpperCase();
					resto   = dc.getTipoJava().substring(1,dc.getTipoJava().length());	
					sbNombreCampo = primero + resto;
					out.write(").to"+sbNombreCampo+"();"+Constante.finLinea);					
                  }					
              }
   			  out.write("    return Constantes :: Exito;"+Constante.finLinea);			  
              out.write("  }"+Constante.finLinea);
			  out.write("  else"+Constante.finLinea);
              out.write("  {"+Constante.finLinea);
              out.write("   return ConstanteError :: Registro_No_Existe;"+Constante.finLinea);
			  out.write("  }"+Constante.finLinea);
              out.write("}\n");
          }

          // si  tiene llave primaria genera el metodos para el borrado del registro
          if ( vdcLlave.size() > 0 ) {

              //crea el metodo para la actualizacion en la base de datos
              out.write("QString AT"+nombreTabla+" :: deleteDB()\n");
              out.write("{"+Constante.finLinea);              
			  out.write("  QSqlCursor stmDelete"+nombreTabla+";"+Constante.finLinea);			  
			  out.write("  QSqlIndex idx"+nombreTabla+";"+Constante.finLinea);
			  
			  out.write("  QString sbError;"+Constante.finLinea+Constante.finLinea);
			  out.write("  stmDelete"+nombreTabla+" = Conexion :: getPreparedStatement( "+Constante.Comilla+nombreTabla+
					Constante.Comilla+" );"+Constante.finLinea);

			  out.write("  idx"+nombreTabla+"=stmDelete"+nombreTabla+".index(rcIndexQuery);"+Constante.finLinea);
			  out.write("  stmDelete"+nombreTabla+".select (idx"+nombreTabla+",idx"+nombreTabla+" );"+Constante.finLinea);
			  out.write("  if (stmDelete"+nombreTabla+".next() )"+Constante.finLinea);
			  out.write("  {"+Constante.finLinea);
			  out.write("    stmDelete"+nombreTabla+".primeDelete();"+Constante.finLinea);
			  out.write("    stmDelete"+nombreTabla+".del();"+Constante.finLinea);
			  out.write("    if ( stmDelete"+nombreTabla+".lastError ().type() == QSqlError::None )"+Constante.finLinea);
			  out.write("    {"+Constante.finLinea);
			  out.write("      Conexion :: commit();"+Constante.finLinea);
			  out.write("      return Constantes :: Exito;"+Constante.finLinea);
			  out.write("    }"+Constante.finLinea);
			  out.write("    else"+Constante.finLinea);
			  out.write("    {"+Constante.finLinea);
			  out.write("      Conexion :: getErrorString (stmDelete"+nombreTabla+".lastError());"+Constante.finLinea);          
			  out.write("      sbError += stmDelete"+nombreTabla+".lastError().text();"+Constante.finLinea);
			  out.write("      Conexion :: rollBack();"+Constante.finLinea);
			  out.write("      return sbError;"+Constante.finLinea);
			  out.write("    }"+Constante.finLinea);
			  out.write("  }"+Constante.finLinea);
			  out.write("  else"+Constante.finLinea);
			  out.write("  {"+Constante.finLinea);
			  out.write("    return ConstanteError :: Registro_No_Existe;"+Constante.finLinea);
			  out.write("  }"+Constante.finLinea);          
			  out.write("}"+Constante.finLinea+Constante.finLinea);
          }

          // si  tiene llave primaria genera el metodos de  de verificacion de
          if ( vdcLlave.size() > 0 ) {

              out.write("bool AT"+nombreTabla+" :: existDB("+sbArgumentos+")\n");              
              out.write("{"+Constante.finLinea);
              out.write("  QSqlCursor stmExist"+nombreTabla+";"+Constante.finLinea);			  			  
			  out.write("  QSqlIndex idx"+nombreTabla+";"+Constante.finLinea);
			  out.write("  QString sbError;"+Constante.finLinea+Constante.finLinea);
			  out.write("  QStringList rcIndexQueryLocal;"+Constante.finLinea+Constante.finLinea);
			  out.write("  stmExist"+nombreTabla+" = Conexion :: getPreparedStatement( "+Constante.Comilla+nombreTabla+
								Constante.Comilla+" );"+Constante.finLinea);
			for ( int i=0; i < vdcLlave.size();i++)
					  {
						dc = (DefinicionCampo) vdcLlave.elementAt(i);

						primero = dc.getNombre().substring(0,1).toLowerCase();
						resto   = dc.getNombre().substring(1,dc.getNombre().length());          	

						sbNombreCampo = primero + resto; 
						out.write("  rcIndexQueryLocal += "+Constante.Comilla+sbNombreCampo+Constante.Comilla+";"+Constante.finLinea);
					  }              

			  out.write("  idx"+nombreTabla+"=stmExist"+nombreTabla+".index(rcIndexQueryLocal);"+Constante.finLinea);
			  out.write("  stmExist"+nombreTabla+".select (idx"+nombreTabla+",idx"+nombreTabla+" );"+Constante.finLinea);
			  out.write("  if (stmExist"+nombreTabla+".next() )"+Constante.finLinea);
			  out.write("  {"+Constante.finLinea);			      
			  out.write("      return TRUE;"+Constante.finLinea);			  
			  out.write("  }"+Constante.finLinea);
			  out.write("  else"+Constante.finLinea);
			  out.write("  {"+Constante.finLinea);
			  out.write("    return FALSE;"+Constante.finLinea);
			  out.write("  }"+Constante.finLinea);          
			  out.write("}"+Constante.finLinea+Constante.finLinea);
              }                    
          out.flush();
          header.close();
    }



}