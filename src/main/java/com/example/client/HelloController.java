package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class HelloController {
    @FXML
    private TextArea resultTextArea;
    @FXML
    private TextField data1TextField;
    @FXML
    private TextField data2TextField;
    @FXML
    private TextField data3TextField;

    private DatagramSocket socket;

    @FXML
    protected void onSendButtonClick() {
        try {
            socket = new DatagramSocket(12346);
            DatagramPacket packet;
            byte[] buf;
            int length = data1TextField.getText().length();
            buf = copy(data1TextField.getText().getBytes(StandardCharsets.UTF_8));
            packet = new DatagramPacket(buf, length, InetAddress.getByName("localhost"), 12345);
            socket.send(packet);

            length = data2TextField.getText().length();
            buf = copy(data2TextField.getText().getBytes(StandardCharsets.UTF_8));
            packet = new DatagramPacket(buf, length, InetAddress.getByName("localhost"), 12345);
            socket.send(packet);

            length = data3TextField.getText().length();
            buf = copy(data3TextField.getText().getBytes(StandardCharsets.UTF_8));
            packet = new DatagramPacket(buf, length, InetAddress.getByName("localhost"), 12345);
            socket.send(packet);

            packet = new DatagramPacket(buf, 100);
            socket.receive(packet);
            resultTextArea.setText(new String(packet.getData()));
        } catch (IOException e)

        {
            e.printStackTrace();
        } finally

        {
            socket.close();
        }
    }

    private byte[] copy(byte[] from)
    {
        byte[] arr = new byte[100];
        System.arraycopy(from, 0, arr, 0, from.length);
        return arr;
    }
}