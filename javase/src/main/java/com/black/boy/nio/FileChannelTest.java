package com.black.boy.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
//		FileInputStream fis = new FileInputStream("C:\\Users\\eBuy\\Desktop\\1.txt");
//		FileChannel channel = fis.getChannel();

		RandomAccessFile fies = new RandomAccessFile("C:\\Users\\eBuy\\Desktop\\1.txt", "rw");
		FileChannel channel = fies.getChannel();
		
		ByteBuffer buffer = ByteBuffer.allocate(48);
		
		int read = channel.read(buffer);
		while(read != -1) {
			System.out.println("read: " + read);
			buffer.flip();
		}
		
		while(buffer.hasRemaining()) {
			System.out.println(buffer.getChar());
		}
		buffer.clear();
	}
}
