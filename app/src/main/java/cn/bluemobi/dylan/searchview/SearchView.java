package cn.bluemobi.dylan.searchview;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bingkong.shadewindow.MainActivity;
import com.bingkong.shadewindow.R;

import java.util.List;

/**
 * Android自定义SearchView
 * Created by yuandl on 2016-11-17.
 */

public class SearchView extends LinearLayout implements TextWatcher, View.OnClickListener {

    public static final int SEARCH_INPUT_TEXT=0;
    /**
     * 输入框
     */
    private EditText et_search;
    /**
     * 输入框后面的那个清除按钮
     */
    private ImageView bt_clear;
    /**
     * 输入框后面的那个搜索按钮
     */
    private ImageView bt_searchbtn;
    private ImageView bt_searchicon;
    RelativeLayout inputsearchkeylayout;
    RelativeLayout waitingsearchlayout;

    Context mContext;
    public interface OnChangeListener {
        void onChange(int type,String content);
    }
    private OnChangeListener mListener;
    public void setOnChangeListener(OnChangeListener listener) {
        mListener = listener;
    }


    public SearchView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        /**加载布局文件*/
        mContext=context;
        LayoutInflater.from(context).inflate(R.layout.pub_searchview, this, true);
        /***找出控件*/
        inputsearchkeylayout=(RelativeLayout)  findViewById(R.id.searchinputkey);
        waitingsearchlayout=(RelativeLayout)  findViewById(R.id.searchwaitcommand);
        et_search = (EditText) findViewById(R.id.et_search);
        bt_clear = (ImageView) findViewById(R.id.bt_clear);
        et_search.addTextChangedListener(this);
        bt_clear.setOnClickListener(this);
        bt_searchicon=(ImageView) findViewById(R.id.iv_searchicon);
        bt_searchbtn=(ImageView)findViewById(R.id.iv_searchbtn);
        waitingsearchlayout.setVisibility(View.VISIBLE);
        inputsearchkeylayout.setVisibility(View.INVISIBLE);
        bt_searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                inputsearchkeylayout.setVisibility(View.VISIBLE);
                waitingsearchlayout.setVisibility(View.INVISIBLE);
                et_search.setFocusable(true);
                et_search.setFocusableInTouchMode(true);
                et_search.requestFocus();

                /*
                ActivityManager am = (ActivityManager) mContext.getSystemService(Activity.ACTIVITY_SERVICE);
                List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                ComponentName componentInfo = taskInfo.get(0).topActivity;
                Log.i("111", componentInfo.getClassName());
                */

                //show input keyboard.
                InputMethodManager imm =(InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                /*
                //关闭软键盘
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                */
            }
        });
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    /****
     * 对用户输入文字的监听
     *
     * @param editable
     */
    @Override
    public void afterTextChanged(Editable editable) {
        /**获取输入文字**/
        String input = et_search.getText().toString().trim();
        if(mListener!=null)  mListener.onChange(SEARCH_INPUT_TEXT,input);
    }

    @Override
    public void onClick(View view) {
        inputsearchkeylayout.setVisibility(View.INVISIBLE);
        waitingsearchlayout.setVisibility(View.VISIBLE);
        et_search.setText("");
    }

}
