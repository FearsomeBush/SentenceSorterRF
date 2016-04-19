/*
 * © 2016 Lone Star College System 
 */
package edu.lonestar.xrayyankee.cosc1337;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * This class only has static methods and fields to support main()'s purpose.
 */
public class SentenceSorterXY {
   /** constant used in regular message dialogs */
   private static final String TITLE = "SentenceSorterXY";
   /** constant used in error message dialogs */
   private static final String ERROR_TITLE = "SentenceSorterXY : Error";
   /** constant used to personalized output file */
   private static final String MY_TEXT = "XY.txt";

   /**
    * This program sorts the lines (sentences) of a file.
    * 
    * @param args
    *           command line arguments (unused)
    */
   public static void main(String[] args) {
      Scanner inFileScanner = null;
      String inFilePath = JOptionPane.showInputDialog(null, "Enter the path to the file to sorted.", TITLE,
            JOptionPane.QUESTION_MESSAGE);
      if (inFilePath != null) {
         try {
            File fileIn = new File(inFilePath);
            inFileScanner = new Scanner(fileIn);
         } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Could not open input file.\n" + e, ERROR_TITLE,
                  JOptionPane.ERROR_MESSAGE);
         }
      }
      ArrayList<String> sentences = new ArrayList<String>();
      if (inFileScanner != null) {
         while (inFileScanner.hasNext()) {
            String line = inFileScanner.nextLine().trim();
            if (line.length() > 0)
               sentences.add(line);
         }
         inFileScanner.close();
         String sortedSentences[] = quickSortSentences(sentences);
         String outFilePath = inFilePath.substring(0, inFilePath.lastIndexOf(".")) + MY_TEXT;
         try {
            PrintWriter pwOut = new PrintWriter(outFilePath);
            for (String s : sortedSentences) {
               pwOut.println(s);
            }
            pwOut.close();
            String msg = String.format("%d sorted sentences output to %s", sortedSentences.length, outFilePath);
            JOptionPane.showMessageDialog(null, msg, TITLE, JOptionPane.INFORMATION_MESSAGE);
         } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Could not open output file.\n" + e, ERROR_TITLE,
                  JOptionPane.ERROR_MESSAGE);
         }
      }
      System.exit(0);
   }

   /**
    * Implements Quick Sort on an ArrayList of String objects, returning an
    * array of the sorted strings
    * 
    * @param sentences
    *           input String ArrayList to sort
    * @return sorted String[] array
    */
   private static String[] quickSortSentences(ArrayList<String> sentences) {
      String[] array = new String[sentences.size()];
      array = sentences.toArray(array);
      doQuickSort(array, 0, array.length - 1);
      return array;
   }

   // TODO: fill in doQuickSort, partition and swap methods

}
