/*
Author:
Joel Salminen
joel.salminen@gmail.com
*/
package bankaccount;

public class FinnishBankAccount {
    int accountNumber;
    
    public FinnishBankAccount(int aNumber){
        accountNumber = aNumber;
        checkValidity();
    }
    private void checkValidity(){
        if (accountNumber == 1){
            System.out.println("Virhe");
        }
    }
}
