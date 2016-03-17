/*
Author:
Joel Salminen
joel.salminen@gmail.com
*/
package bankaccount;

public class FinnishBankAccount {
    private String accountNumber;
    private int[] groupA = {1,2,31,33,34,36,37,38,39};
    private int[] groupB = {4,5,6,8};
    
    public FinnishBankAccount(String aNumber){
        accountNumber = aNumber;
        checkValidity();
    }
    
    public String getAccountNumber(){
        return accountNumber;
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
        
        /* Checking if the hash code at the end of the bank account number is correct */
        /* Comparing the first two digits to the values in arrays groupA and groupB*/
        if (contains(groupA, Integer.parseInt(accountNumber.substring(0,1))) 
                || contains(groupA, Integer.parseInt(accountNumber.substring(0,2)))){
            toMachineCode("A");
        }
        else if (contains(groupB, Integer.parseInt(accountNumber.substring(0,1)))){
            System.out.println("ryhmä B");
            toMachineCode("B");
        }
        else{
            System.out.println("virhe: alku");
        }

        if (calculateHash() != Integer.parseInt(accountNumber.substring(13, 14))){
            System.out.println("Virhe: hash");
        }
    }
    
    
    /* Calculates the hash value of the bank account number */
    private int calculateHash(){
        char [] charArray;
        int [] digitArray = new int [13];
        int sumOfDigits = 0;
        int roundedUpSum;

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

        /* lasketaan jokainen merkki yhteen*/
        sumOfDigits = calculateSumOfDigits(digitArray);
        roundedUpSum = roundUpwards(sumOfDigits);
        return (roundedUpSum - sumOfDigits);
    }
    
    
    /* rounds the input integer  up towards the next number that can be divided by 10 */
    private int roundUpwards(int n){
        if (n % 10 == 0){
            return n;
        }
        return (n - (n % 10) + 10);
    }
    
    
    /* Calculates the sum of all digits in an array
    For example [1, 2, 10] equals 1+2+1+0 = 4 */
    private int calculateSumOfDigits(int [] array){
        int sum = 0;
        for (int i = 0; i<array.length; i++){
            while (array[i] >= 10){
                sum = sum + array[i] % 10;
                array[i] = array[i] / 10;
            }
            sum = sum + array[i];
        }
        return sum;
    }
    
    
    /* Checks if the input string is an integer */
    private boolean isInteger(String n){
        boolean validInteger = false;
        try{
            Integer.parseInt(n);
            /* throws NumberFormatException, if n contains something else than digits*/
            validInteger = true;
        }
        catch (NumberFormatException ex){
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