package com.example.a85224.jsongogo;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
*   This is JSON method to android from jsp server
*   출처(original) :
 *   http://derveljunit.tistory.com/71
*    http://moozi.tistory.com/209
* */
public class MainActivity extends AppCompatActivity {
    private Button sendBtn;
    private EditText sendNameEt;
    private TextView getDataText;

    protected Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if((User)msg.obj == null){
                Toast.makeText(MainActivity.this, "데이터가 전송되지 않았습니다.", Toast.LENGTH_SHORT).show();
            }else{
                setUserInfo((User) msg.obj);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendBtn = (Button)findViewById(R.id.send_btn);
        getDataText = (TextView)findViewById(R.id.getData_tx);
        sendNameEt = (EditText)findViewById(R.id.sendName_et);


        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new userInfo(sendNameEt.getText().toString()).execute();
            }
        });
    }


    public void setUserInfo(User user){
        getDataText.setText(user.getId()+ "@" +user.getEmail()+"\n"+user.getName());
    }


    public class userInfo extends AsyncTask<String, Void, Void> {
        private  String name;

        public userInfo(String name) {
            this.name = name;
        }

        @Override
        protected Void doInBackground(String... params) {
          //  List<String> list = new ArrayList<String>();
            String rs = "";
            RequestUtil req = new RequestUtil(MainActivity.this);
            try {
                rs = req.UserInfo(name);

                User user = null;
                Log.d("****************", "" + rs);

            // 서버에서 보낸 내용: {"List":[{"NAME":"???(내가보낸것)","ID":hello,"EMAIL":"gmail.com"}]}

                    JSONObject json = new JSONObject(rs);
                    JSONArray array = json.getJSONArray("List");

                    for (int i = 0; i < array.length(); i++) {

                        JSONObject jsonObject = array.getJSONObject(i);
                        user = new User();
                        user.setId(jsonObject.getString("ID"));
                        user.setName(jsonObject.getString("NAME"));
                        user.setEmail(jsonObject.getString("EMAIL"));

                    }

                handler.sendMessage(Message.obtain(handler, 1, user));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }
    }

}