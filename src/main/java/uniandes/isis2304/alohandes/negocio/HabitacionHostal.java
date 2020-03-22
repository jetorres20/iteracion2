package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Habitacion Hostal del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class HabitacionHostal implements VOHabitacionHostal{

	/**
	 * id unico del recinto para la habitacion de estal dentro de alohandes
	 */
	private long idHabitacion;
	
	/**
	 * id unico del operador qu es el hostal
	 */
	private long idHostal;
	
	/**
	 * true si se comparte, false si es privada
	 */
	private boolean compartida;
	
	/**
	 * true si el banio se comparte, false si es privado
	 */
	private boolean banioCompartido;
	
	/**
	 * numero de la habitacion dentro del hostal
	 */
	private int numero;
	
	/**
	 * precio por noche
	 */
	private double precioNoche;
	
	/**
	 * capacidad disponible
	 * igual a capacidad total si es privada
	 * o si no hay ninguna reserva
	 */
	private int capacidadDisponible;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public HabitacionHostal() {
		this.idHabitacion = 0;
		this.idHostal = 0;
		this.compartida = false;
		this.banioCompartido = false;
		this.numero = -1;
		this.precioNoche = -1;
		this.capacidadDisponible = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param idHabitacion
	 * @param idHostal
	 * @param compartida
	 * @param banioCompartido
	 * @param numero
	 * @param precioNoche
	 * @param capacidadDisponible
	 */
	public HabitacionHostal(long idHabitacion, long idHostal, boolean compartida, boolean banioCompartido, int numero,
			double precioNoche, int capacidadDisponible) {
		this.idHabitacion = idHabitacion;
		this.idHostal = idHostal;
		this.compartida = compartida;
		this.banioCompartido = banioCompartido;
		this.numero = numero;
		this.precioNoche = precioNoche;
		this.capacidadDisponible = capacidadDisponible;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la habitacion de hostal
	 */
	public String toString() {
		return "HabitacionHostal [idHabitacion=" + idHabitacion + ", idHostal=" + idHostal + ", compartida="
				+ compartida + ", banioCompartido=" + banioCompartido + ", numero=" + numero + ", precioNoche="
				+ precioNoche + ", capacidadDisponible=" + capacidadDisponible + "]";
	}

	/**
	 * @return the idHabitacion
	 */
	public long getIdHabitacion() {
		return idHabitacion;
	}

	/**
	 * @return the idHostal
	 */
	public long getIdHostal() {
		return idHostal;
	}

	/**
	 * @return the compartida
	 */
	public boolean isCompartida() {
		return compartida;
	}

	/**
	 * @return the banioCompartido
	 */
	public boolean isBanioCompartido() {
		return banioCompartido;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @return the precioNoche
	 */
	public double getPrecioNoche() {
		return precioNoche;
	}

	/**
	 * @return the capacidadDisponible
	 */
	public int getCapacidadDisponible() {
		return capacidadDisponible;
	}

	/**
	 * @param idHabitacion the idHabitacion to set
	 */
	public void setIdHabitacion(long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	/**
	 * @param idHostal the idHostal to set
	 */
	public void setIdHostal(long idHostal) {
		this.idHostal = idHostal;
	}

	/**
	 * @param compartida the compartida to set
	 */
	public void setCompartida(boolean compartida) {
		this.compartida = compartida;
	}

	/**
	 * @param banioCompartido the banioCompartido to set
	 */
	public void setBanioCompartido(boolean banioCompartido) {
		this.banioCompartido = banioCompartido;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @param precioNoche the precioNoche to set
	 */
	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}

	/**
	 * @param capacidadDisponible the capacidadDisponible to set
	 */
	public void setCapacidadDisponible(int capacidadDisponible) {
		this.capacidadDisponible = capacidadDisponible;
	}	
}