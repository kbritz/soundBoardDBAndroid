package com.br.kbrzt.soundboarddbandroid;

import java.io.IOException;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
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
	LinearLayout splashLayout;
	String TAG = "SOUND_BOARD";
	Handler handler = new Handler();

	private SoundPool soundPool;
	private int soundIG1, soundIV1;
	private int soundIM1;
	private int soundIC1;
	private int soundIV2;
	private int soundIG2;
	private int soundIV3;
	private int soundIV4;
	private int soundIV5;
	boolean loaded = false;
	float volume;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		splashLayout = (LinearLayout) findViewById(R.id.splash);

		splashLayout.setVisibility(View.VISIBLE);

		initButtons();

		final Runnable r = new Runnable() {
			public void run() {
				splashLayout.setVisibility(View.GONE);
			}
		};
		handler.postDelayed(r, 3000);

		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		// Load the sound
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

		soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
			@Override
			public void onLoadComplete(SoundPool soundPool, int sampleId,
					int status) {
				loaded = true;
			}
		});

		loadAudios();

	}

	public void loadAudios() {
		soundIG1 = soundPool.load(this, R.raw.goku, 1);
		soundIV1 = soundPool.load(this, R.raw.maldade, 1);
		soundIM1 = soundPool.load(this, R.raw.comer, 1);
		soundIC1 = soundPool.load(this, R.raw.maldicao, 1);
		soundIV2 = soundPool.load(this, R.raw.oito_mil, 1);
		soundIG2 = soundPool.load(this, R.raw.kamehameha, 1);
		soundIV3 = soundPool.load(this, R.raw.verme_maldito, 1);
		soundIV4 = soundPool.load(this, R.raw.verme_verde, 1);
		soundIV5 = soundPool.load(this, R.raw.cafe, 1);
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

	public void setVolume() {

		AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		float actualVolume = (float) audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = (float) audioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		volume = actualVolume / maxVolume;

	}

	@Override
	protected void onStop() {
		super.onStop();

	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public void onClick(View v) {
		int ids = v.getId();

		setVolume();
		switch (ids) {
		case R.id.g1:
			soundPool.autoPause();
			if (loaded) {
				soundPool.play(soundIG1, volume, volume, 1, 0, 1f);
				Log.e("Test", "Played sound");
			}
			break;
		case R.id.v1:
			soundPool.autoPause();
			if (loaded) {
				soundPool.play(soundIV1, volume, volume, 1, 0, 1f);
				Log.e("Test", "Played sound");
			}
			break;
		case R.id.m1:
			soundPool.autoPause();
			if (loaded) {
				soundPool.play(soundIM1, volume, volume, 1, 0, 1f);
				Log.e("Test", "Played sound");
			}
			break;
		case R.id.g2:
			soundPool.autoPause();
			if (loaded) {
				soundPool.play(soundIG2, volume, volume, 1, 0, 1f);
				Log.e("Test", "Played sound");
			}
			break;
		case R.id.v2:
			soundPool.autoPause();
			if (loaded) {
				soundPool.play(soundIV2, volume, volume, 1, 0, 1f);
				Log.e("Test", "Played sound");
			}
			break;
		case R.id.c1:
			soundPool.autoPause();
			if (loaded) {
				soundPool.play(soundIC1, volume, volume, 1, 0, 1f);
				Log.e("Test", "Played sound");
			}
			break;
		case R.id.v3:
			soundPool.autoPause();
			if (loaded) {
				soundPool.play(soundIV3, volume, volume, 1, 0, 1f);
				Log.e("Test", "Played sound");
			}
			break;
		case R.id.v4:
			soundPool.autoPause();
			if (loaded) {
				soundPool.play(soundIV4, volume, volume, 1, 0, 1f);
				Log.e("Test", "Played sound");
			}
			break;
		case R.id.v5:
			soundPool.autoPause();
			if (loaded) {
				soundPool.play(soundIV5, volume, volume, 1, 0, 1f);
				Log.e("Test", "Played sound");
			}
			break;
		case R.id.splash:
			splashLayout.setVisibility(View.GONE);
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
