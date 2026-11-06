import java.util.Scanner;
import Modelo.*;
import Estructuras.*;
import Excepciones.EstudianteNoEncontradoException;
import Excepciones.MateriaNoEncontradaException;
import Excepciones.PrerrequisitoNoCumplidoException;

public class Principal {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        GestionarEstudiantes gestionarEstudiantes = new GestionarEstudiantes();
        GestionarMaterias gestionarMaterias = new GestionarMaterias();
        GestionarSalones gestionarSalones = new GestionarSalones();
        GestionarHorarios gestionarHorarios = new GestionarHorarios();
        GestionarEdificios gestionarEdificios = new GestionarEdificios(10);
        GestionarFacultades gestionarFacultades = new GestionarFacultades();
        GestionarSolicitudes gestionarSolicitudes = new GestionarSolicitudes();
        GestionarOperaciones gestionarOperaciones = new GestionarOperaciones();
        GestionarNavegacionReportes gestionarNavegacionReportes = new GestionarNavegacionReportes();
        int opcion;

        do {
            System.out.println("\n==================================");
            System.out.println(" SISTEMA DE GESTION ACADEMICA");
            System.out.println("==================================");
            System.out.println("1. Gestionar estudiantes");
            System.out.println("2. Gestionar materias");
            System.out.println("3. Gestionar salones");
            System.out.println("4. Gestionar horarios");
            System.out.println("5. Gestionar edificios y rutas");
            System.out.println("6. Gestionar facultades");
            System.out.println("7. Gestionar solicitudes");
            System.out.println("8. Deshacer operacion");
            System.out.println("9. Rehacer operacion");
            System.out.println("10. Navegacion de reportes");
            System.out.println("0. Salir");
            System.out.println("==================================");
            System.out.print("Seleccione una opcion: ");

            opcion = teclado.nextInt();

            switch (opcion) {
                // opcion
                // 1------------------------------------------------------------------------
                case 1:
                    int opcionEstudiante;

                    do {
                        System.out.println("\n===== GESTION DE ESTUDIANTES =====");
                        System.out.println("1. Registrar estudiante");
                        System.out.println("2. Buscar estudiante");
                        System.out.println("3. Mostrar todos los estudiantes");
                        System.out.println("4. Eliminar estudiante");
                        System.out.println("5. Registrar notas");
                        System.out.println("0. Volver al menu principal");
                        System.out.print("Seleccione una opcion: ");

                        opcionEstudiante = teclado.nextInt();
                        teclado.nextLine();

                        switch (opcionEstudiante) {

                            case 1:

                                System.out.print("Ingrese el nombre: ");
                                String nombre = teclado.nextLine();

                                System.out.print("Ingrese el ID: ");
                                String id = teclado.nextLine();

                                System.out.print("Ingrese el email: ");
                                String email = teclado.nextLine();

                                System.out.print("Ingrese el semestre: ");
                                int semestre = teclado.nextInt();
                                teclado.nextLine();

                                Estudiante estudiante = new Estudiante(
                                        nombre, id, email, semestre);

                                boolean estudianteExiste = false;

                                try {
                                    gestionarEstudiantes.buscarEstudiante(id);
                                    estudianteExiste = true;

                                } catch (EstudianteNoEncontradoException e) {
                                    estudianteExiste = false;
                                }

                                if (estudianteExiste) {
                                    System.out.println("Ya existe un estudiante con esa identificacion.");

                                } else {
                                    gestionarEstudiantes.agregarEstudiante(estudiante);

                                    Operacion operacion = new Operacion(
                                            "ESTUDIANTE",
                                            "Registro del estudiante " + nombre,
                                            estudiante,

                                            () -> gestionarEstudiantes.eliminarEstudiantes(id),

                                            () -> gestionarEstudiantes.agregarEstudiante(estudiante));

                                    gestionarOperaciones.registrarOperacion(operacion);
                                }
                                break;

                            case 2:

                                System.out.print("Ingrese el ID del estudiante: ");
                                String idBuscar = teclado.nextLine();

                                try {
                                    Estudiante estudianteEncontrado = gestionarEstudiantes.buscarEstudiante(idBuscar);

                                    estudianteEncontrado.mostrarInformacion();

                                } catch (EstudianteNoEncontradoException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }

                                break;

                            case 3:

                                gestionarEstudiantes.mostrarEstudiantes();

                                break;

                            case 4:

                                System.out.print("Ingrese el ID del estudiante a eliminar: ");
                                String idEliminar = teclado.nextLine();

                                gestionarEstudiantes.eliminarEstudiantes(idEliminar);

                                break;

                            case 5:

                                System.out.print("Ingrese el ID del estudiante: ");
                                String idNota = teclado.nextLine();

                                try {
                                    Estudiante estudianteNota = gestionarEstudiantes.buscarEstudiante(idNota);

                                    System.out.print("Ingrese el semestre (1-10): ");
                                    int semestreNota = teclado.nextInt();
                                    teclado.nextLine();

                                    System.out.print("Ingrese la posicion de la materia (1-20): ");
                                    int posicionMateria = teclado.nextInt();
                                    teclado.nextLine();

                                    System.out.print("Ingrese el codigo de la materia: ");
                                    String codigoMateria = teclado.nextLine();

                                    System.out.print("Ingrese la nota (0.0 - 5.0): ");
                                    double nota = teclado.nextDouble();
                                    teclado.nextLine();

                                    int indiceSemestre = semestreNota - 1;
                                    int indiceMateria = posicionMateria - 1;

                                    if (semestreNota < 1 || semestreNota > 10
                                            || posicionMateria < 1 || posicionMateria > 20
                                            || nota < 0 || nota > 5) {

                                        System.out.println("Los datos ingresados no son validos.");

                                    } else if (estudianteNota.getCodigoMateria(
                                            indiceSemestre, indiceMateria) != null) {

                                        System.out.println("Esa posicion ya tiene una materia asociada.");

                                    } else {

                                        estudianteNota.asociarMateria(
                                                indiceSemestre, indiceMateria, codigoMateria);

                                        estudianteNota.registrarNota(
                                                indiceSemestre, indiceMateria, nota);

                                        Operacion operacionNota = new Operacion(
                                                "NOTA",
                                                "Registro de nota para " + codigoMateria,
                                                estudianteNota,

                                                () -> estudianteNota.deshacerRegistroNota(
                                                        indiceSemestre, indiceMateria),

                                                () -> {
                                                    estudianteNota.asociarMateria(
                                                            indiceSemestre, indiceMateria, codigoMateria);

                                                    estudianteNota.registrarNota(
                                                            indiceSemestre, indiceMateria, nota);
                                                });

                                        gestionarOperaciones.registrarOperacion(operacionNota);

                                        System.out.println("Nota registrada y operacion guardada.");
                                    }

                                } catch (EstudianteNoEncontradoException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }

                                break;

                            case 0:

                                System.out.println("Volviendo al menu principal...");

                                break;

                            default:

                                System.out.println("Opcion no valida.");

                                break;
                        }

                    } while (opcionEstudiante != 0);

                    break;
                // opcion
                // 2-----------------------------------------------------------------------
                case 2:
                    int opcionMateria;

                    do {
                        System.out.println("\n===== GESTION DE MATERIAS =====");
                        System.out.println("1. Registrar materia");
                        System.out.println("2. Buscar materia");
                        System.out.println("3. Mostrar todas las materias");
                        System.out.println("4. Eliminar materia");
                        System.out.println("5. Agregar prerrequisito");
                        System.out.println("6. Inscribir estudiante");
                        System.out.println("7. Retirar estudiante");
                        System.out.println("0. Volver al menu principal");
                        System.out.print("Seleccione una opcion: ");

                        opcionMateria = teclado.nextInt();
                        teclado.nextLine();

                        switch (opcionMateria) {

                            case 1:

                                System.out.print("Ingrese el codigo: ");
                                String codigo = teclado.nextLine();

                                System.out.print("Ingrese el nombre: ");
                                String nombreMateria = teclado.nextLine();

                                System.out.print("Ingrese los cupos maximos: ");
                                int cupos = teclado.nextInt();

                                System.out.print("Ingrese los creditos: ");
                                int creditos = teclado.nextInt();
                                teclado.nextLine();

                                Materia nuevaMateria = new Materia(
                                        codigo, nombreMateria, cupos, creditos);

                                boolean materiaExiste = false;

                                try {
                                    gestionarMaterias.buscarMateria(codigo);
                                    materiaExiste = true;

                                } catch (MateriaNoEncontradaException e) {
                                    materiaExiste = false;
                                }

                                if (materiaExiste) {
                                    System.out.println("Ya existe una materia con ese codigo.");

                                } else {
                                    gestionarMaterias.agregarMateria(nuevaMateria);

                                    Operacion operacion = new Operacion(
                                            "MATERIA",
                                            "Registro de materia " + nombreMateria,
                                            nuevaMateria,

                                            () -> gestionarMaterias.eliminarMateria(codigo),

                                            () -> gestionarMaterias.agregarMateria(nuevaMateria));

                                    gestionarOperaciones.registrarOperacion(operacion);
                                }

                                break;

                            case 2:

                                System.out.print("Ingrese el codigo de la materia: ");
                                String codigoBuscar = teclado.nextLine();

                                try {
                                    Materia materiaEncontrada = gestionarMaterias.buscarMateria(codigoBuscar);

                                    materiaEncontrada.mostrarInformacion();

                                } catch (MateriaNoEncontradaException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }

                                break;

                            case 3:

                                gestionarMaterias.mostrarMaterias();

                                break;

                            case 4:

                                System.out.print("Ingrese el codigo de la materia: ");
                                String codigoEliminar = teclado.nextLine();

                                try {
                                    gestionarMaterias.eliminarMateria(codigoEliminar);

                                } catch (MateriaNoEncontradaException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }

                                break;

                            case 5:

                                System.out.print("Codigo de la materia principal: ");
                                String codigoPrincipal = teclado.nextLine();

                                System.out.print("Codigo del prerrequisito: ");
                                String codigoPrerrequisito = teclado.nextLine();

                                try {
                                    Materia materiaPrincipal = gestionarMaterias.buscarMateria(codigoPrincipal);

                                    gestionarMaterias.buscarMateria(codigoPrerrequisito);

                                    materiaPrincipal.agregarPrerrequisito(codigoPrerrequisito);

                                    System.out.println("Prerrequisito agregado correctamente.");

                                } catch (MateriaNoEncontradaException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }

                                break;

                            case 6:

                                System.out.print("Codigo de la materia: ");
                                String codigoInscripcion = teclado.nextLine();

                                System.out.print("ID del estudiante: ");
                                String idInscripcion = teclado.nextLine();

                                try {
                                    gestionarMaterias.inscribirEstudianteRegistrado(
                                            codigoInscripcion,
                                            idInscripcion,
                                            gestionarEstudiantes);

                                } catch (EstudianteNoEncontradoException | MateriaNoEncontradaException
                                        | PrerrequisitoNoCumplidoException e) {

                                    System.out.println("Error: " + e.getMessage());
                                }

                                break;

                            case 7:

                                System.out.print("Codigo de la materia: ");
                                String codigoRetiro = teclado.nextLine();

                                System.out.print("ID del estudiante: ");
                                String idRetiro = teclado.nextLine();

                                try {
                                    gestionarMaterias.retirarEstudiante(
                                            codigoRetiro,
                                            idRetiro);

                                } catch (MateriaNoEncontradaException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }

                                break;

                            case 0:

                                System.out.println("Volviendo al menu principal...");

                                break;

                            default:

                                System.out.println("Opcion no valida.");

                                break;
                        }

                    } while (opcionMateria != 0);

                    break;
                // opcion
                // 3------------------------------------------------------------------------------
                case 3:
                    int opcionSalon;

                    do {
                        System.out.println("\n===== GESTION DE SALONES =====");
                        System.out.println("1. Registrar salon");
                        System.out.println("2. Buscar salon");
                        System.out.println("3. Mostrar todos los salones");
                        System.out.println("4. Eliminar salon");
                        System.out.println("0. Volver al menu principal");
                        System.out.print("Seleccione una opcion: ");

                        opcionSalon = teclado.nextInt();
                        teclado.nextLine();

                        switch (opcionSalon) {

                            case 1:

                                System.out.print("Ingrese el codigo: ");
                                String codigoSalon = teclado.nextLine();

                                System.out.print("Ingrese el nombre: ");
                                String nombreSalon = teclado.nextLine();

                                System.out.print("Ingrese la capacidad: ");
                                int capacidad = teclado.nextInt();
                                teclado.nextLine();

                                Salon nuevoSalon = new Salon(codigoSalon, nombreSalon, capacidad);

                                Salon salonExistente = gestionarSalones.buscarSalon(codigoSalon);

                                if (salonExistente != null) {

                                    System.out.println("Ya existe un salon con ese codigo.");

                                } else {

                                    gestionarSalones.agregarSalon(nuevoSalon);

                                    Operacion operacion = new Operacion(
                                            "SALON",
                                            "Registro del salon " + nombreSalon,
                                            nuevoSalon,
                                            () -> gestionarSalones.eliminarSalon(codigoSalon),
                                            () -> gestionarSalones.agregarSalon(nuevoSalon));

                                    gestionarOperaciones.registrarOperacion(operacion);
                                }

                                break;

                            case 2:

                                System.out.print("Ingrese el codigo del salon: ");
                                String codigoBuscarSalon = teclado.nextLine();

                                Salon salonEncontrado = gestionarSalones.buscarSalon(codigoBuscarSalon);

                                if (salonEncontrado != null) {
                                    salonEncontrado.mostrarInformacion();
                                } else {
                                    System.out.println("No se encontro el salon.");
                                }

                                break;

                            case 3:

                                gestionarSalones.mostrarSalones();

                                break;

                            case 4:

                                System.out.print("Ingrese el codigo del salon: ");
                                String codigoEliminarSalon = teclado.nextLine();

                                gestionarSalones.eliminarSalon(codigoEliminarSalon);

                                break;

                            case 0:

                                System.out.println("Volviendo al menu principal...");

                                break;

                            default:

                                System.out.println("Opcion no valida.");

                                break;
                        }

                    } while (opcionSalon != 0);
                    break;
                // opcion
                // 4------------------------------------------------------------------------
                case 4:
                    int opcionHorario;

                    do {
                        System.out.println("\n===== GESTION DE HORARIOS =====");
                        System.out.println("1. Asignar horario");
                        System.out.println("2. Mostrar horarios registrados");
                        System.out.println("3. Eliminar horario");
                        System.out.println("0. Volver al menu principal");
                        System.out.print("Seleccione una opcion: ");

                        opcionHorario = teclado.nextInt();
                        teclado.nextLine();

                        switch (opcionHorario) {

                            case 1:

                                System.out.print("Ingrese el codigo de la materia: ");
                                String codigoMateriaHorario = teclado.nextLine();

                                System.out.print("Ingrese el codigo del salon: ");
                                String codigoSalonHorario = teclado.nextLine();

                                System.out.println("\nDias de la semana:");
                                System.out.println("0. Lunes");
                                System.out.println("1. Martes");
                                System.out.println("2. Miercoles");
                                System.out.println("3. Jueves");
                                System.out.println("4. Viernes");
                                System.out.println("5. Sabado");
                                System.out.println("6. Domingo");

                                System.out.print("Seleccione el dia: ");
                                int diaHorario = teclado.nextInt();

                                System.out.print("Ingrese la hora (0-23): ");
                                int horaHorario = teclado.nextInt();
                                teclado.nextLine();

                                if (diaHorario < 0 || diaHorario > 6 ||
                                        horaHorario < 0 || horaHorario > 23) {

                                    System.out.println("Error: dia u hora no validos.");
                                    break;
                                }

                                try {

                                    gestionarMaterias.buscarMateria(codigoMateriaHorario);

                                    Salon salonHorario = gestionarSalones.buscarSalon(codigoSalonHorario);

                                    if (salonHorario == null) {

                                        System.out.println("Error: el salon no existe.");

                                    } else if (!salonHorario.estaDisponible(diaHorario, horaHorario)) {

                                        System.out.println("Error: el salon esta ocupado.");

                                    } else {

                                        gestionarHorarios.asignarHorario(
                                                codigoMateriaHorario,
                                                codigoSalonHorario,
                                                diaHorario,
                                                horaHorario,
                                                gestionarSalones);

                                        if (!salonHorario.estaDisponible(diaHorario, horaHorario)) {

                                            Horario nuevoHorario = new Horario(
                                                    codigoMateriaHorario,
                                                    codigoSalonHorario,
                                                    diaHorario,
                                                    horaHorario);

                                            Operacion operacion = new Operacion(
                                                    "HORARIO",
                                                    "Asignacion de horario para la materia " + codigoMateriaHorario,
                                                    nuevoHorario,
                                                    () -> gestionarHorarios.eliminarHorario(
                                                            codigoMateriaHorario,
                                                            codigoSalonHorario,
                                                            diaHorario,
                                                            horaHorario,
                                                            gestionarSalones),
                                                    () -> gestionarHorarios.asignarHorario(
                                                            codigoMateriaHorario,
                                                            codigoSalonHorario,
                                                            diaHorario,
                                                            horaHorario,
                                                            gestionarSalones));

                                            gestionarOperaciones.registrarOperacion(operacion);
                                        }
                                    }

                                } catch (MateriaNoEncontradaException e) {

                                    System.out.println("Error: " + e.getMessage());
                                }

                                break;

                            case 2:

                                gestionarHorarios.mostrarHorarios();

                                break;

                            case 3:

                                System.out.print("Ingrese el codigo de la materia: ");
                                String codigoMateriaEliminar = teclado.nextLine();

                                System.out.print("Ingrese el codigo del salon: ");
                                String codigoSalonEliminar = teclado.nextLine();

                                System.out.println("\nSeleccione el dia (0-6): ");
                                int diaEliminar = teclado.nextInt();

                                System.out.print("Ingrese la hora (0-23): ");
                                int horaEliminar = teclado.nextInt();
                                teclado.nextLine();

                                if (diaEliminar < 0 || diaEliminar > 6 ||
                                        horaEliminar < 0 || horaEliminar > 23) {

                                    System.out.println("Error: dia u hora no validos.");

                                    break;
                                }

                                gestionarHorarios.eliminarHorario(
                                        codigoMateriaEliminar,
                                        codigoSalonEliminar,
                                        diaEliminar,
                                        horaEliminar,
                                        gestionarSalones);

                                break;

                            case 0:

                                System.out.println("Volviendo al menu principal...");

                                break;

                            default:

                                System.out.println("Opcion no valida.");

                                break;
                        }

                    } while (opcionHorario != 0);
                    break;
                // opcion
                // 5----------------------------------------------------------------------------
                case 5:
                    int opcionEdificios;

                    do {
                        System.out.println("\n=== GESTION DE EDIFICIOS Y RUTAS ===");
                        System.out.println("1. Registrar edificio");
                        System.out.println("2. Buscar edificio");
                        System.out.println("3. Mostrar todos los edificios");
                        System.out.println("4. Registrar distancia entre edificios");
                        System.out.println("5. Mostrar matriz de distancias");
                        System.out.println("6. Calcular ruta mas corta");
                        System.out.println("7. Eliminar edificio");
                        System.out.println("0. Volver al menu principal");
                        System.out.print("Seleccione una opcion: ");

                        opcionEdificios = teclado.nextInt();
                        teclado.nextLine();

                        switch (opcionEdificios) {

                            case 1:
                                System.out.print("Ingrese el codigo del edificio: ");
                                String codigoEdificio = teclado.nextLine();

                                System.out.print("Ingrese el nombre del edificio: ");
                                String nombreEdificio = teclado.nextLine();

                                Edificio nuevoEdificio = new Edificio(
                                        codigoEdificio, nombreEdificio);

                                int edificioExistente = gestionarEdificios.buscarEdificio(codigoEdificio);

                                if (edificioExistente != -1) {

                                    System.out.println("Ya existe un edificio con ese codigo.");

                                } else {

                                    gestionarEdificios.agregarEdificio(nuevoEdificio);

                                    Operacion operacion = new Operacion(
                                            "EDIFICIO",
                                            "Registro del edificio " + nombreEdificio,
                                            nuevoEdificio,
                                            () -> gestionarEdificios.eliminarEdificio(codigoEdificio),
                                            () -> gestionarEdificios.agregarEdificio(nuevoEdificio));

                                    gestionarOperaciones.registrarOperacion(operacion);
                                }

                                break;

                            case 2:
                                System.out.print("Ingrese el codigo del edificio: ");
                                String codigoBuscarEdificio = teclado.nextLine();

                                gestionarEdificios.mostrarEdificio(codigoBuscarEdificio);
                                break;

                            case 3:
                                gestionarEdificios.mostrarEdificios();
                                break;

                            case 4:
                                System.out.print("Codigo del edificio de origen: ");
                                String codigoOrigen = teclado.nextLine();

                                System.out.print("Codigo del edificio de destino: ");
                                String codigoDestino = teclado.nextLine();

                                System.out.print("Ingrese la distancia en metros: ");
                                int distancia = teclado.nextInt();
                                teclado.nextLine();

                                gestionarEdificios.agregarDistancia(
                                        codigoOrigen, codigoDestino, distancia);
                                break;

                            case 5:
                                gestionarEdificios.mostrarMatrizDistancias();
                                break;

                            case 6:
                                System.out.print("Codigo del edificio de origen: ");
                                String origenRuta = teclado.nextLine();

                                System.out.print("Codigo del edificio de destino: ");
                                String destinoRuta = teclado.nextLine();

                                gestionarEdificios.calcularRutaMasCorta(
                                        origenRuta, destinoRuta);
                                break;

                            case 7:
                                System.out.print("Ingrese el codigo del edificio a eliminar: ");
                                String codigoEliminarEdificio = teclado.nextLine();

                                gestionarEdificios.eliminarEdificio(codigoEliminarEdificio);
                                break;

                            case 0:
                                System.out.println("Volviendo al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                                break;
                        }

                    } while (opcionEdificios != 0);

                    break;
                // opcion
                // 6------------------------------------------------------------------------
                case 6:
                    int opcionFacultades;

                    do {
                        System.out.println("\n=== GESTION DE FACULTADES ===");
                        System.out.println("1. Registrar facultad");
                        System.out.println("2. Buscar facultad");
                        System.out.println("3. Mostrar todas las facultades");
                        System.out.println("4. Eliminar facultad");
                        System.out.println("0. Volver al menu principal");
                        System.out.print("Seleccione una opcion: ");

                        opcionFacultades = teclado.nextInt();
                        teclado.nextLine();

                        switch (opcionFacultades) {

                            case 1:
                                System.out.print("Ingrese el codigo de la facultad: ");
                                String codigoFacultad = teclado.nextLine();

                                System.out.print("Ingrese el nombre de la facultad: ");
                                String nombreFacultad = teclado.nextLine();

                                System.out.print("Ingrese el nombre del decano: ");
                                String decanoFacultad = teclado.nextLine();

                                Facultad nuevaFacultad = new Facultad(
                                        codigoFacultad,
                                        nombreFacultad,
                                        decanoFacultad);

                                Facultad facultadExistente = gestionarFacultades.buscarFacultad(codigoFacultad);

                                if (facultadExistente != null) {

                                    System.out.println("Ya existe una facultad con ese codigo.");

                                } else {

                                    gestionarFacultades.agregarFacultad(nuevaFacultad);

                                    Operacion operacion = new Operacion(
                                            "FACULTAD",
                                            "Registro de la facultad " + nombreFacultad,
                                            nuevaFacultad,
                                            () -> gestionarFacultades.eliminarFacultad(codigoFacultad),
                                            () -> gestionarFacultades.agregarFacultad(nuevaFacultad));

                                    gestionarOperaciones.registrarOperacion(operacion);
                                }

                                break;
                            case 2:
                                System.out.print("Ingrese el codigo de la facultad: ");
                                String codigoBuscarFacultad = teclado.nextLine();

                                Facultad facultadEncontrada = gestionarFacultades.buscarFacultad(codigoBuscarFacultad);

                                if (facultadEncontrada != null) {
                                    System.out.println("\n=== FACULTAD ENCONTRADA ===");
                                    facultadEncontrada.mostrarInformacion();
                                } else {
                                    System.out.println("No se encontro la facultad.");
                                }
                                break;

                            case 3:
                                gestionarFacultades.mostrarFacultades();
                                break;

                            case 4:
                                System.out.print("Ingrese el codigo de la facultad a eliminar: ");
                                String codigoEliminarFacultad = teclado.nextLine();

                                gestionarFacultades.eliminarFacultad(codigoEliminarFacultad);
                                break;

                            case 0:
                                System.out.println("Volviendo al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                                break;
                        }

                    } while (opcionFacultades != 0);

                    break;
                // opcion
                // 7-----------------------------------------------------------------------------
                case 7:
                    int opcionSolicitudes;

                    do {
                        System.out.println("\n=== GESTION DE SOLICITUDES ===");
                        System.out.println("1. Registrar solicitud");
                        System.out.println("2. Mostrar solicitudes pendientes");
                        System.out.println("3. Procesar siguiente solicitud");
                        System.out.println("4. Procesar todas las solicitudes");
                        System.out.println("5. Cargar solicitudes desde CSV");
                        System.out.println("0. Volver al menu principal");
                        System.out.print("Seleccione una opcion: ");

                        opcionSolicitudes = teclado.nextInt();
                        teclado.nextLine();

                        switch (opcionSolicitudes) {

                            case 1:
                                System.out.print("Ingrese el ID del estudiante: ");
                                String idSolicitud = teclado.nextLine();

                                System.out.print("Ingrese el codigo de la materia: ");
                                String codigoMateriaSolicitud = teclado.nextLine();

                                Solicitud nuevaSolicitud = new Solicitud(
                                        idSolicitud,
                                        codigoMateriaSolicitud);

                                gestionarSolicitudes.agregarSolicitud(nuevaSolicitud);
                                break;

                            case 2:
                                gestionarSolicitudes.mostrarSolicitudes();
                                break;

                            case 3:
                                gestionarSolicitudes.procesarSolicitud(
                                        gestionarEstudiantes,
                                        gestionarMaterias,
                                        gestionarOperaciones);
                                break;

                            case 4:
                                gestionarSolicitudes.procesarTodasLasSolicitudes(
                                        gestionarEstudiantes,
                                        gestionarMaterias,
                                        gestionarOperaciones);
                                break;

                            case 5:
                                System.out.print("Ingrese la ruta del archivo CSV: ");
                                String rutaArchivo = teclado.nextLine();

                                gestionarSolicitudes.cargarSolicitudesCSV(rutaArchivo);

                                break;

                            case 0:
                                System.out.println("Volviendo al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                                break;
                        }

                    } while (opcionSolicitudes != 0);

                    break;
                // opcion
                // 8------------------------------------------------------------------------------
                case 8:
                    gestionarOperaciones.deshacerOperacion();
                    break;
                // opcion
                // 9----------------------------------------------------------------------------------
                case 9:
                    gestionarOperaciones.rehacerOperacion();
                    break;
                // opcion10-----------------------------------------------------------------------------------
                case 10:
                    int opcionReporte;

                    do {
                        System.out.println("\n===== NAVEGACION DE REPORTES =====");
                        System.out.println("1. Abrir reporte");
                        System.out.println("2. Volver al reporte anterior");
                        System.out.println("3. Avanzar al siguiente reporte");
                        System.out.println("4. Mostrar reporte actual");
                        System.out.println("0. Regresar al menu principal");
                        System.out.print("Seleccione una opcion: ");

                        opcionReporte = teclado.nextInt();
                        teclado.nextLine();

                        switch (opcionReporte) {

                            case 1:
                                System.out.println("\nReportes disponibles:");
                                System.out.println("1. Reporte de estudiantes");
                                System.out.println("2. Reporte de materias");
                                System.out.println("3. Reporte de salones");
                                System.out.println("4. Reporte de horarios");
                                System.out.println("5. Reporte de edificios");
                                System.out.println("6. Reporte de facultades");
                                System.out.println("7. Reporte academico individual");

                                System.out.print("Seleccione el reporte: ");
                                int seleccionReporte = teclado.nextInt();
                                teclado.nextLine();

                                String nombreReporte = "";

                                switch (seleccionReporte) {
                                    case 1:
                                        nombreReporte = "Reporte de estudiantes";
                                        break;
                                    case 2:
                                        nombreReporte = "Reporte de materias";
                                        break;
                                    case 3:
                                        nombreReporte = "Reporte de salones";
                                        break;
                                    case 4:
                                        nombreReporte = "Reporte de horarios";
                                        break;
                                    case 5:
                                        nombreReporte = "Reporte de edificios";
                                        break;
                                    case 6:
                                        nombreReporte = "Reporte de facultades";
                                        break;
                                    case 7:
                                        System.out.print("Ingrese el ID del estudiante: ");
                                        String idReporte = teclado.nextLine();
                                        gestionarNavegacionReportes.abrirReporteAcademico(idReporte);

                                        gestionarNavegacionReportes.mostrarContenidoReporte(gestionarEstudiantes,
                                                gestionarMaterias, gestionarSalones,
                                                gestionarHorarios,
                                                gestionarEdificios,
                                                gestionarFacultades);

                                        break;
                                    default:
                                        System.out.println("Opcion no valida.");
                                }

                                if (!nombreReporte.isEmpty()) {
                                    gestionarNavegacionReportes.abrirReporte(nombreReporte);
                                }
                                break;

                            case 2:
                                gestionarNavegacionReportes.volverAtras();
                                break;

                            case 3:
                                gestionarNavegacionReportes.avanzar();
                                break;

                            case 4:
                                gestionarNavegacionReportes.mostrarReporteActual();
                                break;

                            case 0:
                                System.out.println("Regresando al menu principal...");
                                break;

                            default:
                                System.out.println("Opcion no valida.");
                        }

                    } while (opcionReporte != 0);
                    break;
                // opcion
                // 11-----------------------------------------------------------------------------------
                case 0:
                    System.out.println("Cerrando el sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 0);

        teclado.close();
    }
}
