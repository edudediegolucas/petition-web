package es.edudediegolucas.petitionweb.repository.user;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import lombok.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  private final Map<String, UserEntity> mapOfUsers = new HashMap<>();
  private final Map<String, String> mapOfIdsUsers = new HashMap<>();

  public Optional<UserEntity> getUserById(@NonNull String id) {
    return Optional.ofNullable(mapOfUsers.get(id));
  }

  public Optional<UserEntity> getUserByLogin(@NonNull String login) {
    return Optional.ofNullable(mapOfIdsUsers.get(login)).map(mapOfUsers::get);
  }

  public void updateUserLogin(@NonNull UserEntity user) {
    user.setLastAccessTime(LocalDateTime.now());
    mapOfUsers.put(user.getId(), user);
  }

  public UserEntity insertUser(@NonNull String login, @NonNull char[] password, @NonNull String name,
      @NonNull String email) {
    var id = UUID.randomUUID().toString();
    var user = UserEntity.buildUser(login, password, name, email, id);
    mapOfUsers.put(id, user);
    mapOfIdsUsers.put(login, id);
    return user;
  }

  public void deleteUser(@NonNull String id) {
    var user = mapOfUsers.get(id);
    mapOfUsers.remove(id);
    mapOfIdsUsers.remove(user.getLogin());
  }

  public Set<UserEntity> getAllUsers() {
    return new HashSet<>(mapOfUsers.values());
  }
}
