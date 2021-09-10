package generador;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
public class CreaEsquema{
    public static void crearTabla( String nombreTabla,Vector vdc )
    throws IOException{
          FileWriter dat = new FileWriter(Constante.directorio+"cr"+nombreTabla+".sql");
          String separador = new String("\n");
          String espacios = new String("                                             ");
          BufferedWriter out = new BufferedWriter( dat );
          DefinicionCampo dc;
          out.write("/*\n");
          out.write("    Propiedad intelectual de Play Tech. \n\n");
          out.write("    Nombre      :    "+"cr"+nombreTabla+".sql"+"\n");
          out.write("    Descripcion :    "+"Crea la tabla "+nombreTabla+"\n\n");
          out.write("    Autor       :    "+"Play Tech"+"\n\n");
          out.write("    Modificaciones \n");
          out.write("    Fecha    Autor    Descripcion \n");
          out.write("*/\n");
          out.write("CREATE TABLE "+nombreTabla+"\n");
          out.write("(");
          for ( int i = 0 ; i < vdc.size(); i ++ ) {
              dc = (DefinicionCampo) vdc.elementAt(i);
              out.write(separador);
              out.write("    "+dc.getNombre()+espacios.substring(1,15 - (dc.getNombre()).length()));
              if ( (dc.getTipo()).equals("DATE") ||
                   (dc.getTipo()).equals("TIME") ||
                   (dc.getTipo()).equals("INTEGER") ||
                   (dc.getTipo()).equals("REAL")) {
                  out.write(dc.getTipo());
              }
              if ( (dc.getTipo()).equals("VARCHAR") ) {
                  out.write(dc.getTipo()+"("+dc.getPresicion()+")");
              }
              separador = ",\n";
          }

          out.write("\n);\n");
          out.flush();
          dat.close();
    }
}