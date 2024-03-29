package clouds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

public class Cloud extends Sprite {

    public World world;
    public Body body;
    public String cloudName;

    public boolean isDrawLeft;


    public Cloud(World world, String cloudName){
        super(new Texture("Clouds/"+cloudName+".png"));
        this.world = world;
        this.cloudName = cloudName;
    }

    void createBody(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((getX()+getWidth()/4)/ GameInfo.PPM,(getY()+getHeight()/2)/GameInfo.PPM);

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox((getWidth()/2-20f)/GameInfo.PPM,getHeight()/2/GameInfo.PPM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void setSpritePosition (Float x , Float y){
        setPosition(x-getWidth()/2,y);
        createBody();
    }

    public String getCloudName(){
        return this.cloudName;
    }

    public boolean getDrawLeft() {
        return isDrawLeft;
    }

    public void setDrawLeft(boolean drawLeft) {
        isDrawLeft = drawLeft;
    }
}
