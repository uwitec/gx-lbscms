package bjwxsytx.base.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.AuthenticationUtil;
import bjwxsytx.system.entity.TUserOnline;
import bjwxsytx.system.user.service.UserService;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;




import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * 功能描述:验证用户登录拦截器
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author zhaoj 新增日期：2008-11-14
 * @author 你的姓名 修改日期：2008-11-14
 * @since wapportal_manager version(2.0)
 */

@Controller
public class AuthenticationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -4994877859122219609L;
	
	final static public String TIMEOUT = "timeout";
	
	private static Logger _log = Logger.getLogger(AuthenticationInterceptor.class);
	
	@Autowired(required = true)
	private UserService userService;

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		boolean userHadOnlineFlag = false;
		ActionContext actionContext = invocation.getInvocationContext();
		
		_log.info("---intercept----" + invocation.getAction()+ "!" + invocation.getResultCode());
		Map session = actionContext.getSession();
		HttpServletRequest request = (HttpServletRequest)actionContext.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String url = request.getServletPath();
		url = url.substring(1);
//		add step: 唯一性登录验证
		String hql = "from TUserOnline where userId=" + AuthenticationUtil.getCurrentUserId(session) + " order by id desc" ;
//		当前用户的最新登录记录： uOnline
		TUserOnline uOnline = userService.findUserOnlineLasted(hql);
		String sessionId = request.getSession().getId();
		
		if(uOnline != null &&!(uOnline.getSessionId().equals(sessionId))){
			userHadOnlineFlag = true;
			throw new OperationException("您的账号已经在其他地方登录！");
		}else{
			
		}
//		add end...
		if(!userHadOnlineFlag){	// 未在别处登录，则继续	
			if (AuthenticationUtil.isSessionActivated(session)) {
				if(AuthenticationUtil.isAuthorized(url, session)){
					return invocation.invoke();
				}else{
					throw new OperationException("您无权执行此操作！");
				}
			}
		}
		return TIMEOUT;
	}

}
