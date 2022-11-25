
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class paciente extends persona {
    // Atributos del paciente
    private String diagnostico;
    private medico medico;

    // Lista donde se guardarán todos los pacientes
    private static List<paciente> pacientes = new ArrayList<>();

    // Archivo donde se guardarán todos los datos de los pacientes
    private static String ARCHIVO = "pacientes.json";

    // Constructor para crear el paciente usando atributos completos
    public paciente(String diagnostico, medico medico, int id, String nombre, String apellido, int edad, char genero,
            String contraseña, String email) {
        super(id, nombre, apellido, edad, genero, contraseña, email);
        this.diagnostico = diagnostico;
        this.medico = medico;
    }

    // Constructor vacío para su uso en algunos métodos de otras clases
    public paciente() {
    }

    // Métodos Get y Set para atributos privados
    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public medico getMedico() {
        return medico;
    }

    public void setMedico(medico medico) {
        this.medico = medico;
    }

    public static List<paciente> getPacientes() {
        return pacientes;
    }

    public static void setPacientes(List<paciente> pacientes) {
        paciente.pacientes = pacientes;
    }

    // Métodos propios de la clase

    @Override
    // agregaDatosIniciales: añadirá un dato semilla para acceder a las funciones
    public void agregaDatosIniciales() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el archivo nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);
            // Se crea el médico con los datos semilla para crear el paciente semilla
            medico medico = new medico("General", 1, "Melissa", "Luna", 32, 'F', "1234", "melissa@outlook.es");
            // Se crea el paciente con los datos semilla
            paciente semilla = new paciente("Gripa", medico, 1, "Carlos", "Villareal", 30, 'M', "1234",
                    "carlos@outlook.es");

            // Si no existe el archivo, se agrega el paciente semilla para comenzar con el
            // programa
            if (file.canExecute() == false) {
                pacientes.add(semilla);
            }

            // Si sí existe, se leen las líneas contenidas en el archivo
            else {
                // Se crea el lector para el archivo de pacientes.json
                FileReader reader = new FileReader(file);

                // Se crean las variables para convertir el Json a Array
                JsonParser parser = new JsonParser();
                JsonArray array = (JsonArray) parser.parse(reader);

                // Se utiliza un ciclo for para agregar cada paciente en la lista pacientes
                for (Object o : array) {
                    // Se convierte el objeto a String
                    String cadena = o.toString();
                    // Se crea el objeto gson para pasar del formato JSON a un objeto Java
                    Gson gson = new Gson();
                    // Se convierte el objeto
                    paciente paciente = gson.fromJson(cadena, paciente.class);
                    // Se agrega el paciente en la lista pacientes
                    pacientes.add(paciente);
                }

                // Si la lista está vacía, entonces se agrega el dato semilla para comenzar el
                // programa
                if (pacientes.isEmpty()) {
                    pacientes.add(semilla);
                }
            }

            System.out.println("Los pacientes iniciales han sido guardados.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println(
                    "No se pudieron guardar los pacientes semilla correctamente por el error: " + e.getMessage());
        }
    }

    @Override
    // creaPersona: registra a un nuevo paciente para su acceso al sistema
    public void creaPersona() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Variables necesarias para guardar los atributos del paciente
            int id, edad;
            String nombre, apellido, ingresaGenero;
            char genero;
            String contraseña, email, diagnostico;
            // Se crea el arreglo con las opciones de género para evitar error por parte del
            // usuario
            String[] generos = { "F", "M" };

            // Se aumenta un id dependiendo de la cantidad de pacientes guardados en la
            // lista pacientes
            id = pacientes.size() + 1;
            // Se piden los datos del paciente
            nombre = JOptionPane.showInputDialog("Nombre del paciente:");
            apellido = JOptionPane.showInputDialog("Apellido del paciente:");
            edad = Integer.parseInt(JOptionPane.showInputDialog("Edad del paciente:"));
            // Se elige el género del paciente
            ingresaGenero = (String) JOptionPane.showInputDialog(null,
                    "Indica el género del paciente:\n (Usa F para Femenino y M para masculino)\n\n",
                    "", JOptionPane.DEFAULT_OPTION, null, generos, generos[0]);
            // Se convierte la opción elegida para crear el objeto de forma correcta
            genero = ingresaGenero.charAt(0);
            // Continúa con el ingreso de los datos
            contraseña = JOptionPane.showInputDialog("Contraseña con la que accederá el paciente:");
            email = JOptionPane.showInputDialog("Correo electrónico del paciente: ");
            diagnostico = JOptionPane.showInputDialog("Diagnóstico del paciente:");
            // Se crea el objeto medico llamando al método busca medico
            medico medico = cita.buscaMedico();
            // Si está nulo, manda un mensaje de médico no encontrado
            if (medico == null) {
                System.out.println("No existe ningún médico con tal ID");
            }

            // Se crea el objeto paciente
            paciente paciente = new paciente(diagnostico, medico, id, nombre, apellido, edad, genero, contraseña,
                    email);

            // Se agrega el objeto paciente en la lista pacientes
            pacientes.add(paciente);

            // Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente el paciente en la lista pacientes.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo guardar el paciente en la lista por el error: " + e.getMessage());
        }
    }

    @Override
    // eliminaPersona: eliminará al paciente elegido por el usuario de la lista
    // pacientes
    public void eliminaPersona() throws Exception {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se pide el id del paciente y se busca en la lista
            int id = Integer
                    .parseInt(JOptionPane.showInputDialog("Ingresa el número de paciente que deseas eliminar:"));
            boolean existe = pacientes.stream().anyMatch(x -> x.getId() == id);
            // Si no existe el paciente, se manda que el paciente no fue encontrado
            if (existe == false) {
                System.out.println("No existe ningún paciente con dicho ID.");
            }
            // Si sí existe, se elimina y manda mensaje de confirmación
            else {
                // Se elimina el paciente
                pacientes.remove(id - 1);
                // Se confirma la eliminación
                System.out.println("El paciente ha sido eliminado exitosamente.");
            }
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo eliminar el paciente por el error: " + e.getMessage());
        }
    }

    @Override
    // guardaPersona: guarda los datos de los pacientes en un archivo JSON
    public void guardaPersona() {
        // Se crea el String llamado jsonPaciente como variable que guardará el formato
        // JSON.
        String jsonPaciente;

        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el objeto gson que nos ayudará a pasar el objeto paciente a un
            // formato JSON
            Gson gson = new Gson();

            // Se crea el fileWritter para escribir en el archivo
            FileWriter fileWriter = new FileWriter(ARCHIVO);
            // Se crea el printWritter para ir escribiendo en el archivo JSON
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Se pasan los pacientes a un formato JSON
            jsonPaciente = gson.toJson(pacientes);
            // Se escribe en el archivo JSON
            printWriter.print(jsonPaciente);

            // Se cierra el printWritter para que los cambios sean guardados
            printWriter.close();

            // Se manda mensaje al usuario para que pueda ver el guardado exitoso de los
            // pacientes
            System.out.println("Los pacientes han sido guardados correctamente.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out
                    .println("No se pudieron guardar los pacientes en el archivo JSON por el error: " + e.getMessage());
        }
    }

    @Override
    // ingresar: validará si efectivamente el paciente ha sido registrado en el
    // sistema
    public boolean ingresar(int id, String contraseña) {
        // Se crea la variable de retorno del método
        boolean existe = false;

        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se identifica si el paciente ingresado está registrado
            existe = pacientes.stream().anyMatch(x -> x.getId() == id && x.getContraseña().equals(contraseña));
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo validar al paciente.");
        }

        // Se regresa el resultado de la búsqueda
        return existe;
    }

    @Override
    // cargarJSON: leerá y cargará todos los pacientes que se encuentren en el
    // archivo JSON
    public void cargarJSON() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el archivo nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);

            // Se crea el lector para el archivo de pacientes.json
            FileReader reader = new FileReader(file);

            // Se crean las variables para convertir el Json a Array
            JsonParser parser = new JsonParser();
            JsonArray array = (JsonArray) parser.parse(reader);

            // Se indicará al usuario que se mostrarán los pacientes guardados en el archivo
            System.out.println("Los pacientes encontrados en el archivo " + ARCHIVO + " son: ");
            // Se agrega una línea para mejor visibilidad
            System.out.println("");

            // Se utiliza un ciclo for para desplegar cada paciente
            for (Object o : array) {
                // Se convierte el objeto a String
                String cadena = o.toString();
                // Se crea el objeto gson para pasar del formato JSON a un objeto Java
                Gson gson = new Gson();
                // Se convierte el objeto
                paciente paciente = gson.fromJson(cadena, paciente.class);
                // Se despliega la información del paciente
                paciente.despliega();
                // Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());
        }
    }

    @Override
    // despliega: método que ayudará a mostrar los datos de cada paciente
    public void despliega() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se imprimen en pantalla los datos del paciente
            System.out.println("ID del paciente: " + getId());
            System.out.println("Nombre del paciente: " + getNombre());
            System.out.println("Apellido del paciente: " + getApellido());
            System.out.println("Edad del paciente: " + getEdad());
            System.out.println("Género del paciente: " + getGenero());
            System.out.println("Correo del paciente: " + getEmail());
            System.out.println("Contraseña del paciente: " + getContraseña());
            System.out.println("Diagnóstico del paciente: " + diagnostico);
            System.out.println("**** Medico tratante ****");
            // Se llama al método despliega de la clase medico para conocer los atributos
            // del médico
            medico.despliega();
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo mostrar el paciente por el error: " + e.getMessage());
        }
    }

    @Override
    // consultaUsuarios: mostrará todos los pacientes guardados en la lista
    // pacientes
    public void consultaUsuarios() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se indica al usuario que se mostrarán todas los pacientes que se han
            // registrado
            System.out.println("Se han registrado los siguientes pacientes: ");
            // Se recorre la lista pacientes para mostrarle al usuario cada paciente que se
            // ha registrado
            for (paciente x : pacientes) {
                // Se llama al método despliega de la clase paciente
                x.despliega();
                // Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
            // Se indica al usuario que se han terminado de desplegar todos los pacientes
            System.out.println("Se han terminado de mostrar todos los pacientes registrados.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron desplegar los pacientes por el error: " + e.getMessage());
        }
    }

    // asistirCita: llamará al método consultaPaciente para cambiar el diagnóstico
    public void asistirCita(int id) {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se guardan los datos del paciente en un objeto de tipo paciente
            paciente paciente = pacientes.get(id - 1);
            // Se crea el objeto medico para acceder a todos los métodos
            medico medico = new medico();
            // Se llama al método consultaPaciente para el cambio del diagnóstico
            medico.consultaPaciente(paciente);
            // Se le indica al usuario que la consulta fue concluida
            System.out.println("Se ha terminado la consulta con éxito.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo asistir a la cita por el error: " + e.getMessage());
        }
    }
}
