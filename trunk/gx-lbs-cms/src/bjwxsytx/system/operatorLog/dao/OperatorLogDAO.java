package bjwxsytx.system.operatorLog.dao;

import java.util.ArrayList;
import java.util.List;

import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.OperatorLog;
import bjwxsytx.system.operatorLog.vo.QueryVO;

public class OperatorLogDAO extends CommonDAO<OperatorLog>{
public Page list(Page page,QueryVO queryVO){
		
		List params =null;
		StringBuffer where = new StringBuffer();
		params = new ArrayList();
		if(queryVO!=null){

			if(!BlankUtil.isBlank(queryVO.getLoginName())){
				params.add('%' +queryVO.getLoginName().trim() + '%');
				where.append(" and ol.loginName like ? ");
			}
			
			if(!BlankUtil.isBlank(queryVO.getBeginDate())){
				where.append(" and to_char(ol.opertime,'yyyy-mm-dd') >= ? ");
				params.add(queryVO.getBeginDate().trim());
            }
			if(!BlankUtil.isBlank(queryVO.getEndDate())){
				where.append(" and to_char(ol.opertime,'yyyy-mm-dd') <= ? ");
				params.add(queryVO.getEndDate().trim());
            }

			if(!BlankUtil.isBlank(queryVO.getOperType())&&!queryVO.getOperType().equals("-1")){
				params.add('%' +queryVO.getOperType()+ '%');
				where.append(" and ol.opertype like  ? ");
			}
			
		}
		page.setTableName("    OperatorLog ol  ");
		page.setCondition(where.toString());
		page.setDir(" desc ");
		page.setSort(" logid ");
		return this.findResult(page,params);
	}
}
