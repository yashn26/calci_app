package com.example.yash.calci;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView edittext; //shows the entered value and answers
    float previous_number;   //store the number entered before the operator
    int operation=0;      //store the operator pressed
    String operation_string;  //store the operator directly
    private TextView textview;   //shows errors and on-going calculations
    private Button button_plus,button_minus,button_multiply,button_division,button_equal,button_reset,button_back;
    private Button button_one,button_two,button_three,button_four,button_five,button_six,button_seven,button_eight,button_nine,button_zero,button_dot;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup(); //links UI to the button variables
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Button_plus:
                solve("+");
                break;
            case R.id.Button_minus:
                solve("-");
                break;

            case R.id.Button_multiply:
                solve("*");
                break;
            case R.id.Button_divide:
                solve("/");
                break;

            case R.id.Button_equal:
                answer();
                break;

            case R.id.Button_back:
                String string=edittext.getText().toString();
                if (string.matches("")) {        //if there is nothing in the textbox
                    textview.setText("nothing to erase");
                    break;
                }
                StringBuilder builder = new StringBuilder(string);
                builder.deleteCharAt(string.length() - 1);    //takes text removes last element puts it back
                edittext.setText(builder.toString());
                break;
            case R.id.Button_reset:
                operation=0;
                operation_string="";
                edittext.setText("");   //resets everything
                textview.setText("calculate");
                break;

            case R.id.Button_zero:
                input(0);
                break;
            case R.id.Button_one:
                input(1);
                break;
            case R.id.Button_two:
                input(2);
                break;
            case R.id.Button_three:
                input(3);
                break;
            case R.id.Button_four:
                input(4);
                break;
            case R.id.Button_five:
                input(5);
                break;
            case R.id.Button_six:
                input(6);
                break;
            case R.id.Button_seven:
                input(7);
                break;
            case R.id.Button_eight:
                input(8);
                break;
            case R.id.Button_nine:
                input(9);
                break;
            case R.id.Button_dot:
                String string_=edittext.getText().toString()+".";
                edittext.setText(string_);
            default:
                break;
        }
    }

    private void input(int i)  {//if a number is pressed
        String string=edittext.getText().toString()+i;
        edittext.setText(string);
    }

    private void answer() {    //if equal is pressed
        String string=edittext.getText().toString();
        if (string.matches("")) {
            textview.setText("enter number");
            textview.setTextColor(Color.parseColor("#FF0000"));
            return;
        }
        if (operation==0)
        {
            textview.setText("enter operator");
            textview.setTextColor(Color.parseColor("#FF0000"));
            return;
        }
        if(check_occurence(string))   //checks occurence of decimal point
        {
            textview.setText("number incorrect");
            textview.setTextColor(Color.parseColor("#FF0000"));
            return;
        }
        float f = Float.parseFloat(edittext.getText().toString());
        textview.setText(""+previous_number+" "+operation_string+" "+f); //shows user the on going operation
        textview.setTextColor(Color.parseColor("#008000"));
        switch(operation)
        {
            case 1:
                previous_number=previous_number+f;
                break;
            case 2:
                previous_number=previous_number-f;
                break;
            case 3:
                previous_number=previous_number*f;
                break;
            case 4:
                previous_number=previous_number/f;
                break;
        }

        edittext.setText(""+previous_number); //shows the answer
        operation=0;
    }

    private void solve(String s) {    //if any operator is pressed
        String string=edittext.getText().toString();
        if (string.matches("")) {
            textview.setText(" enter number");
            textview.setTextColor(Color.parseColor("#FF0000"));
            return;
        }
        if (operation!=0) //if operator has been pressed more then 1 times without equal sign it first calculates the pending 2 numbers and the operator.
        {
            answer();
        }
        if(check_occurence(string))   //checks the occurence of string
        {
            textview.setText("number incorrect");
            textview.setTextColor(Color.parseColor("#FF0000"));
            return;
        }
        switch (s)      //stores the operator
        {
            case "+":
                operation = 1;
                operation_string="+";
                textview.setText("");
                break;
            case "-":
                operation = 2;
                operation_string="-";
                textview.setText("");
                break;
            case "*":
                operation = 3;
                operation_string="*";
                textview.setText("");
                break;
            case "/":
                operation = 4;
                operation_string="/";
                textview.setText("");
                break;
        }
        previous_number = Float.parseFloat(edittext.getText().toString());
        textview.setText(previous_number+""+operation_string);
        textview.setTextColor(Color.parseColor("#008000"));
        edittext.setText("");
    }

    private boolean check_occurence(String string) {
        if(string.equals("."))
        {
            return true;
        }
        int count=0,i;
        int str_length=string.length();
        String string_;
        for (i=0;i<str_length;i++)
        {
            string_=string.charAt(i)+"";
            if(string_.equals("."))
            {
                count++;
            }
        }
        if(count==0||count==1)
        {
            return false;
        }
        return true;
    }

    private void setup() {
        textview = (TextView) findViewById(R.id.textveiw) ;
        edittext = (TextView) findViewById(R.id.Edittext);
        button_plus=(Button) findViewById(R.id.Button_plus);
        button_minus=(Button) findViewById(R.id.Button_minus);
        button_multiply=(Button) findViewById(R.id.Button_multiply);
        button_division=(Button) findViewById(R.id.Button_divide);
        button_equal=(Button) findViewById(R.id.Button_equal);
        button_back=(Button) findViewById(R.id.Button_back);
        button_reset=(Button) findViewById(R.id.Button_reset);
        button_one=(Button) findViewById(R.id.Button_one);
        button_two=(Button) findViewById(R.id.Button_two);
        button_three=(Button) findViewById(R.id.Button_three);
        button_four=(Button) findViewById(R.id.Button_four);
        button_five=(Button) findViewById(R.id.Button_five);
        button_six=(Button) findViewById(R.id.Button_six);
        button_seven=(Button) findViewById(R.id.Button_seven);
        button_eight=(Button) findViewById(R.id.Button_eight);
        button_nine=(Button) findViewById(R.id.Button_nine);
        button_zero=(Button) findViewById(R.id.Button_zero);
        button_dot=(Button) findViewById(R.id.Button_dot);

        button_equal.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_division.setOnClickListener(this);
        button_reset.setOnClickListener(this);
        button_back.setOnClickListener(this);
        button_one.setOnClickListener(this);
        button_two.setOnClickListener(this);
        button_three.setOnClickListener(this);
        button_four.setOnClickListener(this);
        button_five.setOnClickListener(this);
        button_six.setOnClickListener(this);
        button_seven.setOnClickListener(this);
        button_eight.setOnClickListener(this);
        button_nine.setOnClickListener(this);
        button_zero.setOnClickListener(this);
        button_dot.setOnClickListener(this);
    }
}
