package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ApartamentoOfreceServicio;
import uniandes.isis2304.alohandes.negocio.CapacidadHabitaciones;

public class SQLCapacidadHabitaciones {
	
	
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
	public SQLCapacidadHabitaciones (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un nuevo SERVICIO ofrecido por un 
	 * APARTAMENTO a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idApto - El identificador del recinto
	 * @param idServicio - El identificador del servicio
	 * @return El número de tuplas insertadas
	 */
	public long adicionarCapacidadHabitacion (PersistenceManager pm, long idHabitacion,Timestamp fecha, int capacidadDisponible) 
	{
       Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCapacidadHabitaciones() + "(idHabitacion,fecha,capacidadDisponible values (?, ?, ?)");
       q.setParameters(idHabitacion,fecha,capacidadDisponible);
       return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN SERVICIO DE UN APARTAMENTO de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idApartamento - El identificador del apartamento
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarCapacidadHabitacion (PersistenceManager pm, long idHabitacion,Timestamp fecha)
	{
       Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCapacidadHabitaciones() + " WHERE idHabitacion = ? AND fecha=?");
       q.setParameters(idHabitacion,fecha);
       return (long) q.executeUnique();
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los SERVICIOS de un
	 * APARTAMENTO de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos APARTAMENTOOFRECESERVICIO
	 */
	public List<CapacidadHabitaciones> darCapacidadHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCapacidadHabitaciones());
		q.setResultClass(CapacidadHabitaciones.class);
		List<CapacidadHabitaciones> resp = (List<CapacidadHabitaciones>) q.execute();
		return resp;
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN SERVICIO DE UN APARTAMENTO de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idApartamento - El identificador del apartamento
	 * @return EL número de tuplas eliminadas
	 */
	public CapacidadHabitaciones darCapacidadHabitacionPorFecha (PersistenceManager pm, long idHabitacion,Timestamp fecha)
	{
       Query q = pm.newQuery(SQL, "SELECT FROM " + pp.darTablaCapacidadHabitaciones() + " WHERE idHabitacion = ? AND fecha=?");
       q.setParameters(idHabitacion,fecha);
       q.setResultClass(CapacidadHabitaciones.class);
       return (CapacidadHabitaciones) q.executeUnique();
	}
	
	

}
