package bjwxsytx.system.role.action;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.role.service.RoleService;

public class RoleAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Autowired(required = true)
	private RoleService roleService;
	private List<SysRole> roleList ;
	
	
	
	public List<SysRole> getRoleList() {
		return roleList;
	}



	public void setRoleList(List<SysRole> roleList) {
		this.roleList = roleList;
	}



	public String findRole(){
		this.roleList = this.roleService.findRole();
		return SUCCESS;
	}
}
