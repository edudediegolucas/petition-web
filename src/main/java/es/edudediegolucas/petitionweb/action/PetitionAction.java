package es.edudediegolucas.petitionweb.action;

import es.edudediegolucas.petitionweb.model.PetitionBean;
import es.edudediegolucas.petitionweb.model.UserBean;
import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class PetitionAction extends BaseAction implements LoginRequired {

  private UserBean userBean;
  private PetitionBean petitionBean;

  @Override
  public String execute() throws Exception {
    if (isValueInSession(SESSION_USER_ID)) {
      UserEntitiy userEntitiy = userRepository.getUserById((String) getValueFromSession(SESSION_USER_ID))
              .orElseThrow(() -> new RuntimeException("No such user!"));
      userBean = UserBean.mapToUserBean(userEntitiy);
      return SUCCESS;
    }
    return ERROR;
  }

  public String savePetition() {
    UserEntitiy userEntitiy = null;
    if (isValueInSession(SESSION_USER_ID)) {
      userEntitiy = userRepository.getUserById((String) getValueFromSession(SESSION_USER_ID))
              .orElseThrow(() -> new RuntimeException("No such user!"));
    }
    if (!Objects.isNull(petitionBean)
            && (StringUtils.isNotBlank(petitionBean.getName()))
            && (StringUtils.isNotBlank(petitionBean.getData()))) {
      petitionBean.setId(UUID.randomUUID().toString());
      petitionBean.setCreationTime(LocalDateTime.now());
      petitionRepository.insertPetition(PetitionBean.mapToPetitionEntity(petitionBean), userEntitiy);
      return SUCCESS;
    }
    return ERROR;
  }

}
