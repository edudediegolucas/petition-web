package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntity;

public class DismissActionTest extends BaseActionTest {

  public void testDismissError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/dismiss");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testDismiss() throws Exception {
    UserEntity userEntity = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/dismiss");
    setValueInSession(userEntity.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
    assertTrue(actionProxy.getInvocation().getInvocationContext().getSession().isEmpty());
  }
}
