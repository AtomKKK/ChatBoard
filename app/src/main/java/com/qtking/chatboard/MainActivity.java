package com.qtking.chatboard;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toobar)
    Toolbar mToolbar;
    @BindView(R.id.btn_send)
    Button mButton;
    @BindView(R.id.et_msg)
    EditText mEditText;
    @BindView(R.id.rv)
    RecyclerView mRecyclerView;
    private List<Message> messageList = new ArrayList<>();
    private ChatAdapater mChatAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("猪妹");
//            actionBar.setHomeAsUpIndicator(android.R.drawable.arrow_up_float);
        }

        initMessages();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mChatAdapater = new ChatAdapater(messageList);
        mRecyclerView.setAdapter(mChatAdapater);

    }

    private void initMessages() {
        Message message1 = new Message("Hello , zhu ni mei", Message.TYPE_RECEIVED);
        messageList.add(message1);
        Message message2 = new Message("hehe , zhu ni mei", Message.TYPE_SEND);
        messageList.add(message2);
        Message message3 = new Message("where are u ", Message.TYPE_RECEIVED);
        messageList.add(message3);
    }

    @OnClick(R.id.btn_send)
    public void sendOnClick() {
        String content = mEditText.getText().toString();
        if (!"".equals(content)) {
            Message message = new Message(content, Message.TYPE_SEND);
            messageList.add(message);
            //有动画效果
            mChatAdapater.notifyItemInserted(messageList.size() - 1);
            //将listview定位到最后一行
            mRecyclerView.scrollToPosition(messageList.size() - 1);

            mEditText.setText("");
        }
    }
}
