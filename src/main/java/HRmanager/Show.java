
package HRmanager;
abstract  class showMessage{
 public abstract void Message();
}
public class Show extends showMessage{
    
    @Override
    public void Message(){
        System.out.println("All data saved successfully");
    };
    
    public static void main(String[] args) {
        
   
    }
}
