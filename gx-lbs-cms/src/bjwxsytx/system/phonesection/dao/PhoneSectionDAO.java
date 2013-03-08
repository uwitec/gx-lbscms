package bjwxsytx.system.phonesection.dao;

import java.util.ArrayList;
import java.util.List;

import bjwxsytx.common.BlankUtil;
import bjwxsytx.common.CommonDAO;
import bjwxsytx.common.Page;
import bjwxsytx.system.entity.PhoneSection;
import bjwxsytx.system.phonesection.vo.QueryVO;

public class PhoneSectionDAO extends CommonDAO<PhoneSection>{
	
public Page list(Page page,QueryVO queryVO){
		
		List params =null;
		StringBuffer where = new StringBuffer();
		params = new ArrayList();
		if(queryVO!=null){

			if(!BlankUtil.isBlank(queryVO.getSectionValue())){
				params.add('%' +queryVO.getSectionValue().trim() + '%');
				where.append(" and ps.sectionValue like ? ");
			}
		}
		page.setTableName("    PhoneSection ps  ");
		page.setCondition(where.toString());
		page.setDir(" desc ");
		page.setSort(" sectionId ");
		return this.findResult(page,params);
	}
}
