package dev.testbed.dependency_new;

import dev.testbed.FriendDao;
import dev.testbed.TestBed;
import dev.testbed.UserDao;
import dev.testbed.UserServiceTestInterface;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserService.class)
public class UserServiceTest implements UserServiceTestInterface {

    UserDao mockUserDao;
    FriendDao friendDao;

    @Before
    public void setup() throws Exception {
        mockUserDao = mock(UserDao.class);
        whenNew(UserDao.class).withAnyArguments().thenReturn(mockUserDao);

        friendDao = mock(FriendDao.class);
        whenNew(FriendDao.class).withAnyArguments().thenReturn(friendDao);
    }

    @Test
    @Override
    public void sameUserAndFriend_ThrowsBadRequestException() {
        UserService userService = new TestBuilder().build();
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