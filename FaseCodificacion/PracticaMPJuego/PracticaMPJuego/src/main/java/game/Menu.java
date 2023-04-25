package game;



import java.util.List;
import java.util.Scanner;

import java.util.Scanner;

class Menu {

    private Controller system = Controller.getSystem();

    //Método principal del programa. Muestra el menú inicial de identificación
    public void run() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String option;
            boolean correct;

            do {
                System.out.println("BIENVENIDO AL VIDEOJUEGO");
                System.out.println("0: Iniciar sesión");
                System.out.println("1: Registrarse");
                System.out.print("Seleccione una de las opciones: ");
                option = sc.nextLine();
                correct = option.equals("0") || option.equals("1");
                
                if (!correct) {
                    System.out.println("Opción inválida\n");
                }
            } while (!correct);

            switch (option) {
                case "0": {
                    logIn();
                    break;
                }
                case "1": {
                    signIn();
                    break;
                }
            }
        }
    }
    
    //Método para iniciar sesión
    private void logIn() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduzca su nick: ");
        String nick = sc.nextLine();
        System.out.print("Introduzca su contraseña: ");
        String password = sc.nextLine();
        User user = system.verifyUser(nick, password);

        if (user != null) {
            String userType = user.getClassName();

            if (userType.equals("Client")) {
                Client client = (Client) user;
                showClientInterface(client);
            } else {
                Admin admin = (Admin) user;
                showAdminInterface(admin);
            }
        }
    }
    
    //Método para registrarse en el sistema
    private void signIn() {
        Scanner sc = new Scanner(System.in);
        String option;
        boolean correct;

        do {
            System.out.println("TIPOS DE USUARIO");
            System.out.println("0: Administrador");
            System.out.println("1: Cliente");
            System.out.print("Seleccione el tipo de usuario que desea crear: ");
            option = sc.nextLine();
            correct = option.equals("0") || option.equals("1");

            if (!correct) {
                System.out.println("Opción inválida\n");
            }
        } while (!correct);

        if (option.equals("0")) {
            system.createUser("Admin");
        } else {
            system.createUser("Client");
        }
    }
    
    //Muestra el menú de opciones de los clientes
    private void showClientInterface(Client client) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        do {
            System.out.println("MENÚ DE OPCIONES");
            System.out.println("0: Crear Personaje");
            System.out.println("1: Dar de baja Personaje");
            System.out.println("2: Galeria de armas y armaduras");
            System.out.println("3: Crear desafío");
            System.out.println("4: Desafíos entrantes");
            System.out.println("5: Historial y ganancias");
            System.out.println("6: Ranking Global");
            System.out.println("7: Borrar cuenta");
            System.out.println("8: Cerrar sesión");
            System.out.print("Seleccione una de las opciones: ");
            String option = sc.nextLine();
            boolean correct;

            switch (option) {
                case "0": {
                    do {
                        System.out.println("TIPOS DE PERSONAJE");
                        System.out.println("0: Hombre lobo");
                        System.out.println("1: Vampiro");
                        System.out.println("2: Cazador");
                        System.out.print("Seleccione el tipo de personaje que quiere crear: ");
                        option = sc.nextLine();
                        correct = option.equals("0") || option.equals("1") || 
                                option.equals("2");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);
                    
                    switch (option) {
                        case "0": {
                            
                            break;
                        }
                        case "1": {
                           
                            break;
                        }
                        case "2": {
                            
                            break;
                        }
                    }
                    
                    break;
                }
                case "1": {
                     do {
                        System.out.println("OPCIONES BORRAR PERSONAJE");
                        System.out.println("0: OPCION 1");
                        
                        System.out.print("Seleccione: ");
                        option = sc.nextLine();
                        correct = option.equals("0");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);
                    
                    switch (option) {
                        case "0": {
                            
                            break;
                        }
                    }
                    
                    break;
                }
                case "2": {                    
                     do {
                        System.out.println("Galería de armas y armaduras");
                        System.out.println("0: OPCION 1");
                        System.out.print("Seleccione: ");
                        option = sc.nextLine();
                        correct = option.equals("0");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);
                    
                    switch (option) {
                        case "0": {
                            
                            break;
                        }
                    }
                    
                    break;
                }
                case "3": {
                    do {
                        System.out.println("Crear Desafio");
                        System.out.println("0: OPCION 1");
                        System.out.print("Seleccione: ");
                        option = sc.nextLine();
                        correct = option.equals("0");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);
                    
                    switch (option) {
                        case "0": {
                            
                            break;
                        }
                    }
                    
                    break;
                }
                case "4": {
                   do {
                        System.out.println("Desafios entrantes");
                        System.out.println("0: OPCION 1");
                        System.out.print("Seleccione: ");
                        option = sc.nextLine();
                        correct = option.equals("0");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);
                    
                    switch (option) {
                        case "0": {
                            
                            break;
                        }
                    }
                    
                    break;
                }
                case "5": {
                    do {
                        System.out.println("Historial y ganancias (Pulsa 0 para salir)");
                        System.out.println("Oro total: x");
                        System.out.print("Lista con info ultimos 5 enfrentamientos o asi");
                        option = sc.nextLine();
                        correct = option.equals("0");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);
                    
                    switch (option) {
                        case "0": {
                            
                            break;
                        }
                    }
                    
                    break;
                }
                case "6": {
                    do {
                        System.out.println("Ranking global (0 para salir)");
                        System.out.println("Lista jugadores con mas oro o victorias");
                        
                        option = sc.nextLine();
                        correct = option.equals("0");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);
                    
                    switch (option) {
                        case "0": {
                            
                            break;
                        }
                    }
                    
                    break;
                }
                case "7": {
                     do {
                        System.out.println("Borrar cuenta");
                        System.out.println("0: OPCION 1");
                        
                        System.out.print("Seleccione: ");
                        option = sc.nextLine();
                        correct = option.equals("0");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);
                    
                    switch (option) {
                        case "0": {
                            
                            break;
                        }
                    }
                    
                    break;
                }
                case "8": {
                    do {
                        System.out.print("¿Estás seguro? Introduzca 1 para SI y 0 para NO: ");
                        option = sc.nextLine();
                        correct = option.equals("0") || option.equals("1");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);

                    exit = option.equals("1");

                    if (exit) {
                        System.out.println("Saliendo...\n");
                    }

                    break;
                }
                default: {
                    System.out.println("La opción escogida no es correcta\n");
                }
            }
        } while (!exit);
    }
  
    //Muestra el menú de opciones de los administradores
    private void showAdminInterface(Admin admin) {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        do {
            System.out.println("MENÚ DE OPCIONES");
            System.out.println("0: Administrar baneos");
            System.out.println("1: Administrar personajes");
            System.out.println("2: Validar desafios");
            System.out.println("3: Borrar cuenta");
            System.out.println("4: Cerrar sesión");
            System.out.print("Seleccione una de las opciones: ");
            String option = sc.nextLine();
            boolean correct;

            switch (option) {
                case "0": {
                    do {
                        System.out.print("ADMINISTRAR BANEOS ");
                        System.out.println("0: Agregar ban");
                        System.out.println("1: Eliminar ban");
                        System.out.print("Seleccione una de las opciones: ");
                        option = sc.nextLine();
                        correct = option.equals("0") || option.equals("1");

                        switch (option) {
                            case "0": {
                                //admin.addBanned(); metodo en clase admin
                                break;
                            }
                            case "1": {
                                //admin.deleteBanned();metodo en clase admin
                                break;
                            }
                            default: {
                                System.out.println("La opción escogida no es correcta\n");
                            }
                        }
                    } while (!correct);

                    break;
                }
                case "1": {
                    do {
                        System.out.print("ADMINISTRAR PERSONAJE");
                        System.out.println("0: Editar características");
                        System.out.println("1: Armas, armaduras y esbirros");
                        System.out.print("Seleccione una de las opciones: ");
                        option = sc.nextLine();
                        correct = option.equals("0") || option.equals("1");

                        switch (option) {
                            case "0": {
                                break;
                            }
                            case "1": {
                                break;
                            }
                            default: {
                                System.out.println("La opción escogida no es correcta\n");
                            }
                        }
                    } while (!correct);

                    break;
                }
                case "2": {
                    System.out.print("(Lista de los desafios)");
                    //admin.verifyChallenge(); metodo en clase admin
                    break;
                }
                 case "3": {
                     do {
                        System.out.println("Borrar cuenta");
                        System.out.println("0: OPCION 1");
                        
                        System.out.print("Seleccione: ");
                        option = sc.nextLine();
                        correct = option.equals("0");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);
                    
                    switch (option) {
                        case "0": {
                            
                            break;
                        }
                    }
                    
                    break;
                }
                case "4": {
                    do {
                        System.out.print("¿Estás seguro? Introduzca 1 para SI y 0 para NO: ");
                        option = sc.nextLine();
                        correct = option.equals("0") || option.equals("1");

                        if (!correct) {
                            System.out.println("Opción inválida\n");
                        }
                    } while (!correct);

                    exit = option.equals("1");

                    if (exit) {
                        System.out.println("Saliendo...\n");
                    }

                    break;
                }
                default: {
                    System.out.print("La opción escogida no es correcta\n");
                }
            }
        } while (!exit);
    }
}
