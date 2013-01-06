package bjwxsytx.base.constants;
/**
 * 
* 功能描述:项目异常信息常量类
* <p>版权所有：金鹏科技
* <p>未经本公司许可，不得以任何方式复制或使用本程序任何部分
*
* @author dengcd 新增日期：2008-10-9
* @author 你的姓名 修改日期：2008-10-9
* @since wapportal_manager version(2.0)
 */
public class ExceptionConstants {
	/*------------------异常类别----------------------------*/
	/**
	 * 异常类型关键字--系统异常：0
	 * @author dengcd
	 */
	public static final String EXCEPTION_SYSTEM = "0";
	/**
	 * 异常类型关键字--业务异常：1
	 * @author dengcd
	 */
	public static final String EXCEPTION_OPERATION = "1";
	/*------------------异常类别----------------------------*/
	
	/*------------------系统异常信息定义区----------------------------*/
	/**
	 * 系统异常类型关键字：001--异常信息：网络出现问题，请联系管理员.
	 * @author dengcd
	 */
	public static final String EXCEPTION_SYSTEM_NETERROR = "sys_001";
	/*------------------系统异常信息定义区----------------------------*/
	
	/*------------------业务异常信息定义区----------------------------*/
	/**
	 * 业务异常类型关键字：menu_001--异常信息：不能为最后节点的菜单添加子菜单.
	 * @author dengcd
	 */
	public static final String EXCEPTION_OPERATION_MENU_ISEND = "menu_001";
	
	/**
	 * 业务异常类型关键字：menu_002--异常信息：该菜单存在子菜单，不能删除！
	 * @author dengcd
	 */
	public static final String EXCEPTION_OPERATION_MENU_CHILD = "menu_002";
	
	/**
	 * 业务异常关键字:  item_001 --异常信息:无法删除已被频道使用的栏目
	 */	
	public static final String EXCEPTION_OPERATION_ITEM_USED = "item_001";
	
	/**
	 * 业务异常关键字:  item_002 --异常信息:已经下线的栏目不能被添加到频道中!
	 */	
	public static final String EXCEPTION_OPERATION_ITEM_OFFLINE = "item_002";
	
	/**
	 * 业务异常关键字:  item_003 --异常信息:不存在该栏目记录!
	 */	
	public static final String EXCEPTION_OPERATION_ITEM_NULL = "item_003";
	
	/**
	 * 业务异常关键字:  channel_001 --异常信息:该频道下没有找到对应的栏目!
	 */	
	public static final String EXCEPTION_OPERATION_CHANNEL_HEAD = "channel_001";
	
	/**
	 * 业务异常关键字:  style_001 --异常信息:已经下线的样式不能被添加到频道中!
	 */	
	public static final String EXCEPTION_OPERATION_STYLE_OFFLINE = "style_001";
	
	/**
	 * 业务异常关键字:  item_004 --异常信息:个性化栏目已存在，不能再添加个性化栏目!
	 */	
	public static final String EXCEPTION_OPERATION_ITEM_ISEXIST = "item_004";
	
	/**
	 * 业务异常关键字:  contenttype_001 --异常信息:该内容分类已被引用，不能删除!
	 */	
	public static final String EXCEPTION_OPERATION_CONTENTTYPE_ISUSED = "contenttype_001";
	/**
	 * 业务异常关键字:  contenttype_002 --异常信息:不存在该内容分类！
	 */	
	public static final String EXCEPTION_OPERATION_CONTENTTYPE_ISNULL = "contenttype_002";
	
	/**
	 *  业务异常关键字:  style_001 --异常信息:无法删除已经被频道引用的样式!
	 */
	public static final String EXCEPTION_OPERATION_STYLE_USED = "style_002";
	
	/**
	 * 业务异常关键字：menu_003 --异常信息：菜单被角色引用，不能删除！
	 */
	public static final String EXCEPTION_OPERATION_MENU_USED = "menu_003";
	
	/*------------------业务异常信息定义区----------------------------*/
	
	
    
}
