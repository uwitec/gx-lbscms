package bjwxsytx.system.operatorLog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.common.Page;
import bjwxsytx.system.entity.OperatorLog;
import bjwxsytx.system.operatorLog.vo.QueryVO;
import bjwxsytx.system.operatorLog.dao.OperatorLogDAO;
@Service("operatorLogService")
public class OperatorLogService {
	@Autowired(required = true)
	private OperatorLogDAO operatorLogDAO;
	
	public void saveOperatorLog(OperatorLog entity){
		this.operatorLogDAO.save(entity);
	}
	
	public Page list(Page page,QueryVO queryVO){
		return this.operatorLogDAO.list(page, queryVO);
	}
}
