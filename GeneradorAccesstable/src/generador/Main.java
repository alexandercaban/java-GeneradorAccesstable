package generador;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Vector;

public class Main {

    public static void main(String args[]) {
        try {

            Connection con = DBConexion.getDBConexion();
            if (con == null) {
                System.out.println("no conectado");
            }
            DatabaseMetaData metadata = con.getMetaData();
            ResultSet rsMetadata = metadata.getTables(null, null, null, null);
            //Vector vectorMetadata = new Vector();

            Properties objSistema = System.getProperties();
            if (objSistema.getProperty("os.name").equals("Linux") || objSistema.getProperty("os.name").equals("Mac OS X")) {
                Constante.directorio = "/Desarrollo/generador/Java/";
                Constante.directorioHeaders = "/Desarrollo/generador/AccessTable/headers/";
                Constante.directorioSources = "/Desarrollo/generador/AccessTable/sources/";
                Constante.directorioListaHeaders = "/Desarrollo/generador/AccessTable/headers/";
                Constante.directorioListaSources = "/Desarrollo/generador/AccessTable/sources/";
                Constante.directorioUI = "/Desarrollo/generador/aplicacion/modulos/forms/";
                Constante.directorioProyecto = "/Desarrollo/generador/aplicacion/";
                Constante.directorioSourcesHjos = "/Desarrollo/generador/aplicacion/modulos/sources/";
                Constante.directorioHeadersHjos = "/Desarrollo/generador/aplicacion/modulos/headers/";
                Constante.ArchivoReferencia = "/Desarrollo/generador/ConstanteServiciosAccessTable.java";
                Constante.directorioHeadersLibrerias = "/Desarrollo/generador/Playtech/";
                Constante.directorioLibreria = "/Desarrollo/generador/AccessTable/";
                Constante.DIRECTORIO_ARCHIVOS_PHP = "/Desarrollo/generador/Php/";
            } else {
                Constante.directorio = "C:/Desarrollo/generador/Java/";
                Constante.directorioHeaders = "C:/Desarrollo/generador/AccessTable/headers/";
                Constante.directorioSources = "C:/Desarrollo/generador/AccessTable/sources/";
                Constante.directorioListaHeaders = "C:/Desarrollo/generador/Lista/headers/";
                Constante.directorioListaSources = "C:/Desarrollo/generador/Lista/sources/";
                Constante.directorioUI = "f:/generador/aplicacion/modulos/forms/";
                Constante.directorioProyecto = "f:/generador/aplicacion/";
                Constante.directorioSourcesHjos = "f:/generador/aplicacion/modulos/sources/";
                Constante.directorioHeadersHjos = "f:/generador/aplicacion/modulos/headers/";
                Constante.ArchivoReferencia = "f:/generador/ConstanteServiciosAccessTable.java";
                Constante.directorioLibreria = "f:/generador/AccessTable/";
                Constante.directorioHeadersLibrerias = "C:/Desarrollo/generador/playtech/";
                Constante.DIRECTORIO_ARCHIVOS_PHP = "C:/Desarrollo/Generador/Php/";
            }

            while (rsMetadata.next()) {

                String tabla = rsMetadata.getString(3);
                System.out.println(tabla);
                //tablas problemas
                if (tabla.equals("archivo")
                        || tabla.equals("bitacorasincronizacion")
                        || tabla.equals("tmp_tramasventasgamble")
                        || tabla.equals("tramasventasgamble")
                        || tabla.equals("tempconexred")
                        || tabla.equals("base_subd")
                        || tabla.equals("creditoglobal20agos")
                        || tabla.equals("creditoglobalx1")
                        || tabla.equals("creditosglobalnn")
                        || tabla.equals("creditosglobalx")
                        || tabla.equals("creditosokjr")
                        || tabla.equals("recibos22agos")
                        || tabla.equals("tbclientes")
                        || tabla.equals("tbcreditos")
                        || tabla.equals("usuariorol")
                        || tabla.equals("transaccion")
                        || tabla.equals("tempcodeudor")
                        || tabla.equals("tempcliente")
                        || tabla.equals("detallesinteresmora")
                        || tabla.equals("historialcreditoprenda")
                        || tabla.equals("historialdetalleinteresmora")
                        || tabla.equals("Historialduenospredio")
                        || tabla.equals("historialduenospredio")
                        || tabla.equals("registroprocesos")
                        || tabla.equals("temporaldis")
                        || tabla.equals("franjahoras")) {
                    continue;
                }
                String tipoTabla = rsMetadata.getString(4);
                if (tipoTabla.equals("TABLE") && !tabla.startsWith("sql")) {

                    String primero = tabla.substring(0, 1);
                    String resto = tabla.substring(1, tabla.length());

//					vectorMetadata.addElement(primero.toUpperCase()+resto);
                    Vector tablaInfo = Generator.tablaConf(tabla);
                    Vector tablaInfoC = Generator.tablaConfig(tabla);

                    System.out.println("Tabla -> " + primero.toUpperCase() + resto);

                    // Creo los accesstables como tal de QT
//					CreaHeader.crearHeader	(primero.toUpperCase() + resto,tablaInfoC);
//					CreaHeader.crearSourceFinal(primero.toUpperCase() + resto,tablaInfoC);					
                    //			CreaHeader.crearHeaderProyecto(primero.toUpperCase() + resto,tablaInfoC);
                    //			CreaHeader.crearSourceFinal(primero.toUpperCase() + resto,tablaInfoC);
                    // Crea la lista de AT de QT					
                    //			CreaHeaderLista.crearHeaderLista(primero.toUpperCase()+resto, tablaInfoC);
                    //			CreaHeaderLista.crearSourceLista(primero.toUpperCase()+resto, tablaInfoC);
//					CreaHeaderLista.crearHeaderListaProyecto(primero.toUpperCase()+resto, tablaInfoC);
//					CreaHeaderLista.crearSourceLista(primero.toUpperCase()+resto, tablaInfoC);
                    //Creo las headers para los AT y las listas de AT de QT
//					CreaHeader.crearHeaderLibreria(primero.toUpperCase() + resto,tablaInfoC);					
//					CreaHeaderLista.crearHeaderListaLibrerias(primero.toUpperCase()+resto,tablaInfoC);
                    //			CreaHeader.crearHeaderLibreriaProyecto(primero.toUpperCase() + resto,tablaInfoC);					
                    //			CreaHeaderLista.crearHeaderListaLibreriasProyecto(primero.toUpperCase()+resto,tablaInfoC);
                    //	 Crea las clases de Java
                    //System.out.println(primero.toUpperCase() + " " + resto + " " +   tablaInfo);
                    CreaDA.crearDA(primero.toUpperCase() + resto, tablaInfo);

                    CrearArchivosPHP.crearClasePhp(primero.toUpperCase() + resto, tablaInfoC);

                    //Crea las Forms de QT
                    //CreaUI.crearUI(primero.toUpperCase()+resto,tablaInfoC);
                    //CrearClasesFormas.crearHeaderClasesFormas( primero.toUpperCase()+resto);
                    //CrearClasesFormas.crearSourceClasesFormas( tablaInfoC,primero.toUpperCase()+resto);		
                }
            }

            /*CreaMainWindowUI.crearMainWindowUI( vectorMetadata );
			 CreaHeaderMainWindow.crearHeaderMainWindow( vectorMetadata );
			 CreaHeaderMainWindow.crearSourceMainWindow( vectorMetadata );*/
            //CrearClasesFormas.crearMakeFileLibreira( vectorMetadata );
            //CrearConstantes.crearHeaders_Constantes_C_PLUS_PLUS( vectorMetadata );
            //CrearConstantes.crearSources_Constantes_C_PLUS_PLUS( vectorMetadata );
            //CrearConstantes.crearConstantesJava( vectorMetadata);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
