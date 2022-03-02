package fr.norsys;

import java.util.*;

public class UserService {

    public Optional<User> getUserValue(User user) {
        return Optional.ofNullable(user);
    }

    public User throwExceptionIfUserIsNull(User user) {
        return Optional.ofNullable(user).orElseThrow(NoSuchElementException::new) ;
    }

    public User getUserUsingOrElse(User user) {
//        return Optional.ofNullable(user).orElse(new User("test@norsys.fr"));
        return Optional.ofNullable(user).orElseGet(() ->new User("test@norsys.fr"));
    }

    public String getUserEmail(User user) {
        return Optional.ofNullable(user).map(User::getEmail).orElseGet(()->"unknown");
    }

    public Boolean isUserEmailValid(User user) {
        return Optional.ofNullable(user).map(user1 -> user1.getEmail().contains("@")).orElseThrow(NoSuchElementException::new);
    }

    public String getUserCountryName(User user) {
        return Optional.ofNullable(user).map(user1 -> user1.getAddress()
                        .map(address -> address.getCountry()
                        .map(Country::getName)
                                .orElse("unknown country"))
                        .orElse("unknown address"))

                .orElse("unknown");
    }

    public String getFirstUserEmail(User user1, User user2) throws Exception {
        return Optional.ofNullable(user1).or(() -> Optional.ofNullable(user2)).map(User::getEmail).orElseThrow(NoSuchFieldException::new);
    }


    public List<User> getListFromUser(User user) {
        List <User> users = new ArrayList<>();
        Optional.ofNullable(user).ifPresent(users::add);
        return users;
    }

    public List<User> getListFromUserBis(User user) {
        List <User> users = new ArrayList<>();
        Optional.ofNullable(user).ifPresentOrElse(e -> users.add(e), () -> users.add(new User("random@norsys.fr")));
        return users;    }
}
