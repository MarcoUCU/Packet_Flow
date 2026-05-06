package com.example;
import java.util.Scanner;

public class Menu {

    private Simulador simulador;
    private Scanner scanner;

    public Menu() {
        simulador = new Simulador(50,4); //La prob. por defecto que se pierda el paquete es de 15% si no se indica como 3er parametro del simulador
        scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    crearRed();
                    break;

                case 2:
                    crearMensaje();
                    break;

                case 3:
                    enviarSiguiente();
                    break;

                case 4:
                    recibirPaquete();
                    break;

                case 5:
                    reconstruirMensaje();
                    break;

                case 6:
                    eliminarMensaje();
                    break;

                case 7:
                    consultarEstado();
                    break;

                case 8:
                    listarMensajes();
                    break;

                case 0:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\n===== PACKET FLOW =====");
        System.out.println("1. Crear red");
        System.out.println("2. Crear mensaje");
        System.out.println("3. Enviar mensaje a tránsito");
        System.out.println("4. Recibir paquete");
        System.out.println("5. Reconstruir mensaje");
        System.out.println("6. Eliminar mensaje");
        System.out.println("7. Consultar estado");
        System.out.println("8. Listar mensajes");
        System.out.println("0. Salir");
    }

    private void crearRed() {
        int capacidad = leerEntero("Capacidad máxima de la red: ");
        int tamMax = leerEntero("Tamaño máximo de paquete: ");

        simulador.crearRed(capacidad, tamMax);
        System.out.println("Red creada correctamente.");
    }

    private void crearMensaje() {
        System.out.print("ID del mensaje: ");
        String id = scanner.nextLine();

        System.out.print("Contenido: ");
        String contenido = scanner.nextLine();

        int prioridad = leerEntero("Prioridad: ");

        simulador.crearMensaje(id, contenido, prioridad);
        System.out.println("Mensaje creado.");
    }

    private void enviarSiguiente() {
        String resultado = simulador.enviarSiguiente();
        System.out.println(resultado);
    }

    private void recibirPaquete() {
        String resultado = simulador.recibirPaquete();
        System.out.println(resultado);
    }

    private void reconstruirMensaje() {
        System.out.print("ID del mensaje: ");
        String id = scanner.nextLine();

        String resultado = simulador.reconstruirMensaje(id);
        System.out.println(resultado);
    }

    private void eliminarMensaje() {
        System.out.print("ID del mensaje: ");
        String id = scanner.nextLine();

        simulador.eliminarMensaje(id);
        System.out.println("Mensaje eliminado.");
    }

    private void consultarEstado() {
        System.out.println(simulador.consultarEstado());
    }

    private void listarMensajes() {
        System.out.println(simulador.listarMensajes());
    }

    private int leerEntero(String mensaje) {
        int numero;

        while (true) {
            try {
                System.out.print(mensaje);
                numero = Integer.parseInt(scanner.nextLine());
                return numero;
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
            }
        }
    }
}