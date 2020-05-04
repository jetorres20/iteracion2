package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

public interface VOReserva {

	
	@Override
	public String toString();

	/**
	 * @return the id
	 */
	public long getId();
	/**
	 * @return the recintoId
	 */
	public long getRecintoId();

	/**
	 * @return the personaId
	 */
	public long getPersonaId();

	/**
	 * @return the fechaReserva
	 */
	public Timestamp getFechaReserva();

	/**
	 * @return the fechaInicio
	 */
	public Timestamp getFechaInicio();

	/**
	 * @return the fechaFin
	 */
	public Timestamp getFechaFin();

	/**
	 * @return the personas
	 */
	public int getPersonas();

	/**
	 * @return the subTotal
	 */
	public double getSubTotal();

	/**
	 * @return the fechaCancelacion
	 */
	public Timestamp getFechaCancelacion();

	/**
	 * @return the cobroAdicional
	 */
	public double getCobroAdicional();

	/**
	 * @return the activa
	 */
	public int getActiva();
}
