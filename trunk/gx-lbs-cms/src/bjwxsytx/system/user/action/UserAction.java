package bjwxsytx.system.user.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.common.Page;
import bjwxsytx.common.Result;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.entity.SysUserRole;
import bjwxsytx.system.ips.service.UserIpsService;
import bjwxsytx.system.role.service.RoleService;
import bjwxsytx.system.role.service.RoleUserService;
import bjwxsytx.system.user.service.UserService;
import bjwxsytx.system.user.vo.QueryVO;
import bjwxsytx.system.white.service.WhiteService;
/***
 * 
* 功能描述:XXXXXXXXX（可以分多行编写）
* <p>版权所有：中太数据
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author 刘小明 新增日期：2013-1-16
* @author 你的姓名 修改日期：2013-1-16
* @since gx-cms
 */
public class UserAction extends BaseAction {

	private static final long serialVersionUID = -487695416956573238L;
	@Autowired(required = true)
	private UserService userService;
	@Autowired(required = true)
	private RoleService roleService;
	@Autowired(required = true)
	private RoleUserService roleUserService;
	@Autowired(required = true)
	private WhiteService whiteService;
	
	@Autowired(required = true)
	private UserIpsService userIpsService;
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
	
	public String list(){
		try{
			Page page = new Page(this.getHttpRequest());
			//System.out.println(queryVO.getLoginName());
			this.userService.findUser(page, queryVO);
			this.total = page.getTotalCount();
			this.rows = page.getList();			
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return SUCCESS;
	}
	
	public String saveUser() throws Exception{
		this.result = new Result();
		this.sysUser.setCreateTime(new Date());
		this.userService.saveUser(sysUser,sysUserRole);
		this.result.setFlag(Result.FLAG_SUCCESS);
		return SUCCESS;
	}
	
	public String deleteUser(){
		try {
			this.result = new Result();
			//this.userService.deleteUser(ids);
			List userWhiteList =  whiteService.findUserWhite(ids);
			List userIpsList = userIpsService.findUserIps(ids);
			
			for(int i = 0  ; i  < userWhiteList.size() ; i++){
				Long id = (Long)userWhiteList.get(i);
				if(!userIpsList.contains(id)){  
					userIpsList.add(id);    
		        }  
			}
//			for(int i = 0  ; i  < userIpsList.size() ; i++){
//				Long id = (Long)userIpsList.get(i);
//				System.out.println(id);
//			}
			Map map = new HashMap();
			for(int i = 0  ; i  < userWhiteList.size() ; i++){
				Long id = (Long)userWhiteList.get(i);
				map.put(id.toString(), id.toString());
			}
			String tempIds = "";
			String msg = "";
			String arr[] =  ids.split(",");
			for(int i = 0 ; i  <arr.length;i++){
				String id = arr[i];
				if(map.get(id)==null){
					tempIds = tempIds + id+",";
				}else{
					msg = msg + id+",";
				}
			}
			
			if(tempIds.length()>0){
				this.userService.deleteUser(tempIds.substring(0,tempIds.length()-1));
			}
			if(msg.length()>0){
				result.setMsg("<br/>以下用户与白名单或IP账号数据关联，不能删除!请先删除白名单或IP账号数据再删除该用户!<br/>用户ID:"+msg);
			}else{
				result.setMsg("");
			}
			result.setFlag(Result.FLAG_SUCCESS);
		} catch (Exception e) {
			result.setFlag(Result.FLAG_FAILURE);
			this.result.setMsg(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String findUserById(){
		this.sysUser = this.userService.findUserById(queryVO);
		this.sysUserRole = this.roleUserService.findRoleUser(sysUser.getUserId());
		return SUCCESS;
	}
}
