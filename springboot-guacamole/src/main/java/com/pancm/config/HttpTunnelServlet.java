package com.pancm.config;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.servlet.GuacamoleHTTPTunnelServlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
* @Title: HttpTunnelServlet
* @Description:
* @Version:1.0.0
* @Since:jdk1.8
* @author pancm
* @Date  2021/7/2
**/
@WebServlet(urlPatterns = "/tunnel")
public class HttpTunnelServlet extends GuacamoleHTTPTunnelServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = -3224942386695394818L;

	@Override
	protected GuacamoleTunnel doConnect(HttpServletRequest request) throws GuacamoleException {
        System.out.println("request:"+request.getParameterMap());
        System.out.println("request2:"+request.getParameterNames());

	    String hostname = "192.168.9.32"; //guacamole server地址
        int port = 4822; //guacamole server端口
        GuacamoleConfiguration configuration = new GuacamoleConfiguration();
        configuration.setProtocol("rdp"); // 远程连接协议
        configuration.setParameter("hostname", "192.168.6.93");
        configuration.setParameter("port", "3389");
        configuration.setParameter("username", "administrator");
        configuration.setParameter("password", "123456");
        configuration.setParameter("ignore-cert", "true");

        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(hostname, port),
                configuration
        );
        GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);
        return tunnel;
	}
}
