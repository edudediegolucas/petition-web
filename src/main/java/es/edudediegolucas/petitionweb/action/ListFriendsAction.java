package es.edudediegolucas.petitionweb.action;

import es.edudediegolucas.petitionweb.model.UserBean;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

public class ListFriendsAction extends BaseAction implements LoginRequired {

  @Setter
  @Getter
  private Set<UserBean> listUsers;

  @Override
  public String execute() {
    if (isValueInSession(SESSION_USER_ID)) {
      listUsers = userRepository.getAllUsers().stream()
              .filter(user -> !user.getId().equals((getValueFromSession(SESSION_USER_ID))))
              .map(UserBean::mapToUserBean)
              .collect(Collectors.toSet());
      return SUCCESS;
    }
    return ERROR;
  }
}
