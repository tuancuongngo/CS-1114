import sofia.micro.*; 

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

/**
 * Class that extends World and holds Pictures that can act
 * @author Cay Horstmann
 * @author Barb Ericson
 */ 
public class PictureWorld
    extends World
{
private TransformablePicture pic2;
    /**
     * Constructor that creates a world to show pictures in
     */
    public PictureWorld()
    {
        super(800, 600, 1);
        pic2 = new TransformablePicture("images/The Road.jpg");
        this.add(pic2, 320, 240);
    }
    
    public void blend()
    {
        TransformablePicture pic = new TransformablePicture("images/temple.jpg");
        pic2 = new TransformablePicture("images/The Road.jpg");
        this.add(pic2, 0, 0);
        
        for (Pixel pix : pic)
        pix.setAlpha(128);
        
        pic2.alphaBlend(pic);
    }
}
