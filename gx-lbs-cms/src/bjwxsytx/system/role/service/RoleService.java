package bjwxsytx.system.role.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.entity.SysRoleMenu;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.role.dao.RoleDAO;
import bjwxsytx.system.role.dao.RoleMenuDAO;
import bjwxsytx.system.user.dao.UserDAO;
import bjwxsytx.system.user.vo.QueryVO;

@Service("roleService")
public class RoleService {
	@Autowired(required = true)
	private RoleDAO roleDAO;
	@Autowired(required = true)
	private RoleMenuDAO roleMenuDAO;

	public List<SysRole> findRole(){
		return this.roleDAO.findRole();
	}
	
	public Page findRole(Page page){
		return this.roleDAO.findRole(page);
	}
	
	public void saveRole(SysRole entity,String ids){
		if(entity.getRoleId()!=null){
			this.roleDAO.update(entity);
			this.roleMenuDAO.deleteByHql("delete  SysRoleMenu srm where srm.roleId = ?", new Object[]{Long.valueOf(entity.getRoleId())});
		}else{
			this.roleDAO.save(entity);
		}
		if(ids!=null){
			if(ids.length()>0){
			String[] arr = ids.split(",");
				for(int i = 0 ; i < arr.length ;i++){
					String id = arr[i];
					SysRoleMenu srm = new SysRoleMenu();
					srm.setRoleId(entity.getRoleId());
					srm.setMenuId(new Long(id));
					roleMenuDAO.save(srm);
				}
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteRole(String ids){
		//Object[] obj = new Object[]{ids};
		//this.roleMenuDAO.deleteByHql("delete  SysRoleMenu srm where srm.roleId = ? ", obj);
		//this.roleDAO.deleteByHql("delete SysRole sr where sr.roleId in (?)", obj);
		

		String [] arr = ids.split(",");
		for(int i = 0 ;  i < arr.length ; i++){
			String roleId = arr[i];
			this.roleDAO.deleteByHql("delete SysRole sr where sr.roleId =?", new Object[]{Long.valueOf(roleId)});
			this.roleMenuDAO.deleteByHql("delete  SysRoleMenu srm where srm.roleId = ?", new Object[]{Long.valueOf(roleId)});
		}
		
		
		
		//for(int i = 0 ; i < arr.length;i++){
		////	String roleId = arr[i];
		//	this.roleMenuDAO.deleteByHql("delete  SysRoleMenu srm where srm.roleId = ?", new Object[]{Long.valueOf(roleId)});
		//}
	}

}
