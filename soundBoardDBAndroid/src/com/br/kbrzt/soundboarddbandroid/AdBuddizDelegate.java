package com.br.kbrzt.soundboarddbandroid;

import com.purplebrain.adbuddiz.sdk.AdBuddizError;

public interface AdBuddizDelegate {
    public void didCacheAd();  // an Ad was cached
    public void didShowAd();   // an Ad was displayed
    public void didFailToShowAd(AdBuddizError error); // no Ad was displayed 
    public void didClick();    // the Ad was clicked
    public void didHideAd();   // the Ad was hidden 

}
