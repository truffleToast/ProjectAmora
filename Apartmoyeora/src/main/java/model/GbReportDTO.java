package model;

public class GbReportDTO {

	private int gbReportNum;
	private String id;
	private int gbNum;

	// 테이블 행 삭제 생성자
	public GbReportDTO(int gbReportNum) {
		this.gbReportNum = gbReportNum;
	}

	// 테이블 행 추가 생성자
	public GbReportDTO(String id, int gbNum) {
		this.id = id;
		this.gbNum = gbNum;
	}

	// 전체 생성자
	public GbReportDTO(int gbReportNum, String id, int gbNum) {
		this.gbReportNum = gbReportNum;
		this.id = id;
		this.gbNum = gbNum;
	}
	//getter
	public int getGbReportNum() {
		return gbReportNum;
	}

	public String getId() {
		return id;
	}

	public int getGbNum() {
		return gbNum;
	}

}
