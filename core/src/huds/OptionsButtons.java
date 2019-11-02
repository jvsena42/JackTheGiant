package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jvsena42.jackthegiant.GameMain;

import helpers.GameInfo;
import scenes.MainMenu;

public class OptionsButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton easy, medium, hard, backBtn;
    private Image sign;



    public OptionsButtons(GameMain game) {
        this.game = game;

        gameViewport = new FitViewport(GameInfo.WIDTH,GameInfo.HEIGH,new OrthographicCamera());

        stage = new Stage(gameViewport,game.getBatch());

        Gdx.input.setInputProcessor(stage);

        createAndPositionButtons();
        addAllListeners();

        stage.addActor(easy);
        stage.addActor(medium);
        stage.addActor(hard);
        stage.addActor(backBtn);
        stage.addActor(sign);
    }

    void createAndPositionButtons(){

        easy = new ImageButton(new SpriteDrawable( new Sprite(new Texture("Buttons/Options Buttons/Easy.png"))));
        medium = new ImageButton(new SpriteDrawable( new Sprite(new Texture("Buttons/Options Buttons/Medium.png"))));
        hard = new ImageButton(new SpriteDrawable( new Sprite(new Texture("Buttons/Options Buttons/Hard.png"))));
        backBtn = new ImageButton(new SpriteDrawable( new Sprite(new Texture("Buttons/Options Buttons/Back.png"))));

        sign = new Image(new Texture("Buttons/Options Buttons/Check Sign.png"));

        backBtn.setPosition(17,17, Align.bottomLeft);

        easy.setPosition(GameInfo.WIDTH/2f,GameInfo.HEIGH/2f-110f, Align.center);
        medium.setPosition(GameInfo.WIDTH/2f,GameInfo.HEIGH/2f-30, Align.center);
        hard.setPosition(GameInfo.WIDTH/2f,GameInfo.HEIGH/2f+50, Align.center);


        //Remove this later
        sign.setPosition(GameInfo.WIDTH/2f + 76f, medium.getY()+13f,Align.bottomLeft);

    }

    void addAllListeners(){

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });

        easy.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(easy.getY()+13f);
            }
        });

        medium.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(medium.getY()+13f);
            }
        });

        hard.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                sign.setY(hard.getY()+13f);
            }
        });

    }

    public Stage getStage() {
        return stage;
    }
}
