package bjwxsytx.system.user.dao;

import java.util.ArrayList;
import java.util.List;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.user.vo.QueryVO;

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
		if(!BlankUtil.isBlank(queryVO)){
			StringBuffer where = new StringBuffer();
			params = new ArrayList<String>();
			if(!BlankUtil.isBlank(queryVO.getUsername())){
				params.add('%' + queryVO.getUsername().trim() + '%');
				where.append(" and username like ? ");
			}
			if(!BlankUtil.isBlank(queryVO.getNickname())){
				params.add('%' +queryVO.getNickname()+'%');
				where.append(" and nickname like ? ");
			}
			page.setCondition(where.toString());
		}
		//this.findResult(page, params);
		return this.findResult(page,params);
	}

}
