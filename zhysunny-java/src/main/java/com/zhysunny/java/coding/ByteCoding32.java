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

    public static byte[] decode(byte[] value) {
        if (value.length % 3 != 0) {
            throw new RuntimeException("The array length must be a multiple of 3");
        }
        byte[] decode = new byte[value.length / 3 * 4];
        int offset = 0;
        int i = 0;
        while (offset < value.length) {
            decode[offset++] = (byte)(((value[i] >>> 3) & 0b00011111) * ((value[i] >>> 2 & 0b00000001) == 0 ? 1 : -1));
            decode[offset++] = (byte)((((value[i] & 0b00000011) << 3) | ((value[i + 1] >>> 5) & 0b00000111)) * ((value[i + 1] >>> 4 & 0b00000001) == 0 ? 1 : -1));
            decode[offset++] = (byte)((((value[i + 1] & 0b00001111) << 1) | ((value[i + 2] >>> 7) & 0b00000001)) * ((value[i + 2] >>> 6 & 0b00000001) == 0 ? 1 : -1));
            decode[offset++] = (byte)(((value[i + 2] & 0b00111110) >> 1) * ((value[i + 2] & 0b00000001) == 0 ? 1 : -1));
        }
        return decode;
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
