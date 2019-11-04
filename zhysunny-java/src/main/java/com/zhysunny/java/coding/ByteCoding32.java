package com.zhysunny.java.coding;

/**
 * @author 章云
 * @date 2019/11/1 9:19
 */
public class ByteCoding32 {

    private static final int QP = 128;

    public static byte[] encode(float[] value) {
        if (value.length % 4 != 0) {
            throw new RuntimeException("The array length must be a multiple of 4");
        }
        byte[] bytes = new byte[value.length / 4 * 3];
        int offset = 0;
        int i = 0;
        while (offset < value.length) {
            int valquan1 = Math.round(value[offset++] * QP);
            byte uval1 = (byte)((Math.min(Math.abs(valquan1), 31)));
            byte s1 = (byte)(valquan1 >>> 31);
            int valquan2 = Math.round(value[offset++] * QP);
            byte uval2 = (byte)((Math.min(Math.abs(valquan2), 31)));
            byte s2 = (byte)(valquan2 >>> 31);
            int valquan3 = Math.round(value[offset++] * QP);
            byte uval3 = (byte)((Math.min(Math.abs(valquan3), 31)));
            byte s3 = (byte)(valquan3 >>> 31);
            int valquan4 = Math.round(value[offset++] * QP);
            byte uval4 = (byte)((Math.min(Math.abs(valquan4), 31)));
            byte s4 = (byte)(valquan4 >>> 31);

            bytes[i++] = (byte)((uval1 << 3) | (s1 << 2) | (uval2 >> 3));
            bytes[i++] = (byte)((uval2 << 5) | (s2 << 4) | (uval3 >> 1));
            bytes[i++] = (byte)((uval3 << 7) | (s3 << 6) | (uval4 << 1) | s4);
        }
        return bytes;
    }

    public static float[] decode(byte[] value) {
        if (value.length % 3 != 0) {
            throw new RuntimeException("The array length must be a multiple of 3");
        }
        float[] floats = new float[value.length / 3 * 4];
        int offset = 0;
        int i = 0;
        while (offset < value.length) {
            int b1 = value[offset++];
            int b2 = value[offset++];
            int b3 = value[offset++];

            byte i1 = (byte)(b1 >> 3);
            byte s1 = (byte)((byte)(b1 << 5) >>> 7);
            float v1 = (byte)((s1 << 7) | i1);

            byte i2 = (byte)(((byte)(b1 << 6) >> 3) | ((byte)(b2 >> 5) & ((byte)Math.pow(2, 8 - 5) - 1)));
            byte s2 = (byte)((b2 << 3) >> 7);
            float v2 = (byte)((s2 << 7) | i2);

            byte i3 = (byte)(((b2 << 4) >> 3) | (b3 >> 7));
            byte s3 = (byte)((b3 << 1) >> 7);
            float v3 = (byte)((s3 << 7) | i3);

            byte i4 = (byte)((b2 << 2) >> 3);
            byte s4 = (byte)((b3 << 7) >> 7);
            float v4 = (byte)((s4 << 7) | i4);

            floats[i++] = v1 / 128.0f;
            floats[i++] = v2 / 128.0f;
            floats[i++] = v3 / 128.0f;
            floats[i++] = v4 / 128.0f;
        }
        return floats;
    }

    public static int getInt(byte[] bytes, int offset) {
        return (0xff & bytes[offset]) | ((0xff & bytes[offset + 1]) << 8) | ((0xff & bytes[offset + 2]) << 16) | ((0xff & bytes[offset + 3])
        << 24);
    }

    public static void main(String[] args) {
        byte b = -93;
        System.out.println((byte)(b >> 5) & ((byte)Math.pow(2, 8 - 5) - 1));
    }

}
