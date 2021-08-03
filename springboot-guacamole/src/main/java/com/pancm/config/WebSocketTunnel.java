package com.pancm.config;

import org.apache.guacamole.GuacamoleException;
import org.apache.guacamole.net.GuacamoleSocket;
import org.apache.guacamole.net.GuacamoleTunnel;
import org.apache.guacamole.net.InetGuacamoleSocket;
import org.apache.guacamole.net.SimpleGuacamoleTunnel;
import org.apache.guacamole.protocol.ConfiguredGuacamoleSocket;
import org.apache.guacamole.protocol.GuacamoleClientInformation;
import org.apache.guacamole.protocol.GuacamoleConfiguration;
import org.apache.guacamole.websocket.GuacamoleWebSocketTunnelEndpoint;
import org.springframework.stereotype.Component;

import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
* @Title: WebSocketTunnel
* @Description:
* @Version:1.0.0
* @Since:jdk1.8
* @author pancm
* @Date  2021/8/2
**/
@ServerEndpoint(value = "/webSocket", subprotocols = "guacamole")
@Component
public class WebSocketTunnel extends GuacamoleWebSocketTunnelEndpoint {


    /**
     * Returns a new tunnel for the given session. How this tunnel is created
     * or retrieved is implementation-dependent.
     *
     * @param session       The session associated with the active WebSocket
     *                      connection.
     * @param endpointConfig information associated with the instance of
     *                      the endpoint created for handling this single connection.
     * @return A connected tunnel, or null if no such tunnel exists.
     * @throws GuacamoleException If an error occurs while retrieving the
     *                            tunnel, or if access to the tunnel is denied.
     */
    @Override
    protected GuacamoleTunnel createTunnel(Session session, EndpointConfig endpointConfig) throws GuacamoleException {
        System.out.println("sessionMap:" + session.getRequestParameterMap());
        // 获取url的值
        Integer height = Integer.valueOf(session.getRequestParameterMap().get("height").get(0));
        Integer width = Integer.valueOf(session.getRequestParameterMap().get("width").get(0));
        GuacamoleClientInformation information = new GuacamoleClientInformation();
        information.setOptimalScreenHeight(height);
        information.setOptimalScreenWidth(width);
        //guacamole server地址 r端口
        String hostname = "192.168.9.32";
        int port = 4822;
        GuacamoleConfiguration configuration = new GuacamoleConfiguration();
        configuration.setProtocol("rdp");
        configuration.setParameter("hostname", "192.168.6.93");
        configuration.setParameter("port", "3389");
        configuration.setParameter("username", "administrator");
        configuration.setParameter("password", "123456");
        configuration.setParameter("ignore-cert", "true");


        String fileName = getNowTime() + ".guac";//文件名
        String outputFilePath = "/home/guacamole";
        //添加会话录制--录屏
        configuration.setParameter("recording-path", outputFilePath);
        configuration.setParameter("create-recording-path", "true");
        configuration.setParameter("recording-name", fileName);

        GuacamoleSocket socket = new ConfiguredGuacamoleSocket(
                new InetGuacamoleSocket(hostname, port),
                configuration,
                information
        );

        GuacamoleTunnel tunnel = new SimpleGuacamoleTunnel(socket);
        return tunnel;
    }

    private String getNowTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }
}
