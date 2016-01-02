package com.jolpai.spannablestring;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=(TextView)findViewById(R.id.spannable);
        String regix=null;

        String arabic="خَتَمَ اللَّـهُ عَلَىٰ قُلُوبِهِمْ وَعَلَىٰ سَمْعِهِمْ ۖ وَعَلَىٰ أَبْصَارِهِمْ غِشَاوَةٌ ۖ وَلَهُمْ عَذَابٌ عَظِيمٌ ";
        String english="My Name 23 4 #s Tanim #2 Reja. 423432 .Thank #3 you";//"[+خ ۖ#|]"
        try {
            byte[] utf8Bytes = "[+خ ۖ#|]".getBytes("UTF-8");
            regix= new String(utf8Bytes,"UTF-8");
        }catch(Exception e){

        }



        SpannableString spannableString =new SpannableString(english);
      //  Matcher matcher = Pattern.compile("([0-9_-]+)").matcher(spannableString);
       // Matcher matcher = Pattern.compile("[+خ ۖ#|]").matcher(spannableString);
        Matcher matcher = Pattern.compile("["+ regix +"]").matcher(spannableString);

        while (matcher.find()){


            final String tag = matcher.group(0);
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View textView) {
                    Log.e("click", "click " + tag);
                    Toast.makeText(MainActivity.this,"clicked : "+tag,Toast.LENGTH_SHORT).show();

                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setColor(Color.RED);
                   // ds.setTextSize(30);
                    ds.setUnderlineText(false);
                    ds.setSubpixelText(true);

                }
            };
            spannableString.setSpan(clickableSpan, matcher.start(), matcher.end(), Spanned.SPAN_MARK_POINT);



            //spannableString.setSpan(span1,new ForegroundColorSpan(Color.parseColor("#F44336")), matcher.start(), matcher.end(), 0);

        }

        text.setMovementMethod(LinkMovementMethod.getInstance());
        text.setText(spannableString);
        text.setTextSize(30);
        text.setTextColor(Color.DKGRAY);
    }
}
