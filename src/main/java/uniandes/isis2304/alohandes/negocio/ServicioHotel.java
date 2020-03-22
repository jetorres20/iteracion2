package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Servicios Hotel del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class ServicioHotel implements VOServicioHotel {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico del servicio de hotel en Alohandes 
	 */
	private long id;
	
	/**
	 * nombre del servicio de hotel
	 */
	private String nombre;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	public ServicioHotel() {
		this.id = 0;
		this.nombre = "";
	}
	
	/**
	 * @param id
	 * @param nombre
	 */
	public ServicioHotel(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "ServicioHotel [id=" + id + ", nombre=" + nombre + "]";
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