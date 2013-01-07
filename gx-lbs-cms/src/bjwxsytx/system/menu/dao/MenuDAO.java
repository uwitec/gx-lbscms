package bjwxsytx.system.menu.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.exception.SystemException;
import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.system.entity.SysMenu;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.user.dao.UserDAO;

public class MenuDAO extends CommonDAO<SysMenu>{

	
	public List<SysMenu> findMenuByUserId(Long userId)throws Exception{
		Object[] object = null;
		if(!BlankUtil.isBlank(userId)){
			object = new Object[]{userId};
		}
		StringBuffer hql = new StringBuffer("select sm from ");
		hql.append(" SysUserRole sur ,SysRoleMenu srm, SysMenu sm ");
		hql.append("  where sur.userId = ? and sur.roleId = srm.roleId and sm.menuId = srm.menuId");
		List<SysMenu> list = this.searchAll(hql.toString(),object);
		return list;
	}
	
	public List<SysMenu> findMenuOneByUserId(Long userId) throws Exception{
		Object[] object = null;
		if(!BlankUtil.isBlank(userId)){
			object = new Object[]{userId};
		}
		StringBuffer hql = new StringBuffer("select sm from ");
		hql.append(" SysUserRole sur ,SysRoleMenu srm, SysMenu sm ");
		hql.append("  where sur.userId = ? and sur.roleId = srm.roleId ");
		hql.append("  and sm.menuId = srm.menuId and sm.parentId = 0 and sm.isMenuTree = 1");
		
		List<SysMenu> list = this.searchAll(hql.toString(),object);
		return list;
	}
	
	public List<SysMenu> findMenuTwoByUserId(Long userId)throws Exception{
		Object[] object = null;
		if(!BlankUtil.isBlank(userId)){
			object = new Object[]{userId};
		}
		StringBuffer hql = new StringBuffer("select sm from ");
		hql.append(" SysUserRole sur ,SysRoleMenu srm, SysMenu sm ");
		hql.append("  where sur.userId = ? and sur.roleId = srm.roleId ");
		hql.append("  and sm.menuId = srm.menuId and sm.parentId <> 0 and sm.isMenuTree = 1");
		
		List<SysMenu> list = this.searchAll(hql.toString(),object);
		return list;
	}
}
