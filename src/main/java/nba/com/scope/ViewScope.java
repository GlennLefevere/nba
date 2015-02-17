package nba.com.scope;

import java.io.Serializable;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

public class ViewScope implements Scope, Serializable {
	private static final long serialVersionUID = 1L;
	public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callbacks";

	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		/*
		 * Map<String, Object> viewMap =
		 * FacesContext.getCurrentInstance().getViewRoot().getViewMap(); if
		 * (viewMap.containsKey(name)) { return viewMap.get(name); } else {
		 * Object object = objectFactory.getObject(); viewMap.put(name, object);
		 * return object; }
		 */
		Object instance = getViewMap().get(name);
		if (instance == null) {
			instance = objectFactory.getObject();
			getViewMap().put(name, instance);
		}
		return instance;
	}

	@Override
	public Object remove(String name) {
		/*
		 * if (FacesContext.getCurrentInstance().getViewRoot() != null) { return
		 * FacesContext
		 * .getCurrentInstance().getViewRoot().getViewMap().remove(name); } else
		 * { return null; }
		 */
		Object instance = getViewMap().remove(name);
		if (instance != null) {
			@SuppressWarnings("unchecked")
			Map<String, Runnable> map = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
			Map<String, Runnable> callbacks = map;
			if (callbacks != null) {
				callbacks.remove(name);
			}
		}
		return instance;
	}

	private Map<String, Object> getViewMap() {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap();
	}

	@Override
	public void registerDestructionCallback(String name, Runnable callback) {
		// Not supported
		@SuppressWarnings("unchecked")
		Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
		if (callbacks != null) {
			callbacks.put(name, callback);
		}
	}

	@Override
	public Object resolveContextualObject(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.resolveReference(key);
	}

	@Override
	public String getConversationId() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesRequestAttributes facesRequestAttributes = new FacesRequestAttributes(facesContext);
		return facesRequestAttributes.getSessionId() + "-" + facesContext.getViewRoot().getViewId();
	}

}
