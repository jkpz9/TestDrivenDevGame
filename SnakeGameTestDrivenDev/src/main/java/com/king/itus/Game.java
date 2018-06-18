package com.king.itus;

import javafx.geometry.Point2D;

import java.util.Random;

public class Game {


    private final int size;
    private Snake snake;
    private Food food;
    private boolean isOver = false;
    private Random random = new Random(123);

    private Point2D getRandomPosition() {
        return  new Point2D(random.nextInt(size), random.nextInt(size));
    }

    public Game(int size) {
        this.size = size;
        snake = new Snake(new Point2D(size / 2, size / 2));
        food = new Food(new Point2D(10, 10));
    }

    public void update() {
        snake.update();

        if (snake.isCollisionWith(food)) {
            snake.grow();
            food.setPosition(getRandomPosition());
        }

        if (snake.isOutOfBounds(size) || snake.isDead()) {
            isOver = true;
        }
    }
}
