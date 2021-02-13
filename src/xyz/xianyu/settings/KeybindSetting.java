package xyz.xianyu.settings;

public class KeybindSetting extends Setting {
	public int code;
	
	public KeybindSetting(int code) {
		this.English = "KeyBind";
		this.Chinese = "按键绑定";
		this.code = code;
	}
	
	public int getKeyCode() {
		return code;
	}
	
	public void setKeyCode(int code) {
		this.code=code;
	}
}
