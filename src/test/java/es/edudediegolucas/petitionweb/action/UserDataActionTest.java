package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;

import java.util.HashMap;
import java.util.Map;

public class UserDataActionTest extends BaseActionTest {

  public void testUserDataError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/data");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testUserData() throws Exception {
    UserEntitiy userEntitiy = createUserEntity();
    createPetitionEntity(userEntitiy);
    ActionProxy actionProxy = getActionProxy("/data");
    Map<String, Object> sessionMap = new HashMap<>();
    sessionMap.put(SESSION_USER_ID, userEntitiy.getId());
    actionProxy.getInvocation().getInvocationContext().withSession(sessionMap);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
  }
}
