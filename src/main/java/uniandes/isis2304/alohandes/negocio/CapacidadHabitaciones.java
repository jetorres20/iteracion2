package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

public class CapacidadHabitaciones implements VOCapacidadHabitaciones{
	
	private long idHabitacion;
	
	private Timestamp fecha;
	
	private int capacidadDisponible;
	
	
	
	
	
	public CapacidadHabitaciones(){
		
		idHabitacion=-1;
		
		fecha= new Timestamp(0);
		
		capacidadDisponible=-1;
	}
	
	
public CapacidadHabitaciones(long pIdHab, Timestamp pFecha, int pCap){
		
		idHabitacion= pIdHab;
		
		fecha= pFecha;
		
		capacidadDisponible=pCap;
	}

	public String toString(){
		
		return "CapacidadHabitaciones [idHabitacion=" + idHabitacion + ", fecha=" + fecha + ", capacidadDisponible="
				+ capacidadDisponible + "]";
	};

	/**
	 * @return the idHabitacion
	 */
	public long getIdHabitacion(){
		return idHabitacion;
	};

	/**
	 * @return the idHostal
	 */
	public Timestamp getFecha(){
		return fecha;
	};

	/**
	 * @return the compartida
	 */
	public int getCqpacidadDisponible(){
		return capacidadDisponible;
	};
	
	
	public void setIdHabitacion( long pIdHab){
		idHabitacion=pIdHab;
	}
	
	public void setFecha(Timestamp pFecha){
		fecha= pFecha;
	}
	
	public void setCapacidadDisponible(int pCap){
		capacidadDisponible=pCap;
	}
}
