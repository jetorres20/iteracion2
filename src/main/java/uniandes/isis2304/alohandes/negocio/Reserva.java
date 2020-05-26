package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;import org.hamcrest.core.IsNull;

public class Reserva implements VOReserva{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id de la reserva
	 */
	private long id;
	
	/**
	 * id del recinto asociado a la reserva
	 */
	private long recintoId;
	
	/**
	 * id de la persona que hace la reserva
	 * 
	 */
	private long personaId;
	
	/**
	 * fecha de la reserva
	 */
	private Timestamp fechaReserva;
	
	/**
	 * fecha inicio
	 */
	private Timestamp fechaInicio;
	
	/**
	 * fecha salida 
	 */
	private Timestamp fechaFin;
	
	/**
	 * numero e personas que usaran la reserva
	 */
	private int personas;
	
	/**
	 * subtotal a pagar al confirmar la reserva
	 * puede que cancele y se apliquen cargos adicionales
	 */
	private double subTotal;
	
	/**
	 * fecha de cancelacion si aplica
	 */
	private Timestamp fechaCancelacion;
	
	/**
	 * cobro adicional si se cancela la reserva
	 */
	private Double cobroAdicional;
	
	/**
	 * true si la reserva esta activa, false si paso o fue cancelada
	 */
	private int activa;
	
	/**
	 * semana de la reserva, entre 1 y 52
	 */
	private int semana;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public Reserva() {
		this.id = 0;
		this.recintoId = 0;
		this.personaId = 0;
		this.fechaReserva = new Timestamp(0);
		this.fechaInicio = new Timestamp(0);;
		this.fechaFin = new Timestamp(0);;
		this.personas = 0;
		this.subTotal = 0;
		this.fechaCancelacion = new Timestamp(0);;
		this.cobroAdicional = .0;
		this.activa = 0;
		this.semana=0;
	}
	
	/**
	 * Constructor con valores
	 * @param id
	 * @param recintoId
	 * @param personaId
	 * @param fechaReserva
	 * @param fechaInicio
	 * @param fechaFin
	 * @param personas
	 * @param subTotal
	 * @param fechaCancelacion
	 * @param cobroAdicional
	 * @param activa
	 * @param reservamensual
	 * @param reservascostosas
	 * @param reservassuites
	 */
	public Reserva(long id, long recintoId, long personaId, Timestamp fechaReserva, Timestamp fechaInicio,
			Timestamp fechaFin, int personas, double subTotal, Timestamp fechaCancelacion, Double cobroAdicional,
			int activa, int semana) {
		this.id = id;
		this.recintoId = recintoId;
		this.personaId = personaId;
		this.fechaReserva = fechaReserva;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.personas = personas;
		this.subTotal = subTotal;
		this.fechaCancelacion = fechaCancelacion;
		this.cobroAdicional = cobroAdicional;
		this.activa = activa;
		this.semana=semana;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", recintoId=" + recintoId + ", personaId=" + personaId + ", fechaReserva="
				+ fechaReserva + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", personas=" + personas
				+ ", subTotal=" + subTotal + ", fechaCancelacion=" + fechaCancelacion + ", cobroAdicional="
				+ cobroAdicional + ", activa=" + activa +", semana=" + semana+ "]";
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the recintoId
	 */
	public long getRecintoId() {
		return recintoId;
	}

	/**
	 * @return the personaId
	 */
	public long getPersonaId() {
		return personaId;
	}

	/**
	 * @return the fechaReserva
	 */
	public Timestamp getFechaReserva() {
		return fechaReserva;
	}

	/**
	 * @return the fechaInicio
	 */
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Timestamp getFechaFin() {
		return fechaFin;
	}

	/**
	 * @return the personas
	 */
	public int getPersonas() {
		return personas;
	}

	/**
	 * @return the subTotal
	 */
	public double getSubTotal() {
		return subTotal;
	}

	/**
	 * @return the fechaCancelacion
	 */
	public Timestamp getFechaCancelacion() {
		return fechaCancelacion;
	}

	/**
	 * @return the cobroAdicional
	 */
	public double getCobroAdicional() {
		return cobroAdicional;
	}

	/**
	 * @return the activa
	 */
	public int getActiva() {
		return activa;
	}
	
	/**
	 * @return the semana
	 */
	public int getSemana() {
		return semana;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param recintoId the recintoId to set
	 */
	public void setRecintoId(long recintoId) {
		this.recintoId = recintoId;
	}

	/**
	 * @param personaId the personaId to set
	 */
	public void setPersonaId(long personaId) {
		this.personaId = personaId;
	}

	/**
	 * @param fechaReserva the fechaReserva to set
	 */
	public void setFechaReserva(Timestamp fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}
	


	/**
	 * @param personas the personas to set
	 */
	public void setPersonas(int personas) {
		this.personas = personas;
	}

	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	/**
	 * @param fechaCancelacion the fechaCancelacion to set
	 */
	public void setFechaCancelacion(Timestamp fechaCancelacion) {
		if(fechaCancelacion==null)
		System.out.println("me lee fecha");
		this.fechaCancelacion = fechaCancelacion;
	}
	
	/**
	 * @param cobroAdicional the cobroAdicional to set
	 */
	public void setCobroAdicional( Double cobroAdicional) {
		System.out.println("me lee cobro");
		this.cobroAdicional = cobroAdicional;
	}


	/**
	 * @param activa the activa to set
	 */
	public void setActiva(int activa) {
		this.activa = activa;
	}	
	
	/**
	 * @param activa the semana to set
	 */
	public void setSemana(int semana) {
		this.semana = semana;
	}	
	

}