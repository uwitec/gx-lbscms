package bjwxsytx.system.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.system.entity.SysUserRole;
import bjwxsytx.system.role.dao.RoleUserDAO;
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
