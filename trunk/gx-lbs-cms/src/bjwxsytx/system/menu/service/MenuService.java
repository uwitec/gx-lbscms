package bjwxsytx.system.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.base.exception.SystemException;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysMenu;
import bjwxsytx.system.menu.dao.MenuDAO;
import bjwxsytx.system.menu.vo.QueryVO;
import bjwxsytx.system.role.dao.RoleUserDAO;

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
	
	public Page findMenu(Page page,QueryVO queryVO){
		return this.menuDAO.findMenu(page,queryVO);
	}
	
	public void saveMenu(SysMenu entity){
		this.menuDAO.save(entity);
	}
}
