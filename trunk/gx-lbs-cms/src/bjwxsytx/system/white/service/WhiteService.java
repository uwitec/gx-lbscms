package bjwxsytx.system.white.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.base.exception.OperationException;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.entity.SysUserRole;
import bjwxsytx.system.entity.SysUserWhite;
import bjwxsytx.system.entity.TCellWhite;
import bjwxsytx.system.role.dao.RoleUserDAO;
import bjwxsytx.system.role.service.RoleUserService;
import bjwxsytx.system.user.dao.UserDAO;
import bjwxsytx.system.white.dao.UserWhiteDAO;
import bjwxsytx.system.white.dao.WhiteDAO;
import bjwxsytx.system.white.vo.QueryVO;


@Service("whiteService")
public class WhiteService {
	@Autowired(required = true)
	private WhiteDAO whiteDAO;
	@Autowired(required = true)
	private RoleUserDAO roleUserDAO;
	@Autowired(required = true)
	private UserDAO userDAO;
	
	@Autowired(required = true)
	private UserWhiteDAO userWhiteDAO;
	
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.service
	* file_name:    WhiteService.java
	* description: 白名单列表查询
	* 2013-1-10下午5:34:13
	* Author: chenhui
	 * @param page
	 * @param queryVO
	 * @return
	 */
	public Page findWhiteMdn(Page page,QueryVO queryVO){
		return this.whiteDAO.findWhiteMdn(page,queryVO);
	}

	/**
	 * 
	* package_name: bjwxsytx.system.white.service
	* file_name:    WhiteService.java
	* description: 添加白名单时，返回EC/SI列表
	* 2013-1-10下午4:28:01
	* Author: chenhui
	 * @return
	 */
	public List<SysUser> findAllUserWhenAddWhiteMdn(){
		Object[]  o = null;
		return this.userDAO.searchAll("from SysUser su", o);
		
	}
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.service
	* file_name:    WhiteService.java
	* description:  根据传入号码判断白名单是否已经存在
	* 2013-1-10下午5:36:26
	* Author: chenhui
	 * @param queryVO
	 * @return
	 */
	public boolean isWhiteMdnExist(QueryVO queryVO){
		List<TCellWhite> list = this.whiteDAO.searchAll(" from TCellWhite tw where tw.msisdn = ?", new Object[]{queryVO.getMdn()});
		if(list.size() ==0 ){
			return false;
		}else{
			return true;
		}
	}
	
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.service
	* file_name:    WhiteService.java
	* description: 保存单个白名单号码
	* 2013-1-14上午1:02:29
	* Author: chenhui
	 * @param tCellWhite
	 * @param sysUserWhite
	 * @throws Exception
	 */
	public void saveWhiteMdn(TCellWhite tCellWhite,SysUserWhite sysUserWhite) throws Exception{
	
			this.whiteDAO.save(tCellWhite);
			sysUserWhite.setWhite(tCellWhite.getId());
			this.userWhiteDAO.save(sysUserWhite);

	}
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.service
	* file_name:    WhiteService.java
	* description: 删除白名单号码以及对于关联数据
	* 2013-1-14上午1:02:19
	* Author: chenhui
	 * @param ids
	 * @throws Exception
	 */
	public void deleteWhiteMdn(String ids) throws Exception{
		//this.userDAO.deleteAll(list);
		///roleUserDAO.deleteByHql(hql, values)
		Object[] obj = null;
		this.whiteDAO.deleteByHql("delete  TCellWhite tw where tw.id in ("+ids+")", obj);
		this.userWhiteDAO.deleteByHql("delete SysUserWhite suw where suw.white in ("+ids+")", obj);
	}

}
