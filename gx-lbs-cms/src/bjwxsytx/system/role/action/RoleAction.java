package bjwxsytx.system.role.action;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.common.Page;
import bjwxsytx.common.Result;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.entity.SysRoleMenu;
import bjwxsytx.system.role.service.RoleMenuService;
import bjwxsytx.system.role.service.RoleService;
/***
 * 
* 功能描述:XXXXXXXXX（可以分多行编写）
* <p>版权所有：中太数据
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author 刘小明 新增日期：2013-1-16
* @author 你的姓名 修改日期：2013-1-16
* @since gx-cms
 */
public class RoleAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Autowired(required = true)
	private RoleService roleService;
	@Autowired(required = true)
	private RoleMenuService roleMenuService;
	private List<SysRole> roleList ;
	private SysRole sysRole;
	private Result result;
	private SysRoleMenu sysRoleMenu;

	List<SysRoleMenu> listSysRoleMenu;
	private long total;
	private List<Object> rows; 
	private String ids;
	

	public String list(){
		try{
			Page page = new Page(this.getHttpRequest());
			this.roleService.findRole(page);
			this.total = page.getTotalCount();
			this.rows = page.getList();			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	public String findRole(){
		this.roleList = this.roleService.findRole();
		return SUCCESS;
	}
	
	public String saveRole(){
		result = new Result();
		
		this.roleService.saveRole(sysRole, ids);
		result.setFlag(Result.FLAG_SUCCESS);
		return SUCCESS;
	}
	
	public String deleteRole(){
		
		result = new Result();
		try{

			this.roleService.deleteRole(this.ids);
			result.setFlag(Result.FLAG_SUCCESS);
		}catch(Exception ex){
			result.setFlag(Result.FLAG_FAILURE);
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String findRoleMenuByRoleId(){
		this.listSysRoleMenu = this.roleMenuService.findRoleMenuByRoleId(this.sysRoleMenu);
		return SUCCESS;
	}
	
	public List<SysRoleMenu> getListSysRoleMenu() {
		return listSysRoleMenu;
	}
	public void setListSysRoleMenu(List<SysRoleMenu> listSysRoleMenu) {
		this.listSysRoleMenu = listSysRoleMenu;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public String roleListPage(){
		return SUCCESS;
	}
	
	public List<SysRole> getRoleList() {
		return roleList;
	}


	public SysRoleMenu getSysRoleMenu() {
		return sysRoleMenu;
	}

	public void setSysRoleMenu(SysRoleMenu sysRoleMenu) {
		this.sysRoleMenu = sysRoleMenu;
	}

	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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
}
