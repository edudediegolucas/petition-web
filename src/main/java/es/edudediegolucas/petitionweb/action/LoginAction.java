package es.edudediegolucas.petitionweb.action;

import es.edudediegolucas.petitionweb.model.LoginBean;
import es.edudediegolucas.petitionweb.repository.user.UserEntity;
import java.util.Arrays;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Setter
@Getter
@Slf4j
public class LoginAction extends BaseAction implements LoginRequired {

  private LoginBean loginBean;

  @Override
  public String execute() {
    log.info("login with: {}", loginBean);
    if (!Objects.isNull(loginBean) && StringUtils.isNotBlank(loginBean.getLogin())) {
      if (userRepository.getUserByLogin(loginBean.getLogin()).isPresent()) {
        UserEntity user = userRepository.getUserByLogin(loginBean.getLogin()).get();
        if (Arrays.equals(user.getPassword(), loginBean.getPassword())) {
          userRepository.updateUserLogin(user);
          putInSession(SESSION_USER_ID, user.getId());
          loginBean = LoginBean.mapToLogiBean(user);
          // Go to menu
          log.info("Login success for user {}", user.getLogin());
          return SUCCESS;
        } else {
          return ERROR;
        }
      } else {
        log.info("New user to register! {}", loginBean.getLogin());
        return LOGIN;
      }
    }
    log.error("Trying to log with no authentication!");
    return ERROR;
  }

  public String registerUser() {
    log.info("register: {}", loginBean);
    if (Objects.isNull(loginBean) && Objects.isNull(getValueFromSession(SESSION_USER_ID))) {
      return ERROR;
    }
    if (isValueInSession(SESSION_USER_ID)) {
      loginBean = LoginBean.mapToLogiBean(userRepository.getUserById((String) getValueFromSession(SESSION_USER_ID))
          .orElseThrow(() -> new RuntimeException("No such user!")));
      // Go to menu
      return SUCCESS;
    }
    if (StringUtils.isNotBlank(loginBean.getLogin()) &&
        StringUtils.isNotBlank(loginBean.getEmail()) &&
        StringUtils.isNotBlank(loginBean.getName()) &&
        !Arrays.equals(loginBean.getPassword(), StringUtils.EMPTY.toCharArray())) {
      UserEntity user = userRepository.insertUser(loginBean.getLogin(), loginBean.getPassword(), loginBean.getName(),
          loginBean.getEmail());
      putInSession(SESSION_USER_ID, user.getId());
      // Go to menu
      log.info("Registration complete for user {}", user.getLogin());
      return SUCCESS;
    }
    return ERROR;
  }
}
