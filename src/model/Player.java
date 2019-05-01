package model;

import model.Cards.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Player {
    private final int handCapacity = 5;
    private int mana;
    private Account account;
    private Deck deckCards;
    private HashMap<Integer, Card> handCards = new HashMap<>();
    private HashMap<Card, Cell> inBattleCards = new HashMap<>();
    private HashMap<Integer, Item> items = new HashMap<>();
    private Card selectedCard;
    private Item selectedItem;
    private int nextCardId;
    private HashMap<String, Integer> cardNameIdHashMap = new HashMap<>();

    public Player(Account account, Deck deckCards) {
        this.account = account;
        this.deckCards = deckCards;
        this.mana = 2;
    }

    public int getHandCapacity() {
        return handCapacity;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Deck getDeckCards() {
        return deckCards;
    }

    public void setDeckCards(Deck deckCards) {
        this.deckCards = deckCards;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public HashMap<Integer, Card> getHandCards() {
        return handCards;
    }

    public HashMap<Card, Cell> getInBattleCards() {
        return inBattleCards;
    }

    public HashMap<Integer, Item> getItems() {
        return items;
    }

    public HashMap<String, Integer> getCardNameIdHashMap() {
        return cardNameIdHashMap;
    }

    public void setNextCardId(int nextCardId) {
        this.nextCardId = nextCardId;
    }

    public int getNextCardId() {
        return nextCardId;
    }

    public void increaseMana() {
        this.mana++;
    }

    public boolean isAnyCardSelected() {
        return getSelectedCard() != null;
    }

    public boolean isAnyItemSelected() {
        return getSelectedItem() != null;
    }

//
//    public boolean myCardIsOnGround(String inBattleCardId){}


    public void setHandCards(HashMap<Integer, Card> handCards) {
        this.handCards = handCards;
    }

    public void setInBattleCards(HashMap<Card, Cell> inBattleCards) {
        this.inBattleCards = inBattleCards;
    }

    public void setItems(HashMap<Integer, Item> items) {
        this.items = items;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String handInfo() {
        StringBuilder result = new StringBuilder();
        ArrayList<Card> cards = new ArrayList<>(handCards.values());
        for (Card card : cards) {
            result.append(card.toInfoString());
            result.append("\n");
        }
        result.append("next card is :\n");
        result.append(deckCards.getCards().get(nextCardId));
        result.append("\n");
        return result.toString();
    }

    public void moveARandomCardToHand() {
        if (handCards.size() < handCapacity) {
            Random random = new Random();
            ArrayList<Card> cards = new ArrayList<>(deckCards.getCards().values());
            int rand = random.nextInt(cards.size());
            Card card = cards.get(rand);
            card.setCardStatus(CardStatus.IN_HAND);
            handCards.put(card.getCardId(), card);
        }
    }

    public boolean containsCardInBattle(int cardId) {
        return getInBattleCard(cardId) != null;
    }

    public Card getInBattleCard(int cardId) {
        for (Card card : getInBattleCards().keySet()) {
            if (card.getCardId() == cardId)
                return card;
        }
        return null;
    }
}
