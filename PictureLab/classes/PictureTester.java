/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson and Ishaan Gupta
 * 2-13-20
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("frozen2.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
    Picture beach = new Picture("frozen2.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  /** Method to test pixelate */
  public static void testPixelate()
  {
    Picture peacock = new Picture("peacock.jpg");
    //peacock.explore();
    peacock.pixelate();
    peacock.explore();
  }
  
  /** Method to test blur */
  public static void testBlur()
  {
    Picture peacock = new Picture("peacock.jpg");
    //peacock.explore();
    peacock.blur();
    peacock.explore();
  }
  
  /** Method to test greenScreen */
  public static void testGreenScreen()
  {
    Picture weatherman = new Picture("weatherman.jpg");
    Picture mountains = new Picture("mountains.jpg");
    //weatherman.explore();
    weatherman.greenScreen(mountains);
    weatherman.explore();
  }
  
  /** Method to test fixUnderwater */
  public static void testFixUnderwater()
  {
    Picture beach = new Picture("water.jpg");
    beach.explore();
    beach.fixUnderwater();
    beach.explore();
  }
  
  /** Method to test negate */
  public static void testNegate()
  {
    Picture beach = new Picture("frozen2.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }
  
  /** Method to test grayScale */
  public static void testGrayscale()
  {
    Picture beach = new Picture("frozen2.jpg");
    beach.explore();
    beach.grayScale();
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test flipVert */
  public static void testFlipVert()
  {
    Picture frozen = new Picture("frozen2.jpg");
    //frozen.explore();
    frozen.flipVert();
    frozen.explore();
  }
  
  /** Method to test flipHoriz */
  public static void testFlipHoriz()
  {
    Picture frozen = new Picture("frozen2.jpg");
    //frozen.explore();
    frozen.flipHoriz();
    frozen.explore();
  }
  
  /** Method to test extreme */
  public static void testExtreme()
  {
    Picture peacock = new Picture("peacock.jpg");
    //peacock.explore();
    peacock.extreme();
    peacock.explore();
  }
  
  /** Method to test mirrorDiagonal */
  public static void testMirrorDiagonal()
  {
    Picture caterpillar = new Picture("beach.jpg");
    caterpillar.explore();
    caterpillar.mirrorDiagonal();
    caterpillar.explore();
  }
  
  /** Method to test mirrorHorizontal */
  public static void testMirrorHorizontal()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontal();
    caterpillar.explore();
  }
  
  /** Method to test mirrorHorizontalBotToTop */
  public static void testMirrorHorizontalBotToTop()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorHorizontalBotToTop();
    caterpillar.explore();
  }
  
  /** Method to test mirrorVerticalRightToLeft */
  public static void testMirrorVerticalRightToLeft()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVerticalRightToLeft();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test enhance */
  public static void testEnhance()
  {
    Picture water = new Picture("water.jpg");
    water.explore();
    water.enhance();
    water.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdge()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edge(20);
    swan.explore();
  }
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testFixUnderwater();
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testNegate();
    //testGrayscale();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    //testMirrorVertical();
    //testMirrorVerticalRightToLeft();
    //testMirrorTemple();
    //testMirrorDiagonal();
    //testMirrorHorizontal();
    //testMirrorHorizontalBotToTop();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testMirrorImageVertical();
    //testMirrorImageHorizontal();
    
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetection2();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
    
    
    //testFlipVert();
    testFlipHoriz();
    //testExtreme();
    //testPixelate();
    //testGreenScreen();
    //testEdge();
    //testBlur();
    //testEnhance();
  }
}
