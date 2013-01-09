package bjwxsytx.system.role.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.system.entity.SysRoleMenu;
import bjwxsytx.system.entity.SysUserRole;
import bjwxsytx.system.role.dao.RoleMenuDAO;
import bjwxsytx.system.role.dao.RoleUserDAO;
@Service("roleMenuService")
public class RoleMenuService {
	
	@Autowired(required = true)
	private RoleMenuDAO roleMenuDAO;
	
	
	public SysRoleMenu findSysUserRoleByMenuId(Long menuId){
		

		return this.roleMenuDAO.findSysUserRoleByMenuId(menuId);
	}
}
