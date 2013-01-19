package bjwxsytx.system.role.dao;

import java.util.ArrayList;
import java.util.List;

import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.menu.vo.QueryVO;
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
