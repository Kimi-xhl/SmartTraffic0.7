// Generated code from Butter Knife. Do not modify!
package com.lenovo.smarttraffic.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lenovo.smarttraffic.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Item1Activity_ViewBinding implements Unbinder {
  private Item1Activity target;

  @UiThread
  public Item1Activity_ViewBinding(Item1Activity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Item1Activity_ViewBinding(Item1Activity target, View source) {
    this.target = target;

    target.tabLayoutList = Utils.findRequiredViewAsType(source, R.id.tab_layout_list, "field 'tabLayoutList'", TabLayout.class);
    target.addChannelIv = Utils.findRequiredViewAsType(source, R.id.add_channel_iv, "field 'addChannelIv'", ImageView.class);
    target.headerLayout = Utils.findRequiredViewAsType(source, R.id.header_layout, "field 'headerLayout'", LinearLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Item1Activity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayoutList = null;
    target.addChannelIv = null;
    target.headerLayout = null;
    target.viewPager = null;
  }
}
