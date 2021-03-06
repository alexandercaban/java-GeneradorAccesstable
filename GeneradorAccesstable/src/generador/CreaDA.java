package generador;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.Vector;
public class CreaDA{
    public static void crearDA( String nombreTabla,Vector vdc )
    throws IOException{
    	
    	Date dtFechaActual ;
    	Time tiHoraActual;
	    java.util.Date udtFechaActual =new java.util.Date();
	    dtFechaActual = new Date( udtFechaActual.getTime() );
	    tiHoraActual = new Time( udtFechaActual.getTime() );
	    
          FileWriter dat = new FileWriter(Constante.directorio+nombreTabla+".java");         
          String espacios = new String("                                             ");          
          BufferedWriter out = new BufferedWriter( dat );          
          DefinicionCampo dc;
          
          out.write("/*\n");
          out.write("    Propiedad intelectual de Play Tech. \n\n");
          out.write("    Nombre      :    "+nombreTabla+".java"+"\n");
          out.write("    Descripcion :    "+"Definicion objeto java"+"\n\n");
          out.write("    Autor       :    "+"Play Tech."+"\n");
          out.write("    Creacion :    "+ dtFechaActual+" - "+ tiHoraActual+"\n\n");
          out.write("    Historial de Modificaciones \n");
          out.write("    Fecha    Autor    	Descripcion \n");
          out.write("*/\n\n");
          out.write("package "+ Constante.nombreProyecto +".general.db.accesstable;\n\n");
          out.write("import "+ Constante.nombreProyecto +".general.error.AppException;\n");
          out.write("import "+ Constante.nombreProyecto +".general.error.ConstanteError;\n");
          out.write("import "+ Constante.nombreProyecto +".general.db.conexion.DBConexion;\n");
          out.write("import "+ Constante.nombreProyecto +".general.util.Constante;\n");
          out.write("import "+ Constante.nombreProyecto +".general.util.Parser;\n");
          out.write("import java.sql.PreparedStatement;\n");
          out.write("import java.sql.ResultSet;\n");
          out.write("import java.sql.Time;\n");
          out.write("import java.sql.Types;\n");
          out.write("import java.sql.Date;\n\n");
          out.write("    /* @author PlayTech \n");
          out.write("     * @Version 1.0\n");
          out.write("    */"+" \n\n");
                   
          out.write("public class "+nombreTabla+"{\n");
          // Crea los atributos de la clase
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              out.write("    private  "+dc.getTipoJava()+
                  espacios.substring(1,10 - (dc.getTipoJava()).length())+
                  dc.getPrefijo()+dc.getNombre()+";\n");
          }
          out.write("\n");

          // Crea un constructor para la clase
          out.write("    /* Constructor "+ nombreTabla +"*/\n");
          out.write("    public "+nombreTabla+"(){\n");
          //asigna valores recuparados al objeto
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              String tipoJava = dc.getTipoJava();
              String primero = tipoJava.substring(0,1);
              String resto   = tipoJava.substring(1,tipoJava.length());
              tipoJava = primero.toUpperCase() + resto;

              if ( tipoJava.equals("Int") ) {
                  out.write("                "+dc.getPrefijo()+dc.getNombre()+" = "+"Constante.INT_NULL"+";\n");
              }

              if ( tipoJava.equals("Double") ) {
                  out.write("                this."+dc.getPrefijo()+dc.getNombre()+" = "+"Constante.INT_NULL"+";\n");
              }
          }
          out.write("    }\n");
          out.write("\n");

          // Crea los metodos para asignar valor a los atributos de la clase
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              out.write ("    /* \n");
              out.write ("    *@param "+ dc.getTipoJava()+" "+dc.getPrefijo()+"New"+dc.getNombre() +"\n");
			  out.write ("    *@throws AppException \n");
			  out.write ("    */ \n");
              out.write("    public void set"+dc.getNombre()+"("+dc.getTipoJava()+" "+dc.getPrefijo()+"New"+dc.getNombre()+")\n");
              out.write("    throws AppException{\n");
              out.write("        try{\n");
              out.write("            this."+dc.getPrefijo()+dc.getNombre()+" = "+dc.getPrefijo()+"New"+dc.getNombre()+";\n");
              out.write("        } catch ( Exception e ) {\n");
              out.write("            throw AppException.getException(e);\n");
              out.write("        }\n");
              out.write("    }\n\n");
          }

          // Crea los metodos para recuperar valores de los atributos de la clase
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              out.write ("    /* \n");
              out.write ("    *@return "+ dc.getTipoJava()+" "+dc.getPrefijo()+dc.getNombre() +"\n");
              out.write ("    *@throws AppException \n");
              out.write ("    */ \n");
              out.write("    public  "+dc.getTipoJava()+ " get"+dc.getNombre()+"()\n");
              out.write("    throws AppException{\n");
              out.write("        try{\n");
              out.write("            return  this."+dc.getPrefijo()+dc.getNombre()+";\n");
              out.write("        } catch ( Exception e ) {\n");
              out.write("            throw AppException.getException(e);\n");
              out.write("        }\n");
              out.write("    }\n\n");
          }
          Vector<DefinicionCampo> vdcLlave = new Vector<DefinicionCampo>();
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              if ( dc.getLlave().equals("S") ) {
                  vdcLlave.addElement(dc);
              }
          }
          String sbArgumentos = new String();
          String sbArgumentosCall = new String();
          String sbArgumentosCallThis = new String();
          String sbStringError = new String("\"");
          String separador = new String();
          String separador2 = new String();
          // arma cadenas de where
          if ( vdcLlave.size() > 0 ) {

              separador = " ";
              separador2 = "";
              for ( int i = 0 ; i < vdcLlave.size(); i ++ ) {
                  dc = (DefinicionCampo) vdcLlave.elementAt(i);
                  sbArgumentos += separador+dc.getTipoJava()+" "+dc.getPrefijo()+dc.getNombre();
                  sbArgumentosCall += separador+dc.getPrefijo()+dc.getNombre();
                  sbArgumentosCallThis += separador+"this."+dc.getPrefijo()+dc.getNombre();
                  sbStringError += separador2+dc.getNombre()+"=\"+"+dc.getPrefijo()+dc.getNombre();
                  separador = ", ";
                  separador2 = "+\",\"+\" ";
              }

          }

          // Crea el metodo de insercion en la base de datos
          out.write("    public  void insertDB()\n");
          out.write("    throws AppException{\n");
          out.write("        try{\n");
          if ( vdcLlave.size() > 0 ) {
              out.write("            "+nombreTabla+".valNotExistDB("+sbArgumentosCallThis+" );\n");
          }
          out.write("            PreparedStatement stmInsert"+nombreTabla+";\n");

          out.write("            String sbInsert = new String();\n");
          out.write("            sbInsert = \"insert into "+nombreTabla+"(\"+");
          separador = new String("\n");
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              out.write(separador);
              out.write("                        \"    "+dc.getNombre());
              separador = ", \"+\n";
          }
          out.write(") \"+\n");
          out.write("                    \"values(");
          separador = "";
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              out.write(separador);
              out.write("?");
              separador = ",";
          }
          out.write(")\";\n");
          out.write("            stmInsert"+nombreTabla+" = DBConexion.getPreparedStatement( sbInsert );\n");
          out.write("\n");

          //asigna valores a las variables del insert
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              String tipoJava = dc.getTipoJava();
              String primero = tipoJava.substring(0,1);
              String resto   = tipoJava.substring(1,tipoJava.length());
              tipoJava = primero.toUpperCase() + resto;
                if ( tipoJava.equals("Int") ) {
                  out.write("            if ( this."+dc.getPrefijo()+dc.getNombre()+" == Constante.INT_NULL ){\n");
                  out.write("                stmInsert"+nombreTabla+".setNull("+(i+1)+", Types.INTEGER );\n");
                  out.write("             }else { \n");
                  out.write("                stmInsert"+nombreTabla+".set"+tipoJava+"("+(i+1)+",this."+dc.getPrefijo()+dc.getNombre()+");\n");
                  out.write("             }\n");
                } else if ( tipoJava.equals("Double") ) {
                  out.write("            if ( this."+dc.getPrefijo()+dc.getNombre()+" == Constante.INT_NULL ){\n");
                  out.write("                stmInsert"+nombreTabla+".setNull("+(i+1)+", Types.DOUBLE );\n");
                  out.write("             }else { \n");
                  out.write("                stmInsert"+nombreTabla+".set"+tipoJava+"("+(i+1)+",this."+dc.getPrefijo()+dc.getNombre()+");\n");
                  out.write("             }\n");
                } else {
                  out.write("            stmInsert"+nombreTabla+".set"+tipoJava+"("+(i+1)+",this."+dc.getPrefijo()+dc.getNombre()+");\n");
                }
          }
          out.write("\n");
          out.write("            stmInsert"+nombreTabla+".executeUpdate();\n");
          out.write("            stmInsert"+nombreTabla+".close();\n");
          
          // Modificado por Usuario el 29-sep-2006 - 9:42:41
          //out.write("            DBConexion.executeUpdateBackup( stmInsert"+nombreTabla+" );\n");

          out.write("        } catch ( Exception e ) {\n");
          out.write("            throw AppException.getException(e);\n");
          out.write("        }\n");
          out.write("    }\n");

          // arma el where para los caso que tenga llave primaria
          String sbWhere = new String("                            \"Where ");
          separador = "";
          if ( vdcLlave.size() > 0 ) {
              for ( int i = 0 ; i < vdcLlave.size(); i ++ ) {
                  dc = (DefinicionCampo) vdcLlave.elementAt(i);
                  sbWhere = sbWhere+separador+dc.getNombre()+"=?  \"";
                  separador = "+\n                            \"  and ";
              }
          }
          // si  tiene llave primaria no genera los metodos de actualizacion
          if ( vdcLlave.size() > 0 ) {

              //crea el metodo para la actualizacion en la base de datos
              out.write("    public  void updateDB()\n");
              out.write("    throws AppException{\n");
              out.write("        try{\n");
              out.write("            "+nombreTabla+".valExistDB("+sbArgumentosCallThis+" );\n");
              out.write("            PreparedStatement stmUpdate"+nombreTabla+";\n");
              out.write("            String sbUpdate = new String();\n");
              out.write("            sbUpdate = \"update "+nombreTabla+" set \"+");
              separador = new String("\n");
              for ( int i = 0 ; i < vdc.size(); i ++ ) {
                  dc = (DefinicionCampo) vdc.elementAt(i);
                  out.write(separador);
                  out.write("                        \"    "+dc.getNombre()+"=?");
                  separador = ", \"+\n";
              }
              out.write(" \"+\n");
              out.write(sbWhere);
              out.write(";\n");
              out.write("            stmUpdate"+nombreTabla+" = DBConexion.getPreparedStatement( sbUpdate );\n");
              out.write("\n");

              //asigna valores a las variables del update
              for ( int i = 0 ; i < vdc.size(); i ++ ) {
                  dc = (DefinicionCampo) vdc.elementAt(i);
                  String tipoJava = dc.getTipoJava();
                  String primero = tipoJava.substring(0,1);
                  String resto   = tipoJava.substring(1,tipoJava.length());
                  tipoJava = primero.toUpperCase() + resto;
                  if ( tipoJava.equals("Int") ) {
                      out.write("            if ( this."+dc.getPrefijo()+dc.getNombre()+" == Constante.INT_NULL ){\n");
                      out.write("                stmUpdate"+nombreTabla+".setNull("+(i+1)+", Types.INTEGER );\n");
                      out.write("             }else { \n");
                      out.write("                stmUpdate"+nombreTabla+".set"+tipoJava+"("+(i+1)+",this."+dc.getPrefijo()+dc.getNombre()+");\n");
                      out.write("             }\n");
                  } else if ( tipoJava.equals("Double") ) {
                      out.write("            if ( this."+dc.getPrefijo()+dc.getNombre()+" == Constante.INT_NULL ){\n");
                      out.write("                stmUpdate"+nombreTabla+".setNull("+(i+1)+", Types.DOUBLE );\n");
                      out.write("             }else { \n");
                      out.write("                stmUpdate"+nombreTabla+".set"+tipoJava+"("+(i+1)+",this."+dc.getPrefijo()+dc.getNombre()+");\n");
                      out.write("             }\n");
                  } else {
                     out.write("            stmUpdate"+nombreTabla+".set"+tipoJava+"("+(i+1)+",this."+dc.getPrefijo()+dc.getNombre()+");\n");
                  }

              }
              out.write("\n");

              for ( int i = 0 ; i < vdcLlave.size(); i ++ ) {
                  dc = (DefinicionCampo) vdcLlave.elementAt(i);
                  String tipoJava = dc.getTipoJava();
                  String primero = tipoJava.substring(0,1);
                  String resto   = tipoJava.substring(1,tipoJava.length());
                  tipoJava = primero.toUpperCase() + resto;

                  out.write("            stmUpdate"+nombreTabla+".set"+tipoJava+"("+(i+vdc.size()+1)+",this."+dc.getPrefijo()+dc.getNombre()+");\n");
              }

              out.write("            stmUpdate"+nombreTabla+".executeUpdate();\n");
              out.write("            stmUpdate"+nombreTabla+".close();\n");
              
              //	Modificado por Usuario el 29-sep-2006 - 9:42:41
              //out.write("            DBConexion.executeUpdateBackup( stmUpdate"+nombreTabla+" );\n");

              out.write("\n");
              out.write("        } catch ( Exception e ) {\n");
              out.write("            throw AppException.getException(e);\n");
              out.write("        }\n");
              out.write("    }\n");
          }
/**********************/
/*
          out.write("  static public void toStringTable()\n");
          out.write("    throws AppException{\n");
          out.write("        try{\n");
          out.write("            PreparedStatement stmLoadAll"+nombreTabla+";\n");

          out.write("            String sbLoad = new String();\n");
          out.write("            sbLoad = \"select \"+");
          separador = new String("\n");
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              out.write(separador);
              out.write("                        \"    "+dc.getNombre());
              separador = ", \"+\n";
          }
          out.write(" \"+\n");
          out.write("                        \"from "+nombreTabla+" \"+\n");
          out.write(";\n");
          out.write("            stmLoadAll"+nombreTabla+" = DBConexion.getPreparedStatement( sbLoad );\n");
          out.write("\n");

          out.write("            ResultSet  rsResult;");
          out.write("            rsResult = stmLoadAll"+nombreTabla+".executeQuery();\n");
          out.write("            while (rsResult.next()){\n");

          //asigna valores recuparados al objeto
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              String tipoJava = dc.getTipoJava();
              String primero = tipoJava.substring(0,1);
              String resto   = tipoJava.substring(1,tipoJava.length());
              tipoJava = primero.toUpperCase() + resto;

              out.write("            this."+dc.getPrefijo()+dc.getNombre()+" = "+"rsResult.get"+tipoJava+"("+(i+1)+");\n");

              if ( tipoJava.equals("Int") ) {
                  out.write("            if ( rsResult.wasNull() ){\n");
                  out.write("                this."+dc.getPrefijo()+dc.getNombre()+" = "+"Constante.INT_NULL"+";\n");
                  out.write("             } \n");
              }

              if ( tipoJava.equals("Float") ) {
                  out.write("            if ( rsResult.wasNull() ){\n");
                  out.write("                this."+dc.getPrefijo()+dc.getNombre()+" = "+"Constante.INT_NULL"+";\n");
                  out.write("             } \n");
              }
              if ( tipoJava.equals("String") ) {
                  out.write("            if ( rsResult.wasNull() ){\n");
                  out.write("                this."+dc.getPrefijo()+dc.getNombre()+" = "+"Constante.INT_NULL"+";\n");
                  out.write("             } \n");
              }
              if ( tipoJava.equals("String") ) {
                  out.write("            if ( rsResult.wasNull() ){\n");
                  out.write("                this."+dc.getPrefijo()+dc.getNombre()+" = "+"Constante.INT_NULL"+";\n");
                  out.write("             } \n");
              }
              if ( tipoJava.equals("String") ) {
                  out.write("            if ( rsResult.wasNull() ){\n");
                  out.write("                this."+dc.getPrefijo()+dc.getNombre()+" = "+"Constante.INT_NULL"+";\n");
                  out.write("             } \n");
              }

          }
          out.write("            }\n");
          out.write("            rsResult.close();\n");
          out.write("            stmLoadAll"+nombreTabla+".close();\n");

          out.write("\n");

          out.write("        } catch ( Exception e ) {\n");
          out.write("            throw AppException.getException(e);\n");
          out.write("        }\n");
          out.write("    }\n");
*/
/**********************/

          // si  tiene llave primaria genera el metodos de recuperacion de base de datos
          if ( vdcLlave.size() > 0 ) {


              out.write("    public  void loadDB("+sbArgumentos+")\n");
              out.write("    throws AppException{\n");
              out.write("        try{\n");
              out.write("            "+nombreTabla+".valExistDB("+sbArgumentosCall+" );\n");
              out.write("            PreparedStatement stmLoad"+nombreTabla+";\n");

              out.write("            String sbLoad = new String();\n");
              out.write("            sbLoad = \"select \"+");
              separador = new String("\n");
              for ( int i = 0 ; i < vdc.size(); i ++ ) {
                  dc = (DefinicionCampo) vdc.elementAt(i);
                  out.write(separador);
                  out.write("                        \"    "+dc.getNombre());
                  separador = ", \"+\n";
              }
              out.write(" \"+\n");
              out.write("                        \"from "+nombreTabla+" \"+\n");
              out.write(sbWhere);
              out.write(";\n");
              out.write("            stmLoad"+nombreTabla+" = DBConexion.getPreparedStatement( sbLoad );\n");
              out.write("\n");

              for ( int i = 0 ; i < vdcLlave.size(); i ++ ) {
                  dc = (DefinicionCampo) vdcLlave.elementAt(i);
                  String tipoJava = dc.getTipoJava();
                  String primero = tipoJava.substring(0,1);
                  String resto   = tipoJava.substring(1,tipoJava.length());
                  tipoJava = primero.toUpperCase() + resto;
                  out.write("            stmLoad"+nombreTabla+".set"+tipoJava+"("+(i+1)+","+dc.getPrefijo()+dc.getNombre()+");\n");
              }
              out.write("\n");

              out.write("            ResultSet  rsResult;");
              out.write("            rsResult = stmLoad"+nombreTabla+".executeQuery();\n");
              out.write("            rsResult.next();\n");

              //asigna valores recuparados al objeto
              for ( int i = 0 ; i < vdc.size(); i ++ ) {
                  dc = (DefinicionCampo) vdc.elementAt(i);
                  String tipoJava = dc.getTipoJava();
                  String primero = tipoJava.substring(0,1);
                  String resto   = tipoJava.substring(1,tipoJava.length());
                  tipoJava = primero.toUpperCase() + resto;

                  out.write("            this."+dc.getPrefijo()+dc.getNombre()+" = "+"rsResult.get"+tipoJava+"("+(i+1)+");\n");
                  if ( tipoJava.equals("Int") ) {
                      out.write("            if ( rsResult.wasNull() ){\n");
                      out.write("                this."+dc.getPrefijo()+dc.getNombre()+" = "+"Constante.INT_NULL"+";\n");
                      out.write("             } \n");
                  }

                  if ( tipoJava.equals("Double") ) {
                      out.write("            if ( rsResult.wasNull() ){\n");
                      out.write("                this."+dc.getPrefijo()+dc.getNombre()+" = "+"Constante.INT_NULL"+";\n");
                      out.write("             } \n");
                  }
              }
              out.write("\n");
              out.write("            rsResult.close();\n");
              out.write("            stmLoad"+nombreTabla+".close();\n");

              out.write("\n");

              out.write("        } catch ( Exception e ) {\n");
              out.write("            throw AppException.getException(e);\n");
              out.write("        }\n");
              out.write("    }\n");
          }

          // si  tiene llave primaria genera el metodos para el borrado del registro
          if ( vdcLlave.size() > 0 ) {

              //crea el metodo para la actualizacion en la base de datos
              out.write("    public  void deleteDB()\n");
              out.write("    throws AppException{\n");
              out.write("        try{\n");
              out.write("            "+nombreTabla+".valExistDB("+sbArgumentosCallThis+" );\n");
              out.write("            PreparedStatement stmDelete"+nombreTabla+";\n");
              out.write("            String sbDelete = new String();\n");
              out.write("            sbDelete = \"delete from "+nombreTabla+" \"+\n");
              out.write(sbWhere);
              out.write(";\n");
              out.write("            stmDelete"+nombreTabla+" = DBConexion.getPreparedStatement( sbDelete );\n");
              out.write("\n");

              for ( int i = 0 ; i < vdcLlave.size(); i ++ ) {
                  dc = (DefinicionCampo) vdcLlave.elementAt(i);
                  String tipoJava = dc.getTipoJava();
                  String primero = tipoJava.substring(0,1);
                  String resto   = tipoJava.substring(1,tipoJava.length());
                  tipoJava = primero.toUpperCase() + resto;
                  out.write("            stmDelete"+nombreTabla+".set"+tipoJava+"("+(i+1)+",this."+dc.getPrefijo()+dc.getNombre()+");\n");
              }
              out.write("\n");
              out.write("            stmDelete"+nombreTabla+".executeUpdate();\n");
              out.write("            stmDelete"+nombreTabla+".close();\n");
              
              //	Modificado por Usuario el 29-sep-2006 - 9:42:41
              //out.write("            DBConexion.executeUpdateBackup( stmDelete"+nombreTabla+" );\n");

              out.write("\n");
              out.write("        } catch ( Exception e ) {\n");
              out.write("            throw AppException.getException(e);\n");
              out.write("        }\n");
              out.write("    }\n");
          }

          // si  tiene llave primaria genera el metodos de  de verificacion de
          if ( vdcLlave.size() > 0 ) {

              out.write("    public  static boolean existDB("+sbArgumentos+")\n");
              out.write("    throws AppException{\n");
              out.write("        try{\n");
              out.write("            boolean blResult;\n");
              out.write("            PreparedStatement stmLoad"+nombreTabla+";\n");
              out.write("            String sbLoad = new String();\n");
              out.write("            sbLoad = \"select \"+");
              separador = new String("\n");
              for ( int i = 0 ; i < vdc.size(); i ++ ) {
                  dc = (DefinicionCampo) vdc.elementAt(i);
                  out.write(separador);
                  out.write("                        \"    "+dc.getNombre());
                  separador = ", \"+\n";
              }
              out.write(" \"+\n");
              out.write("                        \"from "+nombreTabla+" \"+\n");
              out.write(sbWhere);
              out.write(";\n");
              out.write("            stmLoad"+nombreTabla+" = DBConexion.getPreparedStatement( sbLoad );\n");
              out.write("\n");

              for ( int i = 0 ; i < vdcLlave.size(); i ++ ) {
                  dc = (DefinicionCampo) vdcLlave.elementAt(i);
                  String tipoJava = dc.getTipoJava();
                  String primero = tipoJava.substring(0,1);
                  String resto   = tipoJava.substring(1,tipoJava.length());
                  tipoJava = primero.toUpperCase() + resto;
                  out.write("            stmLoad"+nombreTabla+".set"+tipoJava+"("+(i+1)+","+dc.getPrefijo()+dc.getNombre()+");\n");

              }
              out.write("\n");

              out.write("            ResultSet  rsResult;\n");
              out.write("            rsResult = stmLoad"+nombreTabla+".executeQuery();\n");
              out.write("            if ( !rsResult.isAfterLast() && !rsResult.isBeforeFirst()  ){\n");
              out.write("                blResult = false;\n");
              out.write("             } else {\n");
              out.write("                blResult = true;\n");
              out.write("             } \n");

              out.write("\n");
              out.write("            rsResult.close();\n");
              out.write("            stmLoad"+nombreTabla+".close();\n");

              out.write("            return blResult;\n");
              out.write("\n");

              out.write("        } catch ( Exception e ) {\n");
              out.write("            throw AppException.getException(e);\n");
              out.write("        }\n");
              out.write("    }\n");
          }


          // si  tiene llave primaria genera el metodos de  de verificacion de
          if ( vdcLlave.size() > 0 ) {


              out.write("    public static void valExistDB("+sbArgumentos+")\n");
              out.write("    throws AppException{\n");
              out.write("        try{\n");
              out.write("            if ( ! "+nombreTabla+".existDB("+sbArgumentosCall+" ) ){\n");
              out.write("                throw AppException.getException( ConstanteError.REG_NO_EXISTE ,"+sbStringError+"+\"|\"+\""+nombreTabla+"\");\n");
              out.write("            }\n");
              out.write("\n");
              out.write("        } catch ( Exception e ) {\n");
              out.write("            throw AppException.getException(e);\n");
              out.write("        }\n");
              out.write("    }\n");
          }

         sbArgumentosCall = new String();
         sbArgumentos = new String();

          // si  tiene llave primaria genera el metodos de  de verificacion de
          if ( vdcLlave.size() > 0 ) {

              separador = " ";
              for ( int i = 0 ; i < vdcLlave.size(); i ++ ) {
                  dc = (DefinicionCampo) vdcLlave.elementAt(i);
                  sbArgumentos += separador+dc.getTipoJava()+" "+dc.getPrefijo()+dc.getNombre();
                  sbArgumentosCall += separador+dc.getPrefijo()+dc.getNombre();
                  separador = ", ";
              }

              out.write("    public static void valNotExistDB("+sbArgumentos+")\n");
              out.write("    throws AppException{\n");
              out.write("        try{\n");
              out.write("            if (  "+nombreTabla+".existDB("+sbArgumentosCall+" ) ){\n");
              out.write("                throw AppException.getException( ConstanteError.REG_YA_EXISTE ,"+sbStringError+"+\"|\"+\""+nombreTabla+"\");\n");
              out.write("            }\n");
              out.write("\n");
              out.write("        } catch ( Exception e ) {\n");
              out.write("            throw AppException.getException(e);\n");
              out.write("        }\n");
              out.write("    }\n");
          }

          out.write("    public  String toString(){\n");
          out.write("        String sbObjecto=\"\";\n");
          String Separador_aux="X";
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              if ( ! Separador_aux.equals("X") ) {
                  out.write("        sbObjecto+="+Separador_aux+";\n");
              }
              Separador_aux = "Constante.SEPARADOR_CAMPO";
              dc = (DefinicionCampo) vdc.elementAt(i);
              String tipoJava = dc.getTipoJava();
              String primero = tipoJava.substring(0,1);
              String resto   = tipoJava.substring(1,tipoJava.length());
              tipoJava = primero.toUpperCase() + resto;
                if ( tipoJava.equals("Int") ) {
                  out.write("        if ( this."+dc.getPrefijo()+dc.getNombre()+" != Constante.INT_NULL ){\n");
                  out.write("            sbObjecto+=this."+dc.getPrefijo()+dc.getNombre()+";\n");
                  out.write("         } \n");
                } else if ( tipoJava.equals("Double") || tipoJava.toUpperCase().equals("DOUBLE") ) {
                  out.write("        if ( this."+dc.getPrefijo()+dc.getNombre()+" != Constante.INT_NULL ){\n");
                  out.write("            sbObjecto+=this."+dc.getPrefijo()+dc.getNombre()+";\n");
                  out.write("         }\n");
                } else if ( tipoJava.equals("Boolean") ) {
                  out.write("        sbObjecto+=this."+dc.getPrefijo()+dc.getNombre()+"?Constante.sbTRUE:Constante.sbFALSE;\n");
                } else {
                  out.write("        if ( this."+dc.getPrefijo()+dc.getNombre()+" != null ){\n");
                  out.write("            sbObjecto+=this."+dc.getPrefijo()+dc.getNombre()+";\n");
                  out.write("         }\n");
                }
          }
          out.write("\n");
          out.write("        return sbObjecto;\n");

          out.write("    }\n");


          out.write("    public  void loadString( String sbObjString )\n");
          out.write("    throws AppException{\n");
          out.write("        try{\n");
          out.write("            Parser objParserCampos = new Parser( sbObjString, Constante.SEPARADOR_CAMPO );\n");

          //asigna valores recuparados al objeto
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              String tipoJava = dc.getTipoJava();
              String primero = tipoJava.substring(0,1);
              String resto   = tipoJava.substring(1,tipoJava.length());
              tipoJava = primero.toUpperCase() + resto;
              out.write("            this."+dc.getPrefijo()+dc.getNombre()+" = objParserCampos.next"+tipoJava+"();\n");
          }

          out.write("\n");

          out.write("        } catch ( Exception e ) {\n");
          out.write("            throw AppException.getException(e);\n");
          out.write("        }\n");
          out.write("    }\n");
          
          out.write("\n");

          out.write("   public void save(" + sbArgumentos + ")\n");
          out.write("    throws AppException{\n");
          out.write("        try{\n");
          out.write("           if(" + nombreTabla + ".existDB(" + sbArgumentosCall + ")){\n");
          out.write("               this.updateDB();\n");
          out.write("           }\n");
          out.write("           else{\n");
          out.write("               this.insertDB();\n");
          out.write("           }\n");
          out.write("        } catch ( Exception e ) {\n");
          out.write("            throw AppException.getException(e);\n");
          out.write("        }\n");
          out.write("    }\n");

          out.write("};\n");
          out.flush();
          dat.close();
    }



}