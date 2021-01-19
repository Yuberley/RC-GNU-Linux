package rc_linux;

/**
 *
 * @author Yuberley Guerrero
 */
import java.net.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import javax.swing.JOptionPane;

/* Programa para ejecutarse en Windows */
public class Client_Windows {

    String texto;

    public static void main(String args[]) {

        Socket socket;

        int Puerto;
        InetAddress IP;
        


        try {

            Puerto = 2022;
            System.out.println("Estableciendo Conexion con el servidor");
            IP = InetAddress.getByName("192.168.128.5");
            socket = new Socket(IP, Puerto);
            System.out.println("Conexion establecida con el servidor");
            
//            Login L = new Login();
//            L.logina();
        
            Principal p = new Principal(socket);
            p.setVisible(true);

            RecibirGraficos RecibirGraficos = new RecibirGraficos(socket);
            RecibirGraficos.start();

        } catch (Exception e) {

        }

    }

}

// Send command
class EnviarComando extends Thread {

    Socket socket;
    String comando;

    public EnviarComando(Socket ss, String comando) {
        this.socket = ss;
        this.comando = comando;
    }

    public void run() {

        String comando1 = comando;

        try {

            DataOutput salida;
            String mensaje;

            do {
                salida = new DataOutputStream(socket.getOutputStream());
                mensaje = comando1;
                salida.writeUTF(mensaje);
            } while (!(mensaje.equals("ffffff")));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// Receive
class RecibirComando extends Thread {

    Socket socket;

    public RecibirComando(Socket ss) {
        this.socket = ss;
    }

    public void run() {

        try {

            DataInputStream entrada;
            String respuesta;

            do {
                entrada = new DataInputStream(socket.getInputStream());
                respuesta = entrada.readUTF();
                System.out.println(respuesta+"\n");
            } while ((respuesta.equals(null)));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

// Receive Processor Data
class RecibirGraficos extends Thread {

    Socket socket;

    public RecibirGraficos(Socket ss) {
        this.socket = ss;
    }

    public void run() {

        try {
            DataInputStream entrada;
            String mensaje;
            String id = "";
            String valor = "";

            do {
                entrada = new DataInputStream(socket.getInputStream());
                mensaje = entrada.readUTF();

                id = mensaje.split("/")[0];
                valor = mensaje.split("/")[1];
                
                if (id.equals("p")) {
                    System.out.println("El procesador es " + valor);
                } else if (id.equals("d")) {
                    System.out.println("El disco es " + valor);
                } else if (id.equals("m")) {
                    System.out.println("El memoria es " + valor);
                }else if (id.equals("a")) {
                    System.out.println("Respuesta " + valor);
                }
            } while (!(mensaje.equals("ffffff")));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class Login {

    public static void login() {
        new Login().logina();

    }

    void logina() {
        File archi = new File("bdUsuarios.txt");
        Cifrador c = new Cifrador();
        String uArchivo = "", pArchivo = "";

        try {
            FileReader frArchivo = new FileReader(archi);
            BufferedReader brArchivo = new BufferedReader(frArchivo);

            String contenido;
            Scanner lector = new Scanner(System.in);
            System.out.print("USU y PASS");
            String usu = lector.nextLine();
            String pasw = lector.nextLine();
            boolean b = false;
            while ((contenido = brArchivo.readLine()) != null) {
                String[] param = contenido.split(":");
                uArchivo = param[0];
                pArchivo = param[1];
                
                if (uArchivo.equals(usu) && pArchivo.equals(pasw)) {
              
                    b = true;
                }
                
                System.out.print(uArchivo + "//" + pArchivo + "\n");
            }
            if (b) {
                System.out.println("Credenciales correctas");
                //abre otra ventana
            } else if (!b){
                System.out.println("El usuario o password es incorrecto");
                //error
            }

            brArchivo.close();

        } catch (Exception e) {

        }
    }

}
