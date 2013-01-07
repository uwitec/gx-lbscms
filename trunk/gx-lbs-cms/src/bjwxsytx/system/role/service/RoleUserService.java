package bjwxsytx.system.role.service;

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
}
