## Introduction ##
This project is a personal one to research and develop a scalable HTTP server in Java.
These days, many applications, especially mobile applications, support Web UI based on the HTTP protocol, and HTTP server feature(function) is getting more important.
But, most of the open-source HTTP server like Tomcat, Jetty are too heavy to use in mobile devices which don't have strong H/W spec like CPU, RAM and etc.

So, I have been developing a micro & scalable HTTP server. This server doesn't support the full specifications of HTTP, but if you want HTTP server to support the specific HTTP functions (protocol specifications), you can make the server support the functions you need by implementing protocol plugins or service plugins.

## Features ##
The main principle(purpose) of this project module is in the following manner :
  * Light weight
  * Extensible API
  * Simple to use
  * Adapted to mobile device


## Design & Structure ##
The following image is a simple class diagram which contains imported classes.

<img src='http://yghttpserver.googlecode.com/files/ckServerUml.jpg' />

This application provides three interfaces to help you to extends functionalities.
  * IServiceScheduler : customize processing method
  * AbstractService : extend to various services based on http protocol
  * Gdplet : make dynamic web service similar to Servlet programming

## How to use ##
To run the http server supporting static resource and GDP service, the following code is recommended. Before running, MicroWebserverCore project should be imported.

```
        public static void main(String...v){
		// V2
		ServiceMapper fCont = new ServiceMapper();
		
		ServiceManager<Gdplet> sMng = new ServiceManager<Gdplet>();
		sMng.addService(new Hello());
		sMng.addService(new Exam());
		sMng.addService(new FileTransExam());
		
		fCont.addContainer(new GdpService(sMng));
				
		fCont.addContainer(new StaticResourceService("HttpRoot/"));
		PLogging.printv(PLogging.DEBUG, fCont.getContainers().size() + " containers registered ..");
		
		MicroWebServer server = new MicroWebServer(2012, fCont, ServiceScheduler.getDefaultScheduler());
		server.startServer();
		
		PLogging.printv(PLogging.DEBUG, "YG XServer stopped..");
	}
```

## How to make GDPlet ##
The following code is the 'Hello GDP' example code which is similar to the low level of Servlet one. You just need to implement the service method of Gdplet class.

```
public class Hello extends Gdplet {

	@Override
	public void service(XRequest req, XResponse res) {
		
		try {
			res.setAttribute(ProtocolHelper.ContentType, ProtocolHelper.ValueContentTypeText);
			
			// write HttpHeader
			res.writeStringToHeader();	
			
			String contBody = "<html><head><title>Exam Title</title></head><body>" +
				"Hello Exam.. "
				+ new Date(System.currentTimeMillis()) + "</body></html>";
			
			// write HttpBody
			res.writeStringToBody(contBody);	
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
```

If server module is running, you can check the result in web browser, like below :<br>

<img src='http://yghttpserver.googlecode.com/files/HelloBrowser.jpg' />

As an example, you can run a server application based on GUI like below : <br>
(MicroWebserverEx project in the source contains below application)<br>
<br>
<img src='http://yghttpserver.googlecode.com/files/ygServerSetting.jpg' />

By using opened API of this server, you can extend functionalities of this module, and  easily adapt to your application.<br>
<br>
<h2>Developer</h2>
<blockquote>Name : Young-Gon Kim, Korea<br>
Email : gonni21c@gmail.com</blockquote>

<h2>Demo</h2>
<a href='http://code.google.com/p/aircctv'>CCTV Solution</a>

<h2>Reference</h2>
- Blog<br>
<ul><li><a href='http://jakarta.tistory.com'>http://jakarta.tistory.com</a> (Korean)<br>
</li><li><a href='http://ygonni.blogspot.com'>http://ygonni.blogspot.com</a> (English)</li></ul>

<h2>Donate this project</h2>
<a href='Hidden comment: 
<form action="https://www.paypal.com/cgi-bin/webscr" method="post">
<input type="hidden" name="cmd" value="_s-xclick">
<input type="hidden" name="encrypted" value="-----BEGIN PKCS7-----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-----END PKCS7-----
">
<input type="image" src="https://www.paypalobjects.com/WEBSCR-640-20110306-1/en_US/i/btn/btn_donate_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
<img alt="" border="0" src="https://www.paypalobjects.com/WEBSCR-640-20110306-1/en_US/i/scr/pixel.gif" width="1" height="1">


Unknown end tag for </form>



'></a>