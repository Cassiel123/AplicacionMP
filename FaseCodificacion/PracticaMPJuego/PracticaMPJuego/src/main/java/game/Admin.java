
package game;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    
    public Admin(String nick, String password) {
        super(nick, password);
    }
    
    /*
    Primeramente se compureba si el cliente existe y después si está ya en la
    lista de sospechosos de piratería. En caso de no estar, se añade a la lista
    */
    public void addHacker() {
        Controller system = Controller.getSystem();
        List<Client> clients = system.getClients();
        
        if (!clients.isEmpty()) {
            System.out.println("Clientes:");
            system.showClients(clients);
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduzca el nick del sospechoso de piratería que quiere añadir a la lista: ");
            String nick = sc.nextLine();
            Client hacker = null;
            boolean found = false;

            for (Client client : clients) {
                if (client.getNick().equals(nick)) {
                    found = true;
                    hacker = client;
                    break;
                }
            }

            if (found) {
                found = false;
                for (Client client : system.getHackers()) {
                    if (client.getNick().equals(nick)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    System.out.println("Ese cliente ya está en la lista de sospechosos de piratería");
                } else {
                    system.addHacker(hacker);
                }
            } else {
                System.out.println("Ese cliente no existe");
            }
        } else {
            System.out.println("Actualmente no hay clientes registrados en el sistema");
        }
    }
    
    /*
    Primeramente se compureba si el cliente existe y después si está en la
    lista de sospechosos de piratería. En tal caso, se le elimina de la lista
    */
    public void deleteHacker() {
        Controller system = Controller.getSystem();
        List<Client> clients = system.getHackers();
        
        if (!clients.isEmpty()) {
            System.out.println("Sospechosos de piratería:");
            system.showClients(clients);
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduzca el nick del sospechoso de piratería que quiere eliminar de la lista: ");
            String nick = sc.nextLine();
            Client hacker = null;
            boolean found = false;

            for (Client client : system.getClients()) {
                if (client.getNick().equals(nick)) {
                    found = true;
                    hacker = client;
                    break;
                }
            }

            if (found) {
                found = false;
                for (Client client : system.getHackers()) {
                    if (client.getNick().equals(nick)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    system.deleteHacker(hacker);
                } else {
                    System.out.println("Ese cliente no se encuentra en la lista de sospechosos de piratería");
                }
            } else {
                System.out.println("Ese cliente no existe");
            }
        } else {
            System.out.println("Actualmente no hay sospechosos de piratería");
        }
    }
    
    /*
    Primeramente se compureba si el cliente existe y después si está ya en la
    lista de sospechosos de fraude. En caso de no estar, se añade a la lista
    */
    public void addFraudSuspect() {
        Controller system = Controller.getSystem();
        List<Client> clients = system.getClients();
        
        if (!clients.isEmpty()) {
            System.out.println("Clientes:");
            system.showClients(clients);
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduzca el nick del sospechoso de fraude que quiere añadir a la lista: ");
            String nick = sc.nextLine();
            Client fraudSuspect = null;
            boolean found = false;

            for (Client client : system.getClients()) {
                if (client.getNick().equals(nick)) {
                    found = true;
                    fraudSuspect = client;
                    break;
                }
            }

            if (found) {
                found = false;
                for (Client client : system.getFraudSuspects()) {
                    if (client.getNick().equals(nick)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    System.out.println("Ese cliente ya está en la lista de sospechosos de fraude");
                } else {
                    system.addFraudSuspect(fraudSuspect);
                }
            } else {
                System.out.println("Ese cliente no existe");
            }
        } else {
            System.out.println("Actualmente no hay clientes registrados en el sistema");
        }
    }
    
    /*
    Primeramente se compureba si el cliente existe y después si está en la
    lista de sospechosos de fraude. En tal caso, se le elimina de la lista
    */
    public void deleteFraudSuspect() {
        Controller system = Controller.getSystem();
        List<Client> clients = system.getFraudSuspects();
        
        if (!clients.isEmpty()) {
            System.out.println("Sospechosos de fraude:");
            system.showClients(clients);
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduzca el nick del sospechoso de fraude que quiere eliminar de la lista: ");
            String nick = sc.nextLine();
            Client fraudSuspect = null;
            boolean found = false;

            for (Client client : system.getClients()) {
                if (client.getNick().equals(nick)) {
                    found = true;
                    fraudSuspect = client;
                    break;
                }
            }

            if (found) {
                found = false;
                for (Client client : system.getFraudSuspects()) {
                    if (client.getNick().equals(nick)) {
                        found = true;
                        break;
                    }
                }

                if (found) {
                    system.deleteFraudSuspect(fraudSuspect);
                } else {
                    System.out.println("Ese cliente no se encuentra en la lista de sospechosos de fraude");
                }
            } else {
                System.out.println("Ese cliente no existe");
            }
        } else {
            System.out.println("Actualmente no hay sospechosos de fraude");
        }
    }
    
    /*
    Accede a la lista de ofertas provisionales y le permite al administrador
    verificar o rechazar el número de ofertas que él considere 
    */
    public void verifyOffer() {
        Controller system = Controller.getSystem();
        List<Offer> offers = system.getProvisionalOffers();
        boolean aux = true;
        String option;
        Scanner sc = new Scanner(System.in);
        boolean correct;
        
        if (!offers.isEmpty()) {
            while (aux && !offers.isEmpty()) {
                Offer offer = offers.get(0);
                System.out.println("Oferta a verificar:");
                offer.showOffer();
                do {
                    System.out.print("¿Desea verificar la oferta? Introduzca 1 para SI y 0 para NO: ");
                    option = sc.nextLine();
                    correct = option.equals("0") || option.equals("1");
                    
                    if (!correct) {
                        System.out.println("Número introducido no válido\n");
                    }
                } while(!correct);
                
                if (option.equals("1")) {
                    system.addOffer(offer);
                    system.deleteProvisionalOffer(offer);
                    List<String> shipTypes = new ArrayList<>();
                    
                    for (Ship ship: offer.getShips()){
                        String shipType = ship.getShipType();
                        
                        if (!shipTypes.contains(shipType)) {
                            system.notifySuscribers(shipType);
                            shipTypes.add(shipType);
                        }
                    }
                }
                else {
                    system.deleteProvisionalOffer(offer);
                    system.notifyInvalidOffer(offer.getSeller());
                }
                
                if (!offers.isEmpty()) {
                    do {
                        System.out.print("¿Quieres seguir verificando ofertas? Introduzca 1 para SI y 0 para NO: ");
                        option = sc.nextLine();
                        correct = option.equals("0") || option.equals("1");
                    
                        if (!correct) {
                            System.out.println("Número introducido no válido\n");
                        }
                    } while (!correct);
                    aux = option.equals("1");
                }
                else {
                    System.out.println("No hay más ofertas por verificar");
                }
            }
        }
        else {
            System.out.println("No hay ofertas provisionales actualmente");
        }
    }
    
    //Método vacío, por el momento los administradores no necesitan recibir notificaciones
    @Override
    public void receiveNotification(String notification) {}
    
    @Override
    public String getClassName() {
        return "Admin";
    }
    
}
