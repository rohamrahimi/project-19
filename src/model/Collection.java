package model;

import model.Cards.Card;
import model.Cards.CardStatus;
import model.Cards.Hero;

import java.util.ArrayList;
import java.util.HashMap;

public class Collection {
    private Deck mainDeck;
    private ArrayList<Item> items = new ArrayList<>();
    private HashMap<String, Deck> decks = new HashMap<>();
    private ArrayList<Card> cards = new ArrayList<>();

    public Deck getMainDeck() {
        return mainDeck;
    }

    public void setMainDeck(String deckName) {
        this.mainDeck = decks.get(deckName);
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public HashMap<String, Deck> getDecks() {
        return decks;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void createDeck(String deckName) {
    }

    public void deleteDeck(String deckName) {
    }

    public boolean isAnyDeckAvailable() {
        for (Deck deck : decks.values()) {
            if (deck.deckIsValid())
                return true;
        }
        return false;
    }
//
//    public boolean cardIsValid(int cardId){}
//
//    public boolean itemIsValid(int itemId){}
//
//    public boolean cardIsInDeck(int cardId, String deckName){}
//
//    public boolean itemIsInDeck(int itemId, String deckName){}
//
//    public boolean deckHasHero(String deckName){}

    public boolean existsInCollection(int productId) {
        for (Card card : cards) {
            if (card.getCardId() == productId)
                return true;
        }
        for (Item item : items) {
            if (item.getItemId() == productId)
                return true;
        }
        return false;
    }

    public boolean existsInDeck(String deckName, int id) {
        if (decks.get(deckName).getCards().containsKey(id) || decks.get(deckName).getItems().containsKey(id))
            return true;
        return false;
    }

    public boolean deckIsFull(String deckName, int id) {
        for (Card card : cards) {
            if (card.getCardId() == id && decks.get(deckName).getCards().size() >= 21)
                return true;
        }
        return false;
    }

    public boolean isHero(int id) {
        for (Card card : cards) {
            if (card.getCardId() == id && card instanceof Hero)
                return true;
        }
        return false;
    }

//    public boolean itemExistsInCollection(int itemId){}

    public void addToDeck(int id, String deckName) {
        for (Card card : cards) {
            if (card.getCardId() == id) {
                decks.get(deckName).getCards().put(id, card);
                card.setCardStatus(CardStatus.IN_DECK);
                return;
            }
        }
        for (Item item : items) {
            if (item.getItemId() == id) {
                decks.get(deckName).getItems().put(id, item);
                return;
            }
        }
    }

    public void removeFromDeck(String deckName, int id) {
        decks.get(deckName).getCards().remove(id);
        decks.get(deckName).getItems().remove(id);
    }

    public void addItemToDeck(int itemId, String deckName) {
    }

    public void removeCard(int cardId, String deckName) {
    }

    public void removeItem(int itemId, String deckName) {
    }
    public boolean existsInCollectionCards(String name){
        for (Card card : cards) {
            if (card.getName().equals(name))
                return true;
        }
        return false;
    }

//    public boolean validateDeck(String deckName){}
}
