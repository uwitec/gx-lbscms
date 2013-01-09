package bjwxsytx.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bjwxsytx.system.entity.SysMenu;
import bjwxsytx.system.entity.SysRole;



/**
 * 
 * 功能描述:登录验证util类
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author zhaoj 新增日期：2008-11-14
 * @author 你的姓名 修改日期：2008-11-14
 * @since wapportal_manager version(2.0)
 */
public class AuthenticationUtil {
	static final public String ACCOUNT_SESSION_KEY = "currentUserAccount";

	static final public String VALIDATE_CODE_KEY = "validateCode";

	static final public String NAME_SESSION_KEY = "currentUserName";

	static final public String ID_SESSION_KEY = "currentUserId";
	
	static final public String AUTHENTICATION_URL = "authenticationUrl";

	@SuppressWarnings("unchecked")
	static public String getCurrentUserAccount(Map session) {
		return (String) session.get(ACCOUNT_SESSION_KEY);

	}

	@SuppressWarnings("unchecked")
	static public void setCurrentUserAccount(Map session, String account) {
		session.put(ACCOUNT_SESSION_KEY, account);

	}

	@SuppressWarnings("unchecked")
	static public String getCurrentUserId(Map session) {
		return (String) session.get(ID_SESSION_KEY);

	}

	@SuppressWarnings("unchecked")
	static public void setCurrentUserId(Map session, String id) {
		session.put(ID_SESSION_KEY, id);

	}

	@SuppressWarnings("unchecked")
	static public String getCurrentUserName(Map session) {
		return (String) session.get(NAME_SESSION_KEY);

	}

	@SuppressWarnings("unchecked")
	static public void setCurrentUserName(Map session, String name) {
		session.put(NAME_SESSION_KEY, name);

	}

	@SuppressWarnings("unchecked")
	static public boolean isSessionActivated(Map session) {
		return !BlankUtil.isBlank(session.get(ACCOUNT_SESSION_KEY));
	}

	@SuppressWarnings("unchecked")
	static public void clearCurrentUserAccount(Map session) {
		session.clear();
	}

	@SuppressWarnings("unchecked")
	static public boolean validateCode(Map session, String validateCode) {
		return !BlankUtil.isBlank(validateCode)
				&& validateCode.equalsIgnoreCase((String) session
						.get(VALIDATE_CODE_KEY));

	}
	
	/**
	 * 
	* 方法用途和描述: 将用户认证的URL信息保存在session
	* @param session
	* @param list
	* @author libu 新增日期：2008-12-4
	* @author 你的姓名 修改日期：2008-12-4
	* @since wapportal_manager version(2.0)
	 */
	@SuppressWarnings("unchecked")
	static public void setAuthenticationUrl(Map session, List<SysMenu> list ){
		List urlList = new ArrayList();
		if(!BlankUtil.isBlank(list) && list.size() > 0){
			for(int i = 0 ;i < list.size() ; i++){
				SysMenu menu = list.get(i);
				urlList.add(menu.getUrl());
				//''System.out.println(menu.getUrl());
			//	Role role = list.get(i);
				//Iterator ite = role.getMenus().iterator();
				//System.out.println(role.getName());
				//while(ite.hasNext()){
					
			//		Menu menu = (Menu)ite.next();
				//	System.out.println(menu.getAuthUrl()+"  menu.getAuthUrl()");
				//	urlList.add(menu.getAuthUrl());
				//}
			}
		}
		session.put(AUTHENTICATION_URL, urlList);
	}
	
	/**
	 * 
	* 方法用途和描述: 对指定的url进行认证
	* @param url  需要认证的url
	* @param session  
	* @return  认证通过返回 true,不通过返回 false
	* @author libu 新增日期：2008-12-4
	* @author 你的姓名 修改日期：2008-12-4
	* @since wapportal_manager version(2.0)
	 */
	@SuppressWarnings("unchecked")
	static public boolean isAuthorized(String url,Map session){
		if(BlankUtil.isBlank(url) || BlankUtil.isBlank(session))
			return false;
		boolean result = false;
		List<String> urlList = (List<String>)session.get(AUTHENTICATION_URL);
		for(String urlItem : urlList){
		//	System.out.println(urlItem+" urlItem"+" url="+url);
			if(BlankUtil.isBlank(urlItem))
				continue;
			if(url.startsWith(urlItem))
					result = true;
		}
		return result;
	}
}
