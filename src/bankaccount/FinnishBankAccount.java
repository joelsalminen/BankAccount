/*
Author:
Joel Salminen
joel.salminen@gmail.com
*/
package bankaccount;

public class FinnishBankAccount {
    int accountNumber;
    String sAccountNumber;
    
    public FinnishBankAccount(String aNumber){
        sAccountNumber = aNumber;
        checkValidity();
    }
    private void checkValidity(){
        /* Checks if the given bank account number is valid */
        
        /* check if the input lenght is between 9-15*/
        if (sAccountNumber.length()<9 || sAccountNumber.length() > 15){
            System.out.println("Virhe: pituus");
        }
        
        /* checks if the 7th character of the input is a dash */
        if (sAccountNumber.charAt(6) != '-'){
            System.out.println("Virhe: väliviiva");
        }
    }
}
