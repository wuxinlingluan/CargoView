package com.github.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${sheldon} on 2018/3/20.
 */

public class GoodShelfView extends View {

    private List<PointBean> goodsList;//货物列表
    private int locationCountByFloor = 3;//一层有几个
    private Rect src;// 货架图片
    private Rect src2;// 货物图片
    private Bitmap goodsShelfBtm;
    private Bitmap goodsBtm;
    private float enlargeX = 1.08f;
    private float enlargeY = 1.09f;
    private int deviationY = 25;
    private int deviationX = 10;
    private int width = 500;
    private int height = 700;

    public GoodShelfView(Context context) {
        super(context);
    }

    public GoodShelfView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GoodShelfView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        goodsShelfBtm = BitmapFactory.decodeResource(context.getResources(), R.drawable.pic_goods_shelf);
        goodsBtm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_goods);
        src = new Rect();
        src2 = new Rect();
        goodsList = new ArrayList<>();

//        int bmpWidth = width - getPaddingLeft() - getPaddingRight();
//        int bmpHeight = height - getPaddingTop() - getPaddingBottom();
        goodsShelfBtm = scaleBitmap(goodsShelfBtm, 1.2f, 1.2f);

        int bmpWidth1 = goodsShelfBtm.getWidth() * 2 / (locationCountByFloor * 5);
        int bmpHeight1 = goodsShelfBtm.getHeight() * 2 / (locationCountByFloor * 5);
        goodsBtm = scaleBitmap(goodsBtm, bmpWidth1, bmpHeight1);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //TODO 不完善
        int width = (int) (measureWidth(widthMeasureSpec) * enlargeX);
        int height = (int) (measureHeight(heightMeasureSpec) * enlargeY);
        this.width = width;
        this.height = height;
        setMeasuredDimension(width, height);
    }

    private int measureHeight(int heightMeasureSpec) {
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.AT_MOST) {
            return goodsShelfBtm.getHeight();
        }
        return heightMeasureSpec;

    }

    private int measureWidth(int widthMeasureSpec) {
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.AT_MOST) {
            return goodsShelfBtm.getWidth();
        }
        return widthMeasureSpec;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //src 这个是表示绘画图片的大小
        src.left = 0;
        src.top = 0;
        src.right = goodsShelfBtm.getWidth();
        src.bottom = goodsShelfBtm.getHeight();
        Rect dst = new Rect();
        dst.left = width / 2 - goodsShelfBtm.getWidth() / 2;
        dst.top = height / 2 - goodsShelfBtm.getHeight() / 2;
        dst.right = width / 2 + goodsShelfBtm.getWidth() / 2;
        dst.bottom = height / 2 + goodsShelfBtm.getHeight() / 2;
        // 下面的 dst 是表示 绘画这个图片的位置
        canvas.drawBitmap(goodsShelfBtm, src, dst, null);

        for (int i = 0; i < goodsList.size(); i++) {
            PointBean pointBean = goodsList.get(i);
            int centerX = pointBean.getGoodsAllocation() * (goodsShelfBtm.getWidth() / locationCountByFloor) - goodsShelfBtm.getWidth() / (2 * locationCountByFloor);
            int bottom = pointBean.getGoodLayer() * goodsShelfBtm.getHeight() / 3;
            src2.left = 0;
            src2.top = 0;
            src2.right = goodsBtm.getWidth();
            src2.bottom = goodsBtm.getHeight();

            Rect dst1 = new Rect();
            dst1.left = centerX - goodsBtm.getWidth() / 2 + deviationX;
            dst1.top = bottom - goodsBtm.getHeight() - deviationY;
            dst1.right = centerX + goodsBtm.getWidth() / 2 + deviationX;
            dst1.bottom = bottom - deviationY;
            canvas.drawBitmap(goodsBtm, src2, dst1, null);
        }
    }

    private Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

    private Bitmap scaleBitmap(Bitmap origin, float exlargeWidth, float enlargeHeight) {
        if (origin == null) {
            return null;
        }
        int height = origin.getHeight();
        int width = origin.getWidth();
        float scaleWidth = exlargeWidth;
        float scaleHeight = enlargeHeight;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix, false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }
    public void setGoodsList(List<PointBean> pointBeens) {
        this.goodsList = pointBeens;
        this.postInvalidate();
    }
}
