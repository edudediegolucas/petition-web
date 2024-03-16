package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntity;

public class LogoutActionTest extends BaseActionTest {

  public void testLogoutError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/logout");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testLogout() throws Exception {
    UserEntity userEntity = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/logout");
    setValueInSession(userEntity.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
    assertTrue(actionProxy.getInvocation().getInvocationContext().getSession().isEmpty());
  }
}
