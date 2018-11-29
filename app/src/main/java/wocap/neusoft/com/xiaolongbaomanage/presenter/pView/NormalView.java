package wocap.neusoft.com.xiaolongbaomanage.presenter.pView;

/**
 * Created by wangmian on 2017/4/28.
 * you can contact me with wangmian1994@outlook.com
 */

public interface NormalView<T> extends BaseView {
    void onSuccess(T object);

    void onError(String result);

    void onStart();

    void onNoMore();
}
