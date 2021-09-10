package generador;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
public class  Generator{
    public static DefinicionCampo parseCampoInfo( String s ){
        DefinicionCampo dc = new DefinicionCampo();
        StringTokenizer lineTokenizer = new StringTokenizer(s, " ");
        String campoNombre = null;
        //Nombre del campo
        if ( lineTokenizer.hasMoreElements() ){
          campoNombre = lineTokenizer.nextToken();
          String primero = campoNombre.substring(0,1);
          String resto   = campoNombre.substring(1,campoNombre.length());
          campoNombre = primero.toUpperCase() + resto;
          dc.setNombre( campoNombre );
        }
        //tipo de dato
        if ( lineTokenizer.hasMoreElements() ){
          String sbTipo = lineTokenizer.nextToken();
          dc.setTipo( sbTipo );
          if ( sbTipo.equals("date") ) {
              dc.setTipoJava("Date");
              dc.setPrefijo("dt");
          }
          else if ( sbTipo.equals("time") ) {
              dc.setTipoJava("Time");
              dc.setPrefijo("ti");
          }
          else if ( sbTipo.equals("int8") ||
               sbTipo.equals("int4") ||
               sbTipo.equals("int2") ) {

              dc.setTipoJava("int");
              dc.setPrefijo("nu");

          }
          else if ( sbTipo.equals("numeric") || sbTipo.equals("float4")) {
              dc.setTipoJava("double");
              dc.setPrefijo("db");
          }

          else if ( sbTipo.equals("varchar") || sbTipo.equals("text") ) {          	
              dc.setTipoJava("String");
              dc.setPrefijo("sb");
          }

          else if ( sbTipo.equals("bool") ) {
              dc.setTipoJava("boolean");
              dc.setPrefijo("bo");
          }
          
          else if ( sbTipo.equals("serial") ) {
        	  dc.setTipoJava("int");
              dc.setPrefijo("nu");
          }
          
        
          else {
              System.out.println("El tipo de dato "+sbTipo+" no esta manejado");
          }

        }
        //longitud del campo
        if ( lineTokenizer.hasMoreElements() ){
          dc.setPresicion( lineTokenizer.nextToken() );
        }
        //requerido S o N
        if ( lineTokenizer.hasMoreElements() ){
          dc.setRequerido( lineTokenizer.nextToken() );
        }
        //es llave S o N
        if ( lineTokenizer.hasMoreElements() ){
          dc.setLlave( lineTokenizer.nextToken() );
        }
		//es llave foranea S o N
		if ( lineTokenizer.hasMoreElements() ){
		  dc.setForane( lineTokenizer.nextToken() );
		}
		
        return dc;
    }    
    
    public static Vector tablaConf( String tablename ) {
    	
        Vector<DefinicionCampo> v = new Vector<DefinicionCampo>();
        try{
            DefinicionCampo dc;
            Connection con = DBConexion.getDBConexion();
            if ( con == null ) {
                System.out.println("no conectado");
            }

            DatabaseMetaData metadata = con.getMetaData();
            //Carga la llave primaria de la tabla
            ResultSet rsMetadataLlavePrimaria= metadata.getPrimaryKeys(null,null,tablename);
			Hashtable<String, Integer> htLlave = new Hashtable<String, Integer>();
            
            //Cargo las llaves foraneas de la tabla            
			ResultSet rsLlavesForaneas = metadata.getImportedKeys(null,null,tablename);
			Hashtable<String, Integer> htLlavesForaneas = new Hashtable<String, Integer>();
            
            int i = 1 ;
            while ( rsMetadataLlavePrimaria.next() ) {
                htLlave.put(rsMetadataLlavePrimaria.getString(4), new Integer(i++));
            }
            
            int y=1;
            while ( rsLlavesForaneas.next()) {
				htLlavesForaneas.put(rsLlavesForaneas.getString(8), new Integer(y++));
            	System.out.println("    Llave foranea "+tablename+"   "+rsLlavesForaneas.getString(8));
            }
            //carga todos los campos de la tabla
            ResultSet rsMetadataColumna = metadata.getColumns(null,null,tablename,null);
            String isLlave = new String();
			String isLlaveForanea = new String();
			int nuTamanoCampo;
			String isNulable;
            while ( rsMetadataColumna.next() ) 
            {
                String campo = rsMetadataColumna.getString(4);
                String tipo = rsMetadataColumna.getString(6);
                nuTamanoCampo = rsMetadataColumna.getInt(7);
                
                if ( rsMetadataColumna.getString(18).equals("YES") )
                {
					isNulable = "S";	
                }
                else
                {
					isNulable = "N";
                }	
                
                
                if ( htLlave.get(campo) != null ) {
                    isLlave = "S";
                } else {
                    isLlave = "N";
                }
                
                if ( htLlavesForaneas.get(campo) != null)
                {
					isLlaveForanea = "S";                	
                }
                else
                {
                	isLlaveForanea = "N";
                }
                dc = Generator.parseCampoInfo("    "+campo+"   "+tipo+"    "+nuTamanoCampo+"    "+isNulable+"   "+ isLlave +"    "+isLlaveForanea);
                v.addElement(dc);
            }
          return v;
        }
        catch ( Exception  e ) {
             System.out.print(e );
             return null;
        }

    }
    
	/*
	 * 2004-07-19
	 * methodos utilizados para la generacion de los accesstables para c/c++
	 * */
	
		public static DefinicionCampo parseCampoInfoC( String s ){
				DefinicionCampo dc = new DefinicionCampo();
				StringTokenizer lineTokenizer = new StringTokenizer(s, " ");

				//Nombre del campo
				if ( lineTokenizer.hasMoreElements() ){
				  String campoNombre = lineTokenizer.nextToken();
				  String primero = campoNombre.substring(0,1);
				  String resto   = campoNombre.substring(1,campoNombre.length());
				  campoNombre = primero.toUpperCase() + resto;
				  dc.setNombre( campoNombre );
				}
				//tipo de dato
				if ( lineTokenizer.hasMoreElements() )
				{
				  String sbTipo = lineTokenizer.nextToken();
				  dc.setTipo( sbTipo );
				  if ( sbTipo.equals("date") ) {
					  dc.setTipoJava("QDate");
					  dc.setPrefijo("dt");
				  }
				  else if ( sbTipo.equals("time") ) {
					  dc.setTipoJava("QTime");
					  dc.setPrefijo("ti");
				  }
				  else if ( sbTipo.equals("int8") ||
					   sbTipo.equals("int4") ||
					   sbTipo.equals("int2") ) {

					  dc.setTipoJava("int");
					  dc.setPrefijo("nu");

				  }
				  else if ( sbTipo.equals("numeric") || sbTipo.equals("float4")) {
					  dc.setTipoJava("double");
					  dc.setPrefijo("db");
				  }

				  else if ( sbTipo.equals("varchar")  || sbTipo.equals("text") ) {
					  dc.setTipoJava("QString");
					  dc.setPrefijo("sb");
				  }
				  else if ( sbTipo.equals("bool") ) {
					  dc.setTipoJava("bool");
					  dc.setPrefijo("bl");
				  }
				  
				  else if ( sbTipo.equals("serial") ) {
					  dc.setTipoJava("int");
					  dc.setPrefijo("nu");
				  }
				  else {
					  System.out.println("El tipo de dato "+sbTipo+" no esta manejado");
				  }

				}
				//longitud del campo
				if ( lineTokenizer.hasMoreElements() ){
				  dc.setPresicion( lineTokenizer.nextToken() );
				}
				//requerido S o N
				if ( lineTokenizer.hasMoreElements() ){
				  dc.setRequerido( lineTokenizer.nextToken() );
				}
				//es llave S o N
				if ( lineTokenizer.hasMoreElements() ){
				  dc.setLlave( lineTokenizer.nextToken() );
				}
				
				//es llave foranea S o N
				if ( lineTokenizer.hasMoreElements() ){
				  dc.setForane( lineTokenizer.nextToken() );
				}
				return dc;
			}
    
    /*2004-07-19*/
	public static Vector tablaConfig( String tablename ) {
			Vector<DefinicionCampo> v = new Vector<DefinicionCampo>();
			try{
				DefinicionCampo dc;
				Connection con = DBConexion.getDBConexion();
				if ( con == null ) {
					System.out.println("no conectado");
				}

				DatabaseMetaData metadata = con.getMetaData();
				//Carga la llave primaria de la tabla
				ResultSet rsMetadataLlavePrimaria= metadata.getPrimaryKeys(null,null,tablename);
				Hashtable<String, Integer> htLlave = new Hashtable<String, Integer>();
				
				//Cargo las llaves foraneas de la tabla            
				ResultSet rsLlavesForaneas = metadata.getImportedKeys(null,null,tablename);
				Hashtable<String, Integer> htLlavesForaneas = new Hashtable<String, Integer>();
				
				int i = 1 ;
				while ( rsMetadataLlavePrimaria.next() ) {
					htLlave.put(rsMetadataLlavePrimaria.getString(4), new Integer(i++));
				}
				
				int y=1;
				while ( rsLlavesForaneas.next()) {
					htLlavesForaneas.put(rsLlavesForaneas.getString(8), new Integer(y++));					
					
					// se a que tabla pertenece esta llave foranea
					System.out.println(rsLlavesForaneas.getString(4));				
					
					System.out.println("    Llave Foranea "+tablename+"   "+rsLlavesForaneas.getString(8));
				}
				//carga todos los campos de la tabla
				ResultSet rsMetadataColumna = metadata.getColumns(null,null,tablename,null);
				String isLlave = new String();
				String isLlaveForanea = new String();
				int nuTamanoCampo;
				String isNulable;
				while ( rsMetadataColumna.next() ) {
					String campo = rsMetadataColumna.getString(4);
					String tipo = rsMetadataColumna.getString(6);
					nuTamanoCampo = rsMetadataColumna.getInt(7);
										
					if ( htLlave.get(campo) != null ) {
						isLlave = "S";
					} else {
						isLlave = "N";
					}
					
					if ( rsMetadataColumna.getString(18).equals("YES") )
					{
						isNulable = "S";	
					}
					else
					{
						isNulable = "N";
					}
					
					if ( htLlavesForaneas.get(campo) != null)
					{
						isLlaveForanea = "S";                	
					}
					else
					{
						isLlaveForanea = "N";
					}
					
					System.out.println("    "+campo+"   "+tipo+"    "+nuTamanoCampo+"    Llave Primaria "+isLlave+"    Llave foranea "+isLlaveForanea+"    Nulo "+isNulable);
					
					dc = Generator.parseCampoInfoC("    "+campo+"   "+tipo+"    "+nuTamanoCampo+"    "+isNulable+"   "+ isLlave+ "    "+isLlaveForanea);
					v.addElement(dc);
				}
			  return v;
			}
			catch ( Exception  e ) {
				 System.out.print( "ERROR "+e);
				 return null;
			}

		}

}


