package bjwxsytx.system.ips.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import bjwxsytx.common.Page;
import bjwxsytx.system.entity.SysUserIps;
import bjwxsytx.system.entity.TCellIps;
import bjwxsytx.system.ips.dao.CellIpsDAO;
import bjwxsytx.system.ips.dao.UserIpsDAO;
import bjwxsytx.system.ips.vo.QueryVO;


/***
 * 
* 功能描述（可以分多行编写）
* <p>版权所有：北京无限盛元
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author 刘小明 新增日期：2013-1-16
* @author 你的姓名 修改日期：2013-1-16
* @since gx-cms
 */
@Service("cellService")
public class CellIpsService {

	@Autowired(required = true)
	private CellIpsDAO cellIpsDAO;
	
	@Autowired(required = true)
	private UserIpsDAO userIpsDAO;
	public Page list(Page page,QueryVO queryVO){
		return this.cellIpsDAO.list(page, queryVO);
	}
	
	public void save(TCellIps cellIps,SysUserIps userIps){
		if(cellIps.getId()==null){
			this.cellIpsDAO.save(cellIps);
			userIps.setIpsId(cellIps.getId());
			this.userIpsDAO.save(userIps);
		}else{
			this.cellIpsDAO.update(cellIps);
			userIps.setIpsId(cellIps.getId());
			this.userIpsDAO.update(userIps);
		}
	}
	
	public void delete(String cellIpsIds,String userIpsIds){
		String[] array = cellIpsIds.split(",");
		for(int i = 0 ; i < array.length ; i++){
			this.cellIpsDAO.delete(TCellIps.class, Long.valueOf(array[i]));
		}
		array =  userIpsIds.split(",");
		for(int i = 0 ; i < array.length ; i++){
			this.userIpsDAO.delete(SysUserIps.class, Long.valueOf(array[i]));
		}
	}
	
	
	public TCellIps findCellIps(Long id){
		return this.cellIpsDAO.get(TCellIps.class, id);
	}
	
	public SysUserIps findSysUserIps(Long id){
		return this.userIpsDAO.get(SysUserIps.class, id);
	}
}
