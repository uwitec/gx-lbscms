package bjwxsytx.base.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.AuthenticationUtil;

import org.apache.log4j.Logger;

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

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		
		_log.info("---AuthenticationInterceptor----" + invocation.getAction()
				+ "!" + invocation.getResultCode());
		Map session = actionContext.getSession();
		HttpServletRequest request = (HttpServletRequest)actionContext.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String url = request.getServletPath();
			url = url.substring(1);
			
		if (AuthenticationUtil.isSessionActivated(session)) {
			if(AuthenticationUtil.isAuthorized(url, session)){
				return invocation.invoke();
			}else{
				throw new OperationException("您无权执行此操作！");
			}
		}
		return TIMEOUT;
	}

}
