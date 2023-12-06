package tr.com.mustafagordesli.bm_vize;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

public class ConverterActivity extends AppCompatActivity {

    String selectedDecimal, selectedByte;
    Button btnResult;
    EditText inputDecimal, inputByte, inputCelcius;
    TextView txtResultDecimal, txtResultByte, txtResultCelcius;
    Spinner spinnerByte, spinnerDecimal;
    RadioButton ckbFah, ckbKel;
    private final String[] numbers = { "2", "8", "16"};
    private final String[] bytes = {"kilobyte","byte","kibibit","bit"};
    private ArrayAdapter<String> adapterByte, adapterDecimal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        viewSettings();
    }

    private void viewSettings() {
        btnResult = (Button) findViewById(R.id.btnResult);
        inputByte = (EditText) findViewById(R.id.inputByte);
        inputCelcius = (EditText) findViewById(R.id.inputCelcius);
        inputDecimal = (EditText) findViewById(R.id.inputDecimal);
        spinnerByte = (Spinner) findViewById(R.id.spinnerByte);
        spinnerDecimal = (Spinner) findViewById(R.id.spinnerDecimal);
        txtResultCelcius = (TextView) findViewById(R.id.txtResultCelcius);
        txtResultByte = (TextView) findViewById(R.id.txtByte);
        txtResultDecimal = (TextView) findViewById(R.id.txtResultDecimal);
        ckbFah = (RadioButton) findViewById(R.id.rdtFah);
        ckbKel = (RadioButton) findViewById(R.id.rdbKelvin);

        adapterByte = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bytes);
        adapterDecimal = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, numbers);

        adapterByte.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapterDecimal.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerByte.setAdapter(adapterByte);
        spinnerDecimal.setAdapter(adapterDecimal);

        spinnerDecimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedDecimal = spinnerDecimal.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerByte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedByte = spinnerByte.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert_decimal();
                convert_byte();
                convert_celcius();
            }
        });
    }

    private void convert_decimal() {
        try {
            double value = Float.parseFloat(inputDecimal.getText().toString());
            if(selectedDecimal == null) {
                Toast toast = Toast.makeText(ConverterActivity.this, "Deicimal spinner seçilmedi", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            String result = "Result : ";
            int base = Integer.parseInt(selectedDecimal);
            long integerPart = (long) value;
            result += Long.toString(integerPart, base);

            if (value % 1 != 0) {
                result += ".";
                double fractionalPart = value % 1;
                for (int i = 0; i < 10; i++) {
                    fractionalPart *= base;
                    int digit = (int) fractionalPart;
                    result += Integer.toString(digit, base);
                    fractionalPart -= digit;
                }
            }
            txtResultDecimal.setText(result);
        } catch (Exception e) {
            Toast toast = Toast.makeText(ConverterActivity.this, "Decimal dönüştürmede hata oluştu", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void convert_byte() {
        try {
            String result = "Result : ";
            int value = Integer.parseInt(inputByte.getText().toString());
            switch (selectedByte) {
                case "kilobyte":
                    result += value * 1024;
                    break;
                case "byte":
                    result += value * 1024 * 1024;
                    break;
                case "kibibit":
                    result += value * 8 * 1024;
                    break;
                case "bit":
                    result += value * 8 * 1024 * 1024;
                    break;
            }
            txtResultByte.setText(result);
        } catch (Exception e) {
            Toast toast = Toast.makeText(ConverterActivity.this, "Byte dönüştürmede hata oluştu", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void convert_celcius() {
        try {
            String result = "Result : ";
            Double celcius = Double.parseDouble(inputCelcius.getText().toString());
            if(ckbKel.isChecked()) {
                result += celcius + 273.15;
            } else if(ckbFah.isChecked()) {
                result += (celcius * 9 / 5) + 32;
            }
            txtResultCelcius.setText(result);
        } catch (Exception e) {
            Toast toast = Toast.makeText(ConverterActivity.this, "Celcius dönüştürmede hata oluştu", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}