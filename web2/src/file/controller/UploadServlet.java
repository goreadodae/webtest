package file.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import file.model.service.FileService;
import file.model.vo.DataFile;
import member.model.vo.Member;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet(name = "Upload", urlPatterns = { "/upload" })
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일 업로드를 구현하려면 몇가지 정보가 필요함
		//1. 사용자 계정명 (업로드한 사람 정보가 있어야함, session 객체에서 꺼냄)
		HttpSession session = request.getSession(false);
		if(session!=null) {
			String userId = ((Member)session.getAttribute("user")).getUserId();
			//2. 최대 업로드 파일 사이즈(설정)
			int fileSizeLimit = 1024*1024*5;//byte 단위(5MB)
			//3. 업로드 될 경로
			String uploadFilePath = getServletContext().getRealPath("/")+"uploadfile"; // -> webContent라는 폴더가 됨
			//4. 인코딩 타입(파일 업로드 타입)
			String encType="UTF-8";
			//위에 정보를 바탕으로
			//5. MultipartRequest 객체를 생성
			MultipartRequest multi = new MultipartRequest(request,uploadFilePath,fileSizeLimit,encType,new DefaultFileRenamePolicy());
			//마지막 인자값인 DefaultFileRenamePolicy 객체를 생성하여 넣어줌으로서
			//파일 중복처리를 자동으로 해결함
			//	ex) a.bmp 업로드 하게 되면 처음에는 a.bmp 그대로 업로드가 됨
			//a.bmp를 또 다시 업로드 하게 되면 a1.bmp로 변경되서 업로드가 됨
			//이후에도 동일한 파일이름을 업로드 시 a2.bmp, a3.bmp, a4.bmp … 등
			
			
			//업로드된 파일의 정보를 DB에 기록하여야 함
			//1. 파일 이름(fileName)
			//getFielsystemName("view의 파라미터 이름");을 하게 되면
			//해당 업로드 될때의 파일 이름을 가져옴
			String fileName = multi.getFilesystemName("upfile");
			
			//2. 업로드 파일의 실제 총 경로(filePath)
			//총 경로 : filePath+파일이름
			//ex) 업로드한 파일이 a.txt라면?
			//총 경로 : c:\webworkspace2\web2\webContent\\uploadfile\a.txt
			String fullFilePath = uploadFilePath+"\\"+fileName;
			
			//3. 파일의 길이=파일의 크기(length)
			File file = new File(fullFilePath);//해당 파일을 연다
			long fileSize = file.length();
			//파일 사이즈가 long인 이유?
			//파일 사이즈는 byte 단위
			
			//4. 파일 유저명
			//굳이 만들필요가 없음(위에 usserId변수 만들어 놓았음)
			
			//5. 파일 업로드 타임(밀리세컨까지)
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			Timestamp uploadTime = null;
			uploadTime = Timestamp.valueOf(formatter.format(Calendar.getInstance().getTimeInMillis()));
			//Timestamp 패키지는 java.sql.Timestamp로 가져와야 함
			
			//객체에 값 저장
			DataFile df = new DataFile(fileName,fullFilePath,fileSize,userId,uploadTime);
			int result = new FileService().uploadFile(df);
			if(result>0) {
				response.sendRedirect("/views/file/fileUploadSuccess.jsp");
			}
			else {
				response.sendRedirect("/views/file/fileError.html");
			}
		}
		else {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
