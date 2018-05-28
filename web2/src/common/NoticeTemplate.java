package common;

public class NoticeTemplate {
	public NoticeTemplate() {}
	
	public static String getPageNaviHtml(int recordTotalCount, int recordCountPerPage
			, int currentPage, int naviCountPerPage, String controllName) {
		int pageTotalCount = 0;//navi 토탈 카운트
		if(recordTotalCount%recordCountPerPage!=0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		//pageTotalCount = (int)Math.ceil(recordTotalCount/recordCountPerPage);
		
		// 현재 페이지르 기점으로 시작 navi와 끝 navi를 만들어야 함
		// 현재 페이지가 1이라면? 1~5
		// 현재 페이지가 3이라면? 1~5
		// ((현재페이지-1)/리스트의 개수)*리스트개수+1
		
		if(currentPage<1) {
			currentPage=1;
		}
		else if(currentPage>pageTotalCount) {
			currentPage=pageTotalCount;
		}
		int startNavi = ((currentPage-1)/naviCountPerPage)*naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		// 끝 navi를 구할 때 주의해야 할 점
		// 토탈 navi가 122라고 할 때! (현재 페이지는 121이라고 할 때)
		// 시작 navi => (121-1)/5*5+1 = 121
		// 끝 navi => 121 + 5-1 = 125
		// 이렇기 때문에 마지막 navi 부분은 아래 코드를 추가해야 함
		
		if(endNavi>pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		// 페이지 navi에서 사용할 '<'모양과 '>'모양을 사용하기 위해
		// 필요한 변수 2개 생성(시작과 끝은 필요없으므로!)
		boolean needPrev = true;
		boolean needNext = true;
		if(startNavi==1) {
			needPrev = false;
		}
		if(endNavi==pageTotalCount) {
			needNext=false;
		}
		
		//여기가지 기본적인 구조는 끝남
		StringBuilder sb = new StringBuilder();
		if(needPrev) { //시작이 1페이지가 아니라면
			sb.append("<a href='/"+controllName+"&currentPage="+(startNavi-1)+"'> < </a>");
		}
		for(int i=startNavi;i<=endNavi;i++) {
			if(i==currentPage) {
				sb.append("<a href='/"+controllName+"&currentPage="+i+"'><B>"+i+"</B></a>");
			}
			else {
				sb.append("<a href='/"+controllName+"&currentPage="+i+"'>"+i+"</a>");
			}
		}
		if(needNext) {
			sb.append("<a href='/"+controllName+"&currentPage="+(endNavi+1)+"'> > </a>");
		}
		return sb.toString();
	}
}
