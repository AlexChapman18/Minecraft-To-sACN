package com.Alex.minecraftsacn.JavaSACN;

import java.io.IOException;
import java.net.*;

public class sACNSocket {

    DatagramSocket Socket;

    public sACNSocket() throws SocketException {
        this.Socket = new DatagramSocket(7000);
    }

    public void sendPacket(DatagramPacket _packet) throws IOException {
        this.Socket.send(_packet);
    }
}