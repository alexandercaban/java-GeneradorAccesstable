package generador;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Properties;
import java.util.Vector;
public class CrearConstantes{
    
	public static void crearHeaders_Constantes_C_PLUS_PLUS( Vector vmt )
		throws IOException{
    	
			int nuCodigoFuncion = 3400;
			int nuCodigoFuncionIncremento;
			String sbNombreTabla;
    	
			Date dtFechaActual ;
			Time tiHoraActual;
			java.util.Date udtFechaActual =new java.util.Date();
			dtFechaActual = new Date( udtFechaActual.getTime() );
			tiHoraActual = new Time( udtFechaActual.getTime() );
	            
			FileWriter define = new FileWriter ( Constante.directorioHeaders+"ServiciosAccessTable.h" );
			BufferedWriter outDefine = new BufferedWriter ( define );             

			outDefine.write( "#ifndef __SERVICES_ACCESS_TABLES_H__"+Constante.finLinea );
			outDefine.write( "#define __SERVICES_ACCESS_TABLES_H__"+Constante.finLinea);					 		
			outDefine.write( "\n\n" );
			outDefine.write("/*\n");
			outDefine.write("    Propiedad intelectual de Play Tech. \n\n");
			outDefine.write("    Nombre  :    ServiciosAccessTable.h"+"\n");
			outDefine.write("    Descripcion  :    "+"Definicion  de constantes de servicios de accesstable.\n");
			outDefine.write("    Autor  :    "+"Play Tech."+"\n");
			outDefine.write("    Creacion  :    "+ dtFechaActual+" - "+ tiHoraActual+"\n\n");
			outDefine.write("    Historial de Modificaciones \n");
			outDefine.write("    Fecha    Autor    	Descripcion \n");
			outDefine.write("*/\n\n");
			outDefine.write( "#include <qstring.h>"+Constante.finLinea+Constante.finLinea );
			outDefine.write( "class ServiciosAccessTable {"+Constante.finLinea );
			outDefine.write( "  public :"+Constante.finLinea );
			
			FileInputStream flArchivo = new FileInputStream ( Constante.ArchivoReferencia );
			
			Properties objPropiedades = new Properties ();
			objPropiedades.load ( flArchivo );
			//int nuTemporal;
			String sbCadenaTemporal = new String ();			
							
			for ( int i=0; i < vmt.size();i++) {
				sbNombreTabla = (String)vmt.elementAt(i);
				if ( ! objPropiedades.containsKey("CONSULTAR_"+sbNombreTabla.toUpperCase()) ) {
					nuCodigoFuncionIncremento = nuCodigoFuncion;
					sbCadenaTemporal +="    static const QString Consultar_"+sbNombreTabla+";"+Constante.finLinea;
					nuCodigoFuncionIncremento += 10;
					sbCadenaTemporal += "    static const QString Update_"+sbNombreTabla+";"+Constante.finLinea;				
					nuCodigoFuncionIncremento += 10;
					sbCadenaTemporal += "    static const QString Consultar_Lista_"+sbNombreTabla+";"+Constante.finLinea+Constante.finLinea;				
					nuCodigoFuncion += 100;					
				}
				else {
						//nuTemporal = Integer.parseInt(objPropiedades.getProperty( ("CONSULTAR_"+sbNombreTabla.toUpperCase()) ) );
						outDefine.write("    static const QString Consultar_"+sbNombreTabla+";"+Constante.finLinea );
						outDefine.write("    static const QString Update_"+sbNombreTabla+";"+Constante.finLinea );
						outDefine.write("    static const QString Consultar_Lista_"+sbNombreTabla+";"+Constante.finLinea+Constante.finLinea );						
				}
			}

			outDefine.write(sbCadenaTemporal );			
/*			for ( int i=0; i < vmt.size();i++)				
			{
				sbNombreTabla = (String)vmt.elementAt(i);			
				                   
				nuCodigoFuncionIncremento = nuCodigoFuncion;
				outDefine.write( "    static const QString Consultar_"+sbNombreTabla+";"+Constante.finLinea );
				nuCodigoFuncionIncremento += 10;
				outDefine.write( "    static const QString Update_"+sbNombreTabla+";"+Constante.finLinea );				
				nuCodigoFuncionIncremento += 10;
				outDefine.write( "    static const QString Consultar_Lista_"+sbNombreTabla+";"+Constante.finLinea );				
				nuCodigoFuncion += 100;
			}*/
			
			
			outDefine.write("};"+Constante.finLinea+Constante.finLinea);
			outDefine.write("#endif\n");
			outDefine.flush();
			define.close();
		}						
		
	public static void crearSources_Constantes_C_PLUS_PLUS( Vector vmt )
		throws IOException{
	
			int nuCodigoFuncion = 3400;
			int nuCodigoFuncionIncremento;
			String sbNombreTabla;
	
			/*Date dtFechaActual ;
			Time tiHoraActual;
			java.util.Date udtFechaActual =new java.util.Date();
			dtFechaActual = new Date( udtFechaActual.getTime() );
			tiHoraActual = new Time( udtFechaActual.getTime() );*/
            
			FileWriter define = new FileWriter ( Constante.directorioSources+"ServiciosAccessTable.cpp" );
			BufferedWriter outDefine = new BufferedWriter ( define );             

			outDefine.write( "#include "+Constante.Comilla+"../headers/ServiciosAccessTable.h"+Constante.Comilla+Constante.finLinea );			
			outDefine.write( "#include <qstring.h>"+Constante.finLinea+Constante.finLinea );
			
			FileInputStream flArchivo = new FileInputStream ( Constante.ArchivoReferencia );
			
			Properties objPropiedades = new Properties ();
			objPropiedades.load ( flArchivo );
			int nuTemporal;
			String sbCadenaTemporal = new String();			
							
			for ( int i=0; i < vmt.size();i++) {
				sbNombreTabla = (String)vmt.elementAt(i);
				if ( ! objPropiedades.containsKey("CONSULTAR_"+sbNombreTabla.toUpperCase()) ) {
					nuCodigoFuncionIncremento = nuCodigoFuncion;
					sbCadenaTemporal += "const QString ServiciosAccessTable :: Consultar_"+sbNombreTabla+" = "+Constante.Comilla+nuCodigoFuncionIncremento+Constante.Comilla+";"+Constante.finLinea;
					nuCodigoFuncionIncremento += 10;
					sbCadenaTemporal += "const QString ServiciosAccessTable :: Update_"+sbNombreTabla+"="+Constante.Comilla+nuCodigoFuncionIncremento+Constante.Comilla+";"+Constante.finLinea;				
					nuCodigoFuncionIncremento += 10;
					sbCadenaTemporal += "const QString ServiciosAccessTable :: Consultar_Lista_"+sbNombreTabla+"="+Constante.Comilla+nuCodigoFuncionIncremento+Constante.Comilla+";"+Constante.finLinea+Constante.finLinea;				
					nuCodigoFuncion += 100;					
				}
				else {
						nuTemporal = Integer.parseInt(objPropiedades.getProperty( ("CONSULTAR_"+sbNombreTabla.toUpperCase()) ) );
						
						outDefine.write( "const QString ServiciosAccessTable :: Consultar_"+sbNombreTabla+" = "+Constante.Comilla+(nuTemporal)+Constante.Comilla+";"+Constante.finLinea );						
						outDefine.write( "const QString ServiciosAccessTable :: Update_"+sbNombreTabla+"="+Constante.Comilla+(nuTemporal+10)+Constante.Comilla+";"+Constante.finLinea );						
						outDefine.write( "const QString ServiciosAccessTable :: Consultar_Lista_"+sbNombreTabla+"="+Constante.Comilla+(nuTemporal+20)+Constante.Comilla+";"+Constante.finLinea+Constante.finLinea );				
						
				}
			}
			
			outDefine.write(sbCadenaTemporal );
			/*for ( int i=0; i < vmt.size();i++)				
			{
				sbNombreTabla = (String)vmt.elementAt(i);			
			                   
				nuCodigoFuncionIncremento = nuCodigoFuncion;
				outDefine.write( "const QString ServiciosAccessTable :: Consultar_"+sbNombreTabla+" = "+Constante.Comilla+nuCodigoFuncionIncremento+Constante.Comilla+";"+Constante.finLinea );
				nuCodigoFuncionIncremento += 10;
				outDefine.write( "const QString ServiciosAccessTable :: Update_"+sbNombreTabla+"="+Constante.Comilla+nuCodigoFuncionIncremento+Constante.Comilla+";"+Constante.finLinea );				
				nuCodigoFuncionIncremento += 10;
				outDefine.write( "const QString ServiciosAccessTable :: Consultar_Lista_"+sbNombreTabla+"="+Constante.Comilla+nuCodigoFuncionIncremento+Constante.Comilla+";"+Constante.finLinea+Constante.finLinea );				
				nuCodigoFuncion += 100;
			}*/			
			outDefine.flush();
			define.close();
		}
     			        
	public static void crearConstantesJava( Vector vmt )
		throws IOException{
	
			int nuCodigoFuncion = 3400;
			int nuCodigoFuncionIncremento;
			String sbNombreTabla;
	
			Date dtFechaActual ;
			Time tiHoraActual;
			java.util.Date udtFechaActual =new java.util.Date();
			dtFechaActual = new Date( udtFechaActual.getTime() );
			tiHoraActual = new Time( udtFechaActual.getTime() );
            
			FileWriter define = new FileWriter ( Constante.directorio+"ConstanteServicios.java" );
			BufferedWriter outDefine = new BufferedWriter ( define );             

			outDefine.write("/*"+Constante.Comilla);
			outDefine.write("    Propiedad intelectual de Play Tech. \n\n");
			outDefine.write("    Nombre      :    ConstanteServicios.java"+"\n");
			outDefine.write("    Descripcion :  Definicion de Constantes que identifican los servicios"+"\n\n");
			outDefine.write("    Autor       :    "+"Play Tech."+"\n");
			outDefine.write("    Creacion :    "+ dtFechaActual+" - "+ tiHoraActual+"\n\n");
			outDefine.write("    Historial de Modificaciones \n");
			outDefine.write("    Fecha    Autor    	Descripcion \n");
			outDefine.write("*/\n\n");
			outDefine.write("package easyplay.general.db.accesstable;\n\n");
			
			outDefine.write("    /* @author PlayTech \n");
			outDefine.write("     * @Version 1.0\n");
			outDefine.write("    */"+" \n\n");

			outDefine.write( "public class ConstanteServicios{"+Constante.finLinea );
			
			FileInputStream flArchivo = new FileInputStream ( Constante.ArchivoReferencia );
			
			Properties objPropiedades = new Properties ();
			objPropiedades.load ( flArchivo );
			int nuTemporal;
			String sbTemporal = new String ();
							
			for ( int i=0; i < vmt.size();i++) {
				sbNombreTabla = (String)vmt.elementAt(i);
				if ( ! objPropiedades.containsKey("CONSULTAR_"+sbNombreTabla.toUpperCase()) ) {
					nuCodigoFuncionIncremento = nuCodigoFuncion;
					sbTemporal += "  public static final int CONSULTAR_"+sbNombreTabla.toUpperCase()+" = "+nuCodigoFuncionIncremento+";"+Constante.finLinea;
					nuCodigoFuncionIncremento += 10;
					sbTemporal += "  public static final int UPDATE_"+sbNombreTabla.toUpperCase()+" = "+nuCodigoFuncionIncremento+";"+Constante.finLinea;
					nuCodigoFuncionIncremento += 10;
					sbTemporal += "  public static final int CONSULTAR_LISTA_"+sbNombreTabla.toUpperCase()+" = "+nuCodigoFuncionIncremento+";"+Constante.finLinea+Constante.finLinea;
					nuCodigoFuncion += 100;
				}
				else {
					nuTemporal = Integer.parseInt(objPropiedades.getProperty( ("CONSULTAR_"+sbNombreTabla.toUpperCase()) ) );
						outDefine.write("  public static final int CONSULTAR_"+sbNombreTabla.toUpperCase()+" = "+(nuTemporal)+";"+Constante.finLinea );
						outDefine.write("  public static final int UPDATE_"+sbNombreTabla.toUpperCase()+" = "+(nuTemporal+10)+";" +Constante.finLinea );
						outDefine.write("  public static final int CONSULTAR_LISTA_"+sbNombreTabla.toUpperCase()+" = "+(nuTemporal+20)+";"+Constante.finLinea+Constante.finLinea );						
				}
			}
		
			outDefine.write(sbTemporal);
			outDefine.write("}\n");
			outDefine.flush();
			define.close();
		}			
}