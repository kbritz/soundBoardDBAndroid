package com.br.kbrzt.soundboarddbandroid;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	ImageButton btn_g1, btn_v1, btn_m1, btn_g2, btn_v2, btn_c1, btn_v3, btn_v4,
			btn_v5;
	OnClickListener g1OnClickListerner, v1OnClickListerner, m1OnClickListerner,
			g2OnClickListerner, v2OnClickListerner, c1OnClickListerner,
			v3OnClickListerner, v4OnClickListerner, v5OnClickListerner;

	MediaPlayer audio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initButtons();
		clicks();

	}

	public MediaPlayer getAudio(String name) {

		audio = MediaPlayer.create(this, R.raw.goku);

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

		btn_g1.setOnClickListener(g1OnClickListerner);
		btn_m1.setOnClickListener(m1OnClickListerner);
		btn_v1.setOnClickListener(v1OnClickListerner);
		btn_g2.setOnClickListener(g2OnClickListerner);
		btn_c1.setOnClickListener(c1OnClickListerner);
		btn_v2.setOnClickListener(v2OnClickListerner);
		btn_v3.setOnClickListener(v3OnClickListerner);
		btn_v4.setOnClickListener(v4OnClickListerner);
		btn_v5.setOnClickListener(v5OnClickListerner);
	}

	public void clicks() {
		btn_g1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if (getAudio(constants.G1).isPlaying()) {						
					Log.d("click botao", "audio esta tocando");
				} else {
					getAudio(constants.G1).start();
					Log.d("click botao", "audio NAO esta tocando");
				}
			}
		});

		btn_v1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MediaPlayer sound = getAudio(constants.V1);
				if (!audio.isPlaying())
					sound.start();

			}
		});

		btn_m1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MediaPlayer sound = getAudio(constants.M1);
				sound.start();

			}
		});

		btn_c1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MediaPlayer sound = getAudio(constants.C1);
				sound.start();

			}
		});

		btn_g2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MediaPlayer sound = getAudio(constants.G2);
				sound.start();

			}
		});

		btn_v2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MediaPlayer sound = getAudio(constants.V2);
				sound.start();

			}
		});
		btn_v3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MediaPlayer sound = getAudio(constants.V3);
				sound.start();

			}
		});
		btn_v4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MediaPlayer sound = getAudio(constants.V4);
				sound.start();

			}
		});
		btn_v5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MediaPlayer sound = getAudio(constants.V5);
				sound.start();

			}
		});

	}
}
