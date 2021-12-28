import sofia.micro.*;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those
// who do.
// -- Cuong Ngo (ngoct)

//-------------------------------------------------------------------------
/**
 *  Picture subclass that creates a picture that can be modified
 *  An object of this type can be transformed in multiple ways,
 *  such as flip, blend, color changes, etc...
 *
 *  @author Cuong Ngo (ngoct)
 *  @version 2019.11.16
 */
public class TransformablePicture extends Picture
{
    //~ Fields ................................................................
    private Pixel[][] pixelList;


    //~ Constructor ...........................................................

    // ----------------------------------------------------------
    
    /**
     * Takes an image file name and passes it to the superclass.
     * 
     * @param fileName name of the image file
     */
    public TransformablePicture(String fileName)
    {
        super(fileName);
    }

    /**
     * A constructor that takes in integer width and height dimensions, 
     * and creates a new blank image of the given size
     * 
     * @param width the image width
     * @param height the image height
     */
    public TransformablePicture(int width, int height)
    {
        super(width, height);
    }

    
    /**
     * A constructor that takes a Picture object as a parameter, 
     * and creates a duplicate of the given image. 
     * 
     * @param picture the picture to duplicate
     */
    public TransformablePicture(Picture picture)
    {
        super(picture);
    }
    
    //~ Methods ...............................................................
   
    /**
     * Force every pixel's red component to the maximum value.
     */
    public void maxRed()
    {
        for (Pixel pixel : this)
        {
            pixel.setRed(255);
        }
        
    }
    
    /**
     * Force every pixel's green component to the maximum value.
     */
    public void maxGreen()
    {
        for (Pixel pixel : this)
        {
            pixel.setGreen(255);
        }
    }
    
    /**
     * Force every pixel's blue component to the maximum value.
     */
    public void maxBlue()
    {
        for (Pixel pixel : this)
        {
            pixel.setBlue(255);
        }
    }
    
    /**
     * Convert each pixel to a grayscale value by averaging its red, 
     * green, and blue components together, 
     * and setting all three components to that average value.
     */
    public void grayscale()
    {
        for (Pixel pixel : this)
        {
            double average = pixel.getAverage();

            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
    }
    
    /**
     * Invert the color of this picture by transforming 
     * each pixel to its inverse color.
     * You can invert a red value of r by setting it to 255 - r. 
     * Inverting a pixel requires inverting all three of its color components.
     */
    public void invert()
    {
        for (Pixel pixel : this)
        {
            double r = pixel.getRed();
            double g = pixel.getGreen();
            double b = pixel.getBlue();
            
            pixel.setRed(255 - r);
            pixel.setGreen(255 - g);
            pixel.setBlue(255 - b);
        }
        
    }
    
    /**
     * Apply multi-colored vertical stripes to thirds of the image
     */
    public void multicolored()
    {
        pixelList = this.getPixels();
        
        int width = pixelList.length;
        int height = pixelList[0].length;
        
        //First third of the image should have max red values
        for (int x = 0; x < width / 3; x++)
        {
            for (int y = 0; y < height; y++)
            {
                pixelList[x][y].setRed(255);
            }
        }
        
        //Second third of the image should have max green values
        for (int x = width / 3; x < (width * 2 / 3); x++)
        {
            for (int y = 0; y < height; y++)
            {
                pixelList[x][y].setGreen(255);
            }
        }
        
        //Last third of the image should have max blue values
        for (int x = (width * 2 / 3); x < width; x++)
        {
            for (int y = 0; y < height; y++)
            {
                pixelList[x][y].setBlue(255);
            }
        }
    }
    
    /**
     * Increase the brightness of this image by 20% 
     * by increasing the red, green, and blue components 
     * of every pixel by this factor.
     */
    public void brighten()
    {
        for (Pixel pixel : this)
        {
            double r = pixel.getRed() + (pixel.getRed() * 0.2);
            double g = pixel.getGreen() + (pixel.getGreen() * 0.2);
            double b = pixel.getBlue() + (pixel.getBlue() * 0.2);
        
            pixel.setRed(r);
            pixel.setGreen(g);
            pixel.setBlue(b);
        }
    }
    
    /**
     * Decrease the brightness of this image by 20% 
     * by decreasing the red, green, and blue components 
     * of every pixel by this factor.
     */
    public void dim()
    {
        for (Pixel pixel : this)
        {
            double r = pixel.getRed() - (pixel.getRed() * 0.2);
            double g = pixel.getGreen() - (pixel.getGreen() * 0.2);
            double b = pixel.getBlue() - (pixel.getBlue() * 0.2);
        
            pixel.setRed(r);
            pixel.setGreen(g);
            pixel.setBlue(b);
        }
    }
    
    /**
     * Flip the image horizontally
     * 
     * @return a new TransformablePicture that represents 
     * the mirror image (horizontally) of this picture.
     */
    public TransformablePicture flipHorizontal()
    {
        //Get the dimensions of the image
        int width = this.getImageWidth();
        int height = this.getImageHeight();
        
        //Make a new transformable picture that will be flipped
        TransformablePicture transformed = 
            new TransformablePicture(width, height);
        
        //2D array to access the pixels of the original image    
        pixelList = this.getPixels();
        
        //2D array to store the flipped pixels of the original image
        Pixel[][] flippedList = transformed.getPixels();
        
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                //Flipping the pixels and assigning it to the
                //appropriate array
                flippedList[i][j] = pixelList[width - 1 - i][j];
                Pixel currentPix = new Pixel(transformed, i, j);
                
                //Asign the value of the flipped pixel to the new picture
                currentPix.setColor(flippedList[i][j].getColor());
            }
        }
        
        return transformed;
    }
    
    /**
     * Flip the image vertically
     * 
     * @return a new TransformablePicture that represents 
     * the mirror image (vertically) of this picture.
     */
    public TransformablePicture flipVertical()
    {
        //Get the dimensions of the image
        int width = this.getImageWidth();
        int height = this.getImageHeight();
        
        //Make a new transformable picture that will be flipped
        TransformablePicture transformed = 
            new TransformablePicture(width, height);
        
        //2D array to access the pixels of the original image 
        pixelList = this.getPixels();
        
        //2D array to store the flipped pixels of the original image
        Pixel[][] flippedList = transformed.getPixels();
        
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                //Flipping the pixels and assigning it to the
                //appropriate array
                flippedList[i][j] = pixelList[i][height - 1 - j];
                Pixel currentPix = new Pixel(transformed, i, j);
                
                //Asign the value of the flipped pixel to the new picture
                currentPix.setColor(flippedList[i][j].getColor());
            }
        }
        
        return transformed;
    }
    
    
    
    /**
     * This method takes another Picture object as a parameter 
     * and composes it on top of the current picture using alpha blending
     * 
     * @param picture the picture to be blended on top
     */
    public void alphaBlend(Picture picture)
    {
        //Get the pixels from both pictures and store
        //them into 2D arrays
        pixelList = this.getPixels();
        Pixel[][] topPic = picture.getPixels();
        
        //Find the boundaries to do alpha blending
        int width = Math.min(this.getImageWidth(), picture.getImageWidth());
        int height = Math.min(this.getImageHeight(), picture.getImageHeight());
        
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                //Get the pixel to modify from the bottom picture
                Pixel pixel1 = pixelList[i][j];
                //Get the pixel to modify from the top picture
                Pixel pixel2 = topPic[i][j];
                
                //Get the alpha value of the top pixel
                int alphaValue = pixel2.getAlpha();
                
                //Find the alpha percents of the top and bottom pictures
                double topAlphaPercent = alphaValue / 255.0;
                double bottomAlphaPercent = (255 - alphaValue) / 255.0;
                
                //Set the new values for the 3 colors on the bottom
                //image's pixel
                pixel1.setRed( (pixel2.getRed() * topAlphaPercent) +
                    (pixel1.getRed() * bottomAlphaPercent) );
                pixel1.setGreen( (pixel2.getGreen() * topAlphaPercent) +
                    (pixel1.getGreen() * bottomAlphaPercent) );
                pixel1.setBlue( (pixel2.getBlue() * topAlphaPercent) +
                    (pixel1.getBlue() * bottomAlphaPercent) );     
            }
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
