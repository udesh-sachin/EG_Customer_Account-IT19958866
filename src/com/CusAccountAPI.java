package com;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CusAccount")
public class CusAccountAPI extends HttpServlet {

	private static final long serialVersionUID = -8821173037461710172L;
	CusAccount Account = new CusAccount();

	public CusAccountAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	// INSERT
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = Account.insertAccount(request.getParameter("AcNumber"),
				request.getParameter("Name"), request.getParameter("NIC"), request.getParameter("Phone"),
				request.getParameter("Email"));
		response.getWriter().write(output);
	}

	// UPDATE
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request);
		String output = Account.updateAccount(paras.get("hidCustomerIDSave").toString(), paras.get("AcNumber").toString(),
				paras.get("Name").toString(), paras.get("NIC").toString(), paras.get("Phone").toString(),
				paras.get("Email").toString());
		response.getWriter().write(output);
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> paras = getParasMap(request);
		String output = Account.deleteAccount(paras.get("idcustomer").toString());
		response.getWriter().write(output);
	}

	public static Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

}
