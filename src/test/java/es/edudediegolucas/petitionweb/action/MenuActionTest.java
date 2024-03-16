package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntity;

public class MenuActionTest extends BaseActionTest {

  public void testMenuError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/menu");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testMenu() throws Exception {
    UserEntity userEntity = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/menu");
    setValueInSession(userEntity.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
  }
}
