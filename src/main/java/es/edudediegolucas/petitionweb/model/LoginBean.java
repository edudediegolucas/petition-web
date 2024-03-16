package es.edudediegolucas.petitionweb.model;

import es.edudediegolucas.petitionweb.repository.user.UserEntity;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class LoginBean implements Serializable {

  private String login;
  private char[] password;
  private String name;
  private String email;

  public static LoginBean mapToLogiBean(UserEntity user) {
    return LoginBean.builder()
        .login(user.getLogin())
        .password(user.getPassword())
        .name(user.getName())
        .email(user.getEmail())
        .build();
  }
}
