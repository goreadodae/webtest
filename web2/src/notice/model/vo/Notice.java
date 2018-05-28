package notice.model.vo;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String subject;
	private String contents;
	private String userId;
	private Date regDate;
	public Notice() {
		// TODO Auto-generated constructor stub
	}
	
	public Notice(int noticeNo, String subject, String contents, String userId, Date regDate) {
		super();
		this.noticeNo = noticeNo;
		this.subject = subject;
		this.contents = contents;
		this.userId = userId;
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", subject=" + subject + ", contents=" + contents + ", userId=" + userId
				+ ", regDate=" + regDate + "]";
	}

	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
}
