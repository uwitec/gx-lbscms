package bjwxsytx.system.user.vo;

public class QueryVO {
	private String username;
	private String nickname;
	private String password;
	private String captcha;

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
		builder.append("]");
		return builder.toString();
	}
}
