package es.edudediegolucas.petitionweb.action;

import es.edudediegolucas.petitionweb.model.PetitionBean;
import es.edudediegolucas.petitionweb.model.UserBean;
import es.edudediegolucas.petitionweb.repository.user.UserEntity;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
public class PetitionAction extends BaseAction implements LoginRequired {

  private UserBean userBean;
  private PetitionBean petitionBean;

  @Override
  public String execute() throws Exception {
    if (isValueInSession(SESSION_USER_ID)) {
      UserEntity userEntity = userRepository.getUserById((String) getValueFromSession(SESSION_USER_ID))
          .orElseThrow(() -> new RuntimeException("No such user!"));
      userBean = UserBean.mapToUserBean(userEntity);
      return SUCCESS;
    }
    return ERROR;
  }

  public String savePetition() {
    UserEntity userEntity = null;
    if (isValueInSession(SESSION_USER_ID)) {
      userEntity = userRepository.getUserById((String) getValueFromSession(SESSION_USER_ID))
          .orElseThrow(() -> new RuntimeException("No such user!"));
    }
    if (!Objects.isNull(petitionBean)
        && (StringUtils.isNotBlank(petitionBean.getName()))
        && (StringUtils.isNotBlank(petitionBean.getData()))) {
      petitionBean.setId(UUID.randomUUID().toString());
      petitionBean.setCreationTime(LocalDateTime.now());
      petitionRepository.insertPetition(PetitionBean.mapToPetitionEntity(petitionBean), userEntity);
      return SUCCESS;
    }
    return ERROR;
  }

}
