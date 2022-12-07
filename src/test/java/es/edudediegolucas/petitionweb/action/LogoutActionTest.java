package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;

public class LogoutActionTest extends BaseActionTest {

  public void testLogoutError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/logout");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testLogout() throws Exception {
    UserEntitiy userEntitiy = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/logout");
    setValueInSession(userEntitiy.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
    assertTrue(actionProxy.getInvocation().getInvocationContext().getSession().isEmpty());
  }
}
