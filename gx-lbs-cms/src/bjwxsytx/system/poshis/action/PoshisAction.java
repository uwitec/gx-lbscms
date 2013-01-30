package bjwxsytx.system.poshis.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.base.exception.SystemException;
import bjwxsytx.common.AuthenticationUtil;
import bjwxsytx.common.Page;
import bjwxsytx.common.Result;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.entity.SysUserWhite;
import bjwxsytx.system.entity.TCellWhite;
import bjwxsytx.system.entity.TLbsHistory;
import bjwxsytx.system.ips.action.CellIpsAction;
import bjwxsytx.system.poshis.service.PoshisService;
import bjwxsytx.system.poshis.vo.QueryVO;
import bjwxsytx.system.poshis.vo.ResultVO;
import bjwxsytx.system.white.ro.WhiteRuseltObject;
import bjwxsytx.system.white.service.WhiteService;

public class PoshisAction extends BaseAction {

	private static Logger _log = Logger.getLogger(CellIpsAction.class);
	private static final long serialVersionUID = 2249681622715190609L;
	private Result result;
	private long total;
	private List<Object> rows; 
	private QueryVO queryVO;
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
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
	public QueryVO getQueryVO() {
		return queryVO;
	}
	public void setQueryVO(QueryVO queryVO) {
		this.queryVO = queryVO;
	}
	@Autowired(required = true)
	private PoshisService poshisService;
	
	public String list(){
		try{
			
			Page page = new Page(this.getHttpRequest());
			if(queryVO==null){
				queryVO = new QueryVO();
			}
			if(queryVO.getResult()!=null){
				if(queryVO.getResult().equals("-1")){
					queryVO.setRespResult(null);
				}else if(queryVO.getResult().equals("false")){
					queryVO.setRespResult(false);
				}else{
					queryVO.setRespResult(true);
				}
			}
			queryVO.setUserId(Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap())));
			//System.out.println(queryVO.getLoginName());
			this.poshisService.list(page, queryVO);
			this.total = page.getTotalCount();
//			this.rows = page.getList();
//			huhuAdd
			
			List tempList = page.getList();
			List<Object>  rowsList = new ArrayList<Object>();
			
			if(tempList!=null&&tempList.size()>0){
				int size = tempList.size();
				Object[] oo;
				TLbsHistory th;
				SysUser su;
				ResultVO rs;
				for(int k = 0;k<size;k++){
					oo = (Object[])tempList.get(k);
					th = (TLbsHistory)oo[0];
					su = (SysUser)oo[1];
					rs = new ResultVO();
					rs.setId(th.getId());
					rs.setUserId(th.getUserId());
					rs.setUserName(su.getUserName());
					rs.setLoginName(su.getLoginName());
					rs.setMdn(th.getMdn());
					rs.setReqTime(th.getReqTime());
					rs.setRespResult(!th.getRespResult());
					rs.setRespCode(th.getRespCode());
					rs.setRespMemo(th.getRespMemo());
					rs.setCellCi(th.getCellCi());
					rs.setCellLac(th.getCellLac());
					rs.setJindu(th.getJindu());
					rs.setWeidu(th.getWeidu());
					rowsList.add(rs);
					
				}
				_log.info("oneListSizeIs:"+rowsList.size());
			}else{
				_log.info("no result record ");
			}
			
			this.rows = rowsList;
			//int a = 1/0;
			this.result = new Result();
			this.result.setFlag(Result.FLAG_SUCCESS);
		}catch(Exception ex){
			
			_log.info(ex);
			ex.printStackTrace();
			this.result = new Result();
			this.result.setFlag(Result.FLAG_ERROE);
			this.result.setMsg(ex.getLocalizedMessage());
		}

		return SUCCESS;
	}
	
	public String poshisListPage(){
		return SUCCESS;
	}
}
