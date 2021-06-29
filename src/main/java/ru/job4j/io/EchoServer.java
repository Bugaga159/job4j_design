package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	public static void main(String[] args) throws IOException {
		try (ServerSocket server = new ServerSocket(9000)) {
			while (!server.isClosed()) {
				Socket socket = server.accept();
				try (OutputStream out = socket.getOutputStream();
					 BufferedReader in = new BufferedReader(
							 new InputStreamReader(socket.getInputStream()))) {
					String answer = "";
					for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
						System.out.println(str);
						if (str.contains("msg=Hello")) {
							answer = "Hello";
						} else if (str.contains("msg=Exit")) {
							answer = "Завершить работу сервера.";
							server.close();
						} else if (str.contains("msg=")) {
							String[] msg = str.split("=");
							answer = msg[1];
						}
					}
					out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
					if (!answer.isEmpty()) {
						out.write((answer + "\r\n").getBytes());
					}
				}
			}
		}
	}
}