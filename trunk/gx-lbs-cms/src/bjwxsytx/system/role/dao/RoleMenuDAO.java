package bjwxsytx.system.role.dao;

import java.util.List;

import bjwxsytx.common.CommonDAO;
import bjwxsytx.system.entity.SysRoleMenu;
import bjwxsytx.system.entity.SysUserRole;

public class RoleMenuDAO extends CommonDAO<SysRoleMenu>{
	public SysRoleMenu findSysUserRoleByMenuId(Long menuId){
		SysRoleMenu srm = null;
		List<SysRoleMenu> list = this.searchAll("from SysRoleMenu srm where srm.menuId = ? ", new Object[]{menuId});
		if(!list.isEmpty()){
			srm = list.get(0);
		}
		return srm;
	}
}
