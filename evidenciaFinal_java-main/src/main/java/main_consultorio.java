
//Se importan librerías necesarias para el funcionamiento de la clase
import javax.swing.*;

public class main_consultorio {
    // Método main
    public static void main(String[] args) throws Exception {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Variables necesarias para el funcionamiento del método
            // id_usuario: guardará el id del usuario para acceder al menú
            int id_usuario;
            // contraseña: guardará la contraseña del usuario para acceder al menú
            String contraseña;
            // intentos: le dará al usuario tres intentos para ingresar al menú
            int intentos = 3;
            // credenciales: ayudará a terminar el ciclo cuando las credenciales hayan sido
            // válidas
            boolean credenciales = false;
            // opciones: guardará los tres tipos de usuarios que pueden acceder al sistema
            String[] usuarios = { "Persona", "Médico", "Paciente" };
            // opcionElegida: le pide el tipo de usuario que desea acceder
            String tipoUsuario;
            // Objetos necesarios para acceder a los métodos de las clases
            paciente paciente = new paciente();
            medico medico = new medico();
            persona persona = new persona();
            cita cita = new cita();
            receta receta = new receta();

            // Acciones del método main

            // Bienvenida al usuario
            System.out.println(" BIENVENIDO AL CONSULTORIO ");
            System.out.println("");
            System.out.println("AUTOR DEBANHI MELISSA LUNA");
            System.out.println("");

            // Se agregan datos iniciales para cada clase
            persona.agregaDatosIniciales();
            paciente.agregaDatosIniciales();
            medico.agregaDatosIniciales();
            cita.agregaDatosIniciales();
            receta.agregaDatosIniciales();

            // Se agrega una línea para mejor visibilidad
            System.out.println("");

            do {
                // Se piden las credenciales del usuario y la contraseña
                id_usuario = Integer.parseInt(JOptionPane.showInputDialog("Por favor, escribe tu id de usuario:"));
                contraseña = JOptionPane.showInputDialog("Por favor, escribe tu contraseña:");

                tipoUsuario = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                        + "tu tipo de usuario", "Ingreso", JOptionPane.DEFAULT_OPTION,
                        null, usuarios, usuarios[0]);

                // Se manda mensaje en consola con el el tipo de usuario que desea ingresar
                System.out.println("Eres: " + tipoUsuario);

                // Se usa un switch ... case para elegir el caso de acuerdo con la opción del
                // usuario
                switch (tipoUsuario) {
                    case "Persona": // Es un usuario privilegiado
                    {
                        credenciales = persona.ingresar(id_usuario, contraseña);
                    }

                    case "Médico": // Es un médico
                    {
                        credenciales = medico.ingresar(id_usuario, contraseña);
                    }

                    case "Paciente": // Es un paciente
                    {
                        credenciales = paciente.ingresar(id_usuario, contraseña);
                    }
                }

                // Si es incorrecto, se restará un intento y se le notificará al usuario que no
                // se ha encontrado el registro
                if (credenciales == false) {
                    // Primero se restará el intento
                    intentos -= 1;
                    // Luego se le notifica al usuario del error
                    JOptionPane.showMessageDialog(null, "Ingresa nuevamente tu id y contraseña",
                            "Registro no encontrado", JOptionPane.ERROR_MESSAGE);
                }
            }
            // Si no se acabó los intentos y llenó el campo, se finaliza el ciclo
            while (intentos != 0 && credenciales != true);

            // Si el usuario se acabó sus intentos disponibles, lanza una excepción
            if (intentos == 0) {
                throw new Exception("Se han terminado los intentos disponibles");
            }

            // Muestra al usuario el menú de opciones
            menu(id_usuario, tipoUsuario, persona, medico, paciente, cita, receta);

            // Se agrega una línea para mejor visibilidad
            System.out.println("");

            // Despedida al usuario por correcta ejecución del programa
            System.out.println("El programa se ha terminado con éxito. Nos vemos pronto :)\n");
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            // Muestra al usuario que ha ocurrido un error y que se terminará el programa
            JOptionPane.showMessageDialog(null,
                    "Ha ocurrido el error: " + e.getMessage() + "," + "\nFinalizando programa...",
                    "El programa será finalizado", JOptionPane.ERROR_MESSAGE);
            // Se muestra en consola el error que causó la finalización del programa
            System.out.println("Error: " + e.getMessage());
            // Se muestra en consola que el programa ha concluido
            System.out.println("\nPrograma finalizado. Ejecuta nuevamente el programa "
                    + "y vuelve a intentarlo.\n");
        }
    }

    public static void menu(int id, String tipoUsuario, persona persona, medico medico, paciente paciente, cita cita,
            receta receta) {
        // Se especifica el manejo de excepciones try ... catch
        // Se intenta la ejecución de las siguientes instrucciones
        try {
            // Se declaran las variables que se usarán en el método
            // salir: es la variable que ayudará a finalizar el ciclo del menú
            int salir = 0;

            // Se comienza el ciclo while hasta que la opción de salir sea 1
            while (salir == 0) {
                // Se usa un switch ... case para elegir el caso de acuerdo con la opción del
                // usuario
                switch (tipoUsuario) {
                    case "Persona": // Es un usuario privilegiado
                    {
                        // opciones: guardará las opciones disponibles para elegir entre las funciones
                        String[] opciones = { "Crear nuevo usuario", "Eliminar usuario", "Cargar usuarios JSON",
                                "Visualizar usuarios creados", "Enviar un mensaje", "Crear nuevo paciente",
                                "Eliminar paciente",
                                "Cargar pacientes JSON", "Visualizar pacientes creados", "Crear nuevo médico",
                                "Eliminar médico",
                                "Cargar médicos JSON", "Visualizar médicos creados", "Crear nueva cita",
                                "Eliminar cita",
                                "Cargar citas JSON", "Visualizar citas creadas", "Crear nueva receta",
                                "Eliminar receta",
                                "Cargar recetas JSON", "Visualizar recetas creadas", "Sellar receta", "Salir" };
                        // opcionElegida: le pide al usuario la opción de la función a realizar
                        String opcionElegida;

                        // Se le pide al usuario la opción que desea realizar
                        opcionElegida = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                                + "la opción que deseas", "Menú principal", JOptionPane.DEFAULT_OPTION,
                                null, opciones, opciones[0]);

                        // Se manda mensaje en consola con la opción que eligió
                        System.out.println("Has elegido: " + opcionElegida);

                        // Se usa un switch ... case para elegir el caso de acuerdo con la opción del
                        // usuario
                        switch (opcionElegida) {
                            case "Crear nuevo usuario": // Se crea un nuevo usuario
                            {
                                // Se llama al método de creaPersona de la clase persona
                                persona.creaPersona();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Eliminar usuario": // Se eliminará un usuario
                            {
                                // Se llama al método eliminaPersona de la clase persona
                                persona.eliminaPersona();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Cargar usuarios JSON": // Se carga el archivo personas.JSON
                            {
                                // Se llama al método de cargarJSON de la clase persona
                                persona.cargarJSON();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar usuarios creados": // Se cargarán los usuarios guardados en la lista
                                                                // personas
                            {
                                // Se llama al método de consultaUsuarios de la clase persona
                                persona.consultaUsuarios();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Enviar un mensaje": // Se creará un mensaje
                            {
                                // Se llama al método de enviaMensaje de la clase persona
                                persona.enviaMensaje();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Crear nuevo paciente": // Se crea un nuevo paciente
                            {
                                // Se llama al método creaPersona de la clase paciente
                                paciente.creaPersona();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Eliminar paciente": // Se eliminará un paciente
                            {
                                // Se llama al método eliminaPersona de la clase paciente
                                paciente.eliminaPersona();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Cargar pacientes JSON": // Se carga el archivo pacientes.JSON
                            {
                                // Se llama al método cargarJSON de la clase paciente
                                paciente.cargarJSON();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar pacientes creados": // Se cargarán los pacientes guardados en la lista
                                                                 // pacientes
                            {
                                // Se llama al método de consultaUsuarios de la clase paciente
                                paciente.consultaUsuarios();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Crear nuevo médico": // Se crea un nuevo médico
                            {
                                // Se llama al método creaPersona de la clase medico
                                medico.creaPersona();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Eliminar médico": // Se eliminará un médico
                            {
                                // Se llama al método eliminaPersona de la clase medico
                                medico.eliminaPersona();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Cargar médicos JSON": // Se carga el archivo medicos.JSON
                            {
                                // Se llama al método cargarJSON de la clase medico
                                medico.cargarJSON();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar médicos creados": // Se cargarán los médicos guardados en la lista medicos
                            {
                                // Se llama al método de consultaUsuarios de la clase medico
                                medico.consultaUsuarios();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Crear nueva cita": // Se creará una nueva cita
                            {
                                // Se llama al método creaCita de la clase cita
                                cita.creaCita();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Eliminar cita": // Se eliminará una cita
                            {
                                // Se llama al método eliminaCita de la clase cita
                                cita.eliminaCita();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Cargar citas JSON": // Se carga el archivo citas.JSON
                            {
                                // Se llama al método cargarJSON de la clase cita
                                cita.cargarJSON();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar citas creadas": // Se cargarán las citas guardadas en la lista citas
                            {
                                // Se llama al método de consultaCitas de la clase cita
                                cita.consultaCitas();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Crear nueva receta": // Se creará una nueva receta
                            {
                                // Se llama al método creaReceta de la clase receta
                                receta.creaReceta();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Eliminar receta": // Se eliminará una receta
                            {
                                // Se llama al método eliminaReceta de la clase receta
                                receta.eliminaReceta();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Cargar recetas JSON": // Se carga el archivo recetas.JSON
                            {
                                // Se llama al método cargarJSON de la clase receta
                                receta.cargarJSON();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar recetas creadas": // Se cargarán las recetas guardadas en la lista recetas
                            {
                                // Se llama al método consultaRecetas de la clase receta
                                receta.consultaRecetas();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Sellar receta": // Sellará la receta
                            {
                                // Se llama al método sellarReceta de la clase receta
                                receta.sellarReceta();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Salir": // Se eligió salir del menú
                            {
                                // Se cambia el valor de salir para que finalice el ciclo
                                salir = 1;
                                // Se termina el switch
                                break;
                            }
                        }

                        // Se termina el switch
                        break;
                    }

                    case "Médico": // Es un médico
                    {
                        // opciones: guardará las opciones disponibles para elegir entre las funciones
                        String[] opciones = { "Enviar un mensaje", "Crear nuevo paciente", "Eliminar paciente",
                                "Cargar pacientes JSON",
                                "Visualizar pacientes creados", "Visualizar pacientes asignados", "Consultar paciente",
                                "Crear nueva cita",
                                "Eliminar cita", "Cargar citas JSON", "Visualizar citas creadas",
                                "Visualizar citas asignadas", "Crear nueva receta",
                                "Eliminar receta", "Cargar recetas JSON", "Visualizar recetas creadas",
                                "Visualizar recetas expedidas",
                                "Firmar receta", "Salir" };
                        // opcionElegida: le pide al usuario la opción de la función a realizar
                        String opcionElegida;

                        // Se le pide al usuario la opción que desea realizar
                        opcionElegida = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                                + "la opción que deseas", "Menú principal", JOptionPane.DEFAULT_OPTION,
                                null, opciones, opciones[0]);

                        // Se usa un switch ... case para elegir el caso de acuerdo con la opción del
                        // usuario
                        switch (opcionElegida) {
                            case "Enviar un mensaje": // Se creará un mensaje
                            {
                                // Se llama al método de enviaMensaje de la clase persona
                                persona.enviaMensaje();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Crear nuevo paciente": // Se crea un nuevo paciente
                            {
                                // Se llama al método creaPersona de la clase paciente
                                paciente.creaPersona();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Eliminar paciente": // Se eliminará un paciente
                            {
                                // Se llama al método eliminaPersona de la clase paciente
                                paciente.eliminaPersona();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Cargar pacientes JSON": // Se carga el archivo pacientes.JSON
                            {
                                // Se llama al método cargarJSON de la clase paciente
                                paciente.cargarJSON();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar pacientes creados": // Se cargarán los pacientes guardados en la lista
                                                                 // pacientes
                            {
                                // Se llama al método de consultaUsuarios de la clase paciente
                                paciente.consultaUsuarios();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar pacientes asignados": // Se visualizarán los pacientes asignados al médico
                            {
                                // Se llama al método buscaPacientes de la clase medico
                                medico.buscaPacientes(id);
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Consultar paciente": // Cambiará el diagnóstico del paciente
                            {
                                // Se llama al método buscaPaciente de la clase cita y se guarda en un objeto de
                                // tipo paciente
                                paciente pacienteConsulta = cita.buscaPaciente();
                                // Se llama al método consultaPaciente de la clase medico
                                medico.consultaPaciente(pacienteConsulta);
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Crear nueva cita": // Se creará una nueva cita
                            {
                                // Se llama al método creaCita de la clase cita
                                cita.creaCita();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Eliminar cita": // Se eliminará una cita
                            {
                                // Se llama al método eliminaCita de la clase cita
                                cita.eliminaCita();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Cargar citas JSON": // Se carga el archivo citas.JSON
                            {
                                // Se llama al método cargarJSON de la clase cita
                                cita.cargarJSON();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar citas creadas": // Se cargarán las citas guardadas en la lista citas
                            {
                                // Se llama al método de consultaCitas de la clase cita
                                cita.consultaCitas();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar citas asignadas": // Se visualizarán las citas asignadas al médico
                            {
                                // Se llama al método buscaCita de la clase cita
                                cita.buscaCita(id, tipoUsuario);
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Crear nueva receta": // Se creará una nueva receta
                            {
                                // Se llama al método creaReceta de la clase receta
                                receta.creaReceta();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Eliminar receta": // Se eliminará una receta
                            {
                                // Se llama al método eliminaReceta de la clase receta
                                receta.eliminaReceta();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Cargar recetas JSON": // Se carga el archivo recetas.JSON
                            {
                                // Se llama al método cargarJSON de la clase receta
                                receta.cargarJSON();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar recetas creadas": // Se cargarán las recetas guardadas en la lista recetas
                            {
                                // Se llama al método consultaRecetas de la clase receta
                                receta.consultaRecetas();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar recetas expedidas": // Se visualizarán las recetas relacionadas con el
                                                                 // médico
                            {
                                // Se llama al método buscaReceta de la clase receta
                                receta.buscaReceta(id, tipoUsuario);
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Firmar receta": // Se firma la receta
                            {
                                // Se llama al método firmarReceta de la clase receta
                                receta.firmarReceta();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Salir": // Se eligió salir del menú
                            {
                                // Se cambia el valor de salir para que finalice el ciclo
                                salir = 1;
                                // Se termina el switch
                                break;
                            }
                        }

                        // Se termina el switch
                        break;
                    }

                    case "Paciente": // Es un paciente
                    {
                        // opciones: guardará las opciones disponibles para elegir entre las funciones
                        String[] opciones = { "Enviar un mensaje", "Asistir a cita", "Visualizar recetas obtenidas",
                                "Surtir receta", "Salir" };
                        // opcionElegida: le pide al usuario la opción de la función a realizar
                        String opcionElegida;

                        // Se le pide al usuario la opción que desea realizar
                        opcionElegida = (String) JOptionPane.showInputDialog(null, "Por favor, selecciona "
                                + "la opción que deseas", "Menú principal", JOptionPane.DEFAULT_OPTION,
                                null, opciones, opciones[0]);

                        // Se usa un switch ... case para elegir el caso de acuerdo con la opción del
                        // usuario
                        switch (opcionElegida) {
                            case "Enviar un mensaje": // Se creará un mensaje
                            {
                                // Se llama al método de enviaMensaje de la clase persona
                                persona.enviaMensaje();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Asistir a cita": // El paciente asistirá una cita
                            {
                                // Se llama al método asistirCita de la clase paciente
                                paciente.asistirCita(id);
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Visualizar recetas obtenidas": // Se visualizarán las recetas relacionadas con el
                                                                 // paciente
                            {
                                // Se llama al método buscaReceta de la clase receta
                                receta.buscaReceta(id, tipoUsuario);
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Surtir receta": // Se surtirá la receta siempre y cuando cumpla los requisitos de
                                                  // sello y firma
                            {
                                // Se llama al método surtirReceta de la clase receta
                                receta.surtirReceta();
                                // Se agrega una línea para mejor visibilidad
                                System.out.println("");
                                // Se termina el switch
                                break;
                            }

                            case "Salir": // Se eligió salir del menú
                            {
                                // Se cambia el valor de salir para que finalice el ciclo
                                salir = 1;
                                // Se termina el switch
                                break;
                            }
                        }
                    }

                        // Se termina el switch
                        break;
                }
            }
            // Si salir es igual a 1 se termina el ciclo
            // Se indica que se va a salir del menú
            System.out.println("Saliendo del menú...");
            // Se agrega una línea para mejor visibilidad
            System.out.println("");
            // Se guardan los objetos creados en los respectivos archivos JSON
            persona.guardaPersona();
            paciente.guardaPersona();
            medico.guardaPersona();
            cita.guardaCita();
            receta.guardaReceta();
            // Se agrega una línea para mejor visibilidad
            System.out.println("");
            // Se muestran todos los objetos guardados en los respectivos archivo JSON
            // Objetos persona
            persona.cargarJSON();
            // Se agrega una línea para mejor visibilidad
            System.out.println("");
            // Objetos paciente
            paciente.cargarJSON();
            // Se agrega una línea para mejor visibilidad
            System.out.println("");
            // Objetos médico
            medico.cargarJSON();
            // Se agrega una línea para mejor visibilidad
            System.out.println("");
            // Objetos cita
            cita.cargarJSON();
            // Se agrega una línea para mejor visibilidad
            System.out.println("");
            // Objetos receta
            receta.cargarJSON();

            // Se termina el menú
        }
        // Capta cualquier excepción que surja durante la ejecución
        catch (Exception e) {
            e.getMessage();
        }
    }
}
