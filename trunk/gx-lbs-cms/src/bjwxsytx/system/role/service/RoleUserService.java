package bjwxsytx.system.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.system.entity.SysUserRole;
import bjwxsytx.system.role.dao.RoleUserDAO;

@Service("roleUserService")
public class RoleUserService {
	@Autowired(required = true)
	private RoleUserDAO roleUserDAO;
	
	public void saveUserRole(SysUserRole entity){
		this.roleUserDAO.saveUserRole(entity);
	}
	
	public SysUserRole findRoleUser(Long userId){
		SysUserRole sur = null;
		List<SysUserRole> list = this.roleUserDAO.searchAll("from SysUserRole sur where sur.userId = ?", new Object[]{userId});
		if(!list.isEmpty()){
			sur = list.get(0);
		}
		return sur;
	}
}
