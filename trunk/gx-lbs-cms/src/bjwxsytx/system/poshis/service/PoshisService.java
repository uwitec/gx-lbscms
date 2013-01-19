package bjwxsytx.system.poshis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import bjwxsytx.common.Page;
import bjwxsytx.system.poshis.dao.PoshisDAO;
import bjwxsytx.system.poshis.vo.QueryVO;


@Service("poshisService")
public class PoshisService {
	@Autowired(required = true)
	private PoshisDAO poshisDAO;
	
	public Page list(Page page,QueryVO queryVO){
		return this.poshisDAO.list(page,queryVO);
	}
}
