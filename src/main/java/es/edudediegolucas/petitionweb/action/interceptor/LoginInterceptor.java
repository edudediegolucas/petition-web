package es.edudediegolucas.petitionweb.action.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import es.edudediegolucas.petitionweb.action.LoginAction;
import es.edudediegolucas.petitionweb.action.LoginRequired;
import java.util.Map;
import java.util.Objects;

public class LoginInterceptor extends AbstractInterceptor {

  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    Map<String, Object> session = ActionContext.getContext().getSession();
    // Check user is logged
    String userId = (String) session.get("userId");
    if (!Objects.isNull(userId)) {
      return invocation.invoke();
    }
    // Check if action does not require Login
    Object action = invocation.getAction();
    if (!(action instanceof LoginRequired)) {
      return invocation.invoke();
    }
    // Check if action required Login and it's not the LoginAction
    if (!(action instanceof LoginAction)) {
      return "loginRedirect";
    }
    // User requests login page
    return invocation.invoke();
  }
}
