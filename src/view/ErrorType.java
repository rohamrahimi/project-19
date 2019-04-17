package view;

public enum ErrorType {
    INVALID_COMMAND("invalid command."),
    USERNAME_TAKEN("this user name is already taken."),
    INVALID_USERNAME("this user name does not exists."),
    INVALID_PASSWORD("your password is wrong."),
    NOT_FOUND("not found"),
    NOT_ENOUGH_MONEY("not enough money"),
    FULL_ITEMS("you have already three items");
    private String message;
    ErrorType(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
