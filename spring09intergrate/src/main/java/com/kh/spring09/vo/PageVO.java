package com.kh.spring09.vo;

import lombok.Data;

@Data
public class PageVO {
	private String column;//매개변수에 있는 검색항목
	private String keyword;//매개변수에 있는 검색항목
	private int page = 1;//매개변수에 있는 페이지번호(없으면 1)
	private int size = 10;//매개변수에 있는 규격(없으면 10)
	private int count;//데이터 갯수(db에서 조회해서 채워야함)

	//목록인지 검색인지 판정하는 메소드
	
	//목록 : 컬럼과 키워드 중 하나라도 없는 경우
	//검색 : 컬럼과 키워드 모두 있는 경우
	
	public boolean isList() {
		return column == null || keyword ==null;
	}
	
	public boolean isSearch() {
		return !isList();
	}

	//시작 Rownum 종료 Rownum
	
	public int getBeginRownum() {
		return page*size -(size-1);
	}
	public int getEndRownum() {
		return page*size;
	}
	
	public String getSearchParams() {
		if(isList()) return "size="+size;
		else return "size="+size+"&column="+column+"&keyword="+keyword;
	}
	//현재 페이지에 맞는 첫 블록 번호 반환
	public int getBeginBlock() {
		return (page-1)/10*10+1;
	}
	
	public boolean hasPrevious() {
		return getBeginBlock()>1;
	}
	
	public int getPreviousBlock() {
		return getBeginBlock()-1;
	}
	
//	총 페이지수를 계산하여 반환(pageCount)
	public int getPageCount() {
		return (count-1)/size+1;
	}
	//현재 페이지 기준 마지막 블록 계산 해 반환(endBlock)
	public int getEndBlock() {
		int endBlock = getBeginBlock()+9;
		return Math.min(getPageCount(), endBlock);
		
	}
	//다음이 존재하는지 판정하여 반환 하는 메소드
	public boolean hasNext() {
		return getEndBlock()<getPageCount();
	}
	//다음을 누르면 나올 블록번호를 계산하는 메소드(endBlock+1)
	public int getNextBlock() {
		return getEndBlock()+1;
	}
}
