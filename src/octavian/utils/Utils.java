/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package octavian.utils;

/**
 *
 * @author octavian
 */
public class Utils {
    public static String bytesToString(byte[] text){
        StringBuilder sb = new StringBuilder();
        
        for(byte chr : text){
            sb.append(Byte.toString(chr));
        }
        
        return sb.toString();
    }
    
     /**
     * Used to generate the filenames
     * @param filename Name of the file we process
     * @return name without extension
     */
    public static String getFilenameWithoutExtension(String filename){
        return filename.substring(0, filename.indexOf("."));
    }
    
   /**
    * Folosit in PlayFair
    * @param matrix Matricea pe care o verificam
    * @param size Marimea matricei tinand cont ca este patratica
    * @return numarul de locuri libere
    */
    public static int tableFreeSpace(char[][] matrix, int size){
        int result = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(matrix[i][j] == 0){
                    result++;
                }
            }
        }
        
        return result;
    }
    
    public static boolean isEven(int number){
        return number % 2 == 0;
    }
    
    public static int getElementX(char[][] matrix, char x){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(x == matrix[i][j]){
                    return i;
                }
            }
        }
        
        return -1;
    }
    
    public static int getElementY(char[][] matrix, char x){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(x == matrix[i][j]){
                    return j;
                }
            }
        }
        
        return -1;
    }
    
    /**
     * Printarea unei matrici patratice
     * @param matrix
     * @param VALUE 
     */
    public static void printMatrix(char[][] matrix, final int VALUE){
        for(int i = 0; i < VALUE; i++){
            for(int j = 0; j < VALUE; j++){
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }
    
    /**
     * Functie utilizata pentru a obtine ordinea unui caracter in alfabet
     * @param x Litera
     * @return Numarul de ordine incepand de la 0
     */
    public static int orderInAlphabet(char x){
        int result;
        char letter = Character.toUpperCase(x);
        
        int ascii = (int) letter;
        result = ascii - 65;
        
        return result;
    }
    
    public static boolean isNumber(char n){
        char[] list = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        
        for(char c : list){
            if(c == n){
                return true;
            }
        }
        
        return false;
    }
    
    public static String numberToBin(int n){
        StringBuilder sb = new StringBuilder();
        StringBuilder aux_sb = new StringBuilder();
        int aux = n;
        
        while(aux != 0){
            if(n % 2 != 0 ){
                aux_sb.append("1");
            }else{
                aux_sb.append("0");
            }
            
            aux = aux / 2;
        }
        
        //Reverse the String
        for(int i = aux_sb.length(); i>0; i++){
            sb.append(aux_sb.charAt(i));
        }
        
        return sb.toString();
    }
    
    public static String hexToBin(String hex){
        int i = Integer.parseInt(hex, 16);
        return Integer.toBinaryString(i);
    }
    
    public static String XOR(String x1, String x2){
        StringBuilder result = new StringBuilder();
        
        for(int i = 0; i < x1.length(); i++){
            if(x1.charAt(i) == x2.charAt(i)){
                result.append('0');
            }else{
                result.append('1');
            }
        }
        
        return result.toString();
    }
}
