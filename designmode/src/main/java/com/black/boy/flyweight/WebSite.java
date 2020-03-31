package com.black.boy.flyweight;

import java.util.HashMap;
import java.util.Map;

public class WebSite {

	public static void main(String[] args) {
		IWebSite wb = WebSiteFactory.getWebSite("WEIBO");
		IWebSite wx = WebSiteFactory.getWebSite("WEIXIN");

		User u1 = new User("lisi", 1);
		wb.use(u1);
		wx.use(u1);

	}
}

class WebSiteFactory {
	private static Map<String, IWebSite> map = new HashMap<String, IWebSite>();

	public static IWebSite getWebSite(String webName) {
		IWebSite web = map.get(webName);
		if (web == null) {
			web = new WebImpl(webName);
			map.put(webName, web);
		}
		return web;
	}
}

interface IWebSite {
	public abstract void use(User user);// 谁使用了什么网站
}

class WebImpl implements IWebSite {
	private String webName;

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public WebImpl(String webName) {
		this.webName = webName;
	}

	@Override
	public void use(User user) {
		System.out.println("webName [" + this.webName + "]" + user.toString());
	}
}

class User {
	private String name;
	private int id;

	public User(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + "]";
	}
}