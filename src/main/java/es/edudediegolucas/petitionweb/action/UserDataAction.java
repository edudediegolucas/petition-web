package es.edudediegolucas.petitionweb.action;

import es.edudediegolucas.petitionweb.model.PetitionBean;
import es.edudediegolucas.petitionweb.model.UserBean;
import es.edudediegolucas.petitionweb.repository.user.UserEntity;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDataAction extends BaseAction implements LoginRequired {

  private UserBean userBean;
  private Set<PetitionBean> listPetitions;

  @Override
  public String execute() throws Exception {
    if (isValueInSession(SESSION_USER_ID)) {
      UserEntity userEntity = userRepository.getUserById((String) getValueFromSession(SESSION_USER_ID))
          .orElseThrow(() -> new RuntimeException("No such user!"));
      userBean = UserBean.mapToUserBean(userEntity);
      listPetitions = petitionRepository.getPetitionPerUser(userEntity.getId()).stream()
          .map(PetitionBean::mapToPetitionBean)
          .collect(Collectors.toSet());
      return SUCCESS;
    }
    return ERROR;
  }
}
