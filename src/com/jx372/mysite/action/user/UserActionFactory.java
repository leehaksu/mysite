package com.jx372.mysite.action.user;

import com.jx372.mysite.action.main.IndexAction;
import com.jx372.web.action.Action;
import com.jx372.web.action.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		// TODO Auto-generated method stub
		Action action=null;
		if("joinform".equals(actionName))
		{
			action = new JoinFormAction();
			
		}else if("join".equals(actionName))
		{
			action = new joinAction();
			
		}else if("joinsuccess".equals(actionName))
		{
			action = new joinSuccessAction();
			
		}else if("login".equals(actionName))
		{
			action = new LoginAction();
			
		}
		else if("loginform".equals(actionName))
		{
			action = new LoginFormAction();
			
		}else if("logout".equals(actionName))
		{
			action = new LogOutAction();
			
		}
		else if("modifyform".equals(actionName))
		{
			
			action = new ModifyFormAction();
			
		}
		else if("modify".equals(actionName))
		{
			
			action = new ModifyAction();
			
		}
		else
		{
			action = new IndexAction();
		}
		return action;
	}

}
