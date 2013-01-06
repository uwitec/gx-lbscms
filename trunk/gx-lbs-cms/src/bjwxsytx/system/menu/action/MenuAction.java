package bjwxsytx.system.menu.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import bjwxsytx.base.action.BaseAction;
import bjwxsytx.common.AuthenticationUtil;
import bjwxsytx.common.Result;
import bjwxsytx.system.entity.SysMenu;
import bjwxsytx.system.menu.service.MenuService;
import bjwxsytx.system.user.service.UserService;
/***
 * 
* 功能描述:菜单管理类（可以分多行编写）
* <p>版权所有：北京无限盛元
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author 刘小明 新增日期：2013-1-6
* @author 你的姓名 修改日期：2013-1-6
* @since gx-cms
 */
public class MenuAction extends BaseAction {

	private static final long serialVersionUID = 5277712039351799309L;
	private static Logger _log = Logger.getLogger(MenuAction.class);
	@Autowired(required = true)
	private MenuService menuService;
	private Result result;
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public String findMenuByUserId(){
		Object obj = this.getSessionMap().get(AuthenticationUtil.ID_SESSION_KEY);
		Long userId = Long.parseLong(obj.toString());
		List<SysMenu> list = menuService.findMenuByUserId(userId);
		this.result = new Result();
		this.result.setData(list);
		List<SmsContactGroup> list = scgd.getContactGroupByOperatorId(userId);
		JSONArray jsonArray = JSONArray.fromObject(list);
		JSONObject jsonObject  = new JSONObject();
		jsonObject.put("list",jsonArray);
		out.println(jsonObject.toString());
		return SUCCESS;
	}
}
