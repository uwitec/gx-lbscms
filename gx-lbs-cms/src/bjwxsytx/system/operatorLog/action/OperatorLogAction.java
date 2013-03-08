package bjwxsytx.system.operatorLog.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.common.Page;
import bjwxsytx.system.operatorLog.vo.QueryVO;
import bjwxsytx.system.operatorLog.service.OperatorLogService;

public class OperatorLogAction  extends BaseAction {

	private static final long serialVersionUID = 7855921648878899492L;
	@Autowired(required = true)
	private OperatorLogService operatorLogService;
	private long total;
	private List<Object> rows; 
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<Object> getRows() {
		return rows;
	}
	public void setRows(List<Object> rows) {
		this.rows = rows;
	}
	private QueryVO queryVO;
	public QueryVO getQueryVO() {
		return queryVO;
	}
	public void setQueryVO(QueryVO queryVO) {
		this.queryVO = queryVO;
	}
	
	public String logListPage(){
		return SUCCESS;
	}
	public String list(){
		Page page = new Page(this.getHttpRequest());
		if(queryVO==null){
			queryVO = new QueryVO();
		}
		this.operatorLogService.list(page, queryVO);
		rows = page.getList();
		this.total = page.getTotalCount();
		
		return SUCCESS;
	}

}
