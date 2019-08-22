package by.future.servicebiz.callback;

/**
 * 按钮--演示回调示例
 *
 * @author by@Deng
 * @create 2019-03-10 21:02
 */
public class Button {

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /** 嵌套内部接口 */
    public interface OnClickListener{
        void onClick();
    }

    /** 模拟点击事件 */
    public void mockClick(){
        if(onClickListener!=null){
            onClickListener.onClick();
        }
    }



}
