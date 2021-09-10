package generador;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
public class CreaMainWindowUI{
    public static void crearMainWindowUI( Vector v )
    throws IOException{
    	
    	//Date dtFechaActual ;
    	//Time tiHoraActual;
	    //java.util.Date udtFechaActual =new java.util.Date();
	    //dtFechaActual = new Date( udtFechaActual.getTime() );
	    //tiHoraActual = new Time( udtFechaActual.getTime() );
	            
        FileWriter header = new FileWriter ( Constante.directorioUI+"frmMainWindow.ui" );
		BufferedWriter outSource = new BufferedWriter ( header );
        String sbIdentacion = new String("    ");
        String sbCopiaIdentacion = new String( sbIdentacion );               
        int nuTamanoIdentacion = 4;
        int nuAnchoWidget = 600;

                        
		outSource.write("<!DOCTYPE UI><UI version="+Constante.Comilla+"3.2"+Constante.Comilla+" stdsetdef="+Constante.Comilla+"1"+Constante.Comilla+">\n");				
		outSource.write("<class>frmMainWindow</class>\n");
		outSource.write("<widget class ="+Constante.Comilla+"QMainWindow"+Constante.Comilla+">\n");
		
		// segmento de manejo de las propiedades de nombre y geometria
		outSource.write(sbCopiaIdentacion+tagProperty("name"));
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+tagcstring("frmMainWindow"));
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+tagCierreProperty());
		
		outSource.write(sbCopiaIdentacion+tagProperty("geometry") );
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+"<rect>\n");
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+"<x>0</x>\n");
		outSource.write(sbCopiaIdentacion+"<y>0</y>\n");		
		outSource.write(sbCopiaIdentacion+"<width>320</width>\n");
		outSource.write(sbCopiaIdentacion+"<height>"+nuAnchoWidget+"</height>\n");
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+"</rect>\n");
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+tagCierreProperty());		
		
		outSource.write(sbCopiaIdentacion+tagProperty("caption") );
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+"<string>Principal</string>\n");
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+tagCierreProperty());
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write("</widget>"+Constante.finLinea);		
		
		outSource.write("<menubar>"+Constante.finLinea);
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+tagProperty("name"));
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+tagcstring("menubar"));
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+tagCierreProperty());
		
		outSource.write(sbCopiaIdentacion+tagProperty("geometry"));
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+"<rect>\n");
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+"<x>0</x>\n");
		outSource.write(sbCopiaIdentacion+"<y>0</y>\n");		
		outSource.write(sbCopiaIdentacion+"<width>320</width>\n");
		outSource.write(sbCopiaIdentacion+"<height>"+nuAnchoWidget+"</height>\n");
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+"</rect>\n");
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+tagCierreProperty());
		
		
		//	crea el label y el control adecuado para el campo
		//asigna valores recuparados al objeto
		String sbNombreTabla;		
		for ( int i = 0 ; i < v.size(); i ++ ) 
		{
			sbNombreTabla = (String) v.elementAt(i);

			if ( i%8 == 0)
			{ 
				sbCopiaIdentacion = sbIdentacion;
				outSource.write(sbCopiaIdentacion+tagItemMenu("Archivo"+i,"archivoMenu"+i));						
				sbCopiaIdentacion += sbIdentacion;
			}
			
			outSource.write(sbCopiaIdentacion+"<action name = "+Constante.Comilla+"Accion"+sbNombreTabla+Constante.Comilla+"/>"+Constante.finLinea);
			if ( (i+1)%8 == 0)
			{
				sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
				outSource.write(sbCopiaIdentacion+tagCloseItemMenu());								
			}
		}		
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+tagCloseItemMenu());
		
		//sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+"</menubar>\n");
		
		outSource.write("<actions>"+Constante.finLinea);		
		for ( int i = 0 ; i < v.size(); i ++ ) 
		{
			sbNombreTabla = (String) v.elementAt(i);
			sbCopiaIdentacion = sbIdentacion;			
			outSource.write(sbCopiaIdentacion+"<action>"+Constante.finLinea);
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+tagProperty("name"));
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+tagcstring("Accion"+sbNombreTabla));			
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+tagCierreProperty());
			
//			outSource.write(sbCopiaIdentacion+tagProperty("iconset"));
//			sbCopiaIdentacion += sbIdentacion;
//			outSource.write(sbCopiaIdentacion+tagiconset(""));			
//			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
//			outSource.write(sbCopiaIdentacion+tagCierreProperty());
			
			outSource.write(sbCopiaIdentacion+tagProperty("text"));
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+tagString(sbNombreTabla));			
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+tagCierreProperty());
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+"</action>"+Constante.finLinea);
						 								
		}
		outSource.write("</actions>"+Constante.finLinea);
		
		
		//outSource.write(sbCopiaIdentacion+"</widget>\n");
		outSource.write("<layoutdefaults spacing="+Constante.Comilla+"6"+Constante.Comilla+" margin="+Constante.Comilla+"11"+Constante.Comilla+" />\n");
		outSource.write("</UI>\n");

		outSource.flush();
		header.close();		  
    }

  
    
    public static String tagProperty ( String sbNombreTag )
	throws IOException	
    {
    	String sbCuerpoTag = new String ();
    	sbCuerpoTag = "<property name = "+Constante.Comilla+sbNombreTag+Constante.Comilla+">"+Constante.finLinea;
    	return sbCuerpoTag;	
    }  	
    
	public static String tagCierreProperty (  )
		throws IOException	
		{
			String sbCuerpoTag = new String ();
			sbCuerpoTag = "</property>"+Constante.finLinea;
			return sbCuerpoTag;	
		}
    
	public static String tagcstring ( String sbNombre )
	throws IOException	
	{
		String sbCuerpoTag = new String ();
		sbCuerpoTag = "<cstring>"+sbNombre+"</cstring>"+Constante.finLinea;
		return sbCuerpoTag;	
	}
	
	public static String tagString ( String sbNombre )
	throws IOException	
	{
		String sbCuerpoTag = new String ();
		sbCuerpoTag = "<string>"+sbNombre+"</string>"+Constante.finLinea;
		return sbCuerpoTag;	
	}	
	
	public static String tagiconset ( String sbNombre )
		throws IOException	
		{
			String sbCuerpoTag = new String ();
			sbCuerpoTag = "<iconset>"+sbNombre+"</iconset>"+Constante.finLinea;
			return sbCuerpoTag;	
		}
	
	public static String tagItemMenu ( String sbNombre, String sbTexto )
	throws IOException	
	{
		String sbCuerpoTag = new String ();
		sbCuerpoTag = "<item text = "+Constante.Comilla+Constante.buddy+sbTexto+Constante.Comilla+" name= "+Constante.Comilla+sbNombre+Constante.Comilla+">"+Constante.finLinea;
		return sbCuerpoTag;	
	}
	
	public static String tagCloseItemMenu ()
	throws IOException	
	{
		String sbCuerpoTag = new String ();
		sbCuerpoTag = "</item>"+Constante.finLinea;
		return sbCuerpoTag;	
	}	
	
	public static String tagAbrirWidget ( String sbCadena )
		throws IOException	
		{
			String sbCuerpoTag = new String ();
			sbCuerpoTag = "<widget class = "+Constante.Comilla+sbCadena+Constante.Comilla+">"+Constante.finLinea;
			return sbCuerpoTag;	
		}
		
	public static String tagCloseWidget ()
		throws IOException	
		{
			String sbCuerpoTag = new String ();
			sbCuerpoTag = "</widget>"+Constante.finLinea;
			return sbCuerpoTag;	
		}
		
	public static String tagOpenColumn ()
		throws IOException	
		{
			String sbCuerpoTag = new String ();
			sbCuerpoTag = "<column>"+Constante.finLinea;
			return sbCuerpoTag;	
		}
		
	public static String tagCloseColumn ()
		throws IOException	
		{
			String sbCuerpoTag = new String ();
			sbCuerpoTag = "</column>"+Constante.finLinea;
			return sbCuerpoTag;	
		}

	public static String tagBool ( String sbValor )
		throws IOException	
		{
			String sbCuerpoTag = new String ();
			sbCuerpoTag = "<bool>"+sbValor+"</bool>"+Constante.finLinea;
			return sbCuerpoTag;	
		}
		
public static String tagOpenRect ()
		throws IOException	
		{
			String sbCuerpoTag = new String ();
			sbCuerpoTag = "<rect>"+Constante.finLinea;
			return sbCuerpoTag;	
		}
	
	public static String tagCloseRect ()
		throws IOException	
		{
			String sbCuerpoTag = new String ();
			sbCuerpoTag = "</rect>"+Constante.finLinea;
			return sbCuerpoTag;	
		}
		
	public static String tagCoordenadas ( String sbNombre, int nuTamano)
			throws IOException	
		{
			String sbCuerpoTag = new String ();
			sbCuerpoTag = "<"+sbNombre+">"+nuTamano+"</"+sbNombre+">"+Constante.finLinea;
			return sbCuerpoTag;	
		}
		
}