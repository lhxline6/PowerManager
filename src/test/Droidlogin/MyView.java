package test.Droidlogin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class MyView extends View
{
  public int p1x;
  public int color;
  public int p1y;
 // public String neirong;
  public int p2x;
  public int p2y;

  public MyView(Context paramContext)
  {
    super(paramContext);
  }

  @Override
protected void onDraw(Canvas canvas)
  {
    super.onDraw(canvas);
    
    Paint paint = new Paint();  
    paint.setColor(Color.BLACK);  
    //设置字体大小  
    paint.setTextSize(100);  
    Style style = Style.STROKE;
    paint.setStyle(style);
    //设置画出的线的 粗细程度  
    paint.setStrokeWidth(2);  
    //画出一根线  
    canvas.drawLine(p1x, p1y, p2x, p2y, paint);  
  }
}