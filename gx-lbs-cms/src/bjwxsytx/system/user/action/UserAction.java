package bjwxsytx.system.user.action;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.common.Page;
import bjwxsytx.common.Result;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.entity.SysUserRole;
import bjwxsytx.system.role.service.RoleService;
import bjwxsytx.system.role.service.RoleUserService;
import bjwxsytx.system.user.service.UserService;
import bjwxsytx.system.user.vo.QueryVO;

public class UserAction extends BaseAction {

	private static final long serialVersionUID = -487695416956573238L;
	@Autowired(required = true)
	private UserService userService;
	@Autowired(required = true)
	private RoleService roleService;
	@Autowired(required = true)
	private RoleUserService roleUserService;
	private Result result;
	private long total;
	private List<Object> rows; 
	private QueryVO queryVO ;
	private SysUser sysUser;
	private SysRole sysRole;
	private SysUserRole sysUserRole;
	private String ids;
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SysUserRole getSysUserRole() {
		return sysUserRole;
	}

	public void setSysUserRole(SysUserRole sysUserRole) {
		this.sysUserRole = sysUserRole;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
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

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String userListPage(){
		return SUCCESS;
	}
	
	public String list(){
		try{
			Page page = new Page(this.getHttpRequest());
			//System.out.println(queryVO.getLoginName());
			this.userService.findUser(page, queryVO);
			this.total = page.getTotalCount();
			this.rows = page.getList();			
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String saveUser() throws Exception{
		this.result = new Result();
		this.sysUser.setCreateTime(new Date());
		this.userService.saveUser(sysUser,sysUserRole);
		this.result.setFlag(Result.FLAG_SUCCESS);
		return SUCCESS;
	}
	
	public String deleteUser(){
		try {
			this.result = new Result();
			this.userService.deleteUser(ids);

			
			result.setFlag(Result.FLAG_SUCCESS);
		} catch (Exception e) {
			result.setFlag(Result.FLAG_FAILURE);
			this.result.setMsg(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String findUserById(){
		this.sysUser = this.userService.findUserById(queryVO);
		this.sysUserRole = this.roleUserService.findRoleUser(sysUser.getUserId());
		return SUCCESS;
	}
}
