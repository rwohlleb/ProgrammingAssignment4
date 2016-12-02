/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rwohl
 */
public class SPClient
{
        public static void main(String[] args)
        {   long startTime = System.currentTimeMillis();
        
            SpellChecker.createDictionary(args[0]);
            SpellChecker.parser(args[1]);
            SpellChecker.output(); 

            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            double seconds = totalTime / 1000.0;
            System.out.println("Total time: " + seconds + " seconds");
        }    
}

