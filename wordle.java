import java.io.*;
import java.util.Scanner;
import java.util.HashMap;


public class wordle{


    static String isSolved = "0";

    public static void main(String[] args) {
        gameLoop((selectWord()));

    }

    // randomly picks a 5 letter words from a list of 500 5 letter words
    public static String selectWord(){
        String line = "";
        int num = (int)(Math.random()*500);
        try (BufferedReader br = new BufferedReader(new FileReader("5letWords.txt"))) {
            for (int i = 0; i < num; i++)
                br.readLine();
            line = br.readLine();
        }
        catch(Exception e){

        }
        return line;
    }

    /*
    */
    public static void gameLoop(String key){
        Scanner reader = new Scanner(System.in);
        boolean solved = false;
        int guesses = 0;
        String guess;
        // Map keyMap = createMap(key);
        while (guesses < 5 && !solved){
            guess = reader.nextLine();
            while(!verifyIn(guess)){
                guess = reader.nextLine();
            }
            String comped = wordleComp(guess, key);
            System.out.println(comped);
            if(comped.equals(isSolved)){
                solved = true;
            }
            guesses++;
        }
        if(solved)
            System.out.println("Winner!");
        else
            System.out.println("Loser!");
        System.out.println("Thanks for playing!");
    }


    //Indexof might be better
    public static String wordleComp(String guess, String key){
        String eldrow = "";
        Boolean isCorrect = false;
        guess = guess.toLowerCase();
        for(int i = 0; i < 5; i ++){
            char curChar = guess.charAt(i);
            if (key.contains(Character.toString(curChar))){
                if(key.charAt(i) == curChar){
                    eldrow = eldrow + Character.toUpperCase(curChar);
                }
                else{
                    eldrow = eldrow + curChar;
                }
            }
            else{
                eldrow = eldrow +  "-";
            }
        }
        if(isCorrect)
            eldrow = isSolved;
        return eldrow;
    }

    //Checks if user input is 5 letters
    //Does not check if input is actually a word
    public static boolean verifyIn(String input){
        if(input.length() != 5){
            System.out.println("Input needs to be length 5");
            return false;
        }
        char[] chars = input.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                System.out.println("Input needs to only be letters");
                return false;
            }
        }
        return true;
    }

    //  potential hashing
    // public static HashMap createMap(String key){
    //     Map keyMap = new HashMap();
    //     key = key.totoUpperCase();
    //     for (int i = 0; i < key.length(); i++){
    //         keyMap.put(i, key[i]);
    //     }
    //     return keyMap;
    // }
    //
    // public static String compareIn(String input, Hashmap keyMap){
    //     HashMap inMap = createMap(input);
    //
    // }
}
