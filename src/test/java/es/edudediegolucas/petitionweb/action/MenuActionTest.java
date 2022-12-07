package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;

public class MenuActionTest extends BaseActionTest {

  public void testMenuError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/menu");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testMenu() throws Exception {
    UserEntitiy userEntitiy = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/menu");
    setValueInSession(userEntitiy.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
  }
}
