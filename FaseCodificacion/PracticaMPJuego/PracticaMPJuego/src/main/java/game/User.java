package game;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String nick;
    private String password;
    
    public User(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    public String getNick() {
        return nick;
    }
    
    public String getPassword() {
        return password;
    }
    
    public abstract void receiveNotification(String notification);
    
    //Devuelve el tipo de usuario (client o admin)
    public abstract String getClassName();
}
