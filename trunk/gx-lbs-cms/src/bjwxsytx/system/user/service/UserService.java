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
/***
 * 
* 功能描述:XXXXXXXXX（可以分多行编写）
* <p>版权所有：中太数据
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author 刘小明 新增日期：2013-1-16
* @author 你的姓名 修改日期：2013-1-16
* @since gx-cms
 */
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
	
	public void updatePwd(SysUser user) throws Exception{
			this.userDAO.update(user);
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
	
	public boolean validatePwd(QueryVO queryVO){
		List<SysUser> list = this.userDAO.searchAll(" from SysUser su where su.loginPass= ? and su.userId = ?", new Object[]{queryVO.getOldPwd(),queryVO.getId()});
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
