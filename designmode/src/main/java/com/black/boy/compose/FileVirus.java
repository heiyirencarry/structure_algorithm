package com.black.boy.compose;

/**
 * @author eBuy 这个是叶子节点
 */
public class FileVirus implements IVirus {
	private String name;

	/**
	 * @param name
	 */
	public FileVirus(String name) {
		super();
		this.name = name;
	}

	@Override
	public void virus() {
		System.out.println("---杀毒文件：" + name);
	}

}
