package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Habitacion visitante del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class HabitacionVisitante implements VOHabitacionVisitante{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico del frecinto que es la habitacion visitante
	 */
	private long idHabitacion;
	
	/**
	 * id de la persona que opera el apartamento
	 */
	private long idOperario;
	
	/**
	 * direccion de la vivienda
	 */
	private String direccion;
	
	/**
	 * true si es compartida, false si es privada
	 */
	private boolean compartido;
	
	/**
	 * true si es compartido, false si es privado
	 */
	private boolean banioCompartido;
	
	/**
	 * precio del alquiler mensual
	 */
	private double precioMes;
	
	/**
	 * capacidad disponible en la habitacion
	 * tiene sentido solo si es copartida
	 * si esta reservada y es privada deberia ser 0 
	 */
	private int capacidadDisponible;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public HabitacionVisitante() {
		this.idHabitacion = 0;
		this.idOperario = 0;
		this.direccion = "";
		this.compartido = false;
		this.banioCompartido = false;
		this.precioMes = 0;
		this.capacidadDisponible = 0;
	}
	
	/** Constructor con valores
	 * @param idHabitacion
	 * @param idOperario
	 * @param direccion
	 * @param compartido
	 * @param banioCompartido
	 * @param precioMes
	 * @param capacidadDisponible
	 */
	public HabitacionVisitante(long idHabitacion, long idOperario, String direccion, boolean compartido,
			boolean banioCompartido, double precioMes, int capacidadDisponible) {
		this.idHabitacion = idHabitacion;
		this.idOperario = idOperario;
		this.direccion = direccion;
		this.compartido = compartido;
		this.banioCompartido = banioCompartido;
		this.precioMes = precioMes;
		this.capacidadDisponible = capacidadDisponible;
	}

	@Override
	public String toString() {
		return "HabitacionVisitante [idHabitacion=" + idHabitacion + ", idOperario=" + idOperario + ", direccion="
				+ direccion + ", compartido=" + compartido + ", banioCompartido=" + banioCompartido + ", precioMes="
				+ precioMes + ", capacidadDisponible=" + capacidadDisponible + "]";
	}

	/**
	 * @return the idHabitacion
	 */
	public long getIdHabitacion() {
		return idHabitacion;
	}

	/**
	 * @return the idOperario
	 */
	public long getIdOperario() {
		return idOperario;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return the compartido
	 */
	public boolean isCompartido() {
		return compartido;
	}

	/**
	 * @return the banioCompartido
	 */
	public boolean isBanioCompartido() {
		return banioCompartido;
	}

	/**
	 * @return the precioMes
	 */
	public double getPrecioMes() {
		return precioMes;
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
	 * @param idOperario the idOperario to set
	 */
	public void setIdOperario(long idOperario) {
		this.idOperario = idOperario;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @param compartido the compartido to set
	 */
	public void setCompartido(boolean compartido) {
		this.compartido = compartido;
	}

	/**
	 * @param banioCompartido the banioCompartido to set
	 */
	public void setBanioCompartido(boolean banioCompartido) {
		this.banioCompartido = banioCompartido;
	}

	/**
	 * @param precioMes the precioMes to set
	 */
	public void setPrecioMes(double precioMes) {
		this.precioMes = precioMes;
	}

	/**
	 * @param capacidadDisponible the capacidadDisponible to set
	 */
	public void setCapacidadDisponible(int capacidadDisponible) {
		this.capacidadDisponible = capacidadDisponible;
	}	
}