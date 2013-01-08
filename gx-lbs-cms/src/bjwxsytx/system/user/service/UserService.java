package bjwxsytx.system.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.entity.SysUserRole;
import bjwxsytx.system.role.dao.RoleUserDAO;
import bjwxsytx.system.role.service.RoleUserService;
import bjwxsytx.system.user.dao.UserDAO;
import bjwxsytx.system.user.vo.QueryVO;

@Service("userService")
public class UserService {
	@Autowired(required = true)
	private UserDAO userDAO;
	@Autowired(required = true)
	private RoleUserDAO roleUserDAO;
	public SysUser login(QueryVO queryVO) throws OperationException{
		return this.userDAO.checkAccount(queryVO);
	}
	
	public Page findUser(Page page,QueryVO queryVO){
		return this.userDAO.findUser(page,queryVO);
	}
	
	public void saveUser(SysUser user,SysUserRole sur) throws Exception{
		if(user.getUserId()!=null){
			this.userDAO.update(user);
			sur.setUserId(user.getUserId());
			this.roleUserDAO.update(sur);
		}else{
			this.userDAO.save(user);
			sur.setUserId(user.getUserId());
			this.roleUserDAO.save(sur);
		}
		
		
	}
	
	public boolean isUserExist(QueryVO queryVO){
		List<SysUser> list = this.userDAO.searchAll(" from SysUser su where su.loginName = ?", new Object[]{queryVO.getUsername()});
		if(list.size() ==0 ){
			return false;
		}else{
			return true;
		}
	}
	
	public void deleteUser(String ids) throws Exception{
		//this.userDAO.deleteAll(list);
		///roleUserDAO.deleteByHql(hql, values)
		Object[] obj = null;
		this.userDAO.deleteByHql("delete  SysUser su where su.userId in ("+ids+")", obj);
		this.roleUserDAO.deleteByHql("delete SysUserRole sur where sur.userId in ("+ids+")", obj);
	}
	
	public SysUser findUserById(QueryVO vo){
		SysUser user = null;
		List<SysUser> list = this.userDAO.searchAll(" from SysUser su where su.userId = ?", new Object[]{vo.getId()});
		if(!list.isEmpty()){
			user = list.get(0);
		}
		return user;
	}
}
