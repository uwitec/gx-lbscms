package bjwxsytx.system.role.dao;

import java.util.List;

import bjwxsytx.common.CommonDAO;
import bjwxsytx.system.entity.SysRoleMenu;
import bjwxsytx.system.entity.SysUserRole;
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
