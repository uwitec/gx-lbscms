package bjwxsytx.system.white.dao;

import java.util.ArrayList;
import java.util.List;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.TCellWhite;
import bjwxsytx.system.white.vo.QueryVO;


public class WhiteDAO extends CommonDAO<TCellWhite>{


	
	public Page findWhiteMdn(Page page,QueryVO queryVO){
		List<String> params =null;
		//page.setField(" u ");
		page.setTableName("TCellWhite tw ,SysUserWhite suw,SysUser su");
		System.out.println(queryVO);
		StringBuffer where = new StringBuffer();
		if(!BlankUtil.isBlank(queryVO)){
			
			params = new ArrayList<String>();
			if(!BlankUtil.isBlank(queryVO.getMdn())){
				params.add('%' + queryVO.getMdn().trim() + '%');
				where.append(" and tw.msisdn like ? ");
			}
//			if(!BlankUtil.isBlank(queryVO.getLoginName())){
//				params.add('%' + queryVO.getLoginName().trim() + '%');
//				where.append(" and u.loginName like ? ");
//			}
			//page.setCondition(where.toString());
		}
		where.append(" and  tw.id = suw.white and su.userId = suw.userId ");
		page.setCondition(where.toString());
		//this.findResult(page, params);
		return this.findResult(page,params);
	}

}
