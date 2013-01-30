package bjwxsytx.system.ips.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.common.AuthenticationUtil;
import bjwxsytx.common.Page;
import bjwxsytx.common.Result;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.entity.SysUserIps;
import bjwxsytx.system.entity.SysUserWhite;
import bjwxsytx.system.entity.TCellIps;
import bjwxsytx.system.entity.TCellWhite;
import bjwxsytx.system.ips.service.CellIpsService;
import bjwxsytx.system.ips.vo.QueryVO;
import bjwxsytx.system.ips.vo.ResultVO;
import bjwxsytx.system.menu.action.MenuAction;
import bjwxsytx.system.white.ro.WhiteRuseltObject;

/***
 * 
* 功能描述:IP账号管理ACTION（可以分多行编写）
* <p>版权所有：北京无限盛元
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author 刘小明 新增日期：2013-1-16
* @author 你的姓名 修改日期：2013-1-16
* @since gx-cms
 */
public class CellIpsAction extends BaseAction {

	
	private static Logger _log = Logger.getLogger(CellIpsAction.class);
	private static final long serialVersionUID = -2256570447416819897L;
	private QueryVO queryVO;
	@Autowired(required = true)
	private CellIpsService cellIpsService;
	private long total;
	private List<Object> rows; 
	private TCellIps cellIps;
	private SysUserIps userIps;
	private Result result;
	private String cellIpsIds;

	public String delete(){
		this.result = new Result();
		try{
			this.cellIpsService.delete(cellIpsIds, userIpsIds);
			this.result.setFlag(Result.FLAG_SUCCESS);
		}catch(Exception ex){
			this.result.setFlag(Result.FLAG_ERROE);
			this.result.setMsg(ex.getLocalizedMessage());
			ex.printStackTrace();
			_log.error(ex);
		}
		return SUCCESS;
	}
	
	public String findCellIps(){
		this.cellIps = this.cellIpsService.findCellIps(queryVO.getId());
		//this.userIps = this.cellIpsService.findSysUserIps(Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap())));
		return SUCCESS;
	}
	public String save(){
		this.result = new Result();
		try{

			this.cellIpsService.save(cellIps, userIps);
			
			this.result.setFlag(Result.FLAG_SUCCESS);
		}catch(Exception ex){
			ex.printStackTrace();
			this.result.setFlag(Result.FLAG_FAILURE);
			this.result.setMsg(ex.getLocalizedMessage());
		}
		return SUCCESS;
	}
	public String update(){
		this.result = new Result();
		try{

			this.cellIpsService.save(cellIps, userIps);
			
			this.result.setFlag(Result.FLAG_SUCCESS);
		}catch(Exception ex){
			ex.printStackTrace();
			this.result.setFlag(Result.FLAG_FAILURE);
			this.result.setMsg(ex.getLocalizedMessage());
		}
		return SUCCESS;
	}
	public void setUserIps(SysUserIps userIps) {
		this.userIps = userIps;
	}

	public TCellIps getCellIps() {
		return cellIps;
	}

	public void setCellIps(TCellIps cellIps) {
		this.cellIps = cellIps;
	}

	public String ipsListPage(){
		return SUCCESS;
	}
	/***
	 * 
	* 方法用途和描述: IP账号管理查询列表（可以分多行编写）
	* @return
	* @author 刘小明 新增日期：2013-1-16
	* @author 你的姓名 修改日期：2013-1-16
	* @since gx-cms
	 */
	public String list(){
		try{
			Page page = new Page(this.getHttpRequest());
			if(queryVO==null){
				queryVO = new QueryVO();
			}
			queryVO.setUserId(Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap())));
			this.cellIpsService.list(page, queryVO);
			this.total = page.getTotalCount();
			List tempList = page.getList();
			List<Object>  rowsList = new ArrayList<Object>();
			if(tempList!=null&&tempList.size()>0){
				int size = tempList.size();
				Object[] oo;
				TCellIps cellIps;
				SysUserIps sysUserIps;
				SysUser sysUser;
				ResultVO rs;
				for(int k = 0;k<size;k++){
					oo = (Object[])tempList.get(k);
					cellIps = (TCellIps)oo[0];
					sysUserIps = (SysUserIps)oo[1];
					sysUser = (SysUser)oo[2];
					rs = new ResultVO();
					rs.setId(cellIps.getId());
					rs.setClientId(cellIps.getClientid());
					rs.setIp(cellIps.getIp());
					rs.setReId(sysUserIps.getId());
					rs.setMemo(cellIps.getMemo());
					rs.setLoginName(sysUser.getLoginName());
					rs.setUserName(sysUser.getUserName());
					rs.setUserId(sysUser.getUserId());
					rowsList.add(rs);
				}
				_log.info("oneListSizeIs:"+rowsList.size());
			}else{
				_log.info("no result record ");
			}
			this.rows = rowsList;	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	public QueryVO getQueryVO() {
		return queryVO;
	}

	public void setQueryVO(QueryVO queryVO) {
		this.queryVO = queryVO;
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
	public String getCellIpsIds() {
		return cellIpsIds;
	}
	public void setCellIpsIds(String cellIpsIds) {
		this.cellIpsIds = cellIpsIds;
	}
	public String getUserIpsIds() {
		return userIpsIds;
	}
	public void setUserIpsIds(String userIpsIds) {
		this.userIpsIds = userIpsIds;
	}

	private String userIpsIds;
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public SysUserIps getUserIps() {
		return userIps;
	}
}
