package game;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Controller {

    private static Controller system;
    private List<User> users = new ArrayList<>();
    private String usersPath = "Files\\usuarios.txt";

    private Controller() {
        try {
            ObjectInputStream input1 = new ObjectInputStream(new FileInputStream(usersPath));
            users = (List<User>) input1.readObject();
        } catch (Exception e) {
        }
    }

    //Sobreescribe en el fichero especificado por path el objeto list
    public void modifyFile(String path, Object list) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path));
            output.writeObject(list);
            output.close();
        } catch (Exception e) {
        }
    }

    public String getUsersPath() {
        return usersPath;
    }

    public static Controller getSystem() {
        if (system == null) {
            system = new Controller();
        }
        return system;
    }

    //Devuelve los usuarios que sean de la clase Client pertenecientes a la lista de usuarios
    public List<Client> getClients() {
        List<Client> list = new ArrayList<>();

        for (User user : users) {
            String clientClass = user.getClassName();
            if ("Client".equals(clientClass)) {
                list.add((Client) user);
            }
        }
        return list;
    }

    public void showClients(List<Client> clients) {
        for (Client client : clients) {
            System.out.print(client.getNick() + ", ");
        }
        System.out.println();
    }

    //En función del tipo deseado se llama al constructor apropiado
    public void createUser(String userType) {
        UserCreator userCreator;
        if ("Client".equals(userType)) {
            userCreator = new ClientCreator();
        } else {
            userCreator = new AdminCreator();
        }

        User user = userCreator.createUser();
        users.add(user);
        modifyFile(usersPath, users);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    //Comprobamos si la contraseña coincide
    private boolean checkPassword(String password, User user) {
        boolean correct = password.equals(user.getPassword());

        if (correct) {
            System.out.println("Bienvenido");
        } else {
            System.out.println("Contraseña incorrecta");
        }
        return correct;
    }

    //Verificación del usuario
    public User verifyUser(String nick, String password) {
        boolean correct;

        //Recorremos la lista de usuarios y comprobamos si el nick coincide
        for (User user : users) {
            if (nick.equals(user.getNick())) {
                if ("Client".equals(user.getClassName())) {
                    Client client = (Client) user;
                } else {
                    //Si es administrador comprobamos la contraseña
                    correct = checkPassword(password, user);
                    if (correct) {
                        return user;
                    } else {
                        return null;
                    }
                }
            }
        }
        //Si el nick no coincide
        System.out.println("Nombre de usuario erróneo o no existente");
        return null;
    }

    private void deleteFromClientList(List<Client> list, Client client) {
        int i = 0;
        Client element;
        do {
            element = list.get(i);

            if (element.getNick().equals(client.getNick())) {
                list.remove(i);
            }
            i++;
        } while (!element.getNick().equals(client.getNick()) && (i < list.size()));
    }
}
  