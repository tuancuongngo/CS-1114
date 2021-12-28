import sofia.micro.*;

//-------------------------------------------------------------------------
/**
 *  Represents a world where particles interact.
 *
 *  @author Stephen Edwards (edwards)
 *  @version 2019.03.28
 */
public class ParticleWorld extends ParticleWorldBase
{
    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new world with the default layout.
     */
    public ParticleWorld()
    {
        super();
    }


    // ----------------------------------------------------------
    /**
     * Creates a new world with a specific layout.
     * @param layout A number 0-2 specifying the layout to use.
     */
    public ParticleWorld(int layout)
    {
        super(layout);
    }


    // ----------------------------------------------------------
    /**
     * Creates a new, empty world with the specified dimensions.
     * @param width  The width of the new world.
     * @param height The height of the new world.
     */
    public ParticleWorld(int width, int height)
    {
        super(width, height);
    }
}
