package com.king.itus;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private Direction direction = Direction.RIGHT;

    private Point2D head;

    private Point2D previousTail;

    private List<Point2D> body;

    public Snake(Point2D head) {
        this.body = new ArrayList<Point2D>();
        this.head = head;
        this.previousTail = head;
        this.body.add(head);
    }

    public void setDirection(Direction direction) {
       this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void update() {
        head = head.add(direction.vector);

        previousTail = body.remove(body.size()-1);

        body.add(0,head);
    }

    public Point2D getPosition() {
        return  head;
    }

    public boolean isCollisionWith(Food food) {
        return head.equals(food.getPosition());
    }

    public void grow() {
        body.add(previousTail);
    }

    public int getLength() {
        return body.size();
    }

    public List<Point2D> getBody() {
        return body;
    }

    public boolean isOutOfBounds(int size) {
        return head.getX() < 0 || head.getX() > size || head.getY() < 0 || head.getY() > size;
    }

    public boolean isDead() {
        return body.lastIndexOf(head) > 0;
    }
}
