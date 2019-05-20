package dev.testbed.dependency_injection;

import dev.testbed.*;

import java.util.Optional;

/**
 * Class to show TestBed in dependency injection scenarios.
 */
public class UserService {

    private final UserDao userDao;
    private final FriendDao friendDao;

    public UserService(UserDao userDao, FriendDao friendDao) {
        this.userDao = userDao;
        this.friendDao = friendDao;
    }

    /**
     * @param userId the calling user
     * @param friendId to create the friendship with
     * @return the friendship id
     * @throws UserNotExistsException if either the calling user or friend does not exist
     * @throws BadRequestException if the user is attempting to connect with themself
     */
    public int createFriendship(String userId, String friendId) throws BadRequestException, UserNotExistsException {
        if (userId.equals(friendId)) {
            throw new BadRequestException("You can't be friends with yourself.");
        }

        Optional<User> user = this.userDao.getUser(userId);
        Optional<User> friend = this.userDao.getUser(friendId);

        if (! user.isPresent()) {
            throw new UserNotExistsException(userId);
        }

        if (! friend.isPresent()) {
            throw new UserNotExistsException(friendId);
        }

        return this.friendDao.createFriendship(user.get(), friend.get());
    }

}
