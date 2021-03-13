package in.cipherschools.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            btnPlus, btnMinus, btnMul, btnDiv,
            btnDot, btnEqual, btnClear, btnBackspace;
    // TextView textView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);
        btnPlus = findViewById(R.id.buttonPlus);
        btnMinus = findViewById(R.id.buttonSub);
        btnMul = findViewById(R.id.buttonMul);
        btnDiv = findViewById(R.id.buttonDiv);
        btnDot = findViewById(R.id.buttonDot);
        btnEqual = findViewById(R.id.buttonEqual);
        btnClear = findViewById(R.id.buttonClear);
        btnBackspace = findViewById(R.id.buttonBack);
        // textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMul.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnBackspace.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String string = editText.getText().toString();
        char lastCharacter = '0';
        if(!string.equals("")) {
            lastCharacter = string.charAt(string.length()-1);
        }
        if(view.getId() == R.id.button0) {
            if(!string.equals("")) {
                if (!string.equals("0")) {
                    if (!lastCharacterValidity(lastCharacter)) {
                        editText.setText(string.concat("0"));
                    } else {
                        int index = string.length() - 1;
                        String zerosCount = "";
                        while (index > -1) {
                            char c = string.charAt(index);
                            if (c != '+' && c != '-' && c != '*' && c != '/') {
                                if(c == '.') {
                                    editText.setText(string.concat("0"));
                                    index = -1;
                                } else {
                                    zerosCount += "0";
                                }
                            } else {
                                if(!string.substring(index+1).equals(zerosCount)) {
                                    editText.setText(string.concat("0"));
                                }
                            }
                            index--;
                        }
                    }
                    // editText.setText(string.concat("0"));
                }
            }
        } else if(view.getId() == R.id.button1) {
            editText.setText(string.concat("1"));
        } else if(view.getId() == R.id.button2) {
            editText.setText(string.concat("2"));
        } else if(view.getId() == R.id.button3) {
            editText.setText(string.concat("3"));
        } else if(view.getId() == R.id.button4) {
            editText.setText(string.concat("4"));
        } else if(view.getId() == R.id.button5) {
            editText.setText(string.concat("5"));
        } else if(view.getId() == R.id.button6) {
            editText.setText(string.concat("6"));
        } else if(view.getId() == R.id.button7) {
            editText.setText(string.concat("7"));
        } else if(view.getId() == R.id.button8) {
            editText.setText(string.concat("8"));
        } else if(view.getId() == R.id.button9) {
            editText.setText(string.concat("9"));
        } else if(view.getId() == R.id.buttonPlus) {
            if(string.equals("")) {
                editText.setText("0+");
            } else if(lastCharacterValidity(lastCharacter)) {
                editText.setText(string.concat("+"));
            }
            // editText.setText(editText.getText() + "+");
        } else if(view.getId() == R.id.buttonSub) {
            if(string.equals("")) {
                editText.setText("0-");
            } else if(lastCharacterValidity(lastCharacter)) {
                editText.setText(string.concat("-"));
            }
            // editText.setText(editText.getText() + "-");
        } else if(view.getId() == R.id.buttonMul) {
            if(string.equals("")) {
                editText.setText("0*");
            } else if(lastCharacterValidity(lastCharacter)) {
                editText.setText(string.concat("*"));
            }
            // editText.setText(editText.getText() + "*");
        } else if(view.getId() == R.id.buttonDiv) {
            if(string.equals("")) {
                editText.setText("0/");
            } else if(lastCharacterValidity(lastCharacter)) {
                editText.setText(string.concat("/"));
            }
            // editText.setText(editText.getText() + "/");
        } else if(view.getId() == R.id.buttonDot) {
            if(string.equals("")) {
                editText.setText("0.");
            } else if(lastCharacterValidity(lastCharacter)){
                int index = string.length() - 1;
                while(index > -1) {
                    if(!lastCharacterValidity(string.charAt(index))) {
                        if(!string.substring(index).contains(".")) {
                            editText.setText(string.concat("."));
                            index = -1;
                        }
                    }
                    index--;
                }
            }
            // editText.setText(editText.getText() + ".");
        } else if(view.getId() == R.id.buttonEqual) {
            if(!string.equals("")) {
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
                double result = 0;
                try{
                    result = (double) engine.eval(string);
                    editText.setText("");
                    editText.setText(String.valueOf(result));
                }catch(Exception e) {
                    Toast.makeText(this, "Exception Raised", Toast.LENGTH_SHORT).show();
                    editText.setText("");
                }
            }
        } else if(view.getId() == R.id.buttonClear) {
            editText.setText("");
        } else if(view.getId() == R.id.buttonBack) {
            if (!string.equals("")) {
                // String string = editText.getText().toString();
                if(string.length() > 0) {
                    string = string.substring(0, string.length() - 1);
                    editText.setText(string);
                }
            }
        }
    }
    public boolean lastCharacterValidity(char lastCharacter) {
        return (lastCharacter != '+' && lastCharacter != '-' && lastCharacter != '*' && lastCharacter != '/' && lastCharacter != '.');
    }
}