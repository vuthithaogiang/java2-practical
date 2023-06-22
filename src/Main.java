import exception.InvalidEmailException;
import exception.InvalidPhoneException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidPhoneException, InvalidEmailException {
        HashMap<String, String> customerHashMap = new HashMap<>();
        var choice = 0;
        Scanner input = new Scanner(System.in);

        do{
            System.out.println("==================== CUSTOMER RELATIONSHIP MANAGEMENT ===============");
            System.out.println("1. Add new Customer");
            System.out.println("2. Find customer by Name");
            System.out.println("3. Display all");
            System.out.println("0. Exit");
            System.out.println("=====================================================================");

            System.out.println("Please enter your choice? ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice){
                case 0:
                    System.out.println("Thanks for using!");
                    break;
                case 1:
                {
                    Customer customer = saveCustomer(input);
                        if(customer == null){
                            System.out.println("Add fail!");
                        }
                        else{
                           if(customerHashMap.size() > 0) {
                               if(checkKeyExist(customerHashMap, customer.getName())
                                       && checkValueExist(customerHashMap, customer.getPhone())){
                                   System.out.println("This name and phone is existed!");
                               }
                               else{
                                   customerHashMap.put(customer.getName(), customer.getPhone());
                                   System.out.println("Add customer successfully!");
                               }
                           }
                           else{
                               customerHashMap.put(customer.getName(), customer.getPhone());
                               System.out.println("Add customer successfully!");
                        }
                    }
                    break;
                }

                case 2:
                    if(customerHashMap.size() > 0) {
                        System.out.println("Enter your name that you want to search: ");
                        var nameSearch = input.nextLine();
                       ArrayList<String> result = findByName(customerHashMap, nameSearch );

                       if(result.size() > 0){
                           System.out.println("===> List phone's customer: " + nameSearch);

                           for(var item : result){
                               System.out.println(item);
                           }
                       }
                       else{

                           System.out.println("Not Found");
                       }
                    }
                    else{
                        System.out.println("Please add customer first!");
                    }
                    break;
                case 3:
                {
                    if(customerHashMap.size() > 0) {
                        System.out.println("===== CRM =====");
                        System.out.println("Name ----------------------------Phone");
                        showAll(customerHashMap);
                    }
                    else{
                        System.out.println("Please add customer first!");
                    }
                    break;
                }

                default:
                    System.out.println("Invalid option. Please choice again!");
                    break;
            }


        }
        while(choice != 0);

    }

    private static ArrayList<String> findByName(HashMap<String, String> map, String name){
        ArrayList<String> listPhone = new ArrayList<>();
         if(map.containsKey(name)){
           listPhone.add(map.get(name));
         }
         return listPhone;
    }

    private  static  boolean checkKeyExist(HashMap<String, String> map, String key) {
       if(map.containsKey(key)){
           return true;
       }
        return false;
    }

    private static boolean checkValueExist(HashMap<String , String> map, String value ){
           for(String key : map.keySet()){
               if(map.get(key).compareTo(value) == 0){
                   return  true;
               }
           }
           return false;
    }

    private static void showAll(HashMap<String, String> hashMap){
        hashMap.forEach((key, value) -> System.out.println(key   + "-------" + value));
    }

    private static Customer saveCustomer(Scanner input) throws InvalidPhoneException, InvalidEmailException{

        Customer customer;
        System.out.println("Enter your name: ");
        var name = input.nextLine();

        System.out.println("Enter your email");
        var email = input.nextLine();


        System.out.println("Enter your phone number: ");
        var phone = input.nextLine();

        try{
            customer = new Customer(name, email, phone);

        }
        catch ( InvalidEmailException e) {
            return null;
        }
        catch (InvalidPhoneException e){
            return null;
        }

        return customer;
    }
}