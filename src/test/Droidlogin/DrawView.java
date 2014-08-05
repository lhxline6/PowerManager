package test.Droidlogin;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class DrawView extends View
{
  public int bottom;
  public int color[]={Color.BLUE,Color.CYAN,Color.YELLOW,Color.GREEN,Color.LTGRAY,Color.RED,Color.WHITE};
  public int left;
  public int i;
  public String neirong;
  public int right;
  public int top;

  public DrawView(Context paramContext)
  {
    super(paramContext);
  }

  @Override
protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    Paint localPaint = new Paint();
    localPaint.setColor(color[i]);
    localPaint.setAntiAlias(true);
    localPaint.setStyle(Paint.Style.FILL);
    paramCanvas.drawRoundRect(new RectF(left, top, right, bottom), 15.0F, 15.0F, localPaint);
    localPaint.setColor(-16777216);
    paramCanvas.drawText(neirong, left, (top + bottom) / 2, localPaint);
  }
}