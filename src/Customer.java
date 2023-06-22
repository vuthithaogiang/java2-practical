import exception.InvalidEmailException;
import exception.InvalidPhoneException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Customer implements Comparable<Customer> {
    private String name;
    private String email;
    private String phone;

    public Customer() {

    }

    public Customer(String name, String email, String phone) throws InvalidEmailException , InvalidPhoneException{
        this.name = name;

        setEmail(email);
        setPhone(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email ) throws InvalidEmailException{
        var regex = "^[a-z]+[\\w._]*@gmail.com$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            this.email = email.toLowerCase();
        }
        else{
            this.email = null;
            var msg = "Email is invalid!";
            throw new InvalidEmailException(msg, email);
        }
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone) throws  InvalidPhoneException{

        var regex = "^[0-9\\-\\+]{9,15}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);

        if(matcher.matches()){
            this.phone = phone;
        }
        else{
            this.phone = null;
            var msg = "Phone is invalid!";
            throw  new InvalidPhoneException(msg, phone);
        }
    }

    public String toString() {
        return name + "   " + email + "   " + phone;
    }

    @Override
    public int compareTo(Customer other) {
       return other.phone.compareTo(this.phone);
    }
}
