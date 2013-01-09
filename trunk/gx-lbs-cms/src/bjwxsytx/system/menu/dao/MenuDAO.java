package bjwxsytx.system.menu.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.exception.SystemException;
import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysMenu;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.menu.vo.QueryVO;
import bjwxsytx.system.user.dao.UserDAO;


public class MenuDAO extends CommonDAO<SysMenu>{

	public Page findMenu(Page page,QueryVO queryVO){
		List params =null;
		//page.setField(" u ");
		StringBuffer where = new StringBuffer();
		if(queryVO!=null){
			
			params = new ArrayList<String>();
			if(!BlankUtil.isBlank(queryVO.getMenuId())){
				params.add(queryVO.getMenuId());
				where.append(" and sm.parentId = ? ");
				
			
			}
		}else{
			where.append(" and sm.parentId = 0 " );
		}
		page.setCondition(where.toString());
		page.setTableName("  SysMenu sm ");
		
		
		return this.findResult(page,params);
	}
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
