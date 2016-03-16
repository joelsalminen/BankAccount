/*
Author:
Joel Salminen
joel.salminen@gmail.com
*/
package bankaccount;

import java.util.ArrayList;

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
        
        /* Removing dash-character*/
        removeDash();
        
        /* Checking if the given bank account number contains something
        else than digits */
        if (!isInteger(accountNumber)){
            System.out.println("Virhe: muita kuin numeroita");
        }
        
        /* Transforming the bank account number into machine code: */
        /* se että miten tämä tapahtuu rippuu siitä mikä numero on alussa
        siksi jaetaan ryhmiin a ja b*/
        


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
        calculateHash();
    }
    
    /* Calculates the hash value of the bank account number */
    private void calculateHash(){
        char [] charArray = new char [50];
        ArrayList <Integer> array = new ArrayList();
        int [] digitArray = new int [50];
        int hashValue = 0;
        
        String number = accountNumber.substring(0, accountNumber.length()-1); /* bank account number minus the last digit*/
        charArray = number.toCharArray(); /* splitting the string into an array of characters*/
        
        /* kopioidaan kirjaintaulukko int taulukkoon */
        for (int i = 0; i<charArray.length; i++){
            digitArray[i] = Character.getNumericValue(charArray[i]);
        }
        
        /* kerrotaan joka toinen kahdella*/
        for (int i = 0; i<digitArray.length; i=i+2){
            digitArray[i] = digitArray[i] * 2;
        }
        
        for (int i = 0; i<digitArray.length; i++){
            System.out.print(digitArray[i]);
        }
        
        digitArray = splitInt(digitArray);
        
        /* lasketaan jokainen merkki yhteen*/
        for (int i = 0; i<digitArray.length; i++){
            System.out.print(digitArray[i] + " ");
            hashValue = hashValue + digitArray[i];
        }
        System.out.println(hashValue);
        
        
    }
    
    
    private int[] splitIntegerIntoDigits(int[] array){
        int counter = 0;
        while (counter < array.length){
            while (array[counter]>=10){
                /*jakohässäkkä*/
                    array[array.length] = array[counter] % 10;
                    array[counter] = array[counter] / 10;
                }
            counter ++;
        }
        return array;
    }
    
    
    /* Checks if the input string is an integer */
    private boolean isInteger(String s){
        boolean validInteger = false;
        try{
            Integer.parseInt(s);
            /* throws NumberFormatException, if n contains something else than digits*/
            validInteger = true;
        }
        catch (NumberFormatException ex){
            System.out.println("aaa");
        }
        
        return validInteger;
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
