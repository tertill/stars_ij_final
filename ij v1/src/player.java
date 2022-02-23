public class player {

    private int health;
    private int maxHealth;

    private item[] bag;
    private Integer bagSpace;

    private Integer armor;

    private Integer fatigue;

    private Integer sauce;

    private quest[] quests;

    private String time;
    private int actions;

    public player(int bs) {
        health = 3;
        maxHealth = health;

        bagSpace = bs;
        bag = new item[bagSpace];

        armor = 0;

        fatigue = 0;

        sauce = 21;

        quests = new quest[8];

        actions = 0;

        time = "dawn";
    }

    //addItem(item i): adds an item if the item is of type one (an actual item)
    public void addItem (item it) {
        int track = 0;

        if (it.getType() == 1) {
            for (int i = 0; i < bagSpace; i++) {
                if (bag[i] == null && track == 0) {
                    bag[i] = it;

                    track++;
                }
            }
        }
    }

    //getBag(): returns the bag array
    public item[] getBag() {
        return bag;
    }

    //printBag(): prints the bag array
    public void printBagWP() {
        System.out.println("//--------------------");

        int nullTracker = 0;

        for (int i = 0; i < bagSpace; i++) {
            if (bag[i] != null) {
                System.out.println("your bag: \n" + i + ". " +bag[i].getName() + ": " + bag[i].getEff());
            } else { // the spot is empty
                nullTracker++; // record that
            }
        }

        System.out.println("you have " + nullTracker + " empty spots");

        System.out.println("\n[x + enter] throw something away");
        System.out.print("[f + enter] interact with something\n[e + enter] leave bag\nwhat will you do?: ");

    }

    public void printBag() {
        System.out.println("//--------------------");

        int nullTracker = 0;

        for (int i = 0; i < bagSpace; i++) {
            if (bag[i] != null) {
                System.out.println("your bag: \n" + i + ". " +bag[i].getName() + ": " + bag[i].getEff());
            } else { // the spot is empty
                nullTracker++; // record that
            }
        }

        System.out.println("you have " + nullTracker + " empty spots");

    }

    //getBagSpace(): returns the amount of space in the bag
    public Integer getBagSpace() {
        return bagSpace;
    }

    //setBagSpace(): sets bagSpace value to specified int
    public void setBagSpace(int i) {
        bagSpace = i;
    }

    //getFat(): returns the fatigue value
    public Integer getFat() {
        return fatigue;
    }

    //setFat(): sets the fatigue value
    public void setFat(int i) {
        fatigue = i;
    }

    //setHP(): sets health value
    public void setHP(int i) {
        health = i;
    }

    //getHP(): gets health value
    public Integer getHP() {
        return health;
    }

    //getSauce: returns the sause value
    public Integer getSauce() {
        return sauce;
    }

    //setSause: sets the sause value
    public void setSauce(int i) {
        sauce = i;
    }

    //speak(): speaks
    public String speak(String s) {
        return "// you: " + s;
    }

    //getMhealth(): returns the max health value
    public Integer getMhealth() {
        return maxHealth;
    }

    //setMhealth(): sets the max health value to the specified int
    public void setMhealth(int i) {
        maxHealth = i;
    }

    //addQuest(): adds a quest object to the players quests[]
    public void addQuest(quest q) {
        int track = 0;

        for (int i = 0; i < quests.length; i++) {
            if (quests[i] == null && track == 0) {
                quests[i] = q;

                track++;
            }
        }
    }

    //getQuests(): returns the quests array
    public quest[] getQuests() {
        return quests;
    }

    //getTime(): returns the time
    public String getTime() {
        return time;
    }

    //setTime(): sets the time to the string parameter
    public void setTime(String s) {
        time = s;
    }

    //getActions(): returns how many actions the player has done
    public int getActions() {
        return actions;
    }

    //setActions(): sets the actions int to the int parameter
    public void setActions(int i) {
        actions = i;
    }

    //printQuests(): prints out the quests the player has atm
    public void printQuests() {

        System.out.println("main quest: ");
        if (quests[0] != null) {
            System.out.println(quests[0].getName() + ":\n" + quests[0].getDesc());
        }


        System.out.println("\nextra quests: ");
        for (int i = 1; i < quests.length; i++) {

            if (quests[i] != null) {
                System.out.println(quests[i].getName() + ":\n" + quests[i].getDesc());
            }


        }

    }

}
