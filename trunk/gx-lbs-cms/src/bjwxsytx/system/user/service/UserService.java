package bjwxsytx.system.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.user.dao.UserDAO;
import bjwxsytx.system.user.vo.QueryVO;

@Service("userService")
public class UserService {
	@Autowired(required = true)
	private UserDAO userDAO;
	
	public SysUser login(QueryVO queryVO) throws OperationException{
		return this.userDAO.checkAccount(queryVO);
	}
	
	public Page findUser(Page page,QueryVO queryVO){
		return this.userDAO.findUser(page,queryVO);
	}
	
	public void saveUser(SysUser entity){
		this.userDAO.save(entity);
	}
}
