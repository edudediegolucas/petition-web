package es.edudediegolucas.petitionweb.action;

import java.util.concurrent.atomic.AtomicReference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogoutAction extends BaseAction implements LoginRequired {

  @Override
  public String execute() {
    if (isValueInSession(SESSION_USER_ID)) {
      AtomicReference<String> login = new AtomicReference<>();
      userRepository.getUserById((String) getValueFromSession(SESSION_USER_ID))
          .ifPresent(user -> login.set(user.getLogin()));
      removeFromSession(SESSION_USER_ID);
      log.info("Logout successfully for user {}", login.get());
      return SUCCESS;
    }
    return ERROR;
  }
}
