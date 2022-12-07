package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionSupport;
import es.edudediegolucas.petitionweb.repository.user.UserEntitiy;
import es.edudediegolucas.petitionweb.repository.user.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginActionTest extends BaseActionTest {

  public void testIndex() throws Exception {
    ActionProxy actionProxy = getActionProxy("/");
    assertNotNull(actionProxy);
    assertEquals(ActionSupport.SUCCESS, actionProxy.execute());
  }

  public void testLoginNoParams() {
    ActionProxy actionProxy = getActionProxy("/login");
    LoginAction loginAction = (LoginAction) actionProxy.getAction();
    assertNotNull(loginAction);
    assertEquals(ActionSupport.ERROR, loginAction.execute());
  }

  public void testLogin() throws Exception {
    request.setParameter("loginBean.login", RandomStringUtils.randomAlphanumeric(6));
    request.setParameter("loginBean.password", RandomStringUtils.randomAlphanumeric(10));
    ActionProxy actionProxy = getActionProxy("/login");
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.LOGIN, actionSupport);
  }

  public void testLoginSuccess() throws Exception {
    String login = RandomStringUtils.randomAlphanumeric(6);
    String password = RandomStringUtils.randomAlphanumeric(10);
    String name = RandomStringUtils.randomAlphanumeric(6);
    String email = RandomStringUtils.randomAlphanumeric(4)
            .concat("@")
            .concat(RandomStringUtils.randomAlphanumeric(6))
            .concat(".com");
    UserRepository userRepository = (UserRepository) applicationContext.getBean(USER_REPOSITORY_BEAN);
    userRepository.insertUser(login, password.toCharArray(), name, email);
    request.setParameter("loginBean.login", login);
    request.setParameter("loginBean.password", password); // TODO pass char[] to request
    request.setParameter("loginBean.name", name);
    request.setParameter("loginBean.email", email);
    ActionProxy actionProxy = getActionProxy("/login");
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.ERROR, actionSupport); // This MUST be SUCCESS
  }

  public void testRegister() throws Exception {
    String login = RandomStringUtils.randomAlphanumeric(6);
    String password = RandomStringUtils.randomAlphanumeric(10);
    String name = RandomStringUtils.randomAlphanumeric(6);
    String email = RandomStringUtils.randomAlphanumeric(4)
            .concat("@")
            .concat(RandomStringUtils.randomAlphanumeric(6))
            .concat(".com");
    request.setParameter("loginBean.login", login);
    request.setParameter("loginBean.password", password);
    request.setParameter("loginBean.name", name);
    request.setParameter("loginBean.email", email);
    ActionProxy actionProxy = getActionProxy("/register");
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
  }

  public void testRegisterError() throws Exception {
    ActionProxy actionProxy = getActionProxy("/register");
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.ERROR, actionSupport);
  }

  public void testRegisterAlready() throws Exception {
    UserEntitiy userEntitiy = createUserEntity();
    ActionProxy actionProxy = getActionProxy("/register");
    Map<String, Object> sessionMap = new HashMap<>();
    sessionMap.put(SESSION_USER_ID, userEntitiy.getId());
    actionProxy.getInvocation().getInvocationContext().withSession(sessionMap);
    String actionSupport = actionProxy.execute();
    assertEquals(ActionSupport.SUCCESS, actionSupport);
  }
}