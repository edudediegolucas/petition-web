package es.edudediegolucas.petitionweb.model;

import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBean implements Serializable {

  private String id;
  private String login;
  private String name;
  private String email;
  private LocalDateTime creationTime;
  private LocalDateTime lastAccessTime;

  public static UserBean mapToUserBean(UserEntitiy user) {
    return UserBean.builder()
            .id(user.getId())
            .login(user.getLogin())
            .name(user.getName())
            .email(user.getEmail())
            .creationTime(user.getCreationTime())
            .lastAccessTime(user.getLastAccessTime())
            .build();
  }
}
