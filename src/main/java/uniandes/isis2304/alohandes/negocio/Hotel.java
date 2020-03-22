package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Hotel del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class Hotel implements VOHotel{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El id del Operario para el Hotel
	 */
	private long idOperario;
	
	/**
	 * El nit UNICO del Hotel
	 */
	private int nit;
	
	/**
	 * nombre del Hotel
	 */
	private String nombre;
	
	/**
	 * numero de estrellas o categoria del hotel
	 */
	private int estrellas;
	
	/**
	 * direccion del Hotel
	 */
	private String direccion;
	
	/**
	 * telefono del Hotel con indicador pais 
	 * +(areaCode)(numero)
	 * 10-14 caracteres
	 */
	private String telefono;

	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public Hotel() {
		this.idOperario = 0;
		this.nit = -1;
		this.nombre = "";
		this.estrellas = -1;
		this.direccion = "";
		this.telefono = "";
	}	
	
	/**
	 * @param idOperario
	 * @param nit hotel
	 * @param nombre hotel
	 * @param estrellas categoria hotel
	 * @param direccion hotel 
	 * @param telefono hotel
	 */
	public Hotel(long idOperario, int nit, String nombre, int estrellas, String direccion, String telefono) {
		this.idOperario = idOperario;
		this.nit = nit;
		this.nombre = nombre;
		this.estrellas = estrellas;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	/**
	 * @return the idOperario
	 */
	public long getIdOperario() {
		return idOperario;
	}

	/**
	 * @param idOperario the idOperario to set
	 */
	public void setIdOperario(long idOperario) {
		this.idOperario = idOperario;
	}

	/**
	 * @return the nit
	 */
	public int getNit() {
		return nit;
	}

	/**
	 * @param nit the nit to set
	 */
	public void setNit(int nit) {
		this.nit = nit;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the estrellas
	 */
	public int getEstrellas() {
		return estrellas;
	}

	/**
	 * @param estrellas the estrellas to set
	 */
	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Hotel
	 */
	public String toString() {
		return "Hotel [idOperario=" + idOperario + ", nit=" + nit + ", nombre=" + nombre + ", estrellas=" + estrellas
				+ ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}
}
