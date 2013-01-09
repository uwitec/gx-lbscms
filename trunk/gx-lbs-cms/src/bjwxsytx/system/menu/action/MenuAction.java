package bjwxsytx.system.menu.action;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.common.AuthenticationUtil;
import bjwxsytx.common.Page;
import bjwxsytx.common.Result;
import bjwxsytx.system.entity.SysMenu;
import bjwxsytx.system.menu.service.MenuService;
import bjwxsytx.system.menu.vo.QueryVO;
import bjwxsytx.system.role.service.RoleMenuService;
import bjwxsytx.system.role.service.RoleUserService;
import bjwxsytx.system.user.service.UserService;
/***
 * 
* 功能描述:菜单管理类（可以分多行编写）
* <p>版权所有：北京无限盛元
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author 刘小明 新增日期：2013-1-6
* @author 你的姓名 修改日期：2013-1-6
* @since gx-cms
 */
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = 5277712039351799309L;
	private static Logger _log = Logger.getLogger(MenuAction.class);
	@Autowired(required = true)
	private MenuService menuService;
	@Autowired(required = true)
	private RoleUserService roleUserService;
	@Autowired(required = true)
	private RoleMenuService roleMenuService;
	private long total;
	private List<Object> rows; 
	private QueryVO queryVO;
	private SysMenu sysMenu;
	public SysMenu getSysMenu() {
		return sysMenu;
	}
	public void setSysMenu(SysMenu sysMenu) {
		this.sysMenu = sysMenu;
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
	List<Hashtable<String,Object>> treeList = new ArrayList<Hashtable<String,Object>>();
	public List<Hashtable<String, Object>> getTreeList() {
		return treeList;
	}
	public void setTreeList(List<Hashtable<String, Object>> treeList) {
		this.treeList = treeList;
	}
	private Result result;
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	
	public String menuListPage(){
		return SUCCESS;
	}
	
	public String saveMenu(){
		if(sysMenu.getParentId()==null){
			sysMenu.setParentId(new Long(0));
		}
		if(sysMenu.getGread().intValue()==3){
			sysMenu.setIsMenuTree(new Long(0));
		}else{
			sysMenu.setIsMenuTree(new Long(1));
		}
		this.menuService.saveMenu(sysMenu);
		this.result = new Result();
		this.result.setFlag(Result.FLAG_SUCCESS);
		return SUCCESS;
	}
	
	
	public String updateMenu(){
		this.menuService.updateMenu(queryVO);
		this.result = new Result();
		this.result.setFlag(Result.FLAG_SUCCESS);
		return SUCCESS;
	}
	
	public String deleteMenu(){
		try{
		this.result = new Result();
		if(roleMenuService.findSysUserRoleByMenuId(this.sysMenu.getMenuId())!=null){
			result.setFlag(Result.FLAG_FAILURE);
			result.setMsg("该菜单已关联角色,请先取消关联关系再删除该菜单!");
			return SUCCESS;
		}else{
			this.menuService.deleteMenu(sysMenu);
			this.result.setFlag(Result.FLAG_SUCCESS);
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String list() throws Exception{
		Page page = new Page(this.getHttpRequest());
		//System.out.println(queryVO.getLoginName());
		this.menuService.findMenu(page, queryVO);
		this.total = page.getTotalCount();
		this.rows = page.getList();	
		for(int i = 0 ; i  < rows.size() ; i++){
			SysMenu sm = (SysMenu)rows.get(i);
			if(sm.getGread().intValue()!=3){
				sm.setState("closed");
			}
			sm.set_parentId(sm.getParentId());
			
		}
		
		return SUCCESS;
	}
	
	
	private List<SysMenu> listOne ;
	private List<SysMenu> listTwo;
	public List<SysMenu> getListOne() {
		return listOne;
	}
	public void setListOne(List<SysMenu> listOne) {
		this.listOne = listOne;
	}
	public List<SysMenu> getListTwo() {
		return listTwo;
	}
	public void setListTwo(List<SysMenu> listTwo) {
		this.listTwo = listTwo;
	}
	public String findMenuByUserId(){
		try{
		Object obj = this.getSessionMap().get(AuthenticationUtil.ID_SESSION_KEY);
		Long userId = Long.parseLong(obj.toString());
		listOne = menuService.findMenuOneByUserId(userId);
		listTwo = menuService.findMenuTwoByUserId(userId);
		List<Hashtable<String,Object>> list1 = new ArrayList<Hashtable<String,Object>>();
		for(int i =  0 ; i < listOne.size() ; i ++){
			SysMenu menuOne = listOne.get(i);
			_log.info(menuOne);
			Hashtable<String,Object> h = new Hashtable<String, Object>();
			h.put("id", menuOne.getMenuId());
			h.put("text", menuOne.getMenuName());
			//h.put("attributes", menuOne.getParentId());
			if(menuOne.getParentId().toString().equals("0")){
				h.put("state","closed");
			}
			//"state":"closed",
			//h.put("state","closed");
			for(int j =0 ; j<listTwo.size() ; j++){
				SysMenu menuTwo = listTwo.get(j);
				if(menuOne.getMenuId().toString().equals(menuTwo.getParentId().toString())){
					Hashtable<String,Object> h1 = new Hashtable<String, Object>();
					h1.put("id", menuTwo.getMenuId());
					h1.put("text", menuTwo.getMenuName());
					h1.put("url", menuTwo.getUrl());
					
					h1.put("attributes", menuTwo.getUrl());
					list1.add(h1);
				}
			}
			h.put("children", list1);
			treeList.add(h);
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
}
