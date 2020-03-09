package cn.cpf.app.jigsaw;

import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * 单元图片
 */
public class Cell extends JButton {
    //private static final long serialVersionUID = 718623114650657819L;
    // 图片宽度
    public static final int IMAGEWIDTH = 117;
    // 图片位置
    private int place;
    
    // 构造方法
    public Cell(Icon icon, int place) {
        this.setSize(IMAGEWIDTH, IMAGEWIDTH);
        this.setIcon(icon);
        this.place = place;
    }
    
    // 移动图片
    public void move(Direction dir) {
        // 获取图片的Rectangle
        Rectangle rec = this.getBounds();
        // 判断方向
        switch (dir) {
            // 向上移动
            case UP:
                this.setLocation(rec.x, rec.y - IMAGEWIDTH);
                break;
            // 向下移动
            case DOWN:
                this.setLocation(rec.x, rec.y + IMAGEWIDTH);
                break;
            // 向左移动
            case LEFT:
                this.setLocation(rec.x - IMAGEWIDTH, rec.y);
                break;
            // 向右移动
            case RIGHT:
                System.out.println(RIGHT);
                this.setLocation(rec.x + IMAGEWIDTH, rec.y);
                break;
        }
    }
    
    // 获取单元图片的x坐标
    public int getX() {
        return this.getBounds().x;
    }
    
    // 获取单元图片的y坐标
    public int getY() {
        return this.getBounds().y;
    }
    
    // 获取单元图片的位置
    public int getPlace() {
        return place;
    }
}
