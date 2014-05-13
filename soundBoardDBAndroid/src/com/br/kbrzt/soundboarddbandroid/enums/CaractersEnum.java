package com.br.kbrzt.soundboarddbandroid.enums;

import com.br.kbrzt.soundboarddbandroid.R;

public enum CaractersEnum {

    GOKU(R.raw.goku, R.drawable.image_selector_goku), 
    MALDADE(R.raw.maldade, R.drawable.image_selector_maldade), 
    MALDICAO(R.raw.maldicao, R.drawable.image_selector_maldicao), 
    VERME_MALDITO(R.raw.verme_maldito, R.drawable.image_selector_verme_maldito), 
    VERME_VERDE(R.raw.verme_verde, R.drawable.image_selector_verme_verde), 
    CAFE(R.raw.cafe, R.drawable.image_selector_cafe), 
    KAMEHAMEHA(R.raw.kamehameha, R.drawable.image_selector_kamehameha), 
    COMER(R.raw.comer, R.drawable.image_selector_comer), 
    OITO_MIL(R.raw.oito_mil, R.drawable.image_selector_oito_mil);

    private int sound;
    private int image;

    public int getImage() {
	return image;
    }

    public int getSound() {
	return sound;
    }

    private CaractersEnum(final int sound, final int image) {
	this.sound = sound;
	this.image = image;
    }

}
