package com.example.chen.calculation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_0;
    private Button bt_1;
    private Button bt_2;
    private Button bt_3;
    private Button bt_4;
    private Button bt_5;
    private Button bt_6;
    private Button bt_7;
    private Button bt_8;
    private Button bt_9;
    private Button bt_point;
    private Button bt_add;
    private Button bt_minus;
    private Button bt_multiply;
    private Button bt_divide;
    private Button bt_eual;
    private Button bt_c;
    private Button bt_del;

    private EditText et_input;
    boolean clear_flag;//清空标识


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_0 = (Button) findViewById(R.id.button_0);
        bt_1 = (Button) findViewById(R.id.button_1);
        bt_2 = (Button) findViewById(R.id.button_2);
        bt_3 = (Button) findViewById(R.id.button_3);
        bt_4 = (Button) findViewById(R.id.button_4);
        bt_5 = (Button) findViewById(R.id.button_5);
        bt_6 = (Button) findViewById(R.id.button_6);
        bt_7 = (Button) findViewById(R.id.button_7);
        bt_8 = (Button) findViewById(R.id.button_8);
        bt_9 = (Button) findViewById(R.id.button_9);
        bt_point = (Button) findViewById(R.id.button_point);
        bt_add = (Button) findViewById(R.id.button_app);
        bt_minus = (Button) findViewById(R.id.button_minus);
        bt_multiply = (Button) findViewById(R.id.button_multiply);
        bt_divide = (Button) findViewById(R.id.button_divide);
        bt_c = (Button) findViewById(R.id.button_clear);
        bt_del = (Button) findViewById(R.id.button_del);
        bt_eual = (Button) findViewById(R.id.button_euql);

        et_input = (EditText) findViewById(R.id.et_input);


        bt_0.setOnClickListener(this);
        bt_1.setOnClickListener(this);
        bt_2.setOnClickListener(this);
        bt_3.setOnClickListener(this);
        bt_4.setOnClickListener(this);
        bt_5.setOnClickListener(this);
        bt_6.setOnClickListener(this);
        bt_7.setOnClickListener(this);
        bt_8.setOnClickListener(this);
        bt_9.setOnClickListener(this);
        bt_point.setOnClickListener(this);
        bt_add.setOnClickListener(this);
        bt_minus.setOnClickListener(this);
        bt_multiply.setOnClickListener(this);
        bt_divide.setOnClickListener(this);
        bt_c.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_eual.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String str = et_input.getText().toString();
        switch (v.getId()) {
            case R.id.button_0:
            case R.id.button_1:
            case R.id.button_2:
            case R.id.button_3:
            case R.id.button_4:
            case R.id.button_5:
            case R.id.button_6:
            case R.id.button_7:
            case R.id.button_8:
            case R.id.button_9:
            case R.id.button_point:
                if (clear_flag){
                    clear_flag = false;
                    str="";
                    et_input.setText("");
                }
                et_input.setText(str + ((Button) v).getText());
                break;
            case R.id.button_app:
            case R.id.button_minus:
            case R.id.button_multiply:
            case R.id.button_divide:
                if (clear_flag){
                    clear_flag = false;
                    et_input.setText("");
                    str="";
                }
                et_input.setText(str + " " + ((Button) v).getText() + " ");
                break;
            case R.id.button_clear:
                clear_flag = false;
                str="";
                et_input.setText("");
                break;
            case R.id.button_del:
                if (clear_flag){
                    clear_flag = false;
                    et_input.setText("");
                    str="";
                }else if (str != null && !str.equals("")) {
                    et_input.setText(str.substring(0, str.length() - 1));
                }
                break;
            case R.id.button_euql:
                getResult();
                break;

        }
    }
//运算结果的函数
    private void getResult() {
        String exp = et_input.getText().toString();

        if (exp == null || exp.equals(""))//什么都没有输入的时候
            return;
        if (!exp.contains(" "))//没有输入运算符的时候
            return;
        if(clear_flag){
            clear_flag = false;
            return;
        }

        clear_flag = true;
        double result = 0.0;
        String s1 = exp.substring(0, exp.indexOf(" "));
        String op = exp.substring(exp.indexOf(" ") + 1, exp.indexOf(" ") + 2);
        String s2 = exp.substring(exp.indexOf(" ") + 3);

        //第一种情况
        if (!s1.equals("") && !s2.equals("")) {
            double d1 = Double.parseDouble(s1);  //这两个东西设为全局直接就没有结果了
            double d2 = Double.parseDouble(s2);

            if (op.equals("+")) {
                result = d1 + d2;
            } else if (op.equals("-")) {
                result = d1 - d2;
            } else if (op.equals("*")) {
                result = d1 * d2;
            } else if (op.equals("/")) {
                if (d2 == 0)
                    result = 0;
                else
                    result = d1 / d2;
            }
            if (!s1.contains(".") && !s2.contains(".")&&!op.equals("/")) {
                int r = (int) result;
                et_input.setText(r + "");
            } else {
                et_input.setText(result + "");
            }
        }




            //第二种情况
            else if (!s1.equals("") && s2.equals(""))
                et_input.setText(exp);

            //第三种情况
            else if (s1.equals("") && !s2.equals("")) {
                //第一个数是0,第二个数有输入,将前面的运算过程中的d1改成0即可
                double d2 = Double.parseDouble(s2);
                if (op.equals("+")) {
                    result = 0 + d2;
                } else if (op.equals("-")) {
                    result = 0 - d2;
                } else if (op.equals("*")) {
                    result = 0 * d2;
                } else if (op.equals("/")) {
                    result = 0;
                }
                if (!s1.contains(".") && !s2.contains(".")) {
                    int r = (int) result;
                    et_input.setText(r + "");
                } else {
                    et_input.setText(result + "");
                }
            }


            else{//第四种情况
                et_input.setText("");
            }


    }
}