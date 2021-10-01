/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller0_2021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import ucn.*;

public class Taller0_2021 {

    /**
     * this function reads the file clientes.txt and saves the corresponding
     * data in the matrix.
     *
     * @param args
     */
    public static int leerClientes(String[][] matriz, int[] SALDO) throws IOException {
        ArchivoEntrada arch = new ArchivoEntrada("Clientes.txt");
        int i = 0;
        while (!arch.isEndFile()) {
            Registro reg = arch.getRegistro();
            String nombre = reg.getString();
            String apellido = reg.getString();
            String rut = reg.getString();
            String clave = reg.getString();
            int saldo = reg.getInt();
            matriz[i][0] = nombre;
            matriz[i][1] = apellido;
            matriz[i][2] = rut;
            matriz[i][3] = clave;
            SALDO[i] = saldo;
            i++;
        }
        return i;
    }

    public static void leerStatus(String[][] matriz, String[] ESTADO, int cantClientes) throws IOException {
        ArchivoEntrada arch = new ArchivoEntrada("Status.txt");
        while (!arch.isEndFile()) {
            Registro reg = arch.getRegistro();
            String rut = reg.getString();
            String estado = reg.getString();

            for (int i = 0; i < cantClientes; i++) {
                if (matriz[i][2].equals(rut)) {
                    ESTADO[i] = estado;
                }
            }
        }
    }

    public static int leerPeliculas(String[] NOMBREPELICULA, String[] TIPOPELICULA, int[] RECAUDADOT, String[][] MATRIZCINE) throws IOException {
        ArchivoEntrada arch = new ArchivoEntrada("Peliculas.txt");
        int i = 0;
        while (!arch.isEndFile()) {
            Registro reg = arch.getRegistro();
            String nomPelicula = reg.getString();
            String tipoP = reg.getString();
            int recaudado = reg.getInt();

            NOMBREPELICULA[i] = nomPelicula;
            TIPOPELICULA[i] = tipoP;
            RECAUDADOT[i] = recaudado;

            //Lo siguiente se utiliza para leer y guardar todos los datos de salas y horarios de las peliculas
            String sala = reg.getString();
            //como la sala no es igual a null entrará al ciclo
            while (sala != null) {
                String horario = reg.getString();
                //Si el horario es "M" se almacenará el nombre de la pelicula en la columna de la 
                //mañana, es decir la 0 y como los numeros de sala comienzan del 1 solo se
                //resta 1 a sala y se tiene la posicion de la fila en la matriz (MATRIZCINE) que almacenará el nombre de la pelicula
                if (horario.equals("M")) {
                    MATRIZCINE[(Integer.parseInt(sala)) - 1][0] = nomPelicula;
                } //De lo contrario si el horario es "T" se almacenará el nombre de la pelicula en la columna de la 
                //tarde, es decir la 1
                else if (horario.equals("T")) {
                    MATRIZCINE[(Integer.parseInt(sala)) - 1][1] = nomPelicula;
                }
                sala = reg.getString();
            }//y así hasta que no hayan más salas en la linea que se está leyendo del txt
            i++;

        }
        return i;
    }

    public static int buscarPeliculaIndice(String lista[], String nombreIngresado) {

        for (int i = 0; i < lista.length; i++) {
            if (nombreIngresado.equals(lista[i])) {
                return i;
            }

        }
        return -1;

    }

    public static boolean buscarPelicula(String nombrePeli[], String nombreIngresado) {

        for (int i = 0; i < nombrePeli.length; i++) {
            if (nombreIngresado.equals(nombrePeli[i])) {
                return true;
            }
            return false;
        }
        return false;
    }

    //funcion para encontrar los datos de la pelicula como el nombre, el horario y la sala
    public static String encontrarPelicula(String[] NOMBREPELICULA, int cantPeliculas, String[][] MATRIZCINE) {
        Scanner scan = new Scanner(System.in);
        System.out.println("ingrese nombre Pelicula: ");
        String peliculaIngresada = scan.nextLine();
        boolean encontrado = false;
        for (int i = 0; i < cantPeliculas; i++) {

            if (NOMBREPELICULA[i].equals(peliculaIngresada)) {
                encontrado = true;
                break;

            }
        }
        if (encontrado) {
            for (int f = 0; f < 3; f++) {
                for (int c = 0; c < 2; c++) {
                    if (MATRIZCINE[f][c].equals(peliculaIngresada)) {
                        if (c == 1) {
                            System.out.println("Horario T" + ",Sala:" + (f + 1));
                        }
                        if (c == 0) {
                            System.out.println("Horario M" + ",Sala:" + (f + 1));
                        }

                    }

                }

            }
            System.out.println("Horario: ");
            String horarioIngresado = scan.nextLine();
            System.out.println("Sala: ");
            String salaIngresado = scan.nextLine();

        }
        return null;

    }

    public static void desplegarComprar(String[][] MATRIZCINE, String[][] MATRIZASIENTO1M, String[][] MATRIZASIENTO1T, String[][] MATRIZASIENTO2M, String[][] MATRIZASIENTO2T, String[][] MATRIZASIENTO3M, String[][] MATRIZASIENTO3T) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Horario: ");
        String horarioIngresado = scan.nextLine();
        System.out.println("Sala: ");
        int salaIngresado = scan.nextInt();
        {
            if (salaIngresado == 1) {

                if (horarioIngresado.equals("M")) {
                    desplegarMatriz(MATRIZASIENTO1M, 10, 30);
                }
                if (horarioIngresado.equals("T")) {
                    desplegarMatriz(MATRIZASIENTO1T, 10, 30);
                }

            }
            if (salaIngresado == 2) {

                if (horarioIngresado.equals("M")) {
                    desplegarMatriz(MATRIZASIENTO2M, 10, 30);
                }
                if (horarioIngresado.equals("T")) {
                    desplegarMatriz(MATRIZASIENTO2T, 10, 30);
                }
            }
            if (salaIngresado == 3) {

                    if (horarioIngresado.equals("M")) {
                        desplegarMatriz(MATRIZASIENTO3M, 10, 30);
                    }
                    if (horarioIngresado.equals("T")) {
                        desplegarMatriz(MATRIZASIENTO3T, 10, 30);
                    }
                }
            }

        }

    

    public static void comprarEntrada(String[] NOMBREPELICULA, String[] TIPOPELICULA, String[][] MATRIZCINE, int cantPeliculas) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println(obtenerCartelera(cantPeliculas, NOMBREPELICULA, MATRIZCINE));
        System.out.println(encontrarPelicula(NOMBREPELICULA, cantPeliculas, MATRIZCINE));

    }

    public static void desplegarMatriz(String[][] matriz, int cantFilas,
            int cantColumnas) {
        for (int i = 0; i < cantFilas; i++) {
            for (int j = 0; j < cantColumnas; j++) {
                StdOut.print(matriz[i][j] + " ");
            }
            StdOut.println("");
        }
    }

    public static void completarconletra(String[][] MATRIZASIENTO1M) throws IOException {
        //Esta procedimiento es para llenar de "a" las partes que no tienen asiento de una sala. El String "0" corresponderá a una parte con asiento que está vacío
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                MATRIZASIENTO1M[j][i] = "a";
            }
        }
        for (int i = 25; i < 30; i++) {
            for (int j = 0; j < 4; j++) {
                MATRIZASIENTO1M[j][i] = "a";
            }
        }
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 10; j++) {
                if (MATRIZASIENTO1M[j][i] == null) {
                    MATRIZASIENTO1M[j][i] = "0";
                }
            }
        }
    }

    public static String obtenertaquilla(int cantPeliculas, String[] NOMBREPELICULA, int[] RECAUDADOT, int[] RECAUDADOH) throws IOException {
        String texto = "Monto recaudado por película\n";
        for (int i = 0; i < cantPeliculas; i++) {
            texto = texto + NOMBREPELICULA[i] + ": total $" + RECAUDADOT[i] + "\n";
        }

        texto = texto + "\n Monto recaudado en horario de la mañana: $" + RECAUDADOH[0]
                + "\n Monto recaudado en horario de la tarde: $" + RECAUDADOH[1]
                + "\n Monto total recaudado a lo largo del día: $" + (RECAUDADOH[0] + RECAUDADOH[1]);

        return texto;
    }

    public static String obtenerCartelera(int cantPeliculas, String[] NOMBREPELICULA, String[][] MATRIZCINE) throws IOException {
        String texto = "";
        boolean estaenmañana = false; //Se utiliza para saber si la pelicula está en horario de mañana
        for (int a = 0; a < cantPeliculas; a++) {
            texto = texto + NOMBREPELICULA[a] + " horario disponible: ";
            for (int j = 0; j < 3; j++) {
                if (MATRIZCINE[j][0].equals(NOMBREPELICULA[a])) {
                    texto = texto + "mañana ";
                    estaenmañana = true;
                    break;
                }
            }
            for (int j = 0; j < 3; j++) {
                if (MATRIZCINE[j][1].equals(NOMBREPELICULA[a])) {
                    if (estaenmañana == true) {
                        texto = texto + "y tarde ";
                    } else {
                        texto = texto + "tarde ";
                    }
                    break;
                }
            }
            texto = texto + "\n";
        }
        return texto;
    }

    public static void obtenerentradasR(String rut, String[][] MATRIZASIENTO1M, String[][] MATRIZASIENTO1T,
            String[][] MATRIZASIENTO2M, String[][] MATRIZASIENTO2T, String[][] MATRIZASIENTO3M, String[][] MATRIZASIENTO3T, String[][] MATRIZCINE) throws IOException {

        String texto = "";
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 10; j++) {
                //Se utliza para verificar en cual de las matrices el cliente compró entrada(s)
                if (MATRIZASIENTO1M[j][i].equals(rut)) {
                    texto = "Pelicula: " + MATRIZCINE[0][0] + "\n Sala: 1 horario mañana \n asiento(s): ";
                    System.out.println(texto);
                    obtenerasientosR(MATRIZASIENTO1M, rut);
                    break;
                } else if (MATRIZASIENTO1T[j][i].equals(rut)) {
                    texto = "Pelicula: " + MATRIZCINE[0][1] + "\n Sala: 1 horario tarde \n asiento(s): ";
                    System.out.println(texto);
                    obtenerasientosR(MATRIZASIENTO1T, rut);
                    break;
                } else if (MATRIZASIENTO2M[j][i].equals(rut)) {
                    texto = "Pelicula: " + MATRIZCINE[1][0] + "\n Sala: 2 horario mañana \n asiento(s): ";
                    System.out.println(texto);
                    obtenerasientosR(MATRIZASIENTO2M, rut);
                    break;
                } else if (MATRIZASIENTO2T[j][i].equals(rut)) {
                    texto = "Pelicula: " + MATRIZCINE[1][1] + "\n Sala: 2 horario tarde \n asiento(s): ";
                    System.out.println(texto);
                    obtenerasientosR(MATRIZASIENTO2T, rut);
                    break;
                } else if (MATRIZASIENTO3M[j][i].equals(rut)) {
                    texto = "Pelicula: " + MATRIZCINE[2][0] + "\n Sala: 3 horario mañana \n asiento(s): ";
                    System.out.println(texto);
                    obtenerasientosR(MATRIZASIENTO3M, rut);
                    break;
                } else if (MATRIZASIENTO3T[j][i].equals(rut)) {
                    texto = "Pelicula: " + MATRIZCINE[2][1] + "\n Sala: 3 horario tarde \n asiento(s): ";
                    System.out.println(texto);
                    obtenerasientosR(MATRIZASIENTO3T, rut);
                    break;
                }
            }
        }
    }

    public static void obtenerasientosR(String[][] MATRIZDEASIENTO, String rut) throws IOException {
        System.out.println("       P A N T A L L A   ");
        System.out.println(" ");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 30; j++) {
                if (MATRIZDEASIENTO[i][j].equals("a")) {
                    System.out.print(" ");
                } else if (MATRIZDEASIENTO[i][j].equals(rut)) {
                    System.out.print("X");
                } else {
                    System.out.print(MATRIZDEASIENTO[i][j]);
                }
            }
            System.out.println("");
        }
        System.out.println(" ");
        System.out.print("X ->  Asiento de usuario");
        System.out.println("0 ->  Asiento desocupado");
    }

    public static void obtenerClienteE(String rutbuscado, String[][] MATRIZCLIENTES, int[] SALDO, int cantClientes) throws IOException {
        for (int i = 0; i < cantClientes; i++) {
            if (rutbuscado.equals(MATRIZCLIENTES[i][2])) {
                System.out.println("Nombre: " + MATRIZCLIENTES[i][0] + "\n Apellido: " + MATRIZCLIENTES[i][1] + "\n Saldo: $" + SALDO[i]);
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String[][] MATRIZCLIENTES = new String[100][4];
        int[] SALDO = new int[100];

        String[] ESTADO = new String[100];

        String[][] MATRIZASIENTO1M = new String[10][30];
        String[][] MATRIZASIENTO1T = new String[10][30];
        String[][] MATRIZASIENTO2M = new String[10][30];
        String[][] MATRIZASIENTO2T = new String[10][30];
        String[][] MATRIZASIENTO3M = new String[10][30];
        String[][] MATRIZASIENTO3T = new String[10][30];
        completarconletra(MATRIZASIENTO1M);
        completarconletra(MATRIZASIENTO1T);
        completarconletra(MATRIZASIENTO2M);
        completarconletra(MATRIZASIENTO2T);
        completarconletra(MATRIZASIENTO3M);
        completarconletra(MATRIZASIENTO3T);

        String[] NOMBREPELICULA = new String[6];
        String[] TIPOPELICULA = new String[6];
        int[] RECAUDADOT = new int[6];
        int[] RECAUDADOH = new int[2];
        String[][] MATRIZCINE = new String[3][2];

        int cantClientes = leerClientes(MATRIZCLIENTES, SALDO);
        leerStatus(MATRIZCLIENTES, ESTADO, cantClientes);
        int cantPeliculas = leerPeliculas(NOMBREPELICULA, TIPOPELICULA, RECAUDADOT, MATRIZCINE);

        BufferedReader bf;

        bf = new BufferedReader(new InputStreamReader(System.in));
        Scanner scanner = new Scanner(System.in);
        int opcion2 = 0;
        int opcion1 = 0;
        int opcion = 0;
        String clave = null, rut = null;
        while (opcion != 2) {
            System.out.println("Bienvenido a Cine Cuevana!");
            System.out.println("1-Iniciar Sesion");
            System.out.println("2-Salir del sistema");

            do {

                try {
                    System.out.println("");
                    System.out.println("Ingrese opcion: ");
                    opcion = Integer.parseInt(bf.readLine());
                    System.out.println("");

                } catch (Exception error) {
                    System.out.println("");
                    System.out.println("ERROR: El programa ha detectado inválido lo ingresado.");
                    System.out.println("");
                    opcion = 0;
                }
                if (opcion != 1 && opcion != 2) {
                    System.out.println("opcion ingresada no es valida");
                }
            } while (opcion < 1 || opcion > 2);

            switch (opcion) {

                case 1:
           
                    try {
                    StdOut.print("Ingrese su RUT: ");
                    rut = StdIn.readString();
                } catch (Exception error) {

                    System.out.println("El formato ingresado no es el correcto, vuelve a intentarlo.");
                }

                StdOut.print("Ingrese su clave: ");
                clave = StdIn.readString();

                //Menu administrador
                if (rut.equals("ADMIN") && clave.equals("ADMIN")) {
                    System.out.println("Bienvenido al Menu de administrador!");
                    System.out.println();

                    StdOut.println("1- Taquilla");
                    StdOut.println("2- Informacion Cliente");

                    System.out.println();
                    do {

                        try {
                            System.out.println("");
                            System.out.println("Ingrese opcion: ");
                            opcion = Integer.parseInt(bf.readLine());
                            System.out.println("");

                        } catch (Exception error) {
                            System.out.println("");
                            System.out.println("ERROR: El programa ha detectado inválido lo ingresado.");
                            System.out.println("");
                            opcion = 0;
                        }
                        if (opcion != 1 && opcion != 2) {
                            System.out.println("opcion ingresada no es valida");
                        }
                    } while (opcion < 1 || opcion > 2);

                    // para ingresar a las distintas opciones de menu admin
                    switch (opcion) {
                        case 1:

                            System.out.println("");
                            System.out.println("INGRESANDO A VER TAQUILLA");
                            System.out.println(obtenertaquilla(cantPeliculas, NOMBREPELICULA, RECAUDADOT, RECAUDADOH));

                            System.out.println("");
                            System.out.println("");
                            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                            break;
                        case 2:

                            System.out.println("");
                            System.out.println("ingrese rut del cliente: ");
                            String rutbuscado = scanner.nextLine();
                            try {
                                System.out.println("");
                            } catch (NullPointerException ex) {
                                System.out.println("");
                                System.out.println(ex.getMessage());
                                System.out.println("");
                            }
                            System.out.println("INGRESANDO A INFORMACION DE CLIENTE");
                            obtenerClienteE(rutbuscado, MATRIZCLIENTES, SALDO, cantClientes);
                            obtenerentradasR(rutbuscado, MATRIZASIENTO1M, MATRIZASIENTO1T, MATRIZASIENTO2M, MATRIZASIENTO2T, MATRIZASIENTO3M, MATRIZASIENTO3T, MATRIZCINE);
                            System.out.println("");
                            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                            break;

                    }
                } else if (!(rut.equals("ADMIN")) && !(clave.equals("ADMIN"))) {

                    System.out.println(rut);
                    System.out.println("Bienvenido al menu Cliente!");
                    System.out.println("");
                    System.out.println("");
                    StdOut.println("1- Comprar entrada");
                    StdOut.println("2- Informacion de usuario");
                    StdOut.println("3- Devolucion de entrada");
                    StdOut.println("4- Cartelera");

                    do {

                        try {
                            System.out.println("");
                            System.out.println("Ingrese opcion: ");
                            opcion2 = Integer.parseInt(bf.readLine());
                            System.out.println("");

                        } catch (Exception error) {
                            System.out.println("");
                            System.out.println("ERROR: El programa ha detectado inválido lo ingresado.");
                            System.out.println("");
                            opcion2 = 0;
                        }
                        if (opcion2 != 1 && opcion2 != 2 && opcion2 != 3 && opcion2 != 4) {
                            System.out.println("opcion ingresada no es valida");
                        }
                    } while (opcion2 < 1 || opcion2 > 4);
                    switch (opcion2) {
                        case 1:
                            System.out.println("");
                            System.out.println("INGRESANDO A COMPRAR ENTRADA");
                            System.out.println("");
                            comprarEntrada(NOMBREPELICULA, TIPOPELICULA, MATRIZCINE, cantPeliculas);
                            System.out.println("");
                            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                            break;
                        case 2:
                            System.out.println("");
                            System.out.println("INFORMACION DE USUARIO");
                            System.out.println("Rut: " + rut);
                            obtenerClienteE(rut, MATRIZCLIENTES, SALDO, cantClientes);
                            obtenerentradasR(rut, MATRIZASIENTO1M, MATRIZASIENTO1T, MATRIZASIENTO2M, MATRIZASIENTO2T, MATRIZASIENTO3M, MATRIZASIENTO3T, MATRIZCINE);
                            System.out.println("");
                            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                            break;
                        case 3:
                            System.out.println("");
                            System.out.println("DEVOLUCION DE ENTRADA");
                            System.out.println("");
                            obtenerentradasR(rut, MATRIZASIENTO1M, MATRIZASIENTO1T, MATRIZASIENTO2M, MATRIZASIENTO2T, MATRIZASIENTO3M, MATRIZASIENTO3T, MATRIZCINE);
                            //le pide el nombre de la pelicula, ingresar cantidad de entradas, seleccioanr asientos si es necesario y devolver 80%
                            System.out.println("");
                            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                            break;

                        case 4:
                            System.out.println("");
                            System.out.println("CARTELERA");
                            System.out.println(obtenerCartelera(cantPeliculas, NOMBREPELICULA, MATRIZCINE));
                            System.out.println("");
                            System.out.println("");
                            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
                            break;

                    }

                }

                case 2:
                    System.out.println();
                    System.out.println("¿Desea hacer algo mas? 1)Si;2)No");
                    System.out.print("Ingrese una opcion: ");
                    opcion = StdIn.readInt();

                    if (opcion == 1) {
                        System.out.println("Volviendo a menu principal");
                        System.out.println("");
                        System.out.println("");
                        break;
                    }

                    if (opcion1 > 2 || opcion1 < 1) {
                        System.out.println("Elija una opcion valida: ");
                        opcion = StdIn.readInt();
                        break;

                    }
            }
        }
    }
}
