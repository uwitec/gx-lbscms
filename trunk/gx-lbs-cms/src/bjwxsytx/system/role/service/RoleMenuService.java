package bjwxsytx.system.role.service;

import java.util.List;

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
	
	public void saveRoleMenu(SysRoleMenu entity){
		this.roleMenuDAO.save(entity);
	}
	
	public List<SysRoleMenu> findRoleMenuByRoleId(SysRoleMenu entity){
		
		return this.roleMenuDAO.searchAll(" from SysRoleMenu srm where srm.roleId = ? ", new Object[]{entity.getRoleId()});
	}
	
}
