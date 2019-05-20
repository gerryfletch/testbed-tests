package dev.testbed.dependency_injection;

import dev.testbed.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Tests with JUnit4 and Dependency Injection
 */
public class UserServiceTest {

    private TestBuilder testBuilder;

    @Before
    public void setup() {
        testBuilder = new TestBuilder();
    }

    // Normal Tests

    @Test
    public void sameUserAndFriend_ThrowsBadRequestException() {
        UserService userService = testBuilder.reset().build();

        assertThatThrownBy(() -> userService.createFriendship("identical", "identical"))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("You can't be friends with yourself.");
    }

    @Test
    public void userDoesNotExist_ThrowsUserNotExistsException() {
        UserService userService = testBuilder.reset()
                .whenGetUserReturn("test-user", Optional.empty())
                .build();

        assertThatThrownBy(() -> userService.createFriendship("test-user", "another-user"))
                .isInstanceOf(UserNotExistsException.class)
                .hasMessageContaining("The user test-user does not exist.");
    }

    @Test
    public void friendDoesNotExist_ThrowsUserNotExistsException() {
        UserService userService = testBuilder.reset()
                .whenGetUserReturn("test-user", Optional.of(new User("test-name", 21)))
                .whenGetUserReturn("test-friend", Optional.empty())
                .build();

        assertThatThrownBy(() -> userService.createFriendship("test-user", "test-friend"))
                .isInstanceOf(UserNotExistsException.class)
                .hasMessageContaining("The user test-friend does not exist.");
    }

    @Test
    public void friendshipCreated_FriendshipIdReturned() {
        UserService userService = testBuilder.reset()
                .whenGetUserReturn("test-user", Optional.of(new User("test-user", 21)))
                .whenGetUserReturn("test-friend", Optional.of(new User("test-friend", 21)))
                .whenCreateFriendshipReturnId(5)
                .build();

        int friendshipId = userService.createFriendship("test-user", "test-friend");

        assertThat(friendshipId).isEqualTo(5);
    }

    // Verification Tests (For display only)

    @Test
    public void userDao_getUser_CalledWithCallingUser() {
        UserService userService = testBuilder.reset()
                .whenGetUserReturn("test-user", Optional.of(new User("test-user", 21)))
                .whenGetUserReturn("test-friend", Optional.of(new User("test-friend", 21)))
                .whenCreateFriendshipReturnId(5)
                .build();

        userService.createFriendship("test-user", "test-friend");

        verify(testBuilder.getDependency(UserDao.class)).getUser("test-user");
        verify(testBuilder.getDependency(UserDao.class)).getUser("test-friend");
    }

    @Test
    public void friendDao_createFriendship_CalledWithUsers() {
        User user = new User("test-user", 21);
        User friend = new User("test-friend", 21);
        UserService userService = testBuilder
                .whenGetUserReturn("test-user", Optional.of(user))
                .whenGetUserReturn("test-friend", Optional.of(friend))
                .whenCreateFriendshipReturnId(5)
                .build();

        userService.createFriendship("test-user", "test-friend");

        verify(testBuilder.getDependency(FriendDao.class)).createFriendship(user, friend);
    }

    class TestBuilder extends TestBed<UserService, TestBuilder> {

        TestBuilder() {
            super(UserService.class);
        }

        TestBuilder whenGetUserReturn(String username, Optional<User> user) {
            when(getDependency(UserDao.class).getUser(username)).thenReturn(user);
            return this;
        }

        TestBuilder whenCreateFriendshipReturnId(int id) {
            when(getDependency(FriendDao.class).createFriendship(any(User.class), any(User.class))).thenReturn(id);
            return this;
        }

    }
}