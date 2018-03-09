package cn.raoxianhua.snailexpress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by raoxianhua on 2018/1/12.
 */

public class RegisterActivity extends AppCompatActivity {

    private TextView uname;
    private TextView pwd;
    private TextView cpwd;
    private TextView name;
    private TextView tel;
    private TextView email;
    private Button login1;
    private Button register1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       uname=(TextView)findViewById(R.id.uname);

       pwd=(TextView)findViewById(R.id.pwd);

       cpwd=(TextView)findViewById(R.id.cpwd);

       name=(TextView)findViewById(R.id.name);

       tel=(TextView)findViewById(R.id.tel);

       email=(TextView)findViewById(R.id.email);

       login1 =(Button)findViewById(R.id.login1);

       register1 =(Button)findViewById(R.id.register1);

       login1.setOnClickListener(new View.OnClickListener() {

           @Override

           public void onClick(View v) {
               Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
               startActivity(intent);
               finish();
           }

       });

       register1.setOnClickListener(new View.OnClickListener() {

           @Override

           public void onClick(View v) {
               if (!pwd.equals(cpwd)){
                   Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
               }
               if (true) {
                   Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                   startActivity(intent);
                   Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                   finish();
               }
               else {
                   Toast.makeText(RegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
               }
           }

       });
    }

}
