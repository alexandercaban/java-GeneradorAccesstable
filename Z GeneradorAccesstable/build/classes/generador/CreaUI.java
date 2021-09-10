package generador;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
public class CreaUI{
    public static void crearUI( String nombreTabla,Vector vdc )
    throws IOException{
    	
    	/*Date dtFechaActual ;
    	Time tiHoraActual;
	    java.util.Date udtFechaActual =new java.util.Date();
	    dtFechaActual = new Date( udtFechaActual.getTime() );
	    tiHoraActual = new Time( udtFechaActual.getTime() );
	    */        
        FileWriter header = new FileWriter ( Constante.directorioUI+"frm"+nombreTabla+".ui" );
		BufferedWriter outSource = new BufferedWriter ( header );
        //String espacios = new String("                                             ");
        String sbComilla = "\"";
        String sbIdentacion = new String("    ");
        String sbCopiaIdentacion = new String( sbIdentacion );  
        int nuFila = 20;       
        int nuTamanoIdentacion = 4;
        int nuAltoComponente = 20;
        int nuAnchoComponente = 100;
        int nuAltoLabel = 20;
        int nuAnchoLabel = 100;
        int nuAnchoWidget = 30 * vdc.size() +80;
		//int nuPosicion = 20;
		String sbOrdenTabulacion = new String ();        
		
		//sbOrdenTabulacion   = "<tabstops>"+Constante.finLinea;                
        
        DefinicionCampo dc;                        
		outSource.write("<!DOCTYPE UI><UI version="+sbComilla+"3.2"+sbComilla+" stdsetdef="+sbComilla+"1"+sbComilla+">\n");				
		outSource.write("<class>frm"+nombreTabla+"</class>\n");
		outSource.write("<widget class ="+sbComilla+"QWidget"+sbComilla+">\n");
		
		// segmento de manejo de las propiedades de nombre y geometria
		outSource.write(sbCopiaIdentacion+"<property name="+sbComilla+"name"+sbComilla+">\n");
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+"<cstring>frm"+nombreTabla+"</cstring>\n");
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+"</property>\n");
		
		outSource.write(sbCopiaIdentacion+"<property name="+sbComilla+"geometry"+sbComilla+">\n");
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+"<rect>\n");
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+"<x>0</x>\n");
		outSource.write(sbCopiaIdentacion+"<y>0</y>\n");
		
		outSource.write(sbCopiaIdentacion+"<width>320</width>\n");
		outSource.write(sbCopiaIdentacion+"<height>"+(nuAnchoWidget+200)+"</height>\n");
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+"</rect>\n");
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+"</property>\n");		
		outSource.write(sbCopiaIdentacion+"<property name="+sbComilla+"caption"+sbComilla+">\n");
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+"<string>"+nombreTabla.toUpperCase()+"</string>\n");
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+"</property>\n");
		sbCopiaIdentacion += sbIdentacion;	
		
		//	crea el label y el control adecuado para el campo
		//asigna valores recuparados al objeto
		for ( int i = 0 ; i < vdc.size(); i ++ ) 
		{
			dc = (DefinicionCampo) vdc.elementAt(i);		
			
			sbCopiaIdentacion = sbIdentacion;
			outSource.write(sbCopiaIdentacion+"<widget class = "+sbComilla+"QLabel"+sbComilla+">\n");
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"name"+sbComilla+">\n");
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+"<cstring> lbl"+dc.getNombre()+" </cstring>\n");
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+"</property>\n");
			outSource.write(sbCopiaIdentacion+"<property name ="+sbComilla+"geometry"+sbComilla+">\n");
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+"<rect>\n");
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+"<x>20</x>\n");
			outSource.write(sbCopiaIdentacion+"<y>"+nuFila+"</y>\n");
			outSource.write(sbCopiaIdentacion+"<width>"+nuAnchoLabel+"</width>\n");
			outSource.write(sbCopiaIdentacion+"<height>"+nuAltoLabel+"</height>\n");
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+"</rect>\n");
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+"</property>\n");					
	
			outSource.write(sbCopiaIdentacion+"<property name="+sbComilla+"text"+sbComilla+">\n");
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+"<string>"+dc.getNombre()+"</string>\n");
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+"</property>\n");
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+"</widget>\n");			
			
			// si el campo es una llave foranea siempre dibujara un combobox			
			if ( dc.getForanea().equals("S"))
			{
				sbOrdenTabulacion += sbIdentacion+"<tabstop>cmb"+dc.getNombre()+"</tabstop>"+Constante.finLinea;				
				
				sbCopiaIdentacion = sbIdentacion;
				outSource.write(sbCopiaIdentacion+"<widget class = "+sbComilla+"QComboBox"+sbComilla+">\n");
				sbCopiaIdentacion += sbIdentacion;
				outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"name"+sbComilla+">\n");
				sbCopiaIdentacion += sbIdentacion;
				outSource.write(sbCopiaIdentacion+"<cstring>cmb"+dc.getNombre()+"</cstring>\n");
				sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
				outSource.write(sbCopiaIdentacion+"</property>\n");					
			}
			else
			{
				if ( dc.getTipoJava().equals("QDate"))
				{
					sbOrdenTabulacion += sbIdentacion+"<tabstop>dt"+dc.getNombre()+"</tabstop>"+Constante.finLinea;					
					
					sbCopiaIdentacion = sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<widget class = "+sbComilla+"QDateEdit"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"name"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<cstring>dt"+dc.getNombre()+"</cstring>\n");
					sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
					outSource.write(sbCopiaIdentacion+"</property>\n");
					//
					outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"order"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<enum>YMD</enum>\n");
					sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
					outSource.write(sbCopiaIdentacion+"</property>\n");
					//
					outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"autoAdvance"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<bool>true</bool>\n");
					sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
					outSource.write(sbCopiaIdentacion+"</property>\n");
				}
				if ( dc.getTipoJava().equals("QTime"))
				{
					sbOrdenTabulacion += sbIdentacion+"<tabstop>ti"+dc.getNombre()+"</tabstop>"+Constante.finLinea;					
					
					sbCopiaIdentacion = sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<widget class = "+sbComilla+"QTimeEdit"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"name"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<cstring>ti"+dc.getNombre()+"</cstring>\n");
					sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
					outSource.write(sbCopiaIdentacion+"</property>\n");
					//
					outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"autoAdvance"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<bool>true</bool>\n");
					sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
					outSource.write(sbCopiaIdentacion+"</property>\n");
				}
				
				if ( dc.getTipoJava().equals("bool"))
				{
					sbOrdenTabulacion += sbIdentacion+"<tabstop>cmb"+dc.getNombre()+"</tabstop>"+Constante.finLinea;					
	
					sbCopiaIdentacion = sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<widget class = "+sbComilla+"QComboBox"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"name"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<cstring>cmb"+dc.getNombre()+"</cstring>\n");
					sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
					outSource.write(sbCopiaIdentacion+"</property>\n");
					//
					
				}
				
				if ( dc.getTipoJava().equals("QString") || dc.getTipoJava().equals("int") || dc.getTipoJava().equals("float") )
				{
					sbOrdenTabulacion += sbIdentacion+"<tabstop>txt"+dc.getNombre()+"</tabstop>"+Constante.finLinea;					
					
					sbCopiaIdentacion = sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<widget class = "+sbComilla+"QLineEdit"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"name"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<cstring>txt"+dc.getNombre()+"</cstring>\n");
					sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
					outSource.write(sbCopiaIdentacion+"</property>\n");
					outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"frameShape"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<enum>LineEditPanel</enum>\n");
					sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
					outSource.write(sbCopiaIdentacion+"</property>\n");
					/////
					outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"frameShadow"+sbComilla+">\n");
					sbCopiaIdentacion += sbIdentacion;
					outSource.write(sbCopiaIdentacion+"<enum>Sunken</enum>\n");
					sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
					outSource.write(sbCopiaIdentacion+"</property>\n");
					if ( dc.getTipoJava().equals("QString") )
					{
						outSource.write(sbCopiaIdentacion+"<property name = "+sbComilla+"maxLength"+sbComilla+">\n");
						sbCopiaIdentacion += sbIdentacion;
						outSource.write(sbCopiaIdentacion+"<number>"+dc.getPresicion()+"</number>\n");
						sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
						outSource.write(sbCopiaIdentacion+"</property>\n");
					}
				}
			}
			outSource.write(sbCopiaIdentacion+"<property name ="+sbComilla+"geometry"+sbComilla+">\n");
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+"<rect>\n");
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+"<x>150</x>\n");
			outSource.write(sbCopiaIdentacion+"<y>"+nuFila+"</y>\n");
			outSource.write(sbCopiaIdentacion+"<width>"+nuAnchoComponente+"</width>\n");
			outSource.write(sbCopiaIdentacion+"<height>"+nuAltoComponente+"</height>\n");
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+"</rect>\n");
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+"</property>\n");			
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbIdentacion+"</widget>\n");			
			
			nuFila += 30;			
		}
		
		sbCopiaIdentacion = sbIdentacion;
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagAbrirWidget("QPushButton"));
		
		sbCopiaIdentacion += sbIdentacion;		
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagProperty("name"));
		
		sbCopiaIdentacion += sbIdentacion;
		outSource.write( sbCopiaIdentacion+CreaMainWindowUI.tagcstring("btnGuardar"));
		
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write( sbCopiaIdentacion+CreaMainWindowUI.tagCierreProperty());
		
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagProperty("geometry"));
		
		sbCopiaIdentacion += sbIdentacion;
		outSource.write( sbCopiaIdentacion+CreaMainWindowUI.tagOpenRect());
		
		sbCopiaIdentacion += sbIdentacion;
		outSource.write( sbCopiaIdentacion+CreaMainWindowUI.tagCoordenadas("x",20));
		outSource.write( sbCopiaIdentacion+CreaMainWindowUI.tagCoordenadas("y",nuFila));
		outSource.write( sbCopiaIdentacion+CreaMainWindowUI.tagCoordenadas("width",100));
		outSource.write( sbCopiaIdentacion+CreaMainWindowUI.tagCoordenadas("height",23));
		
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write( sbCopiaIdentacion+CreaMainWindowUI.tagCloseRect());
		
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write( sbCopiaIdentacion+CreaMainWindowUI.tagCierreProperty());
		
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagProperty("text"));
		
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagString("Guardar"));
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write( sbCopiaIdentacion+CreaMainWindowUI.tagCierreProperty());
				
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCloseWidget());
		
		nuFila += 30;
		nuFila += 30;
		sbCopiaIdentacion = sbIdentacion;
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagAbrirWidget("QListView"));
		
		sbCopiaIdentacion += sbIdentacion;
		for ( int i =0 ; i < vdc.size(); i++)
		{
			dc = (DefinicionCampo)vdc.elementAt(i);			
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagOpenColumn());
			
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagProperty("text"));			
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagString(dc.getNombre()));
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCierreProperty());
			
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagProperty("clickable"));			
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagBool("true"));
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCierreProperty());
			
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagProperty("resizable"));			
			sbCopiaIdentacion += sbIdentacion;
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagBool("true"));
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCierreProperty());			
			
			sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
			outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCloseColumn());
		}
		
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagProperty("name"));
		
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagcstring("list"+nombreTabla));
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCierreProperty());
		
		
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagProperty("geometry"));
		
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagOpenRect());
		
		sbCopiaIdentacion += sbIdentacion;
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCoordenadas("x",20));
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCoordenadas("y",nuFila));
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCoordenadas("width",280));
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCoordenadas("height",160));
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCloseRect());
		sbCopiaIdentacion = sbCopiaIdentacion.substring(nuTamanoIdentacion);
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCierreProperty());
		
		sbCopiaIdentacion = sbIdentacion;
		outSource.write(sbCopiaIdentacion+CreaMainWindowUI.tagCloseWidget());
		
		
		outSource.write("</widget>"+Constante.finLinea );
		
		sbOrdenTabulacion += "</tabstops>"+Constante.finLinea;

		outSource.write(sbOrdenTabulacion);
		
		outSource.write("<layoutdefaults spacing="+sbComilla+"6"+sbComilla+" margin="+sbComilla+"11"+sbComilla+" />\n");
		outSource.write("</UI>\n");

		outSource.flush();
		header.close();		  
    }	
}