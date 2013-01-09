package bjwxsytx.system.white.vo;

public class QueryVO {
	
	private String mdn;
	
	
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
	private String username;
	private String nickname;
	private String password;
	private String captcha;
	private String validateCode;
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("QueryVO [username=");
		builder.append(username);
		builder.append(", nickname=");
		builder.append(nickname);
		builder.append(", password=");
		builder.append(password);
		builder.append(", captcha=");
		builder.append(captcha);
		builder.append(", mdn=");
		builder.append(mdn);
		builder.append("]");
		
		return builder.toString();
	}
	public String getMdn() {
		return mdn;
	}
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
}
