package voyage.exception;

import java.util.ArrayList;

import voyage.Composit;

public class LackExceptions extends Exception {
    ArrayList<Composit> compo;

    public LackExceptions(ArrayList<Composit> compo){
        super();
        this.setCompo(compo);
    }

    public ArrayList<Composit> getCompo() {
        return compo;
    }

    public void setCompo(ArrayList<Composit> compo) {
        this.compo = compo;
    }
    
}

