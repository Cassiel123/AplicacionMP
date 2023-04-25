package game;

import java.util.Scanner;

public class AdminCreator implements UserCreator {
    
    //Pide los valores necesarios para la creación de un Admin, lo crea y lo devuelve
    @Override
    public User createUser() {
        Controller system = Controller.getSystem();
        Scanner sc = new Scanner(System.in);
        boolean found = false;
        String nick;
        
        do {
            System.out.print("Introduzca su nick: ");
            nick = sc.nextLine();
            found = false;
            
            for (User user: system.getUsers()) {
                if (user.getNick().equals(nick)) {
                    found = true;
                    break;
                }    
            }
            
            if (found) {
                System.out.println("Ese nick ya existe\n");
            }
        } while (found);
        
        System.out.print("Introduzca su contraseña: ");
        String password = sc.nextLine();
        
        return new Admin(nick, password);
    }
}
