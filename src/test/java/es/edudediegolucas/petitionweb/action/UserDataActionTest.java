package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntity;
import java.util.HashMap;
import java.util.Map;

public class UserDataActionTest extends BaseActionTest {

  public void testUserDataError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/data");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testUserData() throws Exception {
    UserEntity userEntity = createUserEntity();
    createPetitionEntity(userEntity);
    ActionProxy actionProxy = getActionProxy("/data");
    Map<String, Object> sessionMap = new HashMap<>();
    sessionMap.put(SESSION_USER_ID, userEntity.getId());
    actionProxy.getInvocation().getInvocationContext().withSession(sessionMap);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
  }
}
