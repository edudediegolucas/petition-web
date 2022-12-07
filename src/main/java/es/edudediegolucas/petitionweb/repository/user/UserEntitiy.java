package es.edudediegolucas.petitionweb.repository.user;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@Builder
public class UserEntitiy {

  private String id;
  private String login;
  private char[] password;
  private String name;
  private String email;
  private LocalDateTime creationTime;
  private LocalDateTime lastAccessTime;

  public static UserEntitiy buildUser(String login, @NonNull char[] password, String name, String email, String id) {
    return UserEntitiy.builder()
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
