package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;
import org.apache.commons.lang3.RandomUtils;

import java.util.stream.IntStream;

public class ListFriendsActionTest extends BaseActionTest {

  public void testFriendsError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/friends");
    String actionSupport = actionProxy.execute();
    assertEquals("loginRedirect", actionSupport); // LoginInterceptor
  }

  public void testFriends() throws Exception {
    UserEntitiy userEntitiy = createUserEntity();
    IntStream.range(0, RandomUtils.nextInt(1, 10))
            .forEach(ignored -> createUserEntity());
    ActionProxy actionProxy = getActionProxy("/friends");
    setValueInSession(userEntitiy.getId(), actionProxy);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
  }
}
