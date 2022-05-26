package com.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * 用于辅助完成中介者模式和门面模式
 */
public abstract class GameObject implements Serializable {
    int x,y;
    public abstract void paint(Graphics g);
}
