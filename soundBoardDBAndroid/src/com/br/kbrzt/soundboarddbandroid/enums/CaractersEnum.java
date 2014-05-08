package com.br.kbrzt.soundboarddbandroid.enums;

import com.br.kbrzt.soundboarddbandroid.R;

public enum CaractersEnum {

    GOKU(R.raw.goku, R.drawable.image_selector_goku, true), 
    MALDADE(R.raw.maldade, R.drawable.image_selector_maldade, true), 
    MALDICAO(R.raw.maldicao, R.drawable.image_selector_maldicao, true), 
    VERME_MALDITO(R.raw.verme_maldito, R.drawable.image_selector_verme_maldito, true), 
    VERME_VERDE(R.raw.verme_verde, R.drawable.image_selector_verme_verde, true), 
    CAFE(R.raw.cafe, R.drawable.image_selector_cafe, true), 
    KAMEHAMEHA(R.raw.kamehameha, R.drawable.image_selector_kamehameha, false), 
    COMER(R.raw.comer, R.drawable.image_selector_comer, false), 
    OITO_MIL(R.raw.oito_mil, R.drawable.image_selector_oito_mil, false);

    private int sound;
    private int image;
    private boolean isInSoundPool;


    public boolean isInSoundPool() {
	return isInSoundPool;
    }

    public int getImage() {
	return image;
    }

    public int getSound() {
	return sound;
    }

    private CaractersEnum(final int sound, final int image,
	    final boolean isInSoundPool) {
	this.sound = sound;
	this.image = image;
	this.isInSoundPool = isInSoundPool;
    }

}
