package dev.testbed;

public interface UserServiceTestInterface {

    // Normal Tests

    void sameUserAndFriend_ThrowsBadRequestException();

    void userDoesNotExist_ThrowsUserNotExistsException();

    void friendDoesNotExist_ThrowsUserNotExistsException();

    void friendshipCreated_FriendshipIdReturned();

    // Verification Tests (For display only)

    void userDao_getUser_CalledWithCallingUser();

    void friendDao_createFriendship_CalledWithUsers();

}
