import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu and Ishaan Gupta
 * 2-13-20
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (int r = 0; r < pixels.length; r++)
    {
      for (int c = 0; c < pixels[r].length; c++)
      {
        pixels[r][c].setBlue(0);
      }
    }
  }
  
  /** Method to negate every color by finding 255 - color*/
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(255 - pixelObj.getBlue());
        pixelObj.setRed(255 - pixelObj.getRed());
        pixelObj.setGreen(255 - pixelObj.getGreen());
      }
    }
  }
  
  /** Method to make a grayscale using the avg of the colors*/
  public void grayScale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        double avg = (pixelObj.getBlue() + pixelObj.getRed() + pixelObj.getGreen())/3.0;
        pixelObj.setBlue((int)avg);
        pixelObj.setRed((int)avg);
        pixelObj.setGreen((int)avg);
      }
    }
  }
  
  /** Method to make the fish more clearer */
  public void fixUnderwater()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        if (pixelObj.getBlue() < pixelObj.getGreen())
        {
            pixelObj.setBlue(pixelObj.getGreen());
            pixelObj.setRed(pixelObj.getGreen());
            //pixelObj.setGreen(pixelObj.getGreen()/2);
        }
      }
    }
  }
  
  /** Method to change the colors on the extremes */
  public void extreme()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        if (pixelObj.getBlue() < 128)
        {
            pixelObj.setBlue(0);
        }
        else
        {
			pixelObj.setBlue(255);
		}
		if (pixelObj.getGreen() < 128)
        {
            pixelObj.setGreen(0);
        }
        else
        {
			pixelObj.setGreen(255);
		}
		if (pixelObj.getRed() < 128)
        {
            pixelObj.setRed(0);
        }
        else
        {
			pixelObj.setRed(255);
		}
      }
    }
  }
  
  /** Method to keep only the blue */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setGreen(0);
        pixelObj.setRed(0);
      }
    }
  }
  
  /** Method to pixelate an image in 20x20 squares */
  public void pixelate()
  {
    Pixel[][] pixels = this.getPixels2D();
    //Pixel[][] pixels2 = this.getPixels2D();
    
    
	for (int i = 0; i < pixels.length; i+=20)
    {
		for (int j = 0; j < pixels[i].length; j+=20)
		{
			int sum1 = 0;
			int sum2 = 0;
			int sum3 = 0;
			int count = 0;
			
			for (int r = i; r < i+20 && r < pixels.length; r++)
			{
				for (int c = j; c < j+20 && c < pixels[r].length; c++)
				{
					sum1 += pixels[r][c].getRed();
					sum2 += pixels[r][c].getGreen();
					sum3 += pixels[r][c].getBlue();
					count++;
				}
			}
			
			//System.out.println((sum1/20) + " " + (sum2/20) + " " + (sum3/20));
			
			for (int a = i; a < i+20 && a < pixels.length; a++)
			{
				for (int b = j; b < j+20 && b < pixels[a].length; b++)
				{
					pixels[a][b].setRed(sum1/count);
					pixels[a][b].setGreen(sum2/count);
					pixels[a][b].setBlue(sum3/count);
					
				}
			}
		}
	}
	//pixels = pixels2;
    
  }
  
  public void doubleBlue()
  {
	Pixel [][] pixels = this.getPixels2D();
	
	for (int r = 0; r < pixels.length/2; r++)
	{
		for (int c = 0; c < pixels[0].length; c++)
		{
			pixels[r][c].setBlue(pixels[r][c].getBlue()*2);
		}
	}
  }
  
  /** Method to blur an image */
  public void blur()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel[][] pixels2 = this.getPixels2D();
    
    Pixel[][] newPixels = this.getPixels2D();
    
	for (int i = 0; i < pixels.length; i++)
    {
		for (int j = 0; j < pixels[i].length; j++)
		{
			int sum1 = 0;
			int sum2 = 0;
			int sum3 = 0;
			int count = 0;
			
			for (int r = -5; r <= 5; r++)
			{
				for (int c = -5; c <= 5; c++)
				{
					if ((i+r >= 0) && (i+r < pixels.length) && (j+c >= 0) && (j+c < pixels[0].length))
					{
						//System.out.println((i+r) + " " + (j + c));
						
						sum1 += pixels[i + r][j + c].getRed();
						sum2 += pixels[i + r][j + c].getGreen();
						sum3 += pixels[i + r][j + c].getBlue();
						count++;
					}
				}
			}
			
			
			
			
			
			pixels2[i][j].setRed(sum1/count);
			pixels2[i][j].setGreen(sum2/count);
			pixels2[i][j].setBlue(sum3/count);
		}
    }
    pixels = pixels2;
  }
  
  /** Method to enhance an image */
  public void enhance()
  {
    Pixel[][] pixels = this.getPixels2D();
    
    Pixel[][] pixels2 = this.getPixels2D();
    
	for (int i = 0; i < pixels.length; i++)
    {
		for (int j = 0; j < pixels[i].length; j++)
		{
			int sum1 = 0;
			int sum2 = 0;
			int sum3 = 0;
			int count = 0;
			
			for (int r = -5; r <= 5; r++)
			{
				for (int c = -5; c <= 5; c++)
				{
					if ((i+r >= 0) && (i+r < pixels.length) && (j+c >= 0) && (j+c < pixels[0].length))
					{
						//System.out.println((i+r) + " " + (j + c));
						
						sum1 += pixels[i + r][j + c].getRed();
						sum2 += pixels[i + r][j + c].getGreen();
						sum3 += pixels[i + r][j + c].getBlue();
						count++;
					}
				}
			}
			
			
			
			pixels2[i][j].setRed( 2 * pixels[i][j].getRed() - sum1/count);
			pixels2[i][j].setGreen( 2 * pixels[i][j].getGreen() - sum2/count);
			pixels2[i][j].setBlue( 2 * pixels[i][j].getBlue() - sum3/count);
			
			
		}
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Method that flips and image around the vertical axis */
  public void flipHoriz()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        Color temp = leftPixel.getColor();
        leftPixel.setColor(rightPixel.getColor());
        rightPixel.setColor(temp);
       
        
      }
    } 
  }
  
  /** Method that flips and image around the horizontal axis */
  public void flipVert()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel botPixel = null;
    
    int height = pixels.length;
    
    for (int row = 0; row < height/2; row++)
    {
      for (int col = 0; col < pixels[row].length; col++)
      {
        topPixel = pixels[row][col];
        botPixel = pixels[height-1-row][col];
        Color temp = topPixel.getColor();
        topPixel.setColor(botPixel.getColor());
        botPixel.setColor(temp);
      }
    }
  }
  
  /** Method that flips an image around a diagonal*/
  public void mirrorDiagonal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    int height = pixels.length;
    int length = Math.min(width, height);
    
    for (int row = 0; row < length; row++)
    {
      for (int col = 0; col < length; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[col][row];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors an image around the horizontal axis */
  public void mirrorHorizontal()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel botPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height/2; row++)
    {
      for (int col = 0; col < pixels[row].length; col++)
      {
        topPixel = pixels[row][col];
        botPixel = pixels[height-1-row][col];
        botPixel.setColor(topPixel.getColor());
      }
    } 
  }
  
  /** Method that uses a green screen effect to create a new image */
  public void greenScreen(Picture background)
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel[][] pixelsBg = background.getPixels2D();
    Pixel pixel = null;
    
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < pixels[row].length; col++)
      {
        pixel = pixels[row][col];
        if (pixel.getGreen() > pixel.getRed() + pixel.getBlue() && pixel.getGreen() > 50)
        {
			pixel.setColor(pixelsBg[row][col].getColor());
		}
      }
    }
    
  }
  
  /** Method that mirrors an image from the bottom to the top */
  public void mirrorHorizontalBotToTop()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel topPixel = null;
    Pixel botPixel = null;
    int height = pixels.length;
    for (int row = 0; row < height/2; row++)
    {
      for (int col = 0; col < pixels[row].length; col++)
      {
        topPixel = pixels[row][col];
        botPixel = pixels[height-1-row][col];
        topPixel.setColor(botPixel.getColor());
      }
    } 
  }
  
  /** Method that mirrors an image from the right to the left */
  public void mirrorVerticalRightToLeft()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        leftPixel.setColor(rightPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edge(int edgeDist)
  {
    Pixel pixel = null;
    Pixel rightPixel = null;
    Pixel botPixel = null;
    
    Pixel[][] pixels = this.getPixels2D();
    Color color = null;
    
    for (int row = 0; row < pixels.length-1; row++)
    {
      for (int col = 0; col < pixels[0].length-1; col++)
      {
        pixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        botPixel = pixels[row+1][col];
        color = pixel.getColor();
        
        if ((rightPixel.colorDistance(color) > edgeDist) || (botPixel.colorDistance(color) > edgeDist))
          pixel.setColor(Color.BLACK);
        else
          pixel.setColor(Color.WHITE);
      }
    }
    
  }
  
  public Picture resize(double scale)
  {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture ((int)(pixels.length * scale), (int)(pixels[0].length*scale));
	Pixel [][] resultPixels = result.getPixels2D();
	
	for (int r = 0; r < resultPixels.length; r++)
	{
		for (int c = 0; c < resultPixels[0].length; c++)
		{
			resultPixels[r][c].setColor(pixels[(int)(r/scale)][(int)(c/scale)].getColor());
		}
	}
	return result;
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("peacock.jpg");
    beach.explore();
    //Picture test = beach.resize(0.9);
    beach.doubleBlue();
    beach.explore();
    //test.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
