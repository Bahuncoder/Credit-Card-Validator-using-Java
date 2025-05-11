/**
 * The CreditCard class represents a credit card.
 */
class CreditCard extends Card {
    private String cardNumber;
    private String issuer;

    /**
     * Constructs a CreditCard object with the specified card number, issuer, and card type.
     * 
     * @param cardNumber The card number.
     * @param issuer     The issuer of the card.
     * @param cardType   The type of the card.
     */
    public CreditCard(String cardNumber, String issuer, String cardType) {
        super(cardType);
        this.cardNumber = cardNumber;
        this.issuer = issuer;
    }

    /**
     * Returns the card number.
     * 
     * @return The card number.
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Returns the issuer of the card.
     * 
     * @return The issuer of the card.
     */
    public String getIssuer() {
        return issuer;
    }
}
