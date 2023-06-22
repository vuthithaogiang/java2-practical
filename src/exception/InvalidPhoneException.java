package exception;

public class InvalidPhoneException  extends Exception{
    private String invalidPhone;

    public InvalidPhoneException() {

    }

    public InvalidPhoneException(String message, String invalidPhone){
        super(message);
        this.invalidPhone = invalidPhone;
    }

    public String getInvalidPhone() {
        return invalidPhone;
    }
}
