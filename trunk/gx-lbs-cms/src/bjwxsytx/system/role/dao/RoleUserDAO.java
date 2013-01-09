package bjwxsytx.system.role.dao;

import java.util.List;

import bjwxsytx.common.CommonDAO;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.entity.SysUserRole;

public class RoleUserDAO extends CommonDAO<SysUserRole>{

	public void saveUserRole(SysUserRole entity){
		this.save(entity);
	}
	

}
