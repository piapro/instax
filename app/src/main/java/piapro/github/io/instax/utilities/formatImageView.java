package piapro.github.io.instax.utilities;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class formatImageView extends AppCompatImageView{

    public formatImageView(Context context) {

        super(context);
    }

    public formatImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public formatImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
