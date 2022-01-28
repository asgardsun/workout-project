package common.model;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class CriteriaDTO {

	private int pageNum;
	private int amount;

	private String type;
	private String keyword;
	private String sort;
	
	private int getPageStart() {
		return (this.pageNum -1) * this.amount;
	}
	public CriteriaDTO() {
		this(1, 10);
	}
	
	//기본설정
	public CriteriaDTO(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String[] getTypeArr() {

		return type == null ? new String[] {} : type.split("");
	}
	
	public String getListLink() {

		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());

		return builder.toString();

	}
}