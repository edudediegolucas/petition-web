package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntity;
import org.apache.commons.lang3.RandomStringUtils;

public class PetitionActionTest extends BaseActionTest {

  public void testPetitionError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/petition");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testPetition() throws Exception {
    UserEntity userEntity = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/petition");
    setValueInSession(userEntity.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
  }

  public void testSavePetitionError() throws Exception {
    UserEntity userEntity = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/savePetition");
    setValueInSession(userEntity.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.ERROR, actionSupport);
  }

  public void testSavePetitionLoginRedirect() throws Exception {
    ActionProxy actionProxy = getActionProxy("/savePetition");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testSavePetition() throws Exception {
    UserEntity userEntity = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/savePetition");
    setValueInSession(userEntity.getId(), actionProxy);
    request.setParameter("petitionBean.name", RandomStringUtils.randomAlphanumeric(10));
    request.setParameter("petitionBean.data", RandomStringUtils.randomAlphanumeric(64));
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.ERROR, actionSupport);
  }
}
