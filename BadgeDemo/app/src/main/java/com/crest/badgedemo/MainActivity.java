package com.crest.badgedemo;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int badgeCount = 8;
        ShortcutBadger.applyCount(this, badgeCount); //for 1.1.4+
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.main, paramMenu);
        return super.onCreateOptionsMenu(paramMenu);
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        switch (paramMenuItem.getItemId()) {
            case R.id.action_cart:
                Toast.makeText(this, "Show Cart", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(paramMenuItem);
        }
    }
    public boolean onPrepareOptionsMenu(Menu paramMenu) {
        createCartBadge(7,paramMenu);
        return super.onPrepareOptionsMenu(paramMenu);
    }
    private void createCartBadge(int paramInt,Menu paramMenu) {
        if (Build.VERSION.SDK_INT <= 15) {
            return;
        }
        MenuItem cartItem = paramMenu.findItem(R.id.action_cart);
        LayerDrawable localLayerDrawable = (LayerDrawable) cartItem.getIcon();
        Drawable cartBadgeDrawable = localLayerDrawable
                .findDrawableByLayerId(R.id.ic_badge);
        BadgeDrawable badgeDrawable;
        if ((cartBadgeDrawable != null)
                && ((cartBadgeDrawable instanceof BadgeDrawable))
                && (paramInt < 10)) {
            badgeDrawable = (BadgeDrawable) cartBadgeDrawable;
        } else {
            badgeDrawable = new BadgeDrawable(this);
        }
        badgeDrawable.setCount(String.valueOf(paramInt));
        localLayerDrawable.mutate();
        localLayerDrawable.setDrawableByLayerId(R.id.ic_badge, badgeDrawable);
        cartItem.setIcon(localLayerDrawable);
    }
}
