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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.br.kbrzt.soundboarddbandroid.enums.CaractersEnum;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;
import com.purplebrain.adbuddiz.sdk.AdBuddizDelegate;
import com.purplebrain.adbuddiz.sdk.AdBuddizError;

public class MainActivity extends Activity implements OnClickListener,
	AdBuddizDelegate {

    Button btnAbout;

    LinearLayout splashLayout;
    String TAG = "SOUND_BOARD";
    Handler handler = new Handler();
    MediaPlayer audio;
    OnCompletionListener completionListener;

    private SoundPool soundPool;
    private int soundIG1, soundIV1;
    private int soundIC1;
    private int soundIV3;
    private int soundIV4;
    private int soundIV5;
    
    private List<Integer> sound;
    private GridView gridCaracters;
    boolean loaded = false;
    float volume;
    private boolean hasShownAdd = false;
    private AdBuddizDelegate delegate;
    private GridObjectAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.new_activity_main);

	splashLayout = (LinearLayout) findViewById(R.id.splash);

	audio = new MediaPlayer();

	audio = MediaPlayer.create(this, R.raw.goku);

	splashLayout.setVisibility(View.VISIBLE);

	initComponents();

	audio.setOnErrorListener(new MediaPlayer.OnErrorListener() {

	    @Override
	    public boolean onError(MediaPlayer mp, int what, int extra) {
		mp.reset();
		Log.d(TAG, "aconteceu um erro");
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

	AdBuddiz.setPublisherKey("93152f5e-f83a-4e42-9fed-d428c5ca9355");
	AdBuddiz.setTestModeActive();
	AdBuddiz.setDelegate(delegate);
	AdBuddiz.cacheAds(this); // this = current Activity

	loadAudios();

    }

    public void complete() {
	audio.setOnCompletionListener(completionListener);
    }

    public void loadAudios() {
	
	List<CaractersEnum> caracters = Arrays.asList(CaractersEnum.values());
	
	for (CaractersEnum carac : caracters) {
	    if (carac.isInSoundPool()) {
		sound.add(soundPool.load(this, carac.getSound(), 1));
	    }
	}

//	soundIG1 = soundPool.load(this, R.raw.goku, 1);
//	soundIV1 = soundPool.load(this, R.raw.maldade, 1);
//	soundIC1 = soundPool.load(this, R.raw.maldicao, 1);
//	soundIV3 = soundPool.load(this, R.raw.verme_maldito, 1);
//	soundIV4 = soundPool.load(this, R.raw.verme_verde, 1);
//	soundIV5 = soundPool.load(this, R.raw.cafe, 1);
    }

    public void initComponents() {

	btnAbout = (Button) findViewById(R.id.btn_about);
	btnAbout.setOnClickListener(this);

	gridCaracters = (GridView) findViewById(R.id.grid_caracters);
	
	gridAdapter = new GridObjectAdapter(this);
	gridCaracters.setAdapter(gridAdapter);
	gridAdapter.notifyDataSetChanged();
	
	gridCaracters.setOnItemClickListener(onGridItemClickListener);

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

    private final OnItemClickListener onGridItemClickListener = new OnItemClickListener() {

	@Override
	public void onItemClick(final AdapterView<?> parent, final View view,
		final int position, final long itemId) {
	    
	    Log.d("play audio", "OnItemClickListener");

	    final CaractersEnum carac = gridAdapter.getItem(position);
	    playAudio(carac);
	}
    };

    @Override
    protected void onStop() {
	super.onStop();
	soundPool.autoPause();

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
	soundPool.autoPause();

	if (audio == null)
	    audio = MediaPlayer.create(this, R.raw.goku);

    }

    @Override
    protected void onPause() {
	// TODO Auto-generated method stub
	super.onPause();
	soundPool.autoPause();
    }

    public MediaPlayer loadMediaPlayer(String audioName) {

	if (audioName.equals("comer")) {
	    audio = MediaPlayer.create(this, R.raw.comer);
	} else if (audioName.equals("kamehameha")) {
	    audio = MediaPlayer.create(this, R.raw.kamehameha);
	} else {
	    audio = MediaPlayer.create(this, R.raw.oito_mil);
	}

	return audio;
    }

    public void playMediaPlayer(String audioName) {

	if (audio != null) {
	    if (!audio.isPlaying()) {
		loadMediaPlayer(audioName).start();
	    } else {
		Log.d(TAG, "JA TA TOCANDO");
		audio.stop();
		loadMediaPlayer(audioName).start();
	    }
	}
    }

    @Override
    public void onClick(View v) {
	int ids = v.getId();

	setVolume();
	switch (ids) {
	case R.id.btn_about:
	    soundPool.autoPause();
	    if (audio != null) {
		if (audio.isPlaying()) {
		    audio.stop();
		}
	    }
	    if (loaded) {
		soundPool.play(soundIG1, volume, volume, 1, 0, 1f);
		Log.e("Test", "Played sound");
	    }
	    break;
	case R.id.v1:
	    soundPool.autoPause();
	    if (audio != null) {
		if (audio.isPlaying()) {
		    audio.stop();
		}
	    }
	    if (loaded) {
		soundPool.play(soundIV1, volume, volume, 1, 0, 1f);
		Log.e("Test", "Played sound");
	    }
	    break;
	case R.id.m1:
	    soundPool.autoPause();
	    if (!hasShownAdd) {
		AdBuddiz.showAd(this);
	    }
	    playMediaPlayer("comer");
	    break;
	case R.id.g2:
	    soundPool.autoPause();
	    playMediaPlayer("kamehameha");
	    break;
	case R.id.v2:
	    soundPool.autoPause();
	    playMediaPlayer("oito_mil");
	    break;
	case R.id.c1:
	    soundPool.autoPause();
	    if (audio != null) {
		if (audio.isPlaying()) {
		    audio.stop();
		}
	    }
	    if (loaded) {
		soundPool.play(soundIC1, volume, volume, 1, 0, 1f);
		Log.e("Test", "Played sound");
	    }
	    break;
	case R.id.v3:
	    soundPool.autoPause();
	    if (audio != null) {
		if (audio.isPlaying()) {
		    audio.stop();
		}
	    }
	    if (loaded) {
		soundPool.play(soundIV3, volume, volume, 1, 0, 1f);
		Log.e("Test", "Played sound");
	    }
	    break;
	case R.id.v4:
	    soundPool.autoPause();
	    if (audio != null) {
		if (audio.isPlaying()) {
		    audio.stop();
		}
	    }
	    if (loaded) {
		soundPool.play(soundIV4, volume, volume, 1, 0, 1f);
		Log.e("Test", "Played sound");
	    }
	    break;
	case R.id.v5:
	    soundPool.autoPause();
	    if (audio != null) {
		if (audio.isPlaying()) {
		    audio.stop();
		}
	    }
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

    private void playAudio(final CaractersEnum caracter) {

	if (caracter.isInSoundPool()) {
	    soundPool.autoPause();
	    if (audio != null) {
		if (audio.isPlaying()) {
		    audio.stop();
		}
	    }
	    if (loaded) {
		soundPool.play(soundIV5, volume, volume, 1, 0, 1f);
		Log.e("Test", "Played sound");
	    }
	    
	} else {
	    
	}

    }

    @Override
    public void didCacheAd() {
	// TODO Auto-generated method stub

    }

    @Override
    public void didClick() {
	// TODO Auto-generated method stub

    }

    @Override
    public void didFailToShowAd(AdBuddizError arg0) {
	// TODO Auto-generated method stub

    }

    @Override
    public void didHideAd() {
	// TODO Auto-generated method stub

    }

    @Override
    public void didShowAd() {
	hasShownAdd = true;
    }

}
