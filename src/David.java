import javax.swing.ImageIcon;

public class David extends Sprite{

   private int numStones;

   public David() {
      super();
      image = new ImageIcon("david.png");
      numStones = 0;
   }
   
   //a method where numstones will be added everytime pickUpStone is called in the Main class
   public void pickupStone() {
      this.numStones += 1;
   }
   
   //a condition if numStones is equal to 5
   public boolean isArmed() {
      if (this.numStones == 5) {
         return true;
      } else {
         return false;
      }
   }

   //a method where it will reset the numStones to 0
   public void reset() {
      this.numStones = 0;
   }

}
