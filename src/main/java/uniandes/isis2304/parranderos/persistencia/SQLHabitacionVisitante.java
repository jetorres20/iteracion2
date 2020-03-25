package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Apartamento;
import uniandes.isis2304.alohandes.negocio.HabitacionVisitante;

public class SQLHabitacionVisitante {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public  SQLHabitacionVisitante (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una Habitacion de hostal a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idRecinto - El identificador del recinto
	 * @param idVisitante - El identificador del operario dueño del recinto
	 * @param compartida - indica si la habitacion es compartida o no
	 * @param banioCompartido - Indica si el baño de la habitacion es compartido o no
	 * @param precioNoche - El precio de la habitacion por noche
	 * @param capacidadDisponible - Indica cuantos cupos tiene disponibles la habitacion
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHabitacionVisitante (PersistenceManager pm, long idRecinto,long idOperario, String direccion, int compartido, int baniocompartido, int precioMes, int capacidadDisponible) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacionesVisitante() + "(idRecinto, idOperario, direccion, compartido, baniocompartido, precioMes, capacidadDisponible) values (?, ?, ?, ?, ?,?,?)");
        q.setParameters(idRecinto, idOperario, direccion, compartido, baniocompartido, precioMes, capacidadDisponible);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA HABITACION de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idApartamento - El identificador de la habitacion
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHabitacionPorId (PersistenceManager pm, long idHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionesVisitante () + " WHERE idRecinto = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA habitacion  de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador de la habitacion
	 * @return El objeto HABITACIONHOSTAL que tiene el identificador dado
	 */
	public Apartamento darHabitacionVisitantePorId (PersistenceManager pm, long idHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionesVisitante() + " WHERE idRecinto = ?");
		q.setResultClass(HabitacionVisitante.class);
		q.setParameters(idHabitacion);
		
		Apartamento creado =  (Apartamento) q.executeUnique();
		return creado;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS APARTAMENTOS de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos HABITACIONHOSTAL
	 */
	public List<Apartamento> darHabitacionesVisitante (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionesVisitante());
		q.setResultClass(HabitacionVisitante.class);
		return (List<Apartamento>) q.executeList();
	}
	
	/**
	 * 
	 * Crea y ejecuta la sentencia SQL para cambiar la capacidad disponibles de una habitación en la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del recinto
	 * @param ciudad - La nueva capacidad de la habitacion
	 * @return El número de tuplas modificadas
	 */
	public long cambiarCapacidadDisponibleHabitacion (PersistenceManager pm, long idHabitacion, int capacidad) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaHabitacionesVisitante() + " SET capacidadDisponible = ? WHERE idRecinto = ?");
	     q.setParameters(capacidad, idHabitacion);
	     return (long) q.executeUnique();            
	}	
	
	
}
