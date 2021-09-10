package generador;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;
public class CreaHeaderMainWindow{
	
    public static void crearHeaderMainWindow( Vector vdc )
    throws IOException{
    	
    	Date dtFechaActual ;
    	Time tiHoraActual;
	    java.util.Date udtFechaActual =new java.util.Date();
	    dtFechaActual = new Date( udtFechaActual.getTime() );
	    tiHoraActual = new Time( udtFechaActual.getTime() );
	            
        FileWriter header = new FileWriter ( Constante.directorioHeadersHjos+"MainWindow.h" );
        //String espacios = new String("                                             ");
        String sbComilla = "\"";        
        BufferedWriter outHeader = new BufferedWriter ( header );             
          
        outHeader.write( "#ifndef __MAIN_WINDOW_H__"+Constante.finLinea );
		outHeader.write( "#define __MAIN_WINDOW_H__"+Constante.finLinea );		 		
		outHeader.write( "\n\n" );        
		outHeader.write("/*\n");
		outHeader.write("    Propiedad intelectual de Play Tech. \n\n");
		outHeader.write("    Nombre  :    MainWindow.h"+Constante.finLinea);
		outHeader.write("    Descripcion  :    Header main window aplicacion autogenerada"+Constante.finLinea);
		outHeader.write("    Autor  :    "+"Play Tech."+Constante.finLinea);
		outHeader.write("    Creacion  :    "+ dtFechaActual+" - "+ tiHoraActual+"\n\n");
		outHeader.write("    Historial de Modificaciones \n");
		outHeader.write("    Fecha    Autor    	Descripcion \n");
		outHeader.write("*/\n\n");
		outHeader.write( "#include  "+sbComilla+"../../../General/headers/Constantes.h"+sbComilla+Constante.finLinea); 
		outHeader.write( "#include  "+sbComilla+"../../../General/headers/ControlRed.h"+sbComilla+Constante.finLinea);
		outHeader.write( "#include  "+sbComilla+"../../../General/headers/Utilidades.h"+sbComilla+Constante.finLinea);
		outHeader.write("#include "+Constante.Comilla+"ui_general/frmMainWindow.h"+Constante.Comilla+"\n");
		outHeader.write("#include <qstring.h>\n");
		outHeader.write("#include <qworkspace.h>\n");		
		outHeader.write("#include <qdatetime.h>\n\n\n");
		outHeader.write("class MainWindow : public frmMainWindow"+Constante.finLinea);
		outHeader.write("{\n");
		outHeader.write("  Q_OBJECT\n");
		
		outHeader.write("  public:\n");
		outHeader.write("    QWorkspace * ws;\n");
				
		//	Crea un constructor y destructor para la clase        
		outHeader.write("  public:\n");
		outHeader.write("    MainWindow( QWidget * parent = 0, const char * name = 0, WFlags fl = 0);  /* Constructor */\n");
		outHeader.write("    ~MainWindow();  /* Destructor */\n");       
                
        // Crea los metodos para asignar valor a los atributos de la clase
        outHeader.write("\n\n  private slots:\n");
        String sbNombreTabla;
        for ( int i = 0 ; i < vdc.size(); i ++ ) 
        {
        	sbNombreTabla = (String) vdc.elementAt(i);
            outHeader.write("    void openWindow"+sbNombreTabla+"();\n");              
		}
		outHeader.write("};\n");
				 
		outHeader.write("#endif\n");
		outHeader.flush();
		header.close();
    }
    
    
	public static void crearSourceMainWindow( Vector vdc )
		throws IOException{
    	
			Date dtFechaActual ;
			Time tiHoraActual;
			java.util.Date udtFechaActual =new java.util.Date();
			dtFechaActual = new Date( udtFechaActual.getTime() );
			tiHoraActual = new Time( udtFechaActual.getTime() );
	            
			FileWriter header = new FileWriter ( Constante.directorioSourcesHjos+"MainWindow.cpp" );
			//String espacios = new String("                                             ");
			String sbComilla = "\"";        
			BufferedWriter outHeader = new BufferedWriter ( header );             
          			        
			outHeader.write("/*\n");
			outHeader.write("    Propiedad intelectual de Play Tech. \n\n");
			outHeader.write("    Nombre  :    MainWindow.cpp"+Constante.finLinea);
			outHeader.write("    Descripcion  :    cuerpo main window aplicacion autogenerada"+Constante.finLinea);
			outHeader.write("    Autor  :    "+"Play Tech."+Constante.finLinea);
			outHeader.write("    Creacion  :    "+ dtFechaActual+" - "+ tiHoraActual+"\n\n");
			outHeader.write("    Historial de Modificaciones \n");
			outHeader.write("    Fecha    Autor    	Descripcion \n");
			outHeader.write("*/\n\n");
			outHeader.write( "#include  "+sbComilla+"../General/headers/Constantes.h"+sbComilla+Constante.finLinea); 
			outHeader.write( "#include  "+sbComilla+"../General/headers/ControlRed.h"+sbComilla+Constante.finLinea);
			outHeader.write( "#include  "+sbComilla+"../General/headers/Utilidades.h"+sbComilla+Constante.finLinea);

			outHeader.write( "#include  "+sbComilla+"../headers/MainWindow.h"+sbComilla+Constante.finLinea);
			String sbNombreTabla;
			for ( int i = 0 ; i < vdc.size(); i ++ ) 
			{
				sbNombreTabla = (String) vdc.elementAt(i);
				outHeader.write( "#include  "+sbComilla+"../headers/"+sbNombreTabla+".h"+sbComilla+Constante.finLinea);              
			}			
			
			outHeader.write("\n#include <qstring.h>\n");
			outHeader.write("#include <qvariant.h>\n");
			outHeader.write("#include <qlayout.h>\n");
			outHeader.write("#include <qtooltip.h>\n");
			outHeader.write("#include <qwhatsthis.h>\n");
			outHeader.write("#include <qaction.h>\n");
			outHeader.write("#include <qmenubar.h>\n");
			outHeader.write("#include <qpopupmenu.h>\n");
			outHeader.write("#include <qtoolbar.h>\n");			
			outHeader.write("#include <qdatetime.h>\n\n\n");			
				
			//	Crea un constructor y destructor para la clase       
			
			outHeader.write("MainWindow :: MainWindow ( QWidget * parent, const char * name, WFlags fl ) : frmMainWindow ( parent, name, fl )\n");
			outHeader.write("{\n");
			outHeader.write("  ws = new QWorkspace ( this);"+Constante.finLinea);
    		outHeader.write("  setCentralWidget ( ws );"+Constante.finLinea+Constante.finLinea);
			for ( int i = 0 ; i < vdc.size(); i ++ ) 
			{
				sbNombreTabla = (String) vdc.elementAt(i);
				outHeader.write("  connect ( Accion"+sbNombreTabla+", SIGNAL ( activated () ), this,  SLOT ( openWindow"+sbNombreTabla+" () ) );\n");
			}
			outHeader.write("}\n\n");
			outHeader.write("MainWindow :: ~MainWindow()\n");
			outHeader.write("{\n");
			outHeader.write("}\n");       
			
			for ( int i = 0 ; i < vdc.size(); i ++ ) 
			{
				sbNombreTabla = (String) vdc.elementAt(i);
				outHeader.write("void MainWindow :: openWindow"+sbNombreTabla+"()\n");
				outHeader.write("{\n");
				outHeader.write("  "+sbNombreTabla+" * frm"+sbNombreTabla+";\n");				

				outHeader.write("    frm"+sbNombreTabla+" = new "+sbNombreTabla+" ( ws,"+Constante.Comilla+sbNombreTabla+Constante.Comilla+",WDestructiveClose);\n");
				outHeader.write("    frm"+sbNombreTabla+"->show();\n");

				outHeader.write("}\n\n");			              
			}

			outHeader.flush();
			header.close();
		}
}