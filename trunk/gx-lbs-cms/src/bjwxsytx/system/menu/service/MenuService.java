package bjwxsytx.system.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.system.entity.SysMenu;
import bjwxsytx.system.menu.dao.MenuDAO;

@Service("menuService")
public class MenuService {

	@Autowired(required = true)
	private MenuDAO menuDAO;
	
	
	public List<SysMenu> findMenuByUserId(Long userId){
		return this.menuDAO.findMenuByUserId(userId);
	}
}
