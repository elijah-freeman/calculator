/*H*****************************************************************************
 * Filename: MyButtonListener.java
 * Description: 
 * Comment:
 * Modified: 2021-05-05	Added header comments.
 * Author: Elijah Freeman (elijah@elijahfreeman.com, elijahfreeman.com)
 ****************************************************************************H*/

import java.util.EventListener;

public interface MyButtonListener extends EventListener  {
    public void buttonEventOccured(MyButtonEvent e);
}
