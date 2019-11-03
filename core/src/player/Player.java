package player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import helpers.GameInfo;

public class Player extends Sprite {

    private World world;
    private Body body;

    private TextureAtlas playerAtlas;
    private com.badlogic.gdx.graphics.g2d.Animation animation;
    private float elapsedTime;

    private boolean isWalking;

    public Player(World world, float x, float y){
        super(new Texture("Player/Player 1.png"));
        this.world = world;
        setPosition(x,y);
        createBoody();
        playerAtlas = new TextureAtlas("Player Animation/Player Animation.atlas");
    }

    void createBoody(){
        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX()/ GameInfo.PPM,getY()/GameInfo.PPM);

        body = world.createBody(bodyDef);
        body.setFixedRotation(true);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2f-10f)/GameInfo.PPM,(getHeight()/2f)/GameInfo.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 4f;
        fixtureDef.friction = 2f;
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();

    }

    public void updatePlayer (){

        if(body.getLinearVelocity().x>0){
            //Going right
            setPosition((body.getPosition().x+0.3f)*GameInfo.PPM,body.getPosition().y*GameInfo.PPM);
        }else if (body.getLinearVelocity().x<0){
            //Going left
            setPosition((body.getPosition().x-0.1f)*GameInfo.PPM,body.getPosition().y*GameInfo.PPM);

        }
    }

    public void drawPlayerIdle(SpriteBatch batch){
        if (!isWalking){
            batch.draw(this,getX()+getWidth()/2f-25f,getY()-getHeight()/2f);
        }
    }

    public void drawPlayerAnimation ( SpriteBatch batch){
        if (isWalking){
            elapsedTime += Gdx.graphics.getDeltaTime();

            Array<TextureAtlas.AtlasRegion> frames = playerAtlas.getRegions();

            for (TextureRegion frame: frames){
                if (body.getLinearVelocity().x<0 && !isFlipX()){
                    frame.flip(true,false);
                }else if (body.getLinearVelocity().x>0 && isFlipX()){
                    frame.flip(true,false);
                }

                }

            animation = new Animation(1f/10f,playerAtlas.getRegions());

            batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime,true),getX()+getWidth()/2f-25f,getY()-getHeight()/2f);
        }
    }

    public void movePlayer(float x){

        if (x <0 && !this.isFlipX()){
            //move left, face the player to the left direction
            this.flip(true,false);
        }else if (x>0 && this.isFlipX()){
            //move right, face the player to the right direction
            this.flip(true,false);
        }

        isWalking = true;
        body.setLinearVelocity(x,body.getLinearVelocity().y);
    }

    public void setWalking (boolean isWalking){
        this.isWalking = isWalking;
    }

}
