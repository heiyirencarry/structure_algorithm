package com.black.boy.compose;

/**
 * @author eBuy
 *
 */
public class Client {
	public static void main(String[] args) {
		FolderVirus folder = new FolderVirus("工作文档");
		FileVirus f1 = new FileVirus("svn.txt");
		FileVirus f2 = new FileVirus("说明文档.txt");
		FileVirus f3 = new FileVirus("补丁.txt");
		folder.add(f1);
		folder.add(f2);
		folder.add(f3);
		// 测试叶子节点的杀毒
		f1.virus();
		System.err.println("--------------------");
		// 测试容器节点的杀毒
		folder.virus();

	}
}
