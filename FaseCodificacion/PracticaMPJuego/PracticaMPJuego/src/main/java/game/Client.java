package game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client extends User {
    
    private String name;
    private String planet;
    private String specie;
    private int id = 0;
    private String email;
    private int warnings = 0;
    private LocalDateTime bannedDate = null;
    private boolean fraudSuspect = false;
    private List<Double> scores = new ArrayList<>();
    private double globalScore = 0;
    private List<String> comments = new ArrayList<>();
    private List<String> notifications = new ArrayList<>();
    private List<String> suscriptions = new ArrayList<>();
    private List<Ship> shipsToSell = new ArrayList<>();

    public Client(String nick, String password, String name, String planet, String specie, String email, int id) {
        super(nick, password);
        this.name = name;
        this.planet = planet;
        this.specie = specie;
        this.email = email;
        this.id = id;
    }

    public void addShip(Ship ship) {
        shipsToSell.add(ship);
    }

    //Elimina las naves que han sido compradas en una oferta
    public void deleteShips(List<Ship> ships) {
        for (Ship ship : ships) {
            int i = 0;
            
            do {
                ship = shipsToSell.get(i);

                if (ship.equals(ship)) {
                    shipsToSell.remove(i);
                }
                i++;
            } while ((!ship.equals(ship)) && (i < shipsToSell.size()));
        }
    }

    public String getSpecie() {
        return specie;
    }

    public int getWarnings() {
        return warnings;
    }

    public LocalDateTime getBannedDate() {
        return bannedDate;
    }

    public boolean isFraudSuspect() {
        return fraudSuspect;
    }
    
    public void setFraudSuspect() {
        fraudSuspect = true;
    }
    
    public void deleteFraudSuspect() {
        fraudSuspect = false;
    }

    public String getName() {
        return name;
    }

    public String getPlanet() {
        return planet;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Ship> getShipsToSell() {
        return shipsToSell;
    }

    public List<String> getNotifications() {
        return notifications;
    }

    public void setBannedDate(LocalDateTime bannedDate) {
        this.bannedDate = bannedDate;
    }
    
    public List<String> getSuscriptions() {
        return suscriptions;
    }

    public List<Double> getScores() {
        return scores;
    }

    public double getGlobalScore() {
        return globalScore;
    }
    
    /*
    Comprobamos is el número de advertencias es de 2. Si es así, resetamos las
    advertencias a 0 y guardamos la fecha actual del sistema en la fecha de 
    penalización, para poder contar los días de penalización
     */
    public void increaseWarnings() {
        warnings += 1;
        if (warnings == 2) {
            warnings = 0;
            bannedDate = LocalDateTime.now();
            receiveNotification("El número de advertencias ha superado el límite");
            receiveNotification("Queda penalizado sin poder entrar durante 5 días");
        }
    }

    public void resetDate() {
        bannedDate = null;
    }

    //Añade el tipo de nave a la lista de suscripciones
    public void suscribe(String shipType) {
        suscriptions.add(shipType);
    }

    //Elimina el tipo de nave de la lista de suscripciones
    public boolean unsuscribe(String shipType) {
        return suscriptions.remove(shipType);
    }

    public void showSuscriptions() {
        if (!suscriptions.isEmpty()) {
            System.out.println("Suscripciones:");
            
            for (String suscription : suscriptions) {
                System.out.println("-Estás suscrito al tipo de nave: " + suscription);
            }
        } else {
            System.out.println("Actualmente no está suscrito a ningún tipo de nave");
        }
    }

    @Override
    public void receiveNotification(String notification) {
        notifications.add(notification);
    }

    //Muestra la lista de notificaciones y las borra de la bandeja de entrada
    public void showNotifications() {
        if (!notifications.isEmpty()) {
            System.out.println("Notificaciones:");
            String notification;
            
            do{
                notification = notifications.get(0);
                System.out.println(notification);
                notifications.remove(notification);
            } while (!notifications.isEmpty());
        } else {
            System.out.println("Actualmente no tiene notificaciones");
        }
        Controller system = Controller.getSystem();
        List<User> users = system.getUsers();
        system.modifyFile(system.getUsersPath(), users);
    }

    public void receiveScore(double score) {
        scores.add(score);
        updateGlobalScore();
    }

    private void updateGlobalScore(){
        double average = 0;
        int i = 0;
        
        for (Double score: scores){
            average += score;
            i++;
        }
        
        globalScore = average / i;
    }

    public void receiveComment(String message) {
        comments.add(message);
    }
    
    /*
    Muestra la lista de comentarios que han dejado otros clientes al realizar
    una compra de alguna de sus ofertas, junto con la valoración media
    */
    public void showComments() {
        if (!comments.isEmpty()) {
            System.out.println("Comentarios de otros clientes:");
            
            for (String comment : comments) {
                System.out.println(comment);
            }
            
            System.out.println("\nValoración media: " + globalScore);
        } else {
            System.out.println("Actualmente no tiene comentarios de otros clientes");
        }
    }

    public void createOffer() {
        Controller system = Controller.getSystem();
        Scanner sc = new Scanner(System.in);
        List<Ship> ships = new ArrayList<>();
        
        if (!shipsToSell.isEmpty()) {
            System.out.println("Naves que tiene para vender:");

            for (Ship ship : shipsToSell) {
                ship.showShip();
                System.out.println();
            }
            
            String selection;
            
            do {
                System.out.print("Introduzca el número de registro de la nave que desea añadir a la oferta. "
                    + "Teclee \"Cancelar\" para terminar de añadir naves: ");
                selection = sc.nextLine();
                
                if (!selection.toUpperCase().equals("CANCELAR")) {
                    boolean found = false;
                    
                    for (Ship ship : shipsToSell) {
                        if (selection.equals(ship.getRegNumber()) && (!ships.contains(ship))) {
                            ships.add(ship);
                            found = true;
                            break;
                        }
                    }
                    
                    if (!found) {
                        System.out.println("Ese número de registro no existe\n");
                    }
                }
            } while (!selection.toUpperCase().equals("CANCELAR"));

            if (!ships.isEmpty()) {
                double price = askForPrice();
                selection = askForDate() + "T00:00:00";
                LocalDateTime limitDate = LocalDateTime.parse(selection);
                
                Offer offer = new Offer(this, ships, price, limitDate);
                system.addProvisionalOffer(offer);
            } else {
                System.out.println("No se puede crear una oferta sin seleccionar al menos una nave");
            }
        } else {
            System.out.println("Actualmente no tiene ninguna nave registrada en el sistema");
        }
    }
    
    //Pregunta al cliente el precio de la oferta y comprueba que el valor introducido es válido
    private double askForPrice() {
        Scanner sc = new Scanner(System.in);
        double price = 0;
        boolean correct;
        
        do {
            System.out.print("Introduzca el precio de la oferta: ");
            String priceText = sc.nextLine();
            correct = true;
            
            try {
                price = Double.parseDouble(priceText);
            } catch (Exception e) {
                System.out.println("Opción inválida\n");
                correct = false;
            }
        } while (!correct);
        
        return price;
    }
    
    /*
    Pide al usuario que introduzca por teclado la fecha de expiración de la oferta
    y comprueba que se ajusta al formato indicado, utilizando una expresión regular
    */
    private String askForDate() {
        Scanner sc = new Scanner(System.in);
        String date;
        boolean correct;
        
        do {
            System.out.print("Indique la fecha límite de la oferta, con el formato YYYY-MM-DD: ");
            date = sc.nextLine();
            Pattern pattern = Pattern.compile("20\\d{2}-(0[1-9]|1[0-2])-(3[01]|[12][0-9]|0[1-9])");
            Matcher mat = pattern.matcher(date);
            correct = mat.matches();

            if (!correct) {
                System.out.println("Formato incorrecto\n");
            }
        } while (!correct);
        
        return date;
    }
    
    @Override
    public String getClassName() {
        return "Client";
    }
}
