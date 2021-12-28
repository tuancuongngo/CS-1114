import sofia.micro.*;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

// -------------------------------------------------------------------------
/**
 *  Test the TransformanlePicture() class
 *  Test the various situations of the different
 *  methods in this class
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.16
 */
public class TransformablePictureTest extends TestCase
{
    //~ Fields ................................................................
    private TransformablePicture testPic;

    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new TransformablePictureTest test object.
     */
    public TransformablePictureTest()
    {
        // The constructor is usually empty in unit tests, since it runs
        // once for the whole class, not once for each test method.
        // Per-test initialization should be placed in setUp() instead.
    }


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
    public void setUp()
    {
        testPic = new TransformablePicture(2, 2);
    }


    // ----------------------------------------------------------
    /*# Insert your own test methods here */
    
    /**
     * Test the maxRed() method
     */
    public void testMaxRed()
    {
        testPic.maxRed();
        
        for (Pixel pixel : testPic)
        {
            assertEquals(pixel.getRed(), 255);
        }
    }
    
    
    /**
     * Test the maxGreen() method
     */
    public void testMaxGreen()
    {
        testPic.maxGreen();
        
        for (Pixel pixel : testPic)
        {
            assertEquals(pixel.getGreen(), 255);
        }
    }
    
    
    /**
     * Test the maxBlue() method
     */
    public void testMaxBlue()
    {
        testPic.maxBlue();
        
        for (Pixel pixel : testPic)
        {
            assertEquals(pixel.getBlue(), 255);
        }
    }
    
    
    /**
     * Test the grayscale() method
     */
    public void testGrayScale()
    {
        testPic = new TransformablePicture("images/red2x2.png");
        testPic.grayscale();
        
        for (Pixel pixel : testPic)
        {
            double average = pixel.getAverage();
            assertEquals(average, pixel.getRed(), 0.001);
            assertEquals(average, pixel.getGreen(), 0.001);
            assertEquals(average, pixel.getBlue(), 0.001);
        }
    }
    
    
    /**
     * Test the invert() method
     */
    public void testInvert()
    {
        //The value of each pixel is (255, 0, 0)
        testPic = new TransformablePicture("images/red2x2.png");
        
        testPic.invert();
        
        //After inverting, each pixed will have (0, 255, 255)
        for (Pixel pixel : testPic)
        {
            assertEquals(0, pixel.getRed(), 0.001);
            assertEquals(255, pixel.getGreen(), 0.001);
            assertEquals(255, pixel.getBlue(), 0.001);
        }
    }
    
    
    /**
     * Test the multicolored() method in the 
     * FIRST third of the picture, which should
     * have max RED value after multicolored() is called
     */
    public void testMulticolored1()
    {
        testPic = new TransformablePicture("images/gray5x5.png");
        testPic.multicolored();
        
        //Store pixel color data into a 2D array for testing
        Pixel[][] pixelList = testPic.getPixels();
        
        //Get the dimensions of the picture to test
        int width = testPic.getImageWidth();
        int height = testPic.getImageHeight();
        
        //The pixels in the first third of the image 
        //will have a max red value
        for (int x = 0; x < width / 3; x++)
        {
            for (int y = 0; y < height; y++)
            {
                assertEquals(255, pixelList[x][y].getRed(), 0.001);
            }
        }
    }
    
    
    /**
     * Test the multicolored() method in the 
     * SECOND third of the picture, which should
     * have max GREEN value after multicolored() is called
     */
    public void testMulticolored2()
    {
        testPic.multicolored();
        
        //Store pixel color data into a 2D array for testing
        Pixel[][] pixelList = testPic.getPixels();
        
        //Get the dimensions of the picture to test
        int width = testPic.getImageWidth();
        int height = testPic.getImageHeight();
        
        //The pixels in the second third of the image 
        //will have a max green value
        for (int x = width / 3; x < (width * 2 / 3); x++)
        {
            for (int y = 0; y < height; y++)
            {
                assertEquals(255, pixelList[x][y].getGreen(), 0.001);
            }
        }
    }
    
    /**
     * Test the multicolored() method in the 
     * LAST third of the picture, which should
     * have max BLUE value after multicolored() is called
     */
    public void testMulticolored3()
    {
        testPic.multicolored();
        
        //Store pixel color data into a 2D array for testing
        Pixel[][] pixelList = testPic.getPixels();
        
        //Get the dimensions of the picture to test
        int width = testPic.getImageWidth();
        int height = testPic.getImageHeight();
        
        //The pixels in the last third of the image 
        //will have a max blue value
        for (int x = (width * 2 / 3); x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                assertEquals(255, pixelList[x][y].getBlue(), 0.001);
            }
        }
        
    }
        
        
    
    /**
     * Test the brighten() method
     */
    public void testBrighten()
    {
        //The value of each pixel is (127 127, 127)
        testPic = new TransformablePicture("images/gray2x2.png");
        
        testPic.brighten();
        
        //All pixels are brightened by 20%
        for (Pixel pixel : testPic)
        {
            assertEquals((int)(127 + 127 * 0.2), (int) pixel.getRed()); 
            assertEquals((int)(127 + 127 * 0.2), (int) pixel.getGreen());
            assertEquals((int)(127 + 127 * 0.2), (int) pixel.getBlue());
        }
        
        
    }
    
    /**
     * Test the dim() method
     */
    public void testDim()
    {
        //The value of each pixel is (127 127, 127)
        testPic = new TransformablePicture("images/gray2x2.png");
        
        //The expected value of each pixel after dim() is called
        int value = 127 - (int)(127 * 0.2);
       
        testPic.dim();
        
        //All pixels are dimmed by 20%
        for (Pixel pixel : testPic)
        {
            assertEquals(value, pixel.getRed()); 
            assertEquals(value, pixel.getGreen());
            assertEquals(value, pixel.getBlue());
        }
        
    }
    
    
    /**
     * Test the flipHorizontal() method
     */
    public void testFlipHorizontal()
    {
        //Set a 2x2 picture
        testPic = new TransformablePicture(2, 2);
        
        Pixel[][] pixelList = testPic.getPixels();
        
        //First column of pixels of the Picture 
        //has a red value of 255
        pixelList[0][0].setRed(255);
        pixelList[0][1].setRed(255);
        
        //Second column of pixels of the Picture
        //has a blue value of 255
        pixelList[1][0].setBlue(255);
        pixelList[1][1].setBlue(255);
        
        //Flip the picture horizontally
        testPic = testPic.flipHorizontal();
        
        //Get the new pixel info of the flipped picture
        pixelList = testPic.getPixels();
        
        //Now the first column of pixels is blue
        //and the second column is red
        assertEquals(255, pixelList[0][0].getBlue());
        assertEquals(255, pixelList[0][1].getBlue());
        assertEquals(255, pixelList[1][0].getRed());
        assertEquals(255, pixelList[1][1].getRed());
    }
    
    
    /**
     * Test the flipVertical() method
     */
    public void testFlipVertical()
    {
        //Set a 2x2 picture
        testPic = new TransformablePicture(2, 2);
        
        Pixel[][] pixelList = testPic.getPixels();
        
        //First row of pixels of the Picture 
        //has a red value of 255
        pixelList[0][0].setRed(255);
        pixelList[1][0].setRed(255);
        
        //Second row of pixels of the Picture
        //has a blue value of 255
        pixelList[0][1].setBlue(255);
        pixelList[1][1].setBlue(255);
        
        //Flip the picture vertically
        testPic = testPic.flipVertical();
        
        //Get the new pixel info of the flipped picture
        pixelList = testPic.getPixels();
        
        //Now the first row of pixels is blue
        //and the second row is red
        assertEquals(255, pixelList[0][0].getBlue());
        assertEquals(255, pixelList[1][0].getBlue());
        assertEquals(255, pixelList[0][1].getRed());
        assertEquals(255, pixelList[1][1].getRed());
        
        
    }
    
    
    /**
     * Test the alphaBlend method
     */
    public void testAlphaBlend()
    {
        //Make a 2x2 bottom image
        testPic = new TransformablePicture(2, 2);
        //Set the red, green, blue, and alpha values of
        //every pixel to 100
        for (Pixel pixel : testPic)
        {
            pixel.setColor(100, 100, 100, 100);
        }
        
        //Make a 2x2 top image
        TransformablePicture topPic = new TransformablePicture(testPic);
        //Set the red, green, blue, and alpha values of
        //every pixel to 50
        for (Pixel pixel : topPic)
        {
            pixel.setColor(50, 50, 50, 50);
        }
        
        //Blend the pictures
        testPic.alphaBlend(topPic);
        
        //After blending, if we use the formula
        //we should get that each color value is now 90
        for (Pixel pixel : testPic)
        {
            assertEquals(90, pixel.getRed());
            assertEquals(90, pixel.getGreen());
            assertEquals(90, pixel.getBlue());
        }
    }
    
    
}
