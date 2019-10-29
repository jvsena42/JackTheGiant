package huds;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jvsena42.jackthegiant.GameMain;

import helpers.GameInfo;

public class MainMenuButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton playBtn;
    private ImageButton highScoreBtn;
    private ImageButton optionsBtn;
    private ImageButton quitBtn;
    private ImageButton musicBtn;

    public MainMenuButtons(GameMain game){
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH,GameInfo.HEIGH,new OrthographicCamera());

        stage = new Stage(gameViewport,game.getBatch());

        createAndPositionButtons();

        stage.addActor(playBtn);
        stage.addActor(highScoreBtn);
        stage.addActor(optionsBtn);
        stage.addActor(quitBtn);
        stage.addActor(musicBtn);
    }

    public void createAndPositionButtons(){

        playBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Main Menu/Start Game.png"))));
        highScoreBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Main Menu/Highscore.png"))));
        optionsBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Main Menu/Options.png"))));
        quitBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Main Menu/Quit.png"))));
        musicBtn = new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Main Menu/Music On.png"))));

        playBtn.setPosition(GameInfo.WIDTH/2f - 80f,GameInfo.HEIGH/2f + 50f, Align.center);
        highScoreBtn.setPosition(GameInfo.WIDTH/2f - 60f,GameInfo.HEIGH/2f - 20f, Align.center);
        optionsBtn.setPosition(GameInfo.WIDTH/2f - 40f,GameInfo.HEIGH/2f - 90f, Align.center);
        quitBtn.setPosition(GameInfo.WIDTH/2f - 20f,GameInfo.HEIGH/2f - 160f, Align.center);
        musicBtn.setPosition(GameInfo.WIDTH/2f - 13,13, Align.bottomRight);
    }

    public Stage getStage(){
        return this.stage;
    }

}
