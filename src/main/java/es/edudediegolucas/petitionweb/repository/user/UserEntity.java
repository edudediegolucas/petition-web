package es.edudediegolucas.petitionweb.repository.user;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class UserEntity {

  private String id;
  private String login;
  private char[] password;
  private String name;
  private String email;
  private LocalDateTime creationTime;
  private LocalDateTime lastAccessTime;

  public static UserEntity buildUser(String login, @NonNull char[] password, String name, String email, String id) {
    return UserEntity.builder()
        .id(id)
        .name(name)
        .login(login)
        .email(email)
        .password(password)
        .creationTime(LocalDateTime.now())
        .lastAccessTime(LocalDateTime.now())
        .build();
  }
}
