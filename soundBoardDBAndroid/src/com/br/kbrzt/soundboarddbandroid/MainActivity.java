package com.br.kbrzt.soundboarddbandroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	ImageButton btn_g1, btn_v1, btn_m1, btn_g2, btn_v2, btn_c1, btn_v3, btn_v4,
			btn_v5;
	OnClickListener g1OnClickListerner, v1OnClickListerner, m1OnClickListerner,
			g2OnClickListerner, v2OnClickListerner, c1OnClickListerner,
			v3OnClickListerner, v4OnClickListerner, v5OnClickListerner;
	OnCompletionListener completionListener;
	final long startTime = 30 * 1000;
	final long interval = 1 * 1000;
	LinearLayout splashLayout;
	MediaPlayer audio;
	String TAG = "SOUND_BOARD";
	int PLAY_LIMIT = 5;
	Handler handler = new Handler();

	int g1Count = 0;
	int v1Count = 0;
	int m1Count = 0;
	int g2Count = 0;
	int v2Count = 0;
	int c1Count = 0;
	int v3Count = 0;
	int v4Count = 0;
	int v5Count = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		splashLayout = (LinearLayout) findViewById(R.id.splash);

		audio = new MediaPlayer();

		audio = MediaPlayer.create(this, R.raw.goku);

		splashLayout.setVisibility(View.VISIBLE);

		audio.setOnErrorListener(new MediaPlayer.OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				mp.reset();
				Log.d(TAG, "aconteceu um erro");
				return false;
			}
		});
		audio.setOnCompletionListener(completionListener);

		initButtons();

		final Runnable r = new Runnable() {
			public void run() {
				splashLayout.setVisibility(View.GONE);
			}
		};
		handler.postDelayed(r, 3000);

	}

	public void complete() {
		audio.setOnCompletionListener(completionListener);
	}

	public MediaPlayer getAudio(String name) {

		if (name.equals(constants.G1)) {
			audio = MediaPlayer.create(this, R.raw.goku);
		} else if (name.equals(constants.V1)) {
			audio = MediaPlayer.create(this, R.raw.maldade);
		} else if (name.equals(constants.M1)) {
			audio = MediaPlayer.create(this, R.raw.comer);
		} else if (name.equals(constants.C1)) {
			audio = MediaPlayer.create(this, R.raw.maldicao);
		} else if (name.equals(constants.V2)) {
			audio = MediaPlayer.create(this, R.raw.oito_mil);
		} else if (name.equals(constants.G2)) {
			audio = MediaPlayer.create(this, R.raw.kamehameha);
		} else if (name.equals(constants.V3)) {
			audio = MediaPlayer.create(this, R.raw.verme_maldito);
		} else if (name.equals(constants.V4)) {
			audio = MediaPlayer.create(this, R.raw.verme_verde);
		} else if (name.equals(constants.V5)) {
			audio = MediaPlayer.create(this, R.raw.cafe);
		}

		return audio;
	}

	public void initButtons() {

		btn_g1 = (ImageButton) findViewById(R.id.g1);
		btn_v1 = (ImageButton) findViewById(R.id.v1);
		btn_m1 = (ImageButton) findViewById(R.id.m1);
		btn_g2 = (ImageButton) findViewById(R.id.g2);
		btn_v2 = (ImageButton) findViewById(R.id.v2);
		btn_c1 = (ImageButton) findViewById(R.id.c1);
		btn_v3 = (ImageButton) findViewById(R.id.v3);
		btn_v4 = (ImageButton) findViewById(R.id.v4);
		btn_v5 = (ImageButton) findViewById(R.id.v5);

		btn_g1.setOnClickListener(this);
		btn_m1.setOnClickListener(this);
		btn_v1.setOnClickListener(this);
		btn_g2.setOnClickListener(this);
		btn_c1.setOnClickListener(this);
		btn_v2.setOnClickListener(this);
		btn_v3.setOnClickListener(this);
		btn_v4.setOnClickListener(this);
		btn_v5.setOnClickListener(this);

		splashLayout.setOnClickListener(this);
	}

	public void playAudio(String audioToStart, int count) {

		if (count < PLAY_LIMIT) {
			if (audio != null) {
				if (!audio.isPlaying()) {
					getAudio(audioToStart).release();
					try {
						getAudio(audioToStart).prepare();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					getAudio(audioToStart).start();
				} else {
					audio.stop();
					getAudio(audioToStart).start();
				}
			}
		} else {
			showToast();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();

		if (audio != null) {
			if (audio.isPlaying()) {
				Log.d(TAG, "tem audio tocando! PARA!");
				audio.stop();
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (audio == null)
			audio = MediaPlayer.create(this, R.raw.goku);

	}

	@Override
	public void onClick(View v) {
		int ids = v.getId();

		switch (ids) {
		case R.id.g1:
			clickCounts(R.id.g1);
			playAudio(constants.G1, g1Count);
			break;
		case R.id.v1:
			clickCounts(R.id.v1);
			playAudio(constants.V1, v1Count);
			break;
		case R.id.m1:
			clickCounts(R.id.m1);
			playAudio(constants.M1, m1Count);
			break;
		case R.id.g2:
			clickCounts(R.id.g2);
			playAudio(constants.G2, g2Count);
			break;
		case R.id.v2:
			clickCounts(R.id.v2);
			playAudio(constants.V2, v2Count);
			break;
		case R.id.c1:
			clickCounts(R.id.c1);
			playAudio(constants.C1, c1Count);
			break;
		case R.id.v3:
			clickCounts(R.id.v3);
			playAudio(constants.V3, v3Count);
			break;
		case R.id.v4:
			clickCounts(R.id.v4);
			playAudio(constants.V4, v4Count);
			break;
		case R.id.v5:
			clickCounts(R.id.v5);
			playAudio(constants.V5, v5Count);
			break;
		case R.id.splash:
			splashLayout.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	public void clickCounts(int id) {
		switch (id) {
		case R.id.g1:
			g1Count = g1Count + 1;
			Log.d(TAG, "contador: " + g1Count);
			break;
		case R.id.v1:
			v1Count = v1Count + 1;
			break;
		case R.id.m1:
			m1Count = m1Count + 1;
			break;
		case R.id.g2:
			g2Count = g2Count + 1;
			break;
		case R.id.v2:
			v2Count = v2Count + 1;
			break;
		case R.id.c1:
			c1Count = c1Count + 1;
			break;
		case R.id.v3:
			v3Count = v3Count + 1;
			break;
		case R.id.v4:
			v4Count = v4Count + 1;
			break;
		case R.id.v5:
			v5Count = v5Count + 1;
			break;

		default:
			break;
		}

	}

	public void showToast() {
		Toast.makeText(getApplicationContext(), "Aguarde momento",
				Toast.LENGTH_LONG).show();
	}

}
