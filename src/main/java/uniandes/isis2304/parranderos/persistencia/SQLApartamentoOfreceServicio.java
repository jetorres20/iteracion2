package uniandes.isis2304.parranderos.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ApartamentoOfreceServicio;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto APARTAMENTOS
 * OFRECE SERVICIO
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Juan Esteban Torres
 */
 class SQLApartamentoOfreceServicio {
	


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
		public SQLApartamentoOfreceServicio (PersistenciaAlohandes pp)
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
		public long adicionarServicioaApartamento (PersistenceManager pm, long idApto,long idServicio, int precio, int incluido) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAptoOfreceServicios() + "(idServicio,idApto,precio,incluido values (?, ?, ? , ?)");
	        q.setParameters(idServicio,idApto,precio,incluido);
	        return (long) q.executeUnique();
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN SERVICIO DE UN APARTAMENTO de la base de datos de Alohandes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idApartamento - El identificador del apartamento
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarApartamentoPorId (PersistenceManager pm, long idApartamento ,long idServicio)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAptoOfreceServicios() + " WHERE idApto = ? AND idServicio=?");
	        q.setParameters(idApartamento,idServicio);
	        return (long) q.executeUnique();
		}
		

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SERVICIOS de un
		 * APARTAMENTO de la 
		 * base de datos de Alohandes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos APARTAMENTOOFRECESERVICIO
		 */
		public List<ApartamentoOfreceServicio> darApartamentoOfreceServicios (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAptoOfreceServicios());
			q.setResultClass(ApartamentoOfreceServicio.class);
			List<ApartamentoOfreceServicio> resp = (List<ApartamentoOfreceServicio>) q.execute();
			return resp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de los SERVICIOS de un
		 * APARTAMENTO dado su ID de la  
		 * base de datos de Alohandes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos APARTAMENTOOFRECESERVICIO
		 */
		public List<ApartamentoOfreceServicio> darServiciosDeUnApartamento (PersistenceManager pm, long idApartamento)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAptoOfreceServicios() + " WHERE idApto = ?");
			q.setParameters(idApartamento);
			q.setResultClass(ApartamentoOfreceServicio.class);
			List<ApartamentoOfreceServicio> resp = (List<ApartamentoOfreceServicio>) q.execute();
			return resp;
		}
		
		

}
