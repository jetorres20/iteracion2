package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import org.datanucleus.store.rdbms.sql.method.SQLBooleanMethod;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.alohandes.negocio.Apartamento;
import uniandes.isis2304.alohandes.negocio.ApartamentoOfreceServicio;
import uniandes.isis2304.alohandes.negocio.HabitacionHostal;
import uniandes.isis2304.alohandes.negocio.HabitacionHotel;
import uniandes.isis2304.alohandes.negocio.HabitacionHotelIncluyeServicio;
import uniandes.isis2304.alohandes.negocio.HabitacionVisitante;
import uniandes.isis2304.parranderos.negocio.Bar;
import uniandes.isis2304.parranderos.negocio.Bebedor;
import uniandes.isis2304.parranderos.negocio.Gustan;

public class PersistenciaAlohandes2 {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohandes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohandes2 instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	
	/**
	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
	 */
	private SQLApartamento sqlApartamento;
	
	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLApartamentoOfreceServicio sqlApartamentoOfreceServicio;
	
	/**
	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
	 */
	private SQLHabitacionHostal sqlHabitacionHostal;
	
	/**
	 * Atributo para el acceso a la tabla GUSTAN de la base de datos
	 */
	private SQLHabitacionHotel sqlHabitacionHotel;
	
	/**
	 * Atributo para el acceso a la tabla SIRVEN de la base de datos
	 */
	private SQLHabitacionHotelIncluyeServicio sqlHabitacionHotelIncluyeServicio;
	
	/**
	 * Atributo para el acceso a la tabla VISITAN de la base de datos
	 */
	private SQLHabitacionResidencia sqlHabitacionResidencia;
	
	private SQLHabitacionResidenciaTieneMenajes sqlHabitacionResidenciaTieneMenajes;
	
	private SQLHabitacionVisitante sqlHabitacionVisitante;
	
	private SQLHabitacionVisitanteOfreceServicio sqlHabitacionVisitanteOfreceServicio;
	
	private SQLHostal sqlHostal;
	
	private SQLHotel sqlHotel;
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaAlohandes2 ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Alohandes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Alohandes_sequence");
		tablas.add ("APARTAMENTO_OFRECE_SERVICIO");
		tablas.add ("APARTAMENTOS");
		tablas.add ("HAB_HOTEL_INCLUYE_SERVICIO");
		tablas.add ("HAB_VIS_OFRECE_SERVICIO");
		tablas.add ("HABI_RESIDENCIA_TIENE_MENAJES");
		tablas.add ("HABITACION_RESIDENCIA_INCLUYE");
		tablas.add ("HABITACIONES_HOSTAL");
		tablas.add ("HABITACIONES_HOTEL");
		tablas.add ("HABITACIONES_RESIDENCIA");
		tablas.add ("HABITACIONES_VISITANTE");
		tablas.add ("HOSTALES");
		tablas.add ("HOTEL_OFRECE_SERVICIO");
		tablas.add ("HOTELES");
		tablas.add ("MENAJES");
		tablas.add ("PERSONAS");
		tablas.add ("RECINTOS");
		tablas.add ("RESERVAS");
		tablas.add ("RESIDENCIA_OFRECE_SERVICIOS");
		tablas.add ("RESIDENCIAS");
		tablas.add ("SERVICIO_RESIDENCIA");
		tablas.add ("SERVICIOS_HOTEL");
		tablas.add ("SERVICIOS_PRIVADOS");
		tablas.add ("VIVIENDA_TIENE_MENAJES");
		tablas.add ("VIVIENDAS");
		tablas.add ("OPERARIOS");
		
		
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohandes2 (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaAlohandes existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes2 getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes2 ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaAlohandes existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes2 getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes2 (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlApartamento = new SQLApartamento(this);
		sqlApartamentoOfreceServicio = new SQLApartamentoOfreceServicio(this);
		sqlHabitacionHostal = new SQLHabitacionHostal(this);
		sqlHabitacionHotel = new SQLHabitacionHotel(this);
		sqlHabitacionHotelIncluyeServicio= new SQLHabitacionHotelIncluyeServicio(this);
		sqlHabitacionResidencia = new SQLHabitacionResidencia(this);
		sqlHabitacionResidenciaTieneMenajes = new SQLHabitacionResidenciaTieneMenajes(this);
		sqlHabitacionVisitante = new SQLHabitacionVisitante(this);
		sqlHabitacionVisitanteOfreceServicio=new SQLHabitacionVisitanteOfreceServicio(this);
		sqlHostal = new SQLHostal(this);
		sqlHotel = new SQLHotel(this);
		
	}
	
	
	/**
	 * Transacción para el generador de secuencia de Alohandes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Alohandes
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Apartamento adicionarApartamento(long idOperario, int numHabitaciones, int precioMes, String direccion, int amoblado , int capacidadTotal, Timestamp fechaRetiroOferta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idRecinto = nextval ();
            long tuplasInsertadasRecinto =sqlRecinto.adicionarRecinto(pm,idRecinto,capacidadTotal,null);
            long tuplasInsertadasApartamento = sqlApartamento.adicionarApartamento(pm, idRecinto, idOperario, numHabitaciones, precioMes, direccion, amoblado);
            tx.commit();
            
            log.trace ("Inserción de apartamento : " + idRecinto + ": " + tuplasInsertadasApartamento + " tuplas insertadas");
            boolean bAmoblado = amoblado==1;
         
            return new Apartamento(idRecinto, idOperario, numHabitaciones, precioMes, direccion, bAmoblado);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarApartamentoPorId (long idApto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlApartamento.eliminarApartamentoPorId(pm, idApto);
            sqlRecinto.eliminarRecintoPorId(idApto);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public Apartamento darApartamentoPorId (long idApto) 
	{
		return (Apartamento) sqlApartamento.darApartamentoPorId(pmf.getPersistenceManager(), idApto) ;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<Apartamento> darApartamentos ()
	{
		return sqlApartamento.darApartamentos (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación APARTAMENTOOFRECESERVICIOS
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla GUSTAN
	 * Adiciona entradas al log de la aplicación
	 * @param idBebedor - El identificador del bebedor - Debe haber un bebedor con ese identificador
	 * @param idBebida - El identificador de la bebida - Debe haber una bebida con ese identificador
	 * @return Un objeto GUSTAN con la información dada. Null si ocurre alguna Excepción
	 */
	public ApartamentoOfreceServicio adicionaAdicionarServicioaApartamento (long idApto,long idServicio, int precio, int incluido)  
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlApartamentoOfreceServicio.adicionarServicioaApartamento(pm, idApto, idServicio, precio, incluido);
            tx.commit();

            log.trace ("Inserción de ApartamentoOfreceServicio: [" + idApto + ", " + idServicio + "]. " + tuplasInsertadas + " tuplas insertadas");

            if(incluido==1)
            return new ApartamentoOfreceServicio(idApto,idServicio, precio, true);
            else
            return new ApartamentoOfreceServicio(idApto,idServicio, precio, false);	
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla GUSTAN, dados los identificadores de bebedor y bebida
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioDeApartamento(long idApto, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlApartamentoOfreceServicio.eliminarApartamentoPorId(pm, idApto, idServicio);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<ApartamentoOfreceServicio> darApartamentosOfrecenServicios ()
	{
		return sqlApartamentoOfreceServicio.darApartamentoOfreceServicios (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACIONHOSTAL
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionHostal adicionarHabitacionHostal(long idHostal, int compartida, int baniocompartido, int numero, int precioNoche, int capacidadDisponible, int capacidadTotal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idRecinto = nextval ();
            long tuplasInsertadasRecinto =sqlRecinto.adicionarRecinto(pm,idRecinto,capacidadTotal,null);
            long tuplasInsertadasHabitacionhostal = sqlHabitacionHostal.adicionarHabitacionHostal(pm, idRecinto, idHostal, numero, compartida, baniocompartido, precioNoche, capacidadDisponible);
            tx.commit();
            
            boolean bCompartido= compartida==1;
            boolean bBanio = baniocompartido==1;
            log.trace ("Inserción de apartamento : " + idRecinto + ": " + tuplasInsertadasHabitacionhostal + " tuplas insertadas");
            
            return new HabitacionHostal(idRecinto, idHostal, bBanio, bCompartido, numero, precioNoche, capacidadDisponible);
            	
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionHostalporId (long idHostal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHostal.eliminarHabitacionPorId(pm, idHostal);
            sqlRecinto.eliminarRecintoPorId(idHostal);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	/**
	 * Método que actualiza, de manera transaccional, la capacidad disponible
	 * @param idBebedor - El identificador del bebedor
	 * @param ciudad - La nueva ciudad del bebedor
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarCapacidadDisponibleHabitacionHostal (long idHabitacion, int capacidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHostal.cambiarCapacidadDisponibleHabitacion(pm, idHabitacion, capacidad);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionHostal darHabitacionHostalPorId (long habitacionHostal) 
	{
		return (HabitacionHostal) sqlHabitacionHostal.darHabitacionHostalPorId(pmf.getPersistenceManager(), habitacionHostal) ;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionHostal> darHabitacionesHostal ()
	{
		return sqlHabitacionHostal.darHabitacionesHostal (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACIONHOTEL
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionHotel adicionarHabitacionHotel(long idHotel, int numero, String tipo, int precioNoche, int capacidadTotal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idRecinto = nextval ();
            long tuplasInsertadasRecinto =sqlRecinto.adicionarRecinto(pm,idRecinto,capacidadTotal,null);
            long tuplasInsertadasHabitacionhostal = sqlHabitacionHotel.adicionarHabitacionHotel(pm, idRecinto, idHotel, numero, tipo, precioNoche);
            tx.commit();
         
            log.trace ("Inserción de habitacion hotel : " + idRecinto + ": " + tuplasInsertadasHabitacionhostal + " tuplas insertadas");
            
            return new HabitacionHotel(idRecinto, idHotel, numero, tipo, precioNoche);
            	
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionHotelporId (long idHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHotel.eliminarHabitacionPorId(pm, idHotel);
            sqlRecinto.eliminarRecintoPorId(idHotel);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionHotel darHabitacionHotelPorId (long idHabitacion) 
	{
		return (HabitacionHotel) sqlHabitacionHotel.darHabitacionHotelPorId(pmf.getPersistenceManager(), idHabitacion) ;
	}
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionHotel> darHabitacionesHotel ()
	{
		return sqlHabitacionHotel.darHabitacionesHotel (pmf.getPersistenceManager());
	}
	
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACION HOTEL OFRECE SERVICIOS
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla GUSTAN
	 * Adiciona entradas al log de la aplicación
	 * @param idBebedor - El identificador del bebedor - Debe haber un bebedor con ese identificador
	 * @param idBebida - El identificador de la bebida - Debe haber una bebida con ese identificador
	 * @return Un objeto GUSTAN con la información dada. Null si ocurre alguna Excepción
	 */
	public HabitacionHotelIncluyeServicio adicionaAdicionarServicioAHabitacionHotel(long idHabitacion,long idServicio )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHabitacionHotelIncluyeServicio.adicionarServicioahabitacion(pm, idHabitacion, idServicio);
            tx.commit();

            log.trace ("Inserción de ApartamentoOfreceServicio: [" + idHabitacion + ", " + idServicio + "]. " + tuplasInsertadas + " tuplas insertadas");

           return new HabitacionHotelIncluyeServicio(idHabitacion, idServicio)	;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla GUSTAN, dados los identificadores de bebedor y bebida
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioDeHabitacionHotel(long idHabitacion, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHotelIncluyeServicio.eliminarServicioDeHabitacionPorId(pm, idHabitacion, idServicio);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<HabitacionHotelIncluyeServicio> darHabitacionHotelIncluyeServicios ()
	{
		return sqlHabitacionHotelIncluyeServicio.darHabitacionIncluyeServicios (pmf.getPersistenceManager());
	}
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<HabitacionHotelIncluyeServicio> darServiciosDeUnaHabitacionHotel (long idHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		
		return sqlHabitacionHotelIncluyeServicio.darServiciosDeUnaHabitacion(pm, idHabitacion);
	}
	
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACION VISITANTE
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionVisitante adicionarHabitacionVisitante(long idOperario, String direccion, int compartido, int baniocompartido, int numero, int precioMes, int capacidadDisponible, int capacidadTotal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idRecinto = nextval ();
            long tuplasInsertadasRecinto =sqlRecinto.adicionarRecinto(pm,idRecinto,capacidadTotal,null);
            long tuplasInsertadasHabitacionhostal = sqlHabitacionVisitante.adicionarHabitacionVisitante(pm, idRecinto, idOperario, direccion, compartido, baniocompartido, precioMes, capacidadDisponible);
            tx.commit();
            
            boolean bCompartido= compartido==1;
            boolean bBanio = baniocompartido==1;
            log.trace ("Inserción de apartamento : " + idRecinto + ": " + tuplasInsertadasHabitacionhostal + " tuplas insertadas");
            
            return new HabitacionVisitante(idRecinto, idOperario, direccion, bCompartido, bCompartido, precioMes, capacidadDisponible);
            	
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionVisitanteporId (long idHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionVisitante.eliminarHabitacionPorId(pm, idHabitacion);
            sqlRecinto.eliminarRecintoPorId(idHabitacion);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionVisitante darHabitacionVisitantePorId (long idHabitacion) 
	{
		return (HabitacionVisitante) sqlHabitacionVisitante.darHabitacionVisitantePorId(pm, idHabitacion);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionVisitante> darHabitacionesVisitante ()
	{
		return sqlHabitacionVisitante.darHabitacionesVisitante (pmf.getPersistenceManager());
	}
}
