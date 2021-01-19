/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rc_linux;

import java.io.*;
import java.util.Scanner;

public class Login {

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
