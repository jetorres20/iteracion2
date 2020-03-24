package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Reserva;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Reserva de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLReserva {
	/* ****************************************************************
	 * 			Constantes // TODO cambiar PersistenciaParranderos 
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaParranderos.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaParranderos pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLReserva(PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una Reserva a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
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
	 * @return el numero de tuplas insertadas
	 */
	public long adicionarReserva(PersistenceManager pm, long id, long recintoId, long personaId, Timestamp fechaReserva, Timestamp fechaInicio, Timestamp fechaFin, int personas, double subTotal, Timestamp fechaCancelacion, double cobroAdicional, boolean activa){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSirven () + "(id, recintoId, personaId, fechaReserva, fechaInicio, fechaFin, personas, subTotal, fechaCancelacion, cobroAdicional, activa) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		// TODO cambiar dar tabla por Reserva 
		q.setParameters( id,  recintoId,  personaId,  fechaReserva,  fechaInicio,  fechaFin,  personas,  subTotal,  fechaCancelacion,  cobroAdicional,  activa);
		return (long)q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una reserva de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - el id de la reserva
	 * @return numero de tuplas eliminadas
	 */
	public long eliminarReservaPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE id = ?");
		// TODO cambiar dar tabla por Reserva 
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	
	/**
	 *  Crea y ejecuta la sentencia SQL para encontrar la información de Una Reserva alohandes por su id
	 * @param pm - El manejador de persistencia
	 * @param id - id de la reserva
	 * @return objeto reserva con el id dado
	 */
	public Reserva darReservaPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE id = ?");
		// TODO cambiar dar tabla por Reserva 
		q.setResultClass(Reserva.class);
		q.setParameters(id);
		return (Reserva) q.executeUnique();
	}
	
	
	/**
	 *  Crea y ejecuta la sentencia SQL para encontrar la información de las reservas de una persona especifica
	 * @param pm - El manejador de persistencia
	 * @param personaId
	 * @return lista de objetos reserva de la persona con el id dado
	 */
	public List<Reserva> darReservasPersona(PersistenceManager pm, long personaId){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + "WHERE personaId = ?");
		// TODO cambiar dar tabla por Reserva 
		q.setResultClass(Reserva.class);
		q.setParameters(personaId);
		return (List<Reserva>) q.executeList();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de las reservas de una recnto especifico
	 * @param pm
	 * @param recintoId
	 * @return lista de objetos reserva del recinto con el id dado
	 */
	public List<Reserva> darReservasRecinto(PersistenceManager pm, long recintoId){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + "WHERE recintoId = ?");
		// TODO cambiar dar tabla por Reserva 
		q.setResultClass(Reserva.class);
		q.setParameters(recintoId);
		return (List<Reserva>) q.executeList();
	}
	
	
	/**
	 *  Crea y ejecuta la sentencia SQL para encontrar la información de todos Las Reservas de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todas las reservas de la base de datos
	 */
	public List<Reserva> darReservas(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar ());
		// TODO cambiar dar tabla por Reserva 
		q.setResultClass(Reserva.class);
		return (List<Reserva>) q.executeList();
	}	
}
