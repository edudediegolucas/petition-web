package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import es.edudediegolucas.petitionweb.repository.petition.PetitionEntity;
import es.edudediegolucas.petitionweb.repository.petition.PetitionRepository;
import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;
import es.edudediegolucas.petitionweb.repository.user.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.struts2.StrutsSpringTestCase;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ContextConfiguration("classpath:applicationContext.xml")
public abstract class BaseActionTest extends StrutsSpringTestCase {

  protected static final String USER_REPOSITORY_BEAN = "userRepository";
  protected static final String PETITION_REPOSITORY_BEAN = "petitionRepository";
  protected static final String SESSION_USER_ID = "userId";

  protected UserEntitiy createUserEntity() {
    String login = RandomStringUtils.randomAlphanumeric(6);
    String password = RandomStringUtils.randomAlphanumeric(10);
    String name = RandomStringUtils.randomAlphanumeric(6);
    String email = RandomStringUtils.randomAlphanumeric(4)
            .concat("@")
            .concat(RandomStringUtils.randomAlphanumeric(6))
            .concat(".com");
    UserRepository userRepository = (UserRepository) applicationContext.getBean(USER_REPOSITORY_BEAN);
    return userRepository.insertUser(login, password.toCharArray(), name, email);
  }

  protected PetitionEntity createPetitionEntity(UserEntitiy userEntitiy) {
    PetitionEntity petitionEntity = PetitionEntity.builder()
            .id(UUID.randomUUID().toString())
            .name(RandomStringUtils.randomAlphanumeric(10))
            .data(RandomStringUtils.randomAlphanumeric(64))
            .creationTime(LocalDateTime.now())
            .build();
    PetitionRepository petitionRepository = (PetitionRepository) applicationContext.getBean(PETITION_REPOSITORY_BEAN);
    petitionRepository.insertPetition(petitionEntity, userEntitiy);
    return petitionEntity;
  }

  protected void setValueInSession(String value, ActionProxy actionProxy) {
    Map<String, Object> sessionMap = new HashMap<>();
    sessionMap.put(SESSION_USER_ID, value);
    actionProxy.getInvocation().getInvocationContext().withSession(sessionMap);
  }
}
