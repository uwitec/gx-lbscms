package bjwxsytx.system.white.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.entity.SysUserRole;
import bjwxsytx.system.role.dao.RoleUserDAO;
import bjwxsytx.system.role.service.RoleUserService;
import bjwxsytx.system.white.dao.WhiteDAO;
import bjwxsytx.system.white.vo.QueryVO;


@Service("whiteService")
public class WhiteService {
	@Autowired(required = true)
	private WhiteDAO whiteDAO;
	@Autowired(required = true)
	private RoleUserDAO roleUserDAO;

	
	public Page findWhiteMdn(Page page,QueryVO queryVO){
		return this.whiteDAO.findWhiteMdn(page,queryVO);
	}

	

}
