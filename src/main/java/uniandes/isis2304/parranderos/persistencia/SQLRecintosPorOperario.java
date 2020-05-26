package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.HotelOfreceServicio;
import uniandes.isis2304.alohandes.negocio.RecintosPorOperario;

public class SQLRecintosPorOperario {
	
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
	public SQLRecintosPorOperario(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}

	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un HotelOfreceServicio a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idHotel - el identificador del hotel
	 * @param idServicio - il identificador del servicio de hotel
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarRecintoaOperario(PersistenceManager pm, long idRecinto, long idOperario){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRecintosPorOperario() + "(idOperario, idRecinto) values (?, ?)");		
		q.setParameters(idOperario, idRecinto);
		return (long)q.executeUnique();   
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un HotelOfreceServicio de la base de datos de Alohandes, por sus identificadores
	 * @param idHotel
	 * @param idServicio
	 * @return  numero tuplas eliminadas
	 */
	public long eliminarRecintoaOperario(PersistenceManager pm, long idRecinto){
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRecintosPorOperario() + " WHERE idRecinto =?");
		q.setParameters(idRecinto);
		return (long) q.executeUnique();   
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todas las tuplas HotelOfreceServicio de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos HotelOfreceServicio
	 */
	public List<RecintosPorOperario> darRecintosPorOperario(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRecintosPorOperario());
		q.setResultClass(RecintosPorOperario.class);
		return (List<RecintosPorOperario>) q.executeList();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todas las tuplas HotelOfreceServicio de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos HotelOfreceServicio
	 */
	public List<RecintosPorOperario> darRecintosPorIdOperario(PersistenceManager pm,long idOperario){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRecintosPorOperario() + " WHERE idOperario =?");
		q.setParameters(idOperario);
		q.setResultClass(RecintosPorOperario.class);
		return (List<RecintosPorOperario>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todas las tuplas HotelOfreceServicio de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos HotelOfreceServicio
	 */
	public RecintosPorOperario darOperarioDeRecinto(PersistenceManager pm,long idRecinto){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRecintosPorOperario() + " WHERE idRecinto =?");
		q.setParameters(idRecinto);
		q.setResultClass(RecintosPorOperario.class);
		return (RecintosPorOperario) q.executeUnique();
	}
	
	

	
	

}
