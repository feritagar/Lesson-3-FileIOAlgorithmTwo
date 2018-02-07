package us.mattgreen;

import java.util.Scanner;

public class Main {

    private final static FileInput cardAccts = new FileInput("movie_cards.csv");
    private final static FileInput cardPurchases = new FileInput("movie_purchases.csv");
    //add the movie_rating file
    private final static FileInput movieRating = new FileInput("movie_rating.csv");
    private static String line = "";
    //add another line
    private static String rating_line ="";// added new line

    public static void main(String[] args) {
        String line;
        String[] fields;
        int[] nums = new int[3]; //modify 2 to 3
        boolean first = true;
        //System.out.format("%8s  %-18s %6s %6s\n","Account","Name", "Movies", "Points");
        boolean first_rating = true; //added
        // add ave rating to output %8s
        System.out.format("%8s  %-18s %6s %6s %8s\n","Account","Name", "Movies", "Points", "Ave. Rating");
        while ((line = cardAccts.fileReadLine()) != null) {
            fields = line.split(",");
            //find average
            findPurchases(first, fields[0], nums);
            first = false;
            //add another method
            findRating(first_rating, fields[0], nums);//added
            first_rating = false; //added
            //modify line Sout
            System.out.format("00%6s  %-18s  %2d   %4d     %3d\n",fields[0],fields[1], nums[0], nums[1],nums[2]); //added nums[2]
        }
    }

    public static void findPurchases(boolean first, String acct, int[] nums) {
        nums[0] = 0;
        nums[1] = 0;
        String[] fields;
        boolean done = false;
        if (first) {
            line = cardPurchases.fileReadLine();
        }
        while ((line != null) && !(done)) {
            fields = line.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            }
            else if (fields[0].equals(acct)) {
                nums[0]++;
                nums[1] += Integer.parseInt(fields[2]);
                line = cardPurchases.fileReadLine();
            }

        }
    }

    //create method to calculate average
    public static void findRating(boolean first_rating, String acct, int[] nums) {
        int totalMovie=0;
        nums[2] = 0 ;
        String[] fields;
        boolean done = false;
        if (first_rating) {
            rating_line = movieRating.fileReadLine();
        }
        while ((line != null) && !(done)) {
            fields = rating_line.split(",");
            if (fields[0].compareTo(acct) > 0) {
                done = true;
            } else if (fields[0].equals(acct)) {
                totalMovie++;
                nums[2] += Integer.parseInt(fields[1]);
                rating_line = movieRating.fileReadLine();
            }
        }
        if (totalMovie > 0) {
            nums[2] /= totalMovie;
        }

    }
}