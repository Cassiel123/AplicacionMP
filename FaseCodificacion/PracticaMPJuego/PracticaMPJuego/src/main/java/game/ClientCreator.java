package game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientCreator implements UserCreator {

    //Pide los valores necesarios para la creación de un Client, lo crea y lo devuelve
    @Override
    public User createUser() {
        Controller system = Controller.getSystem();
        Scanner sc = new Scanner(System.in);
        boolean found;
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
        System.out.print("Introduzca su nombre: ");
        String name = sc.nextLine();

        
        int id = system.getClients().size() + 1;
        

        
        return new Client(nick, password, name, id);
    }
    
    /*
    Pide al usuario que introduzca por teclado la fecha de expiración de la oferta
    y comprueba que se ajusta al formato indicado, utilizando una expresión regular
    */
    private String askForEmail() {
        Scanner sc = new Scanner(System.in);
        String email;
        boolean correct;
        
        do {
            System.out.print("Introduzca su email: ");
            email = sc.nextLine();
            Pattern pattern = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
            Matcher mat = pattern.matcher(email);
            correct = mat.matches();

            if (!correct) {
                System.out.println("Formato incorrecto\n");
            }
        } while (!correct);
        
        return email;
    }
}
