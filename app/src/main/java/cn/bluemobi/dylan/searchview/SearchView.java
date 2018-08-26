package cn.bluemobi.dylan.searchview;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bingkong.shadewindow.R;


/**
 * Android自定义SearchView
 * Created by yuandl on 2016-11-17.
 */

public class SearchView extends LinearLayout implements TextWatcher, View.OnClickListener {
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
    private ImageView bt_searchend;
    private ImageView bt_searchbegin;
    Context mContext;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**加载布局文件*/
        mContext=context;
        LayoutInflater.from(context).inflate(R.layout.pub_searchview, this, true);
        /***找出控件*/
        et_search = (EditText) findViewById(R.id.et_search);
        bt_clear = (ImageView) findViewById(R.id.bt_clear);
        bt_clear.setVisibility(GONE);
        et_search.addTextChangedListener(this);
        bt_clear.setOnClickListener(this);
        bt_searchend=(ImageView) findViewById(R.id.iv_searchicon);
        bt_searchbegin=(ImageView)findViewById(R.id.iv_searchbtn);
        bt_searchend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                et_search.setVisibility(View.VISIBLE);
                bt_searchbegin.setVisibility(View.VISIBLE);
                bt_clear.setVisibility(View.VISIBLE);
//                et_search.setFocusable();
                et_search.setFocusable(true);
                et_search.setFocusableInTouchMode(true);
                et_search.requestFocus();
//              SearchView.this.mContext.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
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
        if (input.isEmpty()) {
            bt_clear.setVisibility(GONE);
        } else {
            bt_clear.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        et_search.setVisibility(View.INVISIBLE);
        bt_searchbegin.setVisibility(View.VISIBLE);
        bt_clear.setVisibility(View.INVISIBLE);
        et_search.setText("");
    }

}
