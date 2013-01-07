package bjwxsytx.system.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.role.dao.RoleDAO;
import bjwxsytx.system.user.dao.UserDAO;
import bjwxsytx.system.user.vo.QueryVO;

@Service("roleService")
public class RoleService {
	@Autowired(required = true)
	private RoleDAO roleDAO;
	

	public List<SysRole> findRole(){
		return this.roleDAO.findRole();
	}
	

	

}
