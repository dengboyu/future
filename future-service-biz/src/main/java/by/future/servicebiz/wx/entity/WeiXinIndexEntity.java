package by.future.servicebiz.wx.entity;
/**
 * 微信自定义首页的javabean
 * @author Administrator
 *
 */
public class WeiXinIndexEntity {
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	public WeiXinIndexEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	@Override
	public String toString() {
		return "WeiXinIndexVo [signature=" + signature + ", timestamp=" + timestamp + ", nonce=" + nonce + ", echostr="
				+ echostr + "]";
	}
	
}
