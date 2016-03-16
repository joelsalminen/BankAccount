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
        /* se että miten tämä tapahtuu rippuu siitä mikä numero on alussa
        siksi jaetaan ryhmiin a ja b*/
        
        /* Removing dash-character*/
        removeDash();

        /* Comparing the first two digits to the values in arrays groupA and groupB*/
        if (contains(groupA, Integer.parseInt(accountNumber.substring(0,1))) 
                || contains(groupA, Integer.parseInt(accountNumber.substring(0,2)))){
            System.out.println("ryhmä A");
            toMachineCode("A");
        }
        else if (contains(groupB, Integer.parseInt(accountNumber.substring(0,1)))){
            System.out.println("ryhmä B");
            toMachineCode("B");
        }
        else{
            System.out.println("virhe: alku");
        }

    }
    
    /* Transforms the bank account number into machine code*/
    private void toMachineCode(String group){
        String temp1 = "";
        String temp2 = "";
        
        switch (group) {
            case "A":
                temp1 = accountNumber.substring(0,6);
                temp2 = accountNumber.substring(6);
                break;
            case "B":
                temp1 = accountNumber.substring(0,7);
                temp2 = accountNumber.substring(7);
                break;
        }
        while (temp1.length()+temp2.length() < 14){
            temp1 = temp1 + "0";
        }
        accountNumber = temp1 + temp2;
        System.out.println(accountNumber + " " + accountNumber.length());
    }
    
    
    private void removeDash(){
        /* Removes the 7th letter of the parameter string*/
        accountNumber = accountNumber.substring(0,6) + accountNumber.substring(7);
    }
    
    
    /* Checks if an array contains a certain integer value*/
    private boolean contains(int[] intArray, int value){
        for (int i = 0; i<intArray.length; i++){
            if (intArray[i] == value){
                return true;
            }
        }
        return false;
    }
}
