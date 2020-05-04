/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.persistencia.PersistenciaAlohandes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Germán Bravo
 */
public class Alohandes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(Alohandes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaAlohandes pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public Alohandes ()
	{
		pp = PersistenciaAlohandes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public Alohandes (JsonObject tableConfig)
	{
		pp = PersistenciaAlohandes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE BEBIDA
	 *****************************************************************/
	
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Apartamento adicionarApartamento(long idOperario, int numHabitaciones, int precioMes, String direccion, int amoblado , int capacidadTotal, Timestamp fechaRetiroOferta)
	{
		Apartamento apto = pp.adicionarApartamento(idOperario, numHabitaciones, precioMes, direccion, amoblado, capacidadTotal, fechaRetiroOferta);
		return apto;
	}
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarApartamentoPorId (long idApto) 
	{
		long tuplas = pp.eliminarApartamentoPorId(idApto);
		return tuplas;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public Apartamento darApartamentoPorId (long idApto) 
	{
		return pp.darApartamentoPorId(idApto);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<Apartamento> darApartamentos ()
	{
		return pp.darApartamentos();
	}
	
	
	public List<VOApartamento> darVOApartamentos ()
	{
		List<VOApartamento> voAptos = new LinkedList<VOApartamento> ();
		for(Apartamento apto: pp.darApartamentos())
			voAptos.add(apto);

		return voAptos;
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
		
		return pp.adicionaAdicionarServicioaApartamento(idApto, idServicio, precio, incluido);
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla GUSTAN, dados los identificadores de bebedor y bebida
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioDeApartamento(long idApto, long idServicio) 
	{
		return pp.eliminarServicioDeApartamento(idApto, idServicio);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<ApartamentoOfreceServicio> darApartamentosOfrecenServicios ()
	{
		return pp.darApartamentosOfrecenServicios();
	}
	
	public List<VOApartamentoOfreceServicio> darVOApartamentosOfrecenServicios ()
	{
		List<VOApartamentoOfreceServicio> voServiciosAptos = new LinkedList<VOApartamentoOfreceServicio>();
		for(VOApartamentoOfreceServicio servi : pp.darApartamentosOfrecenServicios())
			voServiciosAptos.add(servi);
		
		return voServiciosAptos;
	}
	
	
	
	
	/* ***********************************************************
	 *  
	 *   
	 *    *****
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
		return pp.adicionarHabitacionHostal(idHostal, compartida, baniocompartido, numero,
				precioNoche, capacidadDisponible, capacidadTotal);
			
	}
	

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionHostalporId (long idHostal) 
	{
		return pp.eliminarHabitacionHostalporId(idHostal);
	}
	
	
	/**
	 * Método que actualiza, de manera transaccional, la capacidad disponible
	 * @param idBebedor - El identificador del bebedor
	 * @param ciudad - La nueva ciudad del bebedor
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarCapacidadDisponibleHabitacionHostal (long idHabitacion, int capacidad)
	{
		return pp.cambiarCapacidadDisponibleHabitacionHostal(idHabitacion, capacidad);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionHostal darHabitacionHostalPorId (long habitacionHostal) 
	{
		return pp.darHabitacionHostalPorId(habitacionHostal);
	}
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionHostal> darHabitacionesHostal ()
	{
		return pp.darHabitacionesHostal();
	}
	
	public List<VOHabitacionHostal> darVOHabitacionesHostal ()
	{
		List<VOHabitacionHostal> habitaciones = new LinkedList<>();
		for(VOHabitacionHostal habi : pp.darHabitacionesHostal())
			habitaciones.add(habi);
		
		return habitaciones;
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
		return pp.adicionarHabitacionHotel(idHotel, numero, tipo, precioNoche, capacidadTotal);
	}
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionHotelporId (long idHotel) 
	{
		return pp.eliminarHabitacionHotelporId(idHotel);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionHotel darHabitacionHotelPorId (long idHabitacion) 
	{
		return pp.darHabitacionHotelPorId(idHabitacion);
	}
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionHotel> darHabitacionesHotel ()
	{
		return pp.darHabitacionesHotel();
	}
	
	public List<VOHabitacionHotel> darVOHabitacionesHotel ()
	{
		List<VOHabitacionHotel> habitaciones = new LinkedList<>();
		for(VOHabitacionHotel habi : pp.darHabitacionesHotel())
			habitaciones.add(habi);
		
		return habitaciones;
			
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
		return pp.adicionaAdicionarServicioAHabitacionHotel(idHabitacion, idServicio);
	}
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla GUSTAN, dados los identificadores de bebedor y bebida
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioDeHabitacionHotel(long idHabitacion, long idServicio) 
	{
		return pp.eliminarServicioDeHabitacionHotel(idHabitacion, idServicio);
	}
	
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<HabitacionHotelIncluyeServicio> darHabitacionHotelIncluyeServicios ()
	{
		return pp.darHabitacionHotelIncluyeServicios ();
	}
	
	public List<VOHabitacionHotelIncluyeServicio> darVOHabitacionHotelIncluyeServicios ()
	{
		
		List<VOHabitacionHotelIncluyeServicio> servicios = new LinkedList<>();
		
		for( VOHabitacionHotelIncluyeServicio servi: pp.darHabitacionHotelIncluyeServicios ())
			servicios.add(servi);
		
		return servicios;
	}
	
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<HabitacionHotelIncluyeServicio> darServiciosDeUnaHabitacionHotel (long idHabitacion){
		
		return pp.darServiciosDeUnaHabitacionHotel( idHabitacion);
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACION RESIDENCIA
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionResidencia adicionarHabitacionResidencia(long idResidencia, int compartido, int baniocompartido, int numero, int precioNoche, int precioMes, int precioSemestre, int capacidadDisponible, int capacidadTotal)
	{
		return pp.adicionarHabitacionResidencia(idResidencia, compartido, baniocompartido, numero, precioNoche, precioMes, precioSemestre, capacidadTotal)
	;
	}
	

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionResidenciaporId (long idHabitacion) 
	{
		return pp.eliminarHabitacionResidenciaporId(idHabitacion);
	}
	
	
	/**
	 * Método que actualiza, de manera transaccional, la capacidad disponible
	 * @param idBebedor - El identificador del bebedor
	 * @param ciudad - La nueva ciudad del bebedor
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarCapacidadDisponibleHabitacionResidencia (long idHabitacion, int capacidad)
	{
		return pp.cambiarCapacidadDisponibleHabitacionResidencia(idHabitacion, capacidad);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionResidencia darHabitacionResidenciaPorId (long idHabitacion) 
	{
		return	pp.darHabitacionResidenciaPorId(idHabitacion);
	
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionResidencia> darHabitacionesResidencia ()
	{
		return pp.darHabitacionesResidencia();
	}
	
	
	public List<VOHabitacionResidencia> darVOHabitacionesResidencia ()
	{
		List<VOHabitacionResidencia> habitaciones = new LinkedList<>();
		
		for( VOHabitacionResidencia habi: pp.darHabitacionesResidencia())
			habitaciones.add(habi);
		return habitaciones;
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
		return pp.adicionarHabitacionVisitante(idOperario, direccion, compartido, baniocompartido, numero, precioMes, capacidadDisponible, capacidadTotal);
	}
	

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionVisitanteporId (long idHabitacion) 
	{
		return pp.eliminarHabitacionVisitanteporId(idHabitacion);
	}
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionVisitante darHabitacionVisitantePorId (long idHabitacion) 
	{
		return pp.darHabitacionVisitantePorId(idHabitacion);
	
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionVisitante> darHabitacionesVisitante ()
	{
		return pp.darHabitacionesVisitante();
	}
	
	public List<VOHabitacionVisitante> darVOHabitacionesVisitante ()
	{
		List<VOHabitacionVisitante> habitaciones = new LinkedList<>();
		for(VOHabitacionVisitante habi : pp.darHabitacionesVisitante())
			habitaciones.add(habi);
		return habitaciones;
	}
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACION VISITANTE OFRECE SERVICIOS
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla GUSTAN
	 * Adiciona entradas al log de la aplicación
	 * @param idBebedor - El identificador del bebedor - Debe haber un bebedor con ese identificador
	 * @param idBebida - El identificador de la bebida - Debe haber una bebida con ese identificador
	 * @return Un objeto GUSTAN con la información dada. Null si ocurre alguna Excepción
	 */
	public HabitacionVisitanteOfreceServicio adicionaAdicionarServicioAHabitacionVisitante(long idHabitacion,long idServicio, int precio, int incluido )
	{
		return pp.adicionaAdicionarServicioAHabitacionVisitante(idHabitacion, idServicio, precio, incluido);
	}
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla GUSTAN, dados los identificadores de bebedor y bebida
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioDeHabitacionVisitante(long idHabitacion, long idServicio) 
	{
		return pp.eliminarServicioDeHabitacionVisitante(idHabitacion, idServicio);
	}
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<HabitacionVisitanteOfreceServicio> darHabitacioVisitanteOfreceServicios ()
	{
		return pp.darHabitacioVisitanteOfreceServicios();
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Hostales
	 *****************************************************************/
	public Residencia adicionarHostal( int nit, String nombre, int horaAbre, int horaCierra, String direccion, int telefono)
	{
		return pp.adicionarHostal(  nit,  nombre,  horaAbre,  horaCierra,  direccion,  telefono)
				;
	}
	
	
	public long eliminarHostalPorID(long idOperario) 
	{
		return pp.eliminarHostalPorID(idOperario);
	}
        
     public long eliminarHostalPorNIT(int nit) 
    {
    		return pp.eliminarHostalPorNIT(nit);
        
	}
        
        
	public Hostal darHostalPorId(long id){
		return pp.darHostalPorId(id);
	}
	
	public Hostal darHostalPorNit(int nit){
		return pp.darHostalPorNit(nit);
	}
	
	public List<Hostal> darHostales(){
		return pp.darHostales();
	}
	
	public List<VOHostal> darVOHostales(){
		List<VOHostal> hostales = new LinkedList<>();
		for(VOHostal hostal :
		pp.darHostales())
			hostales.add(hostal);
		return hostales;
	}
	
	
	
	

	/* ****************************************************************
	 * 			Métodos para manejar los Hoteles
	 *****************************************************************/
	public Residencia adicionarHotel(int nit, String nombre, int estrellas, String direccion, int telefono)
	{
		return pp.adicionarHotel(nit, nombre, estrellas, direccion, telefono);
		
	}
	
	
	public long eliminarHotelPorID(long idOperario) 
	{
		return pp.eliminarHotelPorID(idOperario);
        
	}
        
        
	public Hotel darHotelPorId(long id){
		return pp.darHotelPorId(id);
	}
	
	public Hotel darHotelPorNit(int nit){
		return pp.darHotelPorNit(nit);
	}
	
	public List<Hotel> darHoteles(){
		return pp.darHoteles();
	}

	public List<VOHotel> darVOHoteles(){
		List<VOHotel> hoteles = new LinkedList<>();
		for(VOHotel hotel :
		pp.darHoteles())
			hoteles.add(hotel);
		return hoteles;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Operario
	 *****************************************************************/
	
	public List<Operario> darOperarios ()
	{
		return pp.darOperarios();
	}
	
	public List<VOOperario> darVOOperarios(){
		List<VOOperario> operarios = new LinkedList<>();
		for(VOOperario op :
		pp.darOperarios())
			operarios.add(op);
		return operarios;
	}
	
	
	public Operario darOperarioPorId (long id)
	{
		return pp.darOperarioPorId(id);
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Persona
	 *****************************************************************/
	public Persona adicionarPersona(int cedula, String nombre, String apellido, int telefono, String correo, int vinculo)
	{
		return pp.adicionarPersona(cedula, nombre, apellido, telefono, correo, vinculo);
		
	}
	
	
	public long eliminarPersonaPorId(long idOperario) 
	{
		return pp.eliminarPersonaPorId(idOperario);
        
	}
        
        
	public Hotel darPersonaPorId(long id){
		return pp.darHotelPorId(id);
	}
		
	
	public List<Persona> darPersonas(){
		return pp.darPersonas();
	}

	public List<VOPersona> darVOPersona(){
		List<VOPersona> personas = new LinkedList<>();
		for(VOPersona p :
		pp.darPersonas())
			personas.add(p);
		return personas;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Recinto
	 *****************************************************************/
	public long eliminarRecintoPorID(long id) 
	{
		return pp.eliminarRecinto(id);
        
	}
        
        
	public Recinto darRecintoPorId(long id){
		return pp.darRecintoPorId(id);
	}
		
	
	public List<Recinto> darRecintos(){
		return pp.darRecintos();
	}

	public List<VORecinto> darVORecintos(){
		List<VORecinto> recintos = new LinkedList<>();
		for(VORecinto r :
		pp.darRecintos())
			recintos.add(r);
		return recintos;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Reserva
	 *****************************************************************/
	public Reserva adicionarReserva(long recintoId, long personaId, Timestamp fechaInicio, Timestamp fechaFin, int personas)
	{
		//TODO quitar subtotal aqui y en persistencia
		return pp.adicionarReserva(recintoId, personaId, fechaInicio, fechaFin, personas);
		
	}
	
	public void cancelarReserva(long id) 
	{
		pp.cancelarReserva(id);
		
	}
        
        
	public Reserva darReservaPorId(long id){
		return pp.darReservaPorId(id);
	}
		
	
	public List<Reserva> darReservas(){
		return pp.darReservas();
	}

	public List<VOReserva> darVOReservas(){
		List<VOReserva> reservas = new LinkedList<>();
		if(pp.darReservas().size()>0){
		System.out.println("Voy a hacer la lista de VOReservas");
		for(VOReserva p : pp.darReservas()){
			if(p==null)
				System.out.println("no hay nada en la lista");
			reservas.add(p);
			}
		}
		System.out.println("Ya hice la lista de VOReservas");
		return reservas;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar las Residencias
	 *****************************************************************/
	
	public Residencia adicionarResidencia(int nit,  String nombre, String direccion, int telefono )
	{
		return pp.adicionarResidencia(nit, nombre, direccion, telefono);
		
	}
	
	
	public long eliminarResidenciaPorId(long idOperario) 
	{
		return pp.eliminarResidencia(idOperario);
        
	}
        
        
	public Residencia darResidenciaPOrId(long id){
		return pp.darResidenciaPorId(id);
	}
		
	
	public List<Residencia> darRessidencia(){
		return pp.darResidencias();
	}

	public List<VOResidencia> darVOResidencia(){
		List<VOResidencia> residencias = new LinkedList<>();
		for(VOResidencia p :
		pp.darResidencias())
			residencias.add(p);
		return residencias;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los viviendas
	 *****************************************************************/
	public Vivienda adicionarVivienda(int capacidadTotal, long idOperario, int numeroHabitaciones, double precioNoche, double precioSeguro, String direccion, int diasUsada)
	{
		return pp.adicionarVivienda(capacidadTotal, idOperario, numeroHabitaciones, precioNoche, precioSeguro, direccion, 0);
		
	}
	
	
	public long eliminarViviendaPorId(long idRecinto) 
	{
		return pp.eliminarViviendaPorId(idRecinto);
        
	}
        
        
	public Vivienda darViviendaPorId(long id){
		return pp.darViviendaPorId(id);
	}
		
	
	public List<Vivienda> darViviendas(){
		return pp.darViviendas();
	}

	public List<VOVivienda> darVOViviendas(){
		List<VOVivienda> viviendas = new LinkedList<>();
		for(VOVivienda v :
		pp.darViviendas())
			viviendas.add(v);
		return viviendas;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Menajes
	 *****************************************************************/
	public Menaje adicionarMenaje(String nombre)
	{
		return pp.adicionarMenaje(nombre);
		
	}
	
	
	public long elimnarMenajePorID(long id) 
	{
		return pp.eliminarMenajePorId(id);
	}
        
        
	public Menaje darMenajePorId(long id){
		return pp.darMenajePorId(id);
	}
		
	
	public List<Menaje> darMenajes(){
		return pp.darMenajes();
	}

	public List<VOMenaje> darVOMenajes(){
		List<VOMenaje> menajes = new LinkedList<>();
		for(VOMenaje m :
		pp.darMenajes())
			menajes.add(m);
		return menajes;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los ServicioHotel
	 *****************************************************************/
	public ServicioHotel adicionarServicioHotel(String nombre)
	{
		return pp.adicionarServicioHotel(nombre);
		
	}
	
	
	public long eliminarServicioHotelPOrId(long id) 
	{
		return pp.eliminarServicioHotelPorId(id);
	}
        
        
	public ServicioHotel darServicioHotelPorId(long id){
		return pp.darServicioHotelPorId(id);
	}
		
	
	public List<ServicioHotel> darServiciosHotel(){
		return pp.darServiciosHotel();
	}

	public List<VOServicioHotel> darVOServicioHotel(){
		List<VOServicioHotel> servs = new LinkedList<>();
		for(VOServicioHotel a :
		pp.darServiciosHotel())
			servs.add(a);
		return servs;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los ServiciosResidencia
	 *****************************************************************/
	public ServicioResidencia adicionarServicioResidencia(String nombre, double costoExtraNoche , double costoExtraMes, double costoExtraSemestre)
	{
		return pp.adicionarServicioResidencia(nombre, costoExtraNoche, costoExtraMes, costoExtraSemestre);
		
	}
		
	public long elimnarServicioResidenciaPorID(long id) 
	{
		return pp.eliminarServicioResidenciaPorId(id);
	}
        
        
	public ServicioResidencia darServicioResidenciaPorId(long id){
		return pp.darServicioResidenciaPorId(id);
	}
		
	
	public List<ServicioResidencia> darServiciosResidencia(){
		return pp.darServiciosResidencia();
	}

	public List<VOServicioResidencia> darVOServicioResidencia(){
		List<VOServicioResidencia> servs = new LinkedList<>();
		for(VOServicioResidencia a:
		pp.darServiciosResidencia())
			servs.add(a);
		return servs;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los ServiciosPrivado
	 *****************************************************************/
	public ServicioPrivado adicionarServicioPrivado(String nombre)
	{
		return pp.adicionarServicioPrivado(nombre);
		
	}
		
	public long eliminarServicioPrivado(long id) 
	{
		return pp.eliminarServicioPrivadoPorId(id);
	}
        
        
	public ServicioPrivado darServicioPrivadoPorId(long id){
		return pp.darServicioPrivadoPorId(id);
	}
		
	
	public List<ServicioPrivado> darServiciosPrivados(){
		return pp.darServiciosPrivados();
	}

	public List<VOServicioPrivado> darVOServicioPrivado(){
		List<VOServicioPrivado> servs = new LinkedList<>();
		for(VOServicioPrivado a:
		pp.darServiciosPrivados())
			servs.add(a);
		return servs;
	}
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los HotelOfreceServicio
	 *****************************************************************/
	public HotelOfreceServicio adicionarHotelOfreceServicio(long idHotel, long idServicio)
	{
		return pp.adicionarHotelOfreceServicio(idHotel, idServicio);
		
	}
		
	public long eliminarHotelOfreceServicio(long idHotel, long idServicio) 
	{
		return pp.eliminarHotelOfreceServicio(idHotel, idServicio);
	}
     

	public List<HotelOfreceServicio> darHotelesOfreceServicio(){
		return pp.darHotelesOfreceServicios();
	}

	public List<VOHotelOfreceServicio> darVOHotelesOfreceServicio(){
		List<VOHotelOfreceServicio> servs = new LinkedList<>();
		for(VOHotelOfreceServicio a:
		pp.darHotelesOfreceServicios())
			servs.add(a);
		return servs;
	}
	
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los ResidenciaOfreceServicio
	 *****************************************************************/
	public ResidenciaOfreceServicio adicionarResidenciaOfreceServicio(long idResidencia, long idServicio)
	{
		return pp.adicionarResidenciaOfreceServicio(idResidencia, idServicio);
		
	}
		
	public long eliminarResidenciaOfreceServicio(long idResidencia, long idServicio) 
	{
		return pp.eliminarResidenciaOfreceServicio(idResidencia, idServicio);
	}
        
        
			
	
	public List<ResidenciaOfreceServicio> darResidenciasOfreceServicios(){
		return pp.darResidenciasOfrecenServicios();
	}

	public List<VOResidenciaOfreceServicio> darVOResidenciaOfreceServicio(){
		List<VOResidenciaOfreceServicio> servs = new LinkedList<>();
		for(VOResidenciaOfreceServicio a:
		pp.darResidenciasOfrecenServicios())
			servs.add(a);
		return servs;
	}
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los ViviendaTieneMenajes
	 *****************************************************************/
	public ViviendaTieneMenajes adicionarViviendaTieneMenajes(long idVivienda, long idMenaje, int cantidad, double valorUnidad)
	{
		return pp.adicionarViviendaTieneMenajes(idVivienda, idMenaje, cantidad, valorUnidad);
		
	}
		
	public long eliminarViviendaTieneMenajes(long idVivienda, long idMenaje) 
	{
		return pp.eliminarViviendaTieneMenajes(idVivienda, idMenaje);
	}
        
    
		
	
	public List<ViviendaTieneMenajes> darViviendasTienenMenajes(){
		return pp.darViviendaTieneMenajes();
	}

	public List<VOViviendaTieneMenajes> darVOViviendasTienesMenajes(){
		List<VOViviendaTieneMenajes> servs = new LinkedList<>();
		for(VOViviendaTieneMenajes a:
		pp.darViviendaTieneMenajes())
			servs.add(a);
		return servs;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los ViviendaTieneMenajes
	 *****************************************************************/
	public HabitacionResidenciaTieneMenajes adicionarHabitacionResidenciaTieneMenajes(long idHabitacion, 
			long idMenaje, int cantidad, double valorUnidad)
	{
		return pp.adicionarHabitacionResidenciaTieneMenajes(idHabitacion, idMenaje, cantidad, valorUnidad);
		
	}
		
	public long eliminarHabitacionResidenciaTieneMenajes(long idHabitacion, long idMenaje) 
	{
		return pp.eliminarHabitacionResidenciaTieneMenajes(idHabitacion, idMenaje);
	}
        		
	
	public List<HabitacionResidenciaTieneMenajes> darHabitacionResidenciaTieneMenajes(){
		return pp.darHabitacionResidenciaTieneMenajes();
	}

	public List<VOHabitacionResidenciaTieneMenajes> darVOHabitacionResidenciaTieneMenajes(){
		List<VOHabitacionResidenciaTieneMenajes> servs = new LinkedList<>();
		for(VOHabitacionResidenciaTieneMenajes a:
		pp.darHabitacionResidenciaTieneMenajes())
			servs.add(a);
		return servs;
	}
	
	
	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Alohandes
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public void limpiarAlohandes ()
	{
         pp.limpiarAlohandes();	
        
	}
}
