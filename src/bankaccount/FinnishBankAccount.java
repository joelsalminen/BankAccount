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
        
        /* checks if the 7th character of the input is "-"? */
        if (sAccountNumber.charAt(6) != '-'){
            System.out.println("Virhe");
        }
    }
}
