package bjwxsytx.system.white.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.TCellWhite;
import bjwxsytx.system.white.vo.QueryVO;


public class WhiteDAO extends CommonDAO<TCellWhite>{

	private static Logger log = Logger.getLogger(WhiteDAO.class);
	
	public Page findWhiteMdn(Page page,QueryVO queryVO){
		List params =null;
		//page.setField(" u ");
		page.setTableName("TCellWhite tw ,SysUserWhite suw,SysUser su");
		log.info("queryVO:"+queryVO);
		StringBuffer where = new StringBuffer();
		if(!BlankUtil.isBlank(queryVO)){
			
			params = new ArrayList();
			if(queryVO.getUserId().intValue()!=1){
				params.add(queryVO.getUserId());
				where.append(" and su.userId = ? and su.userId = suw.userId");
			
			}else{
				where.append(" and su.userId = suw.userId ");
			}
			
			
			if(!BlankUtil.isBlank(queryVO.getMdn())){
				params.add('%' + queryVO.getMdn().trim() + '%');
				where.append(" and tw.msisdn like ? ");
			}
			if(!BlankUtil.isBlank(queryVO.getBeginTime())){
				where.append(" and to_char(tw.createTime,'yyyy-mm-dd') >= ? ");
				params.add(queryVO.getBeginTime().trim());
            }
			if(!BlankUtil.isBlank(queryVO.getEndTime())){
				where.append(" and to_char(tw.createTime,'yyyy-mm-dd') <= ? ");
				params.add(queryVO.getEndTime().trim());
            }
			if(!BlankUtil.isBlank(queryVO.getLoginName())){
				params.add('%' + queryVO.getLoginName().trim() + '%');
				where.append(" and su.loginName like ? ");
			}
			if(!BlankUtil.isBlank(queryVO.getUserName())){
				params.add('%' + queryVO.getUserName().trim() + '%');
				where.append(" and su.userName like ? ");
			}

		}
		where.append(" and  tw.id = suw.white  ");
		page.setCondition(where.toString());

		return this.findResult(page,params);
	}

}
