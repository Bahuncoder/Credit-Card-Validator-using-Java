/**
Description: The CreditCardValidator class validates credit card numbers and sorts them into
 * valid and invalid cards.
 * It also writes the sorted cards to output files and
 * allows the user to choose which set of cards to display.
Class: Fall - COSC 1437.82002
Final Project
Date: 05/09/2024
@author Sudip Paudel
@version 1.0
*/

import java.util.*;
import java.io.*;
import java.nio.file.*;
public class CreditCardValidator {
    
    /**
     * The main method is the entry point of the program.
     * It validates and parses command-line arguments, reads input files,
     * validates credit card numbers, separates them into valid and invalid cards,
     * sorts the valid and invalid cards, writes the sorted cards to output files,
     * prompts the user to choose between displaying valid or invalid cards,
     * and prints the chosen set of cards.
     * 
     * @param args The command-line arguments containing input and output file paths.
     */
    public static void main(String[] args) {
        // Validate and parse command line arguments
        if (args.length < 3) {
            System.out.println("Usage: java CreditCardValidator <input file> <output valid file> <output invalid file>");
            return;
        }
        String inputFilePath = args[0];
        String outputValidFilePath = args[1];
        String outputInvalidFilePath = args[2];

        // Prepare collections to hold valid and invalid credit cards
        ArrayList<CreditCard> validCards = new ArrayList<>();
        ArrayList<CreditCard> invalidCards = new ArrayList<>();

        // Process the input file
        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
            for (String line : lines) {
                boolean isValid = CreditCardValidation.isValid(line.trim());
                String issuer = CreditCardValidation.getIssuer(line.trim());
                if (isValid) {
                    validCards.add(new CreditCard(line.trim(), issuer, "Credit Card"));
                } else {
                    invalidCards.add(new CreditCard(line.trim(), issuer, "Credit Card"));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the input file: " + e.getMessage());
            return;
        }

        // Sort the cards by card type and issuer's name
        Collections.sort(validCards, Comparator.comparing(CreditCard::getCardType).thenComparing(CreditCard::getIssuer));
        Collections.sort(invalidCards, Comparator.comparing(CreditCard::getCardType).thenComparing(CreditCard::getIssuer));

        // Write results to files
        writeCardsToFile(validCards, outputValidFilePath);
        writeCardsToFile(invalidCards, outputInvalidFilePath);

        // Interact with the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 'valid' to display valid cards or 'invalid' to display invalid cards:");
        String choice = scanner.nextLine();

        if ("valid".equalsIgnoreCase(choice)) {
            printCards(validCards);
        } else if ("invalid".equalsIgnoreCase(choice)) {
            printCards(invalidCards);
        } else {
            System.out.println("Invalid input. Please type 'valid' or 'invalid'.");
        }

        scanner.close();
    }

    /**
     * Writes the list of credit cards to a file.
     * 
     * @param cards    The list of credit cards to write.
     * @param fileName The name of the file to write to.
     */
    private static void writeCardsToFile(List<CreditCard> cards, String fileName) {
        List<String> lines = new ArrayList<>();
        for (CreditCard card : cards) {
            lines.add(card.getCardNumber() + " - " + card.getIssuer());
        }
        try {
            Files.write(Paths.get(fileName), lines);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Prints the list of credit cards.
     * 
     * @param cards The list of credit cards to print.
     */
    private static void printCards(List<CreditCard> cards) {
        for (CreditCard card : cards) {
            System.out.println(card.getCardNumber() + " - " + card.getIssuer());
        }
    }
}
