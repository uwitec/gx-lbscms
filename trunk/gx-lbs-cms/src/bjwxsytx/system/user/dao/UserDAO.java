package bjwxsytx.system.user.dao;

import java.util.List;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
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
}
