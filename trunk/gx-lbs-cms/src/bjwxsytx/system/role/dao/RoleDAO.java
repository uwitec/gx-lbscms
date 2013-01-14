package bjwxsytx.system.role.dao;

import java.util.ArrayList;
import java.util.List;

import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.menu.vo.QueryVO;

public class RoleDAO extends CommonDAO<SysRole>{

	public List<SysRole> findRole(){
		Object[] obj = null;
		return this.searchAll("from SysRole", obj);
	}
	
	public Page findRole(Page page){
		List params =null;
		//page.setField(" u ");
		StringBuffer where = new StringBuffer();
		page.setCondition(where.toString());
		page.setTableName("  SysRole sr ");
		return this.findResult(page,params);
	}
}
