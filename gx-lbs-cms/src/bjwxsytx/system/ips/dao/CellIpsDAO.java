package bjwxsytx.system.ips.dao;

import java.util.ArrayList;
import java.util.List;

import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.TCellIps;
import bjwxsytx.system.ips.vo.QueryVO;


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
public class CellIpsDAO extends CommonDAO<TCellIps>{
	public Page list(Page page,QueryVO queryVO){
		
		List params =null;
		//page.setField("  ci ");
		StringBuffer where = new StringBuffer();
		params = new ArrayList();
		if(queryVO!=null){
			if(queryVO.getUserId().intValue()!=1){
				params.add(queryVO.getUserId());
				where.append(" and sui.userId = ? and sui.userId = su.userId ");
			}else{
				where.append(" and sui.userId = su.userId ");
			}
			if(!BlankUtil.isBlank(queryVO.getIp())){
				params.add('%' +queryVO.getIp().trim() + '%');
				where.append(" and ci.ip like ? ");
			}
			
			if(!BlankUtil.isBlank(queryVO.getLoginName())){
				params.add('%' +queryVO.getLoginName().trim()+ '%');
				where.append(" and su.loginName like  ? ");
			}
			
			if(!BlankUtil.isBlank(queryVO.getUserName())){
				params.add('%' +queryVO.getUserName()+ '%');
				where.append(" and su.userName like  ? ");
			}
			if(!BlankUtil.isBlank(queryVO.getClientId())){
				params.add('%' +queryVO.getClientId()+ '%');
				where.append(" and ci.clientid like  ? ");
			}
			
		}
		page.setTableName("    TCellIps ci ,SysUserIps sui ,SysUser su  ");
		where.append(" and ci.id = sui.ipsId ");
		
		
		page.setCondition(where.toString());
		
		return this.findResult(page,params);
	}
}
