public class area {
    private String name;
    private String subname;

    private building[] rooms;

    private player[] home;

    // holds the quests, quests[0] is the main quest, quests[1] is the extra quest
    private quest[] quests;

    private String areacode;

    public area (String n, String s, int ic, Integer ac) {
        name = n;
        subname = s;

        rooms = new building[5];

        home = new player[1];

        quests = new quest[2];

        areacode = ac.toString();
    }

    public String getName() {
        return name;
    }

    //getQuests(): returns the quests array
    public quest[] getQuests() {
        return quests;
    }

    //addQuests(): adds a quest object to the quests[]
    public void addQuests(quest q, int pos) {

        quests[pos] = q;

    }

    //count(): returns how many buildings are in the rooms array
    public int count() {
        int tracker = 0;

        for (int i = 0; i < 5; i++) {
            if (rooms[i] != null) {
                tracker++;
            }
        }

        return tracker;
    }



    //building methods

    //addBuilding(building b): adds a building obj to rooms[]
    public void addBuilding (building b) {
        int track = 0;

        for (int i = 0; i < 5; i++) {
            if (rooms[i] == null && track == 0) {
                rooms[i] = b;

                track++;
            }
        }
    }

    //getRooms(): returns rooms[]
    public building[] getRooms() {
        return rooms;
    }

    //getHome(): returns home[]
    public player[] getHome() {
        return home;
    }

    //isHere(): returns true if player is here, false if not
    public boolean isHere() {
        if (home[0] != null) {
            return true;
        } else {
            return false;
        }
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

    //getNumCode(): returns the areaCode int, this will be used for the save code
    public String getNumCode() {
        return areacode;
    }

    //options(): loops through rooms[] and prints oput interaction statements for each element
    public void options(player p, int d, String time) {
        System.out.println("//--------------------\n");
        System.out.println(name + ": " + subname);

        System.out.println("health [ " + p.getHP() + "/" + p.getMhealth() + " ]");
        System.out.println("fatigue [ " + p.getFat() + "/5 ]\nday [ " + d + " ]\ntime [ " + time + " ]\n");

        for (int i = 0; i < 5; i++) { // loop through the rooms[], check for building objects, then print out interaction statements for all of them.
            if (rooms[i] != null) { // if theres a building in this spot, then...
                System.out.println("[" + i + " + enter] enter " + rooms[i].getName()); // print out an interaction statement
            }
        }

        System.out.println("[x + enter] leave " + name + "\n[e + enter] check bag\n[q + enter] view quests" + "\n[r + enter] go resource gathering\n");

        System.out.print("what will you do? ");

    }
}
