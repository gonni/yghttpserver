package org.ygsoft.webserver.gdp;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import org.ygsoft.webserver.Gdplet;
import org.ygsoft.webserver.GdpletController;
import org.ygsoft.webserver.XRequest;
import org.ygsoft.webserver.XResponse;

public class Hello extends Gdplet {

	@Override
	public void service(XRequest req, XResponse res) {
		//System.out.println("Request Service >" + req.getStrMain());
		OutputStream os = res.getOutputStream();
		
		String header = GdpletController.getDefaultOKheader("text/html");
		try {
			os.write(header.getBytes());
			String contBody = "<html><head><title>Hello Title</title></head><body>Hello GDP .. "
				+ System.currentTimeMillis() + "</body></html>";
			os.write(contBody.getBytes());
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}