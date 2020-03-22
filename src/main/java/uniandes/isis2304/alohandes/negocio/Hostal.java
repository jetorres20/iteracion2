package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

/**
 * Clase para modelar el concepto Hostal del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class Hostal implements VOHostal {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El id del Operario para el Hostal
	 */
	private long idOperario;
	
	/**
	 * El nit UNICO del Hostal
	 */
	private int nit;
	
	/**
	 * nombre del Hostal
	 */
	private String nombre;
	
	/**
	 * La hora en que abre el hostal
	 */
	private Timestamp horaAbre;
	
	/**
	 * La hora en que cierra el hostal
	 */
	private Timestamp horaCierra;
	
	/**
	 * direccion del Hostal
	 */
	private String direccion;
	
	/**
	 * telefono del Hostal con indicador pais 
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
	public Hostal() {		
		this.idOperario = 0;
		this.nit = 0;
		this.nombre = "";
		this.horaAbre = new Timestamp(0);
		this.horaCierra = new Timestamp(0);;
		this.direccion = "";
		this.telefono = "";
	}
	
	/**
	 * Constructor con valores
	 * @param idOperario para este hostal
	 * @param nit unico del hostal
	 * @param nombre del hostal
	 * @param horaAbre tiempo Colombia
	 * @param horaCierra tiempo Colombia
	 * @param direccion del hostal
	 * @param telefono del hostal
	 */
	public Hostal(long idOperario, int nit, String nombre, Timestamp horaAbre, Timestamp horaCierra, String direccion,
			String telefono) {		
		this.idOperario = idOperario;
		this.nit = nit;
		this.nombre = nombre;
		this.horaAbre = horaAbre;
		this.horaCierra = horaCierra;
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
	 * @return the horaAbre
	 */
	public Timestamp getHoraAbre() {
		return horaAbre;
	}

	/**
	 * @param horaAbre the horaAbre to set
	 */
	public void setHoraAbre(Timestamp horaAbre) {
		this.horaAbre = horaAbre;
	}

	/**
	 * @return the horaCierra
	 */
	public Timestamp getHoraCierra() {
		return horaCierra;
	}

	/**
	 * @param horaCierra the horaCierra to set
	 */
	public void setHoraCierra(Timestamp horaCierra) {
		this.horaCierra = horaCierra;
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
	 * @return Una cadena de caracteres con todos los atributos del Hostal
	 */
	public String toString() {
		return "Hostal [idOperario=" + idOperario + ", nit=" + nit + ", nombre=" + nombre + ", horaAbre=" + horaAbre
				+ ", horaCierra=" + horaCierra + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}	
}