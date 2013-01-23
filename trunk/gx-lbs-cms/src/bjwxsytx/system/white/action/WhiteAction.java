package bjwxsytx.system.white.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.common.AuthenticationUtil;
import bjwxsytx.common.Page;
import bjwxsytx.common.Result;
import bjwxsytx.common.StringUtil;
import bjwxsytx.system.entity.SysRole;
import bjwxsytx.system.entity.SysUser;
import bjwxsytx.system.entity.SysUserRole;
import bjwxsytx.system.entity.SysUserWhite;
import bjwxsytx.system.entity.TCellWhite;
import bjwxsytx.system.role.service.RoleService;
import bjwxsytx.system.role.service.RoleUserService;
import bjwxsytx.system.user.service.UserService;
import bjwxsytx.system.white.vo.QueryVO;
import bjwxsytx.system.white.ro.*;
import bjwxsytx.system.white.service.WhiteService;


public class WhiteAction extends BaseAction {
	
	private static Logger log = Logger.getLogger(WhiteAction.class);

	private static final long serialVersionUID = -487695416956573238L;
	@Autowired(required = true)
	private WhiteService whiteService;
	@Autowired(required = true)
	private RoleService roleService;
	@Autowired(required = true)
	private RoleUserService roleUserService;
	private Result result;
	private long total;
	private List<Object> rows; 
	private QueryVO queryVO ;
	private SysUser sysUser;
	private SysRole sysRole;
	private SysUserRole sysUserRole;
	private String ids;
	private File file;
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	private TCellWhite cellWhite;
	private List<TCellWhite> cellWhiteList;
	public List<TCellWhite> getCellWhiteList() {
		return cellWhiteList;
	}

	public void setCellWhiteList(List<TCellWhite> cellWhiteList) {
		this.cellWhiteList = cellWhiteList;
	}

	private SysUserWhite sysUserWhite;
	
	private List<SysUser> sysUserList;
	
	private boolean whiteMdnExist;
	
	public String uploadWhiteMdn(){
		this.result = new Result();
		BufferedReader reader = null;  
		try{
			//readFileByLines(file);
			reader = new BufferedReader(new FileReader(file));  
			String tempString = null;  
			int line = 1;  
			StringBuffer sb= new StringBuffer("");
			StringBuffer sb1= new StringBuffer("");
			//一次读入一行，直到读入null为文件结束  
			int l = 0;
			cellWhiteList = new ArrayList<TCellWhite>();
			while ((tempString = reader.readLine()) != null){  
			//显示行号  
				this.queryVO = new QueryVO();
				this.queryVO.setMdn(tempString);

				queryVO.setUserId(sysUserWhite.getUserId());
				boolean bl = StringUtil.isMobileNO(tempString);
				if(bl==false){
					l++;
					sb1.append(tempString+"<br>");
					
				}else{
					whiteMdnExist = this.whiteService.isWhiteMdnExist(queryVO);
					if(!whiteMdnExist){
						cellWhite = new TCellWhite();
						cellWhite.setMsisdn(tempString);
						cellWhite.setCreateTime(new Date());
						//cellWhiteList.add(cellWhite);
						this.whiteService.saveWhiteMdn(cellWhite,sysUserWhite);
					}else{
						if(line==1){
							sb.append("<br>以下号码已存在,未添加<br>");
						}
						sb.append(tempString+"<br>");
					} 
				}
				line++;  
			}  
			
			if(l>0){
				sb1.insert(0, "<br>以下号码不正确，未添加<br>");
			}
			//this.whiteService.saveBatchMdn(cellWhiteList, sysUserWhite);
			this.result.setMsg(sb.toString()+sb1.toString());
		
			reader.close();  
			
			result.setFlag(Result.FLAG_SUCCESS);
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
   
	
	public  void readFileByLines(File file){  
		
		BufferedReader reader = null;  
		try {  
			System.out.println("以行为单位读取文件内容，一次读一整行：");  
			reader = new BufferedReader(new FileReader(file));  
			String tempString = null;  
			int line = 1;  
			//一次读入一行，直到读入null为文件结束  

			while ((tempString = reader.readLine()) != null){  
			//显示行号  
				System.out.println("line " + line + ": " + tempString);  
				line++;  
			}  
			reader.close();  
		} catch (IOException e) {  
			e.printStackTrace();  
		} finally {  
			if (reader != null){  
				try {  
					reader.close();  
				} catch (IOException e1) {  
					
				}  
			}  
		}  
		}  
	
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public SysUserRole getSysUserRole() {
		return sysUserRole;
	}

	public void setSysUserRole(SysUserRole sysUserRole) {
		this.sysUserRole = sysUserRole;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	public QueryVO getQueryVO() {
		return queryVO;
	}

	public void setQueryVO(QueryVO queryVO) {
		this.queryVO = queryVO;
	}

	public Result getResult() {
		return result;
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

	public void setResult(Result result) {
		this.result = result;
	}

	public String userListPage(){
		return SUCCESS;
	}
//	WhiteRuseltObject


	/**
	 * 
	* package_name: bjwxsytx.system.white.action
	* file_name:    WhiteAction.java
	* description: 返回到白名单列表页面
	* 2013-1-9上午11:28:44
	* Author: chenhui
	 * @return
	 */
	public String userWhiteListPage(){
		return SUCCESS;
	}
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.action
	* file_name:    WhiteAction.java
	* description: 查找白名单号码
	* 2013-1-9上午11:29:04
	* Author: chenhui
	 * @return
	 */
	public String list(){
		try{
			
			Page page = new Page(this.getHttpRequest());
			//System.out.println(queryVO.getLoginName());
			if(queryVO==null){
				queryVO = new QueryVO();
			}
			queryVO.setUserId(Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap())));
			this.whiteService.findWhiteMdn(page, queryVO);
			this.total = page.getTotalCount();
//			this.rows = page.getList();
//			huhuAdd
			List tempList = page.getList();
			List<Object>  rowsList = new ArrayList<Object>();
			
			if(tempList!=null&&tempList.size()>0){
				int size = tempList.size();
				Object[] oo;
				TCellWhite tCellWhite;
				SysUserWhite sysUserWhite;
				SysUser sysUser;
				WhiteRuseltObject rs;
				for(int k = 0;k<size;k++){
					oo = (Object[])tempList.get(k);
					tCellWhite = (TCellWhite)oo[0];
					sysUserWhite = (SysUserWhite)oo[1];
					sysUser = (SysUser)oo[2];
					rs = new WhiteRuseltObject();
					rs.setUserWhiteId(sysUserWhite.getId());
					rs.setWhiteId(tCellWhite.getId());
					rs.setUserId(sysUser.getUserId());
					
					rs.setMsisdn(tCellWhite.getMsisdn());
					rs.setUserName(sysUser.getUserName());
					rs.setMemo(tCellWhite.getMemo());
					rs.setCreateTime(tCellWhite.getCreateTime());
					rowsList.add(rs);
					
				}
				log.info("oneListSizeIs:"+rowsList.size());
			}else{
				log.info("no result record ");
			}
			
			this.rows = rowsList;
		}catch(Exception ex){
			ex.printStackTrace();
		}

		return SUCCESS;
	}
	
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.action
	* file_name:    WhiteAction.java
	* description:  添加白名单时，返回EC/SI列表
	* 2013-1-10下午4:33:32
	* Author: chenhui
	 * @return
	 * @throws Exception
	 */
	public String findAllUserWhenAddWhiteMdn() throws Exception{
		String userId = AuthenticationUtil.getCurrentUserId(this.getSessionMap());
		System.out.println("@@findAllUserWhenAddWhiteMdn@@userId:"+userId);
		
		this.setSysUserList(whiteService.findAllUserWhenAddWhiteMdn(Long.parseLong(userId)));
		return SUCCESS;
	}
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.action
	* file_name:    WhiteAction.java
	* description:  新增白名单号码
	* 2013-1-8下午6:04:21
	* Author: chenhui
	 * @return
	 * @throws Exception
	 */
	public String saveWhiteMdn() throws Exception{
		
		this.result = new Result();
		this.cellWhite.setCreateTime(new Date());
        
		this.whiteService.saveWhiteMdn(cellWhite,sysUserWhite);
		this.result.setFlag(Result.FLAG_SUCCESS);
		return SUCCESS;

	}
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.action
	* file_name:    WhiteAction.java
	* description: 删除白名单号码以及对于关联数据
	* 2013-1-14上午1:01:46
	* Author: chenhui
	 * @return
	 */
	public String deleteWhiteMdn(){
		try {
			this.result = new Result();
			this.whiteService.deleteWhiteMdn(ids);
			result.setFlag(Result.FLAG_SUCCESS);
		} catch (Exception e) {
			result.setFlag(Result.FLAG_FAILURE);
			this.result.setMsg(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 
	* package_name: bjwxsytx.system.white.action
	* file_name:    WhiteAction.java
	* description: 判断白名单是否已经存在
	* 2013-1-10下午5:38:04
	* Author: chenhui
	 * @return
	 */
	 public String isWhiteMdnExist(){
		System.out.println("queryVO:"+queryVO);
		queryVO.setUserId(Long.valueOf(AuthenticationUtil.getCurrentUserId(this.getSessionMap())));
		whiteMdnExist = this.whiteService.isWhiteMdnExist(queryVO);
		System.out.println("此号码已经存在于白名单:"+whiteMdnExist);
		return SUCCESS;
	}

	public List<SysUser> getSysUserList() {
		return sysUserList;
	}

	public void setSysUserList(List<SysUser> sysUserList) {
		this.sysUserList = sysUserList;
	}

	public SysUserWhite getSysUserWhite() {
		return sysUserWhite;
	}

	public void setSysUserWhite(SysUserWhite sysUserWhite) {
		this.sysUserWhite = sysUserWhite;
	}

	public void setWhiteMdnExist(boolean whiteMdnExist) {
		this.whiteMdnExist = whiteMdnExist;
	}
	
	public boolean getWhiteMdnExist() {
		return  whiteMdnExist;
	}

	public TCellWhite getCellWhite() {
		return cellWhite;
	}

	public void setCellWhite(TCellWhite cellWhite) {
		this.cellWhite = cellWhite;
	}



}
