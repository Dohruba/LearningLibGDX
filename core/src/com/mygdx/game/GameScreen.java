package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

class GameScreen implements Screen {

    //screen
    private Camera camera;
    private Viewport viewport;

    //Graphics
    private SpriteBatch batch;
//    private Texture backGround;
    private Texture[] backGrounds;
    //Timing
//    private int backgrounfOffset;
    private float[] backgroundOffsets = {0,0,0,0,};
    private float backgroundMayScrollingSpeed;

    //World parameters
    private final int WORLD_WIDTH = 72;
    private final int WORLD_HEIGHT = 128;

    GameScreen(){

        camera = new OrthographicCamera();
        viewport = new StretchViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);

//        backGround = new Texture("darkPurpleStarscape.png");
//        backgrounfOffset = 0;

        backGrounds = new Texture[4];
        backGrounds[0] = new Texture("Starscape00.png");
        backGrounds[1] = new Texture("Starscape01.png");
        backGrounds[2] = new Texture("Starscape02.png");
        backGrounds[3] = new Texture("Starscape03.png");

        backgroundMayScrollingSpeed = (float)WORLD_HEIGHT / 4;

        batch = new SpriteBatch();

    }


    @Override
    public void render(float deltaTime) {
        batch.begin();
        //Scrolling background
        renderBackground(deltaTime);

        batch.end();
    }

    private void renderBackground(float deltaTime){
        backgroundOffsets[0] += deltaTime * backgroundMayScrollingSpeed /8;
        backgroundOffsets[1] += deltaTime * backgroundMayScrollingSpeed /4;
        backgroundOffsets[2] += deltaTime * backgroundMayScrollingSpeed /2;
        backgroundOffsets[3] += deltaTime * backgroundMayScrollingSpeed;

        for (int layer = 0; layer < backGrounds.length; layer++){
            if (backgroundOffsets[layer] > WORLD_HEIGHT){
                backgroundOffsets[layer] = 0;
            }
            batch.draw(backGrounds[layer],
                    0,
                    -backgroundOffsets[layer],
                    WORLD_WIDTH,WORLD_HEIGHT);
            batch.draw(backGrounds[layer],
                    0,
                    -backgroundOffsets[layer] + WORLD_HEIGHT,
                    WORLD_WIDTH,WORLD_HEIGHT);
        }
    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void show() {

    }
}
