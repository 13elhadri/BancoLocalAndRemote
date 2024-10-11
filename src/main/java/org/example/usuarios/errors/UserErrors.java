package org.example.usuarios.errors;

public abstract class UserErrors {
    private final String message;
    public String getMessage(){
        return message;
    }

    public UserErrors(String message){
        this.message = message;
    }

    /**
     * Excepci�n para nombres de usuario inv�lidos
     * @author Javier Hern ndez, Yahya el hadri, Javier Ruiz, Alvaro herrero, Samuel Cortes, Raul Fernandez
     * @version 1.0
     */
    public static class NombreInvalido extends UserErrors{
        public NombreInvalido(String message){
            super("ERROR: " + message);
        }
    }

    /**
     * Excepci�n para correos electr�nicos inv�lidos
     * @author Javier Hern ndez, Yahya el hadri, Javier Ruiz, Alvaro herrero, Samuel Cortes, Raul Fernandez
     * @version 1.0
     */
    public static class EmailInvalido extends UserErrors{
        public EmailInvalido(String message){
            super("ERROR: " + message);
        }
    }
}
