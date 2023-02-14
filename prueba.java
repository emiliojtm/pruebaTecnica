
Este código se conecta a una base de datos MySQL especificando la URL de la base de datos, el usuario y la contraseña. 
Luego, obtiene el parámetro pasado al ejecutable y lo utiliza para filtrar la consulta a la tabla de proveedores. 
Los datos se recolectan en una lista y se escriben en un archivo plano con el nombre "proveedores_" + código del cliente + ".txt". 
Si el cliente no tiene proveedores asignados, se muestra un mensaje por consola. 

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaBD {

    public static void main(String[] args) {
        
		if (args.length == 0) {
            System.out.println("Debe proporcionar un código de cliente");
            return;
        }

        int codigoCliente = Integer.parseInt(args[0]);

        // Datos de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/nombre_basedatos";
        String usuario = "usuario";
        String contrasena = "contraseña";

        // Consulta a ejecutar en la base de datos
        String consulta = "SELECT * FROM proveedores WHERE id_cliente = ?";

        try {
            // Conexión a la base de datos
            Connection conexion = DriverManager.getConnection(url, usuario, contrasena);

            // Ejecución de la consulta
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, codigoCliente);
            ResultSet resultSet = statement.executeQuery();

            // Recolección de los datos en una lista
            List<String> registros = new ArrayList<>();
            while (resultSet.next()) {
                String idProveedor = resultSet.getString("id_proveedor");
                String nombre = resultSet.getString("nombre");
                String fechaAlta = resultSet.getString("fecha_alta");
				String linea = idProveedor + "," + nombre + "," + fechaAlta + "," + codigoCliente "\n";
				registros.add(linea);
			}

			// Generación del archivo plano
			if (registros.isEmpty()) {
				System.out.println("El cliente no tiene proveedores asignados");
			} else {
				FileWriter writer = new FileWriter("proveedores_" + codigoCliente + ".txt");
				for (String linea : registros) {
					writer.write(linea);
				}
				writer.close();
				System.out.println("Archivo generado correctamente");
			}

			// Cierre de la conexión a la base de datos
			resultSet.close();
			statement.close();
			conexion.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
