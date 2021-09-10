package generador;

public class Constante {

    public static String directorio = "/Desarrollo/generador/Java/";

    //Nombre del proyecto, para generar el package
    public static String nombreProyecto = "supergiros";

    // Directorio de los aacesstable generados para QT
    public static String directorioLibreria = "/Desarrollo/generador/AccessTable/";
    public static String directorioHeaders = "/Desarrollo/generador/AccessTable/headers/";
    public static String directorioSources = "/Desarrollo/generador/AccessTable/sources/";

    public static String directorioHeadersLibrerias = "/Desarrollo/generador/playtech/";

    // Directorio de Lista de objetos accesstable que
    public static String directorioListaHeaders = "/Desarrollo/generador/ListaObjetos/headers/";
    public static String directorioListaSources = "/Desarrollo/generador/ListaObjetos/sources/";

    // Directorio de Formas generadas a partir de las tablas del esquema de base de datos
    public static String directorioUI = "/Desarrollo/generador/aplicacion/modulos/forms/";

    public static String directorioProyecto = new String("/Desarrollo/generador/aplicacion/");

    // Directorio de las clases que se derivan de las formas
    public static String directorioSourcesHjos = "/Desarrollo/generador/aplicacion/modulos/sources/";
    public static String directorioHeadersHjos = "/Desarrollo/generador/aplicacion/modulos/headers/";

    //	Directorio para la generacion de los AT de Php
    public static String DIRECTORIO_ARCHIVOS_PHP = new String("/Desarrollo/generador/Php/");

    public static String ArchivoReferencia = "/Desarrollo/generador/";
//        public static String conexion = "jdbc:postgresql://192.168.0.1:5432/recargasv3";
//        public static String conexion = "jdbc:postgresql://192.168.0.1:5432/creditossimples";
//        public static String conexion = "jdbc:postgresql://192.168.0.1:5432/hipotecascesar";
    //public static String conexion = "jdbc:postgresql://192.168.1.8:5432/creditoscalienero";
    public static String conexion = "jdbc:postgresql://10.108.1.5:5432/listotrunk";
//        public static String conexion = "jdbc:postgresql://192.168.0.1:5432/microcreditos";
//        public static String conexion = "jdbc:postgresql://192.168.0.1:5432/supergiros";
//        public static String conexion = "jdbc:postgresql://127.0.0.1:5432/supergiros";
    public static String usuario = "postgres";

    public static String finLinea = new String("\n");
    public static String buddy = new String("&amp;");
    public static String Comilla = new String("\"");
    public static String SLASH = new String(" \\  ");
}
