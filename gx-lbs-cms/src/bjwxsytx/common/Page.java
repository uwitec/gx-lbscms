package bjwxsytx.common;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.json.annotations.JSON;

public class Page implements Serializable {

	private static final long serialVersionUID = 2728437513842150372L;
	private long totalCount;
	private Object data;
	private String field;
	private String tableName;
	private String condition;
	private String groupBy;
	private String sort;
	private String dir;
	private int start;
	private int limit;
	private String anode;
	@JSON(serialize=false)   
	public String getAnode() {
		return anode;
	}

	public void setAnode(String anode) {
		this.anode = anode;
	}

	public Page(HttpServletRequest request){
		if(!BlankUtil.isBlank(request.getParameter("anode"))){
			this.anode = request.getParameter("anode");
		}
		if(BlankUtil.isBlank(request.getParameter("start"))){
			this.start = 0;
		}else{
			this.start = Integer.parseInt(request.getParameter("start"));
		}
		if(BlankUtil.isBlank(request.getParameter("limit"))){
			this.limit = 20;
		}else{
			this.limit = Integer.parseInt(request.getParameter("limit"));			
		}

		this.dir = request.getParameter("dir");
		this.sort = request.getParameter("sort");
	}

	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

}
