package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ApartamentoOfreceServicio;
import uniandes.isis2304.alohandes.negocio.HabitacionVisitanteOfreceServicio;

 class SQLHabitacionVisitanteOfreceServicio {



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
		public SQLHabitacionVisitanteOfreceServicio(PersistenciaAlohandes pp)
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
		public long adicionarServicioaHabitacion (PersistenceManager pm, long idHabitacion,long idServicio, int precio, int incluido) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacionVisitanteOfreceServicio() + "(idHabitacion,idServicio,precio, incluido values (?, ?, ?, ?)");
	        q.setParameters(idHabitacion,idServicio,precio, incluido);
	        return (long) q.executeUnique();
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN SERVICIO DE UN Habitacion de la base de datos de Alohandes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idHabitacion - El identificador del apartamento
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarServicioHabitacionPorId (PersistenceManager pm, long idHabitacion ,long idServicio)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionVisitanteOfreceServicio() + " WHERE idHabitacion = ? AND idServicio=?");
	        q.setParameters(idHabitacion,idServicio);
	        return (long) q.executeUnique();
		}
		

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SERVICIOS de un
		 * APARTAMENTO de la 
		 * base de datos de Alohandes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos APARTAMENTOOFRECESERVICIO
		 */
		public List<HabitacionVisitanteOfreceServicio> darHabitacionVisitanteOfreceServicios (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionVisitanteOfreceServicio());
			q.setResultClass(HabitacionVisitanteOfreceServicio.class);
			List<HabitacionVisitanteOfreceServicio> resp = (List<HabitacionVisitanteOfreceServicio>) q.execute();
			return resp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SERVICIOS de un
		 * APARTAMENTO dado su ID de la  
		 * base de datos de Alohandes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos APARTAMENTOOFRECESERVICIO
		 */
		public List<HabitacionVisitanteOfreceServicio> darServiciosDeUnApartamento (PersistenceManager pm, long idHabitacion)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionVisitanteOfreceServicio() + " WHERE idHabitacion = ?");
			q.setParameters(idHabitacion);
			q.setResultClass(HabitacionVisitanteOfreceServicio.class);
			List<HabitacionVisitanteOfreceServicio> resp = (List<HabitacionVisitanteOfreceServicio>) q.execute();
			return resp;
		}
	
}
