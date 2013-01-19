package bjwxsytx.system.poshis.dao;

import java.util.ArrayList;
import java.util.List;

import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.TCellIps;
import bjwxsytx.system.entity.TLbsHistory;
import bjwxsytx.system.poshis.vo.QueryVO;


public class PoshisDAO extends CommonDAO<TLbsHistory>{
public Page list(Page page,QueryVO queryVO){
		
		List params =null;
		//page.setField("  ci ");
		StringBuffer where = new StringBuffer();
		params = new ArrayList();
		if(queryVO!=null){
			if(queryVO.getUserId().intValue()!=1){
				params.add(queryVO.getUserId());
				where.append(" and th.userId = ? and th.userId = su.userId ");
			}else{
				where.append(" and th.userId = su.userId ");
			}
			if(!BlankUtil.isBlank(queryVO.getMdn())){
				params.add('%' +queryVO.getMdn().trim() + '%');
				where.append(" and th.mdn like ? ");
			}
			if(!BlankUtil.isBlank(queryVO.getBeginTime())){
				where.append(" and to_char(th.reqTime,'yyyy-mm-dd') >= ? ");
				params.add(queryVO.getBeginTime().trim());
            }
			if(!BlankUtil.isBlank(queryVO.getEndTime())){
				where.append(" and to_char(th.reqTime,'yyyy-mm-dd') <= ? ");
				params.add(queryVO.getEndTime().trim());
            }
			if(!BlankUtil.isBlank(queryVO.getRespResult())){
				where.append(" and th.respResult = ? ");
				params.add(queryVO.getRespResult());
            }
			if(!BlankUtil.isBlank(queryVO.getRespCode())){
				where.append(" and th.respCode =  ? ");
				params.add(queryVO.getRespCode().trim());
            }
		}
		page.setTableName("    TLbsHistory th ,SysUser su  ");
		
		
		page.setCondition(where.toString());
		
		return this.findResult(page,params);
	}
}
