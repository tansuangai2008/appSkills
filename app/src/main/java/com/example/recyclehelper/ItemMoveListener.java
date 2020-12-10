package com.example.recyclehelper;

/**
 * author : ly
 * date : 2020/12/10 17:10
 * description : Item移动回调监听事件
 */
public interface ItemMoveListener {
    void moveItemTranslate(int moveY);
    void setVisible(boolean visible);
}
