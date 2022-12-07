package es.edudediegolucas.petitionweb.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import es.edudediegolucas.petitionweb.repository.petition.PetitionRepository;
import es.edudediegolucas.petitionweb.repository.user.UserRepository;
import org.apache.struts2.action.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public abstract class BaseAction extends ActionSupport implements SessionAware, ParameterNameAware {

  protected static final String SESSION_USER_ID = "userId";
  protected Map<String, Object> session;

  @Autowired
  protected UserRepository userRepository;
  @Autowired
  protected PetitionRepository petitionRepository;

  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }

  @Override
  public boolean acceptableParameterName(String parameterName) {
    return !parameterName.contains("session") && !parameterName.contains("request");
  }

  protected void putInSession(String key, Object value) {
    session.put(key, value);
  }

  protected boolean isValueInSession(String key) {
    return session.containsKey(key);
  }

  protected Object getValueFromSession(String key) {
    return session.get(key);
  }

  protected void removeFromSession(String key) {
    session.remove(key);
  }
}
