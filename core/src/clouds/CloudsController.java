package clouds;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Random;

import collectables.Collectable;
import helpers.GameInfo;
import player.Player;

public class CloudsController {

    private World world;

    private Array<Cloud> clouds = new Array<>();
    private Array<Collectable> collectables = new Array<Collectable>();

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
                    c.setDrawLeft(false);
                }else {
                    tempX = randomBetweenNumbers(minX+60 , minX);
                    controlX=0;
                    c.setDrawLeft(true);
                }

                c.setSpritePosition(tempX , positionY);

                positionY -= DISTANCE_BETWEEN_CLOUDS;
                lastCloudPositionY = positionY;
            }

        }

        //Remove this later
        Collectable c1 = new Collectable(world,"Coin");
        c1.setCollectablePosition(clouds.get(1).getX(),clouds.get(1).getY());

        collectables.add(c1);
    }

    public void drawClouds (SpriteBatch batch){
        for (Cloud c : clouds){
            if (c.getDrawLeft()){
                batch.draw(c, c.getX()-10 ,c.getY());
            }else {
                batch.draw(c, c.getX() ,c.getY());
            }

        }
    }

    public void drawCollectables(SpriteBatch batch){
        for (Collectable c: collectables){
            c.updateCollectable();
            batch.draw(c,c.getX(),c.getY());
        }

    }

    public void removeCollectables(){
        for (int i =0; i<collectables.size; i++){
            if (collectables.get(i).getFixture().getUserData() == "Remove"){
                collectables.get(i).changeFilter();
                collectables.get(i).getTexture().dispose();
                collectables.removeIndex(i);
            }
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

    public Player positionThePlayer(Player player){

        player = new Player(world,clouds.get(0).getX(), clouds.get(0).getY()+100);
        return player;
    }

    private float randomBetweenNumbers (float min, float max){
        return random.nextFloat() * (max - min) + min;
    }

}
