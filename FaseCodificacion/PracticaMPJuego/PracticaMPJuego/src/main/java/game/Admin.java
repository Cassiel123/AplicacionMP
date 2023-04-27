
package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    
    public Admin(String nick, String password) {
        super(nick, password);
    }


    
    //Método vacío, por el momento los administradores no necesitan recibir notificaciones
    
    @Override
    public String getClassName() {
        return "Admin";
    }
    
}
