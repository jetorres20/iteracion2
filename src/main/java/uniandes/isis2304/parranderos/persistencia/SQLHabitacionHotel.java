package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Apartamento;
import uniandes.isis2304.alohandes.negocio.HabitacionHotel;

public class SQLHabitacionHotel {

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
	public  SQLHabitacionHotel (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una Habitacion de Hotel a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idRecinto - El identificador del recinto
	 * @param idHotel - El identificador del operario dueño del recinto
	 * @param numero - El numero de la habitación dentro del hotel
	 * @param Tipo - El tipo de la habitación
	 * @param precioNoche - El precio de la habitacion por noche
	 * @return El número de tuplas insertadas
	 */
	public long adicionarHabitacionHotel (PersistenceManager pm, long idRecinto,long idHotel, int numero, String tipo, int precioNoche) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacionesHotel() + "(idRecinto, idHotel, numero, tipo, precioNoche) values (?, ?, ?, ?, ?,)");
        q.setParameters(idRecinto, idHotel, numero, tipo, precioNoche);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UNA HABITACION DE Hotel de la base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idApartamento - El identificador de la habitacion
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHabitacionPorId (PersistenceManager pm, long idHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionesHotel () + " WHERE idRecinto = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA habitacion de Hotel de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idBar - El identificador de la habitacion
	 * @return El objeto HABITACIONHotel que tiene el identificador dado
	 */
	public Apartamento darHabitacionHotelPorId (PersistenceManager pm, long idHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionesHotel() + " WHERE idRecinto = ?");
		q.setResultClass(HabitacionHotel.class);
		q.setParameters(idHabitacion);
		
		Apartamento creado =  (Apartamento) q.executeUnique();
		return creado;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS APARTAMENTOS de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos HABITACIONHotel
	 */
	public List<Apartamento> darHabitacionesHotel (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionesHotel ());
		q.setResultClass(HabitacionHotel.class);
		return (List<Apartamento>) q.executeList();
	}
	
	
}
