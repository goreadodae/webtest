package file.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import file.model.service.FileService;
import file.model.vo.DataFile2;

/**
 * Servlet implementation class FileRemoveServlet
 */
@WebServlet(name = "FileRemove2", urlPatterns = { "/fileRemove2" })
public class FileRemove2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileRemove2Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String afterFileName = request.getParameter("afterFileName");

		DataFile2 df = new FileService().removeFile2(afterFileName);
		
		File file = new File(df.getFilePath());
		if(file.delete()) {
			response.sendRedirect("/fileList2");
		}
		else {
			response.sendRedirect("/views/file/fileError.html");
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
