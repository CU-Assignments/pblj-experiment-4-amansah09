import java.util.*;

public class CardCollection {

    static final String[] SUITS = {"Hearts", "Diamonds", "Clubs", "Spades"};
    static final String[] RANKS = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

    static List<String> cards = new ArrayList<>();

    public static void main(String[] args) {
        generateDeck();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the symbol to find the cards (Hearts, Diamonds, Clubs, Spades): ");
        String userSymbol = scanner.nextLine().trim();

        List<String> filteredCards = findCardsBySymbol(userSymbol);
        
        if (filteredCards.isEmpty()) {
            System.out.println("No cards found with the symbol: " + userSymbol);
        } else {
            System.out.println("Cards with symbol " + userSymbol + ":");
            for (String card : filteredCards) {
                System.out.println(card);
            }
        }

        scanner.close();
    }

    public static void generateDeck() {
        for (String suit : SUITS) {
            for (String rank : RANKS) {
                cards.add(rank + " of " + suit);
            }
        }
    }

    public static List<String> findCardsBySymbol(String symbol) {
        List<String> filteredCards = new ArrayList<>();
        for (String card : cards) {
            if (card.contains(symbol)) {
                filteredCards.add(card);
            }
        }
        return filteredCards;
    }
}
