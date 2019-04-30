package view;

import controller.MenuType;
import model.Game.GameMode;

import java.util.Scanner;

public class Request {
    private static Scanner scanner = new Scanner(System.in);
    private String command;
    private RequestType requestType;
    private ErrorType errorType;
    private String deckName;
    private String cardName;
    private String searchingName;
    private String productName;
    private int productId;
    private String userName;
    private String collectableName;
    private String inBattleCardId;
    private MenuType enteringMenu;
    private int cardOrItemID;
    private int itemID;
    private GameMode gameMode;
    private int numOfFlags;
    private static boolean isAnyCardSelected;
    private static boolean isAnyItemSelected;

    public void getNewCommand() {
        do {
            command = scanner.nextLine().trim().toLowerCase();
        } while (command.equals(""));
    }


    public RequestType findTypeOfRequest(MenuType menuType) {
        switch (menuType) {
            case ACCOUNT:
                if (command.matches("create account \\w+"))
                    return RequestType.CREATE_ACCOUNT;
                if (command.matches("login \\w+"))
                    return RequestType.LOGIN;
                if (command.matches("show leaderboard"))
                    return RequestType.SHOW_LEADER_BOARD;
                break;
            case MAINMENU:
                if (command.matches("save"))
                    return RequestType.SAVE;
                if (command.matches("logout"))
                    return RequestType.LOGOUT;
                break;
            case COLLECTION:
                if (command.matches("show"))
                    return RequestType.SHOW_COLLECTION_ITEMS;
                if (command.matches("search (\\w+ ?)+"))
                    return RequestType.SEARCH_IN_COLLECTION;
                if (command.matches("save"))
                    return RequestType.SAVE_COLLECTION;
                if (command.matches("create deck \\w+"))
                    return RequestType.CREATE_DECK;
                if (command.matches("delete deck \\w+"))
                    return RequestType.DELETE_DECK;
                if (command.matches("add \\d+ to deck \\w+"))
                    return RequestType.ADD_TO_DECK;
                if (command.matches("remove \\d+ from deck \\w+"))
                    return RequestType.REMOVE_FROM_DECK;
                if (command.matches("validate deck \\w+"))
                    return RequestType.VALIDATE_DECK;
                if (command.matches("select deck \\w+"))
                    return RequestType.SELECT_MAIN_DECK;
                if (command.matches("show all decks"))
                    return RequestType.SHOW_ALL_DECKS;
                if (command.matches("show deck \\w+"))
                    return RequestType.SHOW_DECK;
                break;
            case START_NEW_GAME:
                break;
            case SINGLE_GAME_MENU:
                break;
            case SINGLE_GAME_STORY_MODE:
                break;
            case SINGLE_GAME_CUSTOM_MODE:
                break;
            case MULTI_GAME_MENU:
                if (command.matches("show players"))
                    return RequestType.SHOW_ALL_PLAYERS;
                if (command.matches("select user \\w+"))
                    return RequestType.SELECT_OPPONENT_USER;
                if (command.matches("start multiplayer game (\\w+ ?)+ \\d+"))
                    return RequestType.SELECT_MODE;
                break;
            case BATTLE:
                //roham
                if (command.matches("game info"))
                    return RequestType.SHOW_GAME_INFO;
                if (command.matches("show my minions"))
                    return RequestType.SHOW_MY_MINIONS;
                if (command.matches("show opponents minions"))
                    return RequestType.SHOW_OPPONENT_MINIONS;
                if (command.matches("show card info \\d+"))
                    return RequestType.SHOW_CARD_INFO_IN_BATTLE;
                //sajad
                if (command.matches("select \\d+"))
                    return RequestType.SELECT_CARD_OR_COLLECTABLE;
                if (command.matches("move to \\(\\[\\d+], \\[\\d+]\\)") && isAnyCardSelected)
                    return RequestType.MOVE_CARD;
                if (command.matches("attack \\d+") && isAnyCardSelected)
                    return RequestType.ATTACK;
                if (command.matches("attack combo( \\d+ \\d+)+"))
                    return RequestType.COMBO_ATTACK;
                if (command.matches("use special power \\(\\d+, \\d+\\)") && isAnyCardSelected)
                    return RequestType.USE_SPECIAL_POWER;
                //amir
                if (command.matches("show hand"))//done
                    return RequestType.SHOW_HAND;
                if (command.matches("insert \\w+ in \\(\\d+, \\d+\\)"))
                    return RequestType.INSERT_CARD;
                if (command.matches("end turn"))
                    return RequestType.END_TURN;
                if (command.matches("show collectables"))
                    return RequestType.SHOW_GATHERED_COLLECTABLES;
                //roham
                if (command.matches("show info") && isAnyItemSelected)
                    return RequestType.SHOW_COLLECATBLE_INFO;
                //amir
                if (command.matches("use location \\[\\d+, \\d+]"))
                    return RequestType.USE_COLLECTABLE;
                if (command.matches("show next card"))
                    return RequestType.SHOW_NEXT_CARD;
                //sajad
                if (command.matches("enter graveyard"))
                    return RequestType.ENTER_GRAVEYARD;
                //
                if (command.matches("show my choices"))
                    return RequestType.SHOW_MY_CHOICES;
                //amir
                if (command.matches("end game"))
                    return RequestType.END_GAME;
                break;
            case GRAVEYARD:
                if (command.matches("show info \\d+"))
                    return RequestType.SHOW_CARD_INFO_IN_GRAVEYARD;
                if (command.matches("show cards"))
                    return RequestType.SHOW_All_CARDS_IN_GRAVEYARD;
            case SHOP:
                if (command.matches("show collection"))
                    return RequestType.SHOW_COLLECTION_ITEMS;
                if (command.matches("search collection (\\w+ ?)+"))
                    return RequestType.SEARCH_IN_COLLECTION;
                if (command.matches("search (\\w+ ?)+"))
                    return RequestType.SEARCH_IN_SHOP;
                if (command.matches("buy (\\w+ ?)+"))
                    return RequestType.BUY_FROM_SHOP;
                if (command.matches("sell \\d+"))
                    return RequestType.SELL_TO_SHOP;
                if (command.matches("show"))
                    return RequestType.SHOW_SHOP;
                break;
        }
        if (command.matches("help"))
            return RequestType.HELP;
        if (command.matches("exit"))
            return RequestType.EXIT_MENU;
        if (command.matches("enter (\\w+ ?)+"))
            return RequestType.ENTER_MENU;
        return RequestType.ERROR;
    }


    public void parseCommand() {
        switch (requestType) {
            ///////////////////// ACCOUNT ///////////////////
            case CREATE_ACCOUNT:
                userName = command.split(" ")[2];
                break;
            case LOGIN:
                userName = command.split(" ")[1];
                break;
            /////////////////// SHOP //////////////////
            case SEARCH_IN_SHOP:
                searchingName = command.substring(6).trim();
                break;
            case SEARCH_IN_COLLECTION:
                parseSearchInCollection();
                break;
            case BUY_FROM_SHOP:
                productName = command.substring(3).trim();
                break;
            case SELL_TO_SHOP:
                productId = Integer.parseInt(command.split(" ")[1]);
                break;
            /////////////////// COLLECTION /////////////////
            case CREATE_DECK:
                deckName = command.split(" ")[2];
                break;
            case DELETE_DECK:
                deckName = command.split(" ")[2];
                break;
            case ADD_TO_DECK:
                cardOrItemID = Integer.parseInt(command.split(" ")[1]);
                deckName = command.split(" ")[4];
                break;
            case REMOVE_FROM_DECK:
                cardOrItemID = Integer.parseInt(command.split(" ")[1]);
                deckName = command.split(" ")[4];
                break;
            case VALIDATE_DECK:
                deckName = command.split(" ")[2];
                break;
            case SELECT_MAIN_DECK:
                deckName = command.split(" ")[2];
                break;
            case SHOW_DECK:
                deckName = command.split(" ")[2];
                break;
            /////////////////////// CREATING GAME /////////////////
            case SELECT_OPPONENT_USER:
                userName = command.split(" ")[2];
                break;
            case SELECT_MODE:
                parseSelectMode();
            case ENTER_MENU:
                parseEnterMenu();
                break;
            ////////////////////// Battle //////////////////////
            case SHOW_CARD_INFO_IN_BATTLE:
                inBattleCardId = command.split(" ")[3];
        }
    }

    private void parseSelectMode() {
        if (command.substring(22).trim().equals("death match")) {
            gameMode = GameMode.DEATH_MATCH;
            return;
        }
        if (command.substring(22).trim().matches("capture the flag")) {
            gameMode = GameMode.CAPTURE_THE_FLAGS;
            return;
        }
        if (command.substring(22).trim().matches("keep the flag \\d+")) {
            gameMode = GameMode.KEEP_THE_FLAG;
            numOfFlags = Integer.parseInt(command.split(" ")[6]);
            return;
        }
        errorType = ErrorType.INVALID_COMMAND;
    }

    private void parseSearchInCollection() {
        if (command.matches("search collection (\\w+ ?)+"))
            searchingName = command.substring(17).trim();
        else searchingName = command.substring(6).trim();
    }


    public void parseEnterMenu() {
        String enteringMenuName = command.substring(5).trim();
        if ((enteringMenuName).equals("battle"))
            enteringMenu = MenuType.START_NEW_GAME;
        else if (enteringMenuName.equals("shop"))
            enteringMenu = MenuType.SHOP;
        else if (enteringMenuName.equals("collection"))
            enteringMenu = MenuType.COLLECTION;
        else if (enteringMenuName.equals("single player"))
            enteringMenu = MenuType.SINGLE_GAME_MENU;
        else if (enteringMenuName.equals("multi player"))
            enteringMenu = MenuType.MULTI_GAME_MENU;
        else if (enteringMenuName.equals("custom game"))
            enteringMenu = MenuType.SINGLE_GAME_CUSTOM_MODE;
        else if (enteringMenuName.equals("story mode"))
            enteringMenu = MenuType.SINGLE_GAME_STORY_MODE;
    }

    public void checkSyntaxOfShow() {
    }


    public void checkSyntaxOfSearchInCollection() {
    }


    public void checkSyntaxOfCreateDeck() {
    }


    public void checkSyntaxOfDeleteDeck() {
    }

    public void checkSyntaxOfAddToDeck() {
    }


    public void checkSyntaxOfRemoveCardFromDeck() {
    }


    public void checkSyntaxOfValidateDeck() {
    }


    public void checkSyntaxOfSelectMainDeck() {
    }


    public void checkSyntaxOfShowDeck() {
    }

    public void checkSyntaxOfBuyFromShop() {
    }


    public void checkSyntaxOfSellToShop() {
    }


    public void checkSyntaxOfShowCardInfoInBattle() {
    }


    public void checkSyntaxOfSelectCard() {
    }


    public void checkSyntaxOfMoveCard() {
    }


    public void checkSyntaxOfAttack() {
    }


    public void checkSyntaxOfComboAttack() {
    }


    public void checkSyntaxOfInsertCard() {
    }


    public void checkSyntaxOfSelectCollectable() {
    }


    public void checkSyntaxOfUseCollectable() {
    }


    public void checkSyntaxOfCardInfoInGraveYard() {
    }

    public void setRequestType(MenuType menuType) {
        this.requestType = findTypeOfRequest(menuType);
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public void setCollectableName(String collectableName) {
        this.collectableName = collectableName;
    }

    public void setCardOrItemID(int cardOrItemID) {
        this.cardOrItemID = cardOrItemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getCommand() {
        return command;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getDeckName() {
        return deckName;
    }

    public String getCardName() {
        return cardName;
    }

    public String getSearchingName() {
        return searchingName;
    }

    public void setSearchingName(String searchingName) {
        this.searchingName = searchingName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public String getUserName() {
        return userName;
    }

    public String getCollectableName() {
        return collectableName;
    }

    public String getInBattleCardId() {
        return inBattleCardId;
    }

    public int getCardOrItemID() {
        return cardOrItemID;
    }

    public int getItemID() {
        return itemID;
    }

    public MenuType getEnteringMenu() {
        return enteringMenu;
    }

}


