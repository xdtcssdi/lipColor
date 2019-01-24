package com.example.demo.util;

public class ColorUtil {
    private static double R = 100;
    private static double angle = 30;
    private static double h = R * Math.cos((float) angle / 180 * Math.PI);
    private static double r = R * Math.sin((float) angle / 180 * Math.PI);

    public static double DistanceOf(float[] hsv1, float[] hsv2) {
        double x1 = r * hsv1[2] * hsv1[1] * Math.cos(hsv1[0] / 180 * Math.PI);
        double y1 = r * hsv1[2] * hsv1[1] * Math.sin(hsv1[0] / 180 * Math.PI);
        double z1 = h * (1 - hsv1[2]);
        double x2 = r * hsv1[2] * hsv2[1] * Math.cos(hsv2[0] / 180 * Math.PI);
        double y2 = r * hsv1[2] * hsv2[1] * Math.sin(hsv2[0] / 180 * Math.PI);
        double z2 = h * (1 - hsv1[2]);
        double dx = x1 - x2;
        double dy = y1 - y2;
        double dz = z1 - z2;
        return Math.sqrt((float) (dx * dx + dy * dy + dz * dz));
    }

    private static int rgb2hsv_max(int a, int b, int c) {
        int max = a;
        if (b > max) max = b;
        if (c > max) max = c;
        return max;
    }

    private static int rgb2hsv_min(int a, int b, int c) {
        int min = a;
        if (b < min) min = b;
        if (c < min) min = c;
        return min;
    }

    public static float[] rgb2hsv(int r, int g, int b) {
        int imax, imin, diff;
        imax = rgb2hsv_max(r, g, b);
        imin = rgb2hsv_min(r, g, b);
        diff = imax - imin;
        float hsv[] = new float[3];
        hsv[2] = imax;
        if (imax == 0)
            hsv[1] = 0;
        else
            hsv[1] = diff;

        if (diff != 0) {
            if (r == imax) {
                hsv[0] = 60 * (g - b) / diff;
            } else if (g == imax) {
                hsv[0] = 60 * (b - r) / diff + 120;
            } else {
                hsv[0] = 60 * (r - g) / diff + 240;
            }

            if (hsv[0]< 0)
                hsv[0] = hsv[0] + 360;
        } else
            hsv[0] = -1;

        return hsv;
    }
}