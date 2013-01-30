package bjwxsytx.system.ips.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.system.ips.dao.UserIpsDAO;

/***
 * 
* 功能描述（可以分多行编写）
* <p>版权所有：北京无限盛元
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author 刘小明 新增日期：2013-1-16
* @author 你的姓名 修改日期：2013-1-16
* @since gx-cms
 */
@Service("userIpsService")
public class UserIpsService {
	@Autowired(required = true)
	private UserIpsDAO userIpsDAO;
	
	public List findUserIps(String ids){
		Object[] obj = null;
		
		List list =   this.userIpsDAO.getHibernateTemplate().find("select distinct(sui.userId) from SysUserIps sui where sui.userId in("+ids+")");
		
		return list;
	}
}
