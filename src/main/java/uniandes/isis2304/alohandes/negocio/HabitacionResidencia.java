package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Habitacion de Residencia estudiantil del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class HabitacionResidencia implements VOHabitacionResidencia {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico del recinto en todo Alohandes
	 */
	private long idRecinto;
	
	/**
	 * id unico de operario de la residencia que tiene la habitacion
	 */
	private long idResidencia;
	
	/**
	 * true si la habitacion se comparte, falso si es privado
	 */
	private int compartido;
	
	/**
	 * true si el banio se comparte, falso si es privado
	 */
	private int banioCompartido;
	
	/**
	 * numero de la habitacion dentro de la residencia
	 */
	private int numero;
	
	/**
	 * precio a pagar por noche
	 */
	private double precioNoche;
	
	/**
	 * precio a pagar por mes
	 */
	private double precioMes;
	
	/**
	 * precio a pagar por semestre
	 */
	private double precioSemestre;
	
	/**
	 * capacidad disponible. 
	 * igual a capacidad total si la habitacion es privada o sino hay ningun reserva 
	 */
	private int capacidadDisponible;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public HabitacionResidencia() {		
		this.idRecinto = 0;
		this.idResidencia = 0;
		this.compartido = 0;
		this.banioCompartido = 0;
		this.numero = -1;
		this.precioNoche = -1;
		this.precioMes = -1;
		this.precioSemestre = -1;
		this.capacidadDisponible = -1;
	}

	/**
	 * Constructor con valores
	 * @param idRecinto
	 * @param idResidencia
	 * @param compartido
	 * @param banioCompartido
	 * @param numero
	 * @param precioNoche
	 * @param precioMes
	 * @param precioSemestre
	 * @param capacidadDisponible
	 */
	public HabitacionResidencia(long idRecinto, long idResidencia, int compartido, int banioCompartido,
			int numero, double precioNoche, double precioMes, double precioSemestre) {
		super();
		this.idRecinto = idRecinto;
		this.idResidencia = idResidencia;
		this.compartido = compartido;
		this.banioCompartido = banioCompartido;
		this.numero = numero;
		this.precioNoche = precioNoche;
		this.precioMes = precioMes;
		this.precioSemestre = precioSemestre;
	}

	/**
	 * @return the idRecinto
	 */
	public long getIdRecinto() {
		return idRecinto;
	}

	/**
	 * @param idRecinto the idRecinto to set
	 */
	public void setIdRecinto(long idRecinto) {
		this.idRecinto = idRecinto;
	}

	/**
	 * @return the idResidencia
	 */
	public long getIdResidencia() {
		return idResidencia;
	}

	/**
	 * @param idResidencia the idResidencia to set
	 */
	public void setIdResidencia(long idResidencia) {
		this.idResidencia = idResidencia;
	}

	/**
	 * @return the compartido
	 */
	public int getCompartido() {
		return compartido;
	}

	/**
	 * @param compartido the compartido to set
	 */
	public void setCompartido(int compartido) {
		this.compartido = compartido;
	}

	/**
	 * @return the banioCompartido
	 */
	public int getBanioCompartido() {
		return banioCompartido;
	}

	/**
	 * @param banioCompartido the banioCompartido to set
	 */
	public void setBanioCompartido(int banioCompartido) {
		this.banioCompartido = banioCompartido;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @return the precioNoche
	 */
	public double getPrecioNoche() {
		return precioNoche;
	}

	/**
	 * @param precioNoche the precioNoche to set
	 */
	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}

	/**
	 * @return the precioMes
	 */
	public double getPrecioMes() {
		return precioMes;
	}

	/**
	 * @param precioMes the precioMes to set
	 */
	public void setPrecioMes(double precioMes) {
		this.precioMes = precioMes;
	}

	/**
	 * @return the precioSemestre
	 */
	public double getPrecioSemestre() {
		return precioSemestre;
	}

	/**
	 * @param precioSemestre the precioSemestre to set
	 */
	public void setPrecioSemestre(double precioSemestre) {
		this.precioSemestre = precioSemestre;
	}



	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de una habitacion de residencia estudiantil
	 */
	public String toString() {
		return "HabitacionResidencia [idRecinto=" + idRecinto + ", idResidencia=" + idResidencia + ", compartido="
				+ compartido + ", banioCompartido=" + banioCompartido + ", numero=" + numero + ", precioNoche="
				+ precioNoche + ", precioMes=" + precioMes + ", precioSemestre=" + precioSemestre
				+ ", capacidadDisponible=" + capacidadDisponible + "]";
	}
}