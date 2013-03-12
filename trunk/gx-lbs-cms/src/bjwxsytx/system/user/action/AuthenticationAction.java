package bjwxsytx.system.user.action;

import java.util.Date;
import java.util.List;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.base.constants.ExceptionConstants;
import bjwxsytx.base.constants.OperConstants;
import bjwxsytx.common.AuthenticationUtil;
import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.Result;
import bjwxsytx.system.entity.OperatorLog;
import bjwxsytx.system.entity.SysMenu;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.entity.TUserOnline;
import bjwxsytx.system.menu.service.MenuService;
import bjwxsytx.system.operatorLog.service.OperatorLogService;
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
	@Autowired(required = true)
	private OperatorLogService operatorLogService;

	/**
	 * 
	 * 方法用途和描述: 用户登录
	 * 
	 * @return
	 * @author zhaoj 新增日期：2008-11-14
	 * @author libu 修改日期：2008-12-4
	 * @throws Exception 
	 * @since wapportal_manager version(2.0)
	 */
	public String login() throws Exception  {
		try{
			_log.info("---Iamlogin--queryVO:" + queryVO);
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
	//		0:CMS管理员用户;1:EC/SI定位企业
			AuthenticationUtil.setGroupUserFlag(getSessionMap(), user.getGroupUserFlag().toString());
			//获取用户权限信息
			List<SysMenu> list = menuService.findMenuByUserId(user.getUserId());
			//System.out.println(roleMenuList.size());
			//_log.info("role size"+roleMenuList.size());
			//System.out.println(roleMenuList.size());
			AuthenticationUtil.setAuthenticationUrl(getSessionMap(), list);
			OperatorLog oper = new OperatorLog();
			oper.setAdminid(user.getUserId());
			oper.setDescription(OperConstants.DESC_LOGIN);
			oper.setOpertype(OperConstants.TYPE_LOGIN);
			oper.setOpertime(new Date());
			oper.setLoginName(user.getLoginName());
			operatorLogService.saveOperatorLog(oper);
//			==============================
//			唯一性登录控制 start
//			==============================
//			step1:取得上次登录记录
			String hql = "from TUserOnline where userId=" + user.getUserId() + " order by id desc" ;
			TUserOnline uOnline = userService.findUserOnline(hql);
			
			getSessionMap().put("userOnline", uOnline);
//			step2:记录在线表
			TUserOnline online = new TUserOnline();
			
			Date currentdate = new Date();
			
			online.setUserId(user.getUserId());
			online.setSessionId(getHttpRequest().getSession().getId());
			online.setIp(getHttpRequest().getRemoteAddr());
			online.setLoginTime(currentdate);			
			
			userService.saveUserOnline(online);
			
			
//			==============================
//			唯一性登录控制 end
//			==============================	
		}catch(Exception ex){
			ex.printStackTrace();
			_log.error("loginException:",ex);
		}
		return SUCCESS;
	}
	
	public String validateCode(){
		this.result = new Result();
		if(queryVO!=null){
			if(queryVO.getValidateCode()!=null){
				if(this.queryVO.getValidateCode().equals(String.valueOf(this.getSessionMap().get(AuthenticationUtil.VALIDATE_CODE_KEY)))){
					this.result.setFlag(Result.FLAG_SUCCESS);
					return SUCCESS;
				}else{
					this.result.setFlag(Result.FLAG_FAILURE);
				}
			}
		}
		return SUCCESS;
	}
	
	private boolean isUserExist;
	
	public void setUserExist(boolean isUserExist) {
		this.isUserExist = isUserExist;
	}
	public boolean getUserExist() {
		return this.isUserExist;
	}
	public String isUserExist(){
		
		isUserExist = this.userService.isUserExist(queryVO);
		
		return SUCCESS;
	}
	
	public String updatePwd() {
		try{
			this.result = new Result();
			Long userId = Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap()));
			String loginName = AuthenticationUtil.getCurrentUserAccount(this.getSessionMap());
			queryVO.setId(userId);
			if(userId!=null){
				SysUser su = userService.findUserById(queryVO);
				su.setLoginPass(queryVO.getPassword());
				userService.updatePwd(su);
				this.result.setFlag(Result.FLAG_SUCCESS);
				OperatorLog oper = new OperatorLog();
				oper.setAdminid(userId);
				oper.setDescription(OperConstants.DESC_SYS_UPDATE_PASS);
				oper.setOpertype(OperConstants.TYPE_SYS_UPDATE_PASS);
				oper.setOpertime(new Date());
				oper.setLoginName(loginName);
				operatorLogService.saveOperatorLog(oper);
				return SUCCESS;
			}
			this.result.setFlag(Result.FLAG_FAILURE);
			this.result.setMsg("登录超时!");
		}catch(Exception ex){
			ex.printStackTrace();
			_log.info(ex);
			this.result.setFlag(Result.FLAG_FAILURE);
			this.result.setMsg(ex.getLocalizedMessage());
			
		}
		
		return SUCCESS;
	}
	
	
	public String validatePwd(){
		this.result = new Result();
		try{
			if(AuthenticationUtil.getCurrentUserId(this.getSessionMap())==null){
				return "timeout";
			}
			queryVO.setId(Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap())));
			boolean bl = this.userService.validatePwd(queryVO);
			if(bl){
				this.result.setFlag(Result.FLAG_SUCCESS);
			}else{
				this.result.setFlag(Result.FLAG_FAILURE);
			}
			
		}catch(Exception ex){
			this.result.setFlag(Result.FLAG_ERROE);
			this.result.setMsg(ex.getLocalizedMessage());
		}
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
		Long userId = Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap()));
		String loginName = AuthenticationUtil.getCurrentUserAccount(this.getSessionMap());
		
		OperatorLog oper = new OperatorLog();
		oper.setAdminid(userId);
		oper.setLoginName(loginName);
		oper.setDescription(OperConstants.DESC_LOGOUT);
		oper.setOpertype(OperConstants.TYPE_LOGOUT);
		oper.setOpertime(new Date());
		operatorLogService.saveOperatorLog(oper);
		AuthenticationUtil.clearCurrentUserAccount(getSessionMap());
//		add by chenhui,清空所有已定义的session 
		getHttpRequest().getSession().invalidate();
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
