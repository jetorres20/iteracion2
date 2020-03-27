package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.alohandes.negocio.Hotel;

 class SQLHotel {
	
	/* ****************************************************************
	 * 			Constantes // 
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
	public SQLHotel(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	// 
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Hotel a la base de datos de Alohandes
	 * @param pm
	 * @param idHotel
	 * @param idOperario
	 * @param numeroHabitaciones
	 * @param precioNoche
	 * @param precioSeguro
	 * @param direccion
	 * @param diasUtilizadaAñoActual
	 * @return numero de tuplas insertadas
	 */
	public long adicionarHotel(PersistenceManager pm, long idOperario, int nit, String nombre, int estrellas,String direccion, int telefono){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHoteles () + "( idOperario, nit, nombre, estrellas, direccion, telefono) values (?, ?, ?, ?, ?, ?)");
	
		q.setParameters( idOperario, nit, nombre, estrellas, direccion, telefono);
		return (long)q.executeUnique();
	}
	
	

	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una Hotel de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la Hotel
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHotelPorId (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHoteles() + " WHERE idOperario = ?");
		
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una Hotel de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la Hotel
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHotelPorNit (PersistenceManager pm, int nit)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHoteles() + " WHERE nit = ?");
		
		q.setParameters(nit);
		return (long) q.executeUnique();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNa Hotel de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la Hotel
	 * @return el objeto Hotel que tiene el identificador dado
	 */
	public Hotel darHotelPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHoteles () + " WHERE idOperario = ?");
		
		q.setResultClass(Hotel.class);
		q.setParameters(id);
		return (Hotel) q.executeUnique();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNa Hotel de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la Hotel
	 * @return el objeto Hotel que tiene el identificador dado
	 */
	public Hotel darHotelPorNit(PersistenceManager pm, int nit){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHoteles () + " WHERE nit = ?");
		
		q.setResultClass(Hotel.class);
		q.setParameters(nit);
		return (Hotel) q.executeUnique();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Las Hotels de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todos las Hotels en alohandes
	 */
	public List<Hotel> darHoteles(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHoteles ());

		q.setResultClass(Hotel.class);
		return (List<Hotel>) q.executeList();
	}
	


}
