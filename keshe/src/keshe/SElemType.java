/*
 * SElemType.java
 *
 * Created on 2010/3/30, 12:09
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package keshe;

/**
 *
 * @author kai wang
 */
public class SElemType {
    
    int ord;
    PosType seat;
    int di;
    /** Creates a new instance of SElemType */
    public SElemType() {
    }
      public SElemType( PosType seat, int di){
      
        this.seat = seat;
        this.di = di;
    }
    
    public boolean setDi(int di){
        this.di = di;
        return true;
    }

    
}
