package bjwxsytx.system.phonesection.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.base.constants.OperConstants;
import bjwxsytx.common.AuthenticationUtil;
import bjwxsytx.common.Page;
import bjwxsytx.common.Result;
import bjwxsytx.common.StringUtil;
import bjwxsytx.system.entity.OperatorLog;
import bjwxsytx.system.entity.PhoneSection;
import bjwxsytx.system.entity.TCellWhite;
import bjwxsytx.system.operatorLog.service.OperatorLogService;
import bjwxsytx.system.phonesection.service.PhoneSectionService;
import bjwxsytx.system.phonesection.vo.QueryVO;

public class PhoneSectionAction extends BaseAction {

	private static final long serialVersionUID = 5928984563854340556L;
	@Autowired(required = true)
	private PhoneSectionService phoneSectionService;
	private long total;
	private List<Object> rows;
	private PhoneSection phoneSection;
	private Result result;
	private String ids;
	private File file;
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public PhoneSection getPhoneSection() {
		return phoneSection;
	}
	public void setPhoneSection(PhoneSection phoneSection) {
		this.phoneSection = phoneSection;
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
	private QueryVO queryVO;
	public QueryVO getQueryVO() {
		return queryVO;
	}
	public void setQueryVO(QueryVO queryVO) {
		this.queryVO = queryVO;
	}
	public String list(){
		Page page = new Page(this.getHttpRequest());
		if(queryVO==null){
			queryVO = new QueryVO();
		}
		this.phoneSectionService.list(page, queryVO);
		rows = page.getList();
		this.total = page.getTotalCount();
		return SUCCESS;
	}
	public String save(){
		try{
			this.result = new Result();
			Long saveId = phoneSection.getSectionId();
			phoneSectionService.save(phoneSection);
			this.result.setFlag(Result.FLAG_SUCCESS);
			Long userId = Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap()));
			String loginName = AuthenticationUtil.getCurrentUserAccount(this.getSessionMap());
			OperatorLog oper = new OperatorLog();
			oper.setAdminid(userId);
			if(saveId!=null){
				oper.setDescription(OperConstants.DESC_SYS_PHONESECTION_EDIT+";id="+phoneSection.getSectionId()+";sectipnValue="+phoneSection.getSectionValue());
			}else{
				oper.setDescription(OperConstants.DESC_SYS_PHONESECTION_ADD+";id="+phoneSection.getSectionId()+";sectionValue="+phoneSection.getSectionValue());
			}
			oper.setOpertype(OperConstants.TYPE_SYS_PHONESECTION);
			oper.setOpertime(new Date());
			oper.setLoginName(loginName);
			operatorLogService.saveOperatorLog(oper);	
		}catch(Exception ex){
			ex.printStackTrace();
			this.result.setMsg(ex.getLocalizedMessage());
		}
		return SUCCESS;
	}
	public String phonesectionListPage(){
		return SUCCESS;
	}
	
	public String findPhoneSectionById(){
		this.result = new Result();
		try{
			phoneSection = phoneSectionService.findPhoneSectionById(phoneSection.getSectionId());
		this.result.setFlag(Result.FLAG_SUCCESS);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String delete(){
		try{
			this.result = new Result();
			this.phoneSectionService.delete(ids);
			this.result.setFlag(Result.FLAG_SUCCESS);
			Long userId = Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap()));
			String loginName = AuthenticationUtil.getCurrentUserAccount(this.getSessionMap());
			OperatorLog oper = new OperatorLog();
			oper.setAdminid(userId);
			oper.setDescription(OperConstants.DESC_SYS_PHONESECTION_DEL+";ids="+ids);
			oper.setOpertype(OperConstants.TYPE_SYS_PHONESECTION);
			oper.setOpertime(new Date());
			oper.setLoginName(loginName);
			operatorLogService.saveOperatorLog(oper);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	} 
	@Autowired(required = true)
	private OperatorLogService operatorLogService;
	public String upload(){
		this.result = new Result();
		BufferedReader reader = null;  
		try{
			reader = new BufferedReader(new FileReader(file));  
			String tempString = null;  
			int line = 1;  
			String msg = "";
			while ((tempString = reader.readLine()) != null){  
				String array[] = tempString.split(",");
				//System.out.println(array.length);
				if(array.length==3){
					phoneSection =  new PhoneSection();
					phoneSection.setAreaname(array[1]);
					phoneSection.setCarrier(array[2]);
					phoneSection.setSectionValue(array[0]);
					phoneSectionService.save(phoneSection);
				}else{
					msg = msg + tempString+"<br>";
				}
				line++;  
			}  
			reader.close();  
			if(msg.length()>0){
				result.setMsg("以下数据格试错误!<br>"+msg);
			}else{
				result.setMsg("");
			}
			result.setFlag(Result.FLAG_SUCCESS);
			
			Long userId = Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap()));
			String loginName = AuthenticationUtil.getCurrentUserAccount(this.getSessionMap());
			OperatorLog oper = new OperatorLog();
			oper.setAdminid(userId);
			oper.setDescription(OperConstants.DESC_SYS_PHONESECTION_UPLOAD);
			oper.setOpertype(OperConstants.TYPE_SYS_PHONESECTION);
			oper.setOpertime(new Date());	
			oper.setLoginName(loginName);
			operatorLogService.saveOperatorLog(oper);		
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}  
			result.setMsg(ex.getLocalizedMessage());
			result.setFlag(Result.FLAG_FAILURE);
		}
		
		return SUCCESS;
	}
}
