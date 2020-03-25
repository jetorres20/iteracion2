package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ServicioHotel;



/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ServicioHotel de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLServicioHotel {
	/* ****************************************************************
	 * 			Constantes // TODO cambiar PersistenciaAlohandes
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
	public SQLServicioHotel(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Servicio hotel a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id - el identificador del servicio
	 * @param nombre - nombre del servicio
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarServicio(PersistenceManager pm, long id, String nombre){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServiciosHotel () + "(id, nombre) values (?, ?)");
		q.setParameters(id, nombre);
		return (long)q.executeUnique();   
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un Servicio de hotel de la base de datos de Alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del menaje
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarServicioPorNombre (PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServiciosHotel () + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar Servicio Hotel de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id del Servicio de hotel
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarServicioPorId (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServiciosHotel () + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Servicios de hotel de la 
	 * base de datos de alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del servicio
	 * @return una lista de servicios que tienen el nombre dado
	 */
	public List<ServicioHotel> darServiciosHotelPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServiciosHotel () + " WHERE nombre = ?");
		q.setResultClass(ServicioHotel.class);
		q.setParameters(nombre);
		return (List<ServicioHotel>) q.executeList();		
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Un servicio de hotel de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id del servicio de hotel
	 * @return el objeto servicio que tiene el identificador dado
	 */
	public ServicioHotel darServicioHotelPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServiciosHotel () + " WHERE id = ?");
		q.setResultClass(ServicioHotel.class);
		q.setParameters(id);
		return (ServicioHotel) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Servicios de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todos los servicios de hotel en la tabla Menajes
	 */
	public List<ServicioHotel> darServicios(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServiciosHotel ());
		q.setResultClass(ServicioHotel.class);
		return (List<ServicioHotel>) q.executeList();
	}
}