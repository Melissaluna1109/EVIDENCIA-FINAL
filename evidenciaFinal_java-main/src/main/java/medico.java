
//Se importan librerías necesarias para el funcionamiento de la clase
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class medico extends persona {
    // Atributos del médico
    private String especialidad;

    // Lista donde se guardarán todos los médicos
    private static List<medico> medicos = new ArrayList<>();

    // Archivo donde se guardarán todos los datos de los medicos
    private static String ARCHIVO = "medicos.json";

    // Constructor para crear el médico usando atributos completos
    public medico(String especialidad, int id, String nombre, String apellido, int edad, char genero, String contraseña,
            String email) {
        super(id, nombre, apellido, edad, genero, contraseña, email);
        this.especialidad = especialidad;
    }

    // Constructor vacío para su uso en algunos métodos de otras clases
    public medico() {
    }

    // Métodos Get y Set para atributos privados
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public static List<medico> getMedicos() {
        return medicos;
    }

    public static void setMedicos(List<medico> medicos) {
        medico.medicos = medicos;
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
            // Se crea el médico con los datos semilla
            medico semilla = new medico("General", 1, "Ariana", "Horan", 32, 'F', "1234", "arianah@outlook.es");

            // Si no existe el archivo, se agrega el médico semilla para comenzar con el
            // programa
            if (file.canExecute() == false) {
                medicos.add(semilla);
            }

            // Si sí existe, se leen las líneas contenidas en el archivo
            else {
                // Se crea el lector para el archivo de medicos.json
                FileReader reader = new FileReader(file);

                // Se crean las variables para convertir el Json a Array
                JsonParser parser = new JsonParser();
                JsonArray array = (JsonArray) parser.parse(reader);

                // Se utiliza un ciclo for para agregar cada médico en la lista medicos
                for (Object o : array) {
                    // Se convierte el objeto a String
                    String cadena = o.toString();
                    // Se crea el objeto gson para pasar del formato JSON a un objeto Java
                    Gson gson = new Gson();
                    // Se convierte el objeto
                    medico medico = gson.fromJson(cadena, medico.class);
                    // Se agrega el objeto medico en la lista medicos
                    medicos.add(medico);
                }

                // Si la lista está vacía, entonces se agrega el dato semilla para comenzar el
                // programa
                if (medicos.isEmpty()) {
                    medicos.add(semilla);
                }
            }

            System.out.println("Los médicos iniciales han sido guardados.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println(
                    "No se pudieron guardar los médicos semilla correctamente por el error: " + e.getMessage());
        }
    }

    @Override
    // creaPersona: registra a un nuevo médico para su acceso al sistema
    public void creaPersona() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Variables necesarias para guardar los atributos del medico
            int id, edad;
            String nombre, apellido, ingresaGenero;
            char genero;
            String contraseña, email, especialidad;
            // Se crea el arreglo con las opciones de género para evitar error por parte del
            // usuario
            String[] generos = { "F", "M" };

            // Se aumenta un id dependiendo de la cantidad de médicos guardados en la lista
            // medicos
            id = medicos.size() + 1;
            // Se piden los datos del médico
            nombre = JOptionPane.showInputDialog("Nombre del médico:");
            apellido = JOptionPane.showInputDialog("Apellido del médico:");
            email = JOptionPane.showInputDialog("Correo electrónico del médico: ");
            contraseña = JOptionPane.showInputDialog("Contraseña con la que accederá el médico:");
            edad = Integer.parseInt(JOptionPane.showInputDialog("Edad del médico:"));
            especialidad = JOptionPane.showInputDialog("Especialidad del médico:");
            // Se elige el género del médico
            ingresaGenero = (String) JOptionPane.showInputDialog(null,
                    "Indica el género del médico:\n (Usa F para Femenino y M para masculino)\n\n",
                    "", JOptionPane.DEFAULT_OPTION, null, generos, generos[0]);
            // Se convierte la opción elegida para crear el objeto de forma correcta
            genero = ingresaGenero.charAt(0);

            // Se crea el objeto medico
            medico medico = new medico(especialidad, id, nombre, apellido, edad, genero, contraseña, email);

            // Se agrega el objeto medico en la lista medicos
            medicos.add(medico);

            // Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente el médico en la lista medicos.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo guardar el médico en la lista por el error: " + e.getMessage());
        }
    }

    @Override
    // eliminaPersona: eliminará al médico elegido por el usuario de la lista
    // medicos
    public void eliminaPersona() throws Exception {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se pide el id del médico y se busca en la lista
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número de médico que deseas eliminar:"));
            boolean existe = medicos.stream().anyMatch(x -> x.getId() == id);
            // Si no existe el médico, se manda que el médico no fue encontrado
            if (existe == false) {
                System.out.println("No existe ningún médico con dicho ID.");
            }
            // Si sí existe, se elimina y manda mensaje de confirmación
            else {
                // Se elimina el médico
                medicos.remove(id - 1);
                // Se confirma la eliminación
                System.out.println("El médico ha sido eliminado exitosamente.");
            }
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo eliminar el médico por el error: " + e.getMessage());
        }
    }

    @Override
    // guardaPersona: guarda los datos de los médicos en un archivo JSON
    public void guardaPersona() {
        // Se crea el String llamado jsonMedico como variable que guardará el formato
        // JSON.
        String jsonMedico;

        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el objeto gson que nos ayudará a pasar el objeto jsonMedico a un
            // formato JSON
            Gson gson = new Gson();

            // Se crea el fileWritter para escribir en el archivo
            FileWriter fileWriter = new FileWriter(ARCHIVO);
            // Se crea el printWritter para ir escribiendo en el archivo JSON
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Se pasan los médicos a un formato JSON
            jsonMedico = gson.toJson(medicos);
            // Se escribe en el archivo JSON
            printWriter.print(jsonMedico);

            // Se cierra el printWritter para que los cambios sean guardados
            printWriter.close();

            // Se manda mensaje al usuario para que pueda ver el guardado exitoso de los
            // médicos
            System.out.println("Los médicos han sido guardados correctamente.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron guardar los médicos en el archivo JSON por el error: " + e.getMessage());
        }
    }

    @Override
    // ingresar: validará si efectivamente el médico ha sido registrado en el
    // sistema
    public boolean ingresar(int id, String contraseña) throws Exception {
        // Se crea la variable de retorno del método
        boolean existe = false;

        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se identifica si el médico ingresado está registrado
            existe = medicos.stream().anyMatch(x -> x.getId() == id && x.getContraseña().equals(contraseña));
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo validar al médico.");
        }

        // Se regresa el resultado de la búsqueda
        return existe;
    }

    @Override
    // cargarJSON: leerá y cargará todos los médicos que se encuentren en el archivo
    // JSON
    public void cargarJSON() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el archivo nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);

            // Se crea el lector para el archivo de medicos.json
            FileReader reader = new FileReader(file);

            // Se crean las variables para convertir el Json a Array
            JsonParser parser = new JsonParser();
            JsonArray array = (JsonArray) parser.parse(reader);

            // Se indicará al usuario que se mostrarán los médicos guardados en el archivo
            System.out.println("Los médicos encontrados en el archivo " + ARCHIVO + " son: ");
            // Se agrega una línea para mejor visibilidad
            System.out.println("");

            // Se usa un ciclo for para desplegar cada médico
            for (Object o : array) {
                // Se convierte el objeto a String
                String cadena = o.toString();
                // Se crea el objeto gson para pasar del formato JSON a un objeto Java
                Gson gson = new Gson();
                // Se convierte el objeto
                medico medico = gson.fromJson(cadena, medico.class);
                // Se despliega la información del objeto medico
                medico.despliega();
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
    // despliega: método que ayudará a mostrar los datos de cada médico
    public void despliega() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se imprimen en pantalla los datos del médico
            System.out.println("ID del médico: " + getId());
            System.out.println("Nombre del médico: " + getNombre());
            System.out.println("Apellido del médico: " + getApellido());
            System.out.println("Edad del médico: " + getEdad());
            System.out.println("Género del médico: " + getGenero());
            System.out.println("Correo del médico: " + getEmail());
            System.out.println("Contraseña del médico: " + getContraseña());
            System.out.println("Especialidad del médico: " + especialidad);
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo mostrar el médico por el error: " + e.getMessage());
        }
    }

    @Override
    // consultaUsuarios: mostrará todos los médicos guardados en la lista medicos
    public void consultaUsuarios() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se indica al usuario que se mostrarán todas los médicos que se han registrado
            System.out.println("Se han registrado los siguientes médicos: ");
            // Se recorre la lista medicos para mostrarle al usuario cada médico que se ha
            // registrado
            for (medico x : medicos) {
                // Se llama al método despliega de la clase médico
                x.despliega();
                // Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
            // Se indica al usuario que se han terminado de desplegar todos los médicos
            System.out.println("Se han terminado de mostrar todos los médicos registrados.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron desplegar los médicos por el error: " + e.getMessage());
        }
    }

    // buscaPaciente: filtrará todos los pacientes relacionados con el médico
    public static void buscaPacientes(int id) {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se agrega un título para los pacientes obtenidos
            System.out.println("Se encontraron los siguientes pacientes del médico #" + id);
            // Se buscan los pacientes que correspondan al médico y se guardan en una lista
            // de pacientes
            List<paciente> pacientesMedico = paciente.getPacientes().stream().filter(medico -> medico.getId() == id)
                    .collect(Collectors.toList());
            // Se recorre la lista pacientesMedico para mostrarle al médico cada paciente
            // que tiene
            for (paciente x : pacientesMedico) {
                // Se llama al método despliega de la clase paciente
                x.despliega();
                // Se agrega una línea para mejor visibilidad
                System.out.println("");
            }

            // Se indica al usuario que se han terminado de desplegar todos los pacientes
            System.out.println("Se han terminado de mostrar los pacientes del médico #" + id + ".");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron filtrar los pacientes por el error: " + e.getMessage());
        }
    }

    // consultaPaciente: cambiará el diagnóstico del paciente
    public static void consultaPaciente(paciente paciente) {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se pide el nuevo diagnóstico del paciente
            String diagnóstico = JOptionPane.showInputDialog("Ingresa el nuevo diagnóstico del paciente:");
            // Se cambia el diagnóstico del paciente
            paciente.setDiagnostico(diagnóstico);
            // Se le indica al usuario el nuevo diagnóstico
            System.out.println("El nuevo diagnóstico del paciente es: " + paciente.getDiagnostico());
        }
        // Capta cualquier excepción que surga durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo consultar al paciente por el error: " + e.getMessage());
        }
    }
}
