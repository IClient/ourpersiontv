package comt.example.administrator.OurPersionTV;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Administrator on 2017/10/20 0020.
 */

public class BJDialog extends Dialog {
    Context context;
    int theme;
    int style;

    public BJDialog(Context context, int themeResId, int style) {
        super(context, themeResId);
        this.context = context;
        this.style = style;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bjdialog);
    }
}
