/**
 *
 * @author Rick Wohlleb
 * 11-22-16
 * This program spell checks a given document.  First, a dictionary is made of linked lists with
 * the first character of each corresponding to it's position in the alphabet. After the dictionary
 * is created, any document can be parsed and each word from the document will be compared to the words
 * in the dictionary.  The the data is displayed.
 * 
 */
import java.util.*;
import java.io.*;

public class SpellChecker 
{
    private static long found = 0;
    private static long notFound = 0;
    private static long foundComparisons = 0;
    private static long notFoundComparisons = 0;
    private static long totalFoundComparisons = 0;
    static int[] array = new int[1];
   
   
    
    private static MyLinkedList[] dictionary = new MyLinkedList[26];
   
 
    
    /**
     * pre: none
     * post: fills each LinkedList with
     */
    public static void createDictionary(String fileName)
    {
        for(int i = 0 ; i< dictionary.length; i++)
        {
             dictionary[i] = new MyLinkedList<String>();
        }   
        File file = new File(fileName);
        try
        {
            Scanner input = new Scanner(file);
            while(input.hasNext())
            {
                String s = input.next();
                String word = s.toLowerCase();
                word = word.replaceAll("[^\\w\\s]","");
                dictionary[word.charAt(0)-97].addLast(word);     
            } 
            input.close();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }    
    }
    
    /**
     * pre: none
     * post: updates variables found, notFound along with their corresponding comparisons
     * goes through entire document and compares words in the document to the dictionary
     */
    
    public static void parser(String fileName)
    {       
        File file = new File(fileName);
        try
        {
           FileInputStream inf = new FileInputStream(file);
            char let;
            int n;
            String str = "";
             int[] comps = new int[1];
            while ((n = inf.read()) != -1) {
                let = (char) n;
                foundComparisons++;
                comps[0] = 0;
                
                if (Character.isLetter(let)) 
                {
                    str += Character.toLowerCase(let);
                }
                               
                if ((Character.isWhitespace(let) || let == '-') && !str.isEmpty())                    
                {
                    str = str.replaceAll("[^\\w\\s]","");
                    if(dictionary[str.charAt(0)-97].contains(str, comps))
                    {
                        found++;
                        foundComparisons+= comps[0];
                        str = "";
                    }
                    else
                    {
                        notFoundComparisons += comps[0];
                        notFound++;
                        str = "";
                    }
                }  

            }
            System.out.println(str);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        
    }

    
    public static void output()
    {
        double avgCompFound = (double) foundComparisons / found;
        double avgCompNotFound = (double) notFoundComparisons / notFound;
        System.out.println("Words found: " + found);
        System.out.println("Words not found: " + notFound);
        System.out.println("Comparisons for words found: " + foundComparisons);
        System.out.println("Comparisons for words not found: " + notFoundComparisons);
        System.out.printf("Average Comparisons per word found: %.2f" , avgCompFound);
        System.out.println();
        System.out.printf("Average Comparisons per word not found: %.2f" , avgCompNotFound);
        System.out.println();
    }
}