package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ApartamentoOfreceServicio;
import uniandes.isis2304.alohandes.negocio.HabitacionHotelIncluyeServicio;

public class SQLHabitacionHotelIncluyeServicio {


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
		public SQLHabitacionHotelIncluyeServicio(PersistenciaAlohandes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un nuevo SERVICIO ofrecido por un 
		 * HABITACION DE HOTEL a la base de datos de Alohandes
		 * @param pm - El manejador de persistencia
		 * @param idApto - El identificador del recinto
		 * @param idServicio - El identificador del servicio
		 * @return El número de tuplas insertadas
		 */
		public long adicionarServicioahabitacion (PersistenceManager pm, long idRecinto,long idServicio) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabHoteLIncluyeServicio() + "(idServicio,idRecinto values (?, ?)");
	        q.setParameters(idServicio,idRecinto);
	        return (long) q.executeUnique();
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN SERVICIO DE UNA HABITACION de la base de datos de Alohandes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idApartamento - El identificador de la habitacion
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarServicioDeHabitacionPorId (PersistenceManager pm, long idRecinto ,long idServicio)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAptoOfreceServicios() + " WHERE idRecinto = ? AND idServicio=?");
	        q.setParameters(idRecinto,idServicio);
	        return (long) q.executeUnique();
		}
		

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SERVICIOS de un
		 * HABITACION de la base de datos de Alohandes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos HABITACIONHOTELINCLUYEERVICIO
		 */
		public List<HabitacionHotelIncluyeServicio> darHabitacionIncluyeServicios (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAptoOfreceServicios());
			q.setResultClass(HabitacionHotelIncluyeServicio.class);
			List<HabitacionHotelIncluyeServicio> resp = (List<HabitacionHotelIncluyeServicio>) q.execute();
			return resp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SERVICIOS de un
		 * APARTAMENTO dado su ID de la  
		 * base de datos de Alohandes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos APARTAMENTOOFRECESERVICIO
		 */
		public List<HabitacionHotelIncluyeServicio> darServiciosDeUnaHabitacion (PersistenceManager pm, long idHabitacion)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAptoOfreceServicios() + " WHERE idRecinto = ?");
			q.setParameters(idHabitacion);
			q.setResultClass(HabitacionHotelIncluyeServicio.class);
			List<HabitacionHotelIncluyeServicio> resp = (List<HabitacionHotelIncluyeServicio>) q.execute();
			return resp;
		}
	
}
