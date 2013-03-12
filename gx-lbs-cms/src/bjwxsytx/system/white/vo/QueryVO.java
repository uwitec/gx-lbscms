package bjwxsytx.system.white.vo;

public class QueryVO {
	
	private String mdn;
	private String areaname;
	
	public String getAreaname() {
		return areaname;
	}
	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String loginName;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	private String userName;
	private String nickname;
	private String password;
	private String captcha;
	private String validateCode;
	
	private String beginTime;
	private String endTime;
	private Long userId;
	
	private String groupUserFlag; // 0:cms管理员；1：企业定位用户
	
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getValidateCode() {
		return validateCode;
	}
	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	public String getGroupUserFlag() {
		return groupUserFlag;
	}
	public void setGroupUserFlag(String groupUserFlag) {
		this.groupUserFlag = groupUserFlag;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryVO [mdn=");
		builder.append(mdn);
		builder.append(", id=");
		builder.append(id);
		builder.append(", loginName=");
		builder.append(loginName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", password=");
		builder.append(password);
		builder.append(", captcha=");
		builder.append(captcha);
		builder.append(", validateCode=");
		builder.append(validateCode);
		builder.append(", beginTime=");
		builder.append(beginTime);
		builder.append(", endTime=");
		builder.append(endTime);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", groupUserFlag=");
		builder.append(groupUserFlag);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
