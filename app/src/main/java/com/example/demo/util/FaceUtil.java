package com.example.demo.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;

public class FaceUtil {
    /**
     * 在指定画布上将人脸框出来
     *
     * @param canvas      给定的画布
     * @param face        需要绘制的人脸信息
     * @param width       原图宽
     * @param height      原图高
     * @param frontCamera 是否为前置摄像头，如为前置摄像头需左右对称
     * @param DrawOriRect 可绘制原始框，也可以只画四个角
     */

    static public void drawFaceRect(Canvas canvas, FaceRect face, int width, int height, boolean frontCamera, boolean DrawOriRect) {
        if (canvas == null) {
            return;
        }

        Paint paint = new Paint();
        paint.setStyle(Style.STROKE);
        paint.setColor(Color.rgb(255, 203, 15));

        //框人脸
        int len = (face.bound.bottom - face.bound.top) / 8;
        if (len / 8 >= 2) paint.setStrokeWidth(len / 8);
        else paint.setStrokeWidth(2);
        Rect rect = face.bound;
        if (frontCamera) {
            int top = rect.top;
            rect.top = width - rect.bottom;
            rect.bottom = width - top;
        }
        canvas.drawRect(rect, paint);

        //框嘴唇
        int len_m = (face.mouth.bottom - face.mouth.top) / 8;
        if (len_m / 8 >= 2) paint.setStrokeWidth(len_m / 8);
        else paint.setStrokeWidth(2);

        if (frontCamera) {
            int top = face.mouth.top;
            face.mouth.top = width - face.mouth.bottom + 10;
            face.mouth.bottom = width - top - 10;
            int left = face.mouth.left;
            face.mouth.left = height - face.mouth.right - 5;
            face.mouth.right = height - left + 5;
        }else{
            face.mouth.bottom +=5;//下
            face.mouth.top -= 5;//上
            int left = face.mouth.left;
            face.mouth.left = height - face.mouth.right + 5;//左
            face.mouth.right = height - left - 5;//右
        }
        canvas.drawRect(face.mouth, paint);
    }

    private static final String TAG = "FaceUtil";

    /**
     * 将矩形随原图顺时针旋转90度
     *
     * @param r      待旋转的矩形
     * @param width  输入矩形对应的原图宽
     * @param height 输入矩形对应的原图高
     * @return 旋转后的矩形
     */
    static public Rect RotateDeg90(Rect r, int width, int height) {
        int left = r.left;
        r.left = height - r.bottom;
        r.bottom = r.right;
        r.right = height - r.top;
        r.top = left;
        return r;
    }

    /**
     * 将点随原图顺时针旋转90度
     *
     * @param p      待旋转的点
     * @param width  输入点对应的原图宽
     * @param height 输入点对应的原图宽
     * @return 旋转后的点
     */
    static public Point RotateDeg90(Point p, int width, int height) {
        int x = p.x;
        p.x = height - p.y;
        p.y = x;
        return p;
    }
}
