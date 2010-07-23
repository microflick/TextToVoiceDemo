package com.zakainsworth.TextToVoiceDemo;

import java.io.IOException;
import java.net.URLEncoder;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.media.MediaPlayer;

public class TextToVoiceDemo extends Activity {

	private Button speakBtn;
	private EditText inputText;
	private MediaPlayer mPlayer = new MediaPlayer();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		inputText = (EditText) findViewById(R.id.inputText);
		speakBtn = (Button) findViewById(R.id.speakBtn);

		speakBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (inputText.getText().toString().length() != 0) {
					speakText(inputText.getText().toString());
				} else {
					speakText("Please tell me what to say.");
				}
			}
		});

		mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mPlayer) {
				mPlayer.reset();
			}
		});
	}

	public void speakText(String text) {
		try {
			mPlayer.setDataSource("http://translate.google.com/translate_tts?tl=en&q="+URLEncoder.encode(text));
			mPlayer.prepare();
			mPlayer.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}