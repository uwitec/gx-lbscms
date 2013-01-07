package bjwxsytx.system.role.dao;

import java.util.List;

import bjwxsytx.common.CommonDAO;
import bjwxsytx.system.entity.SysRole;

public class RoleDAO extends CommonDAO<SysRole>{

	public List<SysRole> findRole(){
		Object[] obj = null;
		return this.searchAll("from SysRole", obj);
	}
}
