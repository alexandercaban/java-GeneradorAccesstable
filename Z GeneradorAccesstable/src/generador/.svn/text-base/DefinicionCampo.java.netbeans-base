package generador;
public class DefinicionCampo {
    public DefinicionCampo(){
    }
    private String nombre ; 		// nombre campo    
    private String tipo ;		  		// tipo de dato original
    private String presicion;		// cuando es flotante, se refiere a los decimales 
    private String requerido;  	/* cuando el campos es requerido S o N */
    private String llave; 			/* cuando el campo es parte de la llave primaria S o N */
    private String Foranea; 		/* cuando el campo es una llave foranea o es parte de una llave foranea S o N*/
    //private String tablaForanea; // nombre la tabla a la cual es llave foranea
    private String tipoJava;		//   tipo de data de Java
    private String prefijo;			// prefijo para el lenguaje
    private int nuLongitud;		// longitud del campo
    public void setNombre ( String nombre ){
        this.nombre = nombre;
    }
    public void setTipo ( String tipo  ){
        this.tipo = tipo;
    }
    public void setTipoJava ( String tipoJava  ){
        this.tipoJava = tipoJava;
    }

    public void setPresicion ( String presicion ){
        this.presicion = presicion;
    }
    public void setRequerido ( String requerido ){
        this.requerido = requerido;
    }
    public void setLlave ( String llave ){
        this.llave = llave;
    }
	public void setForane ( String llaveForanea ){
			this.Foranea = llaveForanea;
		}
    public void setPrefijo ( String prefijo ){
        this.prefijo = prefijo;
    }
    public void setLongitud ( int nuNewLongitud )
    {
    	nuLongitud = nuNewLongitud;
    }
	
    public String getNombre(){
        return this.nombre;
    }

    public String getTipo(){
        return this.tipo;
    }
    public String getTipoJava(){
        return this.tipoJava;
    }

    public String getPresicion(){
        return this.presicion;
    }
    public String getRequerido(){
        return this.requerido;
    }
    public String getLlave(){
        return this.llave;
    }
	public String getForanea(){
			return this.Foranea;
		}
    public String getPrefijo(){
        return this.prefijo;
    }
    public int getLongitud ()
    {
    	return this.nuLongitud;
    }

    public String toString (){
        return ( "nombre="+nombre+"\n"+
            "tipo="+tipo+"\n"+
            "presicion="+presicion+"\n"+
            "requerido="+requerido+"\n"+
            "llave="+llave+"\n" );
    }
}

