package bjwxsytx.system.user.action;

import java.util.List;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.base.constants.ExceptionConstants;
import bjwxsytx.common.AuthenticationUtil;
import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.Result;
import bjwxsytx.system.entity.SysMenu;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.menu.service.MenuService;
import bjwxsytx.system.user.service.UserService;
import bjwxsytx.system.user.vo.QueryVO;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import bjwxsytx.base.exception.*;

/**
 * 
 * 功能描述:登陆项目框架页面控制类
 * <p>
 * 版权所有：金鹏科技
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author dengcd 新增日期：2008-10-31
 * @author 你的姓名 修改日期：2008-10-31
 * @since wapportal_manager version(2.0)
 */
@Controller
public class AuthenticationAction extends BaseAction {
	/**
	 * 说明清楚此属性的业务含义
	 */
	private static final long serialVersionUID = 7456715684775676928L;
	private static Logger _log = Logger.getLogger(AuthenticationAction.class);
	private String errorCode;
	private boolean success = true;
	private QueryVO queryVO ;
	private Result result;
	@Autowired(required = true)
	private UserService userService;
	@Autowired(required = true)
	private MenuService menuService;

	/**
	 * 
	 * 方法用途和描述: 用户登录
	 * 
	 * @return
	 * @author zhaoj 新增日期：2008-11-14
	 * @author libu 修改日期：2008-12-4
	 * @throws OperationException 
	 * @throws SystemException 
	 * @since wapportal_manager version(2.0)
	 */
	public String login() throws OperationException,SystemException  {
		this.result = new Result();
		
		SysUser user =userService.login(queryVO);
		if (BlankUtil.isBlank(user)) {
			//throw new OperationException(ExceptionConstants.EXCEPTION_OPERATION_MENU_USED);
			this.result.setFlag(Result.FLAG_FAILURE);
			this.result.setMsg("用户密或密码错误!");
			return SUCCESS;
		}
		

		
		this.result.setFlag(Result.FLAG_SUCCESS);
		AuthenticationUtil.setCurrentUserName(getSessionMap(), user.getUserName());
		AuthenticationUtil.setCurrentUserAccount(getSessionMap(), user.getLoginName());
		AuthenticationUtil.setCurrentUserId(getSessionMap(), user.getUserId().toString());
		//获取用户权限信息
		List<SysMenu> list = menuService.findMenuByUserId(user.getUserId());
		//System.out.println(roleMenuList.size());
		//_log.info("role size"+roleMenuList.size());
		//System.out.println(roleMenuList.size());
		AuthenticationUtil.setAuthenticationUrl(getSessionMap(), list);
		return SUCCESS;
	}

	public String isSessionNull(){
		this.result = new Result();
		if(BlankUtil.isBlank(AuthenticationUtil.getCurrentUserId(this.getSessionMap()))){
			this.result.setFlag(2);
		}
		return SUCCESS;
	}
	public String logout() {
		_log.info("--------logout--");
		AuthenticationUtil.clearCurrentUserAccount(getSessionMap());
		return LOGIN;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public QueryVO getQueryVO() {
		return queryVO;
	}
	public void setQueryVO(QueryVO queryVO) {
		this.queryVO = queryVO;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public boolean getSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
