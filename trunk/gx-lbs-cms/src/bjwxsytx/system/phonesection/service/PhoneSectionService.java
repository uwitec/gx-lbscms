package bjwxsytx.system.phonesection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bjwxsytx.common.Page;
import bjwxsytx.system.entity.PhoneSection;
import bjwxsytx.system.entity.TCellIps;
import bjwxsytx.system.phonesection.dao.PhoneSectionDAO;
import bjwxsytx.system.phonesection.vo.QueryVO;
@Service("phoneSectionService")
public class PhoneSectionService {
	@Autowired(required = true)
	private PhoneSectionDAO phoneSectionDAO;
	
	public Page list(Page page,QueryVO queryVO){
		return this.phoneSectionDAO.list(page, queryVO);
	}
	
	public void save(PhoneSection entity){
		
		if(entity.getSectionId()!=null&&!entity.getSectionId().equals("")){
			phoneSectionDAO.update(entity);
		}else{
			phoneSectionDAO.save(entity);
		}
	}
	public void delete(String ids){
		String[] array = ids.split(",");
		for(int i = 0 ; i < array.length ; i++){
			Long id = Long.valueOf(array[i]);
			this.phoneSectionDAO.delete(PhoneSection.class,id);
		}
	}
	public PhoneSection findPhoneSectionById(Long id){
		return this.phoneSectionDAO.get(PhoneSection.class, id);
	}
}
