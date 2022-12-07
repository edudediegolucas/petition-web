package es.edudediegolucas.petitionweb.model;

import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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

  public static LoginBean mapToLogiBean(UserEntitiy user) {
    return LoginBean.builder()
            .login(user.getLogin())
            .password(user.getPassword())
            .name(user.getName())
            .email(user.getEmail())
            .build();
  }
}
