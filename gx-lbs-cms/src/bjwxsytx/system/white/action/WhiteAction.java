package bjwxsytx.system.white.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.common.Page;
import bjwxsytx.common.Result;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.entity.SysUserRole;
import bjwxsytx.system.entity.SysUserWhite;
import bjwxsytx.system.entity.TCellWhite;
import bjwxsytx.system.role.service.RoleService;
import bjwxsytx.system.role.service.RoleUserService;
import bjwxsytx.system.user.service.UserService;
import bjwxsytx.system.white.vo.QueryVO;
import bjwxsytx.system.white.ro.*;
import bjwxsytx.system.white.service.WhiteService;


public class WhiteAction extends BaseAction {
	
	private static Logger log = Logger.getLogger(WhiteAction.class);

	private static final long serialVersionUID = -487695416956573238L;
	@Autowired(required = true)
	private WhiteService whiteService;
	@Autowired(required = true)
	private RoleService roleService;
	@Autowired(required = true)
	private RoleUserService roleUserService;
	private Result result;
	private long total;
	private List<Object> rows; 
	private QueryVO queryVO ;
	private SysUser sysUser;
	private SysRole sysRole;
	private SysUserRole sysUserRole;
	private String ids;
	

	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SysUserRole getSysUserRole() {
		return sysUserRole;
	}

	public void setSysUserRole(SysUserRole sysUserRole) {
		this.sysUserRole = sysUserRole;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public QueryVO getQueryVO() {
		return queryVO;
	}

	public void setQueryVO(QueryVO queryVO) {
		this.queryVO = queryVO;
	}

	public Result getResult() {
		return result;
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

	public void setResult(Result result) {
		this.result = result;
	}

	public String userListPage(){
		return SUCCESS;
	}
//	WhiteRuseltObject


	/**
	 * 
	* package_name: bjwxsytx.system.white.action
	* file_name:    WhiteAction.java
	* description: 返回到白名单列表页面
	* 2013-1-9上午11:28:44
	* Author: chenhui
	 * @return
	 */
	public String userWhiteListPage(){
		return SUCCESS;
	}
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.action
	* file_name:    WhiteAction.java
	* description: 查找白名单号码
	* 2013-1-9上午11:29:04
	* Author: chenhui
	 * @return
	 */
	public String list(){
		try{
			
			Page page = new Page(this.getHttpRequest());
			//System.out.println(queryVO.getLoginName());
			this.whiteService.findWhiteMdn(page, queryVO);
			this.total = page.getTotalCount();
//			this.rows = page.getList();
//			huhuAdd
			List tempList = page.getList();
			List<Object>  rowsList = new ArrayList<Object>();
			
			if(tempList!=null&&tempList.size()>0){
				int size = tempList.size();
				Object[] oo;
				TCellWhite tCellWhite;
				SysUserWhite sysUserWhite;
				SysUser sysUser;
				WhiteRuseltObject rs;
				for(int k = 0;k<size;k++){
					oo = (Object[])tempList.get(k);
					tCellWhite = (TCellWhite)oo[0];
					sysUserWhite = (SysUserWhite)oo[1];
					sysUser = (SysUser)oo[2];
					rs = new WhiteRuseltObject();
					rs.setUserWhiteId(sysUserWhite.getId());
					rs.setWhiteId(tCellWhite.getId());
					rs.setUserId(sysUser.getUserId());
					
					rs.setMsisdn(tCellWhite.getMsisdn());
					rs.setUserName(sysUser.getUserName());
					rs.setMemo(tCellWhite.getMemo());
					rs.setCreateTime(tCellWhite.getCreateTime());
					rowsList.add(rs);
					
				}
				log.info("oneListSizeIs:"+rowsList.size());
			}else{
				log.info("no result record ");
			}
			
			this.rows = rowsList;
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return SUCCESS;
	}
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.action
	* file_name:    WhiteAction.java
	* description:  新增白名单号码
	* 2013-1-8下午6:04:21
	* Author: chenhui
	 * @return
	 * @throws Exception
	 */
	public String saveUserWhite() throws Exception{
		this.result = new Result();
		this.sysUser.setCreateTime(new Date());
		System.out.println(sysUser);


		
		this.result.setFlag(Result.FLAG_SUCCESS);
		return SUCCESS;
	}
	

}
