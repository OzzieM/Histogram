//Title:Histogram.java
//Abstract:Read a file of single characters between A-K and output the number of occurrences in a histogram
//Author: Ozzie Moreno
//Date:Finished 9-16-2019

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Histogram{

    //sort the Histogram
    private static void Bubble(int[] Hist){
            int n = Hist.length;
            for (int i = 0; i < n-1; i++){
                for (int j = 0; j < n - i - 1; j++)
                    if (Hist[j] > Hist[j + 1]) {
                        int temp = Hist[j];
                        Hist[j] = Hist[j + 1];
                        Hist[j + 1] = temp;
                    }
            }
    }
    public static void main(String[] args) {

        //initialize and give a value for A-K
        Map<String, Integer> abc = new HashMap<>();
            abc.put("A",0);
            abc.put("B",0);
            abc.put("C",0);
            abc.put("D",0);
            abc.put("E",0);
            abc.put("F",0);
            abc.put("G",0);
            abc.put("H",0);
            abc.put("I",0);
            abc.put("J",0);
            abc.put("K",0);


        //take user input for txt file
        Scanner a = new Scanner(System.in);
            System.out.print("Enter filename:");
            String filename = a.nextLine();

        File f = new File(filename);
            Scanner scan = null;
        try{
            scan = new Scanner(f);
        }
        catch (FileNotFoundException e){
            System.out.println(filename + " file not found");
        }

        //increments abc Hashmap with characters from file
        int count = 0;
        while(scan.hasNext()){
            String input = scan.nextLine();
            //test.append(input);
            if(!abc.containsKey(input)){ //thought we were reading from user input, rather than file so this may be useless now.
                abc.put(input, 1);
                count++;
            }
            else if(abc.containsKey(input)){
                abc.put(input, abc.get(input) + 1);
            }
        }
        //create array to store Hashmap values
        int increment = 1; //left Hist[0] empty so I could put the number column + creates some more space
            int[] Hist = new int[12];
            for (String i : abc.keySet()){
                Hist[increment++] = abc.get(i);
            }
        //records max height for histogram
        int max =0;
            for(int i = 0; i<Hist.length; i++){
                if(Hist[i] > max)
                    max=Hist[i];
            }
        //sort from least to greatest
        Bubble(Hist);

        System.out.println("========VERTICAL BAR=======");
        //display stars for histogram
        for(int i = max; i>0; i--){
            System.out.print("|"+max--+"|"); //starts getting misaligned if max goes above 2 digits
            for(int j = 0; j<Hist.length; j++){
                if(Hist[j]>=i) {
                    System.out.print("* ");
                }
                else {
                    System.out.print("  ");
                }
            }//new row line
            System.out.println();
        }
        //tried ascii but figured it'd be easier to sort abc map and display only the letters
//        for(int i=65;i<=75;i++)
//            System.out.print(Character.toString((char)i)+" ");


//        for (String i : abc.keySet()){
//
//            if(abc.get(i) < 1){
//                System.out.print(i+" ");
//            }
//
//        }
        //sorts the abc map so that I can print out the keys/letters only
        System.out.println("===========================");
        System.out.print("|NO|");
        abc.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed().reversed()) //reverse twice to sort least to greatest
                .limit(Hist.length).forEachOrdered(i -> System.out.print(" "+i.getKey()));
                                                    //^^^ first time doing this so it was annoying to figure out,
                                                    //i.getKey() solved everything though
        System.out.println();
            System.out.println("===========================");
    }
}