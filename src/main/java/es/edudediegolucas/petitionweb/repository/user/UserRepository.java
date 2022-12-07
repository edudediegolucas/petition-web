package es.edudediegolucas.petitionweb.repository.user;

import lombok.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public class UserRepository {

  private final Map<String, UserEntitiy> mapOfUsers = new HashMap<>();
  private final Map<String, String> mapOfIdsUsers = new HashMap<>();

  public Optional<UserEntitiy> getUserById(@NonNull String id) {
    return Optional.ofNullable(mapOfUsers.get(id));
  }

  public Optional<UserEntitiy> getUserByLogin(@NonNull String login) {
    return Optional.ofNullable(mapOfIdsUsers.get(login)).map(mapOfUsers::get);
  }

  public void updateUserLogin(@NonNull UserEntitiy user) {
    user.setLastAccessTime(LocalDateTime.now());
    mapOfUsers.put(user.getId(), user);
  }

  public UserEntitiy insertUser(@NonNull String login, @NonNull char[] password, @NonNull String name,
                                @NonNull String email) {
    var id = UUID.randomUUID().toString();
    var user = UserEntitiy.buildUser(login, password, name, email, id);
    mapOfUsers.put(id, user);
    mapOfIdsUsers.put(login, id);
    return user;
  }

  public void deleteUser(@NonNull String id) {
    var user = mapOfUsers.get(id);
    mapOfUsers.remove(id);
    mapOfIdsUsers.remove(user.getLogin());
  }

  public Set<UserEntitiy> getAllUsers() {
    return new HashSet<>(mapOfUsers.values());
  }
}
