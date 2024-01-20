package voyage.exception;

import voyage.Composit;

public class LackException extends Exception{
    Composit compo;

    public LackException(Composit compo){
        super();
        this.setCompo(compo);
    }

    public Composit getCompo() {
        return compo;
    }

    public void setCompo(Composit compo) {
        this.compo = compo;
    }
    
}
