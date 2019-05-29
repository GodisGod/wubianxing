package com.example.wubianxing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by HONGDA on 2017/7/6.19:01
 */

public class EightPolygonChartView extends View {

    private int center;//中心点
    private int centerX;//中心点
    private int centerY;//中心点
    private int defalutSize = 300;//默认大小

    //多边形半径
    private float r1;//最外层多边形半径
    private float lineR;//中心线的半径

    //分割线画笔
    private Paint centerLinePaint;
    //绘制多边形的画笔
    private Paint polygonPaint;

    //多边形每一条先的起始坐标和终点坐标
    private float endX, endY;
    double hudu = Math.PI / 4;

    private int foxi = 20, langman = 20, luoji = 20, yuanqi = 20, pianzhi = 20, shanliang = 20, sixFeel = 20, imagenation = 20;
    private int[] ranks = new int[8];//0-100 对应 0 - r1

    private String[] strs = {"佛系", "浪漫", "逻辑", "元气", "偏执", "善良", "第六感", "想象力"};
    private Rect str_rect;
    private Paint strPaint;

    private float rankRadius = 0;

    //8个title一共有5个x值和5个y值
    private float x04 = 0;
    private float x13 = 0;
    private float x2 = 0;
    private float x57 = 0;
    private float x6 = 0;

    private float y0 = 0;
    private float y17 = 0;
    private float y26 = 0;
    private float y35 = 0;
    private float y4 = 0;

    public EightPolygonChartView(Context context) {
        this(context, null);
    }

    public EightPolygonChartView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EightPolygonChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        defalutSize = dp_px(defalutSize);

        initPaint();
    }

    private void initPaint() {
        centerLinePaint = new Paint();
        centerLinePaint.setAntiAlias(true);
        centerLinePaint.setColor(Color.WHITE);

        polygonPaint = new Paint();
        polygonPaint.setAntiAlias(true);
        polygonPaint.setStyle(Paint.Style.FILL);

        strPaint = new Paint();
        strPaint.setAntiAlias(true);
        strPaint.setColor(Color.parseColor("#FFFFFF"));
        strPaint.setTextAlign(Paint.Align.CENTER);
        strPaint.setTextSize(dp_px(10));
        //获取字体矩阵
        str_rect = new Rect();
        //设置字体矩阵
        strPaint.getTextBounds(strs[0], 0, strs[0].length(), str_rect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getSize(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);


        int width, height;

        width = measureSpec(widthMeasureSpec);
        height = measureSpec(heightMeasureSpec);

        setMeasuredDimension(width, height);

        centerX = (width - getPaddingLeft() - getPaddingRight()) / 2;
        centerY = (height - getPaddingTop() - getPaddingBottom()) / 2;

        int min = Math.min(width, height);
//        center = width / 2;
        center = min / 3;

//        lineR = center - getPaddingTop() - str_rect.height() * 2;
        lineR = center;
        r1 = center - 20;//50是用来写字的空间
        setMeasuredDimension(width, height);

        x04 = centerX;
        x13 = (float) (centerX + lineR * Math.sin(hudu) + str_rect.width());
        x2 = (float) (centerX + lineR * Math.sin(hudu * 2)) + str_rect.width() * 2;
        x57 = (float) (centerX + lineR * Math.sin(hudu * 5) - str_rect.width());
        x6 = (float) (centerX + lineR * Math.sin(hudu * 6) - str_rect.width() * 2);

        y0 = centerY - lineR - str_rect.height();
        y17 = (float) (centerY - lineR * Math.cos(hudu) - str_rect.height() * 2);
        y26 = (float) (centerY - lineR * Math.cos(hudu * 2) + str_rect.height() / 2);
        y35 = (float) (centerY - lineR * Math.cos(hudu * 4) - str_rect.height());
        y4 = (float) centerY + getPaddingTop() + lineR + str_rect.height() * 2;

    }

    private int measureSpec(int measureSpec) {
        // TODO Auto-generated method stub
        int result = 0; //结果
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        switch (specMode) {
            case MeasureSpec.AT_MOST:  // 子容器可以是声明大小内的任意大小
                result = specSize;
                break;
            case MeasureSpec.EXACTLY: //父容器已经为子容器设置了尺寸,子容器应当服从这些边界,不论子容器想要多大的空间.  比如EditTextView中的DrawLeft
                result = specSize;
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        ranks[0] = foxi;
        ranks[1] = langman;
        ranks[2] = luoji;
        ranks[3] = yuanqi;
        ranks[4] = pianzhi;
        ranks[5] = shanliang;
        ranks[6] = sixFeel;
        ranks[7] = imagenation;
        drawPolygons(canvas);
        drawCenterLine(canvas);
        drawTitle(canvas);
        drawRank(canvas);
    }

    private void drawPolygons(Canvas canvas) {
        polygonPaint.setColor(Color.parseColor("#c3e3e5"));
        drawPolygon(canvas, r1);

    }

    //绘制多边形的中心线
    private void drawCenterLine(Canvas canvas) {
        canvas.save();
        canvas.rotate(0, centerX, centerY);

//        float endY = getPaddingTop() + str_rect.height() * 2;
        float endY = centerY - lineR - str_rect.height();
        Log.i("LHD", "center = " + center + "  endY = " + endY);
        float degree = 45;
        for (int i = 0; i < 8; i++) {
            canvas.drawLine(centerX, centerY, centerX, endY, centerLinePaint);
            canvas.rotate(degree, centerX, centerY);
        }
        canvas.restore();
    }

    //绘制多边形
    private void drawPolygon(Canvas canvas, float radius) {

        Path path = new Path();
        path.moveTo(centerX, centerY - radius);
        for (int i = 0; i < 8; i++) {
            endX = (float) (centerX + radius * Math.sin(hudu * i));
            endY = (float) (centerY - radius * Math.cos(hudu * i));
            path.lineTo(endX, endY);
        }
        path.close();
        canvas.drawPath(path, polygonPaint);

    }

    //绘制标题
    private void drawTitle(Canvas canvas) {

        canvas.drawText(strs[0], x04, y0, strPaint);
        canvas.drawText(strs[1], x13, y17, strPaint);
        canvas.drawText(strs[2], x2, y26, strPaint);
        canvas.drawText(strs[3], x13, y35, strPaint);
        canvas.drawText(strs[4], x04, y4, strPaint);
        canvas.drawText(strs[5], x57, y35, strPaint);
        canvas.drawText(strs[6], x6, y26, strPaint);
        canvas.drawText(strs[7], x57, y17, strPaint);

    }

    //绘制等级
    private void drawRank(Canvas canvas) {
        polygonPaint.setStyle(Paint.Style.FILL);
        polygonPaint.setColor(Color.parseColor("#42E7FF"));
        Path path = new Path();

        path.moveTo(centerX, centerY - r1 * ranks[0] / 100);
        for (int i = 0; i < 8; i++) {
            rankRadius = r1 * ranks[i] / 100;
            endX = (float) (centerX + rankRadius * Math.sin(hudu * i));
            endY = (float) (centerY - rankRadius * Math.cos(hudu * i));
            path.lineTo(endX, endY);
        }
        path.close();
        canvas.drawPath(path, polygonPaint);
    }

    public void setFoxi(int foxi) {
        this.foxi = foxi;
        invalidate();
    }

    public void setLangman(int langman) {
        this.langman = langman;
        invalidate();
    }

    public void setLuoji(int luoji) {
        this.luoji = luoji;
        invalidate();
    }

    public void setYuanqi(int yuanqi) {
        this.yuanqi = yuanqi;
        invalidate();
    }

    public void setPianzhi(int pianzhi) {
        this.pianzhi = pianzhi;
        invalidate();
    }

    public void setShanliang(int shanliang) {
        this.shanliang = shanliang;
        invalidate();
    }

    public int getSixFeel() {
        return sixFeel;
    }

    public void setSixFeel(int sixFeel) {
        this.sixFeel = sixFeel;
        invalidate();
    }

    public int getImagenation() {
        return imagenation;
    }

    public void setImagenation(int imagenation) {
        this.imagenation = imagenation;
        invalidate();
    }

    /**
     * dp转px
     *
     * @param values
     * @return
     */
    public int dp_px(int values) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (values * density + 0.5f);
    }

}
