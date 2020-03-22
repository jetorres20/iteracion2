package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Servicio privado del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class ServicioPrivado implements VOServicioPrivado{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long id;
	
	private String nombre;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public ServicioPrivado() {
		this.id = 0;
		this.nombre = "";
	}
	
	/**
	 * Constructor con valores
	 * @param id
	 * @param nombre
	 */
	public ServicioPrivado(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "ServicioPrivado [id=" + id + ", nombre=" + nombre + "]";
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
}