package model;

public class ShcDTO {
	private int shcNum;
	private String shcContent;
	private String id;
	private String shcDate;
	private int shcReport;
	private String role;
	private int dadat;
	private String nickname;
	private int shNum;
	

		// 수정용 dto nickname과 comcNum, dadat이 같은걸 where절로 줘서 삭제 가능하게 
		public ShcDTO(String nickname, int shcNum,  int dadat ) {
			this.nickname=nickname;
			this.shcNum =shcNum;
			this.dadat= dadat;
		}
		
		// session에서 가져오기용
		public ShcDTO(String id, String role, String nickname) {
			this.id = id;
			this.role = role;
			this.nickname = nickname;
		}
		
		// 
		
		
		//글쓰기용 dto
		
		public ShcDTO( String gbcContent, String id, int gbNum, String role, int dadat) {
			this.shcContent = gbcContent;
			this.id = id;
			this.shNum = gbNum;
			this.role = role;
			this.dadat = dadat;
		
		}

		
	
	
	//getters
		public int getShcNum() {
			return shcNum;
		}

		public String getShcContent() {
			return shcContent;
		}

		public String getId() {
			return id;
		}

		public String getShcDate() {
			return shcDate;
		}

		public int getShcReport() {
			return shcReport;
		}

		public String getRole() {
			return role;
		}

		public int getDadat() {
			return dadat;
		}

		public String getNickname() {
			return nickname;
		}

		public int getShNum() {
			return shNum;
		}
		
}
	