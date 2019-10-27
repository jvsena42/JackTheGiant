package player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

public class Player extends Sprite {

    private World world;
    private Body body;

    public Player(World world, float x, float y){
        super(new Texture("Player/Player 1.png"));
        this.world = world;
        setPosition(x,y);
        createBoody();
    }

    void createBoody(){
        BodyDef bodyDef = new BodyDef();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX()/ GameInfo.PPM,getY()/GameInfo.PPM);

        body = world.createBody(bodyDef);
        body.setFixedRotation(true);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2f)/GameInfo.PPM,(getHeight()/2f)/GameInfo.PPM);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 4f;
        fixtureDef.friction = 2f;
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();

    }

    public void updatePlayer (){
        setPosition(body.getPosition().x*GameInfo.PPM,body.getPosition().y*GameInfo.PPM);
    }

    public void drawPlayer(SpriteBatch batch){
        batch.draw(this,getX()+getWidth()/2f-25f,getY()-getHeight()/2f);
    }

    public void movePlayer(float x){
        body.setLinearVelocity(x,body.getLinearVelocity().y);
    }

}
