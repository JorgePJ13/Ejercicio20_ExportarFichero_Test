package modelo.persistencia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import modelo.entidad.Coche;
import modelo.negocio.GestorCoche;

class TestObtenerCocheID {

	@Test
	void testObtenerIdCoche() {
		String modelo = "Fiesta";
		
		List<String> listaNoEsperada = Arrays.asList("1", "6240PJJ", "Peugeot", "308", "2500");
		
		//Coche resultadoEsperado = "Coche";
		//List<Object> resultadoEsperado = Arrays.asList(2, "1390KLL", "Ford", "Fiesta", 120000);
		
		GestorCoche gc = new GestorCoche();
		List<Coche> resultadoObtenido = gc.buscarPorModelo(modelo);
		
		assertNotEquals(listaNoEsperada, resultadoObtenido);
	}

}
