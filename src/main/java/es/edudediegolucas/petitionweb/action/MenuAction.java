package es.edudediegolucas.petitionweb.action;

import es.edudediegolucas.petitionweb.model.LoginBean;
import lombok.Getter;
import lombok.Setter;

public class MenuAction extends BaseAction implements LoginRequired {

  @Getter
  @Setter
  private LoginBean loginBean;

  @Override
  public String execute() {
    if (isValueInSession(SESSION_USER_ID)) {
      loginBean = LoginBean.mapToLogiBean(userRepository.getUserById((String) getValueFromSession(SESSION_USER_ID))
              .orElseThrow(() -> new RuntimeException("No such user!")));
      // Go to menu
      return SUCCESS;
    }
    return ERROR;
  }
}
