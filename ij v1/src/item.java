public class item {

    private String name;
    private String effect;

    private String inter;
    private String intereff;
    // +1H

    private int type;

    //npc specific
    private String idle1;
    private String idle2;
    private String idle3;

    private boolean hasTalked;

    public item (int vT, String n, String d) {
        type = vT;
        name = n;
        effect = d;
    }

    //universal methods

    public String getName() {
        return name;
    }

    public String getEff() {
        return effect;
    }

    public int getType() {
        return type;
    }

    //type 2 methods (npc methods)
    public void setIdles(String i1, String i2, String i3) {
        if (type == 2) {
            idle1 = i1;
            idle2 = i2;
            idle3 = i3;
        }
    }

    //speak(): returns a string with a properly formatted dialogue, only if this object is of type 2 (its an npc obj)
    public String speak(String s) {
        if (type == 2) {
            String dialogue = name + ": " + s;

            return dialogue;
        }
        return null;
    }

    //idle(): returns a randomly selected idle string, all 3 idle strings must have a string in them, and this object must be of type 2 (an npc obj)
    public String idle() {
        if (!(idle1.equals(null)) && !(idle2.equals(null)) && !(idle3.equals(null))) {
            if (type == 2) {
                int rand = (int)(Math.random() * (4 - 1) + 1);

                if (rand == 1) {
                    return speak(idle1);
                } else if (rand == 2) {
                    return speak(idle2);
                } else {
                    return speak(idle3);
                }
            }
        }

        return null;
    }

    //setspoken(): changes the hasTalked boolean to the specified boolean
    public void setspoken(boolean b) {
        hasTalked = b;
    }

    //hasTalked(): returns the hastalked boolean
    public boolean hasTalked() {
        return hasTalked;
    }

    //type 1 methods (interactable methods)

    //setInterType(): sets the inter value to the specified string - specifies the type that the interactable is supposed to be (foodstuffs or pins)
    public void setInterType(String s) {
        if (type == 1) {
            inter = s;
        }
    }

    //getInterType(): returns the inter value
    public String getInterType() {
        //if (type == 1) {
        return inter;
        //} else {
        //return null;
        //}
    }

    //getInterEff(): returns the interEff value
    public String getInterEff() {
        //if (type == 1) {
        return intereff;
        //} else {
        //return null;
    }

    //setInterEff(): sets the interEff value to the specified string
    public void setInterEff(String s) {
        intereff = s;
    }

}
