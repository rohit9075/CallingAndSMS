package rohit.com.callingandsms;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonCall, mButtonSMS;
    private EditText mEditTextMobileNO, mEditTextSMS, mEditTextSMSMObile;
    public String  smsMObileno, SMSText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        ref();


    }

    public void init() {
        mButtonCall = (Button) findViewById(R.id.button_call);
        mButtonSMS = (Button) findViewById(R.id.button_sms);
        mEditTextMobileNO = (EditText) findViewById(R.id.editText_mobile_no);
        mEditTextSMS = (EditText) findViewById(R.id.editText_sms);
        mEditTextSMSMObile = (EditText)findViewById(R.id.editText_sms_number);
    }

    public void ref() {
        mButtonSMS.setOnClickListener(this);
        mButtonCall.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_call) {
            phoneCall();
        } else if (v.getId() == R.id.button_sms) {
            sms();
        }

    }


    public void phoneCall() {

        try {

            String uri = "tel:" +mEditTextMobileNO.getText().toString();;
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
            startActivity(dialIntent);

        } catch (Exception e) {

            Toast.makeText(getApplicationContext(), "Your call has failed...",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();

        }
    }



    public void sms() {
       SMSText = mEditTextSMS.getText().toString();
       smsMObileno = mEditTextSMSMObile.getText().toString();

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

        //Get the SmsManager instance and call the sendTextMessage method to send message
        SmsManager sms=SmsManager.getDefault();
        sms.sendTextMessage(smsMObileno, null, SMSText, pi,null);

        Toast.makeText(getApplicationContext(), "Message Sent successfully!",
                Toast.LENGTH_LONG).show();
    }

}


