package generador;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;
public class CrearClasesFormas{
	
    //public static void crearHeaderClasesFormas( Vector vdc, String sbNombreTabla )
	public static void crearHeaderClasesFormas( String sbNombreTabla )
    throws IOException{
    	
    	Date dtFechaActual ;
    	Time tiHoraActual;
	    java.util.Date udtFechaActual =new java.util.Date();
	    dtFechaActual = new Date( udtFechaActual.getTime() );
	    tiHoraActual = new Time( udtFechaActual.getTime() );
	            
        FileWriter header = new FileWriter ( Constante.directorioHeadersHjos+sbNombreTabla+".h" );                        
        BufferedWriter outHeader = new BufferedWriter ( header );             
          
        outHeader.write( "#ifndef __"+sbNombreTabla.toUpperCase()+"_H__"+Constante.finLinea );
		outHeader.write( "#define __"+sbNombreTabla.toUpperCase()+"_H__"+Constante.finLinea );		 		
		outHeader.write( "\n\n" );        
		outHeader.write("/*\n");
		outHeader.write("    Propiedad intelectual de Play Tech. \n\n");
		outHeader.write("    Nombre  :    MainWindow.h"+Constante.finLinea);
		outHeader.write("    Descripcion  :    Header "+sbNombreTabla+" aplicacion autogenerada"+Constante.finLinea);
		outHeader.write("    Autor  :    "+"Play Tech."+Constante.finLinea);
		outHeader.write("    Creacion  :    "+ dtFechaActual+" - "+ tiHoraActual+"\n\n");
		outHeader.write("    Historial de Modificaciones \n");
		outHeader.write("    Fecha    Autor    	Descripcion \n");
		outHeader.write( "*/\n\n");
		outHeader.write( "#include "+Constante.Comilla+"../../ui_general/frm"+sbNombreTabla+".h"+Constante.Comilla+Constante.finLinea+Constante.finLinea);
		outHeader.write( "#include "+Constante.Comilla+"../../../General/headers/Constantes.h"+Constante.Comilla+Constante.finLinea); 
		outHeader.write( "#include "+Constante.Comilla+"../../../General/headers/ControlRed.h"+Constante.Comilla+Constante.finLinea);
		outHeader.write( "#include "+Constante.Comilla+"../../../General/headers/Utilidades.h"+Constante.Comilla+Constante.finLinea+Constante.finLinea);
		
		outHeader.write( "#include  "+Constante.Comilla+"../../../ListaObjetos/headers/Lista"+sbNombreTabla+".h"+Constante.Comilla+Constante.finLinea+Constante.finLinea);
		outHeader.write( "#include  "+Constante.Comilla+"../../../AccessTable/headers/AT"+sbNombreTabla+".h"+Constante.Comilla+Constante.finLinea+Constante.finLinea);		
		outHeader.write("#include <qstring.h>\n");				
		outHeader.write("#include <qlistview.h>\n");
		outHeader.write("#include <qdatetime.h>\n\n\n");
		outHeader.write("class "+sbNombreTabla+" : public frm"+sbNombreTabla+Constante.finLinea);
		outHeader.write("{\n");
		outHeader.write("  Q_OBJECT\n");
					
		//	Crea un constructor y destructor para la clase        
		outHeader.write("  public:\n");
		outHeader.write("    "+sbNombreTabla+"(QWidget * parent = 0, const char * name = 0, WFlags fl = 0);  /* Constructor */\n");
		outHeader.write("    ~"+sbNombreTabla+"();  /* Destructor */\n");
		outHeader.write("    void LlenarListaDetalle();"+Constante.finLinea);
		outHeader.write("    bool blCamposRequeridos();"+Constante.finLinea+Constante.finLinea);       
                
        // Crea los metodos para asignar valor a los atributos de la clase
        outHeader.write("  private slots:\n");
		outHeader.write("    void itemListSelected( QListViewItem * );"+Constante.finLinea);
		outHeader.write("    void keyPressedForm( int );"+Constante.finLinea);		        
		outHeader.write("    void Guardar();"+Constante.finLinea);
		outHeader.write("    void Limpiar();"+Constante.finLinea);
		outHeader.write("    void Cerrar();"+Constante.finLinea+Constante.finLinea);
		outHeader.write("  private:\n");
		outHeader.write("    AT"+sbNombreTabla+" obj"+sbNombreTabla+";"+Constante.finLinea );
		outHeader.write("    Lista"+sbNombreTabla+" objLista"+sbNombreTabla+";"+Constante.finLinea );
        
        //outHeader.write("    void openWindow"+sbNombreTabla+"();\n");             
		
		outHeader.write("};\n\n");
				 
		outHeader.write("#endif\n");
		outHeader.flush();
		header.close();
    }
    
    
	public static void crearSourceClasesFormas( Vector vdc , String sbNombreTabla)
		throws IOException{
    	
			Date dtFechaActual ;
			Time tiHoraActual;
			java.util.Date udtFechaActual =new java.util.Date();
			dtFechaActual = new Date( udtFechaActual.getTime() );
			tiHoraActual = new Time( udtFechaActual.getTime() );
	            
			FileWriter header = new FileWriter ( Constante.directorioSourcesHjos+sbNombreTabla+".cpp" );			        
			BufferedWriter outHeader = new BufferedWriter ( header );
			DefinicionCampo dc;
			boolean blExisteNumericos = false;
			boolean blExisteFlotantes = false;
			Vector<DefinicionCampo> vnum = new Vector<DefinicionCampo>();             
          			        
			outHeader.write("/*\n");
			outHeader.write("    Propiedad intelectual de Play Tech. \n\n");
			outHeader.write("    Nombre  :    MainWindow.cpp"+Constante.finLinea);
			outHeader.write("    Descripcion  :    cuerpo main window aplicacion autogenerada"+Constante.finLinea);
			outHeader.write("    Autor  :    "+"Play Tech."+Constante.finLinea);
			outHeader.write("    Creacion  :    "+ dtFechaActual+" - "+ tiHoraActual+"\n\n");
			outHeader.write("    Historial de Modificaciones \n");
			outHeader.write("    Fecha    Autor    	Descripcion \n");
			outHeader.write("*/\n\n");
			outHeader.write( "#include  "+Constante.Comilla+"../../General/headers/Constantes.h"+Constante.Comilla+Constante.finLinea); 
			outHeader.write( "#include  "+Constante.Comilla+"../../General/headers/ControlRed.h"+Constante.Comilla+Constante.finLinea);
			outHeader.write( "#include  "+Constante.Comilla+"../../General/headers/Utilidades.h"+Constante.Comilla+Constante.finLinea+Constante.finLinea);
			
			outHeader.write( "#include  "+Constante.Comilla+"../../AccessTable/headers/AT"+sbNombreTabla+".h"+Constante.Comilla+Constante.finLinea+Constante.finLinea);
			
			outHeader.write( "#include  "+Constante.Comilla+"../headers/"+sbNombreTabla+".h"+Constante.Comilla+Constante.finLinea+Constante.finLinea);			
						
			outHeader.write("#include <qstring.h>\n");
			outHeader.write("#include <qlineedit.h>\n");
			outHeader.write("#include <qcombobox.h>\n");
			outHeader.write("#include <qdatetimeedit.h>\n");
			outHeader.write("#include <qvalidator.h>\n");
			outHeader.write("#include <qaccel.h>\n");						
			outHeader.write("#include <qlistview.h>\n");
			outHeader.write("#include <qmessagebox.h>\n");
			outHeader.write("#include <qpushbutton.h>\n");
			outHeader.write("#include <qdatetime.h>\n\n\n");			
				
			//	Crea un constructor y destructor para la clase       
			
			outHeader.write(sbNombreTabla+" :: "+sbNombreTabla+" ( QWidget * parent, const char * name, WFlags fl ) : frm"+sbNombreTabla+" ( parent,name,fl )\n");
			outHeader.write("{\n");
			for ( int i = 0 ; i < vdc.size(); i ++ ) 
			{
				dc = (DefinicionCampo) vdc.elementAt(i);
				if ( !dc.getForanea().equals("S"))
				{
					if ( dc.getTipoJava().equals("float") || dc.getTipoJava().equals("int"))
					{
						vnum.addElement(dc);
						if ( dc.getTipoJava().equals("int"))
							blExisteNumericos = true;
						else if ( dc.getTipoJava().equals("float"))
							blExisteFlotantes = true;														
					}				
				}
			}

			if (blExisteNumericos == true )
			{
				outHeader.write("  QIntValidator * intValidate = new QIntValidator ( this);\n");
			}
			
			if (blExisteFlotantes == true )
			{
				outHeader.write("  QDoubleValidator * doubleValidate = new QDoubleValidator ( this);\n");
				outHeader.write("  doubleValidate->setDecimals (2);\n");
			}
			
			outHeader.write("  QAccel * keyPress = new QAccel (this);"+Constante.finLinea );
			outHeader.write("  keyPress->insertItem (Key_Escape, 0);"+Constante.finLinea );
			outHeader.write("  keyPress->insertItem (Key_Enter, 1);"+Constante.finLinea );
			outHeader.write("  keyPress->insertItem (Key_Return, 2);"+Constante.finLinea+Constante.finLinea );
			
			
			for ( int i = 0 ; i < vdc.size(); i ++ ) 
			{
				dc = (DefinicionCampo) vdc.elementAt(i);
				if ( !dc.getForanea().equals("S"))
				{
					if ( dc.getTipoJava().equals("int"))
					{
						outHeader.write("  txt"+dc.getNombre()+"->setValidator ( intValidate );\n\n");
					}				
					if (dc.getTipoJava().equals("float"))
					{
						outHeader.write("  txt"+dc.getNombre()+"->setValidator ( doubleValidate );\n\n");
					}
				}
			}

			if ( vdc.size() > 0 )
			{
				dc = (DefinicionCampo) vdc.elementAt(0);
				if ( dc.getForanea().equals("S"))
				{
					outHeader.write("  cmb"+dc.getNombre()+"->setFocus();"+Constante.finLinea);
				}
				else
				{
					if ( dc.getTipoJava().equals("float") || dc.getTipoJava().equals("int") || dc.getTipoJava().equals("QString") )
					{
						outHeader.write("  txt"+dc.getNombre()+"->setFocus();"+Constante.finLinea);
					}
					else if ( dc.getTipoJava().equals("QDate") )
					{
						outHeader.write("  dt"+dc.getNombre()+"->setFocus();"+Constante.finLinea);						
					}
					else if ( dc.getTipoJava().equals("QTime"))
					{
						outHeader.write("  ti"+dc.getNombre()+"->setFocus();"+Constante.finLinea);
					}
					else if ( dc.getTipoJava().equals("bool"))
					{
						outHeader.write("  cmb"+dc.getNombre()+"->setFocus();"+Constante.finLinea);
					}				
				}
			}	
						
			outHeader.write("  list"+sbNombreTabla+"->setAllColumnsShowFocus ( TRUE );"+Constante.finLinea+Constante.finLinea);
			outHeader.write("  connect (list"+sbNombreTabla+", SIGNAL ( clicked ( QListViewItem * ) ),this, SLOT ( itemListSelected ( QListViewItem * ) ) );"+Constante.finLinea);
			outHeader.write("  connect (keyPress, SIGNAL ( activated ( int ) ),this, SLOT ( keyPressedForm ( int ) ) );"+Constante.finLinea);
			outHeader.write("  objLista"+sbNombreTabla+".loadListRecord();"+Constante.finLinea);
			outHeader.write("  LlenarListaDetalle();"+Constante.finLinea);
			outHeader.write("}\n\n");
			outHeader.write(sbNombreTabla+" :: ~"+sbNombreTabla+"()\n");
			outHeader.write("{"+Constante.finLinea);
			outHeader.write("}"+Constante.finLinea+Constante.finLinea);       

			outHeader.write("void "+sbNombreTabla+" :: LlenarListaDetalle ()"+Constante.finLinea);
			outHeader.write("{"+Constante.finLinea);
			outHeader.write("   it"+sbNombreTabla+" objIterator;"+Constante.finLinea);
			outHeader.write("   QListViewItem * newItemList;"+Constante.finLinea);
			outHeader.write("   for ( objIterator = objLista"+sbNombreTabla+".Begin(); objIterator != objLista"+sbNombreTabla+".End(); ++objIterator )"+Constante.finLinea);
			outHeader.write("   {"+Constante.finLinea);
			outHeader.write("     newItemList = new QListViewItem (list"+sbNombreTabla+");"+Constante.finLinea);
			
			
			// se llena la lista de los campos de acuerdo al tipo de cada uno
			for ( int i = 0 ; i < vdc.size(); i ++ ) 
			{
				dc = (DefinicionCampo) vdc.elementAt(i);
				//if ( !dc.getForanea().equals("S"))
				//{
					if ( dc.getTipoJava().equals("float") || dc.getTipoJava().equals("int"))
					{				
						outHeader.write("     newItemList->setText ( "+i+",QString :: number ( (*objIterator).get"+dc.getNombre()+"() ) );"+Constante.finLinea);													
					}
					else if ( dc.getTipoJava().equals("QDate") )
					{
						outHeader.write("     newItemList->setText ( "+i+",(*objIterator).get"+dc.getNombre()+"().toString("+Constante.Comilla+"yyyy-MM-dd"+Constante.Comilla+")  );"+Constante.finLinea);	
					}
					else if ( dc.getTipoJava().equals("QTime"))
					{
						outHeader.write("     newItemList->setText ( "+i+",(*objIterator).get"+dc.getNombre()+"().toString("+Constante.Comilla+"hh:mm:ss"+Constante.Comilla+") );"+Constante.finLinea);
					}
					else if ( dc.getTipoJava().equals("bool"))
					{
						outHeader.write("     newItemList->setText ( "+i+", "+Constante.Comilla+"T"+Constante.Comilla+" );"+Constante.finLinea);
					}
					else if ( dc.getTipoJava().equals("QString"))
					{
						outHeader.write("     newItemList->setText ( "+i+",(*objIterator).get"+dc.getNombre()+"() );"+Constante.finLinea);
					}
				//}
			}
			
			outHeader.write("   }"+Constante.finLinea);
			outHeader.write("}"+Constante.finLinea+Constante.finLinea);
			
			outHeader.write("void "+sbNombreTabla+" :: itemListSelected ( QListViewItem * itemSelected )"+Constante.finLinea);
			outHeader.write("{"+Constante.finLinea);
			outHeader.write("  if ( itemSelected )"+Constante.finLinea);
			outHeader.write("  {"+Constante.finLinea);
			
			// subo los valores desde la grilla hacia los campos de texto
			for ( int i = 0 ; i < vdc.size(); i ++ ) 
			{
				dc = (DefinicionCampo) vdc.elementAt(i);
				if ( !dc.getForanea().equals("S"))
				{
					if ( dc.getTipoJava().equals("float") || dc.getTipoJava().equals("int") || dc.getTipoJava().equals("QString") )
					{				
						outHeader.write("    txt"+dc.getNombre()+"->setText( itemSelected->text("+i+") );"+Constante.finLinea);													
					}
					/*else if ( dc.getTipoJava().equals("QDate") )
					{
						outHeader.write("     newItemList->setText ( "+i+",(*objIterator).get"+dc.getNombre()+"().toString("+Constante.Comilla+"yyyy-MM-dd"+Constante.Comilla+")  );"+Constante.finLinea);	
					}
					else if ( dc.getTipoJava().equals("QTime"))
					{
						outHeader.write("     newItemList->setText ( "+i+",(*objIterator).get"+dc.getNombre()+"().toString("+Constante.Comilla+"hh:mm:ss"+Constante.Comilla+") );"+Constante.finLinea);
					}
					else if ( dc.getTipoJava().equals("bool"))
					{
						outHeader.write("     newItemList->setText ( "+i+", "+Constante.Comilla+"T"+Constante.Comilla+" );"+Constante.finLinea);
					}
					else if ( dc.getTipoJava().equals("QString"))
					{
						outHeader.write("     newItemList->setText ( "+i+",(*objIterator).get"+dc.getNombre()+"() );"+Constante.finLinea);
					}*/
				}
			}
			
			outHeader.write("  }"+Constante.finLinea);			
			outHeader.write("}"+Constante.finLinea+Constante.finLinea);
			
			outHeader.write("void "+sbNombreTabla+" :: keyPressedForm( int key )"+Constante.finLinea);
			outHeader.write("{"+Constante.finLinea);
			outHeader.write("  if ( key == 0 )"+Constante.finLinea);
			outHeader.write("  {"+Constante.finLinea);
			outHeader.write("    close();"+Constante.finLinea);
			outHeader.write("  }"+Constante.finLinea);
			
			// ciclo para recorrer los campos de la tabla			 
			DefinicionCampo dcTemporal;
			for ( int i = 0, x=1; i < vdc.size(); i ++,x++ ) 
			{
				dc = (DefinicionCampo) vdc.elementAt(i);
				if ( dc.getForanea().equals("S"))
				{
					if ( x < vdc.size() )
					{
						dcTemporal = (DefinicionCampo) vdc.elementAt(x);
						outHeader.write("  if (cmb"+dc.getNombre()+"->hasFocus() )"+Constante.finLinea);					
						outHeader.write("  {"+Constante.finLinea);
						if ( !dcTemporal.getForanea().equals("S") )
						{
							if ( dcTemporal.getTipoJava().equals("float") || dcTemporal.getTipoJava().equals("int") || dcTemporal.getTipoJava().equals("QString"))
							{
								outHeader.write("    txt"+dcTemporal.getNombre()+"->setFocus();"+Constante.finLinea);																				
							}
							else if ( dcTemporal.getTipoJava().equals("QDate") )
							{
								outHeader.write("    dt"+dcTemporal.getNombre()+"->setFocus();"+Constante.finLinea);	
							}
							else if ( dcTemporal.getTipoJava().equals("QTime"))
							{
								outHeader.write("    ti"+dcTemporal.getNombre()+"->setFocus();"+Constante.finLinea);
							}
							else if ( dcTemporal.getTipoJava().equals("bool"))
							{
								outHeader.write("    cmb"+dcTemporal.getNombre()+"->setFocus();"+Constante.finLinea);
							}						
						}
						else
						{
							outHeader.write("    cmb"+dcTemporal.getNombre()+"->setFocus();"+Constante.finLinea);
						}
						outHeader.write("     return;"+Constante.finLinea);
						outHeader.write("  }"+Constante.finLinea);
					}						
				}
				else
				{
					if ( x < vdc.size() )
					{
						dcTemporal = (DefinicionCampo) vdc.elementAt(x);
						outHeader.write("  if ( ");
						if ( dc.getTipoJava().equals("float") || dc.getTipoJava().equals("int") || dc.getTipoJava().equals("QString"))
						{				
							outHeader.write("txt"+dc.getNombre()+"->hasFocus())"+Constante.finLinea);													
						}
						else if ( dc.getTipoJava().equals("QDate") )
						{
							outHeader.write("dt"+dc.getNombre()+"->hasFocus())"+Constante.finLinea);	
						}
						else if ( dc.getTipoJava().equals("QTime"))
						{
							outHeader.write("ti"+dc.getNombre()+"->hasFocus())"+Constante.finLinea);
						}
						else if ( dc.getTipoJava().equals("bool"))
						{
							outHeader.write("cmb"+dc.getNombre()+"->hasFocus())"+Constante.finLinea);
						}
						outHeader.write("  {"+Constante.finLinea);
						
						// aqui averiguamos el tipo del siguiente campo...
						if ( dcTemporal.getForanea().equals("S"))
						{
							outHeader.write("     cmb"+dcTemporal.getNombre()+"->setFocus();"+Constante.finLinea);
						}
						else
						{
							if ( dcTemporal.getTipoJava().equals("float") || dcTemporal.getTipoJava().equals("int") || dcTemporal.getTipoJava().equals("QString"))
							{				
								outHeader.write("     txt"+dcTemporal.getNombre()+"->setFocus();"+Constante.finLinea);													
							}
							else if ( dcTemporal.getTipoJava().equals("QDate") )
							{
								outHeader.write("     dt"+dcTemporal.getNombre()+"->setFocus();"+Constante.finLinea);	
							}
							else if ( dcTemporal.getTipoJava().equals("QTime"))
							{
								outHeader.write("     ti"+dcTemporal.getNombre()+"->setFocus();"+Constante.finLinea);
							}
							else if ( dcTemporal.getTipoJava().equals("bool"))
							{
								outHeader.write("     cmb"+dcTemporal.getNombre()+"->setFocus();"+Constante.finLinea);
							}					
						}
						outHeader.write("     return;"+Constante.finLinea);
						outHeader.write("  }"+Constante.finLinea);
					}
				}
			}
			
			outHeader.write("  "+Constante.finLinea);
			outHeader.write("}"+Constante.finLinea+Constante.finLinea);

			outHeader.write("void "+sbNombreTabla+" :: Guardar()"+Constante.finLinea);
			outHeader.write("{"+Constante.finLinea);
			outHeader.write("}"+Constante.finLinea+Constante.finLinea);

			outHeader.write("void "+sbNombreTabla+" :: Limpiar()"+Constante.finLinea);
			outHeader.write("{"+Constante.finLinea);
			outHeader.write("}"+Constante.finLinea+Constante.finLinea);

			outHeader.write("void "+sbNombreTabla+" :: Cerrar()"+Constante.finLinea);
			outHeader.write("{"+Constante.finLinea);
			outHeader.write("   close();"+Constante.finLinea);
			outHeader.write("}"+Constante.finLinea+Constante.finLinea);

			outHeader.write("bool "+sbNombreTabla+" :: blCamposRequeridos()\n");
			outHeader.write("{\n");
			for ( int i = 0 ; i < vdc.size(); i ++ ) 
			{
				dc = (DefinicionCampo) vdc.elementAt(i);
				if ( !dc.getForanea().equals("S") )
				{ 
					if ( dc.getRequerido().equals("N"))
					{				
						if ( dc.getTipoJava().equals("QTime"))
						{
							outHeader.write("  if ( ti"+dc.getNombre()+"->time().isValid() == false)\n");
							outHeader.write("  {\n");
							outHeader.write("    return false;\n");
							outHeader.write("  }\n");
						}
					
						if ( dc.getTipoJava().equals("QDate"))
						{
							outHeader.write("  if ( dt"+dc.getNombre()+"->date().isValid() == false)\n");
							outHeader.write("  {\n");
							outHeader.write("    return false;\n");
							outHeader.write("  }\n");
						}
						if ( dc.getTipoJava().equals("QString"))
						{
							outHeader.write("  if ( txt"+dc.getNombre()+"->text().isEmpty() == false)\n");
							outHeader.write("  {\n");
							outHeader.write("    return false;\n");
							outHeader.write("  }\n");
						}
					}									
				}
			}		
			outHeader.write("  return true;\n");	
			outHeader.write("}\n");

			outHeader.flush();
			header.close();
		}
		
	public static void crearMakeFileProyecto( Vector vmt )
		throws IOException{
    	
			Date dtFechaActual ;
			Time tiHoraActual;
			java.util.Date udtFechaActual =new java.util.Date();
			dtFechaActual = new Date( udtFechaActual.getTime() );
			tiHoraActual = new Time( udtFechaActual.getTime() );
	            
			FileWriter header = new FileWriter ( Constante.directorioProyecto+"aplicacion.pro" );                        
			BufferedWriter outHeader = new BufferedWriter ( header );             
     			        
			outHeader.write("# archivo de configuracion del proyecto "+Constante.finLinea);
			outHeader.write("# autogenerado por generador "+Constante.finLinea);
			outHeader.write("# Nombre  :    aplicacion.pro"+Constante.finLinea);
			outHeader.write("# Descripcion  :   Archivo de configuracion de proyecto generado a partir del esquema de base de datos"+Constante.finLinea);
			outHeader.write("# Autor  :    "+"Play Tech."+Constante.finLinea);
			outHeader.write("# Creacion  :    "+ dtFechaActual+" - "+ tiHoraActual+Constante.finLinea+Constante.finLinea);
			outHeader.write("# Historial de Modificaciones"+Constante.finLinea);
			outHeader.write("# Fecha         Autor            Descripcion "+Constante.finLinea);
						
			int i;
			String sbNombreTabla;
			outHeader.write("\n\nFORMS =  modulos/forms/frmMainWindow.ui  "+Constante.SLASH+Constante.finLinea);
			for ( i=0; i < vmt.size();i++)				
			{
				sbNombreTabla = (String)vmt.elementAt(i);
				outHeader.write("  modulos/forms/frm"+sbNombreTabla+".ui  "+Constante.SLASH+Constante.finLinea);
			}	
			
			outHeader.write("\n\nHEADERS =  "+Constante.SLASH+Constante.finLinea);			
			outHeader.write("  ../AccessTable/headers/ServiciosAccessTable.h "+Constante.SLASH+Constante.finLinea);						
			outHeader.write("  ../General/headers/Constantes.h "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/headers/Configuracion.h "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/headers/ControlRed.h "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/headers/Utilidades.h "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/headers/Parser.h "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/headers/Socket.h "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/headers/fileReader.h "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/headers/constanteError.h "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/headers/configuracionDB.h "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/headers/Conexion.h "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  modulos/headers/MainWindow.h "+Constante.SLASH+Constante.finLinea);
			
			for ( i=0; i < vmt.size();i++)				
			{
				sbNombreTabla = (String)vmt.elementAt(i);
				outHeader.write("  modulos/headers/"+sbNombreTabla+".h  "+Constante.SLASH+Constante.finLinea);
			}
			for ( i=0; i < vmt.size();i++)				
			{
				sbNombreTabla = (String)vmt.elementAt(i);
				outHeader.write("  ../AccessTable/headers/AT"+sbNombreTabla+".h  "+Constante.SLASH+Constante.finLinea);
			}
			for ( i=0; i < vmt.size();i++)				
			{
				sbNombreTabla = (String)vmt.elementAt(i);
				outHeader.write("  ../ListaObjetos/headers/Lista"+sbNombreTabla+".h  "+Constante.SLASH+Constante.finLinea);
			}
			
			outHeader.write("\n\nSOURCES =  "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  main.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../AccessTable/sources/ServiciosAccessTable.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/sources/Constantes.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/sources/Configuracion.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/sources/ControlRed.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/sources/Utilidades.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/sources/Parser.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/sources/Socket.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/sources/fileReader.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/sources/constanteError.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/sources/configuracionDB.cpp "+Constante.SLASH+Constante.finLinea);
			outHeader.write("  ../General/sources/Conexion.cpp "+Constante.SLASH+Constante.finLinea);			
			outHeader.write("  modulos/sources/MainWindow.cpp "+Constante.SLASH+Constante.finLinea);
			for ( i=0; i < vmt.size();i++)				
			{
				sbNombreTabla = (String)vmt.elementAt(i);
				outHeader.write("  modulos/sources/"+sbNombreTabla+".cpp  "+Constante.SLASH+Constante.finLinea);
			}
			for ( i=0; i < vmt.size();i++)				
			{
				sbNombreTabla = (String)vmt.elementAt(i);
				outHeader.write("  ../AccessTable/sources/AT"+sbNombreTabla+".cpp  "+Constante.SLASH+Constante.finLinea);
			}
			for ( i=0; i < vmt.size();i++)				
			{
				sbNombreTabla = (String)vmt.elementAt(i);
				outHeader.write("  ../ListaObjetos/sources/Lista"+sbNombreTabla+".cpp  "+Constante.SLASH+Constante.finLinea);
			}
			
			outHeader.write(Constante.finLinea+"TEMPLATE=app"+Constante.finLinea+Constante.finLinea);
			outHeader.write("CONFIG	+= qt warn_on release"+Constante.finLinea);
			outHeader.write("LANGUAGE = C++"+Constante.finLinea);
			outHeader.write("TARGET = aplicacion"+Constante.finLinea);
			
			outHeader.write("UI_DIR = ui_general"+Constante.finLinea);
			outHeader.write("MOC_DIR = moc_general"+Constante.finLinea);
			outHeader.write("OBJECTS_DIR = obj_general"+Constante.finLinea);
			
			outHeader.flush();
			header.close();
		}
	
	
	
	// crear el Makefile para generar la libreria
	public static void crearMakeFileLibreira( Vector vmt )
	throws IOException{
	
		Date dtFechaActual ;
		Time tiHoraActual;
		java.util.Date udtFechaActual =new java.util.Date();
		dtFechaActual = new Date( udtFechaActual.getTime() );
		tiHoraActual = new Time( udtFechaActual.getTime() );
            
		FileWriter header = new FileWriter ( Constante.directorioLibreria+"access.pro" );                        
		BufferedWriter outHeader = new BufferedWriter ( header );             
 			        
		outHeader.write("# archivo de configuracion del proyecto "+Constante.finLinea);
		outHeader.write("# autogenerado por generador "+Constante.finLinea);
		outHeader.write("# Nombre		:	access.pro"+Constante.finLinea);
		outHeader.write("# Descripcion	:	Archivo de configuracion de proyecto generado a partir del esquema de base de datos"+Constante.finLinea);
		outHeader.write("# Autor			:	Play Tech."+Constante.finLinea);
		outHeader.write("# Creacion		:	"+ dtFechaActual+" - "+ tiHoraActual+Constante.finLinea+Constante.finLinea);
		outHeader.write("# Historial de Modificaciones"+Constante.finLinea);
		outHeader.write("# Fecha         Autor            Descripcion "+Constante.finLinea+Constante.finLinea);
					
		int i;
		String sbNombreTabla;
		
		outHeader.write("\n\nHEADERS =  "+Constante.SLASH+Constante.finLinea);
		outHeader.write("  headers/ServiciosAccessTable.h  "+Constante.SLASH+Constante.finLinea);
		for ( i=0; i < vmt.size();i++)				
		{
			sbNombreTabla = (String)vmt.elementAt(i);
			outHeader.write("  headers/AT"+sbNombreTabla+".h  "+Constante.SLASH+Constante.finLinea);
		}
		for ( i=0; i < vmt.size();i++)				
		{
			sbNombreTabla = (String)vmt.elementAt(i);
			outHeader.write("  headers/Lista"+sbNombreTabla+".h  "+Constante.SLASH+Constante.finLinea);
		}
		
		outHeader.write("\n\nSOURCES =  "+Constante.SLASH+Constante.finLinea);
		outHeader.write("  sources/ServiciosAccessTable.cpp  "+Constante.SLASH+Constante.finLinea);
		
		for ( i=0; i < vmt.size();i++)				
		{
			sbNombreTabla = (String)vmt.elementAt(i);
			outHeader.write("  sources/AT"+sbNombreTabla+".cpp  "+Constante.SLASH+Constante.finLinea);
		}
		for ( i=0; i < vmt.size();i++)				
		{
			sbNombreTabla = (String)vmt.elementAt(i);
			outHeader.write("  sources/Lista"+sbNombreTabla+".cpp  "+Constante.SLASH+Constante.finLinea);
		}
		
		outHeader.write(Constante.finLinea+"PROJECT=access"+Constante.finLinea+Constante.finLinea);
		outHeader.write("TEMPLATE=lib"+Constante.finLinea+Constante.finLinea);
		outHeader.write("CONFIG	+= qt warn_on release"+Constante.finLinea);
		outHeader.write("LANGUAGE = C++"+Constante.finLinea);
		outHeader.write("TARGET = access"+Constante.finLinea+Constante.finLinea);
		
		outHeader.write("LIBS += -lgeneral"+Constante.finLinea+Constante.finLinea);
		outHeader.write("LIBS += -lcomm"+Constante.finLinea+Constante.finLinea);
		
		outHeader.write("UI_DIR = ui_general"+Constante.finLinea);
		outHeader.write("MOC_DIR = moc_general"+Constante.finLinea);
		outHeader.write("OBJECTS_DIR = obj_general"+Constante.finLinea);
		
		outHeader.flush();
		header.close();
	}
}