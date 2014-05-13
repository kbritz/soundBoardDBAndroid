/*DragonBall SoundBoard is a soundboard that plays some of dragon ball's 
 famous characters lines in brazilian portuguese
  
    Copyright (C) 2014 Kamila Brito - kbrtzdevelopment - kbrtz.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    All images and sounds used in this applications belong to TOEI ANIMATION.

Dragon Ball 2003 BIRD STUDIO/ SHUEISHA, TOEI ANIMATION. Licensed by FUNimation Productions,
Ltd. All Rights Reserved. Dragon Ball and all logos, character names and distinctive likenesses 
thereof are trademarks of TOEI ANIMATION.
 */

package com.br.kbrzt.soundboarddbandroid;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.br.kbrzt.soundboarddbandroid.enums.CaractersEnum;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;
import com.purplebrain.adbuddiz.sdk.AdBuddizDelegate;

public class MainActivity extends Activity implements OnClickListener {

	Button btnAbout;

	LinearLayout splashLayout;
	String TAG = "SOUND_BOARD";
	Handler handler = new Handler();
	MediaPlayer audio;
	OnCompletionListener completionListener;

	private GridView gridCaracters;
	boolean loaded = false;
	private boolean hasShownAd = false;
	private int adCount = 0;
	private AdBuddizDelegate delegate;
	private GridObjectAdapter gridAdapter;
	List<CaractersEnum> caracters;
	LinearLayout aboutLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		splashLayout = (LinearLayout) findViewById(R.id.splash);

		audio = new MediaPlayer();

		audio = MediaPlayer.create(this, R.raw.goku);

		splashLayout.setVisibility(View.VISIBLE);

		initComponents();

		audio.setOnErrorListener(new MediaPlayer.OnErrorListener() {

			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				mp.reset();
				return false;
			}
		});

		audio.setOnCompletionListener(completionListener);

		final Runnable r = new Runnable() {
			public void run() {
				splashLayout.setVisibility(View.GONE);
			}
		};
		handler.postDelayed(r, 3000);

		AdBuddiz.setPublisherKey(Constants.adKey);
		AdBuddiz.setTestModeActive();
		AdBuddiz.cacheAds(this); // this = current Activity

		caracters = Arrays.asList(CaractersEnum.values());

	}

	public void complete() {
		audio.setOnCompletionListener(completionListener);
	}

	public void initComponents() {

		btnAbout = (Button) findViewById(R.id.btn_about);
		btnAbout.setOnClickListener(this);

		gridCaracters = (GridView) findViewById(R.id.grid_caracters);

		gridAdapter = new GridObjectAdapter(this);
		gridCaracters.setAdapter(gridAdapter);
		gridAdapter.notifyDataSetChanged();

		gridCaracters.setOnItemClickListener(onGridItemClickListener);

		aboutLayout = (LinearLayout) findViewById(R.id.about_linear);

		splashLayout.setOnClickListener(this);

		btnAbout.setOnClickListener(this);
	}

	private final OnItemClickListener onGridItemClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(final AdapterView<?> parent, final View view,
				final int position, final long itemId) {

			final CaractersEnum carac = gridAdapter.getItem(position);
			playAudio(carac);

			adCount++;

			Log.d(TAG, "CLICA: " + adCount);
			showAd();
		}
	};

	@Override
	protected void onStop() {
		super.onStop();
		stopAudio();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (audio == null)
			audio = MediaPlayer.create(this, R.raw.goku);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		stopAudio();
	}

	@Override
	public void onClick(View v) {
		int ids = v.getId();
		switch (ids) {
		case R.id.btn_about:
			openAbout();
			break;
		case R.id.splash:
			splashLayout.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	private void openAbout() {

		Log.d(TAG, "openAbout");
		
		AlertDialog dialog;

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// Add the buttons
		
		LayoutInflater inflater = this.getLayoutInflater();
		
		builder.setView(inflater.inflate(R.layout.popup_about, null));
		builder.setNegativeButton(R.string.exit,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						if (dialog != null)
							dialog.dismiss();
					}
				});
		
		builder.setTitle(R.string.btn_about);

		// Create the AlertDialog
		dialog = builder.create();

		dialog.show();

	}

	private void playAudio(final CaractersEnum caracter) {
		stopAudio();
		audio = MediaPlayer.create(this, caracter.getSound());
		audio.start();
	}

	public void stopAudio() {
		if (audio != null) {
			if (audio.isPlaying()) {
				Log.d(TAG, "tem audio tocando! PARA!");
				audio.stop();
				audio.release();
				audio = null;
			}
		}
	}

	public void showAd() {

		Log.d(TAG, "showAd: " + adCount);
		if (adCount >= 8 && !hasShownAd) {
			hasShownAd = true;
			adCount = 0;
			AdBuddiz.showAd(this);
		}
	}

}