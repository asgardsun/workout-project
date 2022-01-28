package common.model;

public class ReplyPager {
	public static final int PAGE_SCALE = 10;	//페이지 당 댓글 수
	public static final int BLOCK_SCALE = 2;	//화면 당 페이지 수
	private int curPage;
	private int prevPage;
	private int nextPage;
	private int totalPage;
	
	private int curBlock;
	private int prevBlock;
	private int nextBlock;
	private int totalBlock;
	
	private int pageBegin;
	private int pageEnd;
	
	private int blockBegin;
	private int blockEnd;
	
	public ReplyPager(int count, int curPage) {
		curBlock = 1;
		this.curPage = curPage;
		setTotalPage(count);
		setPageRange();
		setTotalBlock();
		setBlockRange();
	}
	
	
	public void setBlockRange() {
		curBlock = (int)Math.ceil((curPage-1)/BLOCK_SCALE)+1;
		blockBegin = (curBlock-1)*BLOCK_SCALE+1;
		blockEnd = blockBegin+BLOCK_SCALE-1;
		if(blockEnd > totalPage) {
			blockEnd = totalPage;
		}
		prevPage = (curPage == 1) ? 1 : (curBlock-1)*BLOCK_SCALE;
		nextPage = curBlock > totalBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;
		if(nextPage >= totalPage) {
			nextPage = totalPage;
		}
	}
	
	public void setPageRange() {
		pageBegin = (curPage-1)*PAGE_SCALE+1;
		pageEnd = pageBegin+PAGE_SCALE-1;
	}
	
	// getter, setter
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public void setPrevPage(int prevPage) {
		this.prevPage = prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int count) {
		totalPage = (int) Math.ceil(count*1.0 / PAGE_SCALE);
	}
	public int getCurBlock() {
		return curBlock;
	}
	public void setCurBlock(int curBlock) {
		this.curBlock = curBlock;
	}
	public int getPrevBlock() {
		return prevBlock;
	}
	public void setPrevBlock(int prevBlock) {
		this.prevBlock = prevBlock;
	}
	public int getNextBlock() {
		return nextBlock;
	}
	public void setNextBlock(int nextBlock) {
		this.nextBlock = nextBlock;
	}
	public int getTotalBlock() {
		return totalBlock;
	}
	public void setTotalBlock() {
		totalBlock = (int) Math.ceil(totalPage / BLOCK_SCALE);
	}

	public int getPageBegin() {
		return pageBegin;
	}

	public void setPageBegin(int pageBegin) {
		this.pageBegin = pageBegin;
	}

	public int getPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(int pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getBlockBegin() {
		return blockBegin;
	}

	public void setBlockBegin(int blockBegin) {
		this.blockBegin = blockBegin;
	}

	public int getBlockEnd() {
		return blockEnd;
	}

	public void setBlockEnd(int blockEnd) {
		this.blockEnd = blockEnd;
	}
	
	
	
	
	
	
}
