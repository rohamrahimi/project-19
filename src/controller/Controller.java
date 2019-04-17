package controller;

import model.Account;
import model.Card;
import model.Item;
import view.*;

public class Controller {
    private final static Controller CONTROLLER = new Controller();

    public static Controller getInstance() {
        return CONTROLLER;
    }

    private Controller() {
    }

    private MenuType menuType = MenuType.ACCOUNT;
    //todo:check starting menu
    private Request request;
    private Account loggedInAccount;
    private ErrorType errorType = null;
    private View view = View.getInstance();

    public void run() {
        mainLoop:
        do {
            System.out.println("Menu: " + menuType);
            request = new Request();
            request.getNewCommand();
            request.setRequestType(menuType);
            request.parseCommand();
            switch (request.getRequestType()) {
                case ERROR:
                    errorType = ErrorType.INVALID_COMMAND;
                    break;
                case CREATE_ACCOUNT:
                    createNewAccount();
                    break;
                case LOGIN:
                    login();
                    break;
                case LOGOUT:
                    logOut();
                case SEARCH_IN_SHOP:
                    searchInShop();
                    // TODO: test
                    break;
                case SEARCH_IN_COLLECTION:
                    searchInCollection();
                    // TODO: test
                    break;
                case BUY_FROM_SHOP:
                    buyFromShop();
                    // TODO: buy functions
                case EXIT_MENU:
                    exitMenu();
                    break;
                case ENTER_MENU:
                    enterMenu();
                    break;
                case SHOW_LEADER_BOARD:
                    showLeaderBoard();
                    break;
                case HELP:
                    view.showHelp(menuType);
                    break;
            }
            if (errorType != null) {
                view.printError(errorType);
                errorType = null;
            }
        } while (true);
    }


    public void createNewAccount() {
        if (Account.userNameIsValid(request.getUserName())) {
            errorType = ErrorType.USERNAME_TAKEN;
            return;
        }
        view.printEnterPassword();
        request.getNewCommand();
        Account newAccount = new Account(request.getUserName(), request.getCommand());
        Account.addAccount(newAccount);
        System.out.println("account created");
    }

    public void login() {
        if (!Account.userNameIsValid(request.getUserName())) {
            errorType = ErrorType.INVALID_USERNAME;
            return;
        }
        view.printEnterPassword();
        request.getNewCommand();
        if (!Account.passwordIsValid(request.getCommand(), request.getUserName())) {
            errorType = ErrorType.INVALID_PASSWORD;
            return;
        }
        loggedInAccount = Account.getAccounts().get(request.getUserName());
        menuType = MenuType.MAINMENU;
        System.out.println("logged into " + request.getUserName());
    }

    public void showLeaderBoard() {
        view.show(Account.sortAccounts());
    }

    public void save() {
    }

    public void logOut() {
        menuType = MenuType.ACCOUNT;
        System.out.println("logged out from " + loggedInAccount.getUserName());
        loggedInAccount = null;
    }

    public void help() {
    }

    public void exitMenu() {
        //todo: maybe it need some changes
        switch (menuType) {
            case MAINMENU:
                System.exit(0);
            case BATTLE:
                menuType = MenuType.MAINMENU;
                break;
            case COLLECTION:
                menuType = MenuType.MAINMENU;
                break;
            case SHOP:
                menuType = MenuType.MAINMENU;
                break;
        }
    }

    public void enterMenu() {
        menuType = request.getEntringMenu();
    }

    public void enterCollectionItems() {
    }

    public void searchInCollection() {
        boolean find = false;
        for (Item item : loggedInAccount.getCollection().getItems()) {
            if (item.getName().equals(request.getSearchingName())) {
                System.out.println(item.getItemId());
                find = true;
            }
        }
        for (Card card : loggedInAccount.getCollection().getCards()) {
            if (card.getName().equals(request.getSearchingName())){
                System.out.println(card.getCardId());
                find = true;
            }
        }
        if (!find) request.setErrorType(ErrorType.NOT_FOUND);
    }

    public void saveCollection() {
    }

    public void createDeck() {
    }

    public void deleteDeck() {
    }

    public void addToDeck() {
    }

    public void removeCardFromDeck() {
    }

    public void validateDeck() {
    }

    public void selectMainDeck() {
    }

    public void showAllDecks() {
    }

    public void showDeck() {
    }

    public void searchInShop() {
        for (Item item : loggedInAccount.getShop().getItems())
            if (item.getName().equals(request.getSearchingName())) {
                System.out.println(item.getItemId());
                return;
            }
        for (Card card : loggedInAccount.getShop().getCards())
            if (card.getName().equals(request.getSearchingName())) {
                System.out.println(card.getCardId());
                return;
            }
        request.setErrorType(ErrorType.NOT_FOUND);
    }

    public void buyFromShop() {
        if (!loggedInAccount.getShop().itemExistsInShop(request.getProductName()) &&
        !loggedInAccount.getShop().cardExistsInShop(request.getSearchingName()))
            request.setErrorType(ErrorType.NOT_FOUND);
        else if (loggedInAccount.getShop().priceIsEnough(request.getProductName(), loggedInAccount))
            request.setErrorType(ErrorType.NOT_ENOUGH_MONEY);
        else if (loggedInAccount.getCollection().getItems().size() < 3) request.setErrorType(ErrorType.FULL_ITEMS);
        else{
            loggedInAccount.getShop().buyCard(request.getProductName());
            loggedInAccount.getShop().buyItem(request.getProductName());
        }
    }

    public void sellToShop() {
    }

    public void showShop() {
    }

    public void showGameInfo() {
    }

    public void showMyMinions() {
    }

    public void showOpponentMinions() {
    }

    public void showCardInfoInBattle() {
    }

    public void selectCard() {
    }

    public void moveCard() {
    }

    public void attack() {
    }

    public void comboAttack() {
    }

    public void useSpecialPower() {
    }

    public void showHand() {
    }

    public void insertCard() {
    }

    public void endTurn() {
    }

    public void showGatheredCollectables() {
    }

    public void selectCollectables() {
    }

    public void showCollectableInfo() {
    }

    public void useCollectable() {
    }

    public void showNextCard() {
    }

    public void showCardInfoInGraveYard() {
    }

    public void endGame() {
    }

    public void showMenuOptions() {
    }
}