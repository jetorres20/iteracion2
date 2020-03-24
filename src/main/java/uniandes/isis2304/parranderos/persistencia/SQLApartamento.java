package uniandes.isis2304.parranderos.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Apartamento;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto APARTAMENTOS
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Juan Esteban Torres
 */

  public class SQLApartamento {

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
		public SQLApartamento (PersistenciaAlohandes pp)
		{
			this.pp = pp;
		}
	 
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un APARTAMENTO a la base de datos de Alohandes
		 * @param pm - El manejador de persistencia
		 * @param idApto - El identificador del recinto
		 * @param idOperario - El identificador del operario dueño del recinto
		 * @param numhabitaciones - La cantidad de habitaciones del apartamento
		 * @param precioMes - El precio a pagar por un mes del apartamento
		 * @param Direccion - La direccion del apartamento
		 * @param amoblado - Indica si el apartamento viene amoblado o no
		 * @return El número de tuplas insertadas
		 */
		public long adicionarApartamento (PersistenceManager pm, long idApto,long idOperario, int numHabitaciones, int precioMes, String direccion, int amoblado) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaApartamentos () + "(idApartamento, idOperario, numeroHabitaciones, preciomes, direccion, amoblado) values (?, ?, ?, ?, ?,?)");
	        q.setParameters(idApto,idOperario,numHabitaciones,precioMes,direccion,amoblado);
	        return (long) q.executeUnique();
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN APARTAMENTO de la base de datos de Alohandes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idApartamento - El identificador del apartamento
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarApartamentoPorId (PersistenceManager pm, long idApartamento)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaApartamentos () + " WHERE id = ?");
	        q.setParameters(idApartamento);
	        return (long) q.executeUnique();
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN apartamento de la 
		 * base de datos de Alohandes, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idBar - El identificador del apartamento
		 * @return El objeto APARTAMENTO que tiene el identificador dado
		 */
		public Apartamento darApartamentoPorId (PersistenceManager pm, long idApto) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaApartamentos () + " WHERE idApartamento = ?");
			q.setResultClass(Apartamento.class);
			q.setParameters(idApto);
			
			Apartamento creado =  (Apartamento) q.executeUnique();
			return creado;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS APARTAMENTOS de la 
		 * base de datos de Alohandes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos APARTAMENTO
		 */
		public List<Apartamento> darApartamentos (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaApartamentos ());
			q.setResultClass(Apartamento.class);
			return (List<Apartamento>) q.executeList();
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS APARTAMENTOS de la 
		 * base de datos de Alohandes
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos APARTAMENTO
		 */
		public List<Apartamento> darApartamentosConPresupuesto (PersistenceManager pm, int presupuesto)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaApartamentos () + "WHERE preciomes < ?");
			q.setParameters(presupuesto);
			q.setResultClass(Apartamento.class);
			return (List<Apartamento>) q.executeList();
		}
		
		
		
		
		
}
