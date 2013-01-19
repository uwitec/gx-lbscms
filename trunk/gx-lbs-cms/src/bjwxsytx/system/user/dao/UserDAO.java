package bjwxsytx.system.user.dao;

import java.util.ArrayList;
import java.util.List;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysUser;
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
public class UserDAO extends CommonDAO<SysUser>{

	/***
	 * 验证用户登录
	 */
	public SysUser checkAccount(QueryVO queryVO)throws OperationException{
		Object[] object = null;
		if(!BlankUtil.isBlank(queryVO)){
			object = new Object[]{queryVO.getUsername(),queryVO.getPassword()};
		}
		List<SysUser> list = this.searchAll("from SysUser su where su.loginName = ? and su.loginPass = ?",object);
		SysUser user = null;
		if(!list.isEmpty()){
			user = list.get(0);
		}
		return user;
	}
	
	public Page findUser(Page page,QueryVO queryVO){
		List<String> params =null;
		//page.setField(" u ");
		page.setTableName("SysUser u ");
		System.out.println(queryVO);
		if(!BlankUtil.isBlank(queryVO)){
			StringBuffer where = new StringBuffer();
			params = new ArrayList<String>();
			if(!BlankUtil.isBlank(queryVO.getUsername())){
				params.add('%' + queryVO.getUsername().trim() + '%');
				where.append(" and u.userName like ? ");
			}
			if(!BlankUtil.isBlank(queryVO.getLoginName())){
				params.add('%' + queryVO.getLoginName().trim() + '%');
				where.append(" and u.loginName like ? ");
			}
			page.setCondition(where.toString());
		}
		//this.findResult(page, params);
		return this.findResult(page,params);
	}

}
