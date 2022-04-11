package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Coche;

public interface DaoCoche {

	/**
	 * Metodo que da de alta un coche en la BBDD. Se generará el ID de manera
	 * automática.
	 * @param c el coche a dar de alta
	 * @return true en caso de que se haya dado de alta. false en caso de error
	 * con la BBDD.
	 */
	boolean alta(Coche c);
	
	/**
	 * Metodo que da de baja un coche en la BBDD si el ID introducido coincide con alguna de la BBDD.
	 * @param id el identificador del coche a dar de baja
	 * @return true en caso de que se haya dado de baja. false en caso de error
	 * con la BBDD.
	 */
	boolean eliminar(int id);
	
	/**
	 * Método que modifica los datos de un coche previamente introducido en la BBDD, según el ID del coche.
	 * @param c el coche a modificar 
	 * @return true en caso de que se haya modificado. false en caso de error con la BBDD.
	 */
	boolean modificar(Coche c);
	
	/**
	 * Método que muestra un listado todos los coches que hay en la BBDD
	 * @return la lista de coches habidos en la BBDD
	 */
	List<Coche> listar();
	
	/**
	 * Método que busca un determinado coche según el identificador que posea
	 * @param id el identificador del coche a buscar
	 * @return el coche a buscar
	 */
	Coche buscarPorId(int id);
	
	/**
	 * Método que busca un determinado coche según la matrícula que posea
	 * @param matricula la matricula que posee el coche
	 * @return el coche a buscar
	 */
	Coche buscarPorMatricula(String matricula);
	
	/**
	 * Método que busca un coche según su marca
	 * @param marca la marca perteneciente al coche
	 * @return el coche a buscar
	 */
	List<Coche> buscarPorMarca(String marca);
	
	/**
	 * Método que busca un coche según el tipo de modelo
	 * @param modelo el modelo del coche (que tipo es)
	 * @return el coche a buscar
	 */
	List<Coche> buscarPorModelo(String modelo);
}
