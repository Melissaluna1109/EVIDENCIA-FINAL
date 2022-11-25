
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class receta {

    private int id;
    private String fecha;
    private paciente paciente;
    private medico medico;
    private String medicamento;
    private String presentacion;
    private String dosis;
    private boolean sello;
    private boolean firma;

    // Lista donde se guardarán todos las recetas
    private static List<receta> recetas = new ArrayList<>();

    // Archivo donde se guardarán todas las recetas
    private static String ARCHIVO = "recetas.json";

    // Constructor

    public receta(int id, String fecha, paciente paciente, medico medico, String medicamento, String presentacion,
            String dosis, boolean sello, boolean firma) {
        this.id = id;
        this.fecha = fecha;
        this.paciente = paciente;
        this.medico = medico;
        this.medicamento = medicamento;
        this.presentacion = presentacion;
        this.dosis = dosis;
        this.sello = sello;
        this.firma = firma;
    }

    // Constructor vacío para su uso en algunos métodos de la clase main
    public receta() {
    }

    // Métodos Get y Set para atributos privados
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(paciente paciente) {
        this.paciente = paciente;
    }

    public medico getMedico() {
        return medico;
    }

    public void setMedico(medico medico) {
        this.medico = medico;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public static List<receta> getRecetas() {
        return recetas;
    }

    public static void setRecetas(List<receta> recetas) {
        receta.recetas = recetas;
    }

    public boolean isSello() {
        return sello;
    }

    public void setSello(boolean sello) {
        this.sello = sello;
    }

    public boolean isFirma() {
        return firma;
    }

    public void setFirma(boolean firma) {
        this.firma = firma;
    }

    // Métodos propios de la clase

    // agregaDatosIniciales: añadirá un dato semilla para acceder a las funciones
    public static void agregaDatosIniciales() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el archivo nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);
            // Se crea el médico con los datos semilla para crear la receta semilla
            medico medico = new medico("General", 1, "Melissa", "Luna", 32, 'F', "1234", "melissa@outlook.es");
            // Se crea el paciente con los datos semilla para crear la receta semilla
            paciente paciente = new paciente("Gripa", medico, 1, "Carlos", "Villareal", 30, 'M', "1234",
                    "carlos@outlook.es");
            // Se crea la receta con los datos semilla
            receta semilla = new receta(1, LocalDate.now().toString(), paciente, medico, "Next", "Tabletas",
                    "1 por día", true, true);

            // Si no existe el archivo, se agrega la receta semilla para comenzar con el
            // programa
            if (file.canExecute() == false) {
                recetas.add(semilla);
            }

            // Si sí existe, se leen las líneas contenidas en el archivo
            else {
                // Se crea el lector para el archivo de recetas.json
                FileReader reader = new FileReader(file);

                // Se crean las variables para convertir el Json a Array
                JsonParser parser = new JsonParser();
                JsonArray array = (JsonArray) parser.parse(reader);

                // Se utiliza un ciclo for para agregar cada receta en la lista recetas
                for (Object o : array) {
                    // Se convierte el objeto a String
                    String cadena = o.toString();
                    // Se crea el objeto gson para pasar del formato JSON a un objeto Java
                    Gson gson = new Gson();
                    // Se convierte el objeto
                    receta receta = gson.fromJson(cadena, receta.class);
                    // Se agrega la receta en la lista recetas
                    recetas.add(receta);
                }

                // Si la lista está vacía, entonces se agrega el dato semilla para comenzar el
                // programa
                if (recetas.isEmpty()) {
                    recetas.add(semilla);
                }
            }

            System.out.println("Las recetas iniciales han sido guardadas.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println(
                    "No se pudieron guardar las recetas semilla correctamente por el error: " + e.getMessage());
        }
    }

    // creaReceta: guarda una nueva receta
    public void creaReceta() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se aumenta un id dependiendo de la cantidad de recetas guardadas en la lista
            // recetas
            int id = recetas.size() + 1;
            // Se guarda la fecha actual para la receta
            String fecha = LocalDate.now().toString();
            // Se crea el objeto paciente llamando al método buscaPaciente
            paciente paciente = cita.buscaPaciente();
            // Si está nulo, manda un mensaje de error
            if (paciente == null) {
                System.out.println("No existe ningún paciente con tal ID");
            }
            // Se crea el objeto medico llamando al método buscaMedico
            medico medico = cita.buscaMedico();
            // Si está nulo, manda un mensaje de error
            if (medico == null) {
                System.out.println("No existe ningún médico con tal ID");
            }
            // Se guardan los datos del medicamento
            String medicamento = JOptionPane.showInputDialog("Ingresa el nombre del medicamento: ");
            String presentacion = JOptionPane.showInputDialog("Ingresa la presentación del medicamento: ");
            String dosis = JOptionPane.showInputDialog("Ingresa la dosis del medicamento: ");
            // Se guardan los valores false para el sello y firma de la receta
            boolean sello = false;
            boolean firma = false;

            // Se crea el objeto receta
            receta receta = new receta(id, fecha, paciente, medico, medicamento, presentacion, dosis, sello, firma);

            // Se agrega el objeto receta en la lista recetas
            recetas.add(receta);

            // Se regresa un mensaje en consola indicando el término del método
            System.out.println("Se ha guardado correctamente la receta en la lista recetas.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo guardar la receta en la lista por el error: " + e.getMessage());
        }
    }

    // eliminaReceta: eliminará la receta elegida por el usuario de la lista recetas
    public void eliminaReceta() throws Exception {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se pide el id de la receta y se busca en la lista
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el número de receta que deseas eliminar:"));
            boolean existe = recetas.stream().anyMatch(x -> x.getId() == id);
            // Si no existe la receta, se manda que la receta no fue encontrada
            if (existe == false) {
                System.out.println("No existe ninguna receta con dicho ID.");
            }
            // Si sí existe, se elimina y manda mensaje de confirmación
            else {
                // Se elimina la receta
                recetas.remove(id - 1);
                // Se confirma la eliminación
                System.out.println("La receta ha sido eliminada exitosamente.");
            }
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo eliminar la receta por el error: " + e.getMessage());
        }
    }

    // guardaReceta: guarda los datos de las recetas en un archivo JSON
    public static void guardaReceta() {
        // Se crea el String llamado jsonReceta como variable que guardará el formato
        // JSON.
        String jsonReceta;

        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el objeto gson que nos ayudará a pasar el objeto jsonReceta a un
            // formato JSON
            Gson gson = new Gson();

            // Se crea el fileWritter para escribir en el archivo
            FileWriter fileWriter = new FileWriter(ARCHIVO);
            // Se crea el printWritter para ir escribiendo en el archivo JSON
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Se pasan las recetas a un formato JSON
            jsonReceta = gson.toJson(recetas);
            // Se escribe en el archivo JSON
            printWriter.print(jsonReceta);

            // Se cierra el printWritter para que los cambios sean guardados
            printWriter.close();

            // Se manda mensaje al usuario para que pueda ver el guardado exitoso de las
            // recetas
            System.out.println("Las recetas han sido guardadas correctamente.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron guardar las recetas en el archivo JSON por el error: " + e.getMessage());
        }
    }

    // cargarJSON: leerá y cargará todas las recetas que se encuentren en el archivo
    // JSON
    public void cargarJSON() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se crea el archivo nombrado con la constante ARCHIVO
            File file = new File(ARCHIVO);

            // Se crea el lector para el archivo de recetas.json
            FileReader reader = new FileReader(file);

            // Se crean las variables para convertir el Json a Array
            JsonParser parser = new JsonParser();
            JsonArray array = (JsonArray) parser.parse(reader);

            // Se indicará al usuario que se mostrarán las recetas guardadas en el archivo
            System.out.println("Las recetas encontradas en el archivo " + ARCHIVO + " son: ");
            // Se agrega una línea para mejor visibilidad
            System.out.println("");

            // Se utiliza un ciclo for para desplegar cada receta
            for (Object o : array) {
                // Se convierte el objeto a String
                String cadena = o.toString();
                // Se crea el objeto gson para pasar del formato JSON a un objeto Java
                Gson gson = new Gson();
                // Se convierte el objeto
                receta receta = gson.fromJson(cadena, receta.class);
                // Se despliega la información de la receta
                receta.despliega();
                // Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron cargar correctamente los datos por el error: " + e.getMessage());
        }
    }

    // despliega: método que ayudará a mostrar los datos de cada receta
    public void despliega() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            System.out.println("ID de la receta: " + id);
            System.out.println("Fecha de la receta: " + fecha);
            System.out.println("**** Paciente de la receta ****");
            paciente.despliega();
            System.out.println("**** Medico tratante ****");
            medico.despliega();
            System.out.println("Mecicamento: " + medicamento);
            System.out.println("Presentación del medicamento: " + presentacion);
            System.out.println("Dosis del medicamento: " + dosis);
            System.out.println("Sello: " + sello);
            System.out.println("Firma: " + sello);
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo mostrar la receta por el error: " + e.getMessage());
        }
    }

    // consultaRecetas: permite mostrar todos los datos guardados en la lista
    public void consultaRecetas() {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se indica al usuario que se mostrarán todas las recetas que se han registrado
            System.out.println("Se han registrado las siguientes recetas: ");
            // Se recorre la lista recetas para mostrarle al usuario cada receta que se ha
            // registrado
            for (receta x : recetas) {
                // Se llama al método despliega de la clase receta
                x.despliega();
                // Se agrega una línea para mejor visibilidad
                System.out.println("");
            }
            // Se indica al usuario que se han terminado de desplegar todas las recetas
            System.out.println("Se han terminado de mostrar todas las recetas registradas.");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron desplegar las recetas por el error: " + e.getMessage());
        }
    }

    // buscaReceta: buscará y mostrará las recetas relacionadas con el ID del
    // paciente o del médico
    public void buscaReceta(int id, String usuario) {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se agrega un título para las recetas obtenidas
            System.out.println("Se encontraron las siguientes recetas para el " + usuario.toLowerCase() + " #" + id);
            // Se usa un switch ... case para elegir el caso de acuerdo con la opción del
            // usuario
            switch (usuario) {
                case "Paciente": {
                    // Se buscan las recetas que correspondan al paciente y se guarda en una lista
                    List<receta> recetasPaciente = recetas.stream().filter(paciente -> paciente.getId() == id)
                            .collect(Collectors.toList());
                    // Se recorre la lista recetasPaciente para mostrarle al usuario cada receta que
                    // tiene
                    for (receta x : recetasPaciente) {
                        // Se llama al método despliega de la clase receta
                        x.despliega();
                        // Se agrega una línea para mejor visibilidad
                        System.out.println("");
                    }
                    // Se termina el switch
                    break;
                }

                case "Médico": {
                    // Se buscan las recetas que correspondan al médico y se guarda en una lista
                    List<receta> recetasMedico = recetas.stream().filter(medico -> medico.getId() == id)
                            .collect(Collectors.toList());
                    // Se recorre la lista recetasMedico para mostrarle al usuario cada receta que
                    // tiene
                    for (receta x : recetasMedico) {
                        // Se llama al método despliega de la clase receta
                        x.despliega();
                        // Se agrega una línea para mejor visibilidad
                        System.out.println("");
                    }
                    // Se termina el switch
                    break;
                }
            }

            // Se indica al usuario que se han terminado de desplegar todas las recetas
            System.out.println("Se han terminado de mostrar las recetas del " + usuario.toLowerCase() +
                    " #" + id + ".");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudieron encontrar las recetas por el error: " + e.getMessage());
        }
    }

    // sellarReceta: cambia el boolean sello, indicando que la receta ha sido
    // sellada
    public static void sellarReceta() throws Exception {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Pregunta al usuario el número de receta a sellar
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id de la receta a sellar:"));
            // Se busca la receta en la lista recetas
            boolean existe = recetas.stream().anyMatch(x -> x.getId() == id);
            // Si no existe la receta, se manda un mensaje de error
            if (existe == false) {
                System.out.println("No existe una receta con dicho ID.");
            }
            // Se le indica al usuario que se sellará la receta
            System.out.println("Sellando receta ... ");
            // Se cambia el sello de false a true
            recetas.get(id - 1).setSello(true);
            // Se le indica al usuario que la receta fue sellada
            System.out.println("La receta ha sido sellada: " + recetas.get(id - 1).isSello());
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo sellar la receta por el error: " + e.getMessage());
        }
    }

    // firmarReceta: cambiará el boolean firma, que indica que la receta ha sido
    // firmada
    public void firmarReceta() throws Exception {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Pregunta al usuario el número de receta a firmar
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id de la receta a firmar:"));
            // Se busca la receta en la lista recetas
            boolean existe = recetas.stream().anyMatch(x -> x.getId() == id);
            // Si no existe la receta, se manda un mensaje de error
            if (existe == false) {
                System.out.println("No existe una receta con dicho ID.");
            }
            // Se le indica al usuario que se firmará la receta
            System.out.println("Firmando receta ... ");
            // Se cambia la firma de false a true
            recetas.get(id - 1).setFirma(true);
            // Se le indica al usuario que la receta fue firmada
            System.out.println("La receta ha sido firmada: " + recetas.get(id - 1).isFirma());
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo firmar la receta por el error: " + e.getMessage());
        }
    }

    // surtirReceta: regresará el medicamento, su presentación y dosis, además de un
    // boolean indicando que se terminó el proceso
    public void surtirReceta() throws Exception {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Pregunta al usuario el número de receta a surtir
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el id de la receta a surtir:"));
            // Se busca la receta en la lista recetas
            boolean existe = recetas.stream().anyMatch(x -> x.getId() == id);
            // Si no existe la receta, se manda un mensaje de error
            if (existe == false) {
                System.out.println("No existe una receta con dicho ID.");
            }
            // Se busca si la receta está sellada y firmada
            boolean cumple = recetas.get(id - 1).isSello() == true && recetas.get(id - 1).isFirma() == true;
            // Si la receta está firmada y sellada, se continúa
            if (cumple) {
                // Se indica que se surtirá la receta
                System.out.println("Surtiendo la receta #" + id);
                // Se muestran los datos del medicamento
                System.out.println("Medicamento: " + recetas.get(id - 1).getMedicamento());
                System.out.println("Presentación: " + recetas.get(id - 1).getPresentacion());
                System.out.println("Recuerda tomarlo: " + recetas.get(id - 1).getDosis());
                // Se crea un boolean para indicar que el surtido fue exitoso
                boolean surtido = true;
                // Se indica al usuario el resultado del surtido de la receta
                System.out.println("Receta surtida: " + surtido);
            }
            // Si no, se lanza un mensaje de error
            else {
                System.out.println("La receta no tiene sello y/o firma.");
            }
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            System.out.println("No se pudo surtir la receta por el error: " + e.getMessage());
        }
    }
}
