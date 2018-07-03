package com.itboy.demo.base;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import java.util.List;

/**
 * 所有fragment的基类
 */
public class BaseFragment extends Fragment implements HandleBackInterface {

    public static String TAG = "BaseFragment";
    public BaseFragment() {

    }

    /**
     * @param resId           FramLayout的ID
     * @param clss            要显示类
     * @param bundle          传递的参数
     * @param fragmentManager
     */
    public static void startFragment(int resId, Class<? extends BaseFragment> clss, Bundle bundle, FragmentManager fragmentManager) {
        startFragment(resId, clss, bundle, fragmentManager, 0);
    }


    /**
     * @param resId           FramLayout的ID
     * @param clss            要显示类
     * @param bundle          传递的参数
     * @param saveType        保存类型  是否保存 instance   默认0不保存 | 1保存 |其他待定
     * @param fragmentManager
     */
    public static void startFragment(int resId, Class<? extends BaseFragment> clss, Bundle bundle, FragmentManager fragmentManager, int saveType) {
        String Tag = clss.getName();
        if (fragmentManager == null) {
            Log.e(TAG, "startFragment: fragmentManager =" + fragmentManager);
            return;
        }
        Fragment fragmentByTag = fragmentManager.findFragmentByTag(Tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragmentByTag == null) {
            try {
                fragmentByTag = clss.newInstance();
                fragmentByTag.setArguments(bundle);
                if (saveType == 0)
                    fragmentTransaction.add(resId, fragmentByTag).show(fragmentByTag);
                else if (saveType == 1) {
                    fragmentTransaction.add(resId, fragmentByTag, Tag).show(fragmentByTag);
//                    fragmentTransaction.setPrimaryNavigationFragment(fragmentByTag);
                }
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            fragmentByTag.setArguments(bundle);
            fragmentTransaction.detach(fragmentByTag).attach(fragmentByTag).show(fragmentByTag);
        }

        fragmentTransaction.addToBackStack(null);//默认返回界面为空
        List<Fragment> fragments = fragmentManager.getFragments();
        int size = fragments.size();
        Log.e(TAG, "startFragment: size" + size);
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                String name = fragment.getClass().getName();
                boolean hidden = fragment.isHidden();
                Log.e(TAG, "startFragment: name:" + name);
                Log.e(TAG, "startFragment: hidden:" + hidden);
                //此出必须判断fragment是否为null！
                if (fragment != null && !clss.isInstance(fragment)) {

                    fragmentTransaction.hide(fragment);
                    boolean hidden1 = fragment.isHidden();
                    Log.e(TAG, "startFragment: hidden1:" + hidden1);
                }
            }
        }
        fragmentTransaction.commitAllowingStateLoss();

        return;
    }























    @Override
    public boolean onBackPressed() {
        return HandleBackUtil.handleBackPress(this);
    }



}
