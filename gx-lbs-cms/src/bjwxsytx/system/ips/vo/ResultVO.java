package bjwxsytx.system.ips.vo;

public class ResultVO {
	private Long id;
	private Long reId;
	public Long getReId() {
		return reId;
	}
	public void setReId(Long reId) {
		this.reId = reId;
	}
	private String userName;
	private String loginName;
	private String ip;
	private String clientId;
	private Long userId;
	private String memo;
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String cliendId) {
		this.clientId = cliendId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
}
