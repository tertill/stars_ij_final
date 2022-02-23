public class building {
    private String name;

    private item[] elements;

    private player[] home;

    private Integer locatecode;

    public building(String n, int lc) {
        name = n;

        elements = new item[6];

        home = new player[1];

        locatecode = lc;
    }

    public String getName() {
        return name;
    }

    //element methods

    //addItem(item i): adds an item to the elements array
    public void addItem (item it) {
        int track = 0;

        for (int i = 0; i < 6; i++) {
            if (elements[i] == null && track == 0) {
                elements[i] = it;

                track++;
            }
        }
    }

    //getItems(): returns elements array
    public item[] getItems() {
        return elements;
    }

    //isHere(): returns true if player is here, false if not
    public boolean isHere() {
        if (home[0] != null) {
            return true;
        } else {
            return false;
        }
    }

    //getHome(): returns home[]
    public player[] getHome() {
        return home;
    }

    //getPlayer(): returns the player
    public player getPlayer() {
        return home[0];
    }
    public player getPlayer(player p) {
        return p;
    }

    //removePlayer(): removes the player
    public player removePlayer() {
        player holder;
        holder = getPlayer(getPlayer());

        home[0] = null;

        return holder;
    }

    //count(): returns how many items are in the elements array
    public int count() {
        int tracker = 0;

        for (int i = 0; i < 6; i++) {
            if (elements[i] != null) {
                tracker++;
            }
        }

        return tracker;
    }


    //options(): loops through elements[] and prints out interaction statements for each element, while also requiring the area its nested in
    public void options(area a, player p, int d, String time) {
        System.out.println("//--------------------\n");
        System.out.println(a.getName() + ": " + name);

        System.out.println("health [ " + p.getHP() + "/" + p.getMhealth() + " ]");
        System.out.println("fatigue [ " + p.getFat() + "/5 ]\nday [ " + d + " ]\ntime [ " + time + " ]\n");

        for (int i = 0; i < 6; i++) { // loop through the elements array, look for items, check the type, then print out the corresponding interaction statement

            if (elements[i] != null) { // if theres an item at this spot

                if (elements[i].getType() == 1) { // if the items an interactable

                    System.out.println("[" + i + " + enter] interact with  " + elements[i].getName());

                } else { // the items an npc, so...

                    System.out.println("[" + i + " + enter] talk to " + elements[i].getName());

                }

            }
        }

        if (name.equals("your ship")) {
            System.out.println("[x + enter] leave " + name + "\n[e + enter] check bag\n[q + enter] view quests"); // print out extra statements
            System.out.println("[s + enter] sleep in the comfy bed (save progress and advance to the next day)\n");
        } else {
            System.out.println("[x + enter] leave " + name + "\n[e + enter] check bag\n[q + enter] view quests\n"); // print out extra statements
        }

        System.out.print("what will you do? ");
    }

    public Integer getNumCode() {
        // TODO Auto-generated method stub
        return locatecode;
    }
}
