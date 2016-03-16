/*
Author:
Joel Salminen
joel.salminen@gmail.com
*/
package bankaccount;

public class FinnishBankAccount {
    String accountNumber;
    int[] groupA = {1,2,31,33,34,36,37,38,39};
    int[] groupB = {4,5,6,8};
    
    public FinnishBankAccount(String aNumber){
        accountNumber = aNumber;
        checkValidity();
    }
    private void checkValidity(){
        /* checking if the given bank account number is valid */
        
        /* Checking if the input lenght is between 9-15*/
        if (accountNumber.length()<9 || accountNumber.length() > 15){
            System.out.println("Virhe: pituus");
        }
        
        /* Checking if the 7th character of the input is a dash */
        if (accountNumber.charAt(6) != '-'){
            System.out.println("Virhe: väliviiva");
        }
        
        /* Transforming the bank account number into machine code: */
        
        /* tarkistaa mihin ryhmään tää numero tungetaan*/
        if (contains(groupA, Integer.parseInt(accountNumber.substring(0,1))) 
                || contains(groupA, Integer.parseInt(accountNumber.substring(0,2)))){
            System.out.println("ryhmä A");
        }
        else if (contains(groupB, Integer.parseInt(accountNumber.substring(0,1)))){
            System.out.println("ryhmä B");
        }
        else{
            System.out.println("virhe: alku");
        }

    }
    
    /* Checks if an array contains a certain integer value*/
    private boolean contains(int[] intArray, int value){
        for (int i : intArray){
            if (value == i){
                return true;
            }
        }
        return false;
    }
}
