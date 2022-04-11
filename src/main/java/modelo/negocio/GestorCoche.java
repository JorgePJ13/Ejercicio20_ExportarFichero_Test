package modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.DaoCocheMySQL;
import modelo.persistencia.interfaces.DaoCoche;

public class GestorCoche {

	private DaoCoche daoCoche = new DaoCocheMySQL();
	List<Coche> listaCoches = listarCoches();

	/**
	 * Metodo que da de alta un coche en base de datos. La longitud de la matricula
	 * del coche debe de tener al menos 7 caracteres. El numero de kilometros del
	 * coche no puede ser negativo. No se pueden dar de alta dos coches con la misma
	 * matricula
	 * 
	 * @param c el coche a dar de alta
	 * @return 0 en caso de que hayamos dado de alta el coche, 1 en caso de algun
	 *         error de conexión con la bbdd y 2 en caso de que el coche no cumpla
	 *         con los requisitos previamente establecidos
	 */
	public int alta(Coche c) {

		for (Coche coche : listaCoches) {
			if (!(coche.getMatricula().equals(c.getMatricula())) && c.getMatricula().length() >= 7
					&& c.getKilometros() >= 0) {
				boolean alta = daoCoche.alta(c);
				if (alta) {
					return 0;
				} else {
					return 1;
				}
			} else {
				return 2;
			}
		}
		return c.getId();
	}

	/**
	 * Metodo que da de baja un coche de la base de datos.
	 * 
	 * @param id el id del coche a eliminar
	 * @return true en caso de que se haya eliminado el coche. false en caso de que
	 *         no se haya podido eliminar
	 */
	public boolean baja(int id) {
		boolean baja = daoCoche.eliminar(id);
		return baja;
	}

	/**
	 * Metodo que modifica un coche en base de datos. La matricula del coche debe de
	 * tener al menos 7 caracteres, el numero de kilometros no podrá ser negativo y
	 * no puede haber dos matriculas iguales en la base de datos. La modificación
	 * sera a partir del id del coche
	 * 
	 * @param c el coche a modificar. Dentro tiene que tener el id
	 * @return 0 en caso de que hayamos modificado el coche, 1 en caso de algun
	 *         error de conexión con la bbdd y 2 en caso de que el coche no cumpla
	 *         con los requisitos previamente establecidos
	 */
	public int modificar(Coche c) {
		for (Coche coche : listaCoches) {
			if (!(coche.getMatricula().equals(c.getMatricula())) && c.getMatricula().length() >= 7
					&& c.getKilometros() >= 0) {
				boolean modificado = daoCoche.modificar(c);
				if (modificado) {
					return 0;
				} else {
					return 1;
				}
			}
			return 2;
		}
		return c.getId();
	}

	/**
	 * Método que devuelve la lista de coches en la base de datos.
	 * @return la lista de coches existentes en el concesionario
	 */
	public List<Coche> listarCoches() {
		List<Coche> listaCoches = daoCoche.listar();
		return listaCoches;
	}

	/**
	 * Método que devuelve un coche dado su identificador en la BBDD.
	 * @param id el id por el que se busca el coche en la BBDD.
	 * @return el coche encontrado según su id.
	 */
	public Coche buscarPorId(int id) {
		Coche coche = daoCoche.buscarPorId(id);
		return coche;
	}

	/**
	 * Método que devuelve un coche segun su matricula en la BBDD.
	 * @param matricula la matricula por el que se busca el coche en la BBDD.
	 * @return el coche encontrado según su matricula.
	 */
	public Coche buscarPorMatricula(String matricula) {
		Coche coche = daoCoche.buscarPorMatricula(matricula);
		return coche;
	}

	/**
	 * Método que devuelve un coche segun su marca en la BBDD.
	 * @param marca la marca por el que se busca el coche en la BBDD.
	 * @return el coche encontrado según su marca.
	 */
	public List<Coche> buscarPorMarca(String marca) {
		List<Coche> listaCoches = daoCoche.buscarPorMarca(marca);
		return listaCoches;
	}

	/**
	 * Método que devuelve un coche segun su modelo en la BBDD.
	 * @param modelo el modelo por el que se busca el coche en la BBDD.
	 * @return el coche encontrado según su modelo.
	 */
	public List<Coche> buscarPorModelo(String modelo) {
		List<Coche> listaCoches = daoCoche.buscarPorModelo(modelo);
		return listaCoches;
	}
}
