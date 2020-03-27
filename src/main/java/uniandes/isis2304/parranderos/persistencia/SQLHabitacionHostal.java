package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Apartamento;
import uniandes.isis2304.alohandes.negocio.HabitacionHostal;

 class SQLHabitacionHostal {

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
	public  SQLHabitacionHostal (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una Habitacion de visitante a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idRecinto - El identificador del recinto
	 * @param idHostal - El identificador del operario dueño del recinto
	 * @param compartida - indica si la habitacion es compartida o no
	 * @param banioCompartido - Indica si el baño de la habitacion es compartido o no
	 * @param precioNoche - El precio de la habitacion por noche
	 * @param capacidadDisponible - Indica cuantos cupos tiene disponibles la habitacion
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHabitacionHostal (PersistenceManager pm, long idRecinto,long idHostal, int  numero, int compartida, int baniocompartido, int precioNoche, int capacidadDisponible) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablahabitacionesHostal() + "(idRecinto, idHostal, compartida, baniocompartido, numero,  precioNoche, capacidadDisponible) values (?, ?, ?, ?, ?,?, ? , ?)");
        q.setParameters(idRecinto, idHostal, compartida, baniocompartido,numero, precioNoche, capacidadDisponible);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA HABITACION DE HOSTAL de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idApartamento - El identificador de la habitacion
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHabitacionPorId (PersistenceManager pm, long idHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablahabitacionesHostal () + " WHERE idRecinto = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA habitacion de visitante de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador de la habitacion
	 * @return El objeto HABITACIONHOSTAL que tiene el identificador dado
	 */
	public HabitacionHostal darHabitacionHostalPorId (PersistenceManager pm, long idHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablahabitacionesHostal() + " WHERE idRecinto = ?");
		q.setResultClass(HabitacionHostal.class);
		q.setParameters(idHabitacion);
		
		HabitacionHostal creado =  (HabitacionHostal) q.executeUnique();
		return creado;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS HABITACIONES HOSTAL de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos HABITACIONHOSTAL
	 */
	public List<HabitacionHostal> darHabitacionesHostal (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablahabitacionesHostal());
		q.setResultClass(HabitacionHostal.class);
		return (List<HabitacionHostal>) q.executeList();
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
		 Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablahabitacionesHostal() + " SET capacidadDisponible = ? WHERE idRecinto = ?");
	     q.setParameters(capacidad, idHabitacion);
	     return (long) q.executeUnique();            
	}
	
	
}
