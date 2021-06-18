package com.ets.thcs.easythcsearch.splash;

import android.graphics.RectF;

/**
 * Created by Manirul on 10/23/2016.
 */
public interface TransitionGenerator {

    public Transition generateNextTransition(RectF drawableBounds, RectF viewport);
}
