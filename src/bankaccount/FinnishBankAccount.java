/*
Author:
Joel Salminen
joel.salminen@gmail.com
*/
package bankaccount;

public class FinnishBankAccount {
    private String accountNumber = "";
    private String longFormat = "";
    private String bankName = "";
    
    /* The process of transforming bank account numbers into long format varies
    slightly depending on what the first one or two digits of the bank account
    number are. Arrays group A and groupB contain all the possible combinations
    for different first digits. */
    private final int[] groupA = {1,2,31,33,34,36,37,38,39};
    private final int[] groupB = {4,5,6,8};
    
    private final int[] bankID = {1,2,31,33,34,36,37,38,39,4,5,6,8};
    private final String[] bankNames = {"Nordea","Nordea","SHB","SEB","Danske Bank","Tapiola",
        "DnB NOR","Swedbank","S-Pankki","Sp/Pop/Aktia","OP/OKO/Okopankki","ÅAB","Sampo"};
    
    /* Constructor, requires a 9-15 digit long bank account number as a parameter*/
    public FinnishBankAccount(String aNumber){
        accountNumber = aNumber;
        
        if (checkValidity()){
            for (int i=0;i<bankID.length;i++){
                if (Integer.parseInt(accountNumber.substring(0, 1)) == bankID[i] || 
                    Integer.parseInt(accountNumber.substring(0, 2)) == bankID[i] ){
                    
                    bankName = bankNames[i];
                break;
                }
            }
        }
    }
    
    
    /* Returns the variable accountNumber */
    public String getAccountNumber(){
        return accountNumber;
    }
    
    
    /* Returns the long format of the bank account number */
    public String getLongFormat(){
        return longFormat;
    }
    
    
    /* Returns the name of the bank */
    public String getBankName(){
        return bankName;
    }
    
    
    /* checks if the given bank account number is valid */
    private boolean checkValidity(){
        
        /* Checking if the input lenght is between 9-15*/
        if (accountNumber.length()<9 || accountNumber.length() > 15){
            System.out.println("Syötetty tilinumero ei kelpaa.");
            return false;
        }
        
        /* Checking if the 7th character of the input is a dash */
        if (accountNumber.charAt(6) != '-'){
            System.out.println("Syötetty tilinumero ei kelpaa.");
            return false;
        }
        
        /* Removing the 7th character, which at this point has to be a dash */
        accountNumber = accountNumber.substring(0,6) + accountNumber.substring(7);
        
        /* Checking if the given bank account number contains something
        other than digits */
        if (!isLong(accountNumber)){
            System.out.println("Syötetty tilinumero ei kelpaa.");
            return false;
        }
        
        /* Checking if the hash code at the end of the bank account number is correct */
        /* Comparing the first two digits to the values in arrays groupA and groupB.
        These groups are utilized when transforming the bank account number into 
        long format code. */
        if (contains(groupA, Integer.parseInt(accountNumber.substring(0,1))) 
                || contains(groupA, Integer.parseInt(accountNumber.substring(0,2)))){
            longFormat = toLongFormat("A");
        }
        else if (contains(groupB, Integer.parseInt(accountNumber.substring(0,1)))){
            longFormat = toLongFormat("B");
        }
        else{
            /* Illegal first two digits*/
            System.out.println("Syötetty tilinumero ei kelpaa.");
            return false;
        }

        /* Checking if the last digit of the bank account number matches the 
        calculated hash value */
        if (calculateHash() != Integer.parseInt(longFormat.substring(13, 14))){
            System.out.println("Syötetty tilinumero ei kelpaa.");
            return false;
        }
        return true;
    }
    
    
    /* Calculates the hash value of the bank account number */
    private int calculateHash(){
        char [] charArray;
        int [] digitArray = new int [13];
        int sumOfDigits = 0;

        String number = longFormat.substring(0, longFormat.length()-1); /* bank account number minus the last digit*/
        charArray = number.toCharArray(); /* splitting the string into an array of characters */

        /* Copying the charArray into the digitArray */
        for (int i = 0; i<charArray.length; i++){
            digitArray[i] = Character.getNumericValue(charArray[i]);
        }

        /* Every other element is multiplied by two */
        for (int i = 0; i<digitArray.length; i=i+2){
            digitArray[i] = digitArray[i] * 2;
        }

        /* Calculating the sum of all individual digits in the array */
        sumOfDigits = calculateSumOfDigits(digitArray);
        return (roundUpwards(sumOfDigits) - sumOfDigits);
    }
    
    
    /* Rounds the input integer  up towards the next number that can be divided by 10 */
    private int roundUpwards(int n){
        if (n % 10 == 0){
            return n;
        }
        return (n - (n % 10) + 10);
    }
    
    
    /* Calculates the sum of all digits in an array
    For example [1, 2, 13] would return 1+2+1+3 = 7 */
    private int calculateSumOfDigits(int [] array){
        int sum = 0;
        for (int i = 0; i<array.length; i++){
            while (array[i] >= 10){ /*special case; when the number contains more than one digit*/
                sum = sum + array[i] % 10;
                array[i] = array[i] / 10;
            }
            sum = sum + array[i];
        }
        return sum;
    }
    
    
    /* Checks if the input string value can be converted into long int */
    private boolean isLong(String n){
        boolean validLong = false;
        try{
            Long.parseLong(n);
            /* throws NumberFormatException, if n consists of something else than digits*/
            validLong = true;
        }
        catch (NumberFormatException ex){
        }
        return validLong;
    }
    
    
    /* Transforms the bank account number into long format by adding 
    zeroes into coccect places until the length of the string is 14. */
    private String toLongFormat(String group){
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
        return temp1 + temp2;
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