package modelo.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

public class DaoCocheMySQL implements DaoCoche {

	private Connection conexion;

	public boolean abrirConexion() {
		String url = "jdbc:mysql://localhost:3306/bbdd";
		String usuario = "root";
		String password = "";
		try {
			conexion = DriverManager.getConnection(url, usuario, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean cerrarConexion() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean alta(Coche c) {
		if (!abrirConexion()) {
			return false;
		}
		boolean alta = true;

		String consulta = "INSERT INTO coches(matricula,marca,modelo,kilometros) VALUES(?,?,?,?)";

		try {
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setInt(4, c.getKilometros());

			int nFilasAfectadas = ps.executeUpdate();
			if (nFilasAfectadas == 0) {
				alta = false;
			}
			System.out.println("Numero de filas afectadas: " + nFilasAfectadas);
		} catch (SQLException e) {
			System.out.println("Error al dar de alta el coche: " + c);
			alta = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return alta;
	}

	@Override
	public boolean eliminar(int id) {
		if (!abrirConexion()) {
			return false;
		}
		boolean baja = true;
		String consulta = "DELETE FROM coches WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setInt(1, id);
			int nFilasAfectadas = ps.executeUpdate();
			if (nFilasAfectadas == 0) {
				baja = false;
			}
			System.out.println("Numero de filas afectadas: " + nFilasAfectadas);
		} catch (SQLException e) {
			System.out.println("Error al eliminar el coche con id: " + id);
			baja = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return baja;
	}

	@Override
	public boolean modificar(Coche c) {
		if (!abrirConexion()) {
			return false;
		}
		boolean modificado = true;
		String consulta = "UPDATE coches SET matricula=?, marca=?, modelo=?, kilometros=? WHERE id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setInt(4, c.getKilometros());
			ps.setInt(5, c.getId());

			int nFilasAfectadas = ps.executeUpdate();
			if (nFilasAfectadas == 0)
				modificado = false;
			System.out.println("Numero de filas afectadas: " + nFilasAfectadas);
		} catch (SQLException e) {
			System.out.println("Error al modificar el coche: " + c);
			modificado = false;
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return modificado;
	}

	@Override
	public List<Coche> listar() {
		if (!abrirConexion()) {
			return null;
		}
		List<Coche> listaCoches = new ArrayList<Coche>();

		String consulta = "SELECT id, matricula, marca, modelo, kilometros FROM coches";

		try {
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setKilometros(rs.getInt(5));

				listaCoches.add(coche);
			}
		} catch (Exception e) {
			System.out.println("Error al obtener los coches");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}

		return listaCoches;
	}

	@Override
	public Coche buscarPorId(int id) {
		if (!abrirConexion()) {
			return null;
		}
		Coche coche = null;
		String consulta = "SELECT id, matricula, marca, modelo, kilometros FROM coches WHERE id = ?";

		try {
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setKilometros(rs.getInt(5));
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar el coche con id: " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return coche;
	}

	@Override
	public Coche buscarPorMatricula(String matricula) {
		if (!abrirConexion()) {
			return null;
		}
		Coche coche = null;
		String consulta = "SELECT id, matricula, marca, modelo, kilometros FROM coches WHERE matricula = ?";

		try {
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setString(1, matricula);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setKilometros(rs.getInt(5));
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar el coche con matrícula: " + matricula);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return coche;
	}

	@Override
	public List<Coche> buscarPorMarca(String marca) {
		if (!abrirConexion()) {
			return null;
		}
		List<Coche> listaCoches = new ArrayList<Coche>();
		String consulta = "SELECT id, matricula, marca, modelo, kilometros FROM coches WHERE marca = ?";

		try {
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setString(1, marca);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setKilometros(rs.getInt(5));

				listaCoches.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar el coche por la marca: " + marca);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaCoches;
	}

	@Override
	public List<Coche> buscarPorModelo(String modelo) {
		if (!abrirConexion()) {
			return null;
		}
		List<Coche> listaCoches = new ArrayList<Coche>();
		String consulta = "SELECT id, matricula, marca, modelo, kilometros FROM coches WHERE modelo = ?";

		try {
			PreparedStatement ps = conexion.prepareStatement(consulta);
			ps.setString(1, modelo);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setKilometros(rs.getInt(5));

				listaCoches.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("Error al buscar el coche por el modelo: " + modelo);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return listaCoches;
	}

}
