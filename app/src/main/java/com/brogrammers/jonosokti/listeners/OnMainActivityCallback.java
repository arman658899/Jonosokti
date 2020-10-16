package com.brogrammers.jonosokti.listeners;

import androidx.fragment.app.Fragment;

public interface OnMainActivityCallback {
    void onOpenDrawer();
    void onCloseDrawer();
    void onReplaceFragment(Fragment fragment);
}
