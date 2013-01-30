package bjwxsytx.system.menu.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.base.exception.SystemException;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysMenu;
import bjwxsytx.system.menu.dao.MenuDAO;
import bjwxsytx.system.menu.vo.QueryVO;
import bjwxsytx.system.role.dao.RoleUserDAO;
/***
 * 
* 功能描述:（可以分多行编写）
* <p>版权所有：中太数据
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author 刘小明 新增日期：2013-1-16
* @author 你的姓名 修改日期：2013-1-16
* @since gx-cms
 */
@Service("menuService")
public class MenuService {

	@Autowired(required = true)
	private MenuDAO menuDAO;
	@Autowired(required = true)
	private RoleUserDAO roleUserDAO;
	
	public void updateMenu(QueryVO vo){
		SysMenu sm = this.menuDAO.get(SysMenu.class, vo.getMenuId());
		sm.setMenuName(vo.getMenuName());
		sm.setUrl(vo.getUrl());
		this.menuDAO.update(sm);
	}
	
	public void deleteMenu(SysMenu entity) throws Exception{
		this.menuDAO.delete(SysMenu.class, entity.getMenuId());
	}
	
	
	public List<SysMenu> findMenuByUserId(Long userId)throws Exception{
		return this.menuDAO.findMenuByUserId(userId);
	}
	
	public List<SysMenu> findMenuOneByUserId(Long userId)throws Exception{
		return this.menuDAO.findMenuOneByUserId(userId);
	}
	
	public List<SysMenu> findMenuTwoByUserId(Long userId)throws Exception{
		return this.menuDAO.findMenuTwoByUserId(userId);
	}
	
	
	public List<SysMenu> findMenuOne()throws Exception{
		return this.menuDAO.findMenuOne();
	}
	
	public List<SysMenu> findMenuTwo()throws Exception{
		return this.menuDAO.findMenuTwo();
	}
	public Page findMenu(Page page,QueryVO queryVO){
		return this.menuDAO.findMenu(page,queryVO);
	}
	
	public void saveMenu(SysMenu entity){
		this.menuDAO.save(entity);
	}
	public List<SysMenu>  findAllMenu(Long userId){
		
		List<SysMenu> list = this.menuDAO.searchAll("select sm  from SysMenu sm ,SysUserRole sur,SysRoleMenu srm where sur.userId = ? and sur.roleId = srm.roleId and srm.menuId = sm.menuId and sm.gread = 3 ",new Object[]{userId});
		
		return list;
		
	}
	
	public List<Hashtable<String,Object>> findAllMenu(){
		Object[] obj = null;
		List<SysMenu> list = this.menuDAO.searchAll(" from SysMenu sm ",obj);
		List<Hashtable<String,Object>> listOne = new ArrayList<Hashtable<String,Object>>();
		try{
		for(int i = 0 ; i < list.size() ; i++){
			SysMenu sm = list.get(i);
			if(sm.getGread().intValue()==1){
				List<Hashtable<String,Object>> list1 = new ArrayList<Hashtable<String,Object>>();
				Hashtable<String,Object> hOne = new Hashtable<String, Object>();
				hOne.put("id", sm.getMenuId());
				hOne.put("text", sm.getMenuName()==null?"":sm.getMenuName());
				hOne.put("state","open");
				for(int j =0 ; j<list.size() ; j++){
					SysMenu menuTwo = list.get(j);
					if(menuTwo.getGread().intValue()==2){
						List<Hashtable<String,Object>> list2 = new ArrayList<Hashtable<String,Object>>();
						if(sm.getMenuId().toString().equals(menuTwo.getParentId().toString())){
							Hashtable<String,Object> h1 = new Hashtable<String, Object>();
							h1.put("id", menuTwo.getMenuId());
							h1.put("text", menuTwo.getMenuName()==null?"":menuTwo.getMenuName());
							h1.put("state","open");
							for(int k = 0 ; k  < list.size() ; k++){
								SysMenu menuThree = list.get(k);
								if(menuThree.getGread().intValue()==3){
									if(menuThree.getParentId().toString().equals(menuTwo.getMenuId().toString())){
										Hashtable<String,Object> h2 = new Hashtable<String, Object>();
										h2.put("id", menuThree.getMenuId());
										h2.put("text", menuThree.getMenuName()==null?"":menuThree.getMenuName());
										
										list2.add(h2);
									}
								}
							}
							h1.put("children", list2);
							list1.add(h1);
						}
					}
				}				
				hOne.put("children", list1);
				listOne.add(hOne);
			}
		}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return listOne;
		
	}
}
