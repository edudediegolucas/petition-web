package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;

public class DismissActionTest extends BaseActionTest {

  public void testDismissError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/dismiss");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testDismiss() throws Exception {
    UserEntitiy userEntitiy = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/dismiss");
    setValueInSession(userEntitiy.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
    assertTrue(actionProxy.getInvocation().getInvocationContext().getSession().isEmpty());
  }
}
