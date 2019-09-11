package clouds;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class CloudsController {

    private World world;

    private Array<Cloud> clouds = new Array<Cloud>();

    public CloudsController(World world){
        this.world = world;

        createClouds();
    }

    public void createClouds(){

        for (int i=0; i<2; i++){
            clouds.add(new Cloud(world, "Dark Cloud"));
        }

        int index =1;

        for (int i=0; i<2; i++){
            clouds.add(new Cloud(world,"Cloud "+ index));
            index++;

            if (index ==4){
                index=1;
            }
        }

        clouds.shuffle();

    }

}
