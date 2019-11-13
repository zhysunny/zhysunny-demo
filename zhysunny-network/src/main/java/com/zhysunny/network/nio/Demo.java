package com.zhysunny.network.nio;

import java.nio.IntBuffer;

/**
 * @author 章云
 * @date 2019/11/13 21:42
 */
public class Demo {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(2);
        intBuffer.flip();
        System.err.println("position: " + intBuffer.position());
        System.err.println("limit: " + intBuffer.limit());
        System.err.println("capacity: " + intBuffer.capacity());

        // 这里不能读, 因为 limit == position == 0, 没有数据.
        //System.err.println(intBuffer.get());

        intBuffer.clear();
        System.err.println("position: " + intBuffer.position());
        System.err.println("limit: " + intBuffer.limit());
        System.err.println("capacity: " + intBuffer.capacity());

        // 这里可以读取数据了, 因为 clear 后, limit == capacity == 2, position == 0,
        // 即使我们没有写入任何的数据到 buffer 中.
        System.err.println(intBuffer.get()); // 读取到0
        System.err.println(intBuffer.get()); // 读取到0
    }
}
