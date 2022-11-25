
//Se importan librerías necesarias para el funcionamiento de la clase
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class cita {
    // Atributos de la cita
    private int id;
    private String nombre;
    private String fecha;
    private String hora;
    private String motivo;
    private medico medico;
    private paciente paciente;

    // Lista que guardará todas las citas
    private static List<cita> citas = new ArrayList<>();;

    // Archivo donde se guardarán todas las citas
    private static String ARCHIVO = "citas.json";

    // Constructor
    public cita(int id, String nombre, String fecha, String hora, String motivo, medico medico, paciente paciente) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora = hora;
        this.motivo = motivo;
        this.medico = medico;
        this.paciente = paciente;
    }

    // Constructor vacío para su uso en algunos métodos de otras clases
    public cita() {
    }

    // Métodos Get y Set para atributos privados
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public medico getMedico() {
        return medico;
    }

    public void setMedico(medico medico) {
        this.medico = medico;
    }

    public paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(paciente paciente) {
        this.paciente = paciente;
    }

    public static List<cita> getCitas() {
        return citas;
    }

    public static void setCitas(List<cita> citas) {
        cita.citas = citas;
    }

    // Métodos propios de la clase

    // agregaDatosIniciales: añadirá un dato semilla para acceder a las funciones
    public static void agregaDatosIniciales() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el archivo nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);
            // Se crea el médico con los datos semilla para crear la cita semilla
            medico medico = new medico("General", 1, "Ariana", "Horan", 32, 'F', "1234", "arianah@outlook.es");
            // Se crea el paciente con los datos semilla para crear la cita semilla
            paciente paciente = new paciente("Gripa", medico, 1, "Nam", "Joon", 30, 'M', "1234", "namj@outlook.es");
            // Se crea la cita con los datos semilla
            cita semilla = new cita(1, "Cita 1", LocalDate.now().toString(), LocalTime.now().toString(), "Seguimiento",
                    medico, paciente);

            // Si no existe el archivo, se agrega la cita semilla para comenzar con el
            // programa
            if (file.canExecute() == false) {
                citas.add(semilla);
            }

            // Si sí existe, se leen las líneas contenidas en el archivo
            else {
                // Se crea el lector para el archivo de citas.json
                FileReader reader = new FileReader(file);

                // Se crean las variables para convertir el Json a Array
                JsonParser parser = new JsonParser();
                JsonArray array = (JsonArray) parser.parse(reader);

                // Se utiliza un ciclo for para agregar cada cita en la lista citas
                for (Object o : array) {
                    // El objeto es convertido a String
                    String cadena = o.toString();
                    // Se crea el objeto gson para pasar del formato JSON a un objeto Java
                    Gson gson = new Gson();
                    // Se convierte el objeto
                    cita cita = gson.fromJson(cadena, cita.class);
                    // Se agrega la cita en la lista citas
                    citas.add(cita);
                }

                // Si la lista está vacía, entonces se agrega el dato semilla para comenzar el
                // programa
                if (citas.isEmpty()) {
                    citas.add(semilla);
                }
            }

            System.out.println("Las citas iniciales han sido guardadas.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out
                    .println("No se pudieron guardar las citas semilla correctamente por el error: " + e.getMessage());
        }
    }

    // creaCita: guarda una nueva cita
    public static void creaCita() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Variables necesarias para guardar los atributos de la cita
            int id;
            String nombre, fecha, hora, motivo;

            // Se aumenta un id dependiendo de la cantidad de citas guardadas en la lista
            // citas
            id = citas.size() + 1;
            // Se pide el nombre y el motivo de la cita
            nombre = JOptionPane.showInputDialog("Nombre de la cita:");
            motivo = JOptionPane.showInputDialog("Motivo de la cita:");
            // Se guardan la fecha y hora actuales para la cita
            fecha = LocalDate.now().toString();
            hora = LocalTime.now().toString();
            // Se crea el objeto medico llamando al método buscaMedico
            medico medico = buscaMedico();
            // Si está nulo, manda un mensaje de médico inexistente
            if (medico == null) {
                System.out.println("No existe ningún médico con tal ID.");
            }
            // Se crea el objeto paciente llamando al método buscaPaciente
            paciente paciente = buscaPaciente();
            // Si está nulo, manda un mensaje de paciente inexistente
            if (paciente == null) {
                System.out.println("No existe ningún paciente con tal ID.");
            }

            // Se crea el objeto cita
            cita cita = new cita(id, nombre, fecha, hora, motivo, medico, paciente);

            // Se agrega el objeto cita en la lista citas
            citas.add(cita);

            // Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente la cita en la lista citas.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo guardar la cita en la lista por el error: " + e.getMessage());
        }
    }

    // eliminaCita: eliminará la cita elegida por el usuario de la lista citas
    public static void eliminaCita() throws Exception {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se pide el id de la cita y se busca en la lista
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número de cita que deseas eliminar:"));
            boolean existe = citas.stream().anyMatch(x -> x.getId() == id);
            // Si no existe la cita, se manda que la cita no fue encontrada
            if (existe == false) {
                System.out.println("No existe ninguna cita con dicho ID.");
            }
            // Si sí existe, se elimina y manda mensaje de confirmación
            else {
                // Se elimina la cita
                citas.remove(id - 1);
                // Se confirma la eliminación
                System.out.println("La cita ha sido eliminada exitosamente.");
            }
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo eliminar la cita por el error: " + e.getMessage());
        }
    }

    // buscaMedico: buscará el registro del médico tratante y devuelve nulo si no lo
    // encuentra
    public static medico buscaMedico() {
        // Se crea el objeto medico para guardar los resultados de la búsqueda
        medico medico = null;

        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se pide el id del médico
            int id_medico = Integer.parseInt(JOptionPane.showInputDialog("ID del médico tratante:"));
            // Se crea el objeto metodos_medico para acceder a los métodos de la clase
            // médico
            medico metodos_medico = new medico();
            // Se verifica que el médico exista
            boolean existe_medico = metodos_medico.getMedicos().stream().anyMatch(x -> x.getId() == id_medico);
            // Si existe, envía un mensaje al usuario de que se encontró el médico y guarda
            // los datos en el objeto médico
            if (existe_medico == true) {
                // Se indica que el médico fue encontrado
                System.out.println("Médico encontrado en la lista de medicos.");
                // Se guarda el objeto encontrado en el id del médico en el objeto medico
                medico = metodos_medico.getMedicos().get((id_medico - 1));
            }
            // Si no existe, indicará que el médico no se ha encontrado
            else {
                System.out.println("Médico no encontrado en la lista de medicos.");
            }
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo referenciar al médico por el error: " + e.getMessage());
        }

        // Se regresa el valor del objeto medico
        return medico;
    }

    // buscaPaciente: buscará el registro del paciente y devuelve nulo si no lo
    // encuentra
    public static paciente buscaPaciente() {
        // Se crea el objeto paciente para guardar los resultados de la búsqueda
        paciente paciente = null;

        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se pide el id del paciente
            int id_paciente = Integer.parseInt(JOptionPane.showInputDialog("ID del paciente:"));
            // Se crea el objeto metodos_paciente para acceder a los métodos de la clase
            // paciente
            paciente metodos_paciente = new paciente();
            // Se verifica que el paciente exista
            boolean existe_paciente = metodos_paciente.getPacientes().stream().anyMatch(x -> x.getId() == id_paciente);
            // Si existe, envía un mensaje al usuario de que se encontró el paciente y
            // guarda los datos en el objeto paciente
            if (existe_paciente == true) {
                // Se indica que el paciente fue encontrado
                System.out.println("Paciente encontrado en la lista de pacientes.");
                // Se guarda el objeto encontrado en el id del paciente en el objeto paciente
                paciente = metodos_paciente.getPacientes().get((id_paciente - 1));
            }
            // Si no existe, indicará que no se encontró al paciente
            else {
                System.out.println("Paciente no encontrado en la lista de pacientes.");
            }
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo referenciar al paciente por el error: " + e.getMessage());
        }

        // Se regresa el valor del objeto paciente
        return paciente;
    }

    // guardaCita: guarda los datos de las citas en un archivo JSON
    public static void guardaCita() {
        // Se crea el String llamado jsonCita como variable que guardará el formato
        // JSON.
        String jsonCita;

        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el objeto gson que nos ayudará a pasar el objeto jsonCita a un
            // formato JSON
            Gson gson = new Gson();

            // Se crea el fileWritter para escribir en el archivo
            FileWriter fileWriter = new FileWriter(ARCHIVO);
            // Se crea el printWritter para ir escribiendo en el archivo JSON
            PrintWriter printWriter = new PrintWriter(fileWriter);
            // Se pasan las citas a un formato JSON
            jsonCita = gson.toJson(citas);
            // Se escribe en el archivo JSON
            printWriter.print(jsonCita);

            // Se cierra el printWritter para que los cambios sean guardados
            printWriter.close();

            // Se manda mensaje al usuario de que se guardaron exitosamente las citas
            System.out.println("Las citas han sido guardadas correctamente.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron guardar las citas en el archivo JSON por el error: " + e.getMessage());
        }
    }

    // cargarJSON: leerá y cargará todas las citas que se encuentren en el archivo
    // JSON
    public static void cargarJSON() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el archivo nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);

            // Se crea el lector para el archivo de citas.json
            FileReader reader = new FileReader(file);

            // Se crean las variables para convertir el Json a Array
            JsonParser parser = new JsonParser();
            JsonArray array = (JsonArray) parser.parse(reader);

            // Se indicará al usuario que se mostrarán las citas guardadas en el archivo
            System.out.println("Las citas encontrados en el archivo " + ARCHIVO + " son: ");
            // Se agrega una línea para mejor visibilidad
            System.out.println("");

            // Se usa un ciclo for para desplegar cada cita
            for (Object o : array) {
                // El objeto es convertido a String
                String cadena = o.toString();
                // Se crea el objeto gson para pasar del formato JSON a un objeto Java
                Gson gson = new Gson();
                // Se convierte el objeto
                cita cita = gson.fromJson(cadena, cita.class);
                // Se despliega la información del objeto cita
                cita.despliega();
                // Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());
        }
    }

    // despliega: método que ayudará a mostrar los datos de cada cita
    public void despliega() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se imprimen en pantalla los datos de la cita
            System.out.println("ID de la cita: " + id);
            System.out.println("Nombre de la cita: " + nombre);
            System.out.println("Motivo de la cita: " + motivo);
            System.out.println("Fecha de la cita: " + fecha);
            System.out.println("Hora de la cita: " + hora);
            System.out.println("**** Medico tratante ****");
            // Se llama al método despliega del médico para mostrar sus atributos
            medico.despliega();
            System.out.println("**** Paciente a asistir ****");
            // Se llama al método despliega del paciente para mostrar sus atributos
            paciente.despliega();
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo mostrar la cita por el error: " + e.getMessage());
        }
    }

    // consultaCitas: permite mostrar todos los datos guardados en la lista
    public static void consultaCitas() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se indica al usuario que se mostrarán todas las citas que se han registrado
            System.out.println("Se han registrado las siguientes citas: ");
            // Se recorre la lista citas para mostrarle al usuario cada cita que se ha
            // registrado
            for (cita x : citas) {
                // Se llama al método despliega de la clase cita
                x.despliega();
                // Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
            // Se indica al usuario que se han terminado de desplegar todas las citas
            System.out.println("Se han terminado de mostrar todas las citas registradas.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron desplegar las citas por el error: " + e.getMessage());
        }
    }

    // buscaCita: buscará y mostrará las citas relacionadas con el ID del paciente o
    // del médico
    public static void buscaCita(int id, String usuario) {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se agrega un título para las citas obtenidas
            System.out.println("Se encontraron las siguientes citas para el " + usuario.toLowerCase() + " #" + id);
            // Se usa un switch ... case para elegir el caso de acuerdo con la opción del
            // usuario
            switch (usuario) {
                case "Paciente": {
                    // Se buscan las citas que correspondan al paciente y se guarda en una lista
                    List<cita> citasPaciente = citas.stream().filter(paciente -> paciente.getId() == id)
                            .collect(Collectors.toList());
                    // Se recorre la lista citasPaciente para mostrarle al usuario cada cita que
                    // tiene
                    for (cita x : citasPaciente) {
                        // Se llama al método despliega de la clase cita
                        x.despliega();
                        // Se agrega una línea para mejor visibilidad
                        System.out.println("");
                    }
                    // Se termina el switch
                    break;
                }

                case "Médico": {
                    // Se buscan las citas que correspondan al médico y se guarda en una lista
                    List<cita> citasMedico = citas.stream().filter(medico -> medico.getId() == id)
                            .collect(Collectors.toList());
                    // Se recorre la lista citasMedico para mostrarle al usuario cada cita que tiene
                    for (cita x : citasMedico) {
                        // Se llama al método despliega de la clase cita
                        x.despliega();
                        // Se agrega una línea para mejor visibilidad
                        System.out.println("");
                    }
                    // Se termina el switch
                    break;
                }
            }

            // Se indica al usuario que se han terminado de desplegar todas las citas
            System.out.println("Se han terminado de mostrar las citas del " + usuario.toLowerCase() +
                    " #" + id + ".");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron encontrar las citas por el error: " + e.getMessage());
        }
    }
}
