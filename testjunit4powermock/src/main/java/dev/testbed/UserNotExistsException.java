package dev.testbed;

public class UserNotExistsException extends RuntimeException {

    public UserNotExistsException(String userId) {
        super("The user " + userId + " does not exist.");
    }

}
