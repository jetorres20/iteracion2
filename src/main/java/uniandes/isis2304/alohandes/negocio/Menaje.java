package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Menaje del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class Menaje implements VOMenaje {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id del menaje. unico en todo Alohandes
	 */
	private long id;
	
	/**
	 * nombre del menaje (p.e toalla, sabana)
	 */
	private String nombre;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public Menaje() {
		this.id = 0;
		this.nombre = "";
	}
	
	/**
	 * Constructor con valores
	 * @param id
	 * @param nombre
	 */
	public Menaje(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}	

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Menaje
	 */
	public String toString() {
		return "Menaje [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
