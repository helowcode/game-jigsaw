package cn.cpf.app.jigsaw;

import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

/**
 * 游戏面板
 */
public class GamePanel extends JPanel implements MouseListener {
    //private static final long serialVersionUID = 54L;
    		//-653831947783440122L;
    // 创建单元图片数组
    private Cell[] cells = new Cell[9];
    // 空白
    private Cell cellBlank = null;
    
    // 构造方法
    public GamePanel() {
        super();
        // 设置空布局
        setLayout(null);
        // 初始化
        init();
    }
    
    // 初始化游戏
    public void init() {
        // 图片序号
        int num = 0;
        // 图标对象
        Icon icon = null;
        // 单元图片对象
        Cell cell = null;
        // 循环行
        for (int i = 0; i < 3; i++) {
            // 循环列
            for (int j = 0; j < 3; j++) {
                // 计算图片序号
                num = i * 3 + j;
                // 获取图片
                icon = new ImageIcon(getClass().getResource(
                        "/pic/" + (num + 1) + ".jpg"));
                // 实例化单元图片对象
                cell = new Cell(icon, num);
                // 设置单元图片的坐标
                cell.setLocation(j * Cell.IMAGEWIDTH, i
                        * Cell.IMAGEWIDTH);
                // 将单元图片旋转到单元图片数组中
                cells[num] = cell;
            }
        }
        // 向面板中添加所有单元图片
        for (Cell value : cells) {
            this.add(value);
        }
    }
    
    /**
     * 对图片进行随机排序
     */
    public void random() {
        Random rand = new Random();
        int m, n, x, y;
        if (cellBlank == null) {
            cellBlank = cells[cells.length - 1];
            
            for (int i = 0; i < cells.length; i++) {
                if (i != cells.length - 1) {
                    cells[i].addMouseListener(this);
                }
            }
        }
        // 遍历所有单元图片
        for (int i = 0; i < cells.length; i++) 
        {
            // 产生随机数
//            m = 5;
//            n = 8;
            m = rand.nextInt(cells.length);
            n = rand.nextInt(cells.length);
            // 获取x坐标
            x = cells[m].getX();
            // 获取y坐标
            y = cells[m].getY();
            // 对单元图片调换集团
            cells[m].setLocation(cells[n].getX(), cells[n]
                    .getY());
            cells[n].setLocation(x, y);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        // 获取触发时间的对象
        Cell cell = (Cell) e.getSource();
        // 获取x坐标
        int x = cellBlank.getX();
        // 获取y坐标
        int y = cellBlank.getY();
        // 向右移动
        if ((x - cell.getX()) == Cell.IMAGEWIDTH
                && cell.getY() == y) {
            cell.move(Direction.RIGHT);
            cellBlank.move(Direction.LEFT);
        }
        // 向左移动
        else if ((x - cell.getX()) == -Cell.IMAGEWIDTH
                && cell.getY() == y) {
            cell.move(Direction.LEFT);
            cellBlank.move(Direction.RIGHT);
        }
        // 向上移动
        else if (cell.getX() == x
                && (cell.getY() - y) == Cell.IMAGEWIDTH) {
            cell.move(Direction.UP);
            cellBlank.move(Direction.DOWN);
        }
        // 向下移动
        else if (cell.getX() == x
                && (cell.getY() - y) == -Cell.IMAGEWIDTH) {
            cell.move(Direction.DOWN);
            cellBlank.move(Direction.UP);
        }
        // 判断是否拼图成功
        if (isSuccess()) {
            // 提示成功
            int i = JOptionPane.showConfirmDialog(this,
                    "成功，再来一局？", "拼图成功",
                    JOptionPane.YES_NO_OPTION);
            // 开始新一局
            if (i == JOptionPane.YES_OPTION) {
                random();
            }
            else System.exit(0);
            
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    /**
     * 判断是否拼图成功
     * 
     * @return 布尔值
     */
    public boolean isSuccess() {
        // 遍历所有单元图片
        for (int i = 0; i < cells.length; i++) {
            // 获取x坐标
            int x = cells[i].getX();
            // 获取y坐标
            int y = cells[i].getY();
            // 判断单元图片位置是否正确
            if (i != 0) {
                if (y / Cell.IMAGEWIDTH * 3 + x
                        / Cell.IMAGEWIDTH != cells[i].getPlace()) {
                    return false;
                }
            }
        }
        return true;
    }
}
