package uniandes.isis2304.alohandes.negocio;

public class ServicioResidencia implements VOServicioResidencia {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	private long id;
	
	private String nombre;
	
	private double costoExtraNoche;
	
	private double costoExtraMes;
	
	private double costoExtraSemestre;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/	
	/**
     * Constructor por defecto
     */
	public ServicioResidencia() {		
		this.id = 0;
		this.nombre = "";
		this.costoExtraNoche = -1;
		this.costoExtraMes = -1;
		this.costoExtraSemestre = -1;
	}

	/**
	 * Constructor con valores
	 * @param id
	 * @param nombre
	 * @param costoExtraNoche
	 * @param costoExtraMes
	 * @param costoExtraSemestre
	 */
	public ServicioResidencia(long id, String nombre, double costoExtraNoche, double costoExtraMes,
			double costoExtraSemestre) {		
		this.id = id;
		this.nombre = nombre;
		this.costoExtraNoche = costoExtraNoche;
		this.costoExtraMes = costoExtraMes;
		this.costoExtraSemestre = costoExtraSemestre;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	 * @return the costoExtraNoche
	 */
	public double getCostoExtraNoche() {
		return costoExtraNoche;
	}

	/**
	 * @param costoExtraNoche the costoExtraNoche to set
	 */
	public void setCostoExtraNoche(double costoExtraNoche) {
		this.costoExtraNoche = costoExtraNoche;
	}

	/**
	 * @return the costoExtraMes
	 */
	public double getCostoExtraMes() {
		return costoExtraMes;
	}

	/**
	 * @param costoExtraMes the costoExtraMes to set
	 */
	public void setCostoExtraMes(double costoExtraMes) {
		this.costoExtraMes = costoExtraMes;
	}

	/**
	 * @return the costoExtraSemestre
	 */
	public double getCostoExtraSemestre() {
		return costoExtraSemestre;
	}

	/**
	 * @param costoExtraSemestre the costoExtraSemestre to set
	 */
	public void setCostoExtraSemestre(double costoExtraSemestre) {
		this.costoExtraSemestre = costoExtraSemestre;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de una habitacion de residencia estudiantil
	 */
	public String toString() {
		return "ServicioResidencia [id=" + id + ", nombre=" + nombre + ", costoExtraNoche=" + costoExtraNoche
				+ ", costoExtraMes=" + costoExtraMes + ", costoExtraSemestre=" + costoExtraSemestre + "]";
	}	
}
