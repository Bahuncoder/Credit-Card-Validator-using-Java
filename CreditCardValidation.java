import java.util.ArrayList;
import java.util.List;

class CreditCardValidation {
    /**
     * Validates a credit card number using the Luhn algorithm.
     * @param number the credit card number as a String.
     * @return true if the number is valid, false otherwise.
     */
    public static boolean isValid(String number) {
        int sum = 0;
        boolean alternate = false;

        // Process each digit from right to left
        for (int i = number.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(number.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9; // Subtract 9 from numbers greater than 9
                }
            }
            sum += n;
            alternate = !alternate;
        }

        // Valid if sum is a multiple of 10
        return (sum % 10 == 0);
    }

    /**
     * Determines the issuer of a credit card based on the card number.
     * @param number the credit card number as a String.
     * @return the issuer of the card as a String.
     */
    public static String getIssuer(String number) {
        if (number.startsWith("34") || number.startsWith("37")) return "AMEX";
        if (number.startsWith("54") || number.startsWith("55")) return "Diners Club - North America";
        if (number.startsWith("30")) return "Diners Club - Carte Blanche";
        if (number.startsWith("36")) return "Diners Club - International";
        if (number.startsWith("60")) return "Discover";
        if (number.startsWith("31") || number.startsWith("33")) return "JCB";
        if (number.startsWith("58")) return "Maestro";
        if (number.startsWith("67")) return "LASER";
        if (number.startsWith("48") || number.startsWith("49")) return "Visa Electron";
        if (number.startsWith("63")) return "InstaPayment";
        if (number.startsWith("51") || number.startsWith("53")) return "MasterCard";
        if (number.startsWith("45") || number.startsWith("44")) return "VISA";
        return "Unknown";
    }
}
