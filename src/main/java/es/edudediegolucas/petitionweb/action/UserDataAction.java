package es.edudediegolucas.petitionweb.action;

import es.edudediegolucas.petitionweb.model.PetitionBean;
import es.edudediegolucas.petitionweb.model.UserBean;
import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDataAction extends BaseAction implements LoginRequired {

  private UserBean userBean;
  private Set<PetitionBean> listPetitions;

  @Override
  public String execute() throws Exception {
    if (isValueInSession(SESSION_USER_ID)) {
      UserEntitiy userEntitiy = userRepository.getUserById((String) getValueFromSession(SESSION_USER_ID))
              .orElseThrow(() -> new RuntimeException("No such user!"));
      userBean = UserBean.mapToUserBean(userEntitiy);
      listPetitions = petitionRepository.getPetitionPerUser(userEntitiy.getId()).stream()
              .map(PetitionBean::mapToPetitionBean)
              .collect(Collectors.toSet());
      return SUCCESS;
    }
    return ERROR;
  }
}
