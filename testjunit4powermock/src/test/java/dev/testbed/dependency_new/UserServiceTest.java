package dev.testbed.dependency_new;

import dev.testbed.TestBed;
import dev.testbed.UserServiceTestInterface;
import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

@PrepareForTest(UserService.class)
public class UserServiceTest implements UserServiceTestInterface {


    @Test
    @Override
    public void sameUserAndFriend_ThrowsBadRequestException() {

    }

    @Test
    @Override
    public void userDoesNotExist_ThrowsUserNotExistsException() {

    }

    @Test
    @Override
    public void friendDoesNotExist_ThrowsUserNotExistsException() {

    }

    @Test
    @Override
    public void friendshipCreated_FriendshipIdReturned() {

    }

    @Test
    @Override
    public void userDao_getUser_CalledWithCallingUser() {

    }

    @Test
    @Override
    public void friendDao_createFriendship_CalledWithUsers() {

    }

    class TestBuilder extends TestBed<UserService, TestBuilder> {

        TestBuilder() {
            super(UserService.class);
        }

    }
}