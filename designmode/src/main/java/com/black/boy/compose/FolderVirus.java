package com.black.boy.compose;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eBuy 这个是容器节点
 */
public class FolderVirus implements IVirus {

	private String name;

	public FolderVirus(String name) {
		this.name = name;
	}

	// 这个持有接口对象的集合是组合设计模式的精髓
	// 组合设计模式和继承、组合方式实现代码复用中的组合是两码事
	// 两者没有联系
	private List<IVirus> list = new ArrayList<IVirus>();

	@Override
	public void virus() {
		System.out.println("---正在杀毒：" + name + " 文件夹");
		for (IVirus v : list) {
			v.virus();
		}
	}

	public void add(IVirus iVirus) {
		list.add(iVirus);
	}

	public void remove(IVirus iVirus) {
		list.remove(iVirus);
	}

	public IVirus get(int index) {
		return list.get(index);
	}

}
