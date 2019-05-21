package dev.testbed;

public interface UserServiceTestInterface {

    void sameUserAndFriend_ThrowsBadRequestException();

    void userDoesNotExist_ThrowsUserNotExistsException();

    void friendDoesNotExist_ThrowsUserNotExistsException();

    void friendshipCreated_FriendshipIdReturned();

    void userDao_getUser_CalledWithCallingUser();

    void friendDao_createFriendship_CalledWithUsers();

}
