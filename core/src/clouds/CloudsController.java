package clouds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Random;

import helpers.GameInfo;

public class CloudsController {

    private World world;

    private Array<Cloud> clouds = new Array<>();

    private final float DISTANCE_BETWEEN_CLOUDS = 300f;
    private float minX,maxX;
    private float lastCloudPositionY;
    private Random random = new Random();

    private float cameraY;

    public CloudsController(World world){
        this.world = world;
        minX = GameInfo.WIDTH/2f - 130f;
        maxX = GameInfo.WIDTH/2f + 130f;
        createClouds();
        positionClouds(true);
    }

    public void createClouds(){

        for (int i=0; i<2; i++){
            clouds.add(new Cloud(world, "Dark Cloud"));
        }

        int index =1;

        for (int i=0; i<6; i++){
            clouds.add(new Cloud(world,"Cloud "+ index));
            index++;

            if (index ==4){
                index=1;
            }
        }
        clouds.shuffle();
    }

    public void positionClouds(Boolean firstTimeArranging){

        while (clouds.get(0).getCloudName().equals("Dark Cloud")){
            clouds.shuffle();
        }

        float positionY = 0;
        if (firstTimeArranging){
            positionY = GameInfo.HEIGH/2f;
        }else {
            positionY = lastCloudPositionY;
        }

        int controlX = 0;

        for (Cloud c : clouds){

            if (c.getX() == 0 && c.getY() == 0){
                float tempX = 0;

                if (controlX==0){
                    tempX = randomBetweenNumbers(maxX-60, maxX);
                    controlX =1;
                }else {
                    tempX = randomBetweenNumbers(minX+60 , minX);
                    controlX=0;
                }

                c.setSpritePosition(tempX , positionY);

                positionY -= DISTANCE_BETWEEN_CLOUDS;
                lastCloudPositionY = positionY;
            }

        }
    }

    public void drawClouds (SpriteBatch batch){
        for (Cloud c : clouds){
            batch.draw(c, c.getX() ,c.getY()-c.getY()/2f);

        }
    }

    public void createAndArrangeNewClouds(){
        for (int i=0;i<clouds.size;i++){
            if ((clouds.get(i).getY()-GameInfo.HEIGH/2-5)>cameraY){
                clouds.get(i).getTexture().dispose();
                clouds.removeIndex(i);
            }
        }

        if (clouds.size == 4){
            createClouds();
            positionClouds(false);
        }
    }

    public void setCameraY (float cameraY){
        this.cameraY = cameraY;
    }

    private float randomBetweenNumbers (float min, float max){
        return random.nextFloat() * (max - min) + min;
    }

}
