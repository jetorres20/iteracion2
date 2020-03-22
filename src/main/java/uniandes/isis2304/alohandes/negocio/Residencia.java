package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto RESIDENCIA del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class Residencia implements VOResidencia {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El id del Operario para la residencia
	 */
	private long idOperario;
	
	/**
	 * El nit UNICO de la residencia
	 */
	private int nit;
	
	/**
	 * nombre de la residencia estudiantil
	 */
	private String nombre;
	
	/**
	 * direccion de la residencia
	 */
	private String direccion;
	
	/**
	 * telefono de la residencia con indicador pais 
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
	public Residencia() {
		this.idOperario = 0;
		this.nit = 0;
		this.nombre = "";
		this.direccion = "";
		this.telefono = "";
	}
	
	/** 
	 * Constructor con valores
	 * @param idOperario para esta residencia
	 * @param nit unico de la residencia
	 * @param nombre de la residenia
	 * @param direccion de la residencia
	 * @param telefono de la residencia
	 */
	public Residencia(long idOperario, int nit, String nombre, String direccion, String telefono) {
		this.idOperario = idOperario;
		this.nit = nit;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	/**
	 * @return el id de operario
	 */
	public long getIdOperario() {
		return idOperario;
	}

	/**
	 * @param idOperario 
	 */
	public void setIdOperario(long idOperario) {
		this.idOperario = idOperario;
	}

	/**
	 * @return el nit de la residencia
	 */
	public int getNit() {
		return nit;
	}

	/**
	 * @param nit Unico de la residencia 
	 */
	public void setNit(int nit) {
		this.nit = nit;
	}

	/**
	 * @return nombre de la residencia
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre de la residencia
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return direccion de la residencia
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion de la residencia
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return telefono de la residencia
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono de la residencia
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la residencia
	 */
	public String toString() {
		return "Residencia [idOperario=" + idOperario + ", nit=" + nit + ", nombre=" + nombre + ", direccion="
				+ direccion + ", telefono=" + telefono + "]";
	}	
}
