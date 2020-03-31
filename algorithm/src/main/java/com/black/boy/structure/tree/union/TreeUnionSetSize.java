package com.black.boy.structure.tree.union;

public class TreeUnionSetSize {

	private int N;
	private int[] eleAndGroup;
	private int[] size; //保存分组中元素的个数将决定合并后树的高度
	
	public TreeUnionSetSize(int count) {
		N = count;
		eleAndGroup = new int[N];
		size = new int[N];
		for (int i = 0; i < N; i++) {
			eleAndGroup[i] = i;
			size[i] = 1; //初始化所有分组的元素都是一个高度都是1
		}
	}
	
	public int groupSize() {
		return N;
	}
	
	public int find(int q) {
		while(true) {
			if(eleAndGroup[q] == q) 
				return q;
			q = eleAndGroup[q];
		}
	}
	
	public boolean connected(int q, int p) {
		return find(q) == find(p);
	}
	
	public void union(int q, int p) {
//		if(connected(q, p))
//			return;
		
		int rq = find(q);
		int rp = find(p);
		
		if(rq == rp)
			return;
		
		int rs = size[rq] - size[rp];
		if(rs > 0) {
			//q的分组数量大，将p合并到q中
			eleAndGroup[rp] = rq;
			size[rq] += size[rp];
		} else {
			eleAndGroup[rq] = rp;
			size[rp] += size[rq];
		}
		N--;
	}
	
}
