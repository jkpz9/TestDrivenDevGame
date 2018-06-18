package com.king.itus;
import  org.junit.Test;
import javafx.geometry.Point2D;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SnakeTest {
    @Test
    public void testSnakeMoves()
    {
        Snake snake = new Snake(new Point2D(0,0));

        /*
        snake.setDirection(Direction.RIGHT);

        snake.update();

        assertThat(snake.getPosition(), is(new Point2D(1, 0)));
        */

        for (Direction d : Direction.values()) {
            Point2D old_position = snake.getPosition();
            snake.setDirection(d);
            snake.update();
            assertThat(snake.getPosition(), is(old_position.add(d.vector)));
        }

    }

    @Test
    public void testSnakeFoodCollision() {
        Snake snake = new Snake(new Point2D(10, 5));

        Food food  = new Food(new Point2D(10,5));

        assertTrue(snake.isCollisionWith(food));
    }

    @Test
    public void TestSnakeGrows() {
        Snake snake = new Snake(new Point2D(0,0));

        snake.setDirection(Direction.RIGHT);
        snake.update();
        snake.grow();

        assertThat(snake.getLength(), is(2));
        assertThat(snake.getBody(), hasItems(new Point2D(0,0)));
    }

    @Test
    public void testSnakeOutOfBounds() {
        Snake snake = new Snake(new Point2D(25 ,0));

        assertTrue(snake.isOutOfBounds(24));
        assertFalse(snake.isOutOfBounds(25));
    }

    @Test
    public void testSnakeDies() {
        Snake snake = new Snake(new Point2D(0,0));
        for (int i=0; i<5 ; i++)
        {
            snake.setDirection(Direction.RIGHT);
            snake.update();
            snake.grow();
        }
        snake.setDirection(Direction.UP);
        snake.update();

        snake.setDirection(Direction.LEFT);
        snake.update();

        snake.setDirection(Direction.DOWN);
        snake.update();

        assertTrue(snake.isDead());
    }
}
