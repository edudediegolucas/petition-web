package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;
import org.apache.commons.lang3.RandomStringUtils;

public class PetitionActionTest extends BaseActionTest {

  public void testPetitionError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/petition");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testPetition() throws Exception {
    UserEntitiy userEntitiy = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/petition");
    setValueInSession(userEntitiy.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
  }

  public void testSavePetitionError() throws Exception {
    UserEntitiy userEntitiy = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/savePetition");
    setValueInSession(userEntitiy.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.ERROR, actionSupport);
  }

  public void testSavePetitionLoginRedirect() throws Exception {
    ActionProxy actionProxy = getActionProxy("/savePetition");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testSavePetition() throws Exception {
    UserEntitiy userEntitiy = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/savePetition");
    setValueInSession(userEntitiy.getId(), actionProxy);
    request.setParameter("petitionBean.name", RandomStringUtils.randomAlphanumeric(10));
    request.setParameter("petitionBean.data", RandomStringUtils.randomAlphanumeric(64));
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.ERROR, actionSupport);
  }
}
