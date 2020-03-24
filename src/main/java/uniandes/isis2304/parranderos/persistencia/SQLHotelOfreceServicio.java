package uniandes.isis2304.parranderos.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.alohandes.negocio.HotelOfreceServicio;;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto HotelOfreceServicio de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLHotelOfreceServicio {
	/* ****************************************************************
	 * 			Constantes // TODO cambiar por PersistenciaAlohandes
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
	public SQLHotelOfreceServicio(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un HotelOfreceServicio a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idHotel - el identificador del hotel
	 * @param idServicio - il identificador del servicio de hotel
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarHotelOfreceServicio(PersistenceManager pm, long idHotel, long idServicio){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSirven () + "(idHotel, idServicio) values (?, ?)");
		// TODO cambiar dar tabla sirven por darTablaHotelOfreceServicio 
		q.setParameters(idHotel, idServicio);
		return (long)q.executeUnique();   
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un HotelOfreceServicio de la base de datos de Alohandes, por sus identificadores
	 * @param idHotel
	 * @param idServicio
	 * @return  numero tuplas eliminadas
	 */
	public long eliminarHotelOfreceServicio(PersistenceManager pm, long idHotel, long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSirven () + " WHERE idHotel = ? AND idServicio = ?");
		// TODO cambiar dar tabla sirven por darTablaHotelOfreceServicio 
		q.setParameters(idHotel, idServicio);
		return (long) q.executeUnique();   
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los HotelOfreceServicio de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos HotelOfreceServicio
	 */
	public List<HotelOfreceServicio> darHotelesOfreceServicio(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSirven ());
		// TODO cambiar dar tabla sirven por darTablaHotelOfreceServicio 
		q.setResultClass(HotelOfreceServicio.class);
		return (List<HotelOfreceServicio>) q.execute();
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar el identificador y el número de servicio que ofrecen los hoteles de la 
	 * base de datos de alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un Hotel,
	 * 	el segundo elemento representa el número de servicios que ofrece
	 */
	public List<Object []> darHotelesYCantidadServicios (PersistenceManager pm)
	{
		String sql = "SELECT idHotel, count (*) as numServicios";
		sql += " FROM " + pp.darTablaSirven ();
		// TODO cambiar dar tabla sirven por darTablaHotelOfreceServicio 
		sql	+= " GROUP BY idHotel";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar el identificador y el número de hoteles en los que se ofrece un servicio 
	 * base de datos de alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un Servicio,
	 * 	el segundo elemento representa el número de hoteles en los que se ofrece
	 */
	public List<Object []> darServiciosYCantidadHoteles (PersistenceManager pm)
	{
		String sql = "SELECT idServicio, count (*) as numHoteles";
		sql += " FROM " + pp.darTablaSirven ();
		// TODO cambiar dar tabla sirven por darTablaHotelOfreceServicio 
		sql	+= " GROUP BY idServicio";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
}